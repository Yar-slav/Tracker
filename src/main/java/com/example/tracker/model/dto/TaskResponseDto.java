package com.example.tracker.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TaskResponseDto {

    private Long id;
    private Long projectId;
    private String name;
    private String timeSpent;

//    public void setTimeSpentFromLocalTime(LocalTime timeSpent) {
//        this.timeSpent = String.format("%d:%02d", timeSpent.getHour(), timeSpent.getMinute());
//    }
//
//    public LocalTime getTimeSpentAsLocalTime() {
//        String[] parts = timeSpent.split(":");
//        int hours = Integer.parseInt(parts[0]);
//        int minutes = Integer.parseInt(parts[1]);
//        return LocalTime.of(hours, minutes);
//    }
}
