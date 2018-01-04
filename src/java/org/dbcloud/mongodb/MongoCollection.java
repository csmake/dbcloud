/*! \page mongo MongoDB Development Manual
 ![webmongo](https://avatars1.githubusercontent.com/u/17017373?s=460&v=4)

# webmongo

## About us

Accessing server-side mongodb through client javascript API. This project is a branch of [dbcloud](https://github.com/csmake/dbcloud)

You can do almost invoke on mongodb through the javascript API in browser.

The client javascript api support `IE6.0+ Chrome FireFox and Wechat`

web: http://mongo.dbcloud.org

mail: webmongo@csmake.com   

## Application scenarios

* Rapid prototyping does not need to write server-side code.

* Academic teaching and research, WYSIWYG

* Enterprise internal application

* Other applications that do not focus on the security of the database table structure.

## Start the basic project 

1. Run mongod first. 
2. Put webmongo.war in your servlet server webapps ,Apache Tomcat8.0 , Glass Fish Server4.x and so on.
3. Open browser(Chrome FireFox IE) and put http://localhost:8080/webmongo/, The address may be different depending on your settings

## Start to write your web application

1. Copy MongoCollection.java and MongoCollectionServlet.java from webmongo.war /src/java/.. , into your src/java/org/dbcloud/mongodb

2. Copy all *.js files in webmongo.war /js to your js directory. JQuery is necessary. IE6.0 need to use the 1.x version and json2.js is necessary.

3. Edit the web.xml, like this:
```xml

    <servlet>
        <servlet-name>MongoCollectionServlet</servlet-name>
        <servlet-class>org.dbcloud.mongodb.MongoCollectionServlet</servlet-class>
        <init-param>
            <param-name>dbhost</param-name>
            <param-value>127.0.0.1</param-value>
        </init-param>
        <init-param>
            <param-name>dbport</param-name>
            <param-value>27017</param-value>
        </init-param> 
        <init-param>
            <param-name>db</param-name>
            <param-value>test</param-value>
        </init-param>
        <init-param>
            <param-name>connection</param-name>
            <param-value>test</param-value>
        </init-param>
        <init-param>
            <param-name>user</param-name>
            <param-value></param-value>
        </init-param>
        <init-param>
            <param-name>password</param-name>
            <param-value></param-value>
        </init-param> 
    </servlet>
    <servlet-mapping>
        <servlet-name>MongoCollectionServlet</servlet-name>
        <url-pattern>/org.dbcloud.mongodb.MongoCollection</url-pattern>
    </servlet-mapping> 
```
4. Edit your html file, you can see the index.html 

```javascript
	﻿<!DOCTYPE html>
	<html>
		<head>
		</head>
		<body>
			<div id='log'></div>
		</body>
		<script src="js/json2.js" note='for IE6.0 ,old browser'></script>
		<script src="js/jQuery-1.12.4.min.js" note='for IE6.0 ,new version can use JQuery 2.x'></script>
		<script src="js/org.dbcloud.mongodb.MongoCollection.js"></script>
		<script>
			try {
				function log(msg) {
					$("#log").append("<p>" + msg + "</p>");
				}
				var table = new org.dbcloud.mongodb.MongoCollection();
				log("clear table");
				table.deleteMany({});
				log("insertOne and set options");
				table.insertOne({name: 'validation'}, {validation: true});
				log("count:" + table.count({index: {'$exists': true}}));
				var data = table.find({'index': {'$gt': 5}}, {projection: {'_id': 0}});
				log("find index > 5 and exclude _id column");
				log(JSON.stringify(data));
				log("replaceOne");
				table.replaceOne({name: 'dbcloud'}, {name: 'replace', status: 0});
				log("updateMany with options ");
				table.updateMany({'index': {'$gt': 10}}, {'$set': {name: 'updateMany', status: 100, index: 100}}, {upsert: true, validation: false});
				log("find all");
				var data = table.find();
				log(JSON.stringify(data));
				log('findOneAndUpdate');
				log(JSON.stringify(table.findOneAndUpdate({'index': {'$gt': 10}}, {'$set': {name: 'findOneAndUpdate'}, '$inc': {index: -1}})));
				table.close();
			} catch (e) {
				alert(e.message);
			}
		</script>
	</html>
```
 */
package org.dbcloud.mongodb;

