import Adapters.MainController;
import Adapters.Presenter;
import Database.DataAccess;
import ch.qos.logback.classic.LoggerContext;
import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import io.github.cdimascio.dotenv.Dotenv;
import org.slf4j.LoggerFactory;
import java.util.Scanner;

/**
 * The schedule planner application.
 */
public class App {

    private final SchedulerUI schedulerUI;

    /**
     * Constructs an App object.
     */
    public App(Scanner in) {
        DataAccess databaseGateway = new DataAccess(initializeDatabase());
        Presenter presenter = new Presenter();
        MainController mainController = new MainController(databaseGateway, presenter);
        schedulerUI = new SchedulerUI(in, mainController);
    }

    /**
     * Sets the app to the home page.
     */
    public void run() {
        schedulerUI.toHome();
    }

    /**
     * The main program for running the app.
     */
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        App app = new App(in);
        app.run();
    }

    private MongoClient initializeDatabase(){
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
}