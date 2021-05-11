import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class ConnectionToBD {
    private final String USER_NAME = "root";
    private final String PASSWORD = "root";
    private final String URL = "jdbc:mysql://localhost:3306/products";
    private Statement statement;
    private java.sql.Connection connection;

    public void runDB() throws ClassNotFoundException {
        //Class.forName("com.mysql.cj.jdbc.Driver");
        try {
            connection = DriverManager.getConnection(URL, USER_NAME, PASSWORD);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            throw new RuntimeException();
        }

        try {
            statement = connection.createStatement();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            throw new RuntimeException();
        }

    }


    public void tableCreation() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        statement.executeUpdate("CREATE TABLE food_and_energy_values(id int auto_increment primary key," +
                "name_of_product varchar(30) not null, " +
                "fats double default 0.0," +
                "proteins double default 0.0," +
                "carbohydrates double default 0.0," +
                "sucrose double default 0.0," +
                "powerValueCcal double default 0.0)");
    }

    public void addProductData(FoodValue object) throws SQLException {
        Scanner in = new Scanner(System.in);
        System.out.println("Введите название продукта: ");
        String nameOfProduct = in.nextLine();

        String sql = "INSERT INTO food_and_energy_values(name_of_product, fats, proteins, carbohydrates, " +
                "sucrose, powerValueCcal) VALUES(?,?,?,?,?,?)";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1,nameOfProduct);
        preparedStatement.setDouble(2,object.fats);
        preparedStatement.setDouble(3,object.proteins);
        preparedStatement.setDouble(4,object.carbohydrates);
        preparedStatement.setDouble(5,object.sucrose);
        preparedStatement.setDouble(6,object.powerValueCcal);

        preparedStatement.executeUpdate();
    }









}
