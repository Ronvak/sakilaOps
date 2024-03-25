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
         con = DriverManager.getConnection(connectionURL, "root", "318180700");
         MenuManager.runMenu(con);
         con.close();
     } catch (SQLException | ClassNotFoundException e) {
         e.printStackTrace();
     }
 }
}
