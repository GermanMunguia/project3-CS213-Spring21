package project3;

/**
 The Company class defines the abstract Company type which contains the array consisting of Employee class objects.
 Contains constructors to generate Company objects with an empty array to hold Employee class objects.
 The class allows for Employee objects to be removed, added, and the data members of employees to be changed, the
 earnings of employees to be computed, and can print the earnings of employees based on their current order in the
 array, by date hired, and by the employee's department.
 The employee array length grows with the addition of employees.

 @author German Munguia, Sukhjit Singh
 */

public class Company {
    private Employee[] emplist;
    private int numEmployee;

    /**
     Constructor used to generate a Company object with an array of employees of capacity 4.
     The numEmployee data member initially set to zero.
     */
    public Company() {
        emplist = new Employee[Constants.STARTING_ARRAY_SIZE];
        numEmployee = 0;
    }

    /**
     Finds the index of the given employee within the employee array.
     If the given employee is not in the array then return a negative one.
     @param employee The employee object which is being searched for
     @return the index location of the given employee object, -1 if the employee was not found in the array
     */
    private int find(Employee employee) {
        //Iterate through the employee list and compare the employees until they match.
        for(int i = 0; i < numEmployee; i++) {
            if(employee.equals(emplist[i])){
                return i;
            }
        }

        //if its not found, return -1.
        return Constants.EMPLOYEE_NOT_FOUND;
    }

    /**
     Increases the size of the employee array by four indexes.
     The employee objects are copied into a new array and the employee array gets reassigned.
     */
    private void grow() {
        Employee[] newArray = new Employee[emplist.length + Constants.INCREASE_ARRAY_BY];
        //copy the current employees.
        for(int i = 0; i < numEmployee; i++) {
            newArray[i] = emplist[i];
        }
        //reassign emplist so that it refers to newArray.
        emplist = newArray;
    }

    /**
     Add the given employee into the employee array.
     Use the find() method to check if the employee is inside the employee array.
     If the employee array is full, then increase the size with the grow() method.
     @param employee the book which is being added to the bag
     @return true if the employee was added, false otherwise
     */
    public boolean add(Employee employee) {
        //Check profile and see if the given employee already exists in the system
        if(find(employee) != Constants.EMPLOYEE_NOT_FOUND){
            //If a match is found, the given employee cannot be added to the system
            return false;
        }
        else {
            //Grow the array if no empty index remains
            if (emplist[emplist.length - 1] != null) {
                grow();
            }
            //Add new employee to the first empty index available
            for (int i = 0; i < emplist.length; i++) {
                if (emplist[i] == null) {
                    emplist[i] = employee;
                    break;
                }
            }
            numEmployee++;
            return true;
        }
    } //check the profile before adding

    /**
     Remove the given employee from the employee array.
     Use the find() method to check if the employee is inside the employee array.
     @param employee The employee that is being removed from the bag
     @return true if the employee was removed, false otherwise
     */
    public boolean remove(Employee employee) {
        //find the employee the helper method
        int employeeIndex = find(employee);

        //if the index equals -1 than the employee is not in the system and cannot be removed.
        if(employeeIndex == Constants.EMPLOYEE_NOT_FOUND) {
            return false;
        }
        //set the employee at the given index as null to remove it.
        emplist[employeeIndex] = null;
        //shift the elements to the right of it by one.
        for(int i = employeeIndex+1; i < emplist.length; i++) {
            //will trail i by one that way the previous index can be replaced. Since i can never be zero there is no out of bound issue.
            int previousIndex = i - 1;
            emplist[previousIndex] = emplist[i]; //make the swap of elements
        }
        //reduce the count by one as an employee was successfully just removed.
        numEmployee--;
        return true;
    } //maintain the original sequence

    /**
     Set the working hours of the given part time employee from the employee array.
     Use the find() method to check if the employee is inside the employee array.
     @param employee The employee that is being removed from the bag
     @return true if the employee's working hours were set', false otherwise
     */
    public boolean setHours(Employee employee) {
        //find the employee index with the helper method
        int employeeIndex = find(employee);

        //if the index equals -1 than the employee is not in the system and the working hours cannot be set.
        if(employeeIndex == Constants.EMPLOYEE_NOT_FOUND){
            return false;
        }
        else {
            Parttime parttime = (Parttime) emplist[employeeIndex];
            //Extract the working hours from the given employee and set them for the employee found in the array.

            if(((Parttime) employee).getWorkingHours() > Constants.MAXIMUM_HOURS || ((Parttime) employee).getWorkingHours() < 0) {
                return false;
            }

            parttime.setWorkingHours(((Parttime) employee).getWorkingHours());
            return true;
        }
    } //set working hours for a part time

    /**
     Calculate the payment for each employee from the employee array for a single pay period.
     This method calls the calculatePayment() method for each employee.
     */
    public void processPayments() {
        for(int i = 0; i < numEmployee; i++) {
            emplist[i].calculatePayment();
        }

    } //process payments for all employees

    /**
     Returns a new array with the toString() info of each employee in the database to be exported.
     @return employees the new array with info of all employees.
     */
    public String[] exportDatabase() {

        if(numEmployee == 0) {
            return null;
        }

        String[] employees = new String[numEmployee];

        for(int i = 0; i < numEmployee; i++) {
            employees[i] = emplist[i].toString();
        }
        return employees;
    }

    /**
     Sort the employee array from lexicographically based on the employee department name (CS, ECE, IT).
     */
    public void sortEmployeeDepartment(){
        for (int i = 1; i < emplist.length; i++) {
            Employee[] tempList = new Employee[1];
            tempList[0] = emplist[i];
            int j = i - 1;
            while(j >= 0 && emplist[j] != null && tempList[0] != null &&
                    emplist[j].getDepartment().compareTo(tempList[0].getDepartment()) > 0) {
                emplist[j + 1] = emplist[j];
                j = j - 1;
            }
            emplist[j + 1] = tempList[0];
        }
    }

    /**
     Sort the employee array based on the starting date of each employee from oldest to most recent.
     */
    public void sortEmployeeHiredDateAscending(){
        for (int i = 1; i < emplist.length; i++) {
            Employee[] tempList = new Employee[1];
            tempList[0] = emplist[i];
            int j = i - 1;
            while(j >= 0 && emplist[j] != null && tempList[0] != null && emplist[j].getDateHired().compareTo(tempList[0].getDateHired())
                    == Constants.FIRST_DATE_LESS_THAN_SECOND) {
                emplist[j + 1] = emplist[j];
                j = j - 1;
            }
            emplist[j + 1] = tempList[0];
        }
    }

    /**
     Method to return the number of employees in the employee array.
     @return numEmployee which stores the number of employees in the employee array.
     */
    public int getNumEmployee() {
        return numEmployee;
    }

}
