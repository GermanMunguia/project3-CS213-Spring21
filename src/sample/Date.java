package sample;

/**
 This class defines the abstract data type Date, which encapsulates the data fields and methods of a Date.
 Contains constructors to generate Date objects based on input strings, or the today's date by default.
 Contains method to compare dates
 Contains getters and setters for the date fields.
 @author German Munguia, Sukhjit Singh
 */

import java.util.Calendar;

public class Date implements Comparable<Date> {
    private int year;
    private int month;
    private int day;

    /**
     Constructor used to generate a Date object with a given String input representing mm/dd/yyyy.
     The length of the input string is first tested to see if it meets the minimum length for parsing. If so it is
     then parsed based on the position of the / symbols. If the input is incorrectly formatted, the year, month, and
     day variables are assigned a default value of -1.
     @param date on which the employee started
     */
    public Date(String date) {
        //Check if input meets minimum length of 5 characters. m/d/y
        if(date.length() < Constants.MINIMUM_DATE_SUBSTRING_LENGTH){
            year = Constants.DEFAULT_DATE_IF_INPUT_INVALID;
            month = Constants.DEFAULT_DATE_IF_INPUT_INVALID;
            day = Constants.DEFAULT_DATE_IF_INPUT_INVALID;
        }
        else{
            try {
                //Check if input has format: m/...
                if(date.charAt(Constants.SINGLE_DIGIT_MONTH_DASH_POSITION) == '/') {
                    month = Integer.parseInt(date.substring(Constants.MONTH_SUBSTRING_START, Constants.MONTH_SUBSTRING_END1));
                    //Check if input has format: m/d/...
                    if(date.charAt(Constants.SINGLE_DIGIT_DAY_DASH_POSITION1) == '/') {
                        day = Integer.parseInt(date.substring(Constants.DAY_SUBSTRING_START1,Constants.DAY_SUBSTRING_END1));
                        year = Integer.parseInt(date.substring(Constants.YEAR_SUBSTRING_START1));
                    }
                    //Check if input has format: m/dd/...
                    else if(date.charAt(Constants.DOUBLE_DIGIT_DAY_DASH_POSITION1) == '/'){
                        day = Integer.parseInt(date.substring(Constants.DAY_SUBSTRING_START1,Constants.DAY_SUBSTRING_END2));
                        year = Integer.parseInt(date.substring(Constants.YEAR_SUBSTRING_START2));
                    }
                }
                //Check if input has format: mm/...
                else if(date.charAt(Constants.DOUBLE_DIGIT_MONTH_DASH_POSITION) == '/'){
                    month = Integer.parseInt(date.substring(Constants.MONTH_SUBSTRING_START, Constants.MONTH_SUBSTRING_END2));
                    //Check if input has format: mm/d/...
                    if(date.charAt(Constants.SINGLE_DIGIT_DAY_DASH_POSITION2) == '/') {
                        day = Integer.parseInt(date.substring(Constants.DAY_SUBSTRING_START2,Constants.DAY_SUBSTRING_END2));
                        year = Integer.parseInt(date.substring(Constants.YEAR_SUBSTRING_START2));
                    }
                    //Check if input has format: mm/dd/...
                    else if(date.charAt(Constants.DOUBLE_DIGIT_DAY_DASH_POSITION2) == '/'){
                        day = Integer.parseInt(date.substring(Constants.DAY_SUBSTRING_START2,Constants.DAY_SUBSTRING_END3));
                        year = Integer.parseInt(date.substring(Constants.YEAR_SUBSTRING_START3));
                    }
                }
            } catch (NumberFormatException ex) {
                //If the input is incorrectly formatted or has a non integer value, the class data members are
                // assigned a default value of -1.
                month = Constants.DEFAULT_DATE_IF_INPUT_INVALID;
                day = Constants.DEFAULT_DATE_IF_INPUT_INVALID;
                year = Constants.DEFAULT_DATE_IF_INPUT_INVALID;

            }
        }
    }

