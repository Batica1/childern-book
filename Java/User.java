/**
 * User Class
 *
 * @Authors Zivkovic, F., Kurfurst, D., Novosel, N., Suric, G.
 * @version 03/04/2022
 */

public class User {

    private int userID;
    private String fName;
    private String lName;
    private String email;

    public User(int ID, String fname, String lname) {
        this.userID = ID;
        this.fName = fname;
        this.lName = lname;
        this.email = "";
    }

    public int getId() {
        return this.userID;
    }

    public String getFName() {
        return this.fName;
    }

    public String getLName() {
        return this.lName;
    }

    public String getEmail() {
        return this.email;
    }

    public void setFName(String fname) {
        this.fName = fname;
    }

    public void setLName(String lname) {
        this.lName = lname;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}