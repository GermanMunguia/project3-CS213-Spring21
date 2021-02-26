package project3;

import javafx.fxml.FXML;
import javafx.scene.control.*;

public class Controller {
    //Declare the input fields
    @FXML
    private TextField fullNameField;
    @FXML
    private DatePicker dateHiredField;
    @FXML
    private TextField annualSalaryField;
    @FXML
    private TextField hourlyRateField;
    @FXML
    private TextField hoursWorkedField;

    //Declare the radio buttons
    @FXML
    private RadioButton CS_Button;
    @FXML
    private RadioButton ECE_Button;
    @FXML
    private RadioButton IT_Button;
    @FXML
    private RadioButton Fulltime_Button;
    @FXML
    private RadioButton Parttime_Button;
    @FXML
    private RadioButton Management_Button;
    @FXML
    private RadioButton Manager_Button;
    @FXML
    private RadioButton DepartmentHead_Button;
    @FXML
    private RadioButton Director_Button;

    //Declare Regular Buttons
    @FXML
    private Button add_Button;
    @FXML
    private Button remove_Button;
    @FXML
    private Button calculatePayment_Button;

    //Declare Menu Button Items
    @FXML
    private MenuItem print_Button;
    @FXML
    private MenuItem printByDate_Button;
    @FXML
    private MenuItem printByDepartment_Button;




}
