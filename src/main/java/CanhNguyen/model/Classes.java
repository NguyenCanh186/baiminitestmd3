package CanhNguyen.model;

import java.util.List;

public class Classes {
    int id_classes;
    String name;
    String description;

    public Classes(int id_classes, String name, String description) {
        this.id_classes = id_classes;
        this.name = name;
        this.description = description;
    }

    public Classes(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public Classes() {
    }

    public int getId_classes() {
        return id_classes;
    }

    public void setId_classes(int id_classes) {
        this.id_classes = id_classes;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
