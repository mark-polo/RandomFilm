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

        MongoClientURI connectionString = new MongoClientURI("mongodb://new_user_1:1q2w3e4r5t6yQ@tutorial-shard-00-00.ickxg.mongodb.net:27017,tutorial-shard-00-01.ickxg.mongodb.net:27017,tutorial-shard-00-02.ickxg.mongodb.net:27017/FilmSet?ssl=true&replicaSet=atlas-14253y-shard-0&authSource=admin&retryWrites=true&w=majority");

        MongoClient mongoClient = new MongoClient(connectionString);

        MongoDatabase database = mongoClient.getDatabase("FilmSet");

        MongoCollection<Document> collection = database.getCollection("Film");

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