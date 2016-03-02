package database;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Control;
import javafx.scene.control.ListView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.TilePane;
import javafx.stage.Stage;

import java.util.List;

public class DBApp extends Application
{
    private Database database;
    private Stage window;
    private Scene scene;

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

        setupOvelseListView();

        TilePane tileButtons = setupButtons();

        GridPane layout = setupLayout(tileButtons);

        // Setup Window
        window = primaryStage;
        window.setTitle("Treningsdagbok");
        scene = new Scene(layout, 500, 500);
        window.setScene(scene);
        window.show();
    }

    private GridPane setupLayout(TilePane tileButtons)
    {
        GridPane layout = new GridPane();
        layout.setHgap(10);
        layout.setVgap(10);
        layout.setPadding(new Insets(25, 25, 25, 25));
        layout.add(ovelseListView, 0, 0, 3, 1);
        layout.add(tileButtons, 0, 1, 3, 1);
        return layout;
    }

    private void setupOvelseListView()
    {
        List<Ovelse> ovelser = database.getOvelser();
        ovelseObservableList = FXCollections.observableArrayList(ovelser);
        ovelseListView = new ListView<>(ovelseObservableList);
        ovelseListView.setMaxHeight(Control.USE_PREF_SIZE);
    }

    private TilePane setupButtons()
    {
        leggTil = new Button("Legg til");
        endre = new Button("Endre");
        slett = new Button("Slett");
        leggTil.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        endre.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        slett.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        TilePane tileButtons = new TilePane(Orientation.HORIZONTAL);
        tileButtons.setPadding(new Insets(20, 10, 20, 10));
        tileButtons.setHgap(10.0);
        tileButtons.setVgap(10.0);
        tileButtons.getChildren().addAll(leggTil, endre, slett);
        return tileButtons;
    }

}
