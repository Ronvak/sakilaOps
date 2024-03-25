package sakilaops;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class ActorFilmManager {

 public static void addActorInFilm(Connection con) throws SQLException {
     Scanner sc = new Scanner(System.in);
     System.out.println("Actor first Name:");
     String fName = sc.nextLine();
     System.out.println("Actor last Name:");
     String lName = sc.nextLine();
     String actorID;
     String titleID;
     String findActor = "SELECT actor_id FROM actor WHERE first_name = ? AND last_name = ?";
     PreparedStatement stmt = con.prepareStatement(findActor);
     stmt.setString(1, fName);
     stmt.setString(2, lName);
     ResultSet rs = stmt.executeQuery();
     if (!rs.next()) {
         System.out.println("No such actor found in our database. Please add the actor before this procedure.");
         return;
     } else {
         actorID = rs.getString(1);
     }
     System.out.println("Film title:");
     String title = sc.nextLine();
     String findTitle = "SELECT film_id FROM film WHERE title = ?";
     stmt = con.prepareStatement(findTitle);
     stmt.setString(1, title);
     rs = stmt.executeQuery();
     if (!rs.next()) {
         System.out.println("No such film found in our database. Please add the film before this procedure.");
         return;
     } else {
         titleID = rs.getString(1);
     }
     String query = "INSERT INTO film_actor (actor_id, film_id) VALUES (?, ?)";
     stmt = con.prepareStatement(query);
     stmt.setString(1, actorID);
     stmt.setString(2, titleID);
     stmt.executeUpdate();
     System.out.println("Now " + fName + " " + lName + " is officially in the cast of the film " + title);
 }
}
