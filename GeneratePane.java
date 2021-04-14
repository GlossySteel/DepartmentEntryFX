// Assignment 6: ASU - CSE 205
// Name: Joseph Kharzo
//Lecture Date and Time: TTH 1:30-2:45
//  Description: GeneratePane creats a pane where a user can enter
//  department information and create a list of available departments.

/* --------------- */
/* Import Packages */
/* --------------- */


import javafx.event.ActionEvent; //**Need to import
import javafx.event.EventHandler; //**Need to import

// JavaFX classes
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import static javafx.scene.paint.Color.RED;
import java.util.ArrayList;


/**
 * GeneratePane builds a pane where a user can enter a department
 * information and create a list of available departments.
 */
public class GeneratePane extends HBox {
    /* ------------------ */
    /* Instance variables */
    /* ------------------ */

    ArrayList<Department> departList;
    private SelectPane selectPane; // The relationship between GeneratePane and SelectPane is Aggregation
    private GridPane gridPane;
    private HBox departmentBox, facultyBox, universityBox;
    private Label departmentlbl, facultylbl, universitylbl, departmentStatuslbl;
    private TextField departmentField, facultyField, universityField;
    private Button addDepartmentButton;
    private TextArea departmentsViewArea;

    /**
     * CreatePane constructor
     *
     * @param list   the list of departments
     * @param sePane the SelectPane instance
     */
    public GeneratePane(ArrayList<Department> list, SelectPane sePane) {
        /* ------------------------------ */
        /* Instantiate instance variables */
        /* ------------------------------ */
        
        //initialize each instance variable (textfields, labels, textarea, button, etc.)
        //and set up the layout
	//create a GridPane to hold labels & text fields.
        
        departList = list;
        
        selectPane = sePane;
        
        gridPane = new GridPane();
        
        departmentStatuslbl = new Label();
        departmentlbl = new Label("Department Title");
        facultylbl = new Label("Number of faculty");
        universitylbl = new Label("Name of University");
        
        departmentField = new TextField();
        facultyField = new TextField();
        universityField = new TextField();
        
        departmentBox = new HBox();
        facultyBox = new HBox();
        universityBox = new HBox();
        
        departmentBox.getChildren().addAll(departmentlbl, departmentField);
        facultyBox.getChildren().addAll(facultylbl, facultyField);
        universityBox.getChildren().addAll(universitylbl, universityField);
        
        addDepartmentButton = new Button("Add a Department");
        
        departmentsViewArea = new TextArea("No Department");
        
        
        //Layout setup
        
        gridPane.setVgap(10);
                
        departmentlbl.setPadding(new Insets(0,0,0,10));
        facultylbl.setPadding(new Insets(0,0,0,10));
        universitylbl.setPadding(new Insets(0,0,0,10));

        departmentlbl.setMinWidth(120);
        facultylbl.setMinWidth(120);
        universitylbl.setMinWidth(120);

        departmentBox.setSpacing(10);
        facultyBox.setSpacing(10);
        universityBox.setSpacing(10);
        
        departmentStatuslbl.setTextFill(RED);
        departmentStatuslbl.setPadding(new Insets(10,0,0,0));

        GridPane.setHalignment(addDepartmentButton, HPos.CENTER);
                
	// Set both left and right columns to 50% width (half of window)
        ColumnConstraints halfWidth = new ColumnConstraints();
        halfWidth.setPercentWidth(50);
        gridPane.getColumnConstraints().addAll(halfWidth, halfWidth);

	//Set up the layout for the left half of the GeneratePane.
        gridPane.add(departmentStatuslbl, 0, 0);
        gridPane.add(departmentBox, 0, 1);
        gridPane.add(facultyBox, 0, 2);
        gridPane.add(universityBox, 0, 3);
        gridPane.add(addDepartmentButton, 0, 4);
        
	//the right half of the GeneratePane is simply a TextArea object
	//Note: a ScrollPane will be added to it automatically when there are no more space
	//Add the left half and right half to the GeneratePane

        this.getChildren().addAll(gridPane, departmentsViewArea);
                
	//Note: GeneratePane extends from HBox
	//register/link source object with event handler
        // Bind button click action to event handler
        
        addDepartmentButton.setOnAction(new ButtonHandler());
        

    } // end of constructor

    /**
     * ButtonHandler ButtonHandler listens to see if the button "Add a department" is pushed
     * or not, When the event occurs, it get a department's Title, number of faculties,
     * and its university information from the relevant text fields, then create a
     * new department and adds it to the departList. Meanwhile it will display the department's
     * information inside the text area. using the toString method of the Department
     * class. It also does error checking in case any of the text fields are empty,
     * or a non-numeric value was entered for number of faculty
     */
    private class ButtonHandler implements EventHandler<ActionEvent> {
        /**
         * handle Override the abstract method handle()
         */
        @Override
        public void handle(ActionEvent event) {
            // Declare local variables
            Department newDepart;
            int numberOfFaculty = 0;
            boolean isEmptyFields = false;
            boolean isDepartmentDuplicated = false;

            // If any field is empty, set isEmptyFields flag to true
            
            if(departmentField.getText().isEmpty()){
                isEmptyFields = true;
            }else if(facultyField.getText().isEmpty()){
                isEmptyFields = true;
            }else if(universityField.getText().isEmpty()){
                isEmptyFields = true;
            }

            // Display error message if there are empty fields
            if(isEmptyFields){
                try {
                    throw new Exception("Please fill all fields");
                } catch (Exception ex) {
                    departmentStatuslbl.setText(ex.getMessage());
                }
            }else{
            
                // If all fields are filled, try to add the department
                try {
                    /*
                     * Cast faculties Field to an integer, throws NumberFormatException if unsuccessful
                     */
                    numberOfFaculty = Integer.parseInt(facultyField.getText());

                    // Data is valid, so create new Department object and populate data
                    newDepart = new Department();

                    newDepart.setDeptName(departmentField.getText());
                    newDepart.setNumberOfMembers(numberOfFaculty);
                    newDepart.setUniversity(universityField.getText());

                    // Loop through existing departments to check for duplicates
                    // do not add it to the list if it exists and dispay a message

                    for(Department department: departList){
                        
                        if(department.getDeptName().equals(newDepart.getDeptName()) && department.getUniversity().equals(newDepart.getUniversity()) && department.getNumberOfMembers() == newDepart.getNumberOfMembers())
                            isDepartmentDuplicated = true;
                    }

                    if(isDepartmentDuplicated){
                        throw new Exception("Department not added - already exists");
                    }else{
                        // department is not a duplicate, so display it and add it to list
                        
                        if(departmentsViewArea.getText().equals("No Department"))
                            departmentsViewArea.clear();


                        departList.add(newDepart);

                        departmentsViewArea.appendText(newDepart.toString());

                        departmentStatuslbl.setText("Department added");

                        selectPane.updateDepartList(newDepart);

                        departmentField.clear();
                        facultyField.clear();
                        universityField.clear();
                    }
                        

                } //end of try
                catch (NumberFormatException e) {
                    // If the number of faculties entered was not an integer, display an error
                    departmentStatuslbl.setText("Please enter an integer for faculty.");

                } 
                catch (Exception e) {
                    // Catches generic exception and displays message
                    // Used to display 'Department is not added - already exist' message if department already exist
                    departmentStatuslbl.setText(e.getMessage());
                }
            }
           
        } // end of handle() method
    } // end of ButtonHandler class
} // end of GeneratePane class


