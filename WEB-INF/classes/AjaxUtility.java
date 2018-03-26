import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Iterator;
import java.util.Map.Entry;


public class AjaxUtility {
	
	
		public static HashMap<String, Product> getData() throws SQLException {
			HashMap<String, Product> hm = new HashMap<String, Product>();
			Connection conn = MySqlDataStoreUtilities.getConnection();
			Statement stmt = conn.createStatement();
			String selectCustomerQuery = "select * from product;";
			ResultSet rs = stmt.executeQuery(selectCustomerQuery);
			while (rs.next()) {
				Product p = new Product(rs.getString("productId"), rs.getString("productName"),
						rs.getString("productType"));
				hm.put(rs.getString("productId"), p);
			}
			return hm;
		}
	
	@SuppressWarnings("rawtypes")
	public StringBuffer readdata(String searchId) throws SQLException {
		HashMap<String, Product> data;
		StringBuffer sb = new StringBuffer();

		data = getData();
		Iterator<Entry<String, Product>> it = data.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry pi = (Map.Entry) it.next();
			Product p = (Product) pi.getValue();
			if (p.getName().toLowerCase().startsWith(searchId)) {
				sb.append("<product>");
				sb.append("<id>" + p.getId() + "</id>");
				sb.append("<productType>" + p.getType() + "</productType>");
				sb.append("<productName>" + p.getName() + "</productName>");
				sb.append("</product>");
			}
		}
		return sb;
	}
	
	
	
}