package sakilaops;


import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

public class MenuManager {

 public static void runMenu(Connection con) throws SQLException {
     Scanner sc = new Scanner(System.in);
     int choice;
     do {
         printMenu();
         choice = getValidIntInput(sc);
         switch (choice) {
             case 1:
                 FilmManager.addFilm(con);
                 break;
             case 2:
                 ActorManager.addActor(con);
                 break;
             case 3:
                 ActorFilmManager.addActorInFilm(con);
                 break;
             case 4:
                 QueryExecutor.executeQuery(con);
                 break;
             case 5:
                 QueryExecutor.parametricQuery(con);
                 break;
             case 6:
                 System.out.println("Thank you, see you later.");
                 break;
             default:
                 System.out.println("Invalid choice. Please enter a number between 1 and 6.");
                 break;
         }
     } while (choice != 6);
     sc.close();
 }

 private static void printMenu() {
     System.out.println("Hi Please choose one of the options below !");
     System.out.println("1. Adding a film");
     System.out.println("2. Adding an actor");
     System.out.println("3. Adding info about an actor who acts in a certain film");
     System.out.println("4. Execute a query");
     System.out.println("5. Execute parametric query");
     System.out.println("6. Exit");
 }


 
 public static int getValidIntInput(Scanner sc ) {
     while (!sc.hasNextInt()) {
         System.out.println("Invalid input. Please enter a valid number:");
         sc.next(); // consume the invalid input
     }
     return sc.nextInt();
 
 }
 public static int getValidIntInput(Scanner sc, int min, int max) {
     int input;
     while (true) {
         if (sc.hasNextInt()) {
             input = sc.nextInt();
             if (input >= min && input <= max) {
                 break;
             } else {
                 System.out.println("Invalid input. Please enter a number between " + min + " and " + max + ":");
             }
         } else {
             System.out.println("Invalid input. Please enter a valid number:");
             sc.next(); // consume the invalid input
         }
     }
     return input;
 }
}
