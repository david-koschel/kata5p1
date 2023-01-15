package kata5p1;

import org.sqlite.core.DB;

import java.sql.*;

public class Kata5P1 {

    private static final String DBUrl = "jdbc:sqlite:KATA5.db";

    public static void main(String[] args) {
        getAll();
        newTable();
    }

    public static void getAll() {
        try {
            Connection conn = DriverManager.getConnection(DBUrl);
            if (conn != null) {
                ResultSet rs = conn.createStatement().executeQuery("SELECT * FROM PEOPLE");
                while (rs.next()) {
                    System.out.println(rs.getInt("id") + "\t" +
                            rs.getString("Name") + "\t" +
                            rs.getString("Apellidos") + "\t" +
                            rs.getString("Departamento") + "\t");
                }
                conn.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void newTable() {
        try (Connection conn = DriverManager.getConnection(DBUrl)) {
            Statement stmt = conn.createStatement();
            stmt.execute("CREATE TABLE IF NOT EXISTS EMAIL (Id INTEGER PRIMARY KEY AUTOINCREMENT, Mail text NOT NULL)");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
