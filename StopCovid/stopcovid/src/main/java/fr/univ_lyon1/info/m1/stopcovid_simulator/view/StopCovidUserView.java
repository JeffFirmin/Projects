package fr.univ_lyon1.info.m1.stopcovid_simulator.view;

import fr.univ_lyon1.info.m1.stopcovid_simulator.Controller.Controller;
import fr.univ_lyon1.info.m1.stopcovid_simulator.Controller.strategy.Strategy;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;

import java.util.List;

public class StopCovidUserView {
    private final VBox gui = new VBox();
    private final VBox contacts = new VBox();
    private final HBox firstLine = new HBox();
    private Label statusLabel;
    private String userName;
    private Controller controller;
    private boolean nightMode = false;

    //declarer infecté
    private final EventHandler<ActionEvent> declare = new EventHandler<ActionEvent>() {
        @Override
        public void handle(final ActionEvent event) {
            final int infectedBtnIndex = 3;
            if (!controller.getUser(userName).getStatus().toString().equals(
                    controller.getStatusData("INFECTED").toString())) {
                Strategy strat = ((JfxView) (gui.getParent().getParent())).getCurrentStrategy();
                controller.declareBtnAction(userName, strat);
                ((JfxView) (gui.getParent().getParent())).refreshAllStatusLabels();
                ((JfxView) (gui.getParent().getParent())).getServer().fillRiskyUsersLabels();
                ((Button) gui.getChildren().get(infectedBtnIndex))
                        .setText("Mettre utilisateur sain");
            } else {
                controller.setStatus(userName, controller.getStatusData("NO_RISK"));
                ((JfxView) (gui.getParent().getParent())).refreshAllStatusLabels();
                ((Button) gui.getChildren().get(infectedBtnIndex))
                        .setText("Déclarer infection");
            }
        }
    };

    Node getGui() {
        return gui;
    }

    /**
     * Class for the view of 1 user.
     * @param name name of the user.
     * @param c controller of the application.
     */
    public StopCovidUserView(final String name, final Controller c) {

        this.userName = name;
        this.controller = c;
        this.statusLabel = new Label(controller.getStatus(name).toString());
        final Label l = new Label(controller.getName(name));
        firstLine.getChildren().add(l);
        gui.setStyle("-fx-padding: 10; -fx-border-width: 1;"
                + " -fx-border-radius: 5; -fx-border-color: #505050;");

        final Button declareBtn = new Button("Déclarer infection");
        declareBtn.setOnAction(this.declare);
        gui.getChildren().addAll(firstLine, new Label("Contacts:"), contacts,
                declareBtn, statusLabel);

    }

    /**
     * CovidUserview for tests (avoid to initialize graphic elements).
     * @param name name of the user.
     * @param test value to call this constructor.
     * @param c controller of the application.
     */
    public StopCovidUserView(final String name, final Controller c, final int test) {
        userName = name;
        this.controller = c;
        this.statusLabel = null;
        final Label l = null;
        gui.setStyle("-fx-padding: 10; -fx-border-width: 1;"
                + " -fx-border-radius: 5; -fx-border-color: #505050;");
        final Button declareBtn = null;
    }

    /**
     * creates the combo of users we want to meet.
     */
    public void initCombo() {
        List<StopCovidUserView> users = ((JfxView) gui.getParent().getParent()).getUsers();
        final ObservableList<StopCovidUserView> usersList = FXCollections.observableArrayList();
        users.forEach((k) -> {
            if (!k.getUserName().equals(userName)) {
                usersList.add(k);
            }
        });
        ComboBox<StopCovidUserView> userViewComboBox = new ComboBox<>();
        userViewComboBox.setItems(usersList);
        final Button btn = new Button("est rentré en contact avec " + userName + " ! ");
        btn.setOnAction((event) -> {
            if (userViewComboBox.getValue() == null) {
                controller.meetError();
            } else {
                ((JfxView) (gui.getParent().getParent())).meet(this, userViewComboBox.getValue());
            }
        });
        firstLine.getChildren().addAll(new Label("  avec  "), userViewComboBox, btn);
        userViewComboBox.getSelectionModel().select(0);
    }

