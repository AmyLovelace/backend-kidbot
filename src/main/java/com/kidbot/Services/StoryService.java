package com.kidbot.Services;

import com.kidbot.Entities.Story;
import com.kidbot.Entities.StoryPrompt;

import java.util.List;
import java.util.Optional;

public interface StoryService {
    List<Story> findAllStories();
    Story createStory(StoryPrompt story);
    Story updateStory(Story story);
    Optional<Story> findStoryById(Long id);
    void deleteStoryById(Long id);
}
