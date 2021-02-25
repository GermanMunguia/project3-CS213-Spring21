package project3;

/**
 This class defines the abstract data type Parttime, which encapsulates the data fields and methods of a part time
 employee.
 Contains getters and setters for the Parttime fields.
 @author German Munguia, Sukhjit Singh
 */

public class Parttime extends Employee {

    private float hourlyRate;
    private float workingHours;
    private float singlePayPeriodSalary;

    /**
     Constructor used to generate a Parttime object using the given parameters.
     @param name of the employee.
     @param department the employee works in.
     @param dateHired the starting date of the employee.
     @param hourlyRate of the employee (Pay rate per hour).
     @param workingHours of the employee (Hours the employee worked)
     */
    public Parttime(String name, String department, String dateHired, String hourlyRate, String workingHours){
        super(name, department, dateHired); //Extend the properties of the Employee class
        this.hourlyRate = Float.parseFloat(hourlyRate);
        this.workingHours = Float.parseFloat(workingHours);
        singlePayPeriodSalary = 0; //Employee payment not calculated yet so default pay is $0.00
    }

    /**
     Returns a String with the parttime fields in a specified format.
     @return String with the employee's profile data concatenated with the singlePayPeriod, hourlyRate, and
     workingHours fields
     */
    @Override
    public String toString() {

        String result =
                "Payment $" + String.format("%.2f", singlePayPeriodSalary) + "::PART TIME::Hourly Rate $" + getHourlyRate() + "::Hours worked this period: " +(int)getWorkingHours();

        return super.toString() + result;

    }

    /**
     Compares the given Employee with this Parttime employee based on its name, department, and dateHired.
     @param obj the given employee who's data members will be compared
     @return true if the name, department, dateHired, and class type of both employees are equal, false otherwise
     */
    @Override
    public boolean equals(Object obj) {
        //Check if super class is equal to the given object
        if(!super.equals(obj)) {
            return false;
        }
        //Check if the given object is a Parttime object
        else if (!(obj instanceof Parttime)) {
            return false;
        }
        return true;
    }

    /**
     Method to calculate the payment for one pay period of this employee based on the working hours and hourly rate.
     If the hours worked exceeds 80, the employee is given overtime pay equal to 1.5 times the hourly rate for the
     extra hours worked. The remaining hours are then multiplied by the hourly rate to calculate the regular pay.
     Both payments are summed and assigned to the singlePayPeriodSalary floating point variable.
     */
    @Override
    public void calculatePayment() {
        singlePayPeriodSalary = 0;
        float currentHours = workingHours; //currentHours is used as a temporary value in order to not modify working hours.
        //check if the employee has worked overtime and add it (80 hours)
        if(workingHours > Constants.MAXIMUM_NON_OVERTIME_HOURS_PER_PERIOD) {
            singlePayPeriodSalary += hourlyRate * Constants.OVERTIME_SALARY_MULTIPLIER * (workingHours - Constants.MAXIMUM_NON_OVERTIME_HOURS_PER_PERIOD);
            //reduce workingHours by the overtime that has now been accounted for.
            currentHours = workingHours - (workingHours - Constants.MAXIMUM_NON_OVERTIME_HOURS_PER_PERIOD);
        }
        //Non overtime compensation.
        singlePayPeriodSalary += currentHours * hourlyRate;
    }

    /**
     Method which sets the working hours of this Employee using the given floating point parameter.
     @param workingHours of the employee
     */
    public void setWorkingHours(float workingHours) {
        this.workingHours = workingHours;
    }

    /**
     Returns a floating point representation of the working hours of this employee.
     @return workingHours of the employee
     */
    public float getWorkingHours(){
        return workingHours;
    }
    /**
     Returns a floating point representation of the hourly pay rate of this employee.
     @return hourlyRate of the employee
     */
    public float getHourlyRate(){
        return hourlyRate;
    }


}
