import Schedule.ScheduleDataAccess;
import com.mongodb.DBObject;
import com.mongodb.client.*;
import com.mongodb.client.model.Updates;
import org.bson.Document;
import org.bson.conversions.Bson;
import static com.mongodb.client.model.Filters.*;
import User.UserDataAccess;
import User.UserDoesNotExistException;

import org.bson.types.ObjectId;

import java.util.ArrayList;
import java.util.List;

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
        List<DBObject> array = new ArrayList<DBObject>();
        Document new_us = new Document("username",username).append("schedules", array);
        ObjectId id2 = usc.insertOne(new_us).getInsertedId().asObjectId().getValue();
        //TODO: encrypt password?
    }

    @Override
    public void saveSchedule(String scheduleName, String username, boolean isPublic) {
        MongoCollection<Document> sc = database.getCollection("Schedule");
        Document newSchedule = new Document("Schedule_name", scheduleName).append("public", isPublic);
        //TODO: implement this
        saveUserScheduleCollection(username, "1");
    }

    public void saveUserScheduleCollection(String username, String scheduleId) {
        MongoCollection<Document> suc = database.getCollection("User_Schedule");
        Bson equalComparison = eq("username", username);
//        uc.find(equalComparison).forEach(doc -> System.out.println(doc.toJson()));
        suc.updateOne(equalComparison, Updates.push("schedules",scheduleId)); // username is unique

    }

    @Override
    public void editUser() {

    }

    public void editUserScheduleCollection() {

    }

}