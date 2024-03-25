package sakilaops;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SakilaOps {

 public static void main(String[] args) {
     String connectionURL = "jdbc:mysql://localhost:3306/sakila";
     Connection con = null;
     try {
         Class.forName("com.mysql.cj.jdbc.Driver");
         //Type your connections credentials below:
         con = DriverManager.getConnection(connectionURL, "root", "XXXXXX");
         MenuManager.runMenu(con);
         con.close();
     } catch (SQLException | ClassNotFoundException e) {
         e.printStackTrace();
     }
 }
}
