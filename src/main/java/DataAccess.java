import Schedule.ScheduleDataAccess;
import com.mongodb.DBObject;
import com.mongodb.client.*;
import com.mongodb.client.model.Updates;
import org.bson.Document;
import org.bson.conversions.Bson;
import static com.mongodb.client.model.Filters.*;
import User.UserDataAccess;
import User.UserDoesNotExistException;

import java.util.*;

import org.bson.types.ObjectId;

import javax.print.Doc;

public class DataAccess implements UserDataAccess, ScheduleDataAccess {

    // To directly connect to a single MongoDB server (note that this will not auto-discover the primary even
// if it's a member of a replica set:
    private final MongoDatabase database;

    public DataAccess(MongoClient mongo) {
        database = mongo.getDatabase( "Application" );
    }

    public void test(){
        Document doc = findData("Schedule", eq("UUID", "ass")).first();
        for (Document item : (ArrayList<Document>)doc.get("ass")){
//            item.get()
            System.out.println(item);
        }
        System.out.println(doc.get("ass"));
    }

    @Override
    public String[] loadUserWithUsername(String username) throws UserDoesNotExistException {
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
    public ScheduleInfo loadScheduleWithID(String id) {
        Document doc = findData("Schedule", eq("UUID", id)).first();
//        if (scDoc == null) {
//            try { // temp solution
//                throw new SCDoesNotExistException(username);
//            } catch (SCDoesNotExistException e) {
//                e.printStackTrace();
//            }
//        }
        List<Document> testing = (List<Document>) doc.get("days");
        for (Document item: testing){
            System.out.println(item);
        }
        System.out.println(doc.getList("days", Document.class));
//        ScheduleInfo scheduleInfo = new ScheduleInfo(doc.getString("Schedule_name"),
//                                                     doc.getString("UUID"),
//                doc.get("days", details ));
        List<List<List<Map<String, String>>>> info = new ArrayList<>();
        ScheduleInfo scheduleInfo = new ScheduleInfo("", "", info);
        return scheduleInfo;
    }

    @Override
    public List<ScheduleInfo> loadSchedulesAssociatedWith(String username) {
        Document doc = findData("User_Schedule", eq("username", username)).first();
        if (doc == null) {
            try { // temp solution
                throw new USCDoesNotExistException(username);
            } catch (USCDoesNotExistException e) {
                e.printStackTrace();
            }
        }
        List<String> scheduleIDs = doc.getList("schedules_id", String.class);
        List<ScheduleInfo> schedules = new ArrayList<>();
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

    public void saveSchedule(String id, String scheduleName, String username, boolean isPublic, List<List<List<Map<String, String>>>> days) {
        MongoCollection<Document> sc = database.getCollection("Schedule");
        Document daysDocument = new Document();
        for (List<List<Map<String, String>>> day: days) {
            Document workouts = new Document();
            Document meals = new Document();
            for (Map<String, String> workout: day.get(0)) {
                Document workoutDocument = new Document();
                workoutDocument.append("workoutName", workout.get(workoutName));
                workoutDocument.append("calories", workout.get(calories));
                workouts.append("workout", workoutDocument);
            }
            for (Map<String, String> meal: day.get(1)) {
                Document mealDocument = new Document();
                mealDocument.append("meal_name", meal.get(mealName));
                mealDocument.append("calories", meal.get(calories));
                meals.append("meal", mealDocument);
            }
            ArrayList<Object> array = new ArrayList<>();
            array.add(workouts);
            array.add(meals);
            daysDocument.append("day",array);
        }
        Document newSchedule = new Document("Schedule_name", scheduleName).append("public", isPublic).append("UUID", id).append("days", daysDocument);
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
    public void editUser(String key, String change, String username) {
        MongoCollection<Document> uc = database.getCollection("User");
        Bson equalComparison = eq("username", username);
        uc.updateOne(equalComparison, Updates.addToSet(key, change)); // username is unique
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