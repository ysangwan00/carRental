import java.sql.Timestamp;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class dbmod {

  public static void insertCar(Car car) {

    try {
      Connection c = db.connect();
      String push = "insert into cars(type) values(?)";
      PreparedStatement ps = c.prepareStatement(push);
      ps.setString(1, car.getType());
      ps.executeUpdate();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public static void insertRes(Res res) {

    try {
      Connection c = db.connect();
      String push = "insert into res(start, end, carID) values(?, ?, ?)";
      PreparedStatement ps = c.prepareStatement(push);
      ps.setTimestamp(1, res.getbstart());
      ps.setTimestamp(2, res.getbend());
      ps.setInt(3, res.getcarID());
      ps.executeUpdate();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public static void showBookings() {

    try {
      Connection c = db.connect();
      String push = "select * from res;";
      Statement st = c.createStatement();

      ResultSet rs = st.executeQuery(push);

      System.out.println("\n ********** \n");

      while (rs.next()) {
        System.out.println("Reservation " + rs.getInt("resID") + " --> " + "Start Time: " + rs.getTimestamp("start") + " | " + "End Time: " + rs.getTimestamp("end") + " | " + "Car ID: " + rs.getInt("carID") + "\n");
      }

    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public static ArrayList < Integer > findCars(Timestamp bookStart, Timestamp bookEnd) {

    ArrayList < Integer > yes = new ArrayList < Integer > ();
    ArrayList < Integer > no = new ArrayList < Integer > ();
    try {
      Connection c = db.connect();
      String push = "select * from res;";
      String push3 = "select * from cars;";

      Statement st = c.createStatement();
      Statement st3 = c.createStatement();

      ResultSet rs = st.executeQuery(push);
      ResultSet rs3 = st3.executeQuery(push3);

      while (rs.next()) {
        if (rs.getTimestamp("end").after(bookStart) && rs.getTimestamp("end").before(bookEnd) || rs.getTimestamp("start").after(bookStart) && rs.getTimestamp("start").before(bookEnd) || rs.getTimestamp("start").before(bookStart) && rs.getTimestamp("end").after(bookEnd) || rs.getTimestamp("start").after(bookStart) && rs.getTimestamp("end").before(bookEnd) || rs.getTimestamp("start") == bookStart && rs.getTimestamp("end") == bookEnd) {
          no.add(rs.getInt("carID"));
        } else {
          if (yes.contains(rs.getInt("carID")) == false)
            yes.add(rs.getInt("carID"));
        }
      }
      System.out.println(" *** All cars ***");
      while (rs3.next()) {
        System.out.println("Car ID: " + rs3.getInt("carID") + " | " + "Car type: " + rs3.getString("type"));
        yes.add(rs3.getInt("carID"));
      }
      yes.removeAll(no);
      if (yes.size() == 0) {
        System.out.println("\n *** No cars available for the requested timeslot ***");
        return yes;
      } else {
        System.out.println("\n  *** Cars available for requested timeslot ***");
        for (int i = 0; i < yes.size(); ++i) {
          System.out.println(" Available -> Car ID: " + yes.get(i));
        }
      }

    } catch (Exception e) {
      e.printStackTrace();
    }
    return yes;
  }

  public static void book(Timestamp bstart, Timestamp bend, int carID) {

    try {
      Connection c = db.connect();
      String push = "insert into res(start, end, carID) values(?, ?, ?)";
      PreparedStatement ps = c.prepareStatement(push);
      ps.setTimestamp(1, bstart);
      ps.setTimestamp(2, bend);
      ps.setInt(3, carID);
      ps.executeUpdate();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

}