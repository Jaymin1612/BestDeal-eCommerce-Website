
public class Review {
	private String productName;
	private String userId;
	private String productType;
	private String reviewRating;
	private String reviewDate;
	private String reviewText;
	private String price;
	private String maker;
	private String retailerName;
	private String zipcode;
	private String city;
	private String state;
	private String occupation;
	private String age;
	private String gender;
	private String onSale;
	private String rebate;
	
	public Review(String productName, String productType, String price,String maker,String userId,String retailerName,String zipcode, 
			String city,String state,String onSale,String rebate,String age,String gender,String occupation,String reviewRating,
			String reviewDate,String reviewText){
		this.productName=productName;
		this.userId=userId;
		this.productType=productType;
		this.reviewRating = reviewRating;
		this.reviewDate=reviewDate;
		this.reviewText = reviewText;
		this.price = price;
		this.maker = maker;
		this.retailerName = retailerName;
		this.zipcode = zipcode;
		this.city = city;
		this.state = state;
		this.occupation = occupation;
		this.age = age;
		this.gender = gender;
		this.onSale = onSale;
		this.rebate = rebate;
	}
	
	public Review(){
		
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getMaker() {
		return maker;
	}
	public void setMaker(String maker) {
		this.maker = maker;
	}
	public String getretailerName() {
		return retailerName;
	}
	public void setretailerName(String retailerName) {
		this.retailerName = retailerName;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getZipcode() {
		return zipcode;
	}
	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getonSale() {
		return onSale;
	}
	public void setonSale(String onSale) {
		this.onSale = onSale;
	}
	public String getRebate() {
		return rebate;
	}
	public void setRebate(String rebate) {
		this.rebate = rebate;
	}
	public String getOccupation() {
		return occupation;
	}
	public void setOccupation(String occupation) {
		this.occupation = occupation;
	}
	public String getAge() {
		return age;
	}
	public void setAge(String age) {
		this.age = age;
	}
	public String getProductType() {
		return productType;
	}
	public void setProductType(String productType) {
		this.productType = productType;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getReviewRating() {
		return reviewRating;
	}
	public void setReviewRating(String reviewRating) {
		this.reviewRating = reviewRating;
	}
	public String getReviewDate() {
		return reviewDate;
	}
	public void setReviewDate(String reviewDate) {
		this.reviewDate = reviewDate;
	}
	
	public String getReviewText() {
		return reviewText;
	}

	public void setReviewText(String reviewText) {
		this.reviewText = reviewText;
	}
}
