package project3;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.Window;

import javax.swing.*;
import java.io.*;
import java.time.format.DateTimeFormatter;
import java.util.StringTokenizer;

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

    @FXML
    private HBox Import_Export;

    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");

    Company company = new Company();

    public void addClick(ActionEvent actionEvent) {
        if(employeeIsValid()){
            String department = assignDepartment();
            if (Fulltime_Button.isSelected()) {
                if(!annualSalaryField.getText().equals("")) {
                    try {
                        if (Double.parseDouble(annualSalaryField.getText()) < 0) {
                            textOutput_Screen.appendText("Salary cannot be negative.\n");
                        }
                        else{
                            Fulltime fulltime = new Fulltime(fullNameField.getText(), department,
                                    (dateHiredField.getValue()).format(formatter), annualSalaryField.getText());
                            if(company.add(fulltime)){
                                textOutput_Screen.appendText("Employee added.\n");
                            } else {
                                textOutput_Screen.appendText("Employee is already in the list.\n");
                            }
                        }
                    }
                    //If the annualSalaryField is not a numerical value
                    catch (NumberFormatException ex){
                        textOutput_Screen.appendText("Salary must be a numerical value\n");
                    }
                }
                else{
                    textOutput_Screen.appendText("Enter a valid salary\n");
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
                            textOutput_Screen.appendText("Hourly Rate cannot be negative.\n");
                        }
                        else{
                            Parttime parttime = new Parttime(fullNameField.getText(), department,
                                    (dateHiredField.getValue()).format(formatter), hourlyRateField.getText(), workHours);
                            if(company.add(parttime)){
                                textOutput_Screen.appendText("Employee added.\n");
                            } else {
                                textOutput_Screen.appendText("Employee is already in the list.\n");
                            }
                        }
                    }
                    //If the annualSalaryField is not a numerical value
                    catch (NumberFormatException ex){
                        textOutput_Screen.appendText("Hourly Rate must be a numerical value\n");
                    }
                }
                else{
                    textOutput_Screen.appendText("Enter a valid salary\n");
                }
            }
            else if(Management_Button.isSelected()){
                if(!annualSalaryField.getText().equals("")) {
                    try {
                        if (Double.parseDouble(annualSalaryField.getText()) < 0) {
                            textOutput_Screen.appendText("Salary cannot be negative.\n");
                        }
                        else{
                            Management management = new Management(fullNameField.getText(), department,
                                    (dateHiredField.getValue()).format(formatter), annualSalaryField.getText(), assignManagementRole());
                            if(company.add(management)){
                                textOutput_Screen.appendText("Employee added.\n");
                            } else {
                                textOutput_Screen.appendText("Employee is already in the list.\n");
                            }
                        }
                    }
                    //If the annualSalaryField is not a numerical value
                    catch (NumberFormatException ex){
                        textOutput_Screen.appendText("Salary must be a numerical value\n");
                    }
                }
                else{
                    textOutput_Screen.appendText("Enter a valid salary\n");
                }
            }
        }
        else{
            textOutput_Screen.appendText("Try again\n");
        }
    }

    public void removeClick(ActionEvent actionEvent) {
        if(employeeIsValid()){
            String department = assignDepartment();
            Employee employee = new Employee(fullNameField.getText(), department,
                    (dateHiredField.getValue()).format(formatter));
            if(company.remove(employee)){
                textOutput_Screen.appendText("Employee removed\n");
            }
            else{
                textOutput_Screen.appendText("Employee does not exist.\n");
            }

        }
        else{
            textOutput_Screen.appendText("Try again\n");
        }
    }

    public void calculatePaymentClick(ActionEvent actionEvent){
        if(company.getNumEmployee() == 0){
            textOutput_Screen.appendText("Employee database is empty.\n");
        }
        else {
            company.processPayments();
            textOutput_Screen.appendText("Calculation of employee payments is done.\n");
        }
    }

    public void setHoursClick(ActionEvent actionEvent) {
        if(employeeIsValid()){
            String department = assignDepartment();
            if(Parttime_Button.isSelected()){
                String workHours = "";
                if(hoursWorkedField.getText().equals("")){
                    textOutput_Screen.appendText("Enter the amount of hours!\n");
                }
                else if(isValidWorkingHours()){
                    workHours = hoursWorkedField.getText();
                }

                if(company.setHours(new Parttime(fullNameField.getText(), department, (dateHiredField.getValue()).format(formatter), "0", workHours))) {
                    textOutput_Screen.appendText("Working hours set.\n");
                }
                else{
                    textOutput_Screen.appendText("Employee does not exist.\n");
                }

            }
            else{
                textOutput_Screen.appendText("Invalid employee type\n");
            }

        }
        else{
            textOutput_Screen.appendText("Try again\n");
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
            textOutput_Screen.appendText("Enter the first and last name!\n");
            return false;
        }
        else if(dateHiredField.getValue() == null) {
            textOutput_Screen.appendText("Select the date the employee was hired!\n");
            return false;
        }
        //If the date is out of bounds
        else if(!(new Date((dateHiredField.getValue()).format(formatter)).isValid())){
            textOutput_Screen.appendText((dateHiredField.getValue()).format(formatter) + " is not a valid date!\n");
            return false;
        }
        else if(!(CS_Button.isSelected() || ECE_Button.isSelected() || IT_Button.isSelected())){
            textOutput_Screen.appendText("Select a department!\n");
            return false;
        }
        else if(!(Fulltime_Button.isSelected() || Parttime_Button.isSelected() || Management_Button.isSelected())){
            textOutput_Screen.appendText("Select employee type!\n");
            return false;
        }
        else if(Management_Button.isSelected() && !(Manager_Button.isSelected() || DepartmentHead_Button.isSelected() || Director_Button.isSelected())){
            textOutput_Screen.appendText("Select Management role!\n");
            return false;
        }
        else{
            return true;
        }
    }

    private Boolean isValidWorkingHours(){
        try{
            if (Integer.parseInt(hoursWorkedField.getText()) < 0){
                textOutput_Screen.appendText("Working hours cannot be negative.\n");
                return false;
            }
            else if (Integer.parseInt(hoursWorkedField.getText()) > Constants.MAXIMUM_HOURS){
                textOutput_Screen.appendText("Invalid Hours: over 100.\n");
                return false;
            }
            return true;
        }
        catch (NumberFormatException ex){
            textOutput_Screen.appendText("Hours worked must be a numerical value!\n");
            return false;
        }
    }

    public void print(String printMsg) {
        if(company.getNumEmployee() == 0) {
            textOutput_Screen.appendText("Employee database is empty.\n");
            return;
        }

        textOutput_Screen.appendText(printMsg);
        String[] employees = company.employeeList();
        for(int i = 0; i < employees.length; i++) {
            textOutput_Screen.appendText(employees[i] + "\n");
        }
    }

    public void printClick(ActionEvent actionEvent) {
        String printMsg = "--Printing earning statements for all employees--\n";
        print(printMsg);
    }

    public void printByDateClick(ActionEvent actionEvent) {
        String printMsg = "--Printing earning statements by date hired--\n";
        company.sortEmployeeHiredDateAscending();
        print(printMsg);
    }

    public void printByDepartmentClick(ActionEvent actionEvent) {
        String printMsg = "--Printing earning statements by department--\n";
        company.sortEmployeeDepartment();
        print(printMsg);
    }

    public void importClick(ActionEvent actionEvent) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Choose Import File");
        File selectedFile = fileChooser.showOpenDialog(null);

        //If the selection was cancelled or nothing was chosen then return.
        if(selectedFile == null) {
            textOutput_Screen.appendText("No file was imported.\n");
            return;
        }

        //file has been imported, assume date is correct and add the employees.
        textOutput_Screen.appendText("File Imported.\n");
        try{
            FileReader fr = new FileReader(selectedFile.getPath());
            BufferedReader br = new BufferedReader(fr);

            String input;
            //loop until end of file.
            while((input = br.readLine()) != null) {

                //create string tokenizer with the input and use comma as a delimiter.
                StringTokenizer in = new StringTokenizer(input, ",");
                //the first token will contain the employee type.
                String firstToken = in.nextToken();

                //add Part time employee
                if(firstToken.equals("P")) {
                    Employee employee = new Parttime(in.nextToken(), in.nextToken(), in.nextToken(), in.nextToken(), "0");
                    company.add(employee);
                }

                //add full time employee
                else if(firstToken.equals("F")) {
                    Employee employee = new Fulltime(in.nextToken(), in.nextToken(), in.nextToken(), in.nextToken());
                    company.add(employee);
                }

                //add management employee
                else if(firstToken.equals("M")) {
                    Employee employee = new Management(in.nextToken(), in.nextToken(), in.nextToken(), in.nextToken(), in.nextToken());
                    company.add(employee);
                }
            }
            fr.close(); //close the file

        }
        catch(Exception ex){
            return;
        }
    }

    public void exportClick(ActionEvent actionEvent) {

        //allow for the location of the exported file be chosen.
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Export File");
        File selectedFile = fileChooser.showSaveDialog(null);

        try{
            //use printwriter to write the toString values of each employee.
            PrintWriter pw = new PrintWriter(selectedFile);
            String[] employees = company.employeeList();
            for(int i = 0; i < employees.length; i++) {
                pw.println(employees[i].toString());
            }
            pw.close(); //close the file
        }
        catch(Exception ex){
            textOutput_Screen.appendText("The file was not exported.\n");
            return;
        }

        //Show the file was exported successfully.
        textOutput_Screen.appendText("File Exported.\n");
    }

}
