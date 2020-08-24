package com.telegram.tut;

public class IMDBModel {

    private String imdbRating;
    private String title;
    private String rated;
    private String imdbID;
    private String year;
    private String imdbVotes;
    private String Country;
    private String Poster;
    private String Genre;
    private String Director;
    private String RunTime;
    private String Plot;

    public String getPlot() { return Plot; }

    public void setPlot(String plot) { Plot = plot; }

    public String getRunTime() { return RunTime; }

    public void setRunTime(String runTime) { RunTime = runTime; }

    public String getDirector() { return Director; }

    public void setDirector(String director) { Director = director; }

    public String getGenre() { return Genre; }

    public void setGenre(String genre) { Genre = genre; }

    public String getPoster() { return Poster; }

    public void setPoster(String poster) { Poster = poster; }

    public String getCountry() { return Country; }

    public void setCountry(String Country) { this.Country = Country; }

    public String getImdbVotes() { return imdbVotes; }

    public void setImdbVotes(String imdbVotes) { this.imdbVotes = imdbVotes; }

    public String getYear() { return year; }

    public void setYear(String year) { this.year = year; }

    public String getImdbID() { return imdbID; }

    public void setImdbID(String imdbID) { this.imdbID = imdbID; }

    public String getRated() { return rated; }

    public void setRated(String rated) { this.rated = rated; }

    public String getTitle() { return title; }

    public void setTitle(String title) { this.title = title; }

    public String getImdbRating() { return imdbRating; }

    public void setImdbRating(String imdbRating) { this.imdbRating = imdbRating; }

}