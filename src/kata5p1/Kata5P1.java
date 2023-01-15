package kata5p1;

import java.sql.*;
import java.util.List;

public class Kata5P1 {

    private static final String DBUrl = "jdbc:sqlite:KATA5.db";
    private static Connection conn;

    public static void main(String[] args) {
        connectToDB();
        if (conn != null) {
            getAllPersonEntries();
            createEmailTable();
            String fileName = args.length > 0 ? args[0] : "email.txt";
            addEmails(new MailListReader().read(fileName));
            closeConnection();
        }
    }

    public static void connectToDB() {
        try {
            conn = DriverManager.getConnection(DBUrl);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void closeConnection() {
        try {
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void getAllPersonEntries() {
        try {
            ResultSet rs = conn.createStatement().executeQuery("SELECT * FROM PEOPLE");
            while (rs.next()) {
                System.out.println(rs.getInt("id") + "\t" +
                        rs.getString("Name") + "\t" +
                        rs.getString("Apellidos") + "\t" +
                        rs.getString("Departamento") + "\t");

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void createEmailTable() {
        try {
            Statement stmt = conn.createStatement();
            stmt.execute("CREATE TABLE IF NOT EXISTS EMAIL (Id INTEGER PRIMARY KEY AUTOINCREMENT, Mail text NOT NULL)");
            System.out.println("Table created");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void addEmails(List<String> emails) {
        try {
            PreparedStatement ps = conn.prepareStatement("INSERT INTO EMAIL(Mail) VALUES(?)");
            for (String email : emails) {
                ps.setString(1, email);
                ps.executeUpdate();
                System.out.println("Adding mail...");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
