package enterprises.orbital.evekit.sde;

import java.util.HashSet;
import java.util.Set;

import com.google.gson.Gson;

/**
 * Model data attribute selector. Precedence works as follows:
 * <ol>
 * <li>If any == true, then all other values are ignored and this becomes a wildcard selector.
 * <li>If any == false and like != null, then all other values are ignored and this becomes a like selector.
 * <li>If any == false and like == null and !value.isEmpty(), then all other values are ignored and this becomes a set selector.
 * <li>If any == false and like == null and value.isEmpty() and start != null and end != null, then this becomes a range selector.
 * <li>Otherwise, this becomes a wildcard selector.
 * </ol>
 */
public class AttributeSelector {
  public static enum SelectorType {
                                   WILDCARD,
                                   SET,
                                   RANGE,
                                   LIKE;
  }

  /**
   * If true, allow any value for the attribute associated with this selector.
   */
  public boolean     any;
  /**
   * If not-null, make this a string based "like" selector. Ignored for non-string attributes.
   */
  public String      like;
  /**
   * If non-empty, only select elements in which the attribute has a value which is a member of the given set. Attributes are converted to the appropriate type
   * (an error is thrown if the conversion fails).
   */
  public Set<String> values = new HashSet<String>();
  /**
   * The lower bound of a range selector.
   */
  public String      start;
  /**
   * The upper bound of a range selector.
   */
  public String      end;

  @SuppressWarnings("unused")
  private AttributeSelector() {}

  public AttributeSelector(String json) {
    Gson gson = new Gson();
    AttributeSelector convert = gson.fromJson(json, AttributeSelector.class);
    copy(convert);
  }

  public static AttributeSelector makeAnySelector() {
    return new AttributeSelector("{any: true}");
  }

  public void copy(
                   AttributeSelector other) {
    this.any = other.any;
    this.like = other.like;
    this.values.addAll(other.values);
    this.start = other.start;
    this.end = other.end;
  }

  public SelectorType type() {
    if (any) return SelectorType.WILDCARD;
    if (like != null) return SelectorType.LIKE;
    if (!values.isEmpty()) return SelectorType.SET;
    if (start != null && end != null) return SelectorType.RANGE;
    return SelectorType.WILDCARD;
  }

  public String getLikeValue() {
    return like;
  }

  public Set<String> getStringValues() {
    return values;
  }

  public Set<Long> getLongValues() {
    Set<Long> result = new HashSet<Long>();
    for (String next : values) {
      result.add(Long.parseLong(next));
    }
    return result;
  }

  public Set<Integer> getIntValues() {
    Set<Integer> result = new HashSet<Integer>();
    for (String next : values) {
      result.add(Integer.parseInt(next));
    }
    return result;
  }

  public Set<Double> getDoubleValues() {
    Set<Double> result = new HashSet<Double>();
    for (String next : values) {
      result.add(Double.parseDouble(next));
    }
    return result;
  }

  public String getStringStart() {
    return start;
  }

  public String getStringEnd() {
    return end;
  }

  public long getLongStart() {
    return Long.parseLong(start);
  }

  public long getLongEnd() {
    return Long.parseLong(end);
  }

  public int getIntStart() {
    return Integer.parseInt(start);
  }

  public int getIntEnd() {
    return Integer.parseInt(end);
  }

  public double getDoubleStart() {
    return Double.parseDouble(start);
  }

  public double getDoubleEnd() {
    return Double.parseDouble(end);
  }

  public static void addLifelineSelector(
                                         StringBuilder builder,
                                         String target,
                                         AttributeSelector at) {
    switch (at.type()) {
    case SET:
      // Return items which were live at the given selected points in time
      builder.append(" AND (");
      for (long l : at.getLongValues()) {
        builder.append("(").append(target).append(".lifeStart <= ").append(l).append(" AND ").append(target).append(".lifeEnd > ").append(l).append(") OR");
      }
      builder.setLength(builder.length() - 3);
      builder.append(")");
      break;
    case RANGE:
      // Fetch all values live in the given range
      // lifeStart <= max and lifeEnd > min
      long max = at.getLongEnd();
      long min = at.getLongStart();
      builder.append(" AND ").append(target).append(".lifeStart <= ").append(max);
      builder.append(" AND ").append(target).append(".lifeEnd > ").append(min);
      break;
    case WILDCARD:
    case LIKE:
    default:
      // No constraint, skip
      break;
    }
  }

