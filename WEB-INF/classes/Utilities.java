import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;

@WebServlet("/Utilities")

/* 
	Utilities class contains class variables of type HttpServletRequest, PrintWriter,String and HttpSession.

	Utilities class has a constructor with  HttpServletRequest, PrintWriter variables.
	  
*/

public class Utilities extends HttpServlet{
	HttpServletRequest req;
	PrintWriter pw;
	String url;
	HttpSession session; 
	public Utilities(HttpServletRequest req, PrintWriter pw) {
		this.req = req;
		this.pw = pw;
		this.url = this.getFullURL();
		this.session = req.getSession(true);
	}



	/*  Printhtml Function gets the html file name as function Argument, 
		If the html file name is Header.html then It gets Username from session variables.
		Account ,Cart Information ang Logout Options are Displayed*/

	public void printHtml(String file) {
		String result = HtmlToString(file);
		//to print the right navigation in header of username cart and logout etc
		
		if (file == "Header.html") {
	
				result=result+"";
			if (session.getAttribute("username")!=null){
				String username = session.getAttribute("username").toString();
				username = Character.toUpperCase(username.charAt(0)) + username.substring(1);
				
				result = "<!DOCTYPE html>" +
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
						"	<p><strong> Hello, "+username+" </strong><br></p>"+
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
					"		</div>";

				
				
			}
			else
				result = "<!DOCTYPE html>" +
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
						"		<img src='assets/img/bestdeals.png' alt='bootstrap sexy shop' style='width: 224px; height: 51px;'>"+
						"	</a>"+
						"	</h1>"+
						"	</div>"+
						"	<div class='span4'>"+
						"	<div class=''>"+
						
						"	<i class=''></i>"+
						"	</div>"+
						"	</div>"+
						"	<div class='span4 alignR'>"+
						"	<p><br> <strong>  </strong><br><br></p>"+
						"	<form class='form-horizontal loginFrm' action='ViewOrder'>"+
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
					"				</ul>"+
					"				<div class='span1'></div>"+
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
					"					<a data-toggle='dropdown' class='dropdown-toggle' href='#'><span class='icon-lock'></span> Login <b class='caret'></b></a>"+
					"					<div class='dropdown-menu'>"+
					"					<form class='form-horizontal loginFrm' action='Login'>"+
					"					  <div class='control-group'>"+
					"						<button type='submit' class='shopBtn btn-block' href='Login'>Login</button>"+
					"					  </div>"+
					"					</form>"+
					"					<form class='form-horizontal loginFrm' action='Registration'>"+
					"					  <div class='control-group'>"+
					"						<button type='submit' class='shopBtn btn-block'>Register</button>"+
					
					"					  </div>"+
					"					</form>"+
					"					</div>"+
					"				</li>"+
					"				</ul>"+
					"			  </div>"+
					"			</div>"+
					"		  </div>"+
					"		</div>";

				pw.print(result);
		} else
				pw.print(result);
	}
	

	/*  getFullURL Function - Reconstructs the URL user request  */

	public String getFullURL() {
		String scheme = req.getScheme();
		String serverName = req.getServerName();
		int serverPort = req.getServerPort();
		String contextPath = req.getContextPath();
		StringBuffer url = new StringBuffer();
		url.append(scheme).append("://").append(serverName);

		if ((serverPort != 80) && (serverPort != 443)) {
			url.append(":").append(serverPort);
		}
		url.append(contextPath);
		url.append("/");
		return url.toString();
	}

	/*  HtmlToString - Gets the Html file and Converts into String and returns the String.*/
	public String HtmlToString(String file) {
		String result = null;
		try {
			String webPage = url + file;
			URL url = new URL(webPage);
			URLConnection urlConnection = url.openConnection();
			InputStream is = urlConnection.getInputStream();
			InputStreamReader isr = new InputStreamReader(is);

			int numCharsRead;
			char[] charArray = new char[1024];
			StringBuffer sb = new StringBuffer();
			while ((numCharsRead = isr.read(charArray)) > 0) {
				sb.append(charArray, 0, numCharsRead);
			}
			result = sb.toString();
		} 
		catch (Exception e) {
		}
		return result;
	} 

	/*  logout Function removes the username , usertype attributes from the session variable*/

	public void logout(){
		session.removeAttribute("username");
		session.removeAttribute("usertype");
	}
	
	/*  login Function checks whether the user is loggedIn or Not*/

	public boolean isLoggedin(){
		if (session.getAttribute("username")==null)
			return false;
		return true;
	}

	/*  username Function returns the username from the session variable.*/
	
	public String username(){
		if (session.getAttribute("username")!=null)
			return session.getAttribute("username").toString();
		return null;
	}
	
	/*  userid Function returns the usertype from the session variable.*/
	public String userid(){
		if (session.getAttribute("userid")!=null)
			return session.getAttribute("userid").toString();
		return null;
	}
	
