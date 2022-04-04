package CanhNguyen.dao.student;
import java.sql.SQLException;
import java.util.List;

import CanhNguyen.dao.IServiceDAO;
import CanhNguyen.model.Classes;
import CanhNguyen.model.Student;

public interface IStudentDAO{
    void insertStudent(Student student) throws SQLException;

    Student selectStudent(int id);

    List<Student> selectAllStudent();

    boolean deleteStudent(int id) throws SQLException;

    boolean updateStudent(Student student) throws SQLException;
}
