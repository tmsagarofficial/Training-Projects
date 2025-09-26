package sbank;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
import java.sql.Connection;

public class testing {
	public static User currentUser = null;

    public static void main(String[] args) {
        try {
            String url = "jdbc:mysql://localhost:3306/sbank";
            String uname = "root";
            String pword = "1234";
            Connection con = DriverManager.getConnection(url, uname, pword);
            printlogo();
            String query = "SELECT * FROM userdb";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(query);
            boolean auth=false;
            boolean exitcode=false;
//            while (rs.next()) {
//                int uid = rs.getInt("uid");
//                String name = rs.getString("name");
//                String password = rs.getString("password");
//                int age = rs.getInt("age");
//                String address = rs.getString("address");
//                String email = rs.getString("email");
//                boolean admin = rs.getBoolean("admin");
//                int balance = rs.getInt("balance");
//
//                System.out.println(uid + " - " + name + " - " + password + " - " + age + " - " + address + " - " + email + " - " + admin + " - " + balance);
//            }
            int choice=-1;
            double newbalance=0,wd=0,dp=0,tr=0;
            
            
            
            
            Scanner sc=new Scanner(System.in);
            String clientusername,clientpassword;
            
            while(!auth) {
    			System.out.println("[1] Login to sbank\n[2] Exit\n");
    			switch(sc.nextInt()) {
    			case 1:
    				sc.nextLine();
    				System.out.println("username:?");
    				clientusername=sc.nextLine().toLowerCase();
    				System.out.println("password:?");
    				clientpassword=sc.nextLine();
    				if(authentication(clientusername,clientpassword)) {
    					auth=true;
    					loader("Logging In......");
    				}else {
    					System.out.println("Wrong credentials/User Not found.");
    				}
    				
    				break;
    			case 2:
    				loader("Exiting......");
    				System.exit(0);
    				break;
    			default:System.out.println("Selected a valid option");
    				break;
    			}
    			
    		}
            
            
           
            while (auth) {
                System.out.println("Hi, " + currentUser.name + "! Welcome to SBank.");
                System.out.println("[1] Check Balance\n[2] Deposit\n[3] Withdrawal\n[4] Profile\n[5] Admin\n[6] Exit\n");
                choice = sc.nextInt();

                switch (choice) {
                
                    case 1:
                        System.out.println("Your current balance is: ₹" + currentUser.balance);
                        break;
                    case 2:
                        System.out.println("Your current balance is: ₹" + currentUser.balance);
                        System.out.println("Enter the amount to deposit:");
                        dp = sc.nextDouble();

                        if (dp > 0) {
                            newbalance = currentUser.balance + dp;
                            loader("Depositing.....");

                            String updateQuery = "UPDATE userdb SET balance = ? WHERE uid = ?";
                            PreparedStatement updateStmt = con.prepareStatement(updateQuery);
                            updateStmt.setInt(1, (int) newbalance);
                            updateStmt.setInt(2, currentUser.uid);
                            int rowsAffected = updateStmt.executeUpdate();

                            if (rowsAffected > 0) {
                                System.out.println("Deposited ₹" + dp + " Successfully.");
                                currentUser.balance = (int) newbalance;
                            } else {
                                System.out.println("Deposit failed. Please try again.");
                            }
                        } else {
                            System.out.println("Invalid deposit amount.");
                        }
                        break;

                    case 3: 
                        System.out.println("Your current balance is: ₹" + currentUser.balance);
                        System.out.println("Enter the amount to withdraw:");
                        wd = sc.nextDouble();

                        if (wd > 0 && currentUser.balance >= wd) {
                            newbalance = currentUser.balance - wd;
                            loader("Processing withdrawal.....");

                            String updateQuery = "UPDATE userdb SET balance = ? WHERE uid = ?";
                            PreparedStatement updateStmt = con.prepareStatement(updateQuery);
                            updateStmt.setInt(1, (int) newbalance);
                            updateStmt.setInt(2, currentUser.uid);
                            int rowsAffected = updateStmt.executeUpdate();

                            if (rowsAffected > 0) {
                                System.out.println("Withdrawn ₹" + wd + " Successfully.");
                                currentUser.balance = (int) newbalance;
                            } else {
                                System.out.println("Withdrawal failed. Please try again.");
                            }
                        } else {
                            System.out.println("Insufficient balance or invalid amount.");
                        }
                        break;
                    case 4:
                        System.out.println("Your current profile details are as follows:");
                        System.out.println("[Name]: " + currentUser.name);
                        System.out.println("[Password]: ********");
                        System.out.println("[Age]: " + currentUser.age);
                        System.out.println("[Address]: " + currentUser.address);
                        System.out.println("[Email]: " + currentUser.email);
                        System.out.println("[Admin]: " + (currentUser.admin ? "Yes" : "No"));
                        System.out.println("[Balance]: ₹" + currentUser.balance);
                        break;
                    case 5:if(!isadmin()) {
                    		System.out.println("Action Disallowed. You're not an admin go home.");
                    	}else {
                    		System.out.println("You're an admin . PLAY GOD");
                    		query = "SELECT * FROM userdb";
                            st = con.createStatement();
                            rs = st.executeQuery(query);
                            
                            while (rs.next()) {
                                int uid = rs.getInt("uid");
                                String name = rs.getString("name");
                                String password = rs.getString("password");
                                int age = rs.getInt("age");
                                String address = rs.getString("address");
                                String email = rs.getString("email");
                                boolean admin = rs.getBoolean("admin");
                                int balance = rs.getInt("balance");
                
                                System.out.println(uid + " - " + name + " - " + password + " - " + age + " - " + address + " - " + email + " - " + admin + " - " + balance);
                            }
                    	}
                    	break;
                    case 6:loader("Exiting......");
                    	System.exit(0);
                    	break;
                    default:System.out.println("Invalid option please try again");
                }
            }

            
            
    		
    		
    		
    		
    		
    	
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
//    public static boolean checkadmin(String clientusername) {
//    	String url = "jdbc:mysql://localhost:3306/sbank";
//        String uname = "root";
//        String pword = "1234";
//        try {
//            Connection con = DriverManager.getConnection(url, uname, pword);
//
//            String query = "SELECT admin FROM userdb WHERE name = ?";
//            PreparedStatement pst = con.prepareStatement(query);
//            pst.setString(1, clientusername);
//            ResultSet rs = pst.executeQuery();
//
//            if (rs.next()) {
//                boolean isadmin = rs.getInt("admin");
//                System.out.println(isadmin);
//            } else {
//                return false;
//            }
//
//        } catch (Exception e) {
//            e.printStackTrace();
//            return false;
//        }
//        
//    }
    
    public static boolean isadmin() {
        return currentUser != null && currentUser.admin;
    }

    
    public static boolean authentication(String clientusername, String clientpassword) {
    	String url = "jdbc:mysql://localhost:3306/sbank";
        String uname = "root";
        String pword = "1234";
        try {
            Connection con = DriverManager.getConnection(url, uname, pword);

            String query = "SELECT * FROM userdb WHERE name = ?";
            PreparedStatement pst = con.prepareStatement(query);
            pst.setString(1, clientusername);
            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                String storedPassword = rs.getString("password");
                if (storedPassword.equals(clientpassword)) {
                    currentUser = new User();
                    currentUser.uid = rs.getInt("uid");
                    currentUser.name = rs.getString("name");
                    currentUser.password = storedPassword;
                    currentUser.age = rs.getInt("age");
                    currentUser.address = rs.getString("address");
                    currentUser.email = rs.getString("email");
                    currentUser.admin = rs.getBoolean("admin");
                    currentUser.balance = rs.getInt("balance");

                    return true;
                }
            }
            return false;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        
    }

    
    public static void loader(String text) {
    	for (char ch : text.toCharArray()) {
            System.out.print(ch);
            System.out.flush();
            try {
				Thread.sleep(100);
			} catch (InterruptedException e) {

				e.printStackTrace();
			}
        }
        System.out.println();
        System.out.flush(); 
    }
    
    
    public static void printlogo() {
        System.out.println("______   __                            __       ");
        System.out.println(" /      \\ |  \\                          |  \\      ");
        System.out.println("|  $$$$$$\\| $$____    ______   _______  | $$   __ ");
        System.out.println("| $$___\\$$| $$    \\  |      \\ |       \\ | $$  /  \\");
        System.out.println(" \\$$    \\ | $$$$$$$\\  \\$$$$$$\\| $$$$$$$\\| $$_/  $$");
        System.out.println(" _\\$$$$$$\\| $$  | $$ /      $$| $$  | $$| $$   $$ ");
        System.out.println("|  \\__| $$| $$__/ $$|  $$$$$$$| $$  | $$| $$$$$$\\ ");
        System.out.println(" \\$$    $$| $$    $$ \\$$    $$| $$  | $$| $$  \\$$\\");
        System.out.println("  \\$$$$$$  \\$$$$$$$   \\$$$$$$$ \\$$   \\$$ \\$$   \\$$");
    }
}
