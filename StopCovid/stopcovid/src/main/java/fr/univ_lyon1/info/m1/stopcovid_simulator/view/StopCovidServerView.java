package fr.univ_lyon1.info.m1.stopcovid_simulator.view;

import fr.univ_lyon1.info.m1.stopcovid_simulator.Controller.Controller;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

import javafx.scene.paint.Paint;

import java.util.HashMap;
import java.util.Map;

public class StopCovidServerView {
    private final VBox gui = new VBox();
    private final Map<String, Label> riskyUsersLabels = new HashMap<>();
    private Controller controller;
    private boolean nightMode = false;
    StopCovidServerView(final Controller c) {
        controller = c;
    }
    /** Get the GUI object corresponding to the server. */
    public Node getGui() {
        return gui;
    }

    /**
    * Declare this user as risky, i.e. having been in contact with an infected person.
    *
    */
    // MVC
    public void fillRiskyUsersLabels() {
        controller.getRiskyUsers().forEach((v) -> {
            riskyUsersLabels.put(v, new Label(v));
        });
        refreshRiskyUsersLabel();
    }

    /**
     * refresh labels related to risky users.
     */
    public void refreshRiskyUsersLabel() {

        if (riskyUsersLabels != null) {
            while (!gui.getChildren().isEmpty()) {
                gui.getChildren().remove(0);
            }
            Label legend = new Label("Personnes à risque:");
            if (nightMode) {
                legend.setTextFill(Paint.valueOf("#9dd7ef"));
            }
            gui.getChildren().add(legend);
            riskyUsersLabels.forEach((k, v) -> {
                if (nightMode) {
                    v.setTextFill(Paint.valueOf("#9dd7ef"));
                }
                gui.getChildren().add(v);
            });
        }
    }

    /**
     * enable / disable night mode for risky user's view.
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
     * change the color of risky user's view.
     * @param color a String that represents a color (#------, or a primitive).
     */
    public void changeColor(final String color) {
        gui.getChildren().forEach(node -> {
            ((Label) node).setTextFill(Paint.valueOf(color));
        });
    }

    /**
     * reset all the risky users's view.
     */
    public void reset() {
        while (!controller.getRiskyUsers().isEmpty()) {
            controller.getRiskyUsers().remove(0);
        }

        while (!gui.getChildren().isEmpty()) {
            gui.getChildren().remove(0);
        }

        riskyUsersLabels.clear();
    }

}