import com.alibaba.fastjson.JSON;
import com.csmake.Util;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.MongoCredential;
import com.mongodb.MongoNamespace;
import com.mongodb.ServerAddress;
import com.mongodb.annotations.ThreadSafe;
import com.mongodb.client.FindIterable;
import com.mongodb.client.ListIndexesIterable;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.model.CountOptions;
import com.mongodb.client.model.FindOneAndDeleteOptions;
import com.mongodb.client.model.FindOneAndReplaceOptions;
import com.mongodb.client.model.FindOneAndUpdateOptions;
import com.mongodb.client.model.IndexOptions;
import com.mongodb.client.model.InsertManyOptions;
import com.mongodb.client.model.InsertOneOptions;
import com.mongodb.client.model.RenameCollectionOptions;
import com.mongodb.client.model.ReturnDocument;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import java.util.List;
import org.bson.Document;
import org.bson.codecs.configuration.CodecRegistry;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

//https://github.com/csmake/webmongo.git
/**
 * The MongoCollection interface.
 *
 * <p>
 * Note: Additions to this interface will not be considered to break binary
 * compatibility.</p>
 *
 * <p>
 * MongoCollection is generic allowing for different types to represent
 * documents. includes built-in support for:
 * {@link org.bson.BsonDocument}, {@link Document} .
 * </p>
 *
 * @author www.csmake.com kylin
 * @since dbcloud 1.0 mongo.java.driver 3.2.2
 */
@ThreadSafe
public class MongoCollection {

    private com.mongodb.client.MongoCollection<Document> proxy = null;
    private MongoClient mongoClient = null;

    public void csmakeInit(java.util.HashMap<String, String> args) throws Exception {
        try {
            if (mongoClient == null) {
                String dbName = args.getOrDefault("db", "test");
                String dbhost = args.getOrDefault("dbhost", "127.0.0.1");
                String collection = args.getOrDefault("collection", "user");
                int dbport = Integer.valueOf(args.getOrDefault("dbport", "27017"));

                MongoClientOptions msettings = MongoClientOptions.builder().codecRegistry(com.mongodb.MongoClient.getDefaultCodecRegistry()).build();
                List<MongoCredential> credentials = new ArrayList<>();
                String userName = args.getOrDefault("user", "");
                String password = args.getOrDefault("password", "");
                if (!userName.isEmpty() && !password.isEmpty()) {
                    credentials.add(MongoCredential.createCredential(userName, dbName, password.toCharArray()));
                }
                mongoClient = new MongoClient(new ServerAddress(dbhost, dbport), credentials, msettings);
                proxy = mongoClient.getDatabase(dbName).getCollection(collection);
            }
        } catch (Exception e) {
            System.err.println(e.toString());
        }
    }

    /**
     * connect to server collection by the default settings in web.xml which
     * used in csmakeInit
     *
     */
    public MongoCollection() {
    }

    /**
     * connect to server collection.
     *
     * @param db the database name
     * @param collection the name of collection
     */
    public MongoCollection(String db, String collection) {
        MongoClient mongoClient = new MongoClient("127.0.0.1", 27017);
        proxy = mongoClient.getDatabase(db).getCollection(collection);
    }

    /**
     * connect to server collection.
     *
     * @param url connetion eg. 127.0.0.1:27017
     * @param db the database name
     * @param collection the name of collection
     */
    public MongoCollection(String url, String db, String collection) {
        int port = 27017;
        String[] strs = url.split(":");
        if (strs.length >= 2) {
            port = Integer.valueOf(strs[1]);
        }
        String server = strs[0];
        mongoClient = new MongoClient(server, port);
        proxy = mongoClient.getDatabase(db).getCollection(collection);
    }

    /**
     * connect to server collection. about auth:
     * https://docs.mongodb.com/manual/reference/built-in-roles/
     *
     * @param db the database name
     * @param collection the name of collection
     * @param user the database user name
     * @param password the database password
     */
    public MongoCollection(String db, String collection, String user, String password) {
        List<MongoCredential> credentialsList = new ArrayList<>();
        if (!user.isEmpty() && !password.isEmpty()) {
            credentialsList.add(MongoCredential.createCredential(user, db, password.toCharArray()));
        }
        mongoClient = new MongoClient(new com.mongodb.ServerAddress("127.0.0.1", 27017), credentialsList);
        proxy = mongoClient.getDatabase(db).getCollection(collection);
    }

    /**
     * connect to server collection. about auth:
     * https://docs.mongodb.com/manual/reference/built-in-roles/
     *
     * @param url connetion eg. 127.0.0.1:27017
     * @param db the database name
     * @param collection the name of collection
     * @param user the database user name
     * @param password the database password
     */
    public MongoCollection(String url, String db, String collection, String user, String password) {
        int port = 27017;
        String[] strs = url.split(":");
        if (strs.length >= 2) {
            port = Integer.valueOf(strs[1]);
        }
        String server = strs[0];
        List<MongoCredential> credentialsList = new ArrayList<>();
        if (!user.isEmpty() && !password.isEmpty()) {
            credentialsList.add(MongoCredential.createCredential(user, db, password.toCharArray()));
        }
        mongoClient = new MongoClient(new com.mongodb.ServerAddress(server, port), credentialsList);
        proxy = mongoClient.getDatabase(db).getCollection(collection);
    }

