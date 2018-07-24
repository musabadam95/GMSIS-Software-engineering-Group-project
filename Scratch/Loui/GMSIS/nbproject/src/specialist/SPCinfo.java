package specialist;

import SwingGUI.string;
import common.UserDatabase;
import java.sql.*;
import java.util.*;

public class SPCinfo {

	private string Name, FirstLineAddress, SecoundLineAddress, County, PostCode;
	private int PhoneNumber;
	private string EmailAddress;
        protected ResultSet rs;
        protected Statement st;
        private static final SPCinfo INSTANCE = new SPCinfo();
        private Connection connection = null;

	SPCinfo() {
		try{
			connection = DriverManager.getConnection("jdbc:sqlite:data.db");
			} catch (SQLException ex) {
				ex.printStackTrace();
				throw new RuntimeException("Database connection failed!", ex);
			}
		}

		public void test_database() {
			Statement statement;
			try {

				statement = connection.createStatement();
				statement.setQueryTimeout(10);
				statement.executeUpdate("drop table if exists `Name`");
				statement.executeUpdate("create table `SPC` (`ID` integer,`Name` string,`Address` string ,`Phone` integer,`Email` string,");
				rs = statement.executeQuery("select * from `SPC`");
				System.out.println("ID	Name");
				while (rs.next()) {
				System.out.println(rs.getInt("ID") + "	" + rs.getString("Name"));
			}
		} catch (SQLException ex) {

			System.err.println(ex.getMessage());
		}
		finally {
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException ex) {
					System.err.println(ex.getMessage());
				}
			}
		}
	}
                
                public SPCinfo(string Name, string FirstLineAddress, string SecoundLineAddress, string County, string PostCode, 
                        int PhoneNumber, string EmailAddress){
                    this.Name = Name; this.FirstLineAddress = FirstLineAddress; this.SecoundLineAddress = SecoundLineAddress;
                    this.County = County; this.PostCode = PostCode; this.PhoneNumber = PhoneNumber;
                    this.EmailAddress = EmailAddress;
                }
                
                public void setName(string Name){this.Name = Name;}
                public void setFirstLineAddress(string FirstLineAddress){this.FirstLineAddress = FirstLineAddress;}
                public void setSecoundLineAddress(string SecoundLineAddress){this.SecoundLineAddress = SecoundLineAddress;}
                public void setCounty(string County){this.County = County;}
                public void setPostCode(string PostCode){this.PostCode = PostCode;}
                public void setPhoneNumber(int PhoneNumber){this.PhoneNumber = PhoneNumber;}
                public void setEmailAddress(string EmailAddress){this.EmailAddress = EmailAddress;}
                
                public string getName(){return Name;}
                public string getFirstLineAddress(){return FirstLineAddress;}
                public string getSecoundLineAddress(){return SecoundLineAddress;}
                public string getCounty(){return County;}
                public string getPostCode(){return PostCode;}
                public int getPhoneNumber(){return PhoneNumber;}
                public string getEmailAddress(){return EmailAddress;}
                
                

	public void getDeliveryDate() {
		
		throw new UnsupportedOperationException();
	}

	public void getReturnDate() {
		// TODO - implement SPCinfo.getReturnDate
		throw new UnsupportedOperationException();
	}

	public void getOutstanding() {
		// TODO - implement SPCinfo.getOutstanding
		throw new UnsupportedOperationException();
	}

	public void getCost() {
		// TODO - implement SPCinfo.getCost
		throw new UnsupportedOperationException();
	}

	public void getCustomer() {
		// TODO - implement SPCinfo.getCustomer
		throw new UnsupportedOperationException();
	}

}
