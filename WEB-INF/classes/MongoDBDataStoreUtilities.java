import java.util.ArrayList;
import java.util.HashMap;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.MongoClient;

public class MongoDBDataStoreUtilities {

	static DBCollection myReviews;
	public static void getConnection(){
		MongoClient mongo;
		mongo = new MongoClient("localhost", 27017);
		DB db = mongo.getDB("CustomerReviews");
		myReviews= db.getCollection("myReviews");
	}
	
	public static HashMap<String, ArrayList<Review>> selectReview(){
		getConnection();
		HashMap<String, ArrayList<Review>> reviewHashmap=new HashMap<String, ArrayList<Review>>();
		DBCursor cursor = myReviews.find();
		while (cursor.hasNext()){
			BasicDBObject obj = (BasicDBObject) cursor.next();
			if(! reviewHashmap.containsKey(obj.getString("productName"))){
				ArrayList<Review> arr = new ArrayList<Review>();
				reviewHashmap.put(obj.getString("productName"), arr);
			}
			ArrayList<Review> listReview = reviewHashmap.get(obj.getString("productName"));
			Review review =new Review(obj.getString("productName"),obj.getString("productType"),obj.getString("price"),
					obj.getString("maker"), obj.getString("userId"), obj.getString("retailerName"), obj.getString("zipcode"),
					obj.getString("city"),obj.getString("state"),obj.getString("onSale"), obj.getString("rebate"), 
					obj.getString("age"), obj.getString("gender"), obj.getString("occupation"), obj.getString("reviewRating"),
					obj.getString("reviewDate"), obj.getString("reviewText"));
			listReview.add(review);
		}
		return reviewHashmap;
	}
	
	public static void insertReview(String productName, String productType, String price,String maker,String userId,String retailerName,String zipcode, 
			String city,String state,String onSale,String rebate,String age,String gender,String occupation,String reviewRating,
			String reviewDate,String reviewText){
			getConnection();
			BasicDBObject doc = new BasicDBObject("title", "myReviews").
			append("productName", productName).
			append("productType", productType).
			append("price", price).
			append("maker", maker).
			append("userId", userId).
			append("retailerName", retailerName).
			append("zipcode", zipcode).
			append("city", city).
			append("state", state).
			append("onSale", onSale).
			append("rebate", rebate).
			append("age", age).
			append("gender", gender).
			append("occupation", occupation).
			append("reviewRating", reviewRating).
			append("reviewDate", reviewDate).
			append("reviewText", reviewText);
			myReviews.insert(doc);
	}
	
}
