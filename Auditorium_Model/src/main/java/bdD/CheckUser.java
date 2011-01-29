package bdD;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Map;

import org.apache.hadoop.hbase.client.HTable;


public class CheckUser {

	private HBaseConnector hbaseconnector;
	private  HTable users;
	private final static String usertable="User"; 
	
	public CheckUser()
	{
		hbaseconnector = new HBaseConnector();
		users = hbaseconnector.getHTable(usertable);
	}
	
	public void getListUsersNames()
	{
		System.out.println();
	}
	
	public void getListUsers(String role)
	{
		System.out.println();
	}
	
	public void getUser(String username)
	{
		 Map map_users= hbaseconnector.retrieveColumn(users,username);
		 System.out.println("Password :"+hbaseconnector.getPassword(map_users,"Infos"));
		 System.out.println("Role :"+hbaseconnector.getRole(map_users,"Infos"));
		
	}
	
	
	
	public static void main(String[] args) throws SQLException, ClassNotFoundException, IOException 
	{
		
    	
		
		
	
}
}