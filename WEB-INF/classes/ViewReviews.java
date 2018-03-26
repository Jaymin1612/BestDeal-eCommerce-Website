import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;

@WebServlet("/ViewReviews")

public class ViewReviews extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
		String productName = request.getParameter("name");

		DBCollection myReviews;
		MongoClient mongo;
		mongo = new MongoClient("localhost", 27017);
		DB db = mongo.getDB("CustomerReviews");
		myReviews= db.getCollection("myReviews");
		
		/*
		 * Header, Left Navigation Bar are Printed.
		 * 
		 * and then Footer is Printed
		 */

		Utilities utility = new Utilities(request, pw);
		utility.printHtml("Header.html");
		utility.printHtml("LeftNavigationBar.html");
		pw.print("<div class='span9'><div class='well well-small'>");
		pw.print("<div class='row-fluid'>");
		pw.print("<div class='caption cntr'>");
		
		BasicDBObject query = new BasicDBObject();
		query.put("productName", productName);
		DBCursor dbCursor = myReviews.find(query);
		
		pw.print("<table border='1' class='gridtable'><h3>::"+productName+"::</h3>");
		while (dbCursor.hasNext()){
			BasicDBObject bobj = (BasicDBObject) dbCursor.next();
			pw.print("<tr><td align='center' colspan='2'></td></tr><tr><td>User Id: </td><td>" +
			bobj.getString("userId") + "</td></tr>"
			+ "<tr><td>User age:</td><td>" + bobj.getString("age") + "</td></tr>"
			+ "<tr><td>User gernder:</td><td>" + bobj.getString("gender") + "</td></tr>"
			+ "<tr><td>User occupation:</td><td>" + bobj.getString("occupation")+"</td><tr>"
			+ "<tr><td>Retailer name:</td><td>" + bobj.getString("retailerName")+"</td><tr>"
			+ "<tr><td>Retailer zipcode:</td><td>" + bobj.getString("zipcode")+"</td><tr>"
			+ "<tr><td>Retailer city:</td><td>" + bobj.getString("city")+"</td><tr>"
			+ "<tr><td>Retailer state:</td><td>" + bobj.getString("state")+"</td><tr>"
			+ "<tr><td>Product on sale:</td><td>" + bobj.getString("onSale")+"</td><tr>"
			+ "<tr><td>Rebate:</td><td>" + bobj.getString("rebate")+"</td><tr>"
			+ "<tr><td>Rating:</td><td>" + bobj.getString("reviewRating") + "</td></tr>"
			+ "<tr><td>Date:</td><td>" + bobj.getString("reviewDate") + "</td></tr>"
			+ "<tr><td>Review Text:</td><td>" + bobj.getString("reviewText")+"</td><tr>");
		}
		pw.print("</table>");
		//No data found
		if(dbCursor.count() == 0){
			pw.print("<h2>No Data Found</h2>");
		}
		
		pw.print("</div>");
		pw.print("</div></div></div>");
		pw.print("</div><!-- /container -->");
		pw.print("<section class='our_client'></section>");
		utility.printHtml("Footer.html");

	}
	
	public void constructTableContent(DBCursor dbCursor,PrintWriter pw){
		String tableData = "";
		pw.print("<table border='1' class='gridtable'><h3>::Reviews::</h3>");
		while (dbCursor.hasNext()){
			BasicDBObject bobj = (BasicDBObject) dbCursor.next();
			tableData = "<tr><td align='center' colspan='2'></td></tr><tr><td>Name: </td><td>" +
			bobj.getString("productName") + "</td></tr>"
			+ "<tr><td>Rating:</td><td>" + bobj.getString("reviewRating") + "</td></tr>"
			+ "<tr><td>Date:</td><td>" + bobj.getString("reviewDate") + "</td></tr>"
			+ "<tr><td>Review Text:</td><td>" + bobj.getString("reviewText")+"</td><tr>";
			pw.print(tableData);
		}
		pw.print("</table>");
		//No data found
		if(dbCursor.count() == 0){
			tableData = "<h2>No Data Found</h2>";
			pw.print(tableData);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
