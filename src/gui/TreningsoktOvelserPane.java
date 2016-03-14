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
import treningsdagbok.Ovelse;
import treningsdagbok.Treningsokt;

import java.util.List;

public class TreningsoktOvelserPane extends GridPane
{
    private Database database;
    private Stage window;
    private Scene main;
    private TabPane tabPane;
    private Treningsokt treningsokt;

    public TreningsoktOvelserPane(Database database, Stage window, Scene main, TabPane tabPane, Treningsokt treningsokt)
    {
        this.database = database;
        this.window = window;
        this.main = main;
        this.tabPane = tabPane;
        this.treningsokt = treningsokt;
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
        Label label = new Label("Treningsøkt " + treningsokt.getDato() + ":");
        label.setFont(Font.font("Verdana", FontWeight.BOLD, 30));
        add(label, 0, 0, 3, 1);

        // Setup list
        List<Ovelse> ovelseMedResultater = database.getResultatManager().getOvelserMedResultater(treningsokt.getOktNr());
        ObservableList<Ovelse> ovelseResultatObservableList = FXCollections.observableArrayList(ovelseMedResultater);
        ListView<Ovelse> ovelseResultatListView = new ListView<>(ovelseResultatObservableList);
        add(ovelseResultatListView, 0, 1, 3, 4);
        ovelseResultatListView.setPrefWidth(DBApp.SIZE_X / 2);

        // Setup buttons
        Button buttonLeggTil = new Button("Legg til resultat");
        buttonLeggTil.setOnAction(e -> {
            Ovelse ovelse = ovelseResultatListView.getSelectionModel().getSelectedItem();
            Scene scene = new Scene(new AddResultatPane(database, window, main, tabPane, treningsokt, ovelse), DBApp.SIZE_X, DBApp.SIZE_Y);
            window.setScene(scene);
        });

        Button buttonTilbake = new Button("Tilbake");
        buttonTilbake.setOnAction(e -> {
            tabPane.getSelectionModel().select(0);
            window.setScene(main);
        });

        // Equal sized buttons
        buttonTilbake.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);

        // Buttons properties
        TilePane tileButtons = new TilePane(Orientation.HORIZONTAL);
        tileButtons.setPadding(new Insets(20, 10, 20, 0));
        tileButtons.setHgap(10);
        tileButtons.setVgap(10);
        tileButtons.getChildren().addAll(buttonLeggTil, buttonTilbake);

        // Add buttons to grid
        add(tileButtons, 0, 5, 4, 1);

    }
}
