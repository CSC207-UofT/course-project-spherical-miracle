package Database;

import com.mongodb.DBObject;
import com.mongodb.client.*;
import com.mongodb.client.model.Updates;
import org.bson.Document;
import org.bson.conversions.Bson;
import static com.mongodb.client.model.Filters.*;
import Domain.User.UseCase.UserDoesNotExistException;
import java.time.LocalDate;
import java.util.*;
import org.mindrot.jbcrypt.BCrypt;

/**
 * Gateway that inserts and gets information from the MongoDatabase database.
 */
public class DataAccess implements UserDataAccess, ScheduleDataAccess {

    private final MongoDatabase database;
    private final int workoutNum;
    private final int mealNum;

    /**
     * Constructs a DataAccess object.
     */
    public DataAccess(MongoClient mongo) {
        database = mongo.getDatabase( "Application" );
        workoutNum = 0;
        mealNum = 1;
    }

    @Override
    public void saveUser(String username, String password, String name, String email){
        MongoCollection<Document> uc = database.getCollection("User");
        MongoCollection<Document> usc = database.getCollection("User_Schedule");
        MongoCollection<Document> uWH = database.getCollection("User_WH");
        String pwHash = BCrypt.hashpw(password, BCrypt.gensalt(10));
        Document newUser = new Document("name", name).append("username", username)
                        .append("email", email).append("password", pwHash);
        Objects.requireNonNull(uc.insertOne(newUser).getInsertedId()).asObjectId();
        List<DBObject> array = new ArrayList<>();
        Document newUs = new Document("username",username).append("active_schedule", "").append("schedules", array);
        Objects.requireNonNull(usc.insertOne(newUs).getInsertedId()).asObjectId();
        Document newUWH = new Document("username",username).append("height", array)
                .append("weight", array).append("date", array);
        Objects.requireNonNull(uWH.insertOne(newUWH).getInsertedId()).asObjectId();
    }

    @Override
    public UserInfo loadUserWithUsername(String username) throws UserDoesNotExistException {
        Document doc = findData("User", eq("username", username)).first();
        Document doc2 = findData("User_WH", eq("username", username)).first();
        if (doc == null || doc2 == null) {
            throw new UserDoesNotExistException(username);
        }
        username = doc.getString("username");
        String password = doc.getString("password");
        String name = doc.getString("name");
        String email = doc.getString("email");
        List<Double> weights = doc2.getList("weight", Double.class);
        List<Double> heights = doc2.getList("height", Double.class);

        if (heights.isEmpty() || weights.isEmpty()){
            return new UserInfo(username, password, name, email);
        } else {
            Double height = heights.get(heights.size() - 1);
            Double weight = weights.get(weights.size() - 1);
            return new UserInfo(username, password, name, email, height, weight);
        }
    }

    @Override
    public void addHeightWeight(String username, double height, double weight){
        LocalDate date = LocalDate.now();
        MongoCollection<Document> uWH = database.getCollection("User_WH");
        Bson equalComparison = eq("username", username);
        uWH.updateOne(equalComparison,Updates.combine(Updates.push("height", height),
                Updates.push("weight",weight),
                Updates.push("date",date.toString()))
        );
    }

    @Override
    public BodyMeasurementRecord getHeightsWeightsFor(String username){
        Document doc2 = findData("User_WH", eq("username", username)).first();
        assert doc2 != null;
        List<Double> weight = doc2.getList("weight", Double.class);
        List<Double> height = doc2.getList("height", Double.class);
        List<String> date = doc2.getList("date", String.class);
        List<LocalDate> localDates = new ArrayList<>();
        for (String s : date){
            localDates.add(LocalDate.parse(s));
        }
        return new BodyMeasurementRecord(weight, height, localDates);
    }

