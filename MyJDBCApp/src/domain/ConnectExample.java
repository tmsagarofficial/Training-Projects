package domain;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Connection;

public class ConnectExample {

	public static void main(String[] args) {
		try {
			String url="jdbc:mysql://localhost:3306/sample";
			String uname="root";
			String pword="1234";
			Connection con=DriverManager.getConnection(url,uname,pword);
			System.out.println("Connected");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