    // MVC
    @Override
    public String toString() {
        return userName;
    }
    /**
     * Simulate the meeting of two users. Each user will keep the identifier of
     * the other in memory, and will notify the other if infected.
     *
     */

    public void rencontreLabelsSort() {
        controller.getUser(userName).sortContacts();
        ((JfxView) (gui.getParent().getParent())).refreshAllContactsLabels();
    }


    public String getUserName() {
        return userName;
    }

    /**
     * change the label for one user.
     * @param status the new status we want to set.
     */
    public void setStatusLabel(final String status) {
        statusLabel.setText(status);
    }

    /**
     * set status label with the datas of the controller.
     */
    public void refreshStatusLabel() {
        setStatusLabel(controller.getUser(userName).getStatus().toString());
    }


    /**
     * set the contacts with the datas of the controller.
     */
    public void refreshContacts() {
        while (!contacts.getChildren().isEmpty()) {
            contacts.getChildren().remove(0);
        }
        controller.getUser(userName).getContacts().forEach((k, v) -> {
            final HBox box = new HBox();
            final Label meetLabel = new Label(k  + "     " + v);
            if (!nightMode) {
                box.setStyle("-fx-padding: 2;" + "-fx-border-style: solid inside;"
                        + "-fx-border-width: 1;" + "-fx-border-insets: 5;"
                        + "-fx-border-radius: 5;" + "-fx-border-color: #505050;");
                meetLabel.setTextFill(Paint.valueOf("#505050"));
            } else {
                box.setStyle("-fx-padding: 2;" + "-fx-border-style: solid inside;"
                        + "-fx-border-width: 1;" + "-fx-border-insets: 5;"
                        + "-fx-border-radius: 5;" + "-fx-border-color: #9dd7ef;");
                meetLabel.setTextFill(Paint.valueOf("#9dd7ef"));
            }
            box.setAlignment(Pos.BASELINE_CENTER);
            final Button delete = new Button("x");
            delete.setOnMouseClicked((event) -> {
                controller.removeContact(userName, k);
                refreshContacts();
            });
            box.getChildren().addAll(meetLabel, new Separator(Orientation.VERTICAL), delete);
            contacts.getChildren().add(box);
        });
    }

    /**
     * enable / disable the night mode for one view of a user.
     */
    public void nightMode() {
        if (!nightMode) {
            changeColor("#9dd7ef");
            nightMode = true;
        } else {
            changeColor("#505050");
            nightMode = false;
        }
    }

    /**
     * change the color of the viex of one user.
     * @param color
     */
    public void changeColor(final String color) {
        final int index0 = 0;
        final int index1 = 1;
        statusLabel.setTextFill(Paint.valueOf(color));
        gui.setStyle("-fx-padding: 10; -fx-border-width: 1;"
                + " -fx-border-radius: 5; -fx-border-color: " + color + ";");
        contacts.getChildren().forEach(node -> {
            ((HBox) node).setStyle("-fx-padding: 2;" + "-fx-border-style: solid inside;"
                    + "-fx-border-width: 1;" + "-fx-border-insets: 5;"
                    + "-fx-border-radius: 5;" + "-fx-border-color: " + color + ";");
            ((Label) ((HBox) node).getChildren().get(index0))
                    .setTextFill(Paint.valueOf(color));
        });
        ((Label) gui.getChildren().get(index1)).setTextFill(Paint.valueOf(color));
        ((Label) (((HBox) gui.getChildren().get(index0)).getChildren().get(index0)))
                .setTextFill(Paint.valueOf(color));
        ((Label) (((HBox) gui.getChildren().get(index0)).getChildren().get(index1)))
                .setTextFill(Paint.valueOf(color));
    }

    /**
     * reset all datas for a user : all contacts and set status to no risk.
     */
    public void reset() {
        controller.getUser(userName).getContacts().clear();
        controller.getUser(userName).setStatus(controller.getStatusData("NO_RISK"));
        refreshContacts();
        refreshStatusLabel();
    }

}