    /**
     * Gets the newCollectionNamespace of this collection.
     *
     * @return the newCollectionNamespace
     */
    public MongoNamespace getNamespace() {
        return proxy.getNamespace();
    }

    /**
     * Get the class of documents stored in this collection.
     *
     * @return the class
     */
    public Class<Document> getDocumentClass() {
        return proxy.getDocumentClass();
    }

    /**
     * Get the codec registry for the MongoCollection.
     *
     * @return the {@link org.bson.codecs.configuration.CodecRegistry}
     */
    public CodecRegistry getCodecRegistry() {
        return proxy.getCodecRegistry();
    }

    /**
     * Counts the number of documents in the collection.
     *
     * @return the number of documents in the collection
     */
    public long count() {
        return proxy.count();
    }

    /**
     * Counts the number of documents in the collection according to the given
     * options.
     *
     * @param filter the query filter
     * @return the number of documents in the collection
     */
    public long count(Document filter) {
        return proxy.count(toObjectId(filter));
    }

    /**
     * Counts the number of documents in the collection according to the given
     * options.
     *
     * @param filter the query filter
     * @param options the options describing the count
     * @return the number of documents in the collection
     */
    public long count(Document filter, Document options) {
        CountOptions op = new CountOptions();
        if (options.containsKey("hint")) {
            Document hint = JSON.parseObject(options.get("hint").toString(), Document.class);
            op.hint(hint);
        }
        if (options.containsKey("maxTimeMS")) {
            op.maxTime(Long.valueOf(options.get("maxTimeMS").toString()), TimeUnit.MILLISECONDS);
        }
        if (options.containsKey("limit")) {
            op.limit(Integer.valueOf(options.get("limit").toString()));
        }
        if (options.containsKey("skip")) {
            op.skip(Integer.valueOf(options.get("skip").toString()));
        }
        if (options.containsKey("hintString")) {
            op.hintString(options.get("hintString").toString());
        }

        return proxy.count(toObjectId(filter), op);
    }

    /**
     * Finds all documents in the collection.
     *
     * @return the find iterable interface mongodb.driver.manual
     * tutorial/query-documents/ Find
     */
    public ArrayList<Document> find() {
        return conv(proxy.find());
    }

    /**
     * Finds all documents in the collection.
     *
     * @param filter the query filter
     * @return the find iterable interface mongodb.driver.manual
     * tutorial/query-documents/ Find
     */
    public ArrayList<Document> find(Document filter) {
        return conv(proxy.find(toObjectId(filter)));
    }

    /**
     * Finds all documents in the collection.
     *
     * @param filter the query filter
     * @param options, include projection the projection filter,sort the sort,skip, limit filter
     * @return the find iterable interface mongodb.driver.manual
     * tutorial/query-documents/ Find
     */
    public ArrayList<Document> find(Document filter, Document options) {
        FindIterable<Document> res = proxy.find(toObjectId(filter));
        if (options.containsKey("projection")) {
            Document projection = JSON.parseObject(options.get("projection").toString(), Document.class);
            res = res.projection(projection);
        }
        if (options.containsKey("sort")) {
            Document sort = JSON.parseObject(options.get("sort").toString(), Document.class);
            res = res.sort(sort);
        }
        if (options.containsKey("skip")) {
            res = res.skip(Integer.valueOf(options.get("skip").toString()));
        }
        if (options.containsKey("limit")) {
            res = res.limit(Integer.valueOf(options.get("limit").toString()));
        }
        return conv(res);
    }

    /**
     * Finds all documents in the collection.
     *
     * @param filter the query filter
     * @param skip the skip, the start records
     * @param limit the limit count
     * @return the find iterable interface mongodb.driver.manual
     * tutorial/query-documents/ Find
     */
    public ArrayList<Document> find(Document filter, int skip, int limit) {
        return conv(proxy.find(toObjectId(filter)).skip(skip).limit(limit));
    }

    /**
     * Finds all documents in the collection.
     *
     * @param filter the query filter
     * @param options, include projection the projection filter,sort the  sort,skip, limit
     * @param skip the skip, the start records
     * @param limit the limit count
     * @return the find iterable interface mongodb.driver.manual
     * tutorial/query-documents/ Find
     */
    public ArrayList<Document> find(Document filter, Document options, int skip, int limit) {
        options.append("skip", skip);
        options.append("limit", limit);
        return find(filter, options);
    }

