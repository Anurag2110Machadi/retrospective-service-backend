package com.sis.retrospectiveservice.controller;

import com.sis.retrospectiveservice.model.FeedbackItem;
import com.sis.retrospectiveservice.model.Retrospective;
import com.sis.retrospectiveservice.service.RetrospectiveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/retrospectives")
public class RetrospectiveController {

    @Autowired
    private RetrospectiveService retrospectiveService;

    @PostMapping
    public ResponseEntity<Retrospective> createRetrospective(@RequestBody Retrospective retrospective) {
        Retrospective createdRetrospective = retrospectiveService.createRetrospective(retrospective);
        return new ResponseEntity<>(createdRetrospective, HttpStatus.CREATED);
    }

    @PostMapping("/{id}/feedback")
    public ResponseEntity<Retrospective> addFeedbackItem(
            @PathVariable Long id,
            @RequestBody FeedbackItem feedbackItem) {
        Retrospective updatedRetrospective = retrospectiveService.addFeedbackItem(id, feedbackItem);
        return new ResponseEntity<>(updatedRetrospective, HttpStatus.OK);
    }

    @PutMapping("/{id}/feedback/{itemId}")
    public ResponseEntity<Retrospective> updateFeedbackItem(
            @PathVariable Long id,
            @PathVariable Long itemId,
            @RequestBody FeedbackItem feedbackItem) {
        Retrospective updatedRetrospective = retrospectiveService.updateFeedbackItem(id, itemId, feedbackItem);
        return new ResponseEntity<>(updatedRetrospective, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Retrospective>> getAllRetrospectives(
            @RequestParam(name = "page", defaultValue = "1") int page,
            @RequestParam(name = "pageSize", defaultValue = "10") int pageSize) {
        List<Retrospective> retrospectives = retrospectiveService.getAllRetrospectives(page, pageSize);
        return new ResponseEntity<>(retrospectives, HttpStatus.OK);
    }

    @GetMapping("/search")
    public ResponseEntity<List<Retrospective>> searchRetrospectivesByDate(
            @RequestParam(name = "date") String date,
            @RequestParam(name = "page", defaultValue = "1") int page,
            @RequestParam(name = "pageSize", defaultValue = "10") int pageSize) {
        List<Retrospective> retrospectives = retrospectiveService.searchRetrospectivesByDate(date, page, pageSize);
        return new ResponseEntity<>(retrospectives, HttpStatus.OK);
    }

}
