package enterprises.orbital.evekit.sde.inv;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Embeddable;

/**
 * The primary key class for the invtypereactions database table.
 * 
 */
@Embeddable
public class InvTypeReactionPK implements Serializable {
  // default serial version id, required for serializable classes.
  private static final long serialVersionUID = 1L;

  private int               reactionTypeID;
  private boolean              input;
  private int               typeID;

  public InvTypeReactionPK() {}

  public InvTypeReactionPK(int reactionTypeID, boolean input, int typeID) {
    super();
    this.reactionTypeID = reactionTypeID;
    this.input = input;
    this.typeID = typeID;
  }

  public int getReactionTypeID() {
    return this.reactionTypeID;
  }

  public boolean isInput() {
    return this.input;
  }

  public int getTypeID() {
    return this.typeID;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    InvTypeReactionPK that = (InvTypeReactionPK) o;
    return reactionTypeID == that.reactionTypeID &&
        input == that.input &&
        typeID == that.typeID;
  }

  @Override
  public int hashCode() {

    return Objects.hash(reactionTypeID, input, typeID);
  }

  @Override
  public String toString() {
    return "InvTypeReactionPK [reactionTypeID=" + reactionTypeID + ", input=" + input + ", typeID=" + typeID + "]";
  }

}