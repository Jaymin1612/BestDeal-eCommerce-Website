
public class Product {

	String productName;
	String productId;
	String productType;
	
	public Product(String productId, String productName, String productType) {
		// TODO Auto-generated constructor stub
		this.productId = productId;
		this.productName = productName;
		this.productType = productType;
	}
	
	public Product(){}
	
	public String getName() {
		// TODO Auto-generated method stub
		return productName;
	}
	
	public void setName(String productName) {
		// TODO Auto-generated method stub
		this.productName = productName;
	}

	public String getId() {
		// TODO Auto-generated method stub
		return productId;
	}
	
	public void setId(String productId) {
		// TODO Auto-generated method stub
		this.productId = productId;
	}
	
	public String getType() {
		// TODO Auto-generated method stub
		return productType;
	}
	
	public void setType(String productType) {
		// TODO Auto-generated method stub
		this.productType = productType;
	}
	
}
