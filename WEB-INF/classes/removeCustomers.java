import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.PrintWriter;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class removeCustomers {
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
		
		String username = request.getParameter("username");
		HashMap<String, User> hm=new HashMap<String, User>();
		String TOMCAT_HOME = System.getProperty("catalina.home");
		//user details are validated using a file 
		//if the file containts username and passoword user entered user will be directed to home page
		//else error message will be shown
		try
		{		
          FileInputStream fileInputStream = new FileInputStream(new File(TOMCAT_HOME+"\\webapps\\BestDeal\\UserDetails.txt"));
          ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);	      
		  hm = (HashMap)objectInputStream.readObject();
		}
		catch(Exception e)
		{
				
		}
		User user = hm.get(username);
		if(user!=null)
		{
		 if (username.equals(user.getName())) 
			{
			 
			}
		}
		
	}	
}
