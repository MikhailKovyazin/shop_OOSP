import java.sql.*;
import java.util.*;
public class DB {
    public String url;
    public Connection con;
    private Statement stmt;
    DB(){
        this(System.getProperty("user.dir"));
    }
    DB(String dir){
        url = "jdbc:sqlite:" +dir+"\\db\\db.s3db";
    }

    public void connect(){
        try {
            con = DriverManager.getConnection(url);
        }catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    public void closeConn(){
        try {
            con.close();
        }catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public ResultSet read(String table, String filters) {
        String sqlText = "SELECT * FROM "+table;

        if (!filters.isEmpty()) {
            sqlText += " WHERE "+filters;
        }
       // System.out.println(sqlText);
        try {
            //connect();
            stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sqlText);
            //closeConn();
            return rs;
        }catch (SQLException e){
            System.out.println(e.getMessage());
            return null;
        }
    }
    public void write(String table, String data) {
        String SQLText = "INSERT INTO "+table+" VALUES ("+data+")";
        try {
            //connect();
            stmt = con.createStatement();
            stmt.executeUpdate(SQLText);
            //closeConn();
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }
    public void update(String table, String data, int id) {
        String SQLText = "UPDATE "+table+" SET "+data+" WHERE id="+id;
        try {
           // connect();
            stmt = con.createStatement();
            stmt.executeUpdate(SQLText);
            //closeConn();
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }
}