	/*  usertype Function returns the usertype from the session variable.*/
	public String usertype(){
		if (session.getAttribute("usertype")!=null)
			return session.getAttribute("usertype").toString();
		return null;
	}
	
	/*  getUser Function checks the user is a customer or retailer or manager and returns the user class variable.*/
	public User getUser(){
		String usertype = usertype();
		HashMap<String, User> hm=new HashMap<String, User>();
		String TOMCAT_HOME = System.getProperty("catalina.home");
			try
			{		
				FileInputStream fileInputStream=new FileInputStream(new File(TOMCAT_HOME+"\\webapps\\BestDeal\\UserDetails.txt"));
				ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);	      
				hm= (HashMap)objectInputStream.readObject();
			}
			catch(Exception e)
			{
			}	
		User user = hm.get(username());
		return user;
	}
	
	/*  getCustomerOrders Function gets  the Orders for the user*/
	public ArrayList<OrderItem> getCustomerOrders(){
		ArrayList<OrderItem> order = new ArrayList<OrderItem>(); 
		if(OrdersHashMap.orders.containsKey(username()))
			order= OrdersHashMap.orders.get(username());
		return order;
	}

	/*  getOrdersPaymentSize Function gets  the size of OrderPayment */
	public int getOrderPaymentSize(){
		HashMap<Integer, ArrayList<OrderPayment>> orderPayments = new HashMap<Integer, ArrayList<OrderPayment>>();
		String TOMCAT_HOME = System.getProperty("catalina.home");
			try
			{
				FileInputStream fileInputStream = new FileInputStream(new File(TOMCAT_HOME+"\\webapps\\BestDeal\\PaymentDetails.txt"));
				ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);	      
				orderPayments = (HashMap)objectInputStream.readObject();
			}
			catch(Exception e)
			{
			
			}
			int size=0;
			for(Map.Entry<Integer, ArrayList<OrderPayment>> entry : orderPayments.entrySet()){
					 size=entry.getKey();
			}
			return size;		
	}

	/*  CartCount Function gets  the size of User Orders*/
	public int CartCount(){
		if(isLoggedin())
		return getCustomerOrders().size();
		return 0;
	}
	
	/* StoreProduct Function stores the Purchased product in Orders HashMap according to the User Names.*/

	public void storeProduct(String name,String type,String maker, String acc){
		if(!OrdersHashMap.orders.containsKey(username())){	
			ArrayList<OrderItem> arr = new ArrayList<OrderItem>();
			OrdersHashMap.orders.put(username(), arr);
		}
		ArrayList<OrderItem> orderItems = OrdersHashMap.orders.get(username());
		if(type.equals("tvs")){
			TV tv;
			tv = SaxParserDataStore.tvs.get(name);
			OrderItem orderitem = new OrderItem(tv.getName(), tv.getPrice(), tv.getImage(), tv.getRetailer());
			orderItems.add(orderitem);
		}
		if(type.equals("laptops")){
			Laptop laptop = null;
			laptop = SaxParserDataStore.laptops.get(name);
			OrderItem orderitem = new OrderItem(laptop.getName(), laptop.getPrice(), laptop.getImage(), laptop.getRetailer());
			orderItems.add(orderitem);
		}
		if(type.equals("tablets")){
			Tablet tablet = null;
			tablet = SaxParserDataStore.tablets.get(name);
			OrderItem orderitem = new OrderItem(tablet.getName(), tablet.getPrice(), tablet.getImage(), tablet.getRetailer());
			orderItems.add(orderitem);
		}
		if(type.equals("phones")){	
			Phone phone = SaxParserDataStore.phones.get(name); 
			OrderItem orderitem = new OrderItem(phone.getName(), phone.getPrice(), phone.getImage(), phone.getRetailer());
			orderItems.add(orderitem);
		}
		
	}
	// store the payment details for orders
	public void storePayment(int orderId,
		String orderName,double orderPrice,String userAddress,String creditCardNo){
		HashMap<Integer, ArrayList<OrderPayment>> orderPayments= new HashMap<Integer, ArrayList<OrderPayment>>();
		String TOMCAT_HOME = System.getProperty("catalina.home");
			// get the payment details file 
			try{
				orderPayments = MySqlDataStoreUtilities.selectOrder();
			}catch(Exception e){}
			try
			{
				FileInputStream fileInputStream = new FileInputStream(new File(TOMCAT_HOME+"\\webapps\\BestDeal\\PaymentDetails.txt"));
				ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);	      
				orderPayments = (HashMap)objectInputStream.readObject();
			}
			catch(Exception e)
			{
			
			}
			if(orderPayments==null)
			{
				orderPayments = new HashMap<Integer, ArrayList<OrderPayment>>();
			}
			// if there exist order id already add it into same list for order id or create a new record with order id
			
			if(!orderPayments.containsKey(orderId)){	
				ArrayList<OrderPayment> arr = new ArrayList<OrderPayment>();
				orderPayments.put(orderId, arr);
			}
			ArrayList<OrderPayment> listOrderPayment = orderPayments.get(orderId);		
			OrderPayment orderpayment = new OrderPayment(orderId,username(),orderName,orderPrice,userAddress,creditCardNo);
			listOrderPayment.add(orderpayment);	
			
			try{
				MySqlDataStoreUtilities.insertOrder(orderId, username(), orderName, orderPrice, userAddress, creditCardNo);
			}catch(Exception e){}
			
			// add order details into file

			try
			{	
				FileOutputStream fileOutputStream = new FileOutputStream(new File(TOMCAT_HOME+"\\webapps\\BestDeal\\PaymentDetails.txt"));
				ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            	objectOutputStream.writeObject(orderPayments);
				objectOutputStream.flush();
				objectOutputStream.close();       
				fileOutputStream.close();
			}
			catch(Exception e)
			{
				System.out.println("inside exception file not written properly");
			}	
	}

	
	/* getConsoles Functions returns the Hashmap with all consoles in the store.*/

	public HashMap<String, TV> getTvs(){
			HashMap<String, TV> hm = new HashMap<String, TV>();
			hm.putAll(SaxParserDataStore.tvs);
			return hm;
	}
	
	/* getGames Functions returns the  Hashmap with all Games in the store.*/

	public HashMap<String, Laptop> getLaptops(){
			HashMap<String, Laptop> hm = new HashMap<String, Laptop>();
			hm.putAll(SaxParserDataStore.laptops);
			return hm;
	}
	
	/* getTablets Functions returns the Hashmap with all Tablet in the store.*/

	public HashMap<String, Tablet> getTablets(){
			HashMap<String, Tablet> hm = new HashMap<String, Tablet>();
			hm.putAll(SaxParserDataStore.tablets);
			return hm;
	}
	
	/* getPhones Functions returns the Hashmap with all Tablet in the store.*/

	public HashMap<String, Phone> getPhones(){
			HashMap<String, Phone> hm = new HashMap<String, Phone>();
			hm.putAll(SaxParserDataStore.phones);
			return hm;
	}
	
	/* getProducts Functions returns the Arraylist of consoles in the store.*/

	public ArrayList<String> getProductsTvs(){
		ArrayList<String> ar = new ArrayList<String>();
		for(Map.Entry<String, TV> entry : getTvs().entrySet()){			
			ar.add(entry.getValue().getName());
		}
		return ar;
	}
	
	/* getProducts Functions returns the Arraylist of games in the store.*/

	public ArrayList<String> getProductsLaptop(){		
		ArrayList<String> ar = new ArrayList<String>();
		for(Map.Entry<String, Laptop> entry : getLaptops().entrySet()){
			ar.add(entry.getValue().getName());
		}
		return ar;
	}
	
	/* getProducts Functions returns the Arraylist of Tablets in the store.*/

	public ArrayList<String> getProductsTablets(){		
		ArrayList<String> ar = new ArrayList<String>();
		for(Map.Entry<String, Tablet> entry : getTablets().entrySet()){
			ar.add(entry.getValue().getName());
		}
		return ar;
	}
	
	/* getProducts Functions returns the Arraylist of Tablets in the store.*/

	public ArrayList<String> getProductsPhones(){		
		ArrayList<String> ar = new ArrayList<String>();
		for(Map.Entry<String, Phone> entry : getPhones().entrySet()){
			ar.add(entry.getValue().getName());
		}
		return ar;
	}	
	
	public void storeReview(String productName, String productType, String price, String maker, String retailerName,String zipcode, 
			String city, String state, String onSale, String rebate, String age, String gender, String occupation,
			String reviewRating, String reviewDate, String reviewText){ 
		HashMap<String, ArrayList<Review>> reviews= new HashMap<String, ArrayList<Review>>();
		try{
			reviews=MongoDBDataStoreUtilities.selectReview();
		}catch(Exception e){ }
		if(!reviews.containsKey(productName)){
			ArrayList<Review> arr = new ArrayList<Review>();
			reviews.put(productName, arr);
		}
		ArrayList<Review> listReview = reviews.get(productName);
		Review review = new Review(productName, productType,price, maker, username(), retailerName, zipcode, 
				city, state, onSale, rebate, age, gender, occupation, reviewRating, reviewDate, reviewText);
		listReview.add(review);
		try{
		MongoDBDataStoreUtilities.insertReview(productName, productType,price, maker, username(), retailerName, zipcode, 
				city, state, onSale, rebate, age, gender, occupation, reviewRating, reviewDate, reviewText);
		}catch(Exception e){ }
	}	

}
