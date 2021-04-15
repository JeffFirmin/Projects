package fr.univ_lyon1.info.m1.stopcovid_simulator;

import fr.univ_lyon1.info.m1.stopcovid_simulator.Controller.Controller;
import fr.univ_lyon1.info.m1.stopcovid_simulator.model.User;
import fr.univ_lyon1.info.m1.stopcovid_simulator.model.UserBuilder;
import fr.univ_lyon1.info.m1.stopcovid_simulator.view.JfxView;
import javafx.application.Application;
import javafx.stage.Stage;

import java.util.Map;

/**
 * Main class for the application (structure imposed by JavaFX).
 */
public class App extends Application {
    static final int WIDTH = 1000;
    static final int HEIGHT = 600;
    private static final int NB_USERS = 5;


    /**
     * With javafx, start() is called when the application is launched.
     */
    @Override
    public void start(final Stage stage) throws Exception {

        Map<String, User> users = new UserBuilder()
                .add("Foo")
                .add("Bar")
                .add("Boz")
                .build();

        Controller controller = new Controller(users);
        new JfxView(stage, WIDTH, HEIGHT, controller);

    }


    /**
     * A main method in case the user launches the application using
     * App as the main class.
     *
     * @param args Command-line arguments
     */
    public static void main(final String[] args) {

        Application.launch(args);
    }
}
