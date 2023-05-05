package cz.educanet;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Named;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@ApplicationScoped
@Named
public class StudentService {
    int id;
     String name;
     String birthday;
     int avgMark;
    public List<Student> getStudents() {
        try (
                Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/students?user=root&password=");
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(
                        "SELECT * FROM prg")
        ) {
            ArrayList<Student> students = new ArrayList<>();

            while (resultSet.next()) {
                students.add(new Student(
                        resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getInt(4)
                ));
            }
            return students;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void newStudent(String name, String birthday, int avgMark){
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/students?user=root&password=");

            PreparedStatement preparedStatement = connection.prepareStatement(
                    "INSERT INTO prg (name, birthday, avgMark) VALUES (?, ?, ?)"
            );

            preparedStatement.setString(1, name);
            preparedStatement.setString(2, birthday);
            preparedStatement.setInt(3, avgMark);


            preparedStatement.execute();

            preparedStatement.close();
            this.name = "";
            this.birthday = "";
            this.avgMark = 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void killStudent(int id){
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/students?user=root&password=");

            PreparedStatement preparedStatement = connection.prepareStatement(
                    "DELETE FROM prg WHERE id = ?"
            );

            preparedStatement.setInt(1, id);

            preparedStatement.execute();

            preparedStatement.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void editStudent(int id, String name, String birthday, int avgMark){
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/students?user=root&password=");

            PreparedStatement preparedStatement = connection.prepareStatement(
                    "UPDATE prg SET name = ?, birthday = ?, avgMark = ?  WHERE id = ?"
            );

            preparedStatement.setString(1, name);
            preparedStatement.setString(2, birthday);
            preparedStatement.setInt(3, avgMark);
            preparedStatement.setInt(4, id);


            preparedStatement.execute();

            preparedStatement.close();
            this.name = "";
            this.birthday = "";
            this.avgMark = 0;
            this.id = 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public float getAverageMark(){
        try (
                Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/students?user=root&password=");
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(
                        "SELECT AVG(avgMark) FROM prg")
        ) {
            resultSet.next();
            return (float) resultSet.getInt(1);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public int getAvgMark() {
        return avgMark;
    }

    public void setAvgMark(int avgMark) {
        this.avgMark = avgMark;
    }
}
