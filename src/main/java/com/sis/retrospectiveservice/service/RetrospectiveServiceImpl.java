package com.sis.retrospectiveservice.service;

import com.sis.retrospectiveservice.model.FeedbackItem;
import com.sis.retrospectiveservice.model.Retrospective;
import com.sis.retrospectiveservice.repository.RetrospectiveRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RetrospectiveServiceImpl implements RetrospectiveService {

    @Autowired
    private RetrospectiveRepository retrospectiveRepository;

    @Override
    public Retrospective createRetrospective(Retrospective retrospective) {
        return retrospectiveRepository.createRetrospective(retrospective);
    }

    @Override
    public Retrospective addFeedbackItem(Long id, FeedbackItem feedbackItem) {
        return retrospectiveRepository.addFeedbackItem(id, feedbackItem);
    }

    @Override
    public Retrospective updateFeedbackItem(Long id, Long itemId, FeedbackItem feedbackItem) {
        return retrospectiveRepository.updateFeedbackItem(id, itemId, feedbackItem);
    }

    @Override
    public List<Retrospective> getAllRetrospectives(int page, int pageSize) {
        return retrospectiveRepository.getAllRetrospectives(page, pageSize);
    }

    @Override
    public List<Retrospective> searchRetrospectivesByDate(String date, int page, int pageSize) {
        return retrospectiveRepository.searchRetrospectivesByDate(date, page, pageSize);
    }
}
