import java.sql.*;

public class PetsCareDatabase {
    // Database URL, username, and password
    private static final String DB_URL = "jdbc:mysql://localhost:3306/PetCareDB"; // Updated database name
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
        String query = "INSERT INTO Animals (animal_name, description) VALUES (?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, type); // Assuming 'type' is used as a description
            preparedStatement.executeUpdate();
            System.out.println("Pet added successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Method to fetch all pets
    public void fetchAllPets() {
        String query = "SELECT * FROM Animals";
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            while (resultSet.next()) {
                System.out.println("ID: " + resultSet.getInt("animal_id") +
                        ", Name: " + resultSet.getString("animal_name") +
                        ", Description: " + resultSet.getString("description"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Main method for testing
    public static void main(String[] args) {
        PetsCareDatabase db = new PetsCareDatabase();

        // Add pets
        db.addPet("Buddy", "Friendly dog", 3);
        db.addPet("Whiskers", "Playful cat", 2);

        // Fetch all pets
        db.fetchAllPets();
    }
}
