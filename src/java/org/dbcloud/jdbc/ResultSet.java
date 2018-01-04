/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.dbcloud.jdbc;
import com.alibaba.fastjson.JSON;
import java.util.*;
import org.bson.*;
        
/**
 *
 * @author kylin
 */
public class ResultSet {
    public ArrayList<Document> columns;
    public ArrayList<Object>   rows;
    public Document error=null;
    
    public ResultSet(){
    }
    
    public ResultSet (Document doc){        
        this.columns = (ArrayList<Document>)doc.get("columns");
        this.rows = (ArrayList<Object>)doc.get("rows");
        this.error = (Document)doc.get("error");
    }
    public static ResultSet ToResultSet(Document doc){
        return new ResultSet(doc); 
    }
}
