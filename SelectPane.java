// Assignment 6: ASU - CSE 205
// Name: Joseph Kharzo
//Lecture Date and Time: TTH 1:30-2:45
//  Description: SelectPane displays a list of available department
//  from which a user can select and compute total number of faculties in multiple departments.


import javafx.event.ActionEvent; //**Need to import
import javafx.event.EventHandler; //**Need to import

// import all other necessary JavaFX classes here
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.geometry.Insets;
import java.util.ArrayList;


/**
* SelectPane displays a list of available departments from which a user
* can select and compute total number of faculties for selected departments.
*/
public class SelectPane extends BorderPane {

    //declare instance varibales
    private ArrayList<Department> departList;
    
    private VBox vbox;
    private VBox scrollPaneVBox;
    private HBox facultyHBox;
    private ScrollPane scrollPane;
    private Label selectDepartmentHeader;
    private Label facultyNumberDescription;
    private Label numberOfFaculty;
    private CheckBox checkBox;



    public SelectPane(ArrayList<Department> list) {
        /* ------------------------------ */
        /* Instantiate instance variables */
        /* ------------------------------ */

        this.departList = list;
        
        vbox = new VBox();
        
        scrollPane = new ScrollPane();
        
        selectDepartmentHeader = new Label("Select department(s)");
        
        facultyNumberDescription = new Label("The total number of faculty selected:");
        
        numberOfFaculty = new Label("0");
                
        facultyHBox = new HBox();
        
        // Wrap checkboxContainer in ScrollPane so formatting is
        // correct when many departments are added
        // Setup layout
        selectDepartmentHeader.setPadding(new Insets(0,0,0,5));
        
        facultyNumberDescription.setPadding(new Insets(0,0,0,5));
        
        facultyHBox.getChildren().addAll(facultyNumberDescription, numberOfFaculty);

        facultyHBox.setSpacing(10);
        
        vbox.getChildren().addAll(selectDepartmentHeader, scrollPane, facultyHBox);

        //create an empty pane where you can add check boxes later
        scrollPaneVBox = new VBox();

        //SelectPane is a BorderPane - add the components here
        this.setCenter(vbox);
        
    } // end of SelectPane constructor

    // This method uses the newly added parameter Department object
    // to create a CheckBox and add it to a pane created in the constructor
    // Such check box needs to be linked to its handler class
    public void updateDepartList(Department newdep) {
        
        // Create checkbox for new department with appropriate text
        checkBox = new CheckBox(newdep.toString());
        
        // Bind checkbox toggle action to event handler
        // Passes the number of faculties in each department to the handler. When the checkbox is
        // toggled,this number will be added/subtracted from the total number of selected faculties

        checkBox.setOnAction(new SelectionHandler(newdep.getNumberOfMembers()));

        // Add new checkbox to checkbox container
        
        scrollPaneVBox.getChildren().add(checkBox);
        
        scrollPane.setContent(scrollPaneVBox);

    } // end of updateDepartList method

    /**
     * SelectionHandler This class handles a checkbox toggle event. When the
     * checkbox is toggled, this number will be added/subtracted from the total
     * number of selected faculties.
     */
    private class SelectionHandler implements EventHandler<ActionEvent> {
        // Instance variable for number of faculties associated with a given department/checkbox
        private int faculties;
        private String faculty;
        private int currentFacultyMembers;


        public SelectionHandler(int members) {
            this.faculties = members; // Set instance variable
            faculty = "";
            currentFacultyMembers = 0;
            
        } // end of SelectionHandler constructor

        //over-ride the abstarct method handle
        @Override
        public void handle(ActionEvent event) {
            
            // Get the object that triggered the event, and cast it to a checkbox, since
            // only a department checkbox
            // can trigger the SelectionHandler event. The cast is necessary to have access
            // to the isSelected() method
            
            CheckBox box = (CheckBox)event.getSource();
           
            // Update the selected Faculties label with the new number of selected faculties
            
            faculty = numberOfFaculty.getText();
            currentFacultyMembers = Integer.parseInt(faculty);
            
            
            if(box.isSelected()){
                currentFacultyMembers += faculties;
                numberOfFaculty.setText(currentFacultyMembers + "");
            }else{
                currentFacultyMembers -= faculties;
                numberOfFaculty.setText(currentFacultyMembers + "");
            }
            
        } // end handle method
    } // end of SelectHandler class
} // end of SelectPane class
