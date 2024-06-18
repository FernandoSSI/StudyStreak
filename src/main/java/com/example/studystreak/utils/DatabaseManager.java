package com.example.studystreak.utils;

import com.example.studystreak.models.Counter;
import com.example.studystreak.models.Subjects;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DatabaseManager {

    private static final String DATABASE_URL = "jdbc:sqlite:mydatabase.db";

    public static Connection connect() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(DATABASE_URL);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }

    public static void createTables() {
        String createCounterTable = "CREATE TABLE IF NOT EXISTS counter ("
                + " currentStreak INTEGER,"
                + " totalQst INTEGER,"
                + " dayQst INTEGER,"
                + " date TEXT"
                + ");";

        String createSubjectsTable = "CREATE TABLE IF NOT EXISTS subjects ("
                + " id INTEGER PRIMARY KEY AUTOINCREMENT,"
                + " name TEXT NOT NULL,"
                + " qstCount INTEGER"
                + ");";

        try (Connection conn = connect();
             Statement stmt = conn.createStatement()) {
            stmt.execute(createCounterTable);
            stmt.execute(createSubjectsTable);

            // Ensure there is one row in the counter table
            ResultSet rs = stmt.executeQuery("SELECT COUNT(*) AS count FROM counter");
            if (rs.next() && rs.getInt("count") == 0) {
                stmt.execute("INSERT INTO counter (currentStreak, totalQst, dayQst, date) VALUES (0, 0, 0, '1970-01-01')");
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void updateCounter(int currentStreak, int totalQst, int dayQst, Date date) {
        String sql = "UPDATE counter SET currentStreak = ?, totalQst = ?, dayQst = ?, date = ?";

        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, currentStreak);
            pstmt.setInt(2, totalQst);
            pstmt.setInt(3, dayQst);
            pstmt.setString(4, new java.sql.Date(date.getTime()).toString());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static Counter getCounter() {
        String sql = "SELECT currentStreak, totalQst, dayQst, date FROM counter";
        Counter counter = null;

        try (Connection conn = connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            if (rs.next()) {
                int currentStreak = rs.getInt("currentStreak");
                int totalQst = rs.getInt("totalQst");
                int dayQst = rs.getInt("dayQst");
                Date date = java.sql.Date.valueOf(rs.getString("date"));

                counter = new Counter(currentStreak, totalQst, dayQst, date);
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return counter;
    }

    public static void insertSubject(String name, int qstCount) {
        String sql = "INSERT INTO subjects(name, qstCount) VALUES(?, ?)";

        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, name);
            pstmt.setInt(2, qstCount);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static Subjects getSubjectById(int id) {
        String sql = "SELECT id, name, qstCount FROM subjects WHERE id = ?";
        Subjects subject = null;

        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    subject = new Subjects(rs.getInt("id"), rs.getString("name"), rs.getInt("qstCount"));
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return subject;
    }

    public static List<Subjects> getAllSubjects() {
        String sql = "SELECT id, name, qstCount FROM subjects";
        List<Subjects> subjectsList = new ArrayList<>();

        try (Connection conn = connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Subjects subject = new Subjects(rs.getInt("id"), rs.getString("name"), rs.getInt("qstCount"));
                subjectsList.add(subject);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return subjectsList;
    }

    public static void updateSubject(int id, String name, int qstCount) {
        String sql = "UPDATE subjects SET name = ?, qstCount = ? WHERE id = ?";

        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, name);
            pstmt.setInt(2, qstCount);
            pstmt.setInt(3, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void deleteSubject(int id) {
        String sql = "DELETE FROM subjects WHERE id = ?";

        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
