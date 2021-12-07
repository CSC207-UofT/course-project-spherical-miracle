import Adapters.MainController;
import Adapters.Presenter;
import Database.DataAccess;
import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;

//import java.util.logging.Level;
//import java.util.logging.Logger;

import ch.qos.logback.classic.LoggerContext;
import com.mongodb.client.MongoClients;
import io.github.cdimascio.dotenv.Dotenv;
import org.slf4j.LoggerFactory;

public class testMain {
    public static void main(String[] args) {
            DataAccess databaseGateway = new DataAccess(initializeDatabase());
            Presenter presenter = new Presenter();
            MainController mainController = new MainController(databaseGateway, presenter);
//            databaseGateway.saveUser("testing","ahaha","Tianji","ass@ass.com");
//            databaseGateway.addHeightWeight("testing",123.2,534);
//            databaseGateway.addHeightWeight("testing",126,566);
//            UserDataAccess.BodyMeasurementRecord bodyMeasurementRecord = databaseGateway.getHWListWith("testing");
//            System.out.println(bodyMeasurementRecord.getDate());
//            System.out.println(bodyMeasurementRecord.getHeight());
//            System.out.println(bodyMeasurementRecord.getWeight());
    }

    static private MongoClient initializeDatabase(){
        LoggerContext loggerContext = (LoggerContext) LoggerFactory.getILoggerFactory();
        ch.qos.logback.classic.Logger rootLogger = loggerContext.getLogger("org.mongodb.driver");
        rootLogger.setLevel(ch.qos.logback.classic.Level.OFF);
        Dotenv dotenv = Dotenv.load();
        ConnectionString URI = new ConnectionString(dotenv.get("URI"));
        MongoClientSettings settings = MongoClientSettings.builder()
                .applyConnectionString(URI)
                .build();
        return MongoClients.create(settings);
    }

//        UserController userController = new UserController(access);
//        userController.createUser("nottest1", "nottest1", "nottest1", "nottest1");
//        SetActiveScheduleUseCase s = new SetActiveScheduleUseCase(access);
//        ScheduleController scheduleController = new ScheduleController(access);
//        scheduleController.createSchedule("123", "nottest", false, new ArrayList<>());
//        FetchSchedulesUseCase f = new FetchSchedulesUseCase(access);
//        List<Schedule> schedules = f.getScheduleAssociatedWith("nottest");
//        s.setAsActiveSchedule("nottest", schedules.get(0));

        //mongo loadschedule
//        List<List<List<Map<String, String>>>> days = new ArrayList<>();
//        List<List<Map<String, String>>> day = new ArrayList<>();
//        List<Map<String, String>> workouts = new ArrayList<>();
//        List<Map<String, String>> meals = new ArrayList<>();
//        Map<String, String> workout = new HashMap<>();
//        workout.put("workoutName", "bestname");
//        workout.put("calories", "234");
//        Map<String, String> workout2 = new HashMap<>();
//        workout2.put("workoutName", "bestname2");
//        workout2.put("calories", "2342");
//        Map<String, String> meal = new HashMap<>();
//        meal.put("mealName", "meal");
//        meal.put("calories", "21421");
//        workouts.add(workout);
//        workouts.add(workout2);
//        meals.add(meal);
//        day.add(workouts);
//        day.add(meals);
//        days.add(day);
//        access.saveSchedule("abcd", "TESTsave2", "nottest", true, days);
//        access.test();
//        access.loadScheduleWithID("abcd");

//        try {
//
//        } catch (Exception e) {
//            System.out.println("no");
//        }

//        userController.addUser("test", "test", "test", "test");
//        ScheduleController scheduleController = new ScheduleController(access);
//        scheduleController.createSchedule("test3", "test", false);
    }

