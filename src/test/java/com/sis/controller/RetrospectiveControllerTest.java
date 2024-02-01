package com.sis.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sis.retrospectiveservice.controller.RetrospectiveController;
import com.sis.retrospectiveservice.model.FeedbackItem;
import com.sis.retrospectiveservice.model.Retrospective;
import com.sis.retrospectiveservice.service.RetrospectiveService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class RetrospectiveControllerTest {

    @Mock
    private RetrospectiveService retrospectiveService;

    @InjectMocks
    private RetrospectiveController retrospectiveController;

    private Retrospective sampleRetrospective;
    private FeedbackItem sampleFeedbackItem;

    @BeforeEach
    void setUp() {
        sampleRetrospective = new Retrospective();
        sampleRetrospective.setId(1L);

        sampleFeedbackItem = new FeedbackItem();
        sampleFeedbackItem.setId(1L);
    }

    @Test
    void createRetrospective() {
        when(retrospectiveService.createRetrospective(any(Retrospective.class)))
                .thenReturn(sampleRetrospective);

        ResponseEntity<Retrospective> responseEntity = retrospectiveController.createRetrospective(sampleRetrospective);

        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertEquals(sampleRetrospective, responseEntity.getBody());
        verify(retrospectiveService, times(1)).createRetrospective(any(Retrospective.class));
    }

    @Test
    void addFeedbackItem() {
        when(retrospectiveService.addFeedbackItem(anyLong(), any(FeedbackItem.class)))
                .thenReturn(sampleRetrospective);

        ResponseEntity<Retrospective> responseEntity = retrospectiveController.addFeedbackItem(1L, sampleFeedbackItem);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(sampleRetrospective, responseEntity.getBody());
        verify(retrospectiveService, times(1)).addFeedbackItem(anyLong(), any(FeedbackItem.class));
    }

    @Test
    void updateFeedbackItem() {
        when(retrospectiveService.updateFeedbackItem(anyLong(), anyLong(), any(FeedbackItem.class)))
                .thenReturn(sampleRetrospective);

        ResponseEntity<Retrospective> responseEntity = retrospectiveController.updateFeedbackItem(1L, 1L, sampleFeedbackItem);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(sampleRetrospective, responseEntity.getBody());
        verify(retrospectiveService, times(1)).updateFeedbackItem(anyLong(), anyLong(), any(FeedbackItem.class));
    }

    @Test
    void getAllRetrospectives() {
        when(retrospectiveService.getAllRetrospectives(anyInt(), anyInt()))
                .thenReturn(Collections.singletonList(sampleRetrospective));

        ResponseEntity<List<Retrospective>> responseEntity = retrospectiveController.getAllRetrospectives(1, 10);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(Collections.singletonList(sampleRetrospective), responseEntity.getBody());
        verify(retrospectiveService, times(1)).getAllRetrospectives(anyInt(), anyInt());
    }

    @Test
    void searchRetrospectivesByDate() {
        when(retrospectiveService.searchRetrospectivesByDate(anyString(), anyInt(), anyInt()))
                .thenReturn(Collections.singletonList(sampleRetrospective));

        ResponseEntity<List<Retrospective>> responseEntity = retrospectiveController.searchRetrospectivesByDate("2022-01-01", 1, 10);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(Collections.singletonList(sampleRetrospective), responseEntity.getBody());
        verify(retrospectiveService, times(1)).searchRetrospectivesByDate(anyString(), anyInt(), anyInt());
    }
}
