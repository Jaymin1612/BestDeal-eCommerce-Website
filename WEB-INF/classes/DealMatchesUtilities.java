
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("DealMatchesUtilities")

public class DealMatchesUtilities extends HttpServlet {
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
		PrintWriter out = response.getWriter();
		
		out.println("<!DOCTYPE html>" +
				"<html lang='en'>"+
				  "<head>"+
				   " <meta charset='utf-8'>"+
				   " <title>BestDeal - Best price guarantee</title>"+
				   " <meta name='viewport' content='width=device-width, initial-scale=1.0'>"+
				   " <meta name='description' content=''>"+
				   " <meta name='author' content=''>"+
				   " <!-- Bootstrap styles -->"+
				   " <link href='assets/css/bootstrap.css' rel='stylesheet'/>"+
				   " <!-- Customize styles -->"+
				   " <link href='style.css' rel='stylesheet'/>"+
				   " <!-- font awesome styles -->"+
				   " <link href='assets/font-awesome/css/font-awesome.css' rel='stylesheet'>"+
					"	<!--[if IE 7]>"+
					"		<link href='css/font-awesome-ie7.min.css' rel='stylesheet'>"+
					"	<![endif]-->"+
					"<script type='text/javascript' src='assets/js/javascript.js'></script>"+
					"<script type='text/javascript' src='assets/js/jquery-3.2.1.js'></script>"+
					"<!--[if lt IE 9]>"+
					"		<script src='http://html5shim.googlecode.com/svn/trunk/html5.js'></script>"+
					"	<![endif]-->"+

					"	<!-- Favicons -->"+
				    "<link rel='shortcut icon' href='assets/ico/favicon.ico'>"+
				 " </head>"+
				"<body onload=init()>"+

				"<!--"+
				"Lower Header Section "+
				"-->"+
				"<div class='container'>"+
				"<div id='gototop'> </div>"+
				"<header id='header'>"+
				"<div class='row'>"+
				"	<div class='span4'>"+
				"	<h1>"+
				"	<a class='logo' href='Home'><span>BestDeal - Best price guarantee</span> "+
				"		<img src='assets/img/bestdeals.png' alt='bootstrap sexy shop' style='width:224px;height:51px;'>"+
				"	</a>"+
				"	</h1>"+
				"	</div>"+
				"	<div class='span4'>"+
				"	<div class=''>"+
				"	<i class=''></i>"+
				"	</div>"+
				"	</div>"+
				"	<div class='span4 alignR'>"+
				"	<p><strong> Hello,  </strong><br></p>"+
				"	<p><br><strong><a href='ViewOrder'>View Orders</a></strong><br><br></p>"+
				"	<form class='form-horizontal loginFrm' action='Cart'>"+
				"		<span ><strong>Your Cart</strong></span>"+
				"		<button type='submit' class='icon-shopping-cart'></button>"+
				"	</form>"+
				"	</div>"+
				"</div>"+
				"</header>"+

				"<!--"+
			"	Navigation Bar Section "+
			"	-->"+
			"	<div class='navbar'>"+
			"		  <div class='navbar-inner'>"+
			"			<div class='container'>"+
			"			  <a data-target='.nav-collapse' data-toggle='collapse' class='btn btn-navbar'>"+
			"				<span class='icon-bar'></span>"+
			"				<span class='icon-bar'></span>"+
			"				<span class='icon-bar'></span>"+
			"			  </a>"+
			"			  <div class='nav-collapse'>"+
			"				<ul class='nav'>"+
			"				  <li class='active'><a href='Home'>Home	</a></li>"+
			"				  <li class=''><a href=TVList>Televisions</a></li>"+
			"				  <li class=''><a href=LaptopsList>Laptop</a></li>"+
			"				  <li class=''><a href=TabletList>Tablet</a></li>"+
			"				  <li class=''><a href=PhoneList>Phone</a></li>"+
			"				  <li class=''><a href=Trending>Trending</a></li>"+
			"				  <li class=''><a href=Account>Account</a></li>"+
			"				</ul>"+
			"				<form action='#' class='navbar-search pull-left'>"+
								"<script type='text/javascript' src='assets/js/javascript.js'></script>"+
									"<div name='autofillform'>"+
									"<input type='text' name='searchId' value='' class='search-query span2' id='searchId' onkeyup='doCompletion()' placeholder='search here..' style='padding: 5px; font-size: 16px;' />"+
									"<div id='auto-row'>"+
										"<table id='complete-table' class='gridtable' style='position: absolute; width:210px;'></table>"+
									"</div>"+
									"</div>"+
							"</form>"+						
			"				<ul class='nav pull-right'>"+
			"				<li class='dropdown'>"+
			"					<a data-toggle='dropdown' class='dropdown-toggle' href='#'><span class='icon-lock'></span> Logout <b class='caret'></b></a>"+
			"					<div class='dropdown-menu'>"+
			"					<form class='form-horizontal loginFrm' action='Logout'>"+
			"					  <div class='control-group'>"+
			"						<button type='submit' class='shopBtn btn-block'>Logout</button>"+
			
			"					  </div>"+
			"					</form>"+
			"					</div>"+
			"				</li>"+
			"				</ul>"+
			"			  </div>"+
			"			</div>"+
			"		  </div>"+
			"		</div>");
		
		
		HashMap<String,Product> selectedproducts=new HashMap<String,Product>();
		HashMap<String,Product> productmap = null;
		String line=null;
		try{
			out.println("<table>");
	        out.println("<tr>");
	        out.println("<td>");
			out.println("<h2>");
			out.println("<a href='#'>Welcome to Best Deal</a></h2>");
	        out.println("</td>");
	        out.println("</tr>");

	        out.println("<tr>");
	        out.println("<td>");
			out.println("<h2>The World trust us for The best deals website</h2>");
	        out.println("</td>");
	        out.println("</tr>");

	        out.println("<tr>");
	        out.println("<td>");
			out.println("<h2>We beat our competitor in all aspects. Price-Match Guaranteed</h2>");
	        out.println("</td>");
	        out.println("</tr>");
			
			productmap=AjaxUtility.getData();
		}catch(Exception e){}
		
