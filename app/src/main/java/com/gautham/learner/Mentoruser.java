package com.gautham.learner;

public class Mentoruser {
    private String mentorname;
    private String schoollicense;
    private String teachingsubjects;
    private String id;
    private String type;


    public Mentoruser(String mentorname, String schoollicense, String teachingsubjects, String type,String id) {
        this.mentorname = mentorname;
        this.schoollicense = schoollicense;
        this.teachingsubjects = teachingsubjects;
        this.id = id;
        this.type = type;
    }

    public Mentoruser(){

    }

    public String getMentorname() {
        return mentorname;
    }

    public void setMentorname(String mentorname) {
        this.mentorname = mentorname;
    }

    public String getSchoollicense() {
        return schoollicense;
    }

    public void setSchoollicense(String schoollicense) {
        this.schoollicense = schoollicense;
    }

    public String getTeachingsubjects() {
        return teachingsubjects;
    }


    public void setTeachingsubjects(String teachingsubjects) {
        this.teachingsubjects = teachingsubjects;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
