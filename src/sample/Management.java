package sample;

/**
 This class defines the abstract data type Management, which encapsulates the data fields and methods of a full time
 employee who has a management role.
 Contains getters and setters for the Management fields.
 The 3 possible management roles include manager, department head, and director. Each has a special bonus alongside
 their default annual salary.
 @author German Munguia, Sukhjit Singh
 */

public class Management extends Fulltime {
    private int role;
    private float singlePayPeriodCompensation;
    private float annualCompensation;

    /**
     Constructor used to generate a Management object using the given parameters.
     @param name of the employee.
     @param department the employee works in.
     @param dateHired the starting date of the employee.
     @param annualSalary of the employee
     @param role of the employee (Manager, Department Head, or Director)
     */
    public Management(String name, String department, String dateHired, String annualSalary, String role){
        super(name, department, dateHired, annualSalary);
        this.role = Integer.parseInt(role);
        annualCompensation = setCompensation();
        singlePayPeriodCompensation = 0;
    }

    /**
     Returns a String with the Management fields in a specified format.
     @return String with the employee's profile and fulltime data concatenated with the role and
     singlePayPeriodCompensation fields
     */
    @Override
    public String toString() {
        String result = "::" + managementRole() + " Compensation $" + String.format("%.2f", singlePayPeriodCompensation) ;
        return super.toString() + result;
    }

    /**
     Compares the given Employee with this Management employee based on its name, department, and dateHired.
     @param obj the given employee who's data members will be compared
     @return true if the name, department, dateHired, and class type of both employees are equal, false otherwise
     */
    @Override
    public boolean equals(Object obj) {
        //Check if super class is equal to the given object
        if(!super.equals(obj)) {
            return false;
        }
        //Check if the given object is a Management object
        else if (!(obj instanceof Management)) {
            return false;
        }
        return true;
    }


    @Override
    public float getAnnualSalary() {
        return super.getAnnualSalary();
    }

    /**
     Method to calculate the compensation for one pay period of this employee based on the annual compensation.
     The annual compensation is divided by 26 to calculate the biweekly payment for this employee and is assigned to the
     singlePayPeriodCompensation floating point variable.
     The singlePayPeriodCompensation is added to the singlePayPeriodSalary in the super class to account for this
     increased payment.
     */
    @Override
    public void calculatePayment() {
        super.calculatePayment();

        singlePayPeriodCompensation = annualCompensation/Constants.TOTAL_PAY_PERIODS;
        super.addBonus(singlePayPeriodCompensation);
    }

    /**
     Helper method which returns the String name representation of the employee's role in the company.
     If the role number is 1 the employee is a manager, if 2 the employee is a Department Head, if 3 the employee is
     a director.
     @return String representing the employee's role in the company
     */
    private String managementRole(){
        if(role == Constants.MANAGER){
            return "Manager";
        }
        else if(role == Constants.DEPARTMENT_HEAD){
            return "Department Head";
        }
        else if(role == Constants.DIRECTOR){
            return "Director";
        }
        return "";
    }

    /**
     Helper method which returns the floating point value of the employee's annual compensation based on their role in
     the company.
     Calls the managementRole() method to determine the employee's management role.
     If the employee is a manager their compensation is $5000, if the employee is a Department Head their
     compensation is $9500, if the employee is a director their compensation is $12000.
     @return float representing the employee's annual compensation
     */
    private float setCompensation(){
        if (managementRole().equals("Manager")){
            return Constants.MANAGER_COMPENSATION;
        }
        else if (managementRole().equals("Department Head")){
            return Constants.DEPARTMENT_HEAD_COMPENSATION;
        }
        else if (managementRole().equals("Director")){
            return Constants.DIRECTOR_COMPENSATION;
        }
        return 0;

    }

    public float getCompensationSum() {
        return singlePayPeriodCompensation + super.getAnnualSalary()/Constants.TOTAL_PAY_PERIODS;
    }
}
