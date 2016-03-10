package gui;

import database.Database;
import javafx.beans.NamedArg;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import treningsdagbok.Treningsokt;

import java.util.List;

public class TreningsoktPane extends GridPane
{
    private Database database;
    private Stage window;
    private Scene main;
    private TabPane tabPane;

    public TreningsoktPane(Database database, Stage window, Scene main, TabPane tabPane)
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
        setHgap(10);
        setVgap(10);
        setPadding(new Insets(25, 25, 25, 25));

        // Setup list
        List<Treningsokt> treningsokter = database.getTreningsoktManager().getTreningsokter();
        ObservableList<Treningsokt> treningsoktObservableList = FXCollections.observableArrayList(treningsokter);
        ListView<Treningsokt> treningsoktListView = new ListView<>(treningsoktObservableList);
        treningsoktListView.setPrefWidth(500);
        add(treningsoktListView, 0, 1, 5, 4);

        // Setup buttons
        Button buttonLeggTil = new Button("Legg til");
        buttonLeggTil.setOnAction(e -> {
            Scene scene = new Scene(new AddTreningsoktPane(database, window, main, tabPane, treningsoktObservableList), DBApp.SIZE_X, DBApp.SIZE_Y);
            window.setScene(scene);
        });

        Button buttonSlett = new Button("Slett");
        buttonSlett.setOnAction(e -> {
            Treningsokt treningsokt = treningsoktListView.getSelectionModel().getSelectedItem();

            // If deletion from database was successful, delete locally
            if (database.getTreningsoktManager().deleteTreningsokt(treningsokt.getOktNr()))
            {
                treningsoktObservableList.remove(treningsokt);
            }
        });

        // Equal sized buttons
        buttonLeggTil.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        buttonSlett.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);

        // Buttons properties
        TilePane tileButtons = new TilePane(Orientation.HORIZONTAL);
        tileButtons.setPadding(new Insets(20, 10, 20, 0));
        tileButtons.setHgap(10);
        tileButtons.setVgap(10);
        tileButtons.getChildren().addAll(buttonLeggTil, buttonSlett);

        // Add buttons to grid
        add(tileButtons, 0, 5, 4, 1);
    }

}
