package domain;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Connection;

public class InsertExample {

	public static void main(String[] args) {
		try {
			String url="jdbc:mysql://localhost:3306/sample";
			String uname="root";
			String pword="1234";
			Connection con=DriverManager.getConnection(url,uname,pword);
			System.out.println("Connected");
			String query="INSERT INTO example VALUES (4,'sagr',23,'BLR','tms@gmail.com');";
			Statement st=con.createStatement();
			int row=st.executeUpdate(query);
			System.out.println(row+"row/rows updated.");

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
