package gui;

import database.Database;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TabPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.TilePane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import java.util.List;

public class LoggPane extends GridPane
{
    private Database database;
    private Stage window;
    private Scene main;
    private TabPane tabPane;

    public LoggPane(Database database, Stage window, Scene main, TabPane tabPane)
    {
        this.database = database;
        this.window = window;
        this.main = main;
        this.tabPane = tabPane;
        setup();
    }

    private void setup()
    {
        // Paddings
        setAlignment(Pos.CENTER);
        setHgap(10);
        setVgap(10);
        setPadding(new Insets(25, 25, 25, 25));

        // Label on top
        Label label = new Label("Treningslogg:");
        label.setFont(Font.font("Verdana", FontWeight.BOLD, 30));
        add(label, 0, 0, 3, 1);

        // Setup list
        List<String> logg = database.getTreningsoktManager().getTreningsNotater();
        ObservableList<String> treningsoktObservableList = FXCollections.observableArrayList(logg);
        ListView<String> loggListView = new ListView<>(treningsoktObservableList);
        loggListView.setPrefWidth(DBApp.SIZE_X);
        add(loggListView, 0, 1, 5, 4);

        // Setup buttons
        Button buttonTilbake = new Button("Tilbake");
        buttonTilbake.setOnAction(e -> {

            // Go back to previous scene
            tabPane.getSelectionModel().select(0);
            window.setScene(main);
        });


        // Buttons properties
        buttonTilbake.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        TilePane tileButtons = new TilePane(Orientation.HORIZONTAL);
        tileButtons.setPadding(new Insets(20, 10, 20, 0));
        tileButtons.setHgap(10);
        tileButtons.setVgap(10);
        tileButtons.getChildren().addAll(buttonTilbake);

        // Add button to grid
        add(tileButtons, 0, 6, 4, 1);
    }

}
