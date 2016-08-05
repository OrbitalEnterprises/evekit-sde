package enterprises.orbital.evekit.sde.inv;

import java.io.Serializable;

import javax.persistence.Column;
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
  @Column(
      name = "inputColumn")
  private byte              input;
  private int               typeID;

  public InvTypeReactionPK() {}

  public InvTypeReactionPK(int reactionTypeID, byte input, int typeID) {
    super();
    this.reactionTypeID = reactionTypeID;
    this.input = input;
    this.typeID = typeID;
  }

  public int getReactionTypeID() {
    return this.reactionTypeID;
  }

  public byte getInput() {
    return this.input;
  }

  public int getTypeID() {
    return this.typeID;
  }

  @Override
  public boolean equals(
                        Object other) {
    if (this == other) { return true; }
    if (!(other instanceof InvTypeReactionPK)) { return false; }
    InvTypeReactionPK castOther = (InvTypeReactionPK) other;
    return (this.reactionTypeID == castOther.reactionTypeID) && (this.input == castOther.input) && (this.typeID == castOther.typeID);
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int hash = 17;
    hash = hash * prime + this.reactionTypeID;
    hash = hash * prime + (this.input);
    hash = hash * prime + this.typeID;

    return hash;
  }

  @Override
  public String toString() {
    return "InvTypeReactionPK [reactionTypeID=" + reactionTypeID + ", input=" + input + ", typeID=" + typeID + "]";
  }

}