package sakilaops;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class QueryExecutor {

	public static Scanner sc = new Scanner(System.in);
	public static void parametricQuery (Connection con) {

		System.out.println("Please choose one of the options below:");
		System.out.println("1.Searching a film where a its title contained your String");
		System.out.println("2.Searching all films that a certain actor acts in");
		System.out.println("3.Searching all actors who acts in a film with specific language");
		System.out.println("4.Searching films that according to our data-base acts in exactly x actors");
		System.out.println("5.Searching for a film in English where Robert De Niro isn't acts in");
		
		int pick = MenuManager.getValidIntInput(sc, 1,5);
		try {
			PreparedStatement stmn = null ;
			switch(pick) {
			case 1:{

				stmn= con.prepareStatement("SELECT * FROM film WHERE title LIKE ? ");
				System.out.println("Please enter your String:");
				String userRes = sc.nextLine();
				userRes =sc.nextLine();
				stmn.setString(1,"%" + userRes +"%");
				break;	
			}
			case 2: {
				stmn = con.prepareStatement("SELECT f.film_id , f.title FROM film f , actor a , film_actor acts "
						+ "WHERE a.first_name = ? AND a.last_name = ? AND a.actor_id = "
						+ "acts.actor_id AND acts.film_id = f.film_id");

				System.out.println("Please enter actor first name");
				String fName = sc.nextLine();
				fName = sc.nextLine();
				System.out.println("Please enter actor last name");
				String lName = sc.nextLine();

				stmn.setString(1, fName);
				stmn.setString(2, lName);
				break;		
			}
			case 3:{
				stmn =con.prepareStatement("SELECT actor_id , first_name , last_name FROM actor INTERSECT ("
						+ " SELECT a.actor_id , a.first_name , a.last_name FROM actor a , film f ,film_actor acts"
						+ " WHERE  f.language_id = ?  AND f.film_id = acts.film_id AND acts.actor_id = a.actor_id)");
				System.out.printf("Language ( Pick a number) : %n 1.English %n 2.Italian %n 3.Japanese"
						+ " %n 4.Mandarin %n 5.French %n 6.German %n ");
			
				int lang = MenuManager.getValidIntInput(sc, 1, 6);
				
				
				stmn.setInt(1, lang);
				break;	
			}
			case 4: {
				stmn = con.prepareStatement("SELECT f.film_id , f.title FROM film f , "
						+ "(SELECT film_id , COUNT(*) AS actors_count FROM film_actor GROUP BY film_id) c"
						+ " WHERE c.actors_count = ? AND c.film_id = f.film_id");
				System.out.println("Please type a number of actors");
			
				int actors = MenuManager.getValidIntInput(sc);
				
				   
				
				stmn.setInt(1, actors);
				break;	
			}
			case 5: {
				stmn = con.prepareStatement("SELECT film_id , title FROM film INTERSECT  SELECT f.film_id , "
						+ "f.title FROM film f ,film_actor act ,actor a WHERE f.language_id = 1"
						+ " AND a.first_name != 'ROBERT' AND a.last_name != 'DE NIRO'"
						+ " AND a.actor_id = act.actor_id AND act.film_id = f.film_id");
				break;	
			}
			default:{
				break;
			}


			}



			ResultSet rs =stmn.executeQuery();
			int rows =printResult(rs);
			System.out.println("Rows Number : " + rows);

		}
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void executeQuery (Connection con) {
		System.out.println("Please enter your query");
		String userQuery = sc.nextLine();
		userQuery = sc.nextLine();
		Statement stmt;
		try {
			stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(userQuery);
			int rows = printResult(rs);
			System.out.println("Row number is: " + rows);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

    public static int printResult(ResultSet rs) {
    	int rows = 0;
		List<String> columnNames = new ArrayList<>();
		ResultSetMetaData rsmd;
		try {
			rsmd = rs.getMetaData();

			for (int i = 1; i <= rsmd.getColumnCount(); i++) {
				columnNames.add(rsmd.getColumnLabel(i));
			}
			for( int i=0 ; i<rsmd.getColumnCount(); i++) {
				System.out.print(columnNames.get(i) + "  " );

			}

			System.out.println();
			System.out.print("__________________________________________");

			while (rs.next()) {
				rows++;
				System.out.println();

				// collect row data as objects in a List
				List<Object> rowData = new ArrayList<>();


				for (int i = 1; i <= rsmd.getColumnCount(); i++) {
					rowData.add(rs.getObject(i));
				}

				for (int colIndex = 0; colIndex < rsmd.getColumnCount(); colIndex++) {	
					String objString = "";
					Object columnObject = rowData.get(colIndex);
					if (columnObject != null) {
						objString = columnObject.toString() + " ";

					}

					System.out.printf("%s | ",objString);
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println();

		return rows ;
	}
    
}
