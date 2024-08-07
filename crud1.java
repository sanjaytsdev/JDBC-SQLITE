import java.sql.*;
import java.util.Scanner;

//CRUD OPERATION USING INPUT FROM CONSOLE
public class my_crud {
	public static void main(String[] args) {
		//Scanner class for reading input from console
		Scanner sc = new Scanner(System.in);

        	try {
            		//Database URL
            		String url = "jdbc:sqlite:newdb.db";

            		//Create connection object
            		Connection con = DriverManager.getConnection(url);
            		System.out.println("Connected to DB");

            		//Create statement object
            		Statement stmt = con.createStatement();

            		//Query for table creation in database
            		String ct = "CREATE TABLE IF NOT EXISTS employee(id INTEGER NOT NULL, name VARCHAR(256), age INTEGER, PRIMARY KEY(id));";
            		stmt.execute(ct);
            		
            		boolean exit = false;
            		
			while (!exit) {
                		System.out.println("Choose an operation: ");
                		System.out.println("1. Insert Data");
               			System.out.println("2. Update Data");
                		System.out.println("3. Delete Data");
                		System.out.println("4. Display Data");
                		System.out.println("5. Exit");
                		int choice = sc.nextInt();

                		switch (choice) {
                    			case 1:
                        			//Read the input from console
                        			System.out.println("Enter employee id: ");
                        			int id = sc.nextInt();
                        			System.out.println("Enter employee name: ");
                        			String name = sc.next();
                        			System.out.println("Enter employee age: ");
                        			int age = sc.nextInt();

                        			// Query for data insertion
                        			String ins = "INSERT INTO employee(id, name, age) VALUES (" + id + ", '" + name + "', " + age + ");";
                        			stmt.execute(ins);
                        			System.out.println("Data inserted successfully.");
                        			break;

                    			case 2:
                        			//Read input from console to update data
                        			System.out.println("Enter employee id for changing the age: ");
                        			int updateId = sc.nextInt();
                        			System.out.println("Enter corrected employee age: ");
                        			int newAge = sc.nextInt();

                        			//Query for updating the existing data
                        			String upd = "UPDATE employee SET age = " + newAge + " WHERE id = " + updateId + ";";
                        			stmt.execute(upd);
                        			System.out.println("Data updated successfully.");
                        			break;

                    			case 3:
                        			//Read input from user to delete the data
                        			System.out.println("Enter id of employee to delete the record: ");
                        			int dlt = sc.nextInt();

                        			//Query for deleting a record
                        			String del = "DELETE FROM employee WHERE id = " + dlt + ";";
                        			stmt.execute(del);
                        			System.out.println("Data deleted successfully.");
                        			break;

                    			case 4:
                        			//Display the table content
                        			display(stmt);
                        			break;

                    			case 5:
                        			//Exit the loop
                        			exit = true;
                        			//Close the connection object
                            		con.close();
                            		System.out.println("Exiting......");
                        			break;

                    			default:
                       				 System.out.println("Invalid choice");
                		}
            		}
        	} catch (Exception e) {
            		System.out.println(e);
            }
	}

	private static void display(Statement stmt) throws Exception {
        //Query for displaying the table
        String all = "SELECT * FROM employee;";
        ResultSet rs = stmt.executeQuery(all);

        System.out.println("<---------------------------------------->");
        while (rs.next()) {
            String name = rs.getString("name");
            int age = rs.getInt("age");
            int id = rs.getInt("id");
            System.out.println("Id: " + id + " Name: " + name + " Age: " + age);
        }
        System.out.println("<---------------------------------------->");
    }
}

