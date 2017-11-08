package com.example.logonrm.starwarsapi;

import java.util.List;

public class StarWars {

    private String name;

    private List<Films> films;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Films> getFilms() {
        return films;
    }

    public void setFilms(List<Films> films) {
        this.films = films;
    }
}
