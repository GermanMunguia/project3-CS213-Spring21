package sample;

/**
 This class defines the abstract data type Fulltime, which encapsulates the data fields and methods of a full time
 employee.
 Contains getters and setters for the Fulltime fields.
 @author German Munguia, Sukhjit Singh
 */

public class Fulltime extends Employee {

    private float annualSalary;
    private float singlePayPeriodSalary;

    /**
     Constructor used to generate a Fulltime object using the given parameters.
     @param name of the employee.
     @param department the employee works in.
     @param dateHired the starting date of the employee.
     @param annualSalary of the employee
     */
    public Fulltime(String name, String department, String dateHired, String annualSalary){
        //Extend the properties of the Employee class
        super(name, department, dateHired);
        this.annualSalary = Float.parseFloat(annualSalary);
        singlePayPeriodSalary = 0; //Employee payment not calculated yet so default pay is $0.00
    }

    /**
     Returns a String with the Fulltime fields in a specified format.
     @return String with the employee's profile data concatenated with the singlePayPeriodSalary and annualSalary fields
     */
    @Override
    public String toString() {
        String result =
                "Payment " + String.format("%.2f", singlePayPeriodSalary) + "::FULL TIME::Annual Salary $" + Float.toString(annualSalary);
        return super.toString() + result;
    }

    /**
     Compares the given Employee with this Fulltime employee based on its name, department, and dateHired.
     @param obj the given employee who's data members will be compared
     @return true if the name, department, dateHired, and class type of both employees are equal, false otherwise
     */
    @Override
    public boolean equals(Object obj) {
        //Check if super class is equal to the given object
        if(!super.equals(obj)) {
            return false;
        }
        //Check if the given object is a Fulltime object
        else if (!(obj instanceof Fulltime)) {
            return false;
        }
        return true;
    }

    /**
     Method to calculate the payment for one pay period of this employee based on the annual salary.
     The annual salary is divided by 26 to calculate the biweekly payment for this employee and is assigned to the
     singlePayPeriodSalary floating point variable.
     */
    @Override
    public void calculatePayment() {
        singlePayPeriodSalary = annualSalary/Constants.TOTAL_PAY_PERIODS;
    }

    /**
     Adds single pay compensation to the singlePayPeriodSalary if the employee is a manager, department head, or
     director in the
     company.
     @param PeriodBonus extra compensation based on the employee's management role
     */
    public void addBonus(float PeriodBonus) {
        singlePayPeriodSalary += PeriodBonus;
    }

    /**
     Returns a floating point representation of the annual salary of this employee.
     @return annualSalary of the employee
     */
    public float getAnnualSalary() {
        return annualSalary;
    }

}
