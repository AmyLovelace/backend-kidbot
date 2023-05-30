package com.kidbot.Entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

import static org.hibernate.query.results.Builders.fetch;


@Entity
@Table(name = "stories")
public class Story {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    @JsonBackReference
    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Column(name = "storyTitle")
    private String storyTitle;

    @Column(name = "storyContent",columnDefinition = "TEXT")
    private String storyContent;

    public Long getStoryId() {
        return id;
    }

    public void setStoryId(Long storyId) {
        this.id = id;
    }

    public String getStoryTitle() {
        return storyTitle;
    }

    public void setStoryTitle(String storyTitle) {
        this.storyTitle = storyTitle;
    }

    public String getStoryContent() {
        return storyContent;
    }

    public void setStoryContent(String storyContent) {
        this.storyContent = storyContent;
    }

    @Override
    public String toString() {
        return "Story{" +
                "storyId=" + id +
                ", storyTitle='" + storyTitle + '\'' +
                ", storyContent='" + storyContent + '\'' +
                '}';
    }
}
