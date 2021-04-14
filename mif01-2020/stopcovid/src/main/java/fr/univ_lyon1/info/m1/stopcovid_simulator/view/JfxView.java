package fr.univ_lyon1.info.m1.stopcovid_simulator.view;

import java.util.ArrayList;
import java.util.List;

import fr.univ_lyon1.info.m1.stopcovid_simulator.Controller.Controller;
import fr.univ_lyon1.info.m1.stopcovid_simulator.Controller.strategy.Strategy;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;

public class JfxView extends HBox {
    private List<StopCovidUserView> users = new ArrayList<>();
    private final ComboBox<Strategy> strategies = new ComboBox<>();
    private StopCovidServerView server;
    private Controller controller;
    private boolean nightMode;
    /** View for the whole application.
     * @param stage The JavaFX stage where everything will be displayed.
     * @param width width in px
     * @param height height in px
     * @param c controller of the application
     *
     */
    public JfxView(final Stage stage, final int width,
                   final int height, final Controller c) {
        nightMode = false;
        controller = c;
        server = new StopCovidServerView(c);
        // Name of window
        stage.setTitle("StopCovid Simulator");
        final HBox root = this;
        final VBox usersBox = new VBox();
        final ObservableList<StopCovidUserView> usersList = FXCollections.observableArrayList();
        root.setBackground(new Background(new BackgroundFill(
                Paint.valueOf("#9dd7ef"), null, null)));
        usersBox.getChildren().add(new Label("Utilisateurs"));
        controller.getUsers().forEach((k, v) -> {
            final StopCovidUserView u = new StopCovidUserView(k, controller);
            users.add(u);
            usersBox.getChildren().add(u.getGui());
            usersList.add(u);
        });
        root.getChildren().add(usersBox);
        users.forEach((k) -> {
            k.initCombo();
        });
        final VBox meetBox = new VBox();
        final Label l = new Label("Simulateur de proximité");
        final ComboBox<StopCovidUserView> userA = new ComboBox<>();
        final ComboBox<StopCovidUserView> userB = new ComboBox<>();
        userA.setItems(usersList);
        userB.setItems(usersList);
        controller.getStrategies().forEach((k) -> {
            strategies.getItems().add(k);
        });
        strategies.getSelectionModel().select(0);
        final Button meetBtn = new Button("Rencontre");
        meetBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(final ActionEvent event) {
                if (userA.getValue() == null || userB.getValue() == null) {
                    controller.meetError();
                } else {
                    meet(userA.getValue(), userB.getValue());
                }
            }
        });
        final Button autoBtn = new Button("Simulations aléatoires");
        autoBtn.setOnAction(event -> {
            final int numberOfSimulations = 15;
            final int percentage = 100;
            final int half = 50;
            for (int i = 0; i < numberOfSimulations; i++) {
                double random = Math.random() * percentage;
                if (random < half) {
                    String user1 = controller.getRandomUser();
                    String user2 = controller.getRandomUser();
                    while (user2.equals(user1)) {
                        user2 = controller.getRandomUser();
                    }
                    StopCovidUserView userView1 = null;
                    StopCovidUserView userView2 = null;
                    for (StopCovidUserView u : users) {
                        if (user1.equals(u.getUserName())) {
                            userView1 = u;
                        }
                        if (user2.equals(u.getUserName())) {
                            userView2 = u;
                        }
                    }
                    System.out.println(user1 + "  meets  " + user2);
                    meet(userView1, userView2);
                } else {
                    String user = controller.getRandomUser();
                    StopCovidUserView userView = null;
                    for (StopCovidUserView u : users) {
                        if (user.equals(u.getUserName())) {
                            userView = u;
                        }
                    }
                    double randomStrategy = (Math.random() * percentage)
                            % (controller.getStrategies().size() - 1);
                    strategies.getSelectionModel().select((int) randomStrategy);
                    final int infectedBtnIndex = 3;
                    if (!controller.getUser(user).getStatus()
                            .equals(controller.getStatusData("INFECTED"))) {
                        controller.declareBtnAction(user, getCurrentStrategy());
                        server.fillRiskyUsersLabels();
                        ((Button) ((VBox) userView.getGui()).getChildren().get(infectedBtnIndex))
                                .setText("Mettre utilisateur sain");
                        System.out.println(user + " infected");
                    } else {
                        controller.setStatus(user, controller.getStatusData("NO_RISK"));
                        ((JfxView) (((VBox) userView.getGui()).getParent().getParent()))
                                .refreshAllStatusLabels();
                        ((Button) ((VBox) userView.getGui()).getChildren().get(infectedBtnIndex))
                                .setText("Déclarer infection");
                        System.out.println(user + " NOT infected any more");
                    }
                }
                refreshAll();
            }
        });
        final Button nightModeBtn = new Button("Mode nuit");
        nightModeBtn.setOnAction(event -> {
            if (!nightMode) {
                ((Label) ((VBox) root.getChildren().get(0)).getChildren().get(0))
                        .setTextFill(Paint.valueOf("#9dd7ef"));
                ((Label) ((VBox) root.getChildren().get(2)).getChildren().get(0))
                        .setTextFill(Paint.valueOf("#9dd7ef"));
                root.setBackground(new Background(
                        new BackgroundFill(Paint.valueOf("#505050"), null, null)));
                for (StopCovidUserView u : users) {
                    u.nightMode();
                }
                server.nightMode();
                nightMode = true;
            } else {
                ((Label) ((VBox) root.getChildren().get(0)).getChildren().get(0))
                        .setTextFill(Paint.valueOf("#505050"));
                ((Label) ((VBox) root.getChildren().get(2)).getChildren().get(0))
                        .setTextFill(Paint.valueOf("#505050"));
                root.setBackground(new Background(
                        new BackgroundFill(Paint.valueOf("#9dd7ef"), null, null)));
                for (StopCovidUserView u : users) {
                    u.nightMode();
                }
                server.nightMode();
                nightMode = false;
            }
        });

        Button reset = new Button("réinitialiser");

        reset.setOnAction(event -> {
            for (StopCovidUserView u : users) {
                u.reset();
            }
            server.reset();
        });

        meetBox.getChildren().addAll(l, new HBox(userA, userB, strategies, nightModeBtn), meetBtn,
            new Separator(), server.getGui(), autoBtn, new Separator(), reset);

        root.getChildren().addAll(new Separator(), meetBox);

        final Scene scene = new Scene(root, width, height);
        stage.setScene(scene);
        stage.show();
    }

    StopCovidServerView getServer() {
        return server;
    }

    public Strategy getCurrentStrategy() {
        return strategies.getValue();
    }

    public List<StopCovidUserView> getUsers() {
        return users;
    }

    /**
     * general view incidence when two users meet.
     * @param a the view of the first user.
     * @param b the view of the second user.
     */
    public void meet(final StopCovidUserView a, final StopCovidUserView b) {
        if (controller.meetBtnAction(a.getUserName(), b.getUserName())) {
            a.rencontreLabelsSort();
            b.rencontreLabelsSort();
        }
    }

    /**
     * refresh all userView's labels.
     */
    public void refreshAllStatusLabels() {
        for (StopCovidUserView u: users) {
            u.refreshStatusLabel();
        }
    }

    /**
     * refresh all userView's contacts.
     */
    public void refreshAllContactsLabels() {
        for (StopCovidUserView u : users) {
            u.refreshContacts();
        }
    }

    /**
     * refresh the whole application's views.
     */
    public void refreshAll() {
        refreshAllContactsLabels();
        refreshAllStatusLabels();
        server.refreshRiskyUsersLabel();
    }
}
