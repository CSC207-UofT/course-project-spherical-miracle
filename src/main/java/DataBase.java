import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import io.github.cdimascio.dotenv.Dotenv;

public class DataBase {

    // To directly connect to a single MongoDB server (note that this will not auto-discover the primary even
// if it's a member of a replica set:

    private static String connectionString;

    public static void main(String[] args) {
        Dotenv dotenv = Dotenv.load();
        connectionString = dotenv.get("URI");
        ConnectionString URI = new ConnectionString(connectionString);
        MongoClientSettings settings = MongoClientSettings.builder()
                .applyConnectionString(URI)
                .build();
        MongoClient mongoClient = MongoClients.create(settings);
        MongoDatabase db = mongoClient.getDatabase( "Application" );
        MongoCollection<Document> collection = db.getCollection("User");
//        Document toy = new Document("name", "yoyo").append("ages", new Document("min", 5));
//        ObjectId id = collection.insertOne(toy).getInsertedId().asObjectId().getValue();
    }

}