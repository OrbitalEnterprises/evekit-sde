package enterprises.orbital.evekit.sde.ws;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Locale;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import enterprises.orbital.base.OrbitalProperties;
import enterprises.orbital.evekit.sde.AttributeSelector;

public class ServiceUtil {
  private static final Logger                    log                     = Logger.getLogger(ServiceUtil.class.getName());
  private static final String                    sdeString               = OrbitalProperties.getGlobalProperty("enterprises.orbital.evekit.sde.version",
                                                                                                               "DEBUG");
  public static final long                       DEFAULT_EXPIRY_INTERVAL = TimeUnit.MILLISECONDS.convert(24, TimeUnit.HOURS);
  protected static final ThreadLocal<DateFormat> dateFormat              = OrbitalProperties.dateFormatFactory(new OrbitalProperties.DateFormatGenerator() {

                                                                           @Override
                                                                           public DateFormat generate() {
                                                                             return new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss z", Locale.US);
                                                                           }
                                                                         });

  public static Response finish(
                                Object result,
                                HttpServletRequest request) {
    auditAccess(getSource(request), getRequestURI(request));
    ResponseBuilder rBuilder = Response.ok();
    if (result != null) rBuilder = rBuilder.entity(result);
    return stamp(rBuilder).build();
  }

  public static <A extends Object> Response finish(
                                                   Collection<A> result,
                                                   HttpServletRequest request) {
    auditAccess(getSource(request), getRequestURI(request));
    ResponseBuilder rBuilder = Response.ok();
    if (result != null) rBuilder = rBuilder.entity(result);
    return stamp(rBuilder).build();
  }

  public static String getSource(
                                 HttpServletRequest request) {
    return request == null ? "INTERNAL" : request.getRemoteAddr();
  }

  public static String getRequestURI(
                                     HttpServletRequest request) {
    return request == null ? "INTERNAL" : request.getRequestURI();
  }

  protected static String getServerTime(
                                        long tm) {
    return dateFormat.get().format(new Date(tm));
  }

  public static ResponseBuilder stamp(
                                      ResponseBuilder result) {
    long when = OrbitalProperties.getCurrentTime();
    long expiry = when + DEFAULT_EXPIRY_INTERVAL;
    result = result.expires(new Date(expiry));
    return result.header("Date", getServerTime(when)).header("EveKit-SDE-Version", sdeString);
  }

  protected static String join(
                               String delim,
                               String... args) {
    StringBuilder builder = new StringBuilder();
    for (int i = 0; i < args.length; i++) {
      builder.append(args[i]);
      if (i + 1 < args.length) builder.append(delim);
    }
    return builder.toString();
  }

  public static void auditAccess(
                                 String src,
                                 String path) {
    log.fine(join(", ", "AUDIT", "SDEACCESS", "FROM", src, "PATH", path));
  }

  public static void sanitizeAttributeSelector(
                                               AttributeSelector as) {
    // restrict size of string parameters for all settings to less than 200 characters
    if (as.start != null && as.start.length() > 200) as.start = as.start.substring(0, 200);
    if (as.end != null && as.end.length() > 200) as.end = as.end.substring(0, 200);
    // allow at most 500 set members for set selectors and verify strings are not too long
    if (as.values.size() > 0) {
      Set<String> newSet = new HashSet<String>();
      Iterator<String> i = as.values.iterator();
      for (int j = 0; j < 500 && i.hasNext(); j++) {
        String next = i.next();
        if (next.length() > 200) next = next.substring(0, 200);
        newSet.add(next);
      }
      as.values = newSet;
    }
  }

  public static void sanitizeAttributeSelector(
                                               AttributeSelector... as) {
    for (AttributeSelector next : as)
      sanitizeAttributeSelector(next);
  }

}
