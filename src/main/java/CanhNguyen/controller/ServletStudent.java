package CanhNguyen.controller;

import CanhNguyen.dao.classes.ClassesDAO;
import CanhNguyen.dao.classes.IClassesDAO;
import CanhNguyen.dao.student.IStudentDAO;
import CanhNguyen.dao.student.StudentDAO;
import CanhNguyen.model.Classes;
import CanhNguyen.model.Student;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "ServletStudent", value = "/students")
public class ServletStudent extends HttpServlet {
    IClassesDAO classesDAO = new ClassesDAO();
    IStudentDAO studentDAO = new StudentDAO();
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action==null){
            action="";
        }
        try {
            switch (action) {
                case "add" :
                    addNewStudent(request,response);
                    break;
                case "edit":
                    editStudent(request,response);
                    break;
                case "delete":
                    deleteStudent(request,response);
                    break;
                case "addClass":
                    addNewClass(request,response);
                    break;
                case "editClass":
                    editClass(request,response);
                    break;
                case "deleteClass":
                    deleteClass(request,response);
                    break;
            }
        } catch (SQLException ex ){
            throw new ServletException(ex);
        }

    }

    private void deleteClass(HttpServletRequest request, HttpServletResponse response) throws SQLException {
        int id = Integer.parseInt(request.getParameter("id_class"));
        classesDAO.deleteClasses(id);
        RequestDispatcher dispatcher = request.getRequestDispatcher("classes/delete.jsp");
        try {
            dispatcher.forward(request,response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void deleteStudent(HttpServletRequest request, HttpServletResponse response) throws SQLException {
        int id = Integer.parseInt(request.getParameter("id"));
        studentDAO.deleteStudent(id);
        RequestDispatcher dispatcher = request.getRequestDispatcher("student/delete.jsp");
        try {
            dispatcher.forward(request,response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void editClass(HttpServletRequest request, HttpServletResponse response) throws SQLException {
        String className = request.getParameter("className");
        String classDescription = request.getParameter("classDescription");
        int id = Integer.parseInt(request.getParameter("classId"));
        Classes classes = new Classes(id,className,classDescription);
        classesDAO.updateClasses(classes);
        RequestDispatcher dispatcher = request.getRequestDispatcher("classes/edit.jsp");
        try {
            dispatcher.forward(request,response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void editStudent(HttpServletRequest request, HttpServletResponse response) throws SQLException {
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String address = request.getParameter("address");
        String className = request.getParameter("className");
        int id = Integer.parseInt(request.getParameter("id"));
        Student student = new Student(id,firstName,lastName,address,className);
        studentDAO.updateStudent(student);
        RequestDispatcher dispatcher = request.getRequestDispatcher("student/edit.jsp");
        try {
            dispatcher.forward(request,response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void addNewClass(HttpServletRequest request, HttpServletResponse response) {
        String name= request.getParameter("name");
        String description = request.getParameter("description");
        Classes newClass = new Classes(name,description);
        try {
            classesDAO.insertClasses(newClass);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        RequestDispatcher dispatcher = request.getRequestDispatcher("classes/add.jsp");
        try {
            dispatcher.forward(request,response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void addNewStudent(HttpServletRequest request, HttpServletResponse response) throws SQLException {
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String address = request.getParameter("address");
        int id_class = Integer.parseInt(request.getParameter("className"));
        Student newStudent = new Student(firstName,lastName,address,id_class);
        studentDAO.insertStudent(newStudent);
        RequestDispatcher dispatcher = request.getRequestDispatcher("student/add.jsp");
        try {
            dispatcher.forward(request,response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action==null){
            action="";
        }
        switch (action){
            case "add" :
                showFormAddStudent(request,response);
                break;
            case "edit":
                showFormEditStudent(request,response);
                break;
            case "delete":
                showFormDeleteStudent(request,response);
                break;
            case "class":
                listClass(request,response);
                break;
            case "addClass":
                showFormAddClass(request,response);
            case "editClass":
                showFormEditClass(request,response);
                break;
            case "deleteClass":
                showDeleteFormClass(request,response);
                break;
            default :
                listStudent(request,response);
                break;
        }
    }

    private void showDeleteFormClass(HttpServletRequest request, HttpServletResponse response) {
        int id = Integer.parseInt(request.getParameter("classId"));
        Classes deleteClass = classesDAO.selectClasses(id);
        RequestDispatcher dispatcher = request.getRequestDispatcher("classes/delete.jsp");
        request.setAttribute("classes",deleteClass);
        try {
            dispatcher.forward(request,response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void showFormDeleteStudent(HttpServletRequest request, HttpServletResponse response) {
        int id = Integer.parseInt(request.getParameter("id"));
        Student deleteStudent = studentDAO.selectStudent(id);
        RequestDispatcher dispatcher = request.getRequestDispatcher("student/delete.jsp");
        request.setAttribute("student",deleteStudent);
        try {
            dispatcher.forward(request,response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void showFormAddClass(HttpServletRequest request, HttpServletResponse response) {
        RequestDispatcher dispatcher = request.getRequestDispatcher("classes/add.jsp");
        try {
            dispatcher.forward(request,response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void showFormAddStudent(HttpServletRequest request, HttpServletResponse response) {
        RequestDispatcher dispatcher = request.getRequestDispatcher("student/add.jsp");
        try {
            dispatcher.forward(request,response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void showFormEditClass(HttpServletRequest request, HttpServletResponse response) {
        int id = Integer.parseInt(request.getParameter("id_class"));
        Classes existingClass = classesDAO.selectClasses(id);
        RequestDispatcher dispatcher = request.getRequestDispatcher("classes/edit.jsp");
        request.setAttribute("classes",existingClass);
        try {
            dispatcher.forward(request,response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void showFormEditStudent(HttpServletRequest request, HttpServletResponse response) {
        int id = Integer.parseInt(request.getParameter("id"));
        Student existingStudent = studentDAO.selectStudent(id);
        RequestDispatcher dispatcher =request.getRequestDispatcher("student/edit.jsp");
        request.setAttribute("student",existingStudent);
        try {
            dispatcher.forward(request,response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void listClass(HttpServletRequest request, HttpServletResponse response) {
        List<Classes> classesList = classesDAO.selectAllClasses();
        request.setAttribute("classesList",classesList);
        RequestDispatcher dispatcher = request.getRequestDispatcher("classes/show.jsp");
        try {
            dispatcher.forward(request,response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void listStudent(HttpServletRequest request, HttpServletResponse response) {
        List<Student> studentList = studentDAO.selectAllStudent();
        request.setAttribute("student",studentList);
        RequestDispatcher dispatcher = request.getRequestDispatcher("student/show.jsp");
        try {
            dispatcher.forward(request,response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
