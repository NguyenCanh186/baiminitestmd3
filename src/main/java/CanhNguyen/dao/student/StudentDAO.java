package CanhNguyen.dao.student;

import CanhNguyen.config.ConnectionJDBC;
import CanhNguyen.dao.classes.ClassesDAO;
import CanhNguyen.dao.classes.IClassesDAO;
import CanhNguyen.model.Classes;
import CanhNguyen.model.Student;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static CanhNguyen.config.ConnectionJDBC.getConnection;

public class StudentDAO implements IStudentDAO {

    public static final String SQL_INSERT_INTO_STUDENT = "insert into student (firstName, lastName ,address,id_class) values(?,?,?,?);";
    public static final String SQL_SELECT_FROM_STUDENT_CLASS = "select firstName, lastName ,address, c.name from student join classes c on student.class_id = c.id where student.id=?;";
    public static final String SQL_SELECT_ALL_STUDENT_CLASSNAME = "select id,firstName,lastName,address,c.class_Name from student join classes c on student.class_id = c.id;";
    public static final String SQL_DELETE_STUDENT_BY_ID = "delete from student where id=?";
    public static final String SQL_UPDATE_STUDENT = "update student join classes c on student.id = c.id set firstName = ? , lastName = ? , address = ? , c.name = ? where student.id =?;";
    public static final String SQL_INSER_INTO_CLASS_MANAGER = "insert into class_manager values (?,?)";
    public static final String SQL_DELETE_CLASS_MANAGER = "alter table class_manager drop constraint fk_htk_student_id where student_id = ?";

    @Override
    public void insertStudent(Student student) throws SQLException {
        System.out.println(SQL_INSERT_INTO_STUDENT);
        try(Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_INSERT_INTO_STUDENT, Statement.RETURN_GENERATED_KEYS);
            PreparedStatement preparedStatement1 = connection.prepareStatement(SQL_INSER_INTO_CLASS_MANAGER)){
            preparedStatement.setString(1,student.getFirstName());
            preparedStatement.setString(2,student.getLastName());
            preparedStatement.setString(3,student.getAddress());
            preparedStatement.setInt(4,student.getId_class());
            System.out.println(preparedStatement);
            int rowAffected = preparedStatement.executeUpdate();
            ResultSet rs = preparedStatement.getGeneratedKeys();
            int studentId = 0;
            if (rs.next())
                studentId = rs.getInt(1);
            if (rowAffected == 1){
                preparedStatement1.setInt(1,studentId);
                preparedStatement1.setInt(2,student.getId_class());
                preparedStatement1.executeUpdate();
            } else
                System.err.println("Id not found");
        }
    }


    @Override
    public Student selectStudent(int id) {
        Student student = null;
        try(Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_SELECT_FROM_STUDENT_CLASS)){
            preparedStatement.setInt(1,id);
            System.out.println(preparedStatement);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()){
                String firstName =rs.getString("firstName");
                String lastName =rs.getString("lastName");
                String address =rs.getString("address");
                String classes = rs.getString("classesName");
                student = new Student(firstName,lastName,address,classes);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return student;
    }

    @Override
    public List<Student> selectAllStudent() {
        List<Student> students = new ArrayList<>();
        try(Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_SELECT_ALL_STUDENT_CLASSNAME)){
            System.out.println(preparedStatement);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()){
                int id = rs.getInt("id");
                String firstName =rs.getString("firstName");
                String lastName =rs.getString("lastName");
                String address =rs.getString("address");
                String classes = rs.getString("classesName");
                students.add(new Student(id,firstName,lastName,address,classes));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }return students;
    }

    @Override
    public boolean deleteStudent(int id) throws SQLException {
        boolean rowDelete;
        try(Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_DELETE_STUDENT_BY_ID)){
            preparedStatement.setInt(1,id);
            rowDelete = preparedStatement.executeUpdate() > 0;
        }
        return rowDelete;
    }

    @Override
    public boolean updateStudent(Student student) throws SQLException {
        boolean rowUpdate;
        try(Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_UPDATE_STUDENT)){
            preparedStatement.setString(1,student.getFirstName());
            preparedStatement.setString(2,student.getLastName());
            preparedStatement.setString(3,student.getAddress());
            preparedStatement.setString(4,student.getClassesName());
            preparedStatement.setInt(5,student.getId());
            rowUpdate = preparedStatement.executeUpdate() > 0;
        }
        return rowUpdate;
    }
}
