import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/PhoneList")

public class PhoneList extends HttpServlet {

	/* Phone Page Displays all the phones and their Information */

	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();

		/* Checks the Phones type whether it is Apple or Samsung or Google */
				
		String name = null;
		String CategoryName = request.getParameter("maker");
		String prodId = request.getParameter("productId");
		HashMap<String, Phone> hm = new HashMap<String, Phone>();
		
		if(CategoryName==null)
		{
			if (prodId != null) {

				for (Map.Entry<String, Phone> entry : SaxParserDataStore.phones.entrySet()) {
					if (entry.getValue().getId().equals(prodId)) {
						hm.put(entry.getValue().getId(), entry.getValue());
					}
				}
				name = "";

			} else {
				hm.putAll(SaxParserDataStore.phones);
				name = "";
			}
		}
		else
		{
		  if(CategoryName.equals("Apple"))
		  {
			for(Map.Entry<String,Phone> entry : SaxParserDataStore.phones.entrySet())
				{
				if(entry.getValue().getRetailer().equals("Apple"))
				 {
					 hm.put(entry.getValue().getId(),entry.getValue());
				 }
				}
			name = "Apple";
		  }
		  else if(CategoryName.equals("Google"))
		  {
			for(Map.Entry<String,Phone> entry : SaxParserDataStore.phones.entrySet())
				{
				if(entry.getValue().getRetailer().equals("Google"))
				 {
					 hm.put(entry.getValue().getId(),entry.getValue());
				 }
				}	
			name = "Google";
		  }
		  else if(CategoryName.equals("Samsung"))
		  {
			for(Map.Entry<String,Phone> entry : SaxParserDataStore.phones.entrySet())
				{
				if(entry.getValue().getRetailer().equals("Samsung"))
				 {
					 hm.put(entry.getValue().getId(),entry.getValue());
				 }
				}
			name = "Samsung";
		  }
		}

		/* Header, Left Navigation Bar are Printed.

		All the Phones and Phone information are dispalyed in the Content Section

		and then Footer is Printed*/
		
		Utilities utility = new Utilities(request,pw);
		utility.printHtml("Header.html");
		utility.printHtml("LeftNavigationBar.html");
		pw.print("<div class='span9'><div class='well well-small'><h3>"+name+" Phones</h3>");
		pw.print("<div class='row-fluid'><ul class='thumbnails'>");
		for(Map.Entry<String, Phone> entry : hm.entrySet())
		{
			Phone phone = entry.getValue();
			pw.print("<li class='span3' style='list-style-type:none;'>");
			pw.print("<a href='#'><img src='assets/img/"+phone.getImage()+"' alt=''></a>");
			pw.print("<div class='caption cntr'>");
			pw.print("<p>"+phone.getName()+"</p>");
			pw.print("<p><strong> $"+phone.getPrice()+"</strong></p></br>");
			pw.print("<form method='post' action='Cart'>" +
					"<input type='hidden' name='name' value='"+phone.getName()+"'>"+
					"<input type='hidden' name='type' value='phones'>"+
					"<input type='hidden' name='maker' value='"+CategoryName+"'>"+
					"<input type='hidden' name='price' value='"+phone.getPrice()+"'>"+
					"<input type='hidden' name='retailerName' value='BestDeal'>"+
					"<input type='hidden' name='zipcode' value='60616'>"+
					"<input type='hidden' name='city' value='Chicago'>"+
					"<input type='hidden' name='state' value='Illinois'>"+
					"<input type='hidden' name='onSale' value='Yes'>"+
					"<input type='hidden' name='rebate' value='2.00'>"+
					"<input type='hidden' name='access' value=''>"+
					"<h4><input type='submit' class='shopBtn' value='Add to Cart'></a></h4></form>");
			pw.print("<form method='post' action='WriteReviews'>" +
					"<input type='hidden' name='name' value='"+phone.getName()+"'>"+
					"<input type='hidden' name='type' value='Phones'>"+
					"<input type='hidden' name='maker' value='"+CategoryName+"'>"+
					"<input type='hidden' name='price' value='"+phone.getPrice()+"'>"+
					"<input type='hidden' name='retailerName' value='BestDeal'>"+
					"<input type='hidden' name='zipcode' value='60616'>"+
					"<input type='hidden' name='city' value='Chicago'>"+
					"<input type='hidden' name='state' value='Illinois'>"+
					"<input type='hidden' name='onSale' value='Yes'>"+
					"<input type='hidden' name='rebate' value='2.00'>"+
					"<input type='hidden' name='access' value=''>"+
					"<h4><input type='submit' class='shopBtn' value='Write Review'></a></h4></form>");
			pw.print("<form method='post' action='ViewReviews'>" +
					"<input type='hidden' name='name' value='"+phone.getName()+"'>"+
					"<h4><input type='submit' class='shopBtn' value='View Review'></a></h4>");
			pw.print("</form></div>");
			pw.print("</li>");
		}		
		pw.print("</ul></div></div></div>");
		pw.print("</div><!-- /container -->");
		pw.print("<section class='our_client'></section>");		
		utility.printHtml("Footer.html");	
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