    @Override
    @SuppressWarnings("unchecked")
    public ScheduleInfo loadScheduleWithID(String id) {
        Document doc = findData("Schedule", eq("UUID", id)).first();
        List<List<List<Map<String,String>>>> days = new ArrayList<>();
        assert doc != null;
        for (List<Object> day: (List<List<Object>>)doc.get("days")){ // goes through list of days
            List<List<Map<String, String>>> dayList = new ArrayList<>();
            List<Map<String, String>> workoutList = new ArrayList<>();
            for (Map<String, String> workout: (List<Map<String, String>>) day.get(workoutNum)){
                HashMap<String, String> workoutMap = new HashMap<>();
                workoutMap.put(workoutName,workout.get("workoutName"));
                workoutMap.put(calories,workout.get("calories"));
                workoutList.add(workoutMap);
            }
            List<Map<String, String>> mealList = new ArrayList<>();
            for (Map<String, String> meal: (List<Map<String, String>>) day.get(mealNum)){
                HashMap<String, String> mealMap = new HashMap<>();
                mealMap.put(mealName,meal.get("mealName"));
                mealMap.put(calories,meal.get("calories"));
                mealList.add(mealMap);
            }
            dayList.add(workoutList);
            dayList.add(mealList);
            days.add(dayList);
        }
        return new ScheduleInfo((String) doc.get("UUID"), (String) doc.get("Schedule_name"), days);
    }

    @Override
    public List<ScheduleInfo> loadSchedulesFor(String username) {
        Document doc = findData("User_Schedule", eq("username", username)).first();
        if (doc == null) {
            try { // temp solution
                throw new USCDoesNotExistException(username);
            } catch (USCDoesNotExistException e) {
                e.printStackTrace();
            }
        }
        assert doc != null;
        List<String> scheduleIDs = doc.getList("schedules", String.class);
        List<ScheduleInfo> schedules = new ArrayList<>();
        for(String scheduleID: scheduleIDs) {
            schedules.add(loadScheduleWithID(scheduleID));
        }
        return schedules;
    }

    @Override
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
        Objects.requireNonNull(sc.insertOne(newSchedule).getInsertedId()).asObjectId();
        saveUserScheduleCollection(username, scheduleInfo.getId());
    }

    @Override
    public List<ScheduleInfo> loadPublicSchedules() {
        MongoCursor<Document> cursor = findData("Schedule", eq("public", true)).cursor();
        List<String> publicSchedulesIDs = new ArrayList<>();
        while (cursor.hasNext()) {
            publicSchedulesIDs.add(cursor.next().get("UUID", String.class));
        }
        List<ScheduleInfo> publicSchedules = new ArrayList<>();
        for (String scheduleID: publicSchedulesIDs) {
            publicSchedules.add(loadScheduleWithID(scheduleID));
        }
        return publicSchedules;
    }

    @Override
    public ScheduleInfo loadActiveSchedule(String username) {
        Bson equalComparison = eq("username", username);
        Document doc = findData("User_Schedule", equalComparison).first();
        if (doc == null){
            return null;
        }
        String activeSchedule = doc.getString("active_schedule");
        if (activeSchedule.equals("")){
            return null;
        }
        return loadScheduleWithID(doc.getString("active_schedule"));
    }

    @Override
    public void updateCurrentSchedule(String username, String scheduleId){
        MongoCollection<Document> suc = database.getCollection("User_Schedule");
        Bson equalComparison = eq("username", username);
        suc.updateOne(equalComparison, Updates.set("active_schedule", scheduleId));
    }

    @Override
    public void deleteUserSchedule(String username, String scheduleId){
        Document doc = findData("User_Schedule", eq("username", username)).first();
        assert doc != null;
        String active = doc.getString("active_schedule");
        if (active.equals(scheduleId)){
            updateCurrentSchedule(username, "");
        }
        MongoCollection<Document> suc = database.getCollection("User_Schedule");
        Bson equalComparison = eq("username", username);
        suc.updateOne(equalComparison, Updates.pull("schedules",scheduleId));
        deleteSchedule(scheduleId);
    }

    private void saveUserScheduleCollection(String username, String scheduleId) {
        MongoCollection<Document> suc = database.getCollection("User_Schedule");
        Bson equalComparison = eq("username", username);
        suc.updateOne(equalComparison, Updates.addToSet("schedules",scheduleId)); // username is unique
    }

    private void deleteSchedule(String scheduleID){
        MongoCollection<Document> sc = database.getCollection("Schedule");
        Bson equalComparison = eq("UUID", scheduleID);
        sc.deleteOne(equalComparison);
    }

    private FindIterable<Document> findData(String collectionName, Bson... filters){
        MongoCollection<Document> collection = database.getCollection(collectionName);
        Bson equalComparison = and(filters);
        return collection.find(equalComparison);
    }
}