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
        String[] userInfo = new String[4];
        MongoCollection<Document> collection = database.getCollection("User");
        Bson equalComparison = eq("username", username);
//        collection.find(equalComparison).forEach(doc -> System.out.println(doc.toJson()));
        FindIterable<Document> iterable = collection.find(equalComparison); // username is unique
        Document doc = iterable.first();
        if (doc == null) {
            //raise UserDoesNotExistException;
            return null;
        }
        userInfo[0] = doc.getString("username");
        userInfo[1] = doc.getString("password");
        userInfo[2] = doc.getString("name");
        userInfo[3] = doc.getString("email");
        return userInfo;
    }

    @Override
    public String[] loadSchedule() {
        MongoCollection<Document> collection = database.getCollection("Schedule");
        //TODO: implement this
        return new String[0];
    }


    @Override
    public void saveUser(String username, String password, String name, String email){
        MongoCollection<Document> collection = database.getCollection("User");
        Document newuser = new Document("name", name).append("username", username).append("email", email).append("password", password);
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