import Schedule.ScheduleDataAccess;
import com.mongodb.client.*;
import org.bson.Document;
import org.bson.conversions.Bson;
import static com.mongodb.client.model.Filters.*;
import User.UserDataAccess;
import User.UserDoesNotExistException;

import org.bson.types.ObjectId;

public class DataAccess implements UserDataAccess, ScheduleDataAccess {

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
    public String[] findUser(String username) throws UserDoesNotExistException {
        String[] userInfo = new String[4];
        MongoCollection<Document> uc = database.getCollection("User");
        Bson equalComparison = eq("username", username);
//        uc.find(equalComparison).forEach(doc -> System.out.println(doc.toJson()));
        FindIterable<Document> iterable = uc.find(equalComparison); // username is unique
        Document doc = iterable.first();
        if (doc == null) {
            throw new UserDoesNotExistException(username);
        }
        userInfo[0] = doc.getString("username");
        userInfo[1] = doc.getString("password");
        userInfo[2] = doc.getString("name");
        userInfo[3] = doc.getString("email");
        return userInfo;
    }

    @Override
    public String[] loadSchedule() {
        MongoCollection<Document> sc = database.getCollection("Schedule");
        //TODO: implement this
        return new String[0];
    }

    @Override
    public String[] loadUserScheduleCollection(String username) {
        MongoCollection<Document> usc = database.getCollection("User_Schedule");
        return new String[0];
    }


    @Override
    public void saveUser(String username, String password, String name, String email){
        MongoCollection<Document> uc = database.getCollection("User");
        MongoCollection<Document> usc = database.getCollection("User_Schedule");
        Document newUser = new Document("name", name).append("username", username).append("email", email).append("password", password);
        ObjectId id = uc.insertOne(newUser).getInsertedId().asObjectId().getValue();
        Document new_us = new Document("username",username);
        ObjectId id2 = usc.insertOne(new_us).getInsertedId().asObjectId().getValue();

        //TODO: encrypt password?
    }

    @Override
    public void saveSchedule(String schedule_name, Boolean is_public) {
        MongoCollection<Document> sc = database.getCollection("Schedule");
        Document newSchedule = new Document("Schedule_name", schedule_name).append("public", is_public);
        //TODO: implement this
        saveUserScheduleCollection();
    }

    public void saveUserScheduleCollection() {

    }

    @Override
    public void editUser() {

    }

    public void editUserScheduleCollection() {

    }

}