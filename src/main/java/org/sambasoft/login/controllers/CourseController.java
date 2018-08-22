package org.sambasoft.login.controllers;

import org.sambasoft.login.entities.Course;
import org.sambasoft.login.services.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
public class CourseController {
    @Autowired
    private CourseService courseService;

    @RequestMapping({"/courses"})
    public String showAllCourses(Model model)
    {
        List<Course> courses = (List<Course>) courseService.getAll();
        model.addAttribute("courses", courses);
        return "courses/allCourses";
    }

    @RequestMapping("/course/details/{id}")
    public String showCourseDetails(@PathVariable("id") Long courseId, Model model){
        Course course = courseService.findById(courseId);
        model.addAttribute("course", course);
        return "courses/courseDetails";
    }

    @RequestMapping("/course/new")
    public String addCourse(Model model)
    {
        model.addAttribute("course", new Course());
        return "courses/newCourse";
    }

    @RequestMapping(value = "/course/save", method = RequestMethod.POST)
    public String saveCourse(Course course)
    {
        courseService.create(course);
        return "redirect:/";
    }

    /*edit a single Course*/
    @GetMapping("/edit/course/{id}")
    public String editCourse(@PathVariable(value = "id") Long courseId, Model model) {
        Course course = courseService.findById(courseId);
        model.addAttribute("course", course);
        return "courses/editCourse";
    }

    /*---Update a course by id---*/
    @RequestMapping(path = "/course/{id}", method = RequestMethod.POST)
    public String updateCourse(@PathVariable("id") long id, Course course) {
        courseService.update(id, course);
        //return "redirect:/edit/course/" + id;
        return "redirect:/showAllCourses";
    }

    @RequestMapping(value = "/course/delete/{id}", method = RequestMethod.GET)
    public String deleteCourse(@PathVariable("id") Long courseId, Model model)
    {
        courseService.delete(courseId);
        return "redirect:/showAllCourses";
    }

    @RequestMapping(path = "/course/{courseId}/removeInstructor/{instructorId}", method = RequestMethod.GET)
    public String removeInstructor(@PathVariable(value = "courseId") long courseId,
                                   @PathVariable(value = "instructorId") long instructorId,
                                   Model model)
    {
        //removes Course with id=courseId from the instructors's courseList
        courseService.removeInstructor(instructorId, courseId);

        return "redirect:/edit/course/" + courseId;
    }

    @RequestMapping(path = "/course/{courseId}/removeUser/{studentId}", method = RequestMethod.GET)
    public String removeUserFromCourse(@PathVariable(value = "courseId") long courseId,
                                          @PathVariable(value = "userId") long userId,
                                          Model model)
    {
        //removes Course with id=courseId from the instructors's courseList
        courseService.removeUser(userId, courseId);

        return "redirect:/edit/course/" + courseId;
    }
}