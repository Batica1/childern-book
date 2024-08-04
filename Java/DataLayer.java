import java.math.BigInteger;
import java.security.MessageDigest;
import java.sql.*;
import java.util.*;

import javafx.scene.control.TableView;

/**
 * Data Layer
 * 
 * @Authors Zivkovic, F., Kurfurst, D., Novosel, N., Suric, G.
 * @version 03/04/2022
 */
public class DataLayer {

    private Connection conn;
    private ResultSet rs;
    private Statement stmt;
    private String sql;

    final String DRIVER = "com.mysql.cj.jdbc.Driver";

    /**
     * Data Layer Constructor
     */
    public DataLayer() {
    }

    /**
     * Connect Method
     * 
     * @return connection
     */
    public boolean connect() {
        conn = null;
        Scanner in = new Scanner(System.in);
        String userName = "root";
        String password = "student";
        String url = "jdbc:mysql://localhost/library";

        try {
            Class.forName(DRIVER);
            conn = DriverManager.getConnection(url, userName, password);
            System.out.println("Connection created successfully.");
        } catch (ClassNotFoundException cnfe) {
            System.out.println("ERROR: Connection unsuccessful.");
            System.out.println("ERROR: " + cnfe);
            System.exit(0);
        } catch (SQLException sqle) {
            System.out.println("ERROR: SQLException in connect() " + sqle);
            sqle.printStackTrace();
            System.exit(0);
        }
        in.close();
        return (conn != null);
    }

    /**
     * Get Number of Rows
     * Total number of entries in one table
     * 
     * @param tableName - The table name in database
     * @return numRows - The number of rows
     */
    public int getNumberOfRows(String tableName) {
        int numRows = 0;
        String sql = new String();
        try {
            stmt = conn.createStatement();
            sql = "SELECT count(*) FROM " + tableName + ";";
            rs = stmt.executeQuery(sql);
            rs.next();
            numRows = rs.getInt(1);
        } catch (SQLException sqle) {
            System.out.println("Runtime error has occurred while accessing number of rows from Table:" + tableName
                    + ". Error message: " + sqle);
            sqle.printStackTrace();
        }
        return (numRows);
    }

    /**
     * Get Student Infomation
     * 
     * @param userID - The Student identity number
     * @return
     */
    public String getStudentInfo(String userID) {
        try {
            stmt = conn.createStatement();
            sql = "SELECT UserID, FirstName, LastName, UserEmail, SchoolID, Grade FROM student WHERE UserID == "
                    + userID
                    + ";";
            rs = stmt.executeQuery(sql);
            rs.next();

            // List of students that match the search
            int id = rs.getInt(1);
            String firstName = rs.getString(2);
            String lastName = rs.getString(3);
            String userEmail = rs.getString(4);
            int schoolID = rs.getInt(5);
            String grade = rs.getString(6);

            // Display data for student
            String out = Integer.toString(id) + ", Name: " + firstName + " " + lastName + ", Email: " + userEmail
                    + ", School: "
                    + Integer.toString(schoolID) + ", Grade: " + grade;
            return out;

        } catch (SQLException sqle) {
            System.out.println("Error: " + sqle);
            sqle.printStackTrace();
        }
        return "";
    }

    /**
     * Get Educator Infomation
     * 
     * @param userID - The educator identity number
     * @return
     */
    public String getEducatorInfo(String userID) {
        try {
            stmt = conn.createStatement();
            sql = "SELECT UserID, FirstName, LastName, UserEmail, SchoolID, CourseID FROM educator WHERE UserID == "
                    + userID
                    + ";";
            rs = stmt.executeQuery(sql);
            rs.next();

            // List of educators that match the search
            int id = rs.getInt(1);
            String firstName = rs.getString(2);
            String lastName = rs.getString(3);
            String userEmail = rs.getString(4);
            int schoolID = rs.getInt(5);
            int courseID = rs.getInt(6);

            // Display data for educator
            String out = Integer.toString(id) + ", Name: " + firstName + " " + lastName + ", Email: " + userEmail
                    + ", School: "
                    + Integer.toString(schoolID) + ", Course: " + Integer.toString(courseID);
            return out;

        } catch (SQLException sqle) {
            System.out.println("Error: " + sqle);
            sqle.printStackTrace();
        }
        return "";
    }

    /**
     * Get author information
     * 
     * @param authorID - The author identity number
     * @return
     */
    public String getAuthorInfo(String authorID) {
        try {
            stmt = conn.createStatement();
            String sql = "SELECT AuthorID, FirstName, LastName FROM author WHERE AuthorID = " + authorID + ";";
            System.out.println("Command to be executed: " + sql);
            rs = stmt.executeQuery(sql);

            String firstName = rs.getString(2);
            String lastName = rs.getString(3);
            return "Author: " + firstName + " " + lastName;

        } catch (SQLException sqle) {
            System.out.println("ERROR: " + sqle);
            sqle.printStackTrace();
        }
        return "";
    }

    /**
     * Search Books
     * 
     * @param title - The title of the book
     * @return
     */
    public ArrayList<Book> searchBook(String title) {

        ArrayList<Book> foundBooks = new ArrayList<Book>();
        String query = "SELECT BookID, Title, AuthorID, Language, PublisherID, PublishDate, GenreID FROM book WHERE Title = "
                + title + ";";
        System.out.println("Command to be executed: " + query);
        try {
            // CallableStatement stmt = conn.prepareCall(query);
            // stmt.setString(1, title);
            // ResultSet rs = stmt.executeQuery();
            // int bookID = rs.getInt(1);
            SQLDatabase sql = new SQLDatabase();
            Statement stmt = sql.connection().createStatement();
            stmt.executeUpdate("USE library");
            stmt = sql.connection().createStatement();
            ResultSet rs = stmt.executeQuery(query);
            TableView book_list = new TableView<>();
            //book_list.setModel(DbUtils.resultSetToTableModel(rs));
            //ScrollPane scrollPane = new ScrollPane(book_list);

            /**
             * String titleString = rs.getString(2);
             * int authorID = rs.getInt(3);
             * int language = rs.getInt(4);
             * int publisherID = rs.getInt(5);
             * int genreID = rs.getInt(6);
             * 
             * Book temp = new Book(bookID, titleString, authorID, language, publisherID,
             * genreID);
             * foundBooks.add(temp);
             */

        } catch (

        SQLException sqle) {
            sqle.printStackTrace();
        }
        return foundBooks;
    }

    /**
     * Encrypt Password
     * 
     * @param secret
     * @return
     */
    public String encrypt(String secret) {

        String sha1 = "";
        String value = new String(secret);
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-1");
            digest.reset();
            digest.update(value.getBytes("utf8"));
            sha1 = String.format("%040x", new BigInteger(1, digest.digest()));
        } catch (Exception e) {
            e.printStackTrace();
        }

        return sha1;
    }

    public static void main(String[] args) {
        DataLayer datalayer = new DataLayer();
        datalayer.connect();
    }
}