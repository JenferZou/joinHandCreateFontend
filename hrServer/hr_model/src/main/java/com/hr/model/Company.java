package com.hr.model;

/**
 * ClassName: Company
 * Description:
 * date: 2023/3/13 19:10
 *
 * @author: Jenf
 * @version:
 * @since JDK 17
 */
public class Company {
    private String name;

    private String id;

    private String post;

    private Double salary;

    private Integer timeperiod;

    private Double workinghours;

    private String workrequire;

    private String project;

    public Company() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPost() {
        return post;
    }

    public void setPost(String post) {
        this.post = post;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    public Integer getTimeperiod() {
        return timeperiod;
    }

    public void setTimeperiod(Integer timeperiod) {
        this.timeperiod = timeperiod;
    }

    public Double getWorkinghours() {
        return workinghours;
    }

    public void setWorkinghours(Double workinghours) {
        this.workinghours = workinghours;
    }

    public String getWorkrequire() {
        return workrequire;
    }

    public void setWorkrequire(String workrequire) {
        this.workrequire = workrequire;
    }

    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
    }


    @Override
    public String toString() {
        return "Company{" +
                "name='" + name + '\'' +
                ", id='" + id + '\'' +
                ", post='" + post + '\'' +
                ", salary=" + salary +
                ", timeperiod=" + timeperiod +
                ", workinghours=" + workinghours +
                ", workrequire='" + workrequire + '\'' +
                ", project='" + project + '\'' +
                '}';
    }
}
