package org.sambasoft.login.entities;

import java.util.Date;
import java.util.Set;
import javax.persistence.*;

@Entity
public class Course {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private long courseid;

    @Column(name="coursename")
    private String name;

    private String description;
    private double price;
    private int maxNumberOfParticipants;
    private String location;

    private Date startDate;
    private Date endDate;
    //private String instructor;

    @ManyToMany(mappedBy = "courses")
    private Set<User> users;


    @ManyToMany(mappedBy = "courses")
    private Set<User> instructors;

    public Course() {
    }

    /*Constructor for @Beans*/
    public Course(String name) {
        this.name = name;
    }

    public Course(String name, Date startDate, Date endDate, Set<User> users,
                  Set<User> instructors) {
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
        this.users = users;
        this.instructors = instructors;
    }

    public Course(String name, String description, double price,
                  int maxNumberOfParticipants, Date startDate, Date endDate,
                  Set<User> users, Set<User> instructors, String location) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.maxNumberOfParticipants = maxNumberOfParticipants;
        this.startDate = startDate;
        this.endDate = endDate;
        this.users = users;
        this.instructors = instructors;
        this.location = location;
    }

    public long getCourseid() {
        return courseid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    public boolean hasUser(User user) {
        for (User userInCourse: getUsers()) {
            if (userInCourse.getId() == user.getId()) {
                return true;
            }
        }
        return false;
    }

    public boolean hasInstructor(User instructor){
        for(User instructorOfCourse: getInstructors()){
            if(instructorOfCourse.getId() == instructor.getId()){
                return true;
            }
        }
        return false;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public void setCourseid(long courseid) {
        this.courseid = courseid;
    }

    public Set<User> getInstructors() {
        return instructors;
    }

    public void setInstructors(Set<User> instructors) {
        this.instructors = instructors;
    }

    public void removeStudent(User user)
    {
        if(hasUser(user)){
            this.users.remove(user);
        }
    }

    public void removeInstructor(User instructor){
        if(hasInstructor(instructor)){
            this.instructors.remove(instructor);
        }
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getMaxNumberOfParticipants() {
        return maxNumberOfParticipants;
    }

    public void setMaxNumberOfParticipants(int maxNumberOfParticipants) {
        this.maxNumberOfParticipants = maxNumberOfParticipants;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

}