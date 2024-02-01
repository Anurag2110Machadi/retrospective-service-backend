package com.sis.retrospectiveservice.model;

import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class Retrospective {
    private Long id;
    private String name;
    private String summary;
    private LocalDate date;
    private List<String> participants;
    private List<FeedbackItem> feedbackItems;
}
