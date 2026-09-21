package com.library.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Handles the single SQLite connection used by the whole application and
 * makes sure the required tables exist before the UI starts.
 */
public class DBConnection {

    private static final String DB_URL = "jdbc:sqlite:library.db";
    private static Connection connection;

    private DBConnection() {}

    public static Connection getConnection() throws SQLException {
        if (connection == null || connection.isClosed()) {
            connection = DriverManager.getConnection(DB_URL);
        }
        return connection;
    }

    public static void initializeDatabase() {
        String books = "CREATE TABLE IF NOT EXISTS books (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "title TEXT NOT NULL," +
                "author TEXT NOT NULL," +
                "isbn TEXT," +
                "total_copies INTEGER NOT NULL," +
                "available_copies INTEGER NOT NULL)";

        String members = "CREATE TABLE IF NOT EXISTS members (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "name TEXT NOT NULL," +
                "email TEXT," +
                "phone TEXT)";

        String transactions = "CREATE TABLE IF NOT EXISTS transactions (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "book_id INTEGER NOT NULL," +
                "member_id INTEGER NOT NULL," +
                "issue_date TEXT NOT NULL," +
                "due_date TEXT NOT NULL," +
                "return_date TEXT," +
                "status TEXT NOT NULL," +
                "FOREIGN KEY(book_id) REFERENCES books(id)," +
                "FOREIGN KEY(member_id) REFERENCES members(id))";

        try (Connection conn = getConnection(); Statement st = conn.createStatement()) {
            st.execute(books);
            st.execute(members);
            st.execute(transactions);
        } catch (SQLException e) {
            throw new RuntimeException("Failed to initialize database: " + e.getMessage(), e);
        }
    }
}
