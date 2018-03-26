import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.*;

@WebServlet("/Account")

public class Account extends HttpServlet {
	private String error_msg;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
		displayAccount(request, response);
	}

	/* Display Account Details of the Customer (Name and Usertype) */

	protected void displayAccount(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
		Utilities utility = new Utilities(request, pw);
		try
         {  
           response.setContentType("text/html");
			if(!utility.isLoggedin())
			{
				HttpSession session = request.getSession(true);				
				session.setAttribute("login_msg", "Please Login to add items to cart");
				response.sendRedirect("Login");
				return;
			}

			HttpSession session=request.getSession(); 
			User user=utility.getUser();
			
			if(user.getUsertype().equals("retailer")){
				pw.print("<!DOCTYPE html>" +
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
						   "<script type='text/javascript' src='assets/js/javascript.js'></script>"+
							"<script type='text/javascript' src='assets/js/jquery-3.2.1.js'></script>"+
							"	<!--[if IE 7]>"+
							"		<link href='css/font-awesome-ie7.min.css' rel='stylesheet'>"+
							"	<![endif]-->"+

							"<!--[if lt IE 9]>"+
							"		<script src='http://html5shim.googlecode.com/svn/trunk/html5.js'></script>"+
							"	<![endif]-->"+

							"	<!-- Favicons -->"+
						    "<link rel='shortcut icon' href='assets/ico/favicon.ico'>"+
						 " </head>"+
						"<body onclick=init()>"+

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
						"	<p><strong> Hello, "+user.getName()+" </strong><br></p>"+
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
					"				  <li class=''><a href=#>Televisions</a></li>"+
					"				  <li class=''><a href=#>Laptop</a></li>"+
					"				  <li class=''><a href=#>Tablet</a></li>"+
					"				  <li class=''><a href=#>Phone</a></li>"+
					"				  <li class=''><a href=#>Trending</a></li>"+
					"				  <li class=''><a href=Account>Account</a></li>"+
					"				</ul>"+
					"				<form action='#' class='navbar-search pull-left'>"+
										"<script type='text/javascript' src='assets/js/javascript.js'></script>"+
											"<div name='autofillform'>"+
											"<input type='text' name='searchId' value='' class='search-query span2' id='searchId' onkeyup='doCompletion()' placeholder='search here..' style='padding: 5px; font-size: 16px;' />"+
											"<div id='auto-row'>"+
												"<table id='complete-table' class='gridtable' style='position: absolute; width:315px;'></table>"+
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
				pw.print("<div class='span9'><div class='well well-small'><h3> User Details</h3>");
				pw.print("<div class='row-fluid'>");
				pw.print("<div id='content'><div class='post'><h2 class='title meta'>");
				pw.print("<a style='font-size: 24px;'>Account</a>");
				pw.print("</h2><div class='entry'>");
				pw.print("<table class='gridtable'>");
				pw.print("<tr>");
				pw.print("<td> User Name: </td>");
				pw.print("<td>" +user.getName()+ "</td>");
				pw.print("</tr>");
				pw.print("<tr>");
				pw.print("<td> User Type: </td>");
				pw.print("<td>" +user.getUsertype()+ "</td>");
				pw.print("</tr>");
				pw.print("</table>");
				pw.print("</br><a style='font-size: 24px;'>Add Products</a></br>");
				pw.print("<form method=post action='addProducts'>");
				pw.print("<table>");
				pw.print("<tr>");
				pw.print("<td>Product: <td>");
				pw.print("<td><select name='product' class='input'><option value='tv' selected>Television</option>"+
						"<option value='laptop'>Laptop</option><option value='tablet'>Tablet</option>"+
						"<option value='phone'>Phone</option></select></td>");
				pw.print("</tr><tr>");
				pw.print("<td>id: </td>");
				pw.print("<td><input type='text' name='id' value='' class='input' required /></td>");
				pw.print("</tr><tr>");
				pw.print("</tr><tr>");
				pw.print("<td>name: </td>");
				pw.print("<td><input type='text' name='name' value='' class='input' required /></td>");
				pw.print("</tr><tr>");
				pw.print("</tr><tr>");
				pw.print("<td>price: </td>");
				pw.print("<td><input type='text' name='price' value='' class='input' required /></td>");
				pw.print("</tr><tr>");
				pw.print("</tr><tr>");
				pw.print("<td>image: </td>");
				pw.print("<td><input type='text' name='image' value='' class='input' required /><p>(Please also enter the correct file extension)</p></td>");
				pw.print("</tr><tr>");
				pw.print("</tr><tr>");
				pw.print("<td>manufacturer: </td>");
				pw.print("<td><input type='text' name='maker' value='' class='input' required /></td>");
				pw.print("</tr><tr>");
				pw.print("</tr><tr>");
				pw.print("<td>condition: </td>");
				pw.print("<td><input type='text' name='condition' value='' class='input' required /></td>");
				pw.print("</tr><tr>");
				pw.print("</tr><tr>");
				pw.print("<td>discount: </td>");
				pw.print("<td><input type='text' name='discount' value='' class='input' required /></td>");
				pw.print("</tr><tr>");
				pw.print("</tr><tr>");
				pw.print("<td><input type='submit' class='btnbuy' value='Add' style='float: right;"+
						"height: 20px margin: 20px; margin-right: 10px;'></input></td>");
				pw.print("</tr>");
				pw.print("</table></form>");
				pw.print("</br><a style='font-size: 24px;'>Add Products</a></br>");
				pw.print("<form method='post' action='removeProducts'>");
				pw.print("<table>");
				pw.print("<tr>");
				pw.print("<td>Enter product id: </td>");
				pw.print("<td><input type='text'  name='id' value='' class='input' required></td>");
				pw.print("</tr><tr>");
				pw.print("<td><input type='submit' class='btnbuy' value='Remove' style='float: right;"+
						"height: 20px margin: 20px; margin-right: 10px;'></input></td>");
				pw.print("</tr>");
				pw.print("</table></form>");
				pw.print("</div></div></div>");
				pw.print("</div><!-- /container -->");
				utility.printHtml("Footer.html");
			}
			else if(user.getUsertype().equals("salesman")){
				pw.print("<!DOCTYPE html>" +
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
						   "<script type='text/javascript' src='assets/js/javascript.js'></script>"+
							"<script type='text/javascript' src='assets/js/jquery-3.2.1.js'></script>"+
							"	<!--[if IE 7]>"+
							"		<link href='css/font-awesome-ie7.min.css' rel='stylesheet'>"+
							"	<![endif]-->"+

							"<!--[if lt IE 9]>"+
							"		<script src='http://html5shim.googlecode.com/svn/trunk/html5.js'></script>"+
							"	<![endif]-->"+

							"	<!-- Favicons -->"+
						    "<link rel='shortcut icon' href='assets/ico/favicon.ico'>"+
						 " </head>"+
						"<body onclick=init()>"+

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
						"	<p><strong> Hello, "+user.getName()+" </strong><br></p>"+
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
					"				  <li class=''><a href=#>Televisions</a></li>"+
					"				  <li class=''><a href=#>Laptop</a></li>"+
					"				  <li class=''><a href=#>Tablet</a></li>"+
					"				  <li class=''><a href=#>Phone</a></li>"+
					"				  <li class=''><a href=#>Trending</a></li>"+
					"				  <li class=''><a href=Account>Account</a></li>"+
					"				</ul>"+
					"				<form action='#' class='navbar-search pull-left'>"+
										"<script type='text/javascript' src='assets/js/javascript.js'></script>"+
											"<div name='autofillform'>"+
											"<input type='text' name='searchId' value='' class='search-query span2' id='searchId' onkeyup='doCompletion()' placeholder='search here..' style='padding: 5px; font-size: 16px;' />"+
											"<div id='auto-row'>"+
												"<table id='complete-table' class='gridtable' style='position: absolute; width:315px;'></table>"+
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
				pw.print("<div class='span9'><div class='well well-small'><h3> User Details</h3>");
				pw.print("<div class='row-fluid'>");
				pw.print("<div id='content'><div class='post'><h2 class='title meta'>");
				pw.print("<a style='font-size: 24px;'>Account</a>");
				pw.print("</h2><div class='entry'>");
				pw.print("<table class='gridtable'>");
				pw.print("<tr>");
				pw.print("<td> User Name: </td>");
				pw.print("<td>" +user.getName()+ "</td>");
				pw.print("</tr>");
				pw.print("<tr>");
				pw.print("<td> User Type: </td>");
				pw.print("<td>" +user.getUsertype()+ "</td>");
				pw.print("</tr>");
				pw.print("</table></form>");
				pw.print("</br><a style='font-size: 24px;'>Remove Customer Accounts</a></br>");
				pw.print("<form method='post' action='removeCustomers'>");
				pw.print("<table>");
				pw.print("<tr>");
				pw.print("<td>Enter username of customer: </td>");
				pw.print("<td><input type='text'  name='username' value='' class='input' required></td>");
				pw.print("</tr><tr>");
				pw.print("<td><input type='submit' class='btnbuy' value='Remove' style='float: right;"+
						"height: 20px margin: 20px; margin-right: 10px;'></input></td>");
				pw.print("</tr>");
				pw.print("</table></form>");
				pw.print("</div></div></div>");
				pw.print("</div><!-- /container -->");
				utility.printHtml("Footer.html");

			}
			else{
				utility.printHtml("Header.html");
				utility.printHtml("LeftNavigationBar.html");
				pw.print("<div class='span9'><div class='well well-small'><h3> User Details</h3>");
				pw.print("<div class='row-fluid'>");
				pw.print("<div id='content'><div class='post'><h2 class='title meta'>");
				pw.print("<a style='font-size: 24px;'>Account</a>");
				pw.print("</h2><div class='entry'>");
				pw.print("<table class='gridtable'>");
				pw.print("<tr>");
				pw.print("<td> User Name: </td>");
				pw.print("<td>" +user.getName()+ "</td>");
				pw.print("</tr>");
				pw.print("<tr>");
				pw.print("<td> User Type: </td>");
				pw.print("<td>" +user.getUsertype()+ "</td>");
				pw.print("</tr>");
				pw.print("</table>");		
				pw.print("</div></div></div>");
				pw.print("</div></div></div>");
				utility.printHtml("Footer.html");	
			}
		}
		catch(Exception e)
		{
		}		
	}
}
