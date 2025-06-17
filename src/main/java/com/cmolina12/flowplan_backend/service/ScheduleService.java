package com.cmolina12.flowplan_backend.service;
import com.cmolina12.flowplan_backend.domain.Section;
import java.util.List;

import org.springframework.web.servlet.FlashMapManager;

import java.util.ArrayList;

import com.cmolina12.flowplan_backend.domain.Meeting;

public class ScheduleService {

    // All schedule generation

    public List<List<Section>> generateAllSchedules(List<List<Section>> candidates) {
       List<List<Section>> results = new ArrayList<>();
        backtrack(candidates, 0, new ArrayList<>(), results);
        return results;
    }

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
