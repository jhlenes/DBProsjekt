package gui;

import database.Database;
import treningsdagbok.Maal;
import treningsdagbok.Ovelse;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import treningsdagbok.Treningsokt;

import java.sql.Date;
import java.sql.Time;
import java.util.List;

public class DBApp extends Application
{
    // Window constraints
    public final static int SIZE_X = 500;
    public final static int SIZE_Y = 500;

    // Database connection
    private Database database;

    // GUI components
    private Stage window;
    private Scene main;
    private TabPane tabPane;

    public static void main(String[] args)
    {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage)
    {
        // Setup database connection
        database = new Database();

        // Setup window
        window = primaryStage;
        window.setTitle("Treningsdagbok");

        // Setup main scene
        tabPane = new TabPane();
        main = new Scene(tabPane, SIZE_X, SIZE_Y);

        // Add content to main scene
        Tab tabTreningsokt = new Tab();
        tabTreningsokt.setText("Treningsøkter");
        GridPane treningsoktLayout = new TreningsoktPane(database, window, main, tabPane);
        tabTreningsokt.setContent(treningsoktLayout);

        Tab tabOvelse = new Tab();
        tabOvelse.setText("Øvelser");
        GridPane ovelseLayout = new OvelsePane(database, window, main, tabPane);
        tabOvelse.setContent(ovelseLayout);

        tabPane.getTabs().addAll(tabTreningsokt, tabOvelse);

        // Tabs should not be closed
        tabPane.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);

        // Show window
        window.setScene(main);
        window.show();
    }

}
