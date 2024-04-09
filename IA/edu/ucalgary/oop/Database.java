package edu.ucalgary.oop;

import java.sql.*;

public class Database{

    public class DB {

        private final String url;
        private final String username;
        private final String password;
        private Connection myConnect;

        public DB(String url, String username, String password) {
            this.url = url;
            this.username = username;
            this.password = password;
        }

        public void connect() throws SQLException {
            if (myConnect == null || myConnect.isClosed()) {
                myConnect = DriverManager.getConnection(url, username, password);
            }
        }

        public void addInquirer(String firstName, String lastName, String phoneNumber) throws SQLException {
            connect(); // Ensure connection before adding inquirer

            try (Connection connection = myConnect;
                 Statement maxIdStatement = connection.createStatement();
                 ResultSet resultSet = maxIdStatement.executeQuery("SELECT MAX(id) FROM Inquirer")) {

                int nextId = 1;
                if (resultSet.next()) {
                    nextId = resultSet.getInt(1) + 1; // Get the maximum id and increment by 1
                }

                try (PreparedStatement statement = connection.prepareStatement(
                        "INSERT INTO Inquirer (id, firstname, lastname, phoneNumber) VALUES (?, ?, ?, ?)")) {
                    statement.setInt(1, nextId);
                    statement.setString(2, firstName);
                    statement.setString(3, lastName);
                    statement.setString(4, phoneNumber);
                    statement.executeUpdate();
                    System.out.println("Inquirer added successfully.");
                }
            } catch (SQLException e) {
                System.err.println("Failed to add inquirer:");
                e.printStackTrace();
            }
        }

        public int getInquirerId(String firstName, String lastName, String phoneNumber) throws SQLException {
            connect(); // Ensure connection before getting ID

            try (Connection connection = myConnect;
                 PreparedStatement statement = connection.prepareStatement(
                         "SELECT id FROM Inquirer WHERE firstName = ? AND lastName = ? AND phoneNumber = ?")) {
                statement.setString(1, firstName);
                statement.setString(2, lastName);
                statement.setString(3, phoneNumber);
                ResultSet resultSet = statement.executeQuery();
                return resultSet.next() ? resultSet.getInt(1) : -1; // Return -1 if not found
            } catch (SQLException e) {
                System.err.println("Failed to get inquirer ID:");
                e.printStackTrace();
                return -1;
            }
        }

        public void logInquiry(int inquirerId, Date callDate, String details) throws SQLException {
            connect(); // Ensure connection before logging inquiry

            try (Connection connection = myConnect;
                 PreparedStatement getMaxIdStatement = connection.prepareStatement("SELECT MAX(id) FROM INQUIRY_LOG");
                 ResultSet resultSet = getMaxIdStatement.executeQuery()) {

                int maxId = 0;
                if (resultSet.next()) {
                    maxId = resultSet.getInt(1);
                }

                int newId = maxId + 1; // Increment the maximum ID

                try (PreparedStatement statement = connection.prepareStatement(
                        "INSERT INTO INQUIRY_LOG (id, inquirer, callDate, details) VALUES (?, ?, ?, ?)")) {
                    statement.setInt(1, newId);
                    statement.setInt(2, inquirerId);
                    statement.setDate(3, callDate);
                    statement.setString(4, details);
                    statement.executeUpdate();
                    System.out.println("Inquiry logged successfully.");
                } catch (SQLException e) {
                    System.err.println("Failed to log inquiry:");
                    e.printStackTrace();
                }
            } catch (SQLException e) {
                System.err.println("Failed to retrieve maximum ID:");
                e.printStackTrace();
            }
        }

        public void searchInquirersByName(String query) throws SQLException {
            connect(); // Ensure connection before searching
        
            try (Connection connection = myConnect;
                 PreparedStatement statement = connection.prepareStatement(
                         "SELECT * FROM Inquirer WHERE LOWER(firstName) LIKE ? OR LOWER(lastName) LIKE ?")) {
                statement.setString(1, "%" + query + "%");
                statement.setString(2, "%" + query + "%");
                try (ResultSet resultSet = statement.executeQuery()) {
                    System.out.println("Search results:");
                    while (resultSet.next()) {
                        int id = resultSet.getInt("id");
                        String firstName = resultSet.getString("firstName");
                        String lastName = resultSet.getString("lastName");
                        String phoneNumber = resultSet.getString("phoneNumber");
                        System.out.println("ID: " + id + ", Name: " + firstName + " " + lastName + ", Phone Number: " + phoneNumber);
                    }
                }
            } catch (SQLException e) {
                System.err.println("Failed to search for inquirer:");
                e.printStackTrace();
            }
        }
    }
}

        
