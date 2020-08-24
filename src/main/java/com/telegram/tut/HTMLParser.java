package com.telegram.tut;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HTMLParser {
    public static String parser(int index) throws IOException {

        ArrayList<String> ids = new ArrayList<>();
        String id;

        ////////////////////////////  1 min 21 sec  - 50  ////////////////////////////
        ///////////////////////////   2 sec - 1  ////////////////////////////

        //// here in for loop you can change amount of titles. For example , if i == 2 , then amount of titles(film name) == 51 , if i == 20 , then titles == 69  e.t.c . But than more i , then more time of processing
        for(int i = 1; i <= 2; i++) {
            URL url = new URL("https://www.imdb.com/search/title/?title_type=feature&num_votes=25,&view=simple&sort=num_votes,desc&start=" + i + "&ref_=adv_nxt");  /// instead page better use start ///

            System.out.println("urlN : " + url);

            Document doc = Jsoup.connect(String.valueOf(url)).get();

            Elements el = doc.select(".col-title a");
            for(Element e : el) {
                id = e.absUrl("href");
                Pattern p = Pattern.compile("(tt)\\d+");
                Matcher m = p.matcher(id);
                if(m.find()) ids.add(m.group());
            }
        }

        FilmMongoDb.check(FilmGlobalVariables.USER_ID, ids);

        System.out.println("ids : " + ids.get(index));
        System.out.println("ids size : " + ids.size()); /// 100 el in array
        String s = ids.get(index);
        System.out.println("ids size in bytes : " + Arrays.toString(s.getBytes())); /// 600 Byte
        return ids.get(index);
    }
}