    /**
     * Inserts the provided document. If the document is missing an identifier,
     * the driver should generate one.
     *
     * @param document the document to insert
     * @return _id
     * @throws com.mongodb.MongoWriteException if the write failed due some
     * other failure specific to the insert command
     * @throws com.mongodb.MongoWriteConcernException if the write failed due
     * being unable to fulfil the write concern
     * @throws com.mongodb.MongoException if the write failed due some other
     * failure
     */
    public String insertOne(Document document) {
        proxy.insertOne(document);
        return document.get("_id").toString();
    }

    /**
     * Inserts the provided document. If the document is missing an identifier,
     * the driver should generate one.
     *
     * @param document the document to insert
     * @param options the options to apply to the operation
     * @return _id
     * @throws com.mongodb.MongoWriteException if the write failed due some
     * other failure specific to the insert command
     * @throws com.mongodb.MongoWriteConcernException if the write failed due
     * being unable to fulfil the write concern
     * @throws com.mongodb.MongoCommandException if the write failed due to
     * document validation reasons
     * @throws com.mongodb.MongoException if the write failed due some other
     * failure
     * @since mongo.java.driver 3.2
     */
    public String insertOne(Document document, Document options) {
        InsertOneOptions ioo = new InsertOneOptions();
        ioo.bypassDocumentValidation(options.getBoolean("validation", true));
        proxy.insertOne(document, ioo);
        return document.get("_id").toString();
    }

    /**
     * Inserts one or more documents. A call to this method is equivalent to a
     * call to the {@code bulkWrite} method
     *
     * @param documents the documents to insert
     * @return _id list
     * @throws com.mongodb.MongoBulkWriteException if there's an exception in
     * the bulk write operation
     * @throws com.mongodb.MongoException if the write failed due some other
     * failure
     * @see com.mongodb.client.MongoCollection#bulkWrite
     */
    public List<? extends String> insertMany(List<? extends Document> documents) {
        proxy.insertMany(documents);
        ArrayList<String> ids = new ArrayList();
        int size = documents.size();
        for (int i = 0; i < size; i++) {
            ids.add(documents.get(i).get("_id").toString());
        }
        return ids;
    }

    /**
     * Inserts one or more documents. A call to this method is equivalent to a
     * call to the {@code bulkWrite} method
     *
     * @param documents the documents to insert
     * @param options the options to apply to the operation
     * @return _id list
     * @throws com.mongodb.MongoBulkWriteException if there's an exception in
     * the bulk write operation
     * @throws com.mongodb.MongoException if the write failed due some other
     * failure
     * @see com.mongodb.client.MongoCollection#bulkWrite
     */
    public List<? extends String> insertMany(List<? extends Document> documents, Document options) {
        boolean ordered = options.getBoolean("ordered", true);
        boolean validation = options.getBoolean("validation", true);
        InsertManyOptions imo = new InsertManyOptions();
        imo.ordered(ordered);
        imo.bypassDocumentValidation(validation);
        proxy.insertMany(documents, imo);
        ArrayList<String> ids = new ArrayList();
        int size = documents.size();
        for (int i = 0; i < size; i++) {
            ids.add(documents.get(i).get("_id").toString());
        }
        return ids;
    }

    /**
     * Removes at most one document from the collection that matches the given
     * filter. If no documents match, the collection is not modified.
     *
     * @param filter the query filter to apply the the delete operation
     * @return the result of the remove one operation
     * @throws com.mongodb.MongoWriteException if the write failed due some
     * other failure specific to the delete command
     * @throws com.mongodb.MongoWriteConcernException if the write failed due
     * being unable to fulfil the write concern
     * @throws com.mongodb.MongoException if the write failed due some other
     * failure
     */
    public DeleteResult deleteOne(Document filter) {
        return proxy.deleteOne(toObjectId(filter));
    }

    /**
     * Removes all documents from the collection that match the given query
     * filter. If no documents match, the collection is not modified.
     *
     * @param filter the query filter to apply the the delete operation
     * @return the result of the remove many operation
     * @throws com.mongodb.MongoWriteException if the write failed due some
     * other failure specific to the delete command
     * @throws com.mongodb.MongoWriteConcernException if the write failed due
     * being unable to fulfil the write concern
     * @throws com.mongodb.MongoException if the write failed due some other
     * failure
     */
    public DeleteResult deleteMany(Document filter) {
        return proxy.deleteMany(toObjectId(filter));
    }

