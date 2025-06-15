package com.cmolina12.flowplan_backend.service;
import com.cmolina12.flowplan_backend.models.ApiCourse;
import org.springframework.web.client.RestTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class CourseService {
    
    private final RestTemplate restTemplate; // RestTemplate is used to make HTTP requests to the API to fetch course data.
    private final String apiBaseUrl; // apiBaseUrl is the base URL of the API that provides course information, which is used to construct the full URL for API requests.

    /**
     * Constructor for CourseService.
     * 
     * @param restTemplate the RestTemplate instance used to make HTTP requests.
     * @param apiBaseUrl the base URL of the API, injected from application properties.
     */

    public CourseService(RestTemplate restTemplate, @Value("${uniandes.api.base-url}") String apiBaseUrl) {
        this.restTemplate = restTemplate; // RestTemplate is injected to allow making HTTP requests to the API.
        this.apiBaseUrl = apiBaseUrl; // The base URL is injected from the application properties, allowing flexibility in configuration.
    }

    /**
     * Fetches raw course sections from the API based on the provided name input. This actually works the same way as if you were to use the search bar in the website.
     * 
     * @param nameInput the name input to filter course sections.
     * @return an array of ApiCourse objects representing the course sections.
     */

    public ApiCourse[] fetchRawSections(String nameInput) {
        String url = apiBaseUrl
                   + "?term=&ptrm=&prefix=&attr=&nameInput=" // The URL is constructed to include the base URL, and query parameters for term, ptrm, prefix, attr, and nameInput.
                   + nameInput.toUpperCase(); // The URL is constructed to include the base URL, and query parameters for term, ptrm, prefix, attr, and nameInput INITIALLY. The nameInput is converted to uppercase to match the expected format in the API. Additional query parameters can be added after the nameInput if needed.
        
        // The RestTemplate is used to make a GET request to the constructed URL, and the response is expected to be an array of ApiCourse objects.
        return restTemplate.getForObject(url, ApiCourse[].class);
    }

}
