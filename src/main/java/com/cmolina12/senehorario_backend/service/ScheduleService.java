package com.cmolina12.senehorario_backend.service;
import java.util.List;
import java.util.ArrayList;
import org.springframework.stereotype.Service;

import com.cmolina12.senehorario_backend.domain.Meeting;
import com.cmolina12.senehorario_backend.domain.Section;



@Service
public class ScheduleService {

    /**
     * This method generates all possible schedules based on the provided candidates.
     * It uses backtracking to explore all combinations of sections from different courses.
     *
     * @param candidates List of lists of Section objects representing the courses and their sections.
     * @return List of lists of Section objects representing all valid schedules.
     */

    public List<List<Section>> generateAllSchedules(List<List<Section>> candidates) {
       List<List<Section>> results = new ArrayList<>();
        backtrack(candidates, 0, new ArrayList<>(), results);
        return results;
    }

    /**
     * This method verifies the correctness of the parameters before generating schedules.
     *
     * @param candidates List of lists of Section objects representing the courses and their sections.
     */

    public void verifyCorrectParameters(List<List<Section>> candidates) {

        // Check if candidates is null or empty

        if (candidates == null || candidates.isEmpty()) {
            throw new IllegalArgumentException("Candidates list cannot be null or empty");
        }

        for (List<Section> course : candidates) {

            // Check if each course is null or empty
            if (course == null || course.isEmpty()) {
                throw new IllegalArgumentException("Each course must have at least one section");
            }


            for (Section section : course) {

                // Check if section is null, meetings are null or empty
                if (section == null || section.getMeetings() == null || section.getMeetings().isEmpty()) {
                    throw new IllegalArgumentException("Each section must have at least one meeting");
                }

                if (section.getNrc() == null || section.getNrc().isEmpty()) {
                    throw new IllegalArgumentException("Each section must have a valid NRC");
                }

                if (section.getSectionId() == null || section.getSectionId().isEmpty()) {
                    throw new IllegalArgumentException("Each section must have a valid section ID");
                }

                if (section.getTerm() == null || section.getTerm().isEmpty()) {
                    throw new IllegalArgumentException("Each section must have a valid term");
                }

                if (section.getPtrm() == null || section.getPtrm().isEmpty()) {
                    throw new IllegalArgumentException("Each section must have a valid ptrm");
                }

                if (section.getCampus() == null || section.getCampus().isEmpty()) {
                    throw new IllegalArgumentException("Each section must have a valid campus");
                }

                // Check if meetings are valid
                for (Meeting meeting : section.getMeetings()){

                    if (meeting.getDay() == null) {
                        throw new IllegalArgumentException("Each meeting must have a valid day");
                    }
                    
                    if (meeting.getStart() == null || meeting.getEnd() == null) {
                        throw new IllegalArgumentException("Each meeting must have valid start and end times");
                    }
                    
                    if (meeting.getStart().isAfter(meeting.getEnd())) {
                        throw new IllegalArgumentException("Meeting start time cannot be after end time");
                    }

                }

                // Check if professors are valid
                if (section.getProfessors() == null || section.getProfessors().isEmpty()) {
                    throw new IllegalArgumentException("Each section must have at least one professor");
                }
                
            
            }
        }
    }

    /**
     * This method performs backtracking to find all valid schedules.
     *
     * @param candidates List of lists of Section objects representing the courses and their sections.
     * @param idx Current index in the candidates list.
     * @param current Current schedule being built.
     * @param results List to store all valid schedules found.
     */

    public void backtrack(List<List<Section>> candidates, int idx, List<Section> current, List<List<Section>> results){

        if (idx == candidates.size()){
            results.add(new ArrayList<>(current));
            return;
        }

        for (Section sec : candidates.get(idx)){

            boolean hasConflict = false;
            for (Section chosen : current){
                if (conflict(chosen, sec)){
                    hasConflict = true;
                    break;
                }
            }

            if (hasConflict) continue;

            // Add the section
            current.add(sec);

            // Next course

            backtrack(candidates, idx + 1, current, results);

            // Remove the last course

            current.remove(current.size()-1);


        }
    }

    /**
     * This method checks if there is a conflict between two sections based on their meeting times.
     *
     * @param a First section to compare.
     * @param b Second section to compare.
     * @return true if there is a conflict, false otherwise.
     */

    private boolean conflict(Section a, Section b){

        for (Meeting m1 : a.getMeetings()){

            for (Meeting m2 : b.getMeetings()){

                if (m1.getDay() == m2.getDay() && 
                    m1.getStart().isBefore(m2.getEnd()) && 
                    m1.getEnd().isAfter(m2.getStart())) {
                    return true; // Conflict found
                }
            }
        }
        return false; // No conflict found
    }
}
