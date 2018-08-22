package org.sambasoft.login.services;

import org.sambasoft.login.entities.Course;
import org.sambasoft.login.entities.User;
import org.sambasoft.login.repositories.CourseRepository;
import org.sambasoft.login.repositories.UserRepository;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class CourseServiceImpl implements CourseService{

    private final CourseRepository courseRepository;
    private final UserRepository userRepository;
    //private final InstructorRepository instructorRepository;


    public CourseServiceImpl(CourseRepository courseRepository, UserRepository userRepository) {
        this.courseRepository = courseRepository;
        this.userRepository = userRepository;
    }

    @Override
    public List<Course> getAll(){
        List<Course> courseList = new ArrayList<>();
        courseRepository.findAll().iterator().forEachRemaining(courseList::add);
        return courseList;
    }

    @Override
    public Course findById(Long id){
        Optional<Course> courseOptional = courseRepository.findById(id);

        if (!courseOptional.isPresent()) {
            throw new RuntimeException("Course Not Found!");
        }

        return courseOptional.get();

    }

    @Override
    public Long create(Course course){
        courseRepository.save(course);
        return course.getCourseid();
    }

    @Override
    public  void update(Long id, Course course){
        Course currentCourse = findById(id);
        currentCourse.setName(course.getName());
        currentCourse.setDescription(course.getDescription());
        currentCourse.setLocation(course.getLocation());
        currentCourse.setPrice(course.getPrice());
        currentCourse.setMaxNumberOfParticipants(course.getMaxNumberOfParticipants());
        //currentCourse.setInstructor(course.getInstructor());
        //currentCourse.setStartDate(course.getStartDate());
        //currentCourse.setEndDate(course.getEndDate());
        courseRepository.save(currentCourse);
    }

    @Override
    public void delete(Long id){
        //remove Student from all Courses
        Optional<Course> courseOptional = courseRepository.findById(id);
        Course course = courseOptional.get();
        Set<User> users = course.getUsers();

        for (User user : users)
        {
            user.removeCourse(course);
            userRepository.save(user);
        }

        //deleteStudent
        courseRepository.deleteById(id);
    }

    @Override
    public void removeInstructor(Long instructorId, Long courseId){
        //betreffenden Instructor rausfiltern
        Optional<User> instructorOptional = userRepository.findById(instructorId);
        if(!instructorOptional.isPresent()){
            throw new RuntimeException("Instructor not Found");
        }

        //betreffenden Kurs rausfiltern
        Optional<Course> courseOptional = courseRepository.findById(courseId);
        if (!courseOptional.isPresent()) {
            throw new RuntimeException("Course Not Found!");
        }

        Course course = courseOptional.get();
        User instructor = instructorOptional.get();

        course.removeInstructor(instructor);
        instructor.removeCourse(course);

        courseRepository.save(course);
        userRepository.save(instructor);
    }

    @Override
    public void removeUser(Long studentId, Long courseId){
        //betreffenden Student rausfiltern
        Optional<User> studentOptional = userRepository.findById(studentId);
        if(!studentOptional.isPresent()){
            throw new RuntimeException("Student not Found");
        }

        //betreffenden Kurs rausfiltern
        Optional<Course> courseOptional = courseRepository.findById(courseId);
        if (!courseOptional.isPresent()) {
            throw new RuntimeException("Course Not Found!");
        }

        Course course = courseOptional.get();
        User user = studentOptional.get();

        course.removeStudent(user);
        user.removeCourse(course);

        courseRepository.save(course);
        userRepository.save(user);
    }

}