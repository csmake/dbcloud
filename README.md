![dbcloud](https://avatars1.githubusercontent.com/u/17017373?s=460&v=4)

# dbcloud

## About us

Accessing server-side database through JavaScript API.

Database support `Mongodb` `Oracle` `MySQL` `sqlserver` `SQLite`. 

Client javascript api support `IE6.0+` `Chrome` `FireFox` `Wechat`

You can do almost invoke on database through the javascript API in browser.

The client javascript api support `IE6.0+ Chrome FireFox and Wechat`

web: http://www.dbcloud.org

mail: dbcloud@csmake.com

## Application scenarios

* Rapid prototyping does not need to write server-side code.

* Academic teaching and research, WYSIWYG

* Enterprise internal application

* Other applications that do not focus on the security of the database table structure.

## Roadmap

* 1.0: mongodb support. see [webmongo](https://github.com/csmake/webmongo)

* 1.1: jdbc webclient javascript api support.

* 1.2: SQLite support

* 1.3: MySQL support

* 1.4: Sql server support

* 1.5: Oracle support

* ...

# MongoDB Development Manual

## Start the basic project 

1. Run mongod first. 
2. Put dbcloud.war in your servlet server webapps ,Apache Tomcat8.0 , Glass Fish Server4.x and so on.
3. Open browser(Chrome FireFox IE) and put http://localhost:8080/dbcloud/, The address may be different depending on your settings

## Start to write your web application

1. Copy MongoCollection.java and MongoCollectionServlet.java from dbcloud.war /src/java/.. , into your src/java/org/dbcloud/mongodb

2. Copy all *.js files in dbcloud.war /js to your js directory. JQuery is necessary. IE6.0 need to use the 1.x version and json2.js is necessary.

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

# MySQL Development Manual

## Start the basic project 

1. Run MySQL first. 
2. Put webmysql.war in your servlet server webapps ,Apache Tomcat8.0 , Glass Fish Server4.x and so on.
3. Open browser(Chrome FireFox IE) and put http://localhost:8080/webmysql/, The address may be different depending on your settings

## Start to write your web application

1. Copy Statement.java and StatementServlet.java from webmysql.war /src/java/.. , into your src/java/org/dbcloud/mysql

2. Copy all *.js files in webmysql.war /js to your js directory. JQuery is necessary. IE6.0 need to use the 1.x version and json2.js is necessary.

3. Edit the web.xml, like this:
```xml

	<servlet>
        <servlet-name>MySQLStatement</servlet-name>
        <servlet-class>org.dbcloud.mysql.StatementServlet</servlet-class>
        <init-param>
            <param-name>guid</param-name>
            <param-value>class</param-value>
        </init-param>
        <init-param>
            <param-name>driver</param-name>
            <param-value>com.mysql.jdbc.Driver</param-value>
        </init-param>
        <init-param>
            <param-name>connection</param-name>
            <param-value>jdbc:mysql://localhost:3306/yourdbname?useUnicode=true&amp;characterEncoding=utf8&amp;autoReconnect=true&amp;failOverReadOnly=false</param-value>
        </init-param>
        <init-param>
            <param-name>user</param-name>
            <param-value>test</param-value>
        </init-param>
        <init-param>
            <param-name>password</param-name>
            <param-value>test</param-value>
        </init-param>
    </servlet>
    <servlet-mapping>
        <servlet-name>MySQLStatement</servlet-name>
        <url-pattern>/org.dbcloud.mysql.Statement</url-pattern>
    </servlet-mapping>
```
4. Edit your html file, you can see the index.html 

```javascript
	﻿<!DOCTYPE html>
	<html >
    <head>
        <title>MySQL demo</title>
    </head>
    <body>
        <div id='log'></div>
    </body>
    <script src="js/jQuery-1.12.4.js"></script>
    <script src="js/org.dbcloud.mysql.Statement.js"></script> 
    <script>
        function log(msg) {
            $("#log").append("<p>" + msg.replace("\n", "</p><p>") + "</p>");
        }
        try {
            log("MySQL Test:");
            var stmt = new org.dbcloud.mysql.Statement();
            if (stmt) {
                var result;
                log("create table:" + JSON.stringify(stmt.execute("create table if not exists temp_user(name char(50) ,id int ) ")));
                var start = new Date().getTime();
                var total = 0;
                for (var j = 0; j < 10; j++) {
                    var sqls = [];
                    for (var i = 0; i < 10; i++) {
                        sqls.push("insert into temp_user(name,id) values('names" + i + "'," + i + ")");
                        total++;
                    }
                    log("" + JSON.stringify(stmt.execute(sqls)) + "");
                }
                log((new Date().getTime() - start) / 1000 + "'s insert " + total + " records!");

                result = stmt.executeQuery("select name ,id  from temp_user", {document: true});
                log("Synchronize the query and return the document structure data, which can be operated directly as a JSON object:");
                log(JSON.stringify(result));
                if (result.rows && result.rows.length > 0)
                {
                    log(result.rows[0].name);
                }
                log("Synchronize query and return two dimensional table structure data:");
                result = stmt.executeQuery("select name ,id  from temp_user");
                log(JSON.stringify(result));
                if (result.error) {
                    log("error:" + result.error.message + "\ncode:" + result.error.code + "\nSQLState:" + result.error.SQLState);
                }
                stmt.executeQuery({done: function (r) {
                        log("Asynchronous query:" + JSON.stringify(r));
                    }, fail: function (e) {
                        log(e.message);
                    }, always: function () {
                        log("Deleting the datasheet and closing the data link");
                        stmt.execute("drop table temp_user")
                        //stmt.close();//If a server shares a link, it should not be closed.
                    }}, "select * from temp_user", {document: false});
            }
        } catch (e) {
            log(e.message);
        }
    </script>
</html>

```
