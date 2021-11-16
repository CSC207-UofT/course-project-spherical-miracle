import Schedule.ScheduleDataAccess;
import com.mongodb.DBObject;
import com.mongodb.client.*;
import com.mongodb.client.model.Updates;
import org.bson.Document;
import org.bson.conversions.Bson;
import static com.mongodb.client.model.Filters.*;
import User.UserDataAccess;
import User.UserDoesNotExistException;

import java.util.ArrayList;

import org.bson.types.ObjectId;

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
    public String[] findUserWithUsername(String username) throws UserDoesNotExistException {
        Document doc = findData("User", eq("username", username)).first();
        if (doc == null) {
            throw new UserDoesNotExistException(username);
        }
        String[] userInfo = new String[4];
        userInfo[0] = doc.getString("username");
        userInfo[1] = doc.getString("password");
        userInfo[2] = doc.getString("name");
        userInfo[3] = doc.getString("email");
        return userInfo;
    }

    @Override
    public ArrayList<String> loadScheduleWithID(String id) {
        Document doc = findData("Schedule", eq("UUID", id)).first();
//        if (scDoc == null) {
//            try { // temp solution
//                throw new SCDoesNotExistException(username);
//            } catch (SCDoesNotExistException e) {
//                e.printStackTrace();
//            }
//        }
        ArrayList<String> scheduleInfo = new ArrayList<>();
        scheduleInfo.add(doc.getString("Schedule_name"));
        scheduleInfo.add(doc.getString("UUID"));
        return scheduleInfo;
    }

    @Override
    public List<Object> loadUserSchedules(String username) {
        Document doc = findData("User_Schedule", eq("username", username)).first();
        if (doc == null) {
            try { // temp solution
                throw new USCDoesNotExistException(username);
            } catch (USCDoesNotExistException e) {
                e.printStackTrace();
            }
        }
        List<String> scheduleIDs = doc.getList("schedules_id", String.class);
        List<Object> schedules = new ArrayList<>();
        for(String scheduleID: scheduleIDs) {
            schedules.add(loadScheduleWithID(scheduleID));
        }
        return schedules;
    }


    @Override
    public void saveUser(String username, String password, String name, String email){
        MongoCollection<Document> uc = database.getCollection("User");
        MongoCollection<Document> usc = database.getCollection("User_Schedule");
        Document newUser = new Document("name", name).append("username", username).append("email", email).append("password", password);
        ObjectId id = uc.insertOne(newUser).getInsertedId().asObjectId().getValue();
        List<DBObject> array = new ArrayList<>();
        Document new_us = new Document("username",username).append("active_schedule", "").append("schedules", array);
        ObjectId id2 = usc.insertOne(new_us).getInsertedId().asObjectId().getValue();
        //TODO: encrypt password?
    }

    @Override
    public void saveSchedule(String id, String scheduleName, String username, boolean isPublic, ArrayList<ArrayList<ArrayList<Object>>> days) {
        MongoCollection<Document> sc = database.getCollection("Schedule");
//        for (ArrayList<ArrayList<Object>> day: days) {
//            Document workouts = new Document();
//            for (Object workout: day.get(0)) {
//                workouts.append("workout_name",workout);
//
//            }
//            for(Object meal: day.get(1)){
//
//            }
//            Document day = new Document("day", new Document(""));
//        }
        Document newSchedule = new Document("Schedule_name", scheduleName).append("public", isPublic).append("UUID", id);
        //TODO: implement this
        ObjectId newId = sc.insertOne(newSchedule).getInsertedId().asObjectId().getValue();
        saveUserScheduleCollection(username, id);
    }

    @Override
    public List<Object> loadPublicSchedules() {
        MongoCursor<Document> cursor = findData("Schedule", eq("public", true)).cursor();
        ArrayList<Object> publicSchedules = new ArrayList<>();
        while (cursor.hasNext()) {
            publicSchedules.add(cursor.next().toJson()); //TODO: Figure out what should be in here
        }
        return publicSchedules;
    }

    public void saveUserScheduleCollection(String username, String scheduleId) {
        MongoCollection<Document> suc = database.getCollection("User_Schedule");
        Bson equalComparison = eq("username", username);
//        uc.find(equalComparison).forEach(doc -> System.out.println(doc.toJson()));
        suc.updateOne(equalComparison, Updates.addToSet("schedules_id",scheduleId)); // username is unique

    }

    @Override
    public void editUser() {

    }

    public void editUserScheduleCollection() {

    }

    @Override
    public void updateCurrentSchedule(String username, String scheduleId){
        MongoCollection<Document> suc = database.getCollection("User_Schedule");
        Bson equalComparison = eq("username", username);
        suc.updateOne(equalComparison, Updates.set("active_schedule", scheduleId));
    }

    private FindIterable<Document> findData(String collectionName, Bson... filters){
        MongoCollection<Document> collection = database.getCollection(collectionName);
        Bson equalComparison = and(filters);
        return collection.find(equalComparison);
    }
}