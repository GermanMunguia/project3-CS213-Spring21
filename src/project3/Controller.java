package project3;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.time.format.DateTimeFormatter;

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

    //Declare Output Field
    @FXML
    private TextArea textOutput_Screen;

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
    @FXML
    private Button setHours_Button;

    //Declare Menu Button Items
    @FXML
    private MenuItem print_Button;
    @FXML
    private MenuItem printByDate_Button;
    @FXML
    private MenuItem printByDepartment_Button;

    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");

    Company company = new Company();

    public void addClick(ActionEvent actionEvent) {
        if(employeeIsValid()){
            String department = assignDepartment();
            if (Fulltime_Button.isSelected()) {
                if(!annualSalaryField.getText().equals("")) {
                    try {
                        if (Double.parseDouble(annualSalaryField.getText()) < 0) {
                            textOutput_Screen.setText("Salary cannot be negative.");
                        }
                        else{
                            Fulltime fulltime = new Fulltime(fullNameField.getText(), department,
                                    (dateHiredField.getValue()).format(formatter), annualSalaryField.getText());
                            if(company.add(fulltime)){
                                textOutput_Screen.setText("Employee added.");
                            } else {
                                textOutput_Screen.setText("Employee is already in the list.");
                            }
                        }
                    }
                    //If the annualSalaryField is not a numerical value
                    catch (NumberFormatException ex){
                        textOutput_Screen.setText("Salary must be a numerical value");
                    }
                }
                else{
                    textOutput_Screen.setText("Enter a valid salary");
                }
            }
            else if(Parttime_Button.isSelected()){

                String workHours = "";
                if(hoursWorkedField.getText().equals("")){
                    workHours = "0";
                }
                else if(isValidWorkingHours()){
                    workHours = hoursWorkedField.getText();
                }

                if(!hourlyRateField.getText().equals("") && !workHours.equals("")) {
                    try {
                        if (Double.parseDouble(hourlyRateField.getText()) < 0) {
                            textOutput_Screen.setText("Hourly Rate cannot be negative.");
                        }
                        else{
                            Parttime parttime = new Parttime(fullNameField.getText(), department,
                                    (dateHiredField.getValue()).format(formatter), hourlyRateField.getText(), workHours);
                            if(company.add(parttime)){
                                textOutput_Screen.setText("Employee added.");
                            } else {
                                textOutput_Screen.setText("Employee is already in the list.");
                            }
                        }
                    }
                    //If the annualSalaryField is not a numerical value
                    catch (NumberFormatException ex){
                        textOutput_Screen.setText("Hourly Rate must be a numerical value");
                    }
                }
                else{
                    textOutput_Screen.setText("Enter a valid salary");
                }
            }
            else if(Management_Button.isSelected()){
                if(!annualSalaryField.getText().equals("")) {
                    try {
                        if (Double.parseDouble(annualSalaryField.getText()) < 0) {
                            textOutput_Screen.setText("Salary cannot be negative.");
                        }
                        else{
                            Management management = new Management(fullNameField.getText(), department,
                                    (dateHiredField.getValue()).format(formatter), annualSalaryField.getText(), assignManagementRole());
                            if(company.add(management)){
                                textOutput_Screen.setText("Employee added.");
                            } else {
                                textOutput_Screen.setText("Employee is already in the list.");
                            }
                        }
                    }
                    //If the annualSalaryField is not a numerical value
                    catch (NumberFormatException ex){
                        textOutput_Screen.setText("Salary must be a numerical value");
                    }
                }
                else{
                    textOutput_Screen.setText("Enter a valid salary");
                }
            }
        }
        else{
            textOutput_Screen.setText("Try again");
        }
    }

    public void removeClick(ActionEvent actionEvent) {
        if(employeeIsValid()){
            String department = assignDepartment();
            Employee employee = new Employee(fullNameField.getText(), department,
                    (dateHiredField.getValue()).format(formatter));
            if(company.remove(employee)){
                textOutput_Screen.setText("Employee removed");
            }
            else{
                textOutput_Screen.setText("Employee does not exist.");
            }

        }
        else{
            textOutput_Screen.setText("Try again");
        }
    }

    public void calculatePaymentClick(ActionEvent actionEvent){
        if(company.getNumEmployee() == 0){
            textOutput_Screen.setText("Employee database is empty.");
        }
        else {
            company.processPayments();
            textOutput_Screen.setText("Calculation of employee payments is done.");
        }
    }

    public void setHoursClick(ActionEvent actionEvent) {
        if(employeeIsValid()){
            String department = assignDepartment();
            if(Parttime_Button.isSelected()){
                String workHours = "";
                if(hoursWorkedField.getText().equals("")){
                    textOutput_Screen.setText("Enter the amount of hours!");
                }
                else if(isValidWorkingHours()){
                    workHours = hoursWorkedField.getText();
                }

                if(company.setHours(new Parttime(fullNameField.getText(), department, (dateHiredField.getValue()).format(formatter), "0", workHours))) {
                    textOutput_Screen.setText("Working hours set.");
                }
                else{
                    textOutput_Screen.setText("Employee does not exist.");
                }

            }
            else{
                textOutput_Screen.setText("Invalid employee type");
            }

        }
        else{
            textOutput_Screen.setText("Try again");
        }
    }

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

    private String assignDepartment(){
        if(CS_Button.isSelected()){
            return "CS";
        }
        else if (ECE_Button.isSelected()){
            return "ECE";
        }
        else if (IT_Button.isSelected()) {
            return "IT";
        }
        return "";
    }

    private String assignManagementRole(){
        if(Manager_Button.isSelected()){
            return "1";
        }
        else if (DepartmentHead_Button.isSelected()){
            return "2";
        }
        else if (Director_Button.isSelected()) {
            return "3";
        }
        return "";
    }

    private Boolean employeeIsValid(){
        if(fullNameField.getText().equals("")){
            textOutput_Screen.setText("Enter the first and last name!");
            return false;
        }
        else if(dateHiredField.getValue() == null) {
            textOutput_Screen.setText("Select the date the employee was hired!");
            return false;
        }
        //If the date is out of bounds
        else if(!(new Date((dateHiredField.getValue()).format(formatter)).isValid())){
            textOutput_Screen.setText((dateHiredField.getValue()).format(formatter) + " is not a valid date!");
            return false;
        }
        else if(!(CS_Button.isSelected() || ECE_Button.isSelected() || IT_Button.isSelected())){
            textOutput_Screen.setText("Select a department!");
            return false;
        }
        else if(!(Fulltime_Button.isSelected() || Parttime_Button.isSelected() || Management_Button.isSelected())){
            textOutput_Screen.setText("Select employee type!");
            return false;
        }
        else if(Management_Button.isSelected() && !(Manager_Button.isSelected() || DepartmentHead_Button.isSelected() || Director_Button.isSelected())){
            textOutput_Screen.setText("Select Management role!");
            return false;
        }
        else{
            return true;
        }
    }

    private Boolean isValidWorkingHours(){
        try{
            if (Integer.parseInt(hoursWorkedField.getText()) < 0){
                textOutput_Screen.setText("Working hours cannot be negative.");
                return false;
            }
            else if (Integer.parseInt(hoursWorkedField.getText()) > Constants.MAXIMUM_HOURS){
                textOutput_Screen.setText("Invalid Hours: over 100.");
                return false;
            }
            return true;
        }
        catch (NumberFormatException ex){
            textOutput_Screen.setText("Hours worked must be a numerical value!");
            return false;
        }
    }
}
