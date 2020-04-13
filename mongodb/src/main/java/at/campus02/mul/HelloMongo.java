package at.campus02.mul;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.function.Consumer;
import java.util.stream.Collectors;

import org.bson.Document;
import org.json.JSONArray;

import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;

public class HelloMongo {

	public static void main(String[] args) {
		MongoClient mongoClient = null;
		try {
			mongoClient = new MongoClient("localhost", 27017);
			for (String dbName : mongoClient.listDatabaseNames()) {
				System.out.println(dbName);
			}

			MongoDatabase database = mongoClient.getDatabase("testDB");
			database.drop();
			database.createCollection("customers");
			System.out.println("Current collections: ");
			for (String collectionName : database.listCollectionNames()) {
				System.out.println("  " + collectionName);
			}
			MongoCollection<Document> collection = database.getCollection("customers");
			System.out.println("Collection size: " + collection.count());
			initCustomers(collection);
			System.out.println("Collection size after initCustomers: " + collection.count());
			
			exampleQueries(collection);
			
			
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if(null!=mongoClient) {
				mongoClient.close();
			}
		}
	}
	
	private static void exampleQueries(MongoCollection<Document> collection) {
		long numberOfFemaleCustomers = collection.count(Filters.eq("gender", "Female"));
		System.out.println("===== Number of Female Customers: " + numberOfFemaleCustomers);
		FindIterable<Document> maleCustomersLoggedIn = collection.find(Filters.and(Filters.eq("gender", "Male"), Filters.eq("session.logged_out", false)));
		printResult("Male Customers Logged In", maleCustomersLoggedIn);
		
		FindIterable<Document> powerUsers = collection.find(Filters.in("usage.frequency", "Often", "Daily"));
		printResult("Power Users", powerUsers);

		FindIterable<Document> customersWithoutSSN = collection.find(Filters.eq("ssn", null));
		printResult("Customers Withour SSN", customersWithoutSSN);
	}
	
	private static void printResult(String header, FindIterable<Document> documents) {
		System.out.println("===== "+header+": ");
		documents.forEach((Consumer<? super Document>) (Document document) -> {
			System.out.println(document.toJson());
		});
	}

	private static void initCustomers(MongoCollection<Document> collection) throws IOException {
		ClassLoader classloader = Thread.currentThread().getContextClassLoader();
		InputStream is = classloader.getResourceAsStream("MOCK_DATA.json");
		String jsonString;
		try (InputStreamReader isr = new InputStreamReader(is); BufferedReader reader = new BufferedReader(isr)) {
			jsonString = reader.lines().collect(Collectors.joining(System.lineSeparator()));
		}
		System.out.println("JSON String from Classpath: ");
		System.out.println(jsonString);

		JSONArray jsonArray = new JSONArray(jsonString);
		jsonArray.forEach(entry -> {
			Document document = Document.parse(entry.toString());
			System.out.println(document);

			collection.insertOne(document);
		});
	}

}
