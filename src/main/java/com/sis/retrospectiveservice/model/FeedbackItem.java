package com.sis.retrospectiveservice.model;

import lombok.Data;

@Data
public class FeedbackItem {
    private Long id;
    private String name;
    private String body;
    private FeedbackType feedbackType;
}
