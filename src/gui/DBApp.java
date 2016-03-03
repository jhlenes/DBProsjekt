package gui;

import database.Database;
import database.Ovelse;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
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

    private Button leggTil = new Button("Legg til");
    private Button endre = new Button("Endre");
    private Button slett = new Button("Slett");

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
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));

        setupOvelseListView(grid);
        setupButtons(grid);
        return grid;
    }

    private void setupOvelseListView(GridPane layout)
    {
        List<Ovelse> ovelser = database.getOvelser();
        ovelseObservableList = FXCollections.observableArrayList(ovelser);
        ovelseListView = new ListView<>(ovelseObservableList);
        layout.add(ovelseListView, 0, 0, 3, 4);
    }

    private void setupButtons(GridPane grid)
    {
        leggTil.setOnAction(e -> window.setScene(addScene));

        slett.setOnAction(e -> {
            Ovelse ovelse = ovelseListView.getSelectionModel().getSelectedItem();
            if (database.deleteOvelse(ovelse.getOvelseNr()))
            {
                ovelseObservableList.remove(ovelse);
            }
        });

        // Let buttons grow, otherwise they will be different sizes based
        // on the length of the label
        leggTil.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        endre.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        slett.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);

        TilePane tileButtons = new TilePane(Orientation.HORIZONTAL);
        tileButtons.setPadding(new Insets(20, 10, 20, 0));
        tileButtons.setHgap(10);
        tileButtons.setVgap(10);
        tileButtons.getChildren().addAll(leggTil, endre, slett);

        grid.add(tileButtons, 0, 4, 3, 1);
    }

    private GridPane setupAddScene()
    {
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(12);

        ColumnConstraints column1 = new ColumnConstraints();
        column1.setHalignment(HPos.RIGHT);
        grid.getColumnConstraints().add(column1);

        ColumnConstraints column2 = new ColumnConstraints();
        column2.setHalignment(HPos.LEFT);
        grid.getColumnConstraints().add(column2);

        Label labelNavn = new Label("Navn:");
        TextField tfNavn = new TextField();
        Label labelBeskrivelse = new Label("Beskrivelse:");
        TextField tfBeskrivelse = new TextField();

        Button leggTil = new Button("Legg til");
        leggTil.setOnAction(event -> {     // Update database and listview
            if (database.addOvelse(tfNavn.getText(), tfBeskrivelse.getText()))
            {
                ovelseObservableList.add(database.getOvelse(tfNavn.getText()));
                tfNavn.clear();
                tfBeskrivelse.clear();
                window.setScene(ovelserScene);
            }
        });
        Button avbryt = new Button("Avbryt");
        avbryt.setOnAction(event -> {   // Go back to previous screen
            tfNavn.clear();
            tfBeskrivelse.clear();
            window.setScene(ovelserScene);
        });

        HBox hbButtons = new HBox();
        hbButtons.setSpacing(10.0);
        hbButtons.setAlignment(Pos.CENTER_LEFT);
        hbButtons.getChildren().addAll(leggTil, avbryt);

        grid.add(labelNavn, 0, 0);
        grid.add(tfNavn, 1, 0);
        grid.add(labelBeskrivelse, 0, 1);
        grid.add(tfBeskrivelse, 1, 1);
        grid.add(hbButtons, 1, 2, 1, 1);

        return grid;
    }

}
