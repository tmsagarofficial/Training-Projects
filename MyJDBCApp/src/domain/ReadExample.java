package domain;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Connection;

public class ReadExample {

	public static void main(String[] args) {
		try {
			String url="jdbc:mysql://localhost:3306/sample";
			String uname="root";
			String pword="1234";
			Connection con=DriverManager.getConnection(url,uname,pword);
			System.out.println("Connected");
			String query="SELECT * FROM example";
			Statement st=con.createStatement();
			ResultSet rs=st.executeQuery(query);
			while(rs.next()) {
				int id=rs.getInt(1);
				String name=rs.getString(2);
				int age=rs.getInt(3);
				String address=rs.getString(4);
				String email=rs.getString(5);
				System.out.println(id+" - "+name+" - "+age+" - "+address+" - "+email);
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
