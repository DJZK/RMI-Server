package com.Bryan.Server;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static com.Bryan.Server.ConnectorImpl.ZK;

public class DatabaseReader {
    public static String databasePath = "C:\\Database\\Server.db";
    public static int clientCounter;

    public static void checkAccount(String username) { // Account Checking from sqlite Database
        try {
            try (Connection conn = DriverManager.getConnection("jdbc:sqlite:" + databasePath); Statement statement = conn.createStatement()) {
                statement.execute("SELECT * FROM Accounts WHERE Username = '" + username + "'");
                try (ResultSet results = statement.getResultSet()) {
                    while (results.next()) {
                        ZK[0] = results.getString("Username");
                        ZK[1] = results.getString("Password");
                    }
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getErrorCode() + e.getMessage());
        }
    }
    public static void loadUser(String username) { // Loading User to from database -if the password is right
        try {
            try (Connection conn = DriverManager.getConnection("jdbc:sqlite:" + databasePath); Statement statement = conn.createStatement()) {
                statement.execute("SELECT * FROM Accounts WHERE Username = '" + username + "'");
                try (ResultSet results = statement.getResultSet()) {
                    while (results.next()) {
                        ZK[0] = results.getString("Username");
                        ZK[1] = results.getString("Password");
                        ZK[2] = results.getString("Region");
                        ZK[3] = results.getString("Type");
                    }
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getErrorCode() + e.getMessage());
        }

    }

    public static void genReport(String Region) { // generation of report from database -depends on region
        try {
            try (Connection conn = DriverManager.getConnection("jdbc:sqlite:" + databasePath); Statement statement = conn.createStatement()) {
                statement.execute("SELECT * FROM Report WHERE Region = '" + Region + "'");
                try (ResultSet results = statement.getResultSet()) {
                    clientCounter = 0;
                    ZK[0] = "";
                    while (results.next()) {
                        ZK[0] += ((results.getString("Email") + " ") + results.getString("ClientName") +"\n");
                        clientCounter++;
                        ZK[1] = "" + clientCounter;
                    }

                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
