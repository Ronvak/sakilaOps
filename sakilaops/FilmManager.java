package sakilaops;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class FilmManager {

 public static void addFilm(Connection con) throws SQLException {
     Scanner sc = new Scanner(System.in);
     System.out.println("Please write the details below:");
     System.out.println("Film title:");
     String title = sc.nextLine();
     System.out.println("Film description:");
     String desc = sc.nextLine();
     System.out.println("Release year:");
     int year = MenuManager.getValidIntInput(sc);
     System.out.printf("Language (Pick a number):\n1. English\n2. Italian\n3. Japanese\n4. Mandarin\n5. French\n6. German\n");
     int lang = MenuManager.getValidIntInput(sc, 1, 6);

     String query = "INSERT INTO film (title, description, release_year, language_id) VALUES (?, ?, ?, ?)";
     PreparedStatement stmt = con.prepareStatement(query);
     stmt.setString(1, title);
     stmt.setString(2, desc);
     stmt.setInt(3, year);
     stmt.setInt(4, lang);
     stmt.executeUpdate();
     System.out.println("Film inserted successfully");
 }
}
