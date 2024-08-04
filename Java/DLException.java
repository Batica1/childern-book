import java.io.*;
import java.sql.*;
import java.util.ArrayList;

/**
 * DL Exception Class
 * 
 * @Authors Zivkovic, F., Kurfurst, D., Novosel, N., Suric, G.
 * @version 03/04/2022
 */

public class DLException extends Exception {

    Exception exception = new Exception();
    ArrayList<ArrayList<String>> info = new ArrayList<ArrayList<String>>();

    /**
     *
     * Parameterized constructor which accepts an exception
     *
     * @param exception
     *
     */
    public DLException(Exception _exception) {
        exception = _exception;
        writeLog();
    }

    /**
     *
     * Paramterized constructor that accepts an exception and a 2D ArrayList
     * containing additional info.
     *
     * @param _exception - the exception passed in.
     * @param array      - 2D ArrayList containing additional information.
     *
     */
    public DLException(Exception _exception, ArrayList<ArrayList<String>> array) {
        exception = _exception;
        info = array;
        writeLog();
    }

    /**
     *
     * Writes Log
     * Writes exception information to the log file
     *
     */
    private void writeLog() {
        try {
            FileWriter fw = new FileWriter("Errors.log", true);
            PrintWriter pw = new PrintWriter(fw);
            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
            pw.println("Exception: ");
            exception.printStackTrace(pw);
            if (info != null) {
                for (ArrayList<String> data : info) {
                    pw.println(data.toString());
                }
            }
            pw.println("Current time: " + timestamp.toString());
            pw.println();
            pw.close();
        } catch (FileNotFoundException fnfe) {
            System.out.println("Error: Log file does not exist.");
        } catch (IOException ioe) {
            System.out.println("Error could not be logged.");
        }
    }
}