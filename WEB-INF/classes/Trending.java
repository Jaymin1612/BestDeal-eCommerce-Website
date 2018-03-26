import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mongodb.AggregationOutput;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;

@WebServlet("/Trending")

public class Trending extends HttpServlet {

	/* Trending Page Displays all the Consoles and their Information in Game Speed*/

	protected void doGet(HttpServletRequest request,
		HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
		

		DBCollection myReviews;
		MongoClient mongo;
		mongo = new MongoClient("localhost", 27017);
		DB db = mongo.getDB("CustomerReviews");
		myReviews= db.getCollection("myReviews");
		
		/**/
		
		Utilities utility = new Utilities(request,pw);
		utility.printHtml("Header.html");
		utility.printHtml("LeftNavigationBar.html");
		pw.print("<div class='span9'><div class='well well-small'>");
		pw.print("<div class='row-fluid'>");
		pw.print("<div class='caption cntr'>");
		pw.print("<table border='1' id='bestseller'><center>");
		
		//This code is for top five zipcodes where maximum number of products sold.
		DBObject groupFields = new BasicDBObject("_id", 0);
		groupFields.put("count",new BasicDBObject("$sum",1));
		groupFields.put("_id", "$zipcode");
		DBObject group = new BasicDBObject("$group", groupFields);
		DBObject sort = new BasicDBObject();
		sort = new BasicDBObject();
		DBObject projectFields = new BasicDBObject("_id", 0);
		projectFields.put("value", "$_id");
		projectFields.put("ReviewValue","$count");
		DBObject project = new BasicDBObject("$project", projectFields);
		sort.put("ReviewValue",-1);
		DBObject limit=new BasicDBObject();
		DBObject orderby=new BasicDBObject();
		orderby=new BasicDBObject("$sort",sort);
		limit=new BasicDBObject("$limit",5);
		AggregationOutput aggregate = myReviews.aggregate(group,project,orderby,limit);
		constructGroupByContent(aggregate,pw);
		pw.print("</table>");
		
		//This code is for top five most liked products.
		pw.print("<table border='1' id='bestseller'>");
		DBObject match=new BasicDBObject("$match",new BasicDBObject("reviewRating","5"));

		DBObject groupFields2=new BasicDBObject("_id",0);
		groupFields2.put("_id","$productName");
		groupFields2.put("count",new BasicDBObject("$sum",1));
		DBObject group2=new BasicDBObject("$group",groupFields2);

		DBObject projectFields2=new BasicDBObject("_id",0);
		projectFields2.put("productName","$_id");
		projectFields2.put("ReviewValue","$count");
		DBObject project2=new BasicDBObject("$project",projectFields2);

		DBObject sort2=new BasicDBObject();
		sort2.put("ReviewValue",-1);
		DBObject orderby2=new BasicDBObject();
		orderby2=new BasicDBObject("$sort",sort2);
		DBObject limit2=new BasicDBObject();
		limit2=new BasicDBObject("$limit",5);
				AggregationOutput aggregate2 = myReviews.aggregate(match,group2,project2,orderby2,limit2);
				constructGroupByContent2(aggregate2,pw);
				pw.print("</table>");
		
		pw.print("<table border='1' id='bestseller'>");
		//This code is for top five products sold.
		DBObject groupFields1 = new BasicDBObject("_id", 0);
		groupFields1.put("count",new BasicDBObject("$sum",1));
		groupFields1.put("_id", "$productName");
		DBObject group1 = new BasicDBObject("$group", groupFields1);
		DBObject sort1 = new BasicDBObject();
		sort1 = new BasicDBObject();
		DBObject projectFields1 = new BasicDBObject("_id", 0);
		projectFields1.put("productName", "$_id");
		projectFields1.put("ReviewValue","$count");
		DBObject project1 = new BasicDBObject("$project", projectFields1);
		sort1.put("ReviewValue",-1);
		DBObject limit1 =new BasicDBObject();
		DBObject orderby1 =new BasicDBObject();
		orderby1 =new BasicDBObject("$sort",sort1);
		limit1 =new BasicDBObject("$limit",5);
		AggregationOutput aggregate1 = myReviews.aggregate(group1,project1,orderby1,limit1);
		constructGroupByContent1(aggregate1,pw);
		pw.print("</table>");
		
		
	}
	
	public void constructGroupByContent(AggregationOutput aggregate, PrintWriter pw){
		pw.print("<h3>Top five zipcodes based on review count.</h3>");
		pw.print("<tr><td>Zipcode</td><td>Review count</td></tr>");
		for (DBObject result : aggregate.results()) {
			BasicDBObject bobj = (BasicDBObject) result;
			String tableData = "<tr><td> "+bobj.getString("value")+"</td>&nbsp"+ "<td>"+bobj.getString("ReviewValue")+"</td></tr>";
			pw.print(tableData);
		}
	}
	
	public void constructGroupByContent1(AggregationOutput aggregate, PrintWriter pw){
		pw.print("<h3>Top five most sold products.</h3>");
		pw.print("<tr><td>Product name </td><td> number of times sold</td></tr>");
		for (DBObject result : aggregate.results()) {
			BasicDBObject bobj = (BasicDBObject) result;
			String tableData = "<tr><td> "+bobj.getString("productName")+"</td>&nbsp"+ "<td>"+bobj.getString("ReviewValue")+"</td></tr>";
			pw.print(tableData);
		}
	}
	
	public void constructGroupByContent2(AggregationOutput aggregate, PrintWriter pw){
		pw.print("<h3>Top five most liked products.</h3>");
		pw.print("<tr><td>Product name </td><td> Review Ratings</td></tr>");
		for (DBObject result : aggregate.results()) {
			BasicDBObject bobj = (BasicDBObject) result;
			String tableData = "<tr><td> "+bobj.getString("productName")+"</td>&nbsp"+ "<td>"+bobj.getString("ReviewValue")+"</td></tr>";
			pw.print(tableData);
		}
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

	}

}
