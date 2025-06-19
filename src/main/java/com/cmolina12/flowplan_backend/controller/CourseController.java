package com.cmolina12.flowplan_backend.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.cmolina12.flowplan_backend.service.CourseService; // Importing the CourseService to use its methods for fetching course data from the API.
import com.cmolina12.flowplan_backend.models.ApiCourse; // Importing the ApiCourse model to represent the course data returned by the API.
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import com.cmolina12.flowplan_backend.domain.Course; // Importing the Course domain model to represent the course data in a structured way.
import com.cmolina12.flowplan_backend.domain.Section; // Importing the Section domain model to represent sections of a course.
import java.util.List; // Importing List to handle collections of courses.


@RestController // This annotation indicates that this class is a REST controller, which means it will handle HTTP requests and return responses in a RESTful manner.
@RequestMapping("/api/courses") // This annotation maps HTTP requests to /api/courses to this controller, allowing it to handle requests related to courses.
public class CourseController {
    
    private final CourseService courseService; // courseService is an instance of CourseService, which is used to fetch course data from the API.

    /**
     * Constructor for CourseController.
     * 
     * @param courseService the CourseService instance used to fetch course data.
     */

    public CourseController(CourseService courseService) {
        this.courseService = courseService; // The CourseService instance is injected into the controller, allowing it to use the service methods to fetch course data.
    }

    /**
     * Fetches courses based on the provided name input.
     * 
     * @param nameInput the name input to filter courses.
     * @return a ResponseEntity containing an array of ApiCourse objects representing the courses.
     */

    @GetMapping
    public ResponseEntity<ApiCourse[]> getCourses(
        @RequestParam("nameInput") String nameInput
    ) {
        ApiCourse[] courses = courseService.fetchRawSections(nameInput);
        if (courses == null || courses.length == 0) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(courses);
    }

    /**
     * Fetches domain courses based on the provided name input.
     * 
     * @param nameInput the name input to filter domain courses.
     * @return a ResponseEntity containing a list of Course objects representing the domain courses.
     */

    @GetMapping("/domain")
    public ResponseEntity<List<Course>> getDomainCourses(
        @RequestParam("nameInput") String nameInput
    ) {
        List<Course> courses = courseService.getDomainCourses(nameInput);
        if (courses.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(courses);
    }

    @GetMapping("/{code}/sections")
    public ResponseEntity<List<Section>> getSectionsByCode(@PathVariable("code") String code) {
  
        List<Section> sections = courseService.findSectionsByCourseCode(code);
        if (sections.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(sections);
    }
    

}
