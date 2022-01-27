package com.gautham.learner;

public class Studentsuser {
    private String id;
    private String studentname;
    private String subjects;
    private String type;

    public Studentsuser(String id, String studentname, String subjects, String type) {
        this.id = id;
        this.studentname = studentname;
        this.subjects = subjects;
        this.type = type;
    }
    public Studentsuser(){

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStudentname() {
        return studentname;
    }

    public void setStudentname(String studentname) {
        this.studentname = studentname;
    }

    public String getSubjects() {
        return subjects;
    }

    public void setSubjects(String subjects) {
        this.subjects = subjects;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
