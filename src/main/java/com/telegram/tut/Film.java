package com.telegram.tut;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Random;
import java.util.Scanner;

public class Film {

    public static String film(IMDBModel model) throws IOException {

        URL url = new URL("http://www.omdbapi.com/?i=" + HTMLParser.parser(randomIMDBId(FilmGlobalVariables.FILM_FIRST_NUM , FilmGlobalVariables.FILM_SECOND_NUM)) + "&apikey=c19eae94");

        Scanner in = new Scanner((InputStream) url.getContent());

        StringBuilder result = new StringBuilder();

        while (in.hasNext()) {
            result.append(in.nextLine());
        }
        try {
            JSONObject object = new JSONObject(result.toString());
            model.setTitle(object.getString("Title"));
            model.setImdbRating(object.getString("imdbRating"));
            model.setRated(object.getString("Rated"));
            model.setImdbID(object.getString("imdbID"));
            model.setYear(object.getString("Year"));
            model.setImdbVotes(object.getString("imdbVotes"));
            model.setCountry(object.getString("Country"));
            model.setPoster(object.getString("Poster"));
            model.setGenre(object.getString("Genre"));
            model.setDirector(object.getString("Director"));
            model.setRunTime(object.getString("Runtime"));
            model.setPlot(object.getString("Plot"));

            FilmGlobalVariables.FILM_NAME = model.getTitle(); /// for url in class WhatWatch
            FilmGlobalVariables.IMDB_ID = model.getImdbID();  /// for url in class WhatWatch

            return "Title : " + model.getTitle() + "\n" +
                    "Country : " + model.getCountry() + "\n" +
                    "Year : " + model.getYear() + "\n" +
                    "Genre : " + model.getGenre() + "\n" +
                    "Director : " + model.getDirector() + "\n" +
                    "RunTime : " + model.getRunTime() + "\n" +
                    "Plot : " + model.getPlot() + "\n" +
                    "Rated : " + model.getRated() + "\n" +
                    "IMDB Rating : " + model.getImdbRating() + "\n" +
                    "IMDB Votes : " + model.getImdbVotes() + "\n" +
                    "IMDB ID : " + model.getImdbID() + "\n" +
                    model.getPoster();

        } catch (JSONException e) {
            return "Error 404... not found film or tv show... " + "\n" +
                    "IMDB ID : " + model.getImdbID();
        }
    }

    public static int randomIMDBId(int min , int max) {
        Random rand = new Random();
        return rand.nextInt((max-min)+1)+min;
    }
}