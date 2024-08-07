import java.sql.*;

//CRUD OPERATION WITH DATA GIVEN IN THE CODE ITSELF
public class my_crud {
	public static void main(String[] args) {
        	try {
        		//Database URL
        		String url = "jdbc:sqlite:newdb.db";
        	
        		//Create connection object
            		Connection con = DriverManager.getConnection(url);
            		System.out.println("Connected to DB");
            	
            		//Create statement object
            		Statement stmt = con.createStatement();
            	
            		//Query for table creation in database
           		String ct = "CREATE TABLE IF NOT EXISTS employee(id int NOT NULL, name varchar(256), age int, PRIMARY KEY(id));";
           	
           		//Execute the Query -> Result : Table creation
           		stmt.execute(ct);
           	
           		//Query for data insertion
           		String ins = "INSERT INTO employee(id, name, age) VALUES (22, 'Sivan', 16);";
            		String ins1 = "INSERT INTO employee(id, name, age) VALUES (46, 'Saju', 23);";
            	
            		//Execute the query -> Result : Data insertion
            		stmt.execute(ins);
            		stmt.execute(ins1);
        
        		//Displays the table content
        		display(stmt);
        		
            		//Query for updating the existing data
            		String upd = "UPDATE employee SET age = 28 WHERE id = 22;";
            	
            		//Execute the query -> Result : Data updation
            		stmt.execute(upd);
            		display(stmt);
            		
            	
            		//Query for deleting a record
            		String del = "DELETE FROM employee WHERE id = 46;";
            	
            		//Execute the query -> Result : Record deletion
            		stmt.execute(del);
            		display(stmt);
       		
       			//Close the connection object
            		con.close();
        	} catch (Exception e) {
            		System.out.println(e);
        	} 
    	}
    	
    	private static void display(Statement stmt) throws Exception{
    	
    		//Query for display the table
		String all = "SELECT * FROM employee;";
		
		//Execute the query and store in result set
         	ResultSet rs = stmt.executeQuery(all);
         	System.out.println("<---------------------------------------->");
         	while (rs.next()) {
             		String name = rs.getString("name");
             		int age = rs.getInt("age");
             		int id = rs.getInt("id");
             		System.out.println(" Id: "+ id +" Name: " + name + " Age: " + age);
         	}
	}
}

