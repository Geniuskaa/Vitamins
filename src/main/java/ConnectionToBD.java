import java.sql.*;
import java.util.Scanner;

public class ConnectionToBD {
    private final String URL = "jdbc:sqlite:products";
    private Statement statement;
    private java.sql.Connection connection;


    public void tableCreationSQLite() throws ClassNotFoundException, SQLException {
        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:products");

            statement = connection.createStatement();
            String sql = "CREATE TABLE food_and_energy_values " +
                    "(id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
                    "name_of_product VARCHAR(40)  NOT NULL, " +
                    "fats DOUBLE DEFAULT 0.0," +
                    "proteins DOUBLE DEFAULT 0.0," +
                    "carbohydrates DOUBLE DEFAULT 0.0," +
                    "sucrose DOUBLE DEFAULT 0.0," +
                    "powerValueCcal DOUBLE DEFAULT 0.0)";
            statement.executeUpdate(sql);
            statement.close();
            connection.close();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
    }

    public void addProductDataSQLite(FoodValue object) throws SQLException, ClassNotFoundException {
        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:products");
            connection.setAutoCommit(false);

            statement = connection.createStatement();

            Scanner in = new Scanner(System.in);
            System.out.println("Введите название продукта: ");
            String nameOfProduct = in.nextLine();

            String sql = "INSERT INTO food_and_energy_values(name_of_product, fats, proteins, carbohydrates, " +
                    "sucrose, powerValueCcal) VALUES(?,?,?,?,?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, nameOfProduct);
            preparedStatement.setDouble(2, object.getFats());
            preparedStatement.setDouble(3, object.getProteins());
            preparedStatement.setDouble(4, object.getCarbohydrates());
            preparedStatement.setDouble(5, object.getSucrose());
            preparedStatement.setDouble(6, object.getPowerValueCcal());
            preparedStatement.executeUpdate();

            statement.close();
            connection.commit();
            connection.close();
        }catch (Exception e){
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
    }









}
