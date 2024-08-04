/**
 * Student Class
 *
 * @Authors Zivkovic, F., Kurfurst, D., Novosel, N., Suric, G.
 * @version 03/04/2022
 */

public class Student extends User {

    private int courseID;
    private String courseName;

    public Student(int userID, String fname, String lname, int courseID, String courseName, String email) {
        super(userID, fname, lname);
        this.courseID = courseID;
        this.courseName = courseName;
        this.setEmail(email);
    }

    public void setCourseID(int courseID) {
        this.courseID = courseID;
    }

    public int getCourseID() {
        return this.courseID;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getCourseName() {
        return this.courseName;
    }

    public void addInterest(String interestID) {
        this.addInterest(interestID);
    }

    public String toString() {
        String out = "";
        out += "Name: " + this.getLName() + ", " + this.getFName() + "\n";
        out += "Email: " + this.getEmail() + "\n";
        out += "Course ID: " + this.getCourseID() + " Course Name: " + this.getCourseName() + "\n";
        return out;
    }
}