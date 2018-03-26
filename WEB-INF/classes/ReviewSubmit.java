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

@WebServlet("/ReviewSubmit")

public class ReviewSubmit  extends HttpServlet{

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
		String age = request.getParameter("userAge");
		String gender = request.getParameter("gender");
		String occupation = request.getParameter("occupation");
		String reviewRating = request.getParameter("rating");
		String reviewDate = request.getParameter("reviewDate");
		String reviewText = request.getParameter("reviewText");
		
		/* Header, Left Navigation Bar are Printed.

		and then Footer is Printed*/

		Utilities utility = new Utilities(request,pw);
		
		if(!utility.isLoggedin()){
			HttpSession session = request.getSession(true);				
			session.setAttribute("login_msg", "Please Login to add items to cart");
			response.sendRedirect("Login");
			return;
		}
		
		utility.storeReview(productName, productType,price, maker, retailerName, zipcode, 
				city, state, onSale, rebate, age, gender, occupation, reviewRating, reviewDate, reviewText);
		
		utility.printHtml("Header.html");
		utility.printHtml("LeftNavigationBar.html");
		pw.print("<div class='span9'><div class='well well-small'><h3>Review submitted.</h3>");
		pw.print("<div class='row-fluid'>");
		pw.print("<div class='caption cntr'>");
		pw.print("<h4>Your review has been submitted.</h4>");
		pw.print("</div>");
		pw.print("</div></div></div>");
		pw.print("</div><!-- /container -->");
		pw.print("<section class='our_client'></section>");			
		utility.printHtml("Footer.html");
		
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
}
