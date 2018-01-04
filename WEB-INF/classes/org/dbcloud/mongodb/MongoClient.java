/*
 * Copyright (c) 2017, dbcloud.org and/or its affiliates. All rights reserved.
 * Use is subject to license terms.
 *
 *
 *
 *
 *
 */
package org.dbcloud.mongodb;

import com.mongodb.client.MongoIterable;
import java.util.ArrayList;

/**
 *
 * @author kylin
 */
public class MongoClient {

    private com.mongodb.MongoClient mongoClient;

    public String Server = "127.0.0.1";
    public int Port = 27017;

    public MongoClient() {
        mongoClient = new com.mongodb.MongoClient(Server, Port);
    }

    public MongoClient(String url) {
        int port = Port;
        String[] strs = url.split(":");
        if (strs.length >= 2) {
            port = Integer.valueOf(strs[1]);
            Port = port;
        }
        String server = strs[0];
        mongoClient = new com.mongodb.MongoClient(server, port);
        Server = server;
        Port = port;
    }

    public MongoClient(String server, int port) {
        mongoClient = new com.mongodb.MongoClient(server, port);
        Server = server;
        Port = port;
    }

    public ArrayList<String> listCollectionNames(String dbName) {
        com.mongodb.client.MongoDatabase db = mongoClient.getDatabase(dbName);
        MongoIterable<String> list = db.listCollectionNames();
        ArrayList<String> newlist = new ArrayList<>();
        for (String s : list) {
            newlist.add(s);
        }
        return newlist;
    }

    public ArrayList<String> listDatabaseNames() {
        MongoIterable<String> list = mongoClient.listDatabaseNames();
        ArrayList<String> newlist = new ArrayList<>();
        for (String s : list) {
            newlist.add(s);
        }
        return newlist;
    }

}
