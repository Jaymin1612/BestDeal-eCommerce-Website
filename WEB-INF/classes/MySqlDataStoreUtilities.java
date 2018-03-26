import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

public class MySqlDataStoreUtilities{
	static Connection conn = null;
	
	public static Connection getConnection(){
		try{
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bestdeal", "root", "password");
		}
		catch(Exception e){}
		return conn;
	}
	
	public static void insertUser(String username, String password, String usertype){
		try{
			
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bestdeal", "root", "password");
			
			String insertIntoCustomerRegisterQuery = "INSERT INTO Registration (username, password, usertype)"
					+ "VALUES (?,?,?);";
			PreparedStatement ps = conn.prepareStatement(insertIntoCustomerRegisterQuery);
			ps.setString(1, username);
			ps.setString(2, password);
			ps.setString(3, usertype);
			ps.execute();
			
		}catch (Exception e) {}
	}
	
	public static HashMap<String, User> selectUser(){
		try{
			
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bestdeal", "root", "password");
			
			ResultSet rs = null;
            Statement st = conn.createStatement();
            String sql = ("select * from Registration");
            rs=st.executeQuery(sql);
            while (rs.next()) { 
                HashMap<String, User> user = new HashMap<String, User>();
                String name=rs.getString("username");
                String desc=rs.getString("password");
                String image=rs.getString("usertype");
                user.put("P1", new User(name,desc,image));
                return user;
            }
            rs.close();
            st.close();
            conn.close();
          
		}catch (Exception e) {}
		return null;
	}
	
	public static HashMap<Integer, ArrayList<OrderPayment>> selectOrder(){
		HashMap<Integer, ArrayList<OrderPayment>> orderPayments = new HashMap<Integer, ArrayList<OrderPayment>>();
		 try{
			 Class.forName("com.mysql.jdbc.Driver").newInstance();
			 conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bestdeal", "root", "password");
			 String selectorderquery = "select * from customerorders";
			 PreparedStatement ps = conn.prepareStatement(selectorderquery);
			 ResultSet rs = ps.executeQuery();
			 ArrayList<OrderPayment> orderlist = new ArrayList<OrderPayment>();
			 while(rs.next()){
				 if(!orderPayments.containsKey(rs.getInt("OrderId"))){
					 ArrayList<OrderPayment> arr = new ArrayList<OrderPayment>();
					 orderPayments.put(rs.getInt("OrderId"), arr);
				 }
				 ArrayList<OrderPayment> listorderPayment = orderPayments.get(rs.getInt("OrderId"));
				 OrderPayment order = new OrderPayment(rs.getInt("OrderId"), rs.getString("username"), rs.getString("ordername"), 
						 rs.getDouble("orderprice"), rs.getString("useraddress"), rs.getString("creditcardno"));
				 listorderPayment.add(order);
			 }
			 
		 }catch(Exception e){}
		 return orderPayments;
	}
	
	public static void insertOrder(int orderId, String username, String ordername, double orderprice, String useraddress, String creditcardno){
		try{
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bestdeal", "root", "password");
			String insertIntoCustomerOrderQuery = "INSERT INTO customerorders(OrderId, username, ordername, orderprice, "
					+ "useraddress, creditcardno) VALUES (?,?,?,?,?,?);";
			PreparedStatement ps = conn.prepareStatement(insertIntoCustomerOrderQuery);
			ps.setInt(1, orderId);
			ps.setString(2, username);
			ps.setString(3, ordername);
			ps.setDouble(4, orderprice);
			ps.setString(5, useraddress);
			ps.setString(6, creditcardno);
			ps.execute();
		}catch(Exception e){}
	}
	
	public static void deleteOrder(int orderId, String ordername){
		try{
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bestdeal", "root", "password");
			String deleteFromCustomerOrderQuery = "DELETE FROM customerorders WHERE OrderId = ? and ordername = ?";
			PreparedStatement ps = conn.prepareStatement(deleteFromCustomerOrderQuery);
            ps.setInt(1,  orderId);
            ps.setString(2, ordername);
            ps.execute();
		}catch(Exception e){}
	}
}