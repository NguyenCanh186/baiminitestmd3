package CanhNguyen.dao.classes;

import CanhNguyen.config.ConnectionJDBC;
import CanhNguyen.model.Classes;
import CanhNguyen.model.Student;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static CanhNguyen.config.ConnectionJDBC.getConnection;

public class ClassesDAO implements IClassesDAO {

    public static final String INSERT_INTO_CLASSES = "insert into classes values (?,?,?);";
    public static final String SELECT_FROM_CLASSES = "select name, description from classes where id_class = ?;";
    public static final String SELECT_ALL_FROM_CLASSES = "select * from classes";
    public static final String SQL_DELETE_CLASSES = "delete from classes where id_class=?";
    public static final String SQL_EDIT_CLASS = "update classes set name = ? , description = ? where id = ?";
    @Override
    public void insertClasses(Classes classes) throws SQLException {
        System.out.println(INSERT_INTO_CLASSES);
        try(Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_INTO_CLASSES)){
            preparedStatement.setInt(1,classes.getId_classes());
            preparedStatement.setString(2,classes.getName());
            preparedStatement.setString(3,classes.getDescription());
            System.out.println(preparedStatement);
            preparedStatement.executeUpdate();
        }
    }

    @Override
    public Classes selectClasses(int id) {
        Classes classes = null;
        try(Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_FROM_CLASSES)){
            preparedStatement.setInt(1,id);
            ResultSet rs =  preparedStatement.executeQuery();
            while (rs.next()){
                String className = rs.getString("name");
                String classDescription = rs.getString("description");
                classes = new Classes(id,className,classDescription);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return classes;
    }

    @Override
    public List<Classes> selectAllClasses() {
        List<Classes> classes = new ArrayList<>();
        try(Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_FROM_CLASSES)){
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()){
                int id = rs.getInt("id_class");
                String className = rs.getString("name");
                String classDescription = rs.getString("description");
                classes.add(new Classes(id,className,classDescription));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return classes;
    }

    @Override
    public boolean deleteClasses(int id) throws SQLException {
        boolean rowDeleted;
        try(Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_DELETE_CLASSES)){
            preparedStatement.setInt(1,id);
            rowDeleted = preparedStatement.executeUpdate() > 0;
        }
        return rowDeleted;
    }

    @Override
    public boolean updateClasses(Classes classes) throws SQLException {
        boolean rowUpdated;
        try(Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_EDIT_CLASS)){
            preparedStatement.setString(1,classes.getName());
            preparedStatement.setString(2,classes.getDescription());
            preparedStatement.setInt(3,classes.getId_classes());
            rowUpdated = preparedStatement.executeUpdate() >0 ;
        }
        return rowUpdated;
    }
}
