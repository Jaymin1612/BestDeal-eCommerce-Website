import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.PrintWriter;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

@WebServlet("/addProducts")

public class addProducts {
	protected void doPost(HttpServletRequest request,
		
		HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
		String TOMCAT_HOME = System.getProperty("catalina.home");	
		
		String product = request.getParameter("product");
		String id = request.getParameter("id");
		String name = request.getParameter("name");
		String price = request.getParameter("price");
		String image = request.getParameter("image");
		String maker = request.getParameter("maker");
		String condition = request.getParameter("condition");
		String discount = request.getParameter("discount");
		
		try {
		    File inputFile = new File(TOMCAT_HOME+"\\webapps\\BestDeal\\ProductCatalog.xml");
		    DocumentBuilderFactory factory=DocumentBuilderFactory.newInstance();
		    DocumentBuilder builder = factory.newDocumentBuilder();
		    // creating input stream
		    Document doc = builder.parse(inputFile );
			
			if(product.equals("tv")){
				Element rootElement = (Element) doc.getElementsByTagName("TVCatalog");
				
				Element tv = doc.createElement("tv");
				rootElement.appendChild(tv);
				
				Attr attr = doc.createAttribute("id");
				attr.setValue(id);
				tv.setAttributeNode(attr);
				
				Element pname = doc.createElement("name");
				pname.appendChild(doc.createTextNode(name));
				tv.appendChild(pname);
				
				Element pprice = doc.createElement("price");
				pprice.appendChild(doc.createTextNode(price));
				tv.appendChild(pprice);
				
				Element pimage = doc.createElement("image");
				pimage.appendChild(doc.createTextNode(image));
				tv.appendChild(pimage);
				
				Element pmaker = doc.createElement("manufacturer");
				pmaker.appendChild(doc.createTextNode(maker));
				tv.appendChild(pmaker);
				
				Element pcond = doc.createElement("condition");
				pcond.appendChild(doc.createTextNode(condition));
				tv.appendChild(pcond);
				
				Element pdis = doc.createElement("discount");
				pdis.appendChild(doc.createTextNode(discount));
				tv.appendChild(pdis);
			}
			else if(product.equals("laptop")){
				Element rootElement = (Element) doc.getElementsByTagName("TVCatalog");
				
				Element laptop = doc.createElement("laptop");
				rootElement.appendChild(laptop);
				
				Attr attr = doc.createAttribute("id");
				attr.setValue(id);
				laptop.setAttributeNode(attr);
				
				Element pname = doc.createElement("name");
				pname.appendChild(doc.createTextNode(name));
				laptop.appendChild(pname);
				
				Element pprice = doc.createElement("price");
				pprice.appendChild(doc.createTextNode(price));
				laptop.appendChild(pprice);
				
				Element pimage = doc.createElement("image");
				pimage.appendChild(doc.createTextNode(image));
				laptop.appendChild(pimage);
				
				Element pmaker = doc.createElement("manufacturer");
				pmaker.appendChild(doc.createTextNode(maker));
				laptop.appendChild(pmaker);
				
				Element pcond = doc.createElement("condition");
				pcond.appendChild(doc.createTextNode(condition));
				laptop.appendChild(pcond);
				
				Element pdis = doc.createElement("discount");
				pdis.appendChild(doc.createTextNode(discount));
				laptop.appendChild(pdis);
			}
			
			else if(product.equals("tablet")){
				Element rootElement = (Element) doc.getElementsByTagName("TVCatalog");
				
				Element tablet = doc.createElement("tablet");
				rootElement.appendChild(tablet);
				
				Attr attr = doc.createAttribute("id");
				attr.setValue(id);
				tablet.setAttributeNode(attr);
				
				Element pname = doc.createElement("name");
				pname.appendChild(doc.createTextNode(name));
				tablet.appendChild(pname);
				
				Element pprice = doc.createElement("price");
				pprice.appendChild(doc.createTextNode(price));
				tablet.appendChild(pprice);
				
				Element pimage = doc.createElement("image");
				pimage.appendChild(doc.createTextNode(image));
				tablet.appendChild(pimage);
				
				Element pmaker = doc.createElement("manufacturer");
				pmaker.appendChild(doc.createTextNode(maker));
				tablet.appendChild(pmaker);
				
				Element pcond = doc.createElement("condition");
				pcond.appendChild(doc.createTextNode(condition));
				tablet.appendChild(pcond);
				
				Element pdis = doc.createElement("discount");
				pdis.appendChild(doc.createTextNode(discount));
				tablet.appendChild(pdis);
			}
			
			else if(product.equals("phone")){
				Element rootElement = (Element) doc.getElementsByTagName("PhoneCatalog");
				
				Element phone = doc.createElement("phone");
				rootElement.appendChild(phone);
				
				Attr attr = doc.createAttribute("id");
				attr.setValue(id);
				phone.setAttributeNode(attr);
				
				Element pname = doc.createElement("name");
				pname.appendChild(doc.createTextNode(name));
				phone.appendChild(pname);
				
				Element pprice = doc.createElement("price");
				pprice.appendChild(doc.createTextNode(price));
				phone.appendChild(pprice);
				
				Element pimage = doc.createElement("image");
				pimage.appendChild(doc.createTextNode(image));
				phone.appendChild(pimage);
				
				Element pmaker = doc.createElement("manufacturer");
				pmaker.appendChild(doc.createTextNode(maker));
				phone.appendChild(pmaker);
				
				Element pcond = doc.createElement("condition");
				pcond.appendChild(doc.createTextNode(condition));
				phone.appendChild(pcond);
				
				Element pdis = doc.createElement("discount");
				pdis.appendChild(doc.createTextNode(discount));
				phone.appendChild(pdis);
			}
			
			// write the content into xml file
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(doc);
			StreamResult result = new StreamResult(new File(TOMCAT_HOME+"\\webapps\\BestDeal\\ProductCatalog.xml"));

			// Output to console for testing
			// StreamResult result = new StreamResult(System.out);
			
			pw.println("<html>");
			pw.println("<body>");
			pw.println("<h1>something</h1>");
			pw.println("</body>");
			pw.println("</html>");
			
			transformer.transform(source, result);

			System.out.println("File saved!");

			} catch (ParserConfigurationException pce) {
				pce.printStackTrace();
			} catch (TransformerException tfe) {
				tfe.printStackTrace();
			} catch (SAXException e) {
				e.printStackTrace();
			}		
		
	}	
}
