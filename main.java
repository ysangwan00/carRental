import java.util.Scanner;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.sql.Timestamp;
import java.text.ParseException;

class Main {
  public static void main(String[] args) {

    Scanner in = new Scanner(System.in);

    System.out.println("-----------------------------------");
    System.out.println("Welcome to CRD car rental service!");
    System.out.println("-----------------------------------");

    String input = "";
    String sDate = "";
    String eDate = "";
    Date bookStartDate;
    Date bookEndDate;
    Timestamp bstart;
    Timestamp bend;

    while (input != "3") {

      System.out.println("Menu options: " + "\n 1 -> Rent a car" + "\n 2 -> Show bookings" + "\n 3 -> Exit");
      System.out.print("Please enter the option number: ");

      input = in .nextLine();

      switch (input) {
      case "1":
        while (true) {
          System.out.print("Enter rental start date (MM/DD/YYYY): ");

          input = in .nextLine();

          DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
          df.setLenient(false);

          try {
            Date dp = df.parse(input);
            sDate = df.format(dp);
            break;
          } catch (ParseException e) {
            System.out.println("Please enter a valid date in the format MM/DD/YYYY");
            continue;
          }
        }

        while (true) {
          System.out.print("Enter rental start time (hh:mm am/pm): ");

          input = in .nextLine();

          SimpleDateFormat tdf = new SimpleDateFormat("MM/dd/yyyy hh:mm a");
          tdf.setLenient(false);
          try {
            bookStartDate = tdf.parse(sDate + " " + input);
            bstart = new Timestamp(bookStartDate.getTime());
            break;
          } catch (ParseException e) {
            System.out.println("Please enter a valid time in the format hh:mm am/pm");
            continue;
          }
        }

        while (true) {
          System.out.print("Enter rental end date (MM/DD/YYYY): ");

          input = in .nextLine();

          DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
          df.setLenient(false);

          try {
            Date dp = df.parse(input);
            eDate = df.format(dp);
          } catch (ParseException e) {
            System.out.println("Please enter a valid date in the format MM/DD/YYYY");
            continue;
          } catch (IllegalArgumentException e) {
            System.out.println("Please enter a date on or after the start date in the format MM/DD/YYYY");
            continue;
          }

          System.out.print("Enter rental end time (hh:mm am/pm): ");

          input = in .nextLine();

          DateFormat tdf = new SimpleDateFormat("MM/dd/yyyy hh:mm a");

          try {
            bookEndDate = tdf.parse(eDate + " " + input);
            bend = new Timestamp(bookEndDate.getTime());
            if (bookEndDate.before(bookStartDate) || bookEndDate == bookStartDate) {
              throw new IllegalArgumentException();
            }
            break;
          } catch (ParseException e) {
            System.out.println("Please enter a valid time in the format hh:mm am/pm");
            continue;
          } catch (IllegalArgumentException e) {
            System.out.println("Please enter a time after the start time in the format hh:mm am/pm");
            continue;
          }
        }

        while (true) {
          try {
            ArrayList < Integer > l = dbmod.findCars(bstart, bend);
            if (l.size() > 0) {
              System.out.print("Please enter the Car ID for the car you would like to book: ");
              input = in .nextLine();
              if (l.contains(Integer.parseInt(input)) == false) {
                throw new IllegalArgumentException();
              }
              dbmod.book(bstart, bend, Integer.parseInt(input));
              break;
            } else {
              break;
            }
          } catch (Exception e) {
            System.out.println("Please enter Car ID for a car from the available options");
            continue;
          }
        }

        case "2":
          dbmod.showBookings();
          break;
        case "3":
          input = "3";
          System.out.println("--------------------\nThank you, see ya!\n--------------------");
          break;
        default:
          System.out.println("Please enter a valid option number");
          break;
      }
    }
  }
}