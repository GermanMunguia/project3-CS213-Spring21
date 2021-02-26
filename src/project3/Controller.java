package project3;

import javafx.event.ActionEvent;
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



    public void fulltimeClick(ActionEvent actionEvent) {
        disableManagementRoles();
        disablePartime();
    }

    public void managementClick(ActionEvent actionEvent) {
            Manager_Button.setDisable(false);
            DepartmentHead_Button.setDisable(false);
            Director_Button.setDisable(false);
            disablePartime();
    }

    public void ParttimeClick(ActionEvent actionEvent) {
        disableManagementRoles();
        hourlyRateField.setDisable(false);
        annualSalaryField.setDisable(true);
        hoursWorkedField.setDisable(false);
    }

    public void disableManagementRoles(){
        Manager_Button.setDisable(true);
        DepartmentHead_Button.setDisable(true);
        Director_Button.setDisable(true);
    }

    public void disablePartime() {
        hourlyRateField.setDisable(true);
        annualSalaryField.setDisable(false);
        hoursWorkedField.setDisable(true);

    }

}
