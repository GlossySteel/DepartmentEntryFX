// Assignment 6: ASU - CSE 205
// Name: Joseph Kharzo
//Lecture Date and Time: TTH 1:30-2:45
//  Description: The Assignment6 class creates a Tabbed Pane with
//               two tabs, one for Department Creation and one for
//               Department Selection.

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.StackPane;
import java.util.ArrayList;

public class Assignment6 extends Application {
    private TabPane tabPane;
    private GeneratePane createPane;
    private SelectPane selectPane;
    private ArrayList<Department> departList;

    @Override
    public void start(Stage stage) {
        StackPane root = new StackPane();

        // departList to be used in both generatePane & selectPane
        departList = new ArrayList<Department>();

        selectPane = new SelectPane(departList);
        createPane = new GeneratePane(departList, selectPane);

        tabPane = new TabPane();

        Tab tab1 = new Tab();
        tab1.setText("Add Department");
        tab1.setContent(createPane);

        Tab tab2 = new Tab();
        tab2.setText("Select Department");
        tab2.setContent(selectPane);

        tabPane.getSelectionModel().select(0);
        tabPane.getTabs().addAll(tab1, tab2);

        root.getChildren().add(tabPane);

        Scene scene = new Scene(root, 900, 400);
        stage.setTitle("Department Selection App");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
