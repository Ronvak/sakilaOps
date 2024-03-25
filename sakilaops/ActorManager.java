package sakilaops;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class ActorManager {

 public static void addActor(Connection con) throws SQLException {
     Scanner sc = new Scanner(System.in);
     System.out.println("Please write the details below:");
     System.out.println("First name:");
     String fName = sc.nextLine();
     System.out.println("Last name:");
     String lName = sc.nextLine();
     String query = "INSERT INTO actor (first_name, last_name) VALUES (?, ?)";
     PreparedStatement stmt = con.prepareStatement(query);
     stmt.setString(1, fName);
     stmt.setString(2, lName);
     stmt.executeUpdate();
     System.out.println("Actor inserted successfully");
 }
}
