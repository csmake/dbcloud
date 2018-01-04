/*! \mainpage Development Manual
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
## The tutorial
//!- \subpage mongo 

//!- \subpage mysql 
 */
 


package org.dbcloud;

/**
 *
 * @author kylin
 */
public class Version {    
    public  String ToString(){
        return "1.1";
    }
}
