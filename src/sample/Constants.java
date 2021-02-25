package sample;

/**
 Contains constants which are referenced throughout other classes.
 All are made immutable as they are constants.
 @author German Munguia, Sukhjit Singh
 */
public final class Constants {

    //Constants for Month Names
    public static final int JANUARY = 1;
    public static final int FEBRUARY = 2;
    public static final int MARCH = 3;
    public static final int APRIL = 4;
    public static final int MAY = 5;
    public static final int JUNE = 6;
    public static final int JULY = 7;
    public static final int AUGUST = 8;
    public static final int SEPTEMBER = 9;
    public static final int OCTOBER = 10;
    public static final int NOVEMBER = 11;
    public static final int DECEMBER = 12;

    //Constants for days in a Month
    public static final int DAYS_IN_LONG_MONTH = 31;
    public static final int DAYS_IN_SHORT_MONTH = 30;
    public static final int DAYS_IN_FEB_LEAP_YEAR = 29;
    public static final int DAYS_IN_FEB_NORMAL_YEAR = 28;

    //Constants for year limits
    public static final int MINIMUM_YEAR_LIMIT = 1900;

    //Constant for default value if date input is not an integer
    public static final int DEFAULT_DATE_IF_INPUT_INVALID = -1;

    //Constants for date substring positions
    public static final int MINIMUM_DATE_SUBSTRING_LENGTH = 5;

    public static final int SINGLE_DIGIT_MONTH_DASH_POSITION = 1;
    public static final int DOUBLE_DIGIT_MONTH_DASH_POSITION = 2;
    public static final int SINGLE_DIGIT_DAY_DASH_POSITION1 = 3;
    public static final int SINGLE_DIGIT_DAY_DASH_POSITION2 = 4;
    public static final int DOUBLE_DIGIT_DAY_DASH_POSITION1 = 4;
    public static final int DOUBLE_DIGIT_DAY_DASH_POSITION2 = 5;

    public static final int MONTH_SUBSTRING_START = 0;
    public static final int MONTH_SUBSTRING_END1 = 1;
    public static final int MONTH_SUBSTRING_END2 = 2;

    public static final int DAY_SUBSTRING_START1 = 2;
    public static final int DAY_SUBSTRING_START2 = 3;
    public static final int DAY_SUBSTRING_END1 = 3;
    public static final int DAY_SUBSTRING_END2 = 4;
    public static final int DAY_SUBSTRING_END3 = 5;

    public static final int YEAR_SUBSTRING_START1 = 4;
    public static final int YEAR_SUBSTRING_START2 = 5;
    public static final int YEAR_SUBSTRING_START3 = 6;

    //Constants for leap year calculation
    public static final int QUADRENNIAL = 4;
    public static final int CENTENNIAL = 100;
    public static final int QUARTERCENTENNIAL = 400;

    //Constants for company class
    public static final int EMPLOYEE_NOT_FOUND = -1;
    public static final int STARTING_ARRAY_SIZE = 4;
    public static final int INCREASE_ARRAY_BY = 4;
    public static final int FIRST_DATE_LESS_THAN_SECOND = -1;
    public static final float OVERTIME_SALARY_MULTIPLIER = 1.5f;
    public static final int MAXIMUM_NON_OVERTIME_HOURS_PER_PERIOD = 80;

    //Constants for employee payment calculation
    public static final float TOTAL_PAY_PERIODS = 26;

    //Constants for management class
    public static final int MANAGER = 1;
    public static final int DEPARTMENT_HEAD = 2;
    public static final int DIRECTOR = 3;
    public static final float MANAGER_COMPENSATION = 5000;
    public static final float DEPARTMENT_HEAD_COMPENSATION = 9500;
    public static final float DIRECTOR_COMPENSATION = 12000;

    //Constants for PayrollProcessing class
    public static final int MAXIMUM_HOURS = 100;
    public static final int TOKEN_COUNT_NON_MANAGEMENT_EMPLOYEE = 4;
    public static final int TOKEN_COUNT_MANAGEMENT_EMPLOYEE = 5;
    public static final int TOKEN_COUNT_REMOVE = 3;
    public static final int TOKEN_COUNT_SET_NUMBER = 4;

}
