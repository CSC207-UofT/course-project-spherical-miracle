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

public class DataAccess implements UserDataAccess, ScheduleDataAccess {

    // To directly connect to a single MongoDB server (note that this will not auto-discover the primary even
// if it's a member of a replica set:
    private final MongoDatabase database;
    private final int workoutNum;
    private final int mealNum;

    public DataAccess(MongoClient mongo) {
        database = mongo.getDatabase( "Application" );
        workoutNum = 0;
        mealNum = 1;
    }

    public Object test() {
        String s = "asdf";
        MongoCursor<Document> cursor = findData("Schedule", eq("public", true)).cursor();
        ArrayList<Object> publicSchedules = new ArrayList<>();
        while (cursor.hasNext()) {
            publicSchedules.add(cursor.next().entrySet().toArray()); //TODO: Figure out what should be in here
        }
        System.out.println(publicSchedules);
        return publicSchedules;
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
    @SuppressWarnings("unchecked")
    public ScheduleInfo loadScheduleWith(String id) {
        Document doc = findData("Schedule", eq("UUID", id)).first();
        List<List<List<Map<String,String>>>> days = new ArrayList<>();
        for (List<Object> day: (List<List<Object>>)doc.get("days")){
            List<List<Map<String, String>>> dayList = new ArrayList<>();
            List<Map<String, String>> workoutList = new ArrayList<>();
            for (Map<String, String> workout: (List<Map<String, String>>) day.get(workoutNum)){
                HashMap<String, String> workoutMap = new HashMap<>();
                workoutMap.put(workoutName,workout.get("workoutName"));
                workoutMap.put(calories,workout.get("calories"));
                workoutList.add(workoutMap);
            };
            List<Map<String, String>> mealList = new ArrayList<>();
            for (Map<String, String> meal: (List<Map<String, String>>) day.get(mealNum)){
                HashMap<String, String> mealMap = new HashMap<>();
                mealMap.put(mealName,meal.get("mealName"));
                mealMap.put(calories,meal.get("calories"));
                mealList.add(mealMap);
            };
            dayList.add(workoutList);
            dayList.add(mealList);
            days.add(dayList);
        }
        return new ScheduleInfo((String) doc.get("UUID"), (String) doc.get("Schedule_name"), days);
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
        assert doc != null;
        List<String> scheduleIDs = doc.getList("schedules_id", String.class);
        List<ScheduleInfo> schedules = new ArrayList<>();
        for(String scheduleID: scheduleIDs) {
            schedules.add(loadScheduleWith(scheduleID));
        }
        return schedules;
    }


    @Override
    public void saveUser(String username, String password, String name, String email){
        MongoCollection<Document> uc = database.getCollection("User");
        MongoCollection<Document> usc = database.getCollection("User_Schedule");
        Document newUser = new Document("name", name).append("username", username).append("email", email).append("password", password);
        ObjectId id = Objects.requireNonNull(uc.insertOne(newUser).getInsertedId()).asObjectId().getValue();
        List<DBObject> array = new ArrayList<>();
        Document new_us = new Document("username",username).append("active_schedule", "").append("schedules", array);
        ObjectId id2 = Objects.requireNonNull(usc.insertOne(new_us).getInsertedId()).asObjectId().getValue();
        //TODO: encrypt password?
    }

    public void createSchedule(ScheduleInfo scheduleInfo, String username, boolean isPublic) {
        MongoCollection<Document> sc = database.getCollection("Schedule");
        ArrayList<Object> dayArray = new ArrayList<>();
        for (List<List<Map<String, String>>> day: scheduleInfo.getDetails()) {
            ArrayList<Object> workouts = new ArrayList<>();
            ArrayList<Object> meals = new ArrayList<>();
            for (Map<String, String> workout: day.get(workoutNum)) {
                Document workoutDocument = new Document();
                workoutDocument.append("workoutName", workout.get(workoutName));
                workoutDocument.append("calories", workout.get(calories));
                workouts.add(workoutDocument);
            }
            for (Map<String, String> meal: day.get(mealNum)) {
                Document mealDocument = new Document();
                mealDocument.append("mealName", meal.get(mealName));
                mealDocument.append("calories", meal.get(calories));
                meals.add(mealDocument);
            }
            ArrayList<Object> array = new ArrayList<>();
            array.add(workouts);
            array.add(meals);
            dayArray.add(array);
        }
        Document newSchedule = new Document("Schedule_name", scheduleInfo.getName())
                                .append("public", isPublic)
                                .append("UUID", scheduleInfo.getId())
                                .append("days", dayArray);
        ObjectId newId = Objects.requireNonNull(sc.insertOne(newSchedule).getInsertedId()).asObjectId().getValue();
        saveUserScheduleCollection(username, scheduleInfo.getId());
    }

    @Override
    public List<Object> loadPublicSchedules() {
        MongoCursor<Document> cursor = findData("Schedule", eq("public", true)).cursor();
        ArrayList<Object> publicSchedules = new ArrayList<>();
        while (cursor.hasNext()) {
            publicSchedules.add(cursor.next().entrySet().toArray());
        }
        return publicSchedules;
    }

    public void saveUserScheduleCollection(String username, String scheduleId) {
        MongoCollection<Document> suc = database.getCollection("User_Schedule");
        Bson equalComparison = eq("username", username);
        suc.updateOne(equalComparison, Updates.addToSet("schedules_id",scheduleId)); // username is unique
    }

    @Override
    public void editUser(String key, String change, String username) {
        MongoCollection<Document> uc = database.getCollection("User");
        Bson equalComparison = eq("username", username);
        uc.updateOne(equalComparison, Updates.addToSet(key, change)); // username is unique
    }

    public void editUserScheduleCollection(String key, String change, String username) {
        MongoCollection<Document> usc = database.getCollection("User_Schedule");
        Bson equalComparison = eq("username", username);
        usc.updateOne(equalComparison, Updates.addToSet(key, change)); // username is unique
    }

    @Override
    public ScheduleInfo loadActiveSchedule(String username) {
        Bson equalComparison = eq("username", username);
        Document doc = findData("User_schedule", equalComparison).first();
        assert doc != null;
        return loadScheduleWith(doc.getString("active_schedule"));
    }


    @Override
    public void updateCurrentSchedule(String username, String scheduleId){
        MongoCollection<Document> suc = database.getCollection("User_Schedule");
        Bson equalComparison = eq("username", username);
        suc.updateOne(equalComparison, Updates.set("active_schedule", scheduleId));
    }

    public void deleteSchedule(String scheduleId){
        MongoCollection<Document> sc = database.getCollection("Schedule");
        Bson equalComparison = eq("UUID", scheduleId);
        sc.deleteOne(equalComparison);
    }

    private FindIterable<Document> findData(String collectionName, Bson... filters){
        MongoCollection<Document> collection = database.getCollection(collectionName);
        Bson equalComparison = and(filters);
        return collection.find(equalComparison);
    }
}