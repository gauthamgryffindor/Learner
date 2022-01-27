package com.gautham.learner;

public class Blog {
    private String id;
    private String title;
    private String blog;
    private String links;
    private String date;
    private String subjects;

    public Blog(String id, String title, String blog, String links, String date, String subjects) {
        this.id = id;
        this.title = title;
        this.blog = blog;
        this.links = links;
        this.date = date;
        this.subjects = subjects;
    }
    public Blog(){

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBlog() {
        return blog;
    }

    public void setBlog(String blog) {
        this.blog = blog;
    }

    public String getLinks() {
        return links;
    }

    public void setLinks(String links) {
        this.links = links;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getSubjects() {
        return subjects;
    }

    public void setSubjects(String subjects) {
        this.subjects = subjects;
    }
}
