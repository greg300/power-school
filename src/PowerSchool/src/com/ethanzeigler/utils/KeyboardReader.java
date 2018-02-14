package PowerSchool.src.com.ethanzeigler.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Random;

/**
 * Improvement on KeyboardReader provided in Turtle Graphics for Eastern High School's programming courses.
 * Adds error checking, input ranges, and several more input types
 *
 * @author Ethan Zeigler, class of '16
 */
public class KeyboardReader {
	private static String WITHOUT_EXCEPTION_ERROR_TEMPLATE = "Invalid input. Input must be between %d and %d.%n";
	private static String WITH_EXCEPTION_ERROR_TEMPLATE = "Invalid input. Input must be between %d and %d and not %d%n";
	
    private BufferedReader bR;

    /**
     * Constructs a new KeyboardReader Object
     */
    public KeyboardReader() {
        bR = new BufferedReader(new InputStreamReader(System.in));
    }

    //<editor-fold desc="Reading Strings">

    /**
     * Reads a String given by the user to the console
     *
     * @return A String value of the input given by the user
     */
    public String readLine() {
        return this.readLine("", true);
    }

    /**
     * Reads the String value given by the user
     *
     * @param message Message of the prompt
     * @return A String value of the input given by the user
     */
    public String readLine(String message) {
        return this.readLine(message, true);
    }

    /**
     * Reads a String value given by the user. If the value cannot be returned as a valid string or is invalid as
     * defined by boolean emptyIsValid, the user will be prompted to enter the input again.
     *
     * @param message      Message of the prompt
     * @param emptyIsValid used to determine whether an empty line is valid. Ex. "" with true given will not be accepted.
     * @return The String value of the input given by the user
     */
    public String readLine(String message, boolean emptyIsValid) {
        String string = null;
        boolean isValid = false;
        do {
            System.out.print(message + ": ");
            try {
                //read line from console
                string = bR.readLine();
                if (emptyIsValid || string.length() != 0) {
                    //string input is acceptable
                    isValid = true;
                } else {
                    System.out.println("Invalid input. Input must have a length of a least 1. Please retry.");
                }
            } catch (IOException e) {
                // catch IOException that could be thrown by bR.readLine()
                System.out.println("An IOException occurred:\n");
                e.printStackTrace();
            }
        } while (!isValid); // if a non-valid input was given, retry
        return string;
    }
    //</editor-fold>

    //<editor-fold desc="Reading Integers">

