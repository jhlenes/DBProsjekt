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

import java.util.List;

public class OvelsePane extends GridPane
{
    private Database database;
    private Stage window;
    private Scene main;
    private TabPane tabPane;

    public OvelsePane(Database database, Stage window, Scene main, TabPane tabPane)
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
        Label label = new Label("Øvelser:");
        label.setFont(Font.font("Verdana", FontWeight.BOLD, 30));
        add(label, 0, 0, 3, 1);

        // Setup list
        List<Ovelse> ovelser = database.getOvelseManager().getOvelser();
        ObservableList<Ovelse> ovelseObservableList = FXCollections.observableArrayList(ovelser);
        ListView<Ovelse> ovelseListView = new ListView<>(ovelseObservableList);
        add(ovelseListView, 0, 1, 3, 4);
        ovelseListView.setPrefWidth(DBApp.SIZE_X / 2);

        // Setup buttons
        Button buttonLeggTil = new Button("Legg til");
        buttonLeggTil.setOnAction(e -> {
            Scene scene = new Scene(new AddOvelsePane(database, window, main, tabPane, ovelseObservableList), DBApp.SIZE_X, DBApp.SIZE_Y);
            window.setScene(scene);
        });

        Button buttonEndre = new Button("Endre");
        buttonEndre.setOnAction(e -> {
            Ovelse ovelse = ovelseListView.getSelectionModel().getSelectedItem();
            Scene scene = new Scene(new EditOvelsePane(database, window, main, tabPane, ovelseObservableList, ovelse), DBApp.SIZE_X, DBApp.SIZE_Y);
            window.setScene(scene);
        });

        Button buttonSlett = new Button("Slett");
        buttonSlett.setOnAction(e -> {
            Ovelse ovelse = ovelseListView.getSelectionModel().getSelectedItem();
            if (database.getOvelseManager().deleteOvelse(ovelse.getOvelseNr()))
            {
                ovelseObservableList.remove(ovelse);
            }
        });

        Button buttonMaal = new Button("Mål");
        buttonMaal.setOnAction(e -> {
            Ovelse ovelse = ovelseListView.getSelectionModel().getSelectedItem();
            Scene scene = new Scene(new MaalPane(database, window, main, tabPane, ovelse), DBApp.SIZE_X, DBApp.SIZE_Y);
            window.setScene(scene);
        });

        Button buttonResultat = new Button("Resultat");
        buttonResultat.setOnAction(e -> {
            Ovelse ovelse = ovelseListView.getSelectionModel().getSelectedItem();
            Scene scene = new Scene(new ResultatPane(database, window, main, tabPane, ovelse), DBApp.SIZE_X, DBApp.SIZE_Y);
            window.setScene(scene);
        });

        // Equal sized buttons
        buttonLeggTil.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        buttonEndre.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        buttonSlett.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        buttonMaal.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        buttonResultat.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);

        // Buttons properties
        TilePane tileButtons = new TilePane(Orientation.HORIZONTAL);
        tileButtons.setPadding(new Insets(20, 10, 20, 0));
        tileButtons.setHgap(10);
        tileButtons.setVgap(10);
        tileButtons.getChildren().addAll(buttonLeggTil, buttonEndre, buttonSlett, buttonMaal, buttonResultat);

        // Add buttons to grid
        add(tileButtons, 0, 5, 4, 1);
    }
}
