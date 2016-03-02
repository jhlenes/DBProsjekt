package gui;

import database.Database;
import database.Ovelse;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.TilePane;
import javafx.stage.Stage;

import java.util.List;

public class DBApp extends Application
{
    private Database database;
    private Stage window;
    private Scene ovelserScene;
    private Scene addScene;

    private ListView<Ovelse> ovelseListView;
    private ObservableList<Ovelse> ovelseObservableList;

    private Button leggTil;
    private Button endre;
    private Button slett;

    public static void main(String[] args)
    {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage)
    {
        // Setup database connection
        database = new Database();

        GridPane layout = setupOvelseScene();
        ovelserScene = new Scene(layout, 500, 500);

        GridPane addLayout = setupAddScene();
        addScene = new Scene(addLayout, 500, 500);


        // Setup Window
        window = primaryStage;
        window.setTitle("Treningsdagbok");
        window.setScene(ovelserScene);
        window.show();
    }

    private GridPane setupOvelseScene()
    {
        GridPane layout = setupLayout();
        setupOvelseListView(layout);
        setupButtons(layout);
        return layout;
    }

    private GridPane setupLayout()
    {
        GridPane layout = new GridPane();
        layout.setHgap(10);
        layout.setVgap(10);
        layout.setPadding(new Insets(25, 25, 25, 25));
        return layout;
    }

    private void setupOvelseListView(GridPane layout)
    {
        List<Ovelse> ovelser = database.getOvelser();
        ovelseObservableList = FXCollections.observableArrayList(ovelser);
        ovelseListView = new ListView<>(ovelseObservableList);
        ovelseListView.setMaxHeight(Control.USE_PREF_SIZE);
        layout.add(ovelseListView, 0, 0, 3, 4);
    }

    private void setupButtons(GridPane layout)
    {
        leggTil = new Button("Legg til");
        leggTil.setOnAction(e -> window.setScene(addScene));
        endre = new Button("Endre");
        slett = new Button("Slett");
        slett.setOnAction(e -> {
            Ovelse ovelse = ovelseListView.getSelectionModel().getSelectedItem();
            database.deleteOvelse(ovelse.getOvelseNr());
            ovelseObservableList.remove(ovelse);
        });

        leggTil.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        endre.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        slett.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);

        TilePane tileButtons = new TilePane(Orientation.HORIZONTAL);

        tileButtons.setPadding(new Insets(20, 10, 20, 10));
        tileButtons.setHgap(10.0);
        tileButtons.setVgap(10.0);
        tileButtons.getChildren().addAll(leggTil, endre, slett);

        layout.add(tileButtons, 0, 4, 3, 1);
    }

    private GridPane setupAddScene()
    {
        GridPane layout = setupLayout();
        setupEditMenu(layout);
        return layout;
    }

    private void setupEditMenu(GridPane layout)
    {
        Label navnLabel = new Label("Navn:");
        Label beskrivelseLabel = new Label("Beskrivelse:");
        TextField navnTF = new TextField();
        TextField beskrivelseTF = new TextField();

        Button send = new Button("Send");
        send.setOnAction(event -> {
            database.addOvelse(navnTF.getText(), beskrivelseTF.getText());
            ovelseObservableList.add(database.getOvelse(navnTF.getText()));
            navnTF.clear();
            beskrivelseTF.clear();
            window.setScene(ovelserScene);
        });

        layout.add(navnLabel, 0, 0);
        layout.add(navnTF, 1, 0);
        layout.add(beskrivelseLabel, 0, 1);
        layout.add(beskrivelseTF, 1, 1);
        layout.add(send, 1, 2);
    }

}