    /**
     * Reads the Integer value given by the user. If the value cannot be returned as a valid Integer, the user will be prompted to enter the input again.
     *
     * @return Integer value of the user input
     */
    public int readInt() {
        return this.readInt("", Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    /**
     * Reads the Integer value given by the user. If the value cannot be returned as a valid Integer, the user will be prompted to enter the input again.
     *
     * @param message Message of the prompt
     * @return Integer value of the user input
     */
    public int readInt(String message) {
        return this.readInt(message, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    /**
     * Reads the Integer value given by the user. If the value cannot be returned as a valid Integer, the user will be prompted to enter the input again.
     *
     * @param minLimit The minimum Integer value accepted
     * @param maxLimit The maximum Integer value accepted
     * @return Integer value of the user input
     */
    public int readInt(int minLimit, int maxLimit) {
        return this.readInt("", minLimit, maxLimit);
    }
    

    /**
     * Reads the Integer value given by the user. If the value cannot be returned as a valid Integer, the user will be prompted to enter the input again.
     *
     * @param message  Message of the prompt
     * @param minLimit The minimum Integer value accepted
     * @param maxLimit The maximum Integer value accepted
     * @return Integer value of the user input
     */
    public int readInt(String message, int minLimit, int maxLimit, int... exceptions) {
        if (minLimit > maxLimit) throw new IllegalArgumentException("min limit cannot be greater than max limit");

        int num = 0;
        String stringValueOf = null;
        boolean isValid = false;
        do {
            System.out.print(message + ": ");
            try {
                stringValueOf = bR.readLine();
                num = Integer.parseInt(stringValueOf);
                boolean isException = false;
                
                for(int val: exceptions) {
                	if(val == num) isException = true;
                }
                if (num >= minLimit && num <= maxLimit && !isException) {
                    isValid = true;
                } else {
                	if(exceptions.length == 0)
                		System.out.printf(WITHOUT_EXCEPTION_ERROR_TEMPLATE, minLimit, maxLimit);
                	else 
                		System.out.printf(WITH_EXCEPTION_ERROR_TEMPLATE, minLimit, maxLimit, exceptions[0]);
                }
            } catch (IOException e) {
                System.out.println("An IOException occurred:");
                e.printStackTrace();
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. \"" + stringValueOf + "\" is not an Integer. Please retry.");
            }
        } while (!isValid);
        return num;
    }
    //</editor-fold>

    //<editor-fold desc="Reading Doubles">

    /**
     * Reads the Double value given by the user. If the value cannot be returned as a valid Double, the user will be prompted to enter the input again.
     *
     * @return Double value of the user input
     */
    public double readDouble() {
        return this.readDouble("", Double.MIN_VALUE, Double.MAX_VALUE);
    }

    /**
     * Reads the Double value given by the user. If the value cannot be returned as a valid Double, the user will be prompted to enter the input again.
     *
     * @param message Message of the prompt
     * @return Double value of the user input
     */
    public double readDouble(String message) {
        return this.readDouble(message, Double.MAX_VALUE, Double.MIN_VALUE);
    }

    /**
     * Reads the Double value given by the user. If the value cannot be returned as a valid Double, the user will be prompted to enter the input again.
     *
     * @param minLimit The minimum Double value accepted
     * @param maxLimit The maximum Double value accepted
     * @return Double value of the user input
     */
    public double readDouble(double minLimit, double maxLimit) {
        return this.readDouble("", minLimit, maxLimit);
    }

    /**
     * Reads the Double value given by the user. If the value cannot be returned as a valid Double or is outside the
     * given range, the user will be prompted to enter the input again.
     *
     * @param message  Message of the prompt
     * @param minLimit The minimum Double value accepted inclusively
     * @param maxLimit The maximum Double value accepted inclusively
     * @return Double value of the user input
     */
    public double readDouble(String message, double minLimit, double maxLimit) {
        // if the given min limit is greater than the max limit, throw IllegalArgumentException
        if (minLimit > maxLimit) throw new IllegalArgumentException("min limit cannot be greater than max limit");

        double num = 0;
        String stringValueOf = null;
        boolean isValid = false;
        do {
            System.out.print(message + ": ");
            try {
                stringValueOf = bR.readLine();
                num = Double.parseDouble(stringValueOf);
                if (num >= minLimit && num <= maxLimit) {
                    isValid = true;
                } else {
                    System.out.printf("Invalid input. Input must be between %s and %s%n", minLimit, maxLimit);
                }
            } catch (IOException e) {
                System.out.println("An IOException occurred:");
                e.printStackTrace();
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. \"" + stringValueOf + "\" is not a Double. Please retry.");
            }
        } while (!isValid);
        return num;
    }
    //</editor-fold>

    //<editor-fold desc="Reading Characters">

    /**
     * Reads the Character value given by the user. If the value cannot be returned as a valid Character, the user will be prompted to enter the input again.
     *
     * @return Character value of the user input
     */
    public char readChar() {
        return this.readChar("", Character.MIN_VALUE, Character.MAX_VALUE);
    }

    /**
     * Reads the Character value given by the user. If the value cannot be returned as a valid Character, the user will be prompted to enter the input again.
     *
     * @param message Message of the prompt
     * @return Character value of the user input
     */
    public char readChar(String message) {
        return this.readChar(message, Character.MIN_VALUE, Character.MAX_VALUE);
    }

    /**
     * Reads the Character value given by the user. If the value cannot be returned as a valid Character, the user will be prompted to enter the input again.
     *
     * @param minLimit The minimum Character value accepted
     * @param maxLimit The maximum Character value accepted
     * @return Character value of the user input
     */
    public char readChar(char minLimit, char maxLimit) {
        return this.readChar("", minLimit, maxLimit);
    }

    /**
     * Reads the Character value given by the user. If the value cannot be returned as a valid Character, the user will be prompted to enter the input again.
     *
     * @param message  Message of the prompt
     * @param minLimit The minimum Character value accepted
     * @param maxLimit The maximum Character value accepted
     * @return Character value of the user input
     */
    public char readChar(String message, char minLimit, char maxLimit) {
        if (minLimit > maxLimit) throw new IllegalArgumentException("min limit cannot be greater than max limit");

        char character = ' ';
        String stringValueOf;
        boolean isValid = false;
        do {
            System.out.print(message + ": ");
            try {
                stringValueOf = bR.readLine();
                if (stringValueOf.length() == 1) {
                    character = stringValueOf.charAt(0);
                    if (character >= minLimit && character <= maxLimit) {
                        isValid = true;
                    } else {
                        System.out.printf("Invalid input. %s is not between %s and %s. Please retry.%n", stringValueOf, minLimit, maxLimit);
                    }
                } else {
                    System.out.printf("Invalid input. \"%s\" is not a character. Please retry.%n", stringValueOf);
                }
            } catch (IOException e) {
                System.out.println("An IOException occurred:");
                e.printStackTrace();
            }
        } while (!isValid);
        return character;
    }
    //</editor-fold>

    //<editor-fold desc="Reading Floats">

    /**
     * Reads the Float value given by the user. If the value cannot be returned as a valid Float, the user will be prompted to enter the input again.
     *
     * @return Float value of the user input
     */
    public float readFloat() {
        return this.readFloat("", Float.MAX_VALUE, Float.MIN_VALUE);
    }

    /**
     * Reads the Float value given by the user. If the value cannot be returned as a valid Float, the user will be prompted to enter the input again.
     *
     * @param message Message of the prompt
     * @return Float value of the user input
     */
    public float readFloat(String message) {
        return this.readFloat(message, Float.MAX_VALUE, Float.MIN_VALUE);
    }

    /**
     * Reads the Float value given by the user. If the value cannot be returned as a valid Float, the user will be prompted to enter the input again.
     *
     * @param minLimit The minimum Float value accepted
     * @param maxLimit The maximum Float value accepted
     * @return Float value of the user input
     */
    public float readFloat(float minLimit, float maxLimit) {
        return this.readFloat("", minLimit, maxLimit);
    }

    /**
     * Reads the Float value given by the user. If the value cannot be returned as a valid Float, the user will be prompted to enter the input again.
     *
     * @param message  Message of the prompt
     * @param minLimit The minimum Float value accepted
     * @param maxLimit The maximum Float value accepted
     * @return Float value of the user input
     */
    public float readFloat(String message, float minLimit, float maxLimit) {
        if (minLimit > maxLimit)
            throw new IllegalArgumentException("min limit cannot be greater than or equal to max limit");

        float num = 0;
        String stringValueOf = null;
        boolean isValid = false;
        do {
            System.out.print(message + ": ");
            try {
                stringValueOf = bR.readLine();
                num = Float.parseFloat(stringValueOf);
                if ((num == Float.POSITIVE_INFINITY || num == Float.NEGATIVE_INFINITY) || (num < minLimit || num > maxLimit))
                    System.out.printf("Invalid input. %s is not between %s and %s. Please retry.%n", stringValueOf, minLimit, maxLimit);
                else
                    isValid = true;
            } catch (NumberFormatException e) {
                System.out.printf("Invalid input. \"%s\" is not a valid Float. Please retry.%n", stringValueOf);
            } catch (IOException e) {
                System.out.println("An IOException occurred:");
                e.printStackTrace();
            }
        } while (!isValid);
        return num;
    }
    //</editor-fold>

    //<editor-fold desc="Reading Longs">

    /**
     * Reads the Long value given by the user. If the value cannot be returned as a valid Long, the user will be prompted to enter the input again.
     *
     * @return Long value of the user input
     */
    public long readLong() {
        return this.readLong("", Long.MIN_VALUE, Long.MAX_VALUE);
    }

    /**
     * Reads the Long value given by the user. If the value cannot be returned as a valid Long, the user will be prompted to enter the input again.
     *
     * @param message Message of the prompt
     * @return Long value of the user input
     */
    public long readLong(String message) {
        return this.readLong(message, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    /**
     * Reads the Long value given by the user. If the value cannot be returned as a valid Long, the user will be prompted to enter the input again.
     *
     * @param minLimit The minimum Long value accepted
     * @param maxLimit The maximum Long value accepted
     * @return Long value of the user input
     */
    public long readLong(long minLimit, long maxLimit) {
        return this.readLong("", minLimit, maxLimit);
    }

    /**
     * Reads the Long value given by the user. If the value cannot be returned as a valid Long, the user will be prompted to enter the input again.
     *
     * @param message  Message of the prompt
     * @param minLimit The minimum Long value accepted
     * @param maxLimit The maximum Long value accepted
     * @return Long value of the user input
     */
    public long readLong(String message, long minLimit, long maxLimit) {
        if (minLimit > maxLimit) throw new IllegalArgumentException("min cannot be greater than max");

        long num = 0;
        String stringValueOf = null;
        boolean isValid = false;
        do {
            System.out.print(message + ": ");
            try {
                stringValueOf = bR.readLine();
                num = Long.parseLong(stringValueOf);
                if (num >= minLimit && num <= maxLimit) {
                    isValid = true;
                } else {
                    System.out.printf("Invalid input. %s is not between %s and %s. Please retry.%n", stringValueOf, minLimit, maxLimit);
                }
            } catch (NumberFormatException e) {
                System.out.printf("%s is not a valid Long. Please retry%n", stringValueOf);
            } catch (IOException e) {
                System.out.println("An IOException occurred:\n");
                e.printStackTrace();
            }
        } while (!isValid);
        return num;
    }
    //</editor-fold>

    //<editor-fold desc="Reading Booleans">

    /**
     * Reads the boolean value given by the user. If the value cannot be returned as a valid boolean, the user will be prompted to enter the input again.
     *
     * @return boolean value of the user input
     */
    public boolean readBoolean() {
        return this.readBoolean("");
    }

    /**
     * Reads the boolean value given by the user. If the value cannot be returned as a valid boolean, the user will be prompted to enter the input again.
     *
     * @param message Message of the prompt
     * @return boolean value of the user input
     */
    public boolean readBoolean(String message) {
        String stringValueOf;
        boolean isValid = false;
        do {
            System.out.print(message + ": ");
            try {
                stringValueOf = bR.readLine();
                if (stringValueOf.equalsIgnoreCase("true") || stringValueOf.equalsIgnoreCase("yes") || stringValueOf.equalsIgnoreCase("y")) {
                    isValid = true;
                    return true;
                } else if (stringValueOf.equalsIgnoreCase("false") || stringValueOf.equalsIgnoreCase("no") || stringValueOf.equalsIgnoreCase("n")) {
                    isValid = true;
                    return false;
                } else {
                    System.out.printf("%s is not a valid boolean. Use true or false / yes or no / y or n. Please retry.%n", stringValueOf);
                }
            } catch (IOException e) {
                System.out.println("An IOException occurred:");
                e.printStackTrace();
            }
        } while (!isValid);
        return false;//it needed a concrete end, but this will never run (hopefully)
    }
    //</editor-fold>

    /**
     * Pauses the current thread until the user strikes ENTER
     *
     * @param message Message of the prompt
     */
    public void pause(String message, boolean displayColon) {
    	System.out.print(message + (displayColon ? ":" : "") + " ");
        try {
            bR.readLine();
        } catch (IOException e) {
            System.out.println("An IOException occurred:");
            e.printStackTrace();
        }
    }

    /**
     * Closes the stream linked to the console.
     */
    public void close() {
        try {
            bR.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}