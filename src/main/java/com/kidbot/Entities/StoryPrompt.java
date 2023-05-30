package com.kidbot.Entities;

import java.util.List;

public class StoryPrompt {

    private Long id;
    private String characterName;
    private int age;
    private String themeOne;
    private String themeTwo;
    private String genre;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCharacterName() {
        return characterName;
    }

    public void setCharacterName(String characterName) {
        this.characterName = characterName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getThemeOne() {
        return themeOne;
    }

    public void setThemeOne(String themeOne) {
        this.themeOne = themeOne;
    }

    public String getThemeTwo() {
        return themeTwo;
    }

    public void setThemeTwo(String themeTwo) {
        this.themeTwo = themeTwo;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }
}
