
/**
 *  Project Data Layer
 *  @Authors Zivkovic, F., Kurfurst, D., Novosel, N., Suric, G.
 *  @version 03/04/2022
 */

import java.sql.*;
import java.util.*;

public class SQLDatabase {

    private String database;
    private String user;
    private String pass;
    private Connection conn;
    private ArrayList<ArrayList<String>> results = new ArrayList<ArrayList<String>>();

    public void MySQLDatabase(String _user, String _pass) {
        user = _user;
        pass = _pass;
        database = "jdbc:mysql://localhost/library";
    }

    public boolean connect() throws DLException {
        try {
            conn = DriverManager.getConnection(database, user, pass);
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new DLException(e);
        }
    }

    public boolean close() throws DLException {
        try {
            conn.close();
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new DLException(e);
        }
    }

    public Connection connection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/library?user=root&password=admin");
            return con;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public ArrayList<ArrayList<String>> getData(String sql, ArrayList<String> values) throws DLException {
        try {
            PreparedStatement ps = prepare(sql, values);
            ResultSet rs = ps.executeQuery();
            ArrayList<ArrayList<String>> results = new ArrayList<ArrayList<String>>();
            ResultSetMetaData rsmd = rs.getMetaData();
            int resultColumns = rsmd.getColumnCount();

            ArrayList<String> fieldnames = new ArrayList<String>();
            ArrayList<String> fieldwidths = new ArrayList<String>();
            for (int i = 1; i <= resultColumns; i++) {
                fieldnames.add(rsmd.getColumnLabel(i));
                fieldwidths.add(Integer.toString(rsmd.getColumnDisplaySize(i)));
            }
            fieldnames.toString();
            fieldwidths.toString();
            results.add(fieldnames);
            results.add(fieldwidths);

            while (rs.next()) {
                ArrayList<String> resultrow = new ArrayList<String>();
                for (int i = 1; i <= resultColumns; i++) {
                    resultrow.add(rs.getString(i));
                }
                results.add(resultrow);
            }
            return results;
        } catch (Exception e) {
            System.out.println("Data could not be retrieved.");
            ArrayList<ArrayList<String>> info = new ArrayList<ArrayList<String>>();
            ArrayList<String> statement = new ArrayList<String>();
            statement.add("SQL Query:");
            statement.add(sql);
            info.add(statement);
            values.add(0, "Values to be bound:");
            info.add(values);
            throw new DLException(e, info);
        }
    }

    public int setData(String sql, ArrayList<String> values) throws DLException {
        try {
            return executeStmt(sql, values);
        } catch (Exception e) {
            System.out.println("Data could not be changed.");
            ArrayList<ArrayList<String>> info = new ArrayList<ArrayList<String>>();
            ArrayList<String> statement = new ArrayList<String>();
            statement.add("SQL Query:");
            statement.add(sql);
            info.add(statement);
            values.add(0, "Values to be bound:");
            info.add(values);
            throw new DLException(e, info);
        }
    }

    private PreparedStatement prepare(String sql, ArrayList<String> values) throws DLException {
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            for (int i = 1; i <= values.size(); i++) {
                ps.setString(i, values.get(i - 1));
            }
            return ps;
        } catch (Exception e) {
            System.out.println("Statement could not be prepared.");
            ArrayList<ArrayList<String>> info = new ArrayList<ArrayList<String>>();
            ArrayList<String> statement = new ArrayList<String>();
            statement.add("SQL Query:");
            statement.add(sql);
            info.add(statement);
            values.add(0, "Values to be bound:");
            info.add(values);
            throw new DLException(e, info);
        }
    }

    private int executeStmt(String sql, ArrayList<String> values) throws DLException {
        try {
            PreparedStatement ps = prepare(sql, values);
            int changed;
            changed = ps.executeUpdate();
            return changed;
        } catch (Exception e) {
            System.out.println("Statement could not be executed.");
            ArrayList<ArrayList<String>> info = new ArrayList<ArrayList<String>>();
            ArrayList<String> statement = new ArrayList<String>();
            statement.add("SQL Query:");
            statement.add(sql);
            info.add(statement);
            values.add(0, "Values to be bound:");
            info.add(values);
            throw new DLException(e, info);
        }
    }

}