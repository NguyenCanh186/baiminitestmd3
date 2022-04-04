package CanhNguyen.model;

import java.util.List;

public class Student {
    int id;
    String firstName;
    String lastName;
    String address;
    int id_class;
    String classesName;

    public Student() {
    }

    public Student(String firstName, String lastName, String address, String classesName) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.classesName = classesName;
    }

    public Student(int id, String firstName, String lastName, String address, String classesName) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.classesName = classesName;
    }

    public Student(String firstName, String lastName, String address) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
    }

    public Student(String firstName, String lastName, String address, int id_class) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.id_class = id_class;
    }

    public Student(int id, String firstName, String lastName, String address, int id_class) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.id_class = id_class;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getId_class() {
        return id_class;
    }

    public void setId_class(int id_class) {
        this.id_class = id_class;
    }

    public String getClassesName() {
        return classesName;
    }

    public void setClassesName(String classesName) {
        this.classesName = classesName;
    }
}
