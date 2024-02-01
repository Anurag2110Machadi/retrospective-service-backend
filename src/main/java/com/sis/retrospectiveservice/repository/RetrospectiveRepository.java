package com.sis.retrospectiveservice.repository;

import com.sis.retrospectiveservice.model.FeedbackItem;
import com.sis.retrospectiveservice.model.FeedbackType;
import com.sis.retrospectiveservice.model.Retrospective;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class RetrospectiveRepository {

    private final Map<Long, Retrospective> retrospectiveMap = new HashMap<>();
    private final AtomicLong idGenerator = new AtomicLong(1);

    public RetrospectiveRepository() {
        initSampleData();
    }

    public Retrospective createRetrospective(Retrospective retrospective) {
        Long id = idGenerator.getAndIncrement();
        retrospective.setId(id);
        retrospectiveMap.put(id, retrospective);
        return retrospective;
    }

    public Retrospective addFeedbackItem(Long id, FeedbackItem feedbackItem) {
        Retrospective retrospective = getRetrospectiveById(id);
        if (retrospective != null) {
            List<FeedbackItem> feedbackItems = new ArrayList<>(retrospective.getFeedbackItems());
    
            // Check if the feedback item ID is unique within the retrospective
            boolean isUniqueItemId = feedbackItems.stream()
                    .noneMatch(existingFeedbackItem -> existingFeedbackItem.getId().equals(feedbackItem.getId()));
    
            if (!isUniqueItemId) {
                // Throw an exception if the ID is not unique
                throw new IllegalArgumentException("Feedback item ID must be unique within the retrospective.");
            }
    
            feedbackItems.add(feedbackItem);
            retrospective.setFeedbackItems(feedbackItems);
            retrospectiveMap.put(id, retrospective);
        }
        return retrospective;
    }
    

    public Retrospective updateFeedbackItem(Long id, Long itemId, FeedbackItem updatedFeedbackItem) {
        Retrospective retrospective = getRetrospectiveById(id);
        if (retrospective != null) {
            List<FeedbackItem> feedbackItems = retrospective.getFeedbackItems();
            for (FeedbackItem feedbackItem : feedbackItems) {
                if (feedbackItem.getId().equals(itemId)) {
                    feedbackItem.setBody(updatedFeedbackItem.getBody());
                    feedbackItem.setFeedbackType(updatedFeedbackItem.getFeedbackType());
                    //retrospectiveMap.put(id, retrospective);
                    break;
                }
            }
        }
        return retrospective;
    }

    public List<Retrospective> getAllRetrospectives(int page, int pageSize) {
        int startIndex = (page - 1) * pageSize;
        int endIndex = Math.min(startIndex + pageSize, retrospectiveMap.size());

        return new ArrayList<>(retrospectiveMap.values()).subList(startIndex, endIndex);
    }

    public List<Retrospective> searchRetrospectivesByDate(String date, int page, int pageSize) {
        List<Retrospective> result = new ArrayList<>();
        for (Retrospective retrospective : retrospectiveMap.values()) {
            if (retrospective.getDate().toString().equals(date)) {
                result.add(retrospective);
            }
        }
        int startIndex = (page - 1) * pageSize;
        int endIndex = Math.min(startIndex + pageSize, result.size());
        return result.subList(startIndex, endIndex);
    }

    private Retrospective getRetrospectiveById(Long id) {
        return retrospectiveMap.getOrDefault(id, null);
    }

    private void initSampleData() {
        Retrospective retrospective1 = new Retrospective();
        retrospective1.setName("Retrospective 1");
        retrospective1.setSummary("Post release retrospective");
        retrospective1.setDate(LocalDate.parse("2022-07-27"));
        retrospective1.setParticipants(List.of("Viktor", "Gareth", "Mike"));

        FeedbackItem feedbackItem1 = new FeedbackItem();
        feedbackItem1.setId(1L);
        feedbackItem1.setName("Gareth");
        feedbackItem1.setBody("Sprint objective met");
        feedbackItem1.setFeedbackType(FeedbackType.POSITIVE);

        FeedbackItem feedbackItem2 = new FeedbackItem();
        feedbackItem2.setId(2L);
        feedbackItem2.setName("Viktor");
        feedbackItem2.setBody("Too many items piled up in the awaiting QA");
        feedbackItem2.setFeedbackType(FeedbackType.NEGATIVE);

        FeedbackItem feedbackItem3 = new FeedbackItem();
        feedbackItem3.setId(3L);
        feedbackItem3.setName("Mike");
        feedbackItem3.setBody("We should be looking to start using VS2015");
        feedbackItem3.setFeedbackType(FeedbackType.IDEA);

        retrospective1.setFeedbackItems(List.of(feedbackItem1, feedbackItem2, feedbackItem3));

        createRetrospective(retrospective1);
    }
}