  public static void addIntSelector(
                                    StringBuilder builder,
                                    String target,
                                    String column,
                                    AttributeSelector as) {
    switch (as.type()) {
    case SET:
      builder.append(" AND ").append(target).append(".").append(column).append(" IN (");
      for (int l : as.getIntValues()) {
        builder.append(l).append(", ");
      }
      builder.setLength(builder.length() - 2);
      builder.append(")");
      break;
    case RANGE:
      builder.append(" AND ").append(target).append(".").append(column).append(" >= ").append(as.getIntStart()).append(" AND ").append(target).append(".")
          .append(column).append(" <= ").append(as.getIntEnd());
      break;
    case WILDCARD:
    case LIKE:
    default:
      // No constraint, skip
      break;
    }
  }

  public static void addLongSelector(
                                     StringBuilder builder,
                                     String target,
                                     String column,
                                     AttributeSelector as) {
    switch (as.type()) {
    case SET:
      builder.append(" AND ").append(target).append(".").append(column).append(" IN (");
      for (long l : as.getLongValues()) {
        builder.append(l).append(", ");
      }
      builder.setLength(builder.length() - 2);
      builder.append(")");
      break;
    case RANGE:
      builder.append(" AND ").append(target).append(".").append(column).append(" >= ").append(as.getLongStart()).append(" AND ").append(target).append(".")
          .append(column).append(" <= ").append(as.getLongEnd());
      break;
    case WILDCARD:
    case LIKE:
    default:
      // No constraint, skip
      break;
    }
  }

  public static void addDoubleSelector(
                                       StringBuilder builder,
                                       String target,
                                       String column,
                                       AttributeSelector as) {
    switch (as.type()) {
    case SET:
      builder.append(" AND ").append(target).append(".").append(column).append(" IN (");
      for (double l : as.getDoubleValues()) {
        builder.append(l).append(", ");
      }
      builder.setLength(builder.length() - 2);
      builder.append(")");
      break;
    case RANGE:
      builder.append(" AND ").append(target).append(".").append(column).append(" >= ").append(as.getDoubleStart()).append(" AND ").append(target).append(".")
          .append(column).append(" <= ").append(as.getDoubleEnd());
      break;
    case WILDCARD:
    case LIKE:
    default:
      // No constraint, skip
      break;
    }
  }

  public static void addStringSelector(
                                       StringBuilder builder,
                                       String target,
                                       String column,
                                       AttributeSelector as,
                                       AttributeParameters p) {
    switch (as.type()) {
    case SET:
      builder.append(" AND ").append(target).append(".").append(column).append(" IN (");
      for (String l : as.getStringValues()) {
        String next = ":" + p.addStringParam(l);
        builder.append(next).append(", ");
      }
      builder.setLength(builder.length() - 2);
      builder.append(")");
      break;
    case RANGE:
      String minParam = ":" + p.addStringParam(as.getStringStart());
      String maxParam = ":" + p.addStringParam(as.getStringEnd());
      builder.append(" AND ").append(target).append(".").append(column).append(" >= ").append(minParam).append(" AND ").append(target).append(".")
          .append(column).append(" <= ").append(maxParam);
      break;
    case LIKE:
      // Like clause. Attribute should contain any needed wildcards.
      String likeParam = ":" + p.addStringParam(as.getLikeValue());
      builder.append(" AND ").append(target).append(".").append(column).append(" LIKE ").append(likeParam);
      break;
    case WILDCARD:
    default:
      // No constraint, skip
      break;
    }
  }

  public static void addBooleanSelector(
                                        StringBuilder builder,
                                        String target,
                                        String column,
                                        AttributeSelector as) {
    switch (as.type()) {
    case SET:
      // Only take the first value in the set (according to the iterator). This value determines the value for comparison.
      boolean value = Boolean.valueOf(as.getStringValues().iterator().next());
      builder.append(" AND ").append(target).append(".").append(column).append(" = ").append(value);
      break;
    case RANGE:
    case LIKE:
    case WILDCARD:
    default:
      // No constraint, skip
      break;
    }
  }

  public static void addSetLongSelector(
                                        StringBuilder builder,
                                        String target,
                                        String column,
                                        AttributeSelector as) {
    switch (as.type()) {
    case SET:
      // Check that at least one set member is a member of the long valued target collection
      builder.append(" AND SOME ELEMENTS(").append(target).append(".").append(column).append(") IN (");
      for (long next : as.getLongValues()) {
        builder.append(next).append(", ");
      }
      builder.setLength(builder.length() - 2);
      builder.append(")");
      break;
    case RANGE:
      // Check that some of the elements of the set are within the range.
      long min = as.getLongStart();
      long max = as.getLongEnd();
      builder.append(" AND SOME ELEMENTS(").append(target).append(".").append(column).append(") BETWEEN ").append(min).append(" AND ").append(max);
      break;
    case LIKE:
    case WILDCARD:
    default:
      // No constraint, skip
      break;
    }
  }

}
