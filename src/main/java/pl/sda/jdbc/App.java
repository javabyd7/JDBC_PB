package pl.sda.jdbc;

import java.sql.*;

/**
 * Hello world!
 *
 */
public class App 
{
    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public App(){
        System.out.println("To jest drugi test");
    }



    public static void main( String[] args )
    {
        Connection connection = null;
        try {
           connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/employee_db?serverTimezone=UTC", "root", "password");
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        Statement statement = null;

        try {
            statement = connection.createStatement();
            String sql = "select * from employees";
            ResultSet resultSet = statement.executeQuery(sql);
            System.out.println(resultSet);
            while (resultSet.next()){
                System.out.println("Imie: " + resultSet.getString("firstName"));
            }

            String inert = "insert into employees (employeeNumber, LastName," +
                    "firstName, extension, email, officeCode, reportsTo, jobTitle)" +
                    "values (?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(inert);
            preparedStatement.setInt(1, 10001);
            preparedStatement.setString(2, "TestnoweImie");
            preparedStatement.setString(3, "Lucky");
            preparedStatement.setString(4, "xxx");
            preparedStatement.setString(5, "a@a.pl");
            preparedStatement.setInt(6, 6);
            preparedStatement.setInt(7, 1102);
            preparedStatement.setString(8, "HerOber");

            preparedStatement.executeUpdate();

            resultSet = statement.executeQuery(sql);


        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }


    }
}
