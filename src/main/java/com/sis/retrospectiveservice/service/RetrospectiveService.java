package com.sis.retrospectiveservice.service;

import com.sis.retrospectiveservice.model.FeedbackItem;
import com.sis.retrospectiveservice.model.Retrospective;

import java.util.List;

public interface RetrospectiveService {
    Retrospective createRetrospective(Retrospective retrospective);
    Retrospective addFeedbackItem(Long id, FeedbackItem feedbackItem);
    Retrospective updateFeedbackItem(Long id, Long itemId, FeedbackItem feedbackItem);
    List<Retrospective> getAllRetrospectives(int page, int pageSize);
    List<Retrospective> searchRetrospectivesByDate(String date, int page, int pageSize);
}
