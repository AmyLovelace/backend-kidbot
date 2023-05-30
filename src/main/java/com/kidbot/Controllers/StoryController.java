package com.kidbot.Controllers;

import com.kidbot.Entities.Story;
import com.kidbot.Entities.StoryPrompt;
import com.kidbot.Services.impl.StoryServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1")
public class StoryController {
    @Autowired
    StoryServiceImpl storyService;

    @GetMapping("/stories")
    public ResponseEntity<?> findAllStories() {
        List<Story> listStory = this.storyService.findAllStories();
        return ResponseEntity.ok(listStory);
    }

    @PostMapping("/stories")
    public ResponseEntity<?> createStory(@RequestBody StoryPrompt storyPrompt) {
        Story createdStory = this.storyService.createStory(storyPrompt);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdStory);
    }

    @PutMapping("/stories/{id}")
    public ResponseEntity<?> updateStory(@PathVariable("id") Long id, @RequestBody Story story) {
        Story modifiedStory = this.storyService.updateStory(story);
        return ResponseEntity.status(HttpStatus.CREATED).body(modifiedStory);
    }

    @GetMapping("/stories/search/{id}")
    public ResponseEntity<?> findStoryById(@PathVariable("id") Long id) {
        Optional<Story> story = this.storyService.findStoryById(id);
        return ResponseEntity.ok(story);
    }

    @DeleteMapping("/stories/{id}")
    public ResponseEntity<?> deleteStoryById(@PathVariable Long id) {
        this.storyService.deleteStoryById(id);
        return ResponseEntity.ok().build();
    }

}

