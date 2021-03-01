package project3;

/**
 The Controller class defines the methods associated with the View.fxml GUI file.
 The methods define the actions performed the event of clicking a button in the GUI application.
 @author German Munguia, Sukhjit Singh
 */

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.FileChooser;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.PrintWriter;
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

    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");


    Company company = new Company();

    /**
     Checks and validates the data input and selection fields in the GUI application and based on that, adds an employee
     to the employee array.
     Calls the employeeIsValid() method to check if the data input fields are populated and contain valid data.
     Based on the employee type selected, an associated employee class (Fulltime, Parttime, or Management) instance is
     created with the data field values and added to the employee array.
     If employee is successfully added a success message is displayed, otherwise a Try Again message is displayed.
     @param actionEvent associated with the clicking of the add button
     */
    public void addClick(ActionEvent actionEvent) {
        if(employeeIsValid("add")){
            //String containing the selected employee department.
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

    /**
     Checks and validates the data input and selection fields in the GUI application and based on that, removes an
     employee from the employee array.
     Calls the employeeIsValid() method to check if the name, date, and department input fields are populated and
     contain valid data.
     A generic employee class instance is created with the data field values and passed as a parameter in the remove()
     method of the company class.
     If employee is successfully removed a success message is displayed, otherwise a Try Again message is displayed.
     @param actionEvent associated with the clicking of the remove button
     */
    public void removeClick(ActionEvent actionEvent) {
        if(employeeIsValid("remove")){
            //String containing the selected employee department.
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

    /**
     Calls the processPayments() method in the company class to calculate the single payment period salaries for each
     employee.
     If there are no employees in the employee array, a message saying that the database is empty is displayed.
     Otherwise a success message is displayed.
     @param actionEvent associated with the clicking of the calculate payment button
     */
    public void calculatePaymentClick(ActionEvent actionEvent){
        if(company.getNumEmployee() == 0){
            textOutput_Screen.appendText("Employee database is empty.\n");
        }
        else {
            company.processPayments();
            textOutput_Screen.appendText("Calculation of employee payments is done.\n");
        }
    }

    /**
     Checks and validates the data input and selection fields in the GUI application and based on that, sets the
     hours of the given employee in the employee array if they are found.
     Calls the employeeIsValid() method to check if the name, date, and department input fields are populated and
     contain valid data.
     A generic Parttime employee instance is created with the data field values and passed as a parameter in the
     setHours() method of the company class.
     If employee is successfully found and their working hours are set, a success message is displayed, otherwise a
     Try Again message is displayed.
     @param actionEvent associated with the clicking of the set hours button
     */
    public void setHoursClick(ActionEvent actionEvent) {
        if(employeeIsValid("setHours")){
            //String containing the selected employee department.
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
                //If any other employee type is selected
                textOutput_Screen.appendText("Invalid employee type\n");
            }
        }
        else{
            //If the data field values are invalid.
            textOutput_Screen.appendText("Try again\n");
        }
    }

    /**
     If the fulltime employee radio button is selected, the other employee type radio buttons become disabled.
     Calls the disableManagementRoles() method to disable the management role radio buttons.
     Calls the disablePartime() method to disable the parttime employee data fields.
     @param actionEvent associated with the clicking of the fulltime button
     */
    public void fulltimeClick(ActionEvent actionEvent) {
        disableManagementRoles();
        disablePartime();
    }

    /**
     If the management employee radio button is selected, the other employee type radio buttons become disabled and
     the associated management subtype radio buttons are enabled.
     Calls the disablePartime() method to disable the parttime employee data fields.
     @param actionEvent associated with the clicking of the management button
     */
    public void managementClick(ActionEvent actionEvent) {
        Manager_Button.setDisable(false);
        DepartmentHead_Button.setDisable(false);
        Director_Button.setDisable(false);
        disablePartime();
    }

    /**
     If the parttime employee radio button is selected, the other employee type radio buttons and data fields become
     disabled while the parttime salary and hours worked fields become enabled.
     Calls the disableManagementRoles() method to disable the management role radio buttons/
     @param actionEvent associated with the clicking of the parttime button
     */
    public void ParttimeClick(ActionEvent actionEvent) {
        disableManagementRoles();
        hourlyRateField.setDisable(false);
        annualSalaryField.setDisable(true);
        hoursWorkedField.setDisable(false);
    }

    /**
     Method to disable the management role radio buttons
     */
    public void disableManagementRoles(){
        Manager_Button.setDisable(true);
        DepartmentHead_Button.setDisable(true);
        Director_Button.setDisable(true);
    }

    /**
     Method to disable the parttime employee data fields and enable the fulltime employee data fields.
     */
    public void disablePartime() {
        hourlyRateField.setDisable(true);
        annualSalaryField.setDisable(false);
        hoursWorkedField.setDisable(true);
    }

    /**
     Helper method which returns a string representing the department an employee works in based on the department
     radio button selection.
     @return String value representing the employee's selected department
     */
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

    /**
     Helper method which returns a string representing the management role of a management employee based on the
     radio button selection.
     @return String value representing the employee's selected management role
     */
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

    /**
     Helper method which returns a Boolean representing the validity of the data given in the GUI input fields.
     @param command associated with the type of action button a user has selected (Add, remove, set hours)
     @return Boolean which returns true if employee data fields are valid, false otherwise
     */
    private Boolean employeeIsValid(String command){
        //Check if the name field is populated
        if(fullNameField.getText().equals("")){
            textOutput_Screen.appendText("Enter the first and last name!\n");
            return false;
        }
        //Check if a date is selected
        else if(dateHiredField.getValue() == null) {
            textOutput_Screen.appendText("Select the date the employee was hired!\n");
            return false;
        }
        //Check if the selected date is out of bounds
        else if(!(new Date((dateHiredField.getValue()).format(formatter)).isValid())){
            textOutput_Screen.appendText((dateHiredField.getValue()).format(formatter) + " is not a valid date!\n");
            return false;
        }
        //Check if the department radio buttons are selected (Only when adding an employee)
        else if(!(CS_Button.isSelected() || ECE_Button.isSelected() || IT_Button.isSelected()) && command.equals("add")){
            textOutput_Screen.appendText("Select a department!\n");
            return false;
        }
        //Check if the employee type radio buttons are selected (Only when adding an employee)
        else if(!(Fulltime_Button.isSelected() || Parttime_Button.isSelected() || Management_Button.isSelected()) && command.equals("add")){
            textOutput_Screen.appendText("Select employee type!\n");
            return false;
        }
        //Check is the management role radio buttons are selected if management is selected (Only when adding an employee)
        else if(Management_Button.isSelected() && !(Manager_Button.isSelected() || DepartmentHead_Button.isSelected() || Director_Button.isSelected()) && command.equals("add")){
            textOutput_Screen.appendText("Select Management role!\n");
            return false;
        }
        //Everything is valid
        else{
            return true;
        }
    }

    /**
     Helper method which returns a Boolean representing the validity of the parttime employee working hours given in
     the GUI input fields.
     @return Boolean which returns true if parttime employee working hours are valid, false otherwise
     */
    private Boolean isValidWorkingHours(){
        try{
            //Check if working hours are negative
            if (Integer.parseInt(hoursWorkedField.getText()) < 0){
                textOutput_Screen.appendText("Working hours cannot be negative.\n");
                return false;
            }
            //Check if working hours exceeds maximum of 100 hours
            else if (Integer.parseInt(hoursWorkedField.getText()) > Constants.MAXIMUM_HOURS){
                textOutput_Screen.appendText("Invalid Hours: over 100.\n");
                return false;
            }
            return true;
        }
        //Exception if the input is not a numerical value
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
        String[] employees = company.exportDatabase();
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
            String[] employees = company.exportDatabase();
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
