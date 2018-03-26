import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/WriteReviews")

public class WriteReviews  extends HttpServlet{

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
		String maker = request.getParameter("maker");
		String productName = request.getParameter("name");
		String productType = request.getParameter("type");
		String price = request.getParameter("price");
		String retailerName = request.getParameter("retailerName");
		String zipcode = request.getParameter("zipcode");
		String city = request.getParameter("city");
		String state = request.getParameter("state");
		String onSale = request.getParameter("onSale");
		String rebate = request.getParameter("rebate");
		
		/* Header, Left Navigation Bar are Printed.

		and then Footer is Printed*/

		Utilities utility = new Utilities(request,pw);
		
		if(!utility.isLoggedin()){
			HttpSession session = request.getSession(true);				
			session.setAttribute("login_msg", "Please Login to add items to cart");
			response.sendRedirect("Login");
			return;
		}
		
		utility.printHtml("Header.html");
		utility.printHtml("LeftNavigationBar.html");
		pw.print("<div class='span9'><div class='well well-small'><h3>Reviews</h3>");
		pw.print("<div class='row-fluid'>");
		pw.print("<div class='caption cntr'>");
		pw.print("<table border='1'>");
		pw.print("<tr><td>Product Name: </td><td>"+productName+"</td></tr>");
		pw.print("<tr><td>Product Category: </td><td>"+productType+"</td></tr>");
		pw.print("<tr><td>Product price: </td><td>"+price+"</td></tr>");
		pw.print("<tr><td>Manufacturer Name: </td><td>"+maker+"</td></tr>");
		pw.print("</table></br>");
		pw.print("<form method='post' action='ReviewSubmit'>" +
				"<p>User age: </p><input type='number' name='userAge' value=''></br>"+
				"<p>Gender: </p><input type='radio' name='gender' value='Male'> Male <input type='radio' name='gender' value='Female'> Female <input type='radio' name='gender' value='Other'> Other</br>"+
				"<p>Occupation: </p><input type='text' name='occupation' value=''></br>"+
				"<p>Review rating: </p><select name='rating'>"+
					"<option value='1'>1"+
					"<option value='2'>2"+
					"<option value='3'>3"+
					"<option value='4'>4"+
					"<option value='5'>5</select></br>"+
				"<p>Review date: </p><input type='text' name='reviewDate' value=''></br>"+
				"<p>Review text: </p><textarea rows='4' cols='50' name='reviewText' value=''></textarea></br>"+
				"<input type='hidden' name='name' value='"+productName+"'>"+
				"<input type='hidden' name='type' value='"+productType+"'>"+
				"<input type='hidden' name='maker' value='"+maker+"'>"+
				"<input type='hidden' name='price' value='"+price+"'>"+
				"<input type='hidden' name='retailerName' value='BestDeal'>"+
				"<input type='hidden' name='zipcode' value='60616'>"+
				"<input type='hidden' name='city' value='Chicago'>"+
				"<input type='hidden' name='state' value='Illinois'>"+
				"<input type='hidden' name='onSale' value='Yes'>"+
				"<input type='hidden' name='rebate' value='2.00'>"+
				"<input type='hidden' name='access' value=''>"+
				"<h4><input type='submit' class='shopBtn' value='Submit Review'></a></h4>");
		pw.print("</form></div>");
		pw.print("</div></div></div>");
		pw.print("</div><!-- /container -->");
		pw.print("<section class='our_client'></section>");			
		utility.printHtml("Footer.html");
		
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
}
