package org.sambasoft.login.services;

import org.sambasoft.login.entities.Course;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CourseService {

    List<Course> getAll();

    Course findById(Long id);

    void update(Long id, Course course);

    void delete(Long id);

    Long create(Course course);

    void removeInstructor(Long instructorId, Long courseId);

    void removeUser(Long usertId, Long courseId);
}