    /**
     * Replace a document in the collection according to the specified
     * arguments.
     *
     * @param filter the query filter to apply the the replace operation
     * @param replacement the replacement document
     * @return the result of the replace one operation
     * @throws com.mongodb.MongoWriteException if the write failed due some
     * other failure specific to the replace command
     * @throws com.mongodb.MongoWriteConcernException if the write failed due
     * being unable to fulfil the write concern
     * @throws com.mongodb.MongoException if the write failed due some other
     * failure mongodb.driver.manual
     * tutorial/modify-documents/#replace-the-document Replace
     */
    public UpdateResult replaceOne(Document filter, Document replacement) {
        return proxy.replaceOne(toObjectId(filter), replacement);
    }

    /**
     * Replace a document in the collection according to the specified
     * arguments.
     *
     * @param filter the query filter to apply the the replace operation
     * @param replacement the replacement document
     * @param options the options to apply to the replace operation
     * @return the result of the replace one operation
     * @throws com.mongodb.MongoWriteException if the write failed due some
     * other failure specific to the replace command
     * @throws com.mongodb.MongoWriteConcernException if the write failed due
     * being unable to fulfil the write concern
     * @throws com.mongodb.MongoException if the write failed due some other
     * failure mongodb.driver.manual
     * tutorial/modify-documents/#replace-the-document Replace
     */
    public UpdateResult replaceOne(Document filter, Document replacement, Document options) {
        return proxy.replaceOne(toObjectId(filter), replacement, toUpdateOptions(options));
    }

    /**
     * Update a single document in the collection according to the specified
     * arguments.
     *
     * @param filter a document describing the query filter, which may not be
     * null.
     * @param update a document describing the update, which may not be null.
     * The update to apply must include only update operators.
     * @return the result of the update one operation
     * @throws com.mongodb.MongoWriteException if the write failed due some
     * other failure specific to the update command
     * @throws com.mongodb.MongoWriteConcernException if the write failed due
     * being unable to fulfil the write concern
     * @throws com.mongodb.MongoException if the write failed due some other
     * failure mongodb.driver.manual tutorial/modify-documents/ Updates
     * mongodb.driver.manual reference/operator/update/ Update Operators
     */
    public UpdateResult updateOne(Document filter, Document update) {
        return proxy.updateOne(toObjectId(filter), update);
    }

    /**
     * Update a single document in the collection according to the specified
     * arguments.
     *
     * @param filter a document describing the query filter, which may not be
     * null.
     * @param update a document describing the update, which may not be null.
     * The update to apply must include only update operators.
     * @param options the options to apply to the update operation
     * @return the result of the update one operation
     * @throws com.mongodb.MongoWriteException if the write failed due some
     * other failure specific to the update command
     * @throws com.mongodb.MongoWriteConcernException if the write failed due
     * being unable to fulfil the write concern
     * @throws com.mongodb.MongoException if the write failed due some other
     * failure mongodb.driver.manual tutorial/modify-documents/ Updates
     * mongodb.driver.manual reference/operator/update/ Update Operators
     */
    public UpdateResult updateOne(Document filter, Document update, Document options) {
        return proxy.updateOne(toObjectId(filter), update, toUpdateOptions(options));
    }

    /**
     * Update all documents in the collection according to the specified
     * arguments.
     *
     * @param filter a document describing the query filter, which may not be
     * null.
     * @param update a document describing the update, which may not be null.
     * The update to apply must include only update operators.
     * @return the result of the update one operation
     * @throws com.mongodb.MongoWriteException if the write failed due some
     * other failure specific to the update command
     * @throws com.mongodb.MongoWriteConcernException if the write failed due
     * being unable to fulfil the write concern
     * @throws com.mongodb.MongoException if the write failed due some other
     * failure mongodb.driver.manual tutorial/modify-documents/ Updates
     * mongodb.driver.manual reference/operator/update/ Update Operators
     */
    public UpdateResult updateMany(Document filter, Document update) {
        return proxy.updateMany(toObjectId(filter), update);
    }

    /**
     * Update all documents in the collection according to the specified
     * arguments.
     *
     * @param filter a document describing the query filter, which may not be
     * null.
     * @param update a document describing the update, which may not be null.
     * The update to apply must include only update operators.
     * @param options the options to apply to the update operation
     * @return the result of the update one operation
     * @throws com.mongodb.MongoWriteException if the write failed due some
     * other failure specific to the update command
     * @throws com.mongodb.MongoWriteConcernException if the write failed due
     * being unable to fulfil the write concern
     * @throws com.mongodb.MongoException if the write failed due some other
     * failure mongodb.driver.manual tutorial/modify-documents/ Updates
     * mongodb.driver.manual reference/operator/update/ Update Operators
     */
    public UpdateResult updateMany(Document filter, Document update, Document options) {
        return proxy.updateMany(toObjectId(filter), update, toUpdateOptions(options));
    }