		for(Map.Entry<String, Product> entry : productmap.entrySet()){
			if(selectedproducts.size()<2 && !selectedproducts.containsKey(entry.getKey())){
				String TOMCAT_HOME = System.getProperty("catalina.home");
				BufferedReader reader = new BufferedReader(new FileReader (new File(TOMCAT_HOME+"\\webapps\\BestDeal\\DealMatches.txt")));
				line=reader.readLine();
				if(line==null){ 
					out.println("<tr>");
			        out.println("<td>");

					out.println("<h2 align='center'>NO OFFERS FOUND</h2>");
					out.println("</td>");
			        out.println("</tr>");
			        break;
				}else{ 
					do {
					if(line.contains(entry.getKey())){
						out.println("<tr>");
				        out.println("<td>");
					    out.print("<h3 style='color:blue;'>"+line+"</h3>");
					    
						selectedproducts.put(entry.getKey(),entry.getValue());
						
						out.println("</td>");
				        out.println("</tr>");	
						break;
					}
				}
				while((line = reader.readLine()) != null);
			}
		}
	}
		out.println("</table>");
		Set set = selectedproducts.entrySet();
		Iterator iterator = set.iterator();
		out.println("<table>");	
		out.println("<tr>");	
			while(iterator.hasNext())
			{
				Map.Entry<String, Product> map = (Map.Entry)iterator.next();
				Product bean = (Product)map.getValue();
				
				out.println("<td>");
				out.println("<table>");
				out.println("<tr>");
				out.println("<td >");
		        out.println("<h2>Product Id<h2>");
		        out.println("</td>");
		        
		        out.println("<td>");
		        out.println("<h2>" + bean.getId() + "</h2>");
		        out.println("</td>");	
		        out.println("</tr>");
		        
		        out.println("<tr>");
		        out.println("<td>");
		        out.println("Retailer   	:");
		        out.println("</td>");
		        out.println("<td>");
		    	out.println("<a>"  + bean.getType() + "</a>");
		        out.println("</td>");	
		        out.println("</tr>");

		        out.println("<tr>");
		        out.println("<td>");
		        out.println("Product Name   :");
		        out.println("</td>");
		        out.println("<td>");
		        out.println("<a>"  + bean.getName() + "</a>");
		        out.println("</td>");	
		        out.println("</tr>");

		        out.println("<tr>");
		     
		        out.println("<td>");

		        out.println("<tr>");
		        
		        out.println("<td>");

		        out.println("<input type ='hidden' name='id' value="+bean.getId()+">");
		        String link="<a href='ViewProduct?id="+bean.getId()+"'"+">View Product</a>";
		        String linkSubmitReview ="<a href='WriteReview?productId="+bean.getId()+"&productName="+bean.getName()+"&retailer="+bean.getType()+"&category=Laptop"+"'"+">Submit Reviews</a>";
		        String linkViewReview   ="<a href='ViewReview?productName="+bean.getName()+"'"+">View Reviews</a>";
		        out.println("<tr>");
		        out.println("<td>");
		        out.println(link);
		        out.println("</td>");
		        out.println("<td text-align='left'>");
		  	    out.println(linkSubmitReview);
		  	    out.println("</td>");
		  	    out.println("<td text-align='left'>");
		  	    out.println(linkViewReview);
		  	    out.println("</td>");
		  	    out.println("</tr>");
		  	    out.println("</table>");
		  	    out.println("</td>");
		  	    

				
			}	
			out.println("</tr>");
			out.println("</table>");
	
