import java.sql.Timestamp;

public class Res {

  Timestamp bstart;
  Timestamp bend;
  int carID;

  public Res(Timestamp bstart, Timestamp bend, int carID) {
    this.bstart = bstart;
    this.bend = bend;
    this.carID = carID;
  }

  public Timestamp getbstart() {
    return this.bstart;
  }

  public Timestamp getbend() {
    return this.bend;
  }

  public int getcarID() {
    return this.carID;
  }

  @Override
  public String toString() {
    return ("Reserved car: " + this.getcarID() + "\nReservation start time: " + this.getbstart() + "\nReservation end time: " + this.getbend());
  }

}