    /**
     * Atomically find a document and remove it.
     *
     * @param filter the query filter to find the document with
     * @return the document that was removed. If no documents matched the query
     * filter, then null will be returned
     */
    public Document findOneAndDelete(Document filter) {
        return fromObjectId(proxy.findOneAndDelete(toObjectId(filter)));
    }

    /**
     * Atomically find a document and remove it.
     *
     * @param filter the query filter to find the document with
     * @param options the options to apply to the operation
     * @return the document that was removed. If no documents matched the query
     * filter, then null will be returned
     */
    public Document findOneAndDelete(Document filter, Document options) {
        FindOneAndDeleteOptions foado = new FindOneAndDeleteOptions();
        if (options.containsKey("sort")) {
            Document sort = JSON.parseObject(options.get("sort").toString(), Document.class);
            foado.sort(sort);
        }
        if (options.containsKey("projection")) {
            Document projection = JSON.parseObject(options.get("projection").toString(), Document.class);
            foado.projection(projection);
        }
        if (options.containsKey("maxTimeMS")) {
            foado.maxTime(Long.valueOf(options.get("maxTimeMS").toString()), TimeUnit.MILLISECONDS);
        }
        return fromObjectId(proxy.findOneAndDelete(toObjectId(filter), foado));
    }

    /**
     * Atomically find a document and replace it.
     *
     * @param filter the query filter to apply the the replace operation
     * @param replacement the replacement document
     * @return the document that was replaced. Depending on the value of the
     * {@code returnOriginal} property, this will either be the document as it
     * was before the update or as it is after the update. If no documents
     * matched the query filter, then null will be returned
     */
    public Document findOneAndReplace(Document filter, Document replacement) {
        return fromObjectId(proxy.findOneAndReplace(toObjectId(filter), replacement));
    }

    /**
     * Atomically find a document and replace it.
     *
     * @param filter the query filter to apply the the replace operation
     * @param replacement the replacement document
     * @param options the options to apply to the operation
     * @return the document that was replaced. Depending on the value of the
     * {@code returnOriginal} property, this will either be the document as it
     * was before the update or as it is after the update. If no documents
     * matched the query filter, then null will be returned
     */
    public Document findOneAndReplace(Document filter, Document replacement, Document options) {
        FindOneAndReplaceOptions foaro = new FindOneAndReplaceOptions();

        if (options.containsKey("sort")) {
            Document sort = JSON.parseObject(options.get("sort").toString(), Document.class);
            foaro.sort(sort);
        }
        if (options.containsKey("projection")) {
            Document projection = JSON.parseObject(options.get("projection").toString(), Document.class);
            foaro.projection(projection);
        }
        if (options.containsKey("maxTimeMS")) {
            foaro.maxTime(Long.valueOf(options.get("maxTimeMS").toString()), TimeUnit.MILLISECONDS);
        }
        foaro.bypassDocumentValidation(options.getBoolean("validation", false));
        foaro.upsert(options.getBoolean("upsert", false));
        boolean returnNew = options.getBoolean("returnNew", false);
        foaro.returnDocument(returnNew ? com.mongodb.client.model.ReturnDocument.AFTER : com.mongodb.client.model.ReturnDocument.BEFORE);
        Document o = fromObjectId(proxy.findOneAndReplace(toObjectId(filter), replacement, foaro));
        return returnNew ? replacement : o;
    }

    /**
     * Atomically find a document and update it.
     *
     * @param filter a document describing the query filter, which may not be
     * null.
     * @param update a document describing the update, which may not be null.
     * The update to apply must include only update operators.
     * @return the document that was updated before the update was applied. If
     * no documents matched the query filter, then null will be returned
     */
    public Document findOneAndUpdate(Document filter, Document update) {
        return fromObjectId(proxy.findOneAndUpdate(toObjectId(filter), update));
    }

    /**
     * Atomically find a document and update it.
     *
     * @param filter a document describing the query filter, which may not be
     * null.
     * @param update a document describing the update, which may not be null.
     * The update to apply must include only update operators.
     * @param options the options to apply to the operation
     * @return the document that was updated. Depending on the value of the
     * {@code returnOriginal} property, this will either be the document as it
     * was before the update or as it is after the update. If no documents
     * matched the query filter, then null will be returned
     */
    public Document findOneAndUpdate(Document filter, Document update, Document options) {
        FindOneAndUpdateOptions foauo = new FindOneAndUpdateOptions();

        if (options.containsKey("sort")) {
            Document sort = JSON.parseObject(options.get("sort").toString(), Document.class);
            foauo.sort(sort);
        }
        if (options.containsKey("projection")) {
            Document projection = JSON.parseObject(options.get("projection").toString(), Document.class);
            foauo.projection(projection);
        }
        if (options.containsKey("maxTimeMS")) {
            foauo.maxTime(Long.valueOf(options.get("maxTimeMS").toString()), TimeUnit.MILLISECONDS);
        }
        foauo.bypassDocumentValidation(options.getBoolean("validation", true));
        foauo.upsert(options.getBoolean("upsert", false));
        foauo.returnDocument(options.getBoolean("returnNew", false) ? com.mongodb.client.model.ReturnDocument.AFTER : com.mongodb.client.model.ReturnDocument.BEFORE);
        return fromObjectId(proxy.findOneAndUpdate(toObjectId(filter), update, foauo));
    }

    /**
     * Drops this collection from the Database.
     *
     * mongodb.driver.manual reference/command/drop/ Drop Collection
     */
    public void drop() {
        proxy.drop();
    }

    /**
     * close collection from the Database.
     *
     */
    public void close() {
        mongoClient.close();
    }

    /**
     * Create an index with the given keys.
     *
     * @param keys an object describing the index key(s), which may not be null.
     * @return the index name mongodb.driver.manual
     * reference/command/createIndexes Create indexes
     */
    public String createIndex(Document keys) {
        return proxy.createIndex(keys);
    }

    /**
     * Create an index with the given keys and options.
     *
     * @param keys an object describing the index key(s), which may not be null.
     * @param options the options for the index
     * @return the index name mongodb.driver.manual
     * reference/command/createIndexes Create indexes
     */
    public String createIndex(Document keys, Document options) {
        IndexOptions io = new IndexOptions();
        if (options.containsKey("background")) {
            io.background(options.getBoolean("background", false));
        }
        if (options.containsKey("unique")) {
            io.unique(options.getBoolean("unique", false));
        }
        if (options.containsKey("sparse")) {
            io.sparse(options.getBoolean("sparse", false));
        }
        if (options.containsKey("name")) {
            io.name(options.get("name").toString());
        }
        if (options.containsKey("expireAfterSeconds")) {
            io.expireAfter(Long.valueOf(options.get("expireAfterSeconds").toString()), TimeUnit.SECONDS);
        }
        if (options.containsKey("version")) {
            io.version(Integer.valueOf(options.get("expireAfterSeconds").toString()));
        }
        if (options.containsKey("weights")) {
            io.weights(JSON.parseObject(options.get("weights").toString(), Document.class));
        }
        if (options.containsKey("storageEngine")) {
            io.storageEngine(JSON.parseObject(options.get("storageEngine").toString(), Document.class));
        }
        if (options.containsKey("partialFilterExpression")) {
            io.partialFilterExpression(JSON.parseObject(options.get("partialFilterExpression").toString(), Document.class));
        }
        if (options.containsKey("weights")) {
            io.weights(JSON.parseObject(options.get("weights").toString(), Document.class));
        }
        if (options.containsKey("bits")) {
            io.bits(Integer.valueOf(options.get("bits").toString()));
        }
        if (options.containsKey("sphereVersion")) {
            io.sphereVersion(Integer.valueOf(options.get("sphereVersion").toString()));
        }
        if (options.containsKey("textVersion")) {
            io.textVersion(Integer.valueOf(options.get("textVersion").toString()));
        }
        if (options.containsKey("min")) {
            io.min(Double.valueOf(options.get("min").toString()));
        }
        if (options.containsKey("max")) {
            io.max(Double.valueOf(options.get("max").toString()));
        }
        if (options.containsKey("bucketSize")) {
            io.bucketSize(Double.valueOf(options.get("bucketSize").toString()));
        }
        if (options.containsKey("defaultLanguage")) {
            io.defaultLanguage(options.get("defaultLanguage").toString());
        }
        if (options.containsKey("languageOverride")) {
            io.languageOverride(options.get("languageOverride").toString());
        }
        return proxy.createIndex(keys, io);
    }