	out.println("<!--"+
	"Footer-->"+
	"<footer class='footer'>"+
	"<div class='row-fluid'>"+
	"<div class='span2'>"+
	"<h5>Your Account</h5>"+
	"<a href='Account'>Account</a><br>"+
	 "</div>"+
	"<div class='span2'>"+
	"<h5>Products</h5>"+
	"<a href='TVList'>Tvs</a><br>"+
	"<a href='LaptopsList'>Laptops</a><br>"+
	"<a href='TabletList'>Tablets</a><br>"+
	"<a href='PhoneList'>Phones</a><br>"+
	"<a href='Trending'>Trending</a><br>"+
	 "</div>"+
	"<div class='span2'>"+
	"<h5>Our Top Clients</h5>"+
	"<a href='#'>Samsung</a> <br>"+
	"<a href='#'>Apple</a><br>"+
	"<a href='#'>Dell</a><br>"+
	"<a href='#'>Google</a><br>"+
	"<a href='#'>Sony</a> <br/>"+
	 "</div>"+
	 "<div class='span6'>"+
	"<h5>Best Deal - Best Price Guarantee</h5>"+
	"The standard chunk of Lorem Ipsum used since the 1500s is reproduced below for"+
	 "those interested. Sections 1.10.32 and 1.10.33 from 'de Finibus Bonorum et"+ 
	 "Malorum' by Cicero are also reproduced in their exact original form,"+ 
	"accompanied by English versions from the 1914 translation by H. Rackham."+
	 "</div>"+
	 "</div>"+
	"</footer>"+


	"<div class='copyright'>"+
	"<div class='container'>"+
		"<p class='pull-right'>"+
			"<a href='#'><img src='assets/img/maestro.png' alt='payment'></a>"+
			"<a href='#'><img src='assets/img/mc.png' alt='payment'></a>"+
			"<a href='#'><img src='assets/img/pp.png' alt='payment'></a>"+
			"<a href='#'><img src='assets/img/visa.png' alt='payment'></a>"+
			"<a href='#'><img src='assets/img/disc.png' alt='payment'></a>"+
		"</p>"+
		"<span>Copyright &copy; 2017<br>Best Deal - Best Price Guarantee </span>"+
	"</div>"+
	"</div>"+
	"<a href='#' class='gotop'><i class='icon-double-angle-up'></i></a>"+
	    "<!-- Placed at the end of the document so the pages load faster -->"+
	    "<script src='assets/js/jquery.js'></script>"+
		"<script src='assets/js/bootstrap.min.js'></script>"+
		"<script src='assets/js/jquery.easing-1.3.min.js'></script>"+
	    "<script src='assets/js/jquery.scrollTo-1.4.3.1-min.js'></script>"+
	    "<script src='assets/js/shop.js'></script>"+
	  "</body>"+
	"</html>");
	}
	
	
}