package com.telegram.tut;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.util.ArrayList;
import java.util.logging.Level;

public class FilmMongoDb {
    public static void check(long user_id, ArrayList<String> id) {

        java.util.logging.Logger.getLogger("org.mongodb.driver").setLevel(Level.OFF);

        MongoClientURI connectionString = new MongoClientURI("your client uri");

        MongoClient mongoClient = new MongoClient(connectionString);

        MongoDatabase database = mongoClient.getDatabase("your name db");

        MongoCollection<Document> collection = database.getCollection("your user");

        long found = collection.count(Document.parse("{id : " + user_id + "}"));

        Document doc = new Document("user_id ", FilmGlobalVariables.USER_ID).
                append("film_id ", id);
        collection.insertOne(doc);


        if (found == 0) {
            mongoClient.close();
            System.out.println("User not exists in database. Written.");
        } else {
            System.out.println("User exists in database.");
            mongoClient.close();
        }
    }
}
