import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.*;
import org.bson.Document;
import org.bson.conversions.Bson;
import static com.mongodb.client.model.Filters.*;
import io.github.cdimascio.dotenv.Dotenv;
import org.bson.types.ObjectId;

import java.awt.*;
public class DataAccess implements DataAccessInterface {

    // To directly connect to a single MongoDB server (note that this will not auto-discover the primary even
// if it's a member of a replica set:
    private final MongoDatabase database;

    public DataAccess(MongoClient mongo) {
        database = mongo.getDatabase( "Application" );

    }

    public void testAdd(){
        MongoCollection<Document> collection = database.getCollection("User");
        Document toy = new Document("name", "yoyo").append("ages", new Document("min", 5));
        ObjectId id = collection.insertOne(toy).getInsertedId().asObjectId().getValue();
    }

    @Override
    public String[] findUser(String username) {
        String[] out_string = new String[4];
        MongoCollection<Document> collection = database.getCollection("User");
        Bson equalComparison = eq("username", username);
//        collection.find(equalComparison).forEach(doc -> System.out.println(doc.toJson()));
        FindIterable<Document> iterable = collection.find(equalComparison); // username is unique
        Document doc = iterable.first();
        out_string[0] = doc.getString("username");
        out_string[1] = doc.getString("password");
        out_string[2] = doc.getString("name");
        out_string[3] = doc.getString("email");
        return out_string;
    }

    @Override
    public String[] loadSchedule() {
        MongoCollection<Document> collection = database.getCollection("Schedule");
        //TODO: implement this
        return new String[0];
    }


    @Override
    public void saveUser(String[] user) {
        MongoCollection<Document> collection = database.getCollection("User");
        Document newuser = new Document("name", user[0]).append("username", user[1]).append("email", user[2]).append("password", user[3]);
        ObjectId id = collection.insertOne(newuser).getInsertedId().asObjectId().getValue();
        //TODO: encrypt password?
        //TODO: add a third database connecting User and Schedule
    }

    @Override
    public void saveSchedule() {
        MongoCollection<Document> collection = database.getCollection("Schedule");
        //TODO: implement this
    }

}