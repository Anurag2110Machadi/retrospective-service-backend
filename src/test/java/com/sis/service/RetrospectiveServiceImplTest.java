package com.sis.service;

import com.sis.retrospectiveservice.model.FeedbackItem;
import com.sis.retrospectiveservice.model.Retrospective;
import com.sis.retrospectiveservice.repository.RetrospectiveRepository;
import com.sis.retrospectiveservice.service.RetrospectiveServiceImpl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class RetrospectiveServiceImplTest {

    @Mock
    private RetrospectiveRepository retrospectiveRepository;

    @InjectMocks
    private RetrospectiveServiceImpl retrospectiveService;

    @Test
    void createRetrospective() {
        Retrospective inputRetrospective = new Retrospective();
        when(retrospectiveRepository.createRetrospective(any(Retrospective.class))).thenReturn(inputRetrospective);

        Retrospective createdRetrospective = retrospectiveService.createRetrospective(inputRetrospective);

        assertEquals(inputRetrospective, createdRetrospective);
        verify(retrospectiveRepository, times(1)).createRetrospective(eq(inputRetrospective));
    }

    @Test
    void addFeedbackItem() {
        Long retrospectiveId = 1L;
        FeedbackItem feedbackItem = new FeedbackItem();
        when(retrospectiveRepository.addFeedbackItem(eq(retrospectiveId), any(FeedbackItem.class))).thenReturn(new Retrospective());

        Retrospective updatedRetrospective = retrospectiveService.addFeedbackItem(retrospectiveId, feedbackItem);

        assertNotNull(updatedRetrospective);
        verify(retrospectiveRepository, times(1)).addFeedbackItem(eq(retrospectiveId), eq(feedbackItem));
    }

    @Test
    void updateFeedbackItem() {
        Long retrospectiveId = 1L;
        Long feedbackItemId = 1L;
        FeedbackItem feedbackItem = new FeedbackItem();
        when(retrospectiveRepository.updateFeedbackItem(eq(retrospectiveId), eq(feedbackItemId), any(FeedbackItem.class))).thenReturn(new Retrospective());

        Retrospective updatedRetrospective = retrospectiveService.updateFeedbackItem(retrospectiveId, feedbackItemId, feedbackItem);

        assertNotNull(updatedRetrospective);
        verify(retrospectiveRepository, times(1)).updateFeedbackItem(eq(retrospectiveId), eq(feedbackItemId), eq(feedbackItem));
    }

    @Test
    void getAllRetrospectives() {
        int page = 1;
        int pageSize = 10;
        when(retrospectiveRepository.getAllRetrospectives(eq(page), eq(pageSize))).thenReturn(List.of(new Retrospective()));

        List<Retrospective> retrospectives = retrospectiveService.getAllRetrospectives(page, pageSize);

        assertNotNull(retrospectives);
        assertEquals(1, retrospectives.size());
        verify(retrospectiveRepository, times(1)).getAllRetrospectives(eq(page), eq(pageSize));
    }

    @Test
    void searchRetrospectivesByDate() {
        String date = "2022-01-31";
        int page = 1;
        int pageSize = 10;
        when(retrospectiveRepository.searchRetrospectivesByDate(eq(date), eq(page), eq(pageSize))).thenReturn(List.of(new Retrospective()));

        List<Retrospective> retrospectives = retrospectiveService.searchRetrospectivesByDate(date, page, pageSize);

        assertNotNull(retrospectives);
        assertEquals(1, retrospectives.size());
        verify(retrospectiveRepository, times(1)).searchRetrospectivesByDate(eq(date), eq(page), eq(pageSize));
    }
}