    /**
     * Create multiple indexes.
     *
     * @param indexes the list of indexes
     * @return the list of index names mongodb.driver.manual
     * reference/command/createIndexes Create indexes mongodb.server.release 2.6
     */
    public List<String> createIndexes(List<Document> indexes) {
        ArrayList<String> rets = new ArrayList();
        for (Document document : indexes) {
            rets.add(createIndex(JSON.parseObject(document.get("keys").toString(), Document.class), JSON.parseObject(document.get("options").toString(), Document.class)));
        }
        return rets;
    }

    /**
     * Get all the indexes in this collection.
     *
     * @return the list indexes iterable interface mongodb.driver.manual
     * reference/command/listIndexes/ List indexes
     */
    public ListIndexesIterable<Document> listIndexes() {
        return proxy.listIndexes();
    }

    /**
     * Drops the index given its name.
     *
     * @param keys the name of the index to remove mongodb.driver.manual
     * reference/command/dropIndexes/ Drop indexes
     */
    public void dropIndex(String keys) {
        proxy.dropIndex(keys);
    }

    /**
     * Drops the index given the keys used to create it.
     *
     * @param keys the keys of the index to remove mongodb.driver.manual
     * reference/command/dropIndexes/ Drop indexes
     */
    public void dropIndex(Document keys) {
        proxy.dropIndex(keys);
    }

    /**
     * Drop all the indexes on this collection, except for the default on _id.
     *
     * mongodb.driver.manual reference/command/dropIndexes/ Drop indexes
     */
    public void dropIndexes() {
        proxy.dropIndexes();
    }

    /**
     * Rename the collection with oldCollectionName to the newCollectionName.
     *
     * @param newCollectionNamespace the newCollectionNamespace the collection
     * will be renamed to
     * @throws com.mongodb.MongoServerException if you provide a
     * newCollectionName that is the name of an existing collection, or if the
     * oldCollectionName is the name of a collection that doesn't exist
     * mongodb.driver.manual reference/commands/renameCollection Rename
     * collection
     */
    public void renameCollection(Document newCollectionNamespace) {
        this.renameCollection(newCollectionNamespace, null);
    }

    /**
     * Rename the collection with oldCollectionName to the newCollectionName.
     *
     * @param newCollectionNamespace the name the collection will be renamed to
     * @param options the options for renaming a collection
     * @throws com.mongodb.MongoServerException if you provide a
     * newCollectionName that is the name of an existing collection and
     * dropTarget is false, or if the oldCollectionName is the name of a
     * collection that doesn't exist mongodb.driver.manual
     * reference/commands/renameCollection Rename collection
     */
    public void renameCollection(Document newCollectionNamespace, Document options) {
        MongoNamespace mn = null;
        String fullName = newCollectionNamespace.getString("fullName");
        if (fullName != null && !fullName.isEmpty()) {
            mn = new MongoNamespace(fullName);
        } else {
            String databaseName = newCollectionNamespace.getString("databaseName");
            String collectionName = newCollectionNamespace.getString("collectionName");

            if (databaseName == null || databaseName.isEmpty()) {
                databaseName = proxy.getNamespace().getDatabaseName();
            }
            if (collectionName == null || collectionName.isEmpty()) {
                collectionName = proxy.getNamespace().getCollectionName();
            }
            mn = new MongoNamespace(databaseName, collectionName);
        }
        if (mn != null) {
            if (options != null) {
                RenameCollectionOptions rco = new RenameCollectionOptions();
                rco.dropTarget(options.getBoolean("dropTarget", false));
                proxy.renameCollection(mn, rco);
            } else {
                proxy.renameCollection(mn);
            }
        }
    }

    private Document fromObjectId(Document document) {
        if (document != null && document.containsKey("_id")) {
            Object id = document.get("_id");
            document.replace("_id", id.toString());
        }
        return document;
    }

    private Document toObjectId(Document document) {
        String _id = document.getString("_id");
        if (_id != null && _id.length() == 24) {
            document.replace("_id", new org.bson.types.ObjectId(_id));
        }
        return document;
    }

    private com.mongodb.client.model.UpdateOptions toUpdateOptions(Document options) {
        com.mongodb.client.model.UpdateOptions uo = new com.mongodb.client.model.UpdateOptions();
        uo.bypassDocumentValidation(options.getBoolean("validation", false));
        uo.upsert(options.getBoolean("upsert", false));
        return uo;
    }

    private ArrayList<Document> conv(FindIterable<Document> cursors) {
        ArrayList<Document> list = new ArrayList<>();
        try (MongoCursor<Document> ret = cursors.iterator()) {
            while (ret.hasNext()) {
                Document document = ret.next();
                Object id = document.get("_id");
                if (id != null) {
                    document.replace("_id", id.toString());
                }
                list.add(document);
            }
        }
        return list;
    }
}
