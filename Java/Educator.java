/**
 * Educator Class
 *
 * @Authors Zivkovic, F., Kurfurst, D., Novosel, N., Suric, G.
 * @version 03/04/2022
 */

public class Educator extends User {

    private int courseID;
    private String courseName;

    public Educator(int userID, String fname, String lname, int courseID, String courseName, String email) {
        super(userID, fname, lname);
        this.courseID = courseID;
        this.setEmail(email);
        ;
    }

    public void setCourseID(int deptId) {
        this.courseID = deptId;
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

    public String toString() {
        String out = "";
        out += "Educator ID: " + this.getId() + "\n";
        out += "Name: " + this.getLName() + ", " + this.getFName() + "\n";
        out += "Email: " + this.getEmail() + "\n";
        out += "Course ID: " + this.getCourseID() + "| Course Name:" + this.getCourseName() + "\n";

        return out;
    }
}