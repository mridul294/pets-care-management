import java.sql.*;

public class PetsCareDatabase {
    // Database URL, username, and password
    private static final String DB_URL = "jdbc:mysql://localhost:3306/petscare";
    private static final String USER = "root"; // Replace with your MySQL username
    private static final String PASS = "password"; // Replace with your MySQL password

    // JDBC objects
    private Connection connection;

    // Constructor to establish a connection
    public PetsCareDatabase() {
        try {
            connection = DriverManager.getConnection(DB_URL, USER, PASS);
            System.out.println("Connected to the database successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Method to add a pet
    public void addPet(String name, String type, int age) {
        String query = "INSERT INTO pets (name, type, age) VALUES (?, ?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, type);
            preparedStatement.setInt(3, age);
            preparedStatement.executeUpdate();
            System.out.println("Pet added successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Method to fetch all pets
    public void fetchAllPets() {
        String query = "SELECT * FROM pets";
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            while (resultSet.next()) {
                System.out.println("ID: " + resultSet.getInt("id") +
                        ", Name: " + resultSet.getString("name") +
                        ", Type: " + resultSet.getString("type") +
                        ", Age: " + resultSet.getInt("age"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Method to update a pet's information
    public void updatePet(int id, String name, String type, int age) {
        String query = "UPDATE pets SET name = ?, type = ?, age = ? WHERE id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, type);
            preparedStatement.setInt(3, age);
            preparedStatement.setInt(4, id);
            preparedStatement.executeUpdate();
            System.out.println("Pet updated successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Method to delete a pet
    public void deletePet(int id) {
        String query = "DELETE FROM pets WHERE id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
            System.out.println("Pet deleted successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Main method for testing
    public static void main(String[] args) {
        PetsCareDatabase db = new PetsCareDatabase();

        // Add pets
        db.addPet("Buddy", "Dog", 3);
        db.addPet("Whiskers", "Cat", 2);

        // Fetch all pets
        db.fetchAllPets();

        // Update a pet
        db.updatePet(1, "Buddy", "Dog", 4);

        // Delete a pet
        db.deletePet(2);

        // Fetch all pets again
        db.fetchAllPets();
    }
}
