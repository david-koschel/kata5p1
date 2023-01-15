package kata5p1;

import java.sql.*;

public class Kata5P1 {

    public static void main(String[] args) {
        try {
            Connection conn = DriverManager.getConnection("jdbc:sqlite:KATA5.db");
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
}
