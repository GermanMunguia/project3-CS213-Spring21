package project3;

/**
 This class defines the abstract data type Employee, which encapsulates the data fields and methods of an employee.
 Contains getters and setters for the employee fields.
 @author German Munguia, Sukhjit Singh
 */
public class Employee {
    private Profile profile;

    /**
     Constructor used to generate an Employee object containing a Profile object created using the given parameters.
     @param name of the employee.
     @param department the employee works in.
     @param dateHired the starting date of the employee.
     */
    Employee(String name, String department, String dateHired){
        this.profile = new Profile(name,department,dateHired);
    }

    /**
     Returns a String with the employee's Profile fields in a specified format.
     Calls the toString() method in the Profile class.
     @return String returned from the employee's profile.
     */
    @Override
    public String toString() {
        return profile.toString();
    }

    /**
     Compares the given Employee object with the current Employee object based on its Profile data members.
     @param obj the given employee who's Profile will be compared
     @return true if the serial numbers of both employees are equal, false otherwise
     */
    @Override
    public boolean equals(Object obj) {
        Employee employee = (Employee) obj;
        if (profile.equals(employee.profile)){
            return true;
        }
        return false;
    }

    /**
     Skeleton class which is overridden in the subclasses.
     */
    public void calculatePayment() {
    }

    /**
     Returns a Date object containing the date the employee was hired.
     Calls the getDateHired() method in the Profile class to obtain the date the employee was hired.
     @return profile.getDateHired() Date object obtained from the employee's profile.
     */
    public Date getDateHired(){
        return profile.getDateHired();
    }

    /**
     Returns a String containing the department the employee works in.
     Calls the getDepartment() method in the Profile class to obtain the department the employee works in.
     @return profile.getDepartment() String obtained from the employee's profile.
     */
    public String getDepartment(){
        return profile.getDepartment();
    }

}