    /**
     Constructor used to generate a Date object with today's date as default
     A Calendar class object is used to extract today's date. By default month starts at 0 instead of 1, so +1 is
     added to correct this.
     */
    public Date() {
        Calendar calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH) + 1;
        day = calendar.get(Calendar.DATE);
    }

    /**
     Method which returns an integer representing if the date value of the Date object is greater than, equal to, or
     less than the given Date object value. By greater it means that one date is older than the other.
     Example: 2/10/2020 > 3/5/2020. Here the first date is older than the second.
     @return -1, 0, or 1 as this object is less than, equal to, or greater than the given Date object.
     */
    @Override
    public int compareTo(Date date) {
        //If the year of this Date object is greater than the year of the given Date object return -1
        //Example 1999 > 1998
        if(this.year > date.getYear()){
            return -1;
        }
        else if(this.year == date.getYear()){
            /*If same year but the month of this Date object is greater than the month of the given Date object
             return -1 */
            if(this.month > date.getMonth()){
                return -1;
            }
            else if(this.month == date.getMonth()){
                //If same month but day of this Date object is greater than the day of the given Date object return -1
                if (this.day > date.getDay()){
                    return -1;
                }
                else if (this.day == date.getDay()){
                    return 0;
                }
                //If same month but day of this Date object is less than the day of the given Date object return 1
                else if (this.day < date.getDay()){
                    return 1;
                }
            }
            /*If same year but the month of this Date object is less than the month of the given Date object return 1 */
            else if(this.month < date.getMonth()){
                return 1;
            }
        }
        //If the year of this Date object is less than the year of the given Date object return 1
        //Example 1998 < 1999
        else if(this.year < date.getYear()) {
            return 1;
        }

        return 0;
    } //return 1, 0, or -1

    /**
     Helper method which returns an integer representing the 'year' data member of the Date class.
     @return year the employee started.
     */
    public int getYear(){
        return year;
    }

    /**
     Helper method which returns an integer representing the 'month' data member of the Date class.
     @return month the employee started.
     */
    public int getMonth(){
        return month;
    }

    /**
     Helper method which returns an integer representing the 'day' data member of the Date class.
     @return day the employee started.
     */
    public int getDay(){
        return day;
    }

    /**
     Helper method which checks if the Date object's 'year' data member represents a leap year, returning the
     Boolean value true if it is, false otherwise.
     The method first checks if the year is divisible by 4 returning false otherwise. Then it checks if it is divisible
     by 100, returning true otherwise, and it checks if it is further divisible by 400, returning false otherwise.
     @return true if the year is a leap year, false otherwise.
     */
    private Boolean isLeapYear(){
        if (year % Constants.QUADRENNIAL == 0){
            if (year % Constants.CENTENNIAL == 0){
                if(year % Constants.QUARTERCENTENNIAL == 0){
                    return true;
                }
                else{
                    return false;
                }
            }
            else{
                return true;
            }
        }
        else{
            return false;
        }
    }

    /**
     Method to check to see if a Date object has a valid date and returns a Boolean value of true if the date is
     valid, false otherwise.
     First check the year. If it is valid, check the month. If month is valid, check the day. Uses the isLeapYear()
     helper method to aid in calculating the maximum number of days in February.
     @return true if the date is valid, false otherwise.
     */
    public Boolean isValid() {
        //Calendar calendar = Calendar.getInstance();
        Date today = new Date();
        //If year is not within bounds return false
        if(year < Constants.MINIMUM_YEAR_LIMIT || year > today.getYear()){
            return false;
        }
        else if(year == today.getYear()){//calendar.get(Calendar.YEAR)) {
            //If it is current year, and month is greater than current month return false
            if(month > (today.getMonth())){
                return false;
            }
            //If it is current year and month, and date is greater than current date return false
            else if (month == (today.getMonth())){
                if(day > today.getDay()){
                    return false;
                }
            }
        }
        //If month is not within bounds return false
        else if(month < Constants.JANUARY || month > Constants.DECEMBER) {
            return false;
        }
        //If it is a long month, and day is outside of bounds return false
        else if(month == Constants.JANUARY || month == Constants.MARCH || month == Constants.MAY || month == Constants.JULY ||
                month == Constants.AUGUST || month == Constants.OCTOBER || month == Constants.DECEMBER){
            if(day > Constants.DAYS_IN_LONG_MONTH || day < 1){
                return false;
            }
        }
        //If it is a short month, and day is outside of bounds return false
        else if(month == Constants.APRIL || month == Constants.JUNE || month == Constants.SEPTEMBER || month == Constants.NOVEMBER){
            if(day > Constants.DAYS_IN_SHORT_MONTH || day < 1){
                return false;
            }
        }
        //If month is February determine if it is a leap year and if day is outside of bounds return false
        else if(month == Constants.FEBRUARY){
            if(isLeapYear()){
                if (day > Constants.DAYS_IN_FEB_LEAP_YEAR || day < 1) {
                    return false;
                }
            }
            else if(day > Constants.DAYS_IN_FEB_NORMAL_YEAR || day < 1){
                return false;
            }
        }
        return true;
    }

}
