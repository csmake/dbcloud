/**
 * Copyright (c) 2012-2018 CSMAKE, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * Generate by csmake for java Thu Jan 04 16:57:11 CST 2018
 * support for: www.csmake.com
 */
package org.dbcloud.mongodb;
import java.io.IOException;
import java.io.PrintWriter;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * The MongoCollectionServlet interface.
 *
 * <p>Note: Generate by csmake for java .</p>
 *
 * {@link org.dbcloud.mongodb.MongoCollection}
 * @author www.csmake.com
 */
public class MongoCollectionServlet extends HttpServlet{
    public MongoCollectionServlet(){
        _maps = new java.util.HashMap<>();
        classLoader = new com.csmake.ClassLoader();
    }
    @Override
    public void init() {
        _params = new java.util.HashMap<>();
        java.util.Enumeration<String> names = this.getInitParameterNames();
        while (names.hasMoreElements()) {
            String name = names.nextElement();
            _params.put(name, this.getInitParameter(name));
        }
    }
    /**
    * Handles the HTTP <code>GET</code> method.
    *
    * @param request servlet request
    * @param response servlet response
    * @throws ServletException if a servlet-specific error occurs
    * @throws IOException if an I/O error occurs
    */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException
    {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter())
        {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>com.csmake.com</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>post method for the <span style='color:blue'> org.dbcloud.mongodb.MongoCollection</span> servlet call</h1>");
            out.println("<h2>more infomation goto <a style='color:black' href='http://com.csmake.com'><strong> www.csmake.com </strong></a> ...</h2>");
            out.println("</body>");
            out.println("</html>");
        }
    }
    /**
    * Handles the HTTP <code>POST</code> method.
    *
    * @param request servlet request
    * @param response servlet response
    * @throws ServletException if a servlet-specific error occurs
    * @throws IOException if an I/O error occurs
    */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json;charset=UTF-8");
        try{
                com.csmake.JSONInvoke invoke = JSON.parseObject(request.getInputStream(), com.csmake.JSONInvoke.class);
                java.util.HashMap<String, Object> ctx= new java.util.HashMap();
                ctx.put("request", request);
                ctx.put("response", response);
                com.csmake.JSONReturn ret = Recive(classLoader, invoke, ctx);
                byte[] utf8 = JSON.toJSONString(ret).getBytes("UTF-8");
                String ac = request.getHeader("Accept-Encoding");
                boolean zipFlag = ac != null && ac.toLowerCase().contains("gzip") && utf8.length > 999999;
                if (zipFlag) {
                    response.addHeader("Content-Encoding", "gzip");
                    try (java.util.zip.GZIPOutputStream gos = new java.util.zip.GZIPOutputStream(response.getOutputStream())) {
                        gos.write(utf8);
                        gos.flush();
                    }
                } else {
                    response.setContentLength(utf8.length);
                    try (javax.servlet.ServletOutputStream out = response.getOutputStream()) {
                        out.write(utf8);
                    }
                }
        } catch (Exception ex) {
            StringBuilder sb = new StringBuilder();
            sb.append("{error:500,message:\"").append(ex.toString()).append("\",value:''}");
            byte[] utf8 = sb.toString().getBytes("UTF-8");
            response.setContentLength(utf8.length);
            try (javax.servlet.ServletOutputStream out = response.getOutputStream()) {
            out.write(utf8);
            }
        }
    }
    private com.csmake.JSONReturn Recive(com.csmake.ClassLoader loader, com.csmake.JSONInvoke context, java.util.HashMap<String, Object> ctx)
    {
        int errorCode = 0;
        Object retValue = null;
        String exception = "";
        com.csmake.GuidObject<org.dbcloud.mongodb.MongoCollection> guidObject = _maps.get(context.guid);
        try
        {
            switch (context.symbol){
                case "constructor" :{ 
                    HttpServletRequest request = ((HttpServletRequest)ctx.get("request"));
                    String guid=this.getInitParameter("guid");
                    if ("class".equals(guid)) {
                        guidObject = _maps.get("org.dbcloud.mongodb.MongoCollection");
                        guid = "org.dbcloud.mongodb.MongoCollection";
                    } else if ("host".equals(guid)) {
                        guidObject = _maps.get(request.getRemoteHost());
                        guid = request.getRemoteHost();
                    }
                     if (guidObject == null) {
                        Class<?> clazz = loader.getInstance().loadClass(context.guid);
                        if(clazz!=null){
                            java.lang.reflect.Constructor con = clazz.getConstructor(java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class); 
                            Object obj = con.newInstance((String)(context.args.get("0")), (String)(context.args.get("1")), (String)(context.args.get("2")), (String)(context.args.get("3")), (String)(context.args.get("4")));
                            guidObject = new com.csmake.GuidObject((org.dbcloud.mongodb.MongoCollection)obj);
                            guid=this.getInitParameter("guid");
                            if (guid == null) {
                                context.guid = java.util.UUID.randomUUID().toString();
                            } else if ("class".equals(guid)) {
                                context.guid = "org.dbcloud.mongodb.MongoCollection";
                            } else if ("host".equals(guid)) {
                                context.guid = request.getRemoteHost();
                            }else{
                                context.guid = guid;
                            }
                            try{
                                java.lang.reflect.Method m = clazz.getDeclaredMethod("csmakeInit", _params.getClass());
                                _params.replace("guid", context.guid);
                                m.invoke(obj, _params);
                            }catch(Exception ex){
                            }
                            _maps.put(context.guid, guidObject);
                        }
                    }else{context.guid=guid;}
                    retValue = guidObject == null ? "": context.guid;
                    break;
                }
                case "constructor2" :{ 
                    HttpServletRequest request = ((HttpServletRequest)ctx.get("request"));
                    String guid=this.getInitParameter("guid");
                    if ("class".equals(guid)) {
                        guidObject = _maps.get("org.dbcloud.mongodb.MongoCollection");
                        guid = "org.dbcloud.mongodb.MongoCollection";
                    } else if ("host".equals(guid)) {
                        guidObject = _maps.get(request.getRemoteHost());
                        guid = request.getRemoteHost();
                    }
                     if (guidObject == null) {
                        Class<?> clazz = loader.getInstance().loadClass(context.guid);
                        if(clazz!=null){
                            java.lang.reflect.Constructor con = clazz.getConstructor(); 
                            Object obj = con.newInstance();
                            guidObject = new com.csmake.GuidObject((org.dbcloud.mongodb.MongoCollection)obj);
                            guid=this.getInitParameter("guid");
                            if (guid == null) {
                                context.guid = java.util.UUID.randomUUID().toString();
                            } else if ("class".equals(guid)) {
                                context.guid = "org.dbcloud.mongodb.MongoCollection";
                            } else if ("host".equals(guid)) {
                                context.guid = request.getRemoteHost();
                            }else{
                                context.guid = guid;
                            }
                            try{
                                java.lang.reflect.Method m = clazz.getDeclaredMethod("csmakeInit", _params.getClass());
                                _params.replace("guid", context.guid);
                                m.invoke(obj, _params);
                            }catch(Exception ex){
                            }
                            _maps.put(context.guid, guidObject);
                        }
                    }else{context.guid=guid;}
                    retValue = guidObject == null ? "": context.guid;
                    break;
                }
                case "constructor3" :{ 
                    HttpServletRequest request = ((HttpServletRequest)ctx.get("request"));
                    String guid=this.getInitParameter("guid");
                    if ("class".equals(guid)) {
                        guidObject = _maps.get("org.dbcloud.mongodb.MongoCollection");
                        guid = "org.dbcloud.mongodb.MongoCollection";
                    } else if ("host".equals(guid)) {
                        guidObject = _maps.get(request.getRemoteHost());
                        guid = request.getRemoteHost();
                    }
                     if (guidObject == null) {
                        Class<?> clazz = loader.getInstance().loadClass(context.guid);
                        if(clazz!=null){
                            java.lang.reflect.Constructor con = clazz.getConstructor(java.lang.String.class, java.lang.String.class); 
                            Object obj = con.newInstance((String)(context.args.get("0")), (String)(context.args.get("1")));
                            guidObject = new com.csmake.GuidObject((org.dbcloud.mongodb.MongoCollection)obj);
                            guid=this.getInitParameter("guid");
                            if (guid == null) {
                                context.guid = java.util.UUID.randomUUID().toString();
                            } else if ("class".equals(guid)) {
                                context.guid = "org.dbcloud.mongodb.MongoCollection";
                            } else if ("host".equals(guid)) {
                                context.guid = request.getRemoteHost();
                            }else{
                                context.guid = guid;
                            }
                            try{
                                java.lang.reflect.Method m = clazz.getDeclaredMethod("csmakeInit", _params.getClass());
                                _params.replace("guid", context.guid);
                                m.invoke(obj, _params);
                            }catch(Exception ex){
                            }
                            _maps.put(context.guid, guidObject);
                        }
                    }else{context.guid=guid;}
                    retValue = guidObject == null ? "": context.guid;
                    break;
                }
                case "constructor4" :{ 
                    HttpServletRequest request = ((HttpServletRequest)ctx.get("request"));
                    String guid=this.getInitParameter("guid");
                    if ("class".equals(guid)) {
                        guidObject = _maps.get("org.dbcloud.mongodb.MongoCollection");
                        guid = "org.dbcloud.mongodb.MongoCollection";
                    } else if ("host".equals(guid)) {
                        guidObject = _maps.get(request.getRemoteHost());
                        guid = request.getRemoteHost();
                    }
                     if (guidObject == null) {
                        Class<?> clazz = loader.getInstance().loadClass(context.guid);
                        if(clazz!=null){
                            java.lang.reflect.Constructor con = clazz.getConstructor(java.lang.String.class, java.lang.String.class, java.lang.String.class); 
                            Object obj = con.newInstance((String)(context.args.get("0")), (String)(context.args.get("1")), (String)(context.args.get("2")));
                            guidObject = new com.csmake.GuidObject((org.dbcloud.mongodb.MongoCollection)obj);
                            guid=this.getInitParameter("guid");
                            if (guid == null) {
                                context.guid = java.util.UUID.randomUUID().toString();
                            } else if ("class".equals(guid)) {
                                context.guid = "org.dbcloud.mongodb.MongoCollection";
                            } else if ("host".equals(guid)) {
                                context.guid = request.getRemoteHost();
                            }else{
                                context.guid = guid;
                            }
                            try{
                                java.lang.reflect.Method m = clazz.getDeclaredMethod("csmakeInit", _params.getClass());
                                _params.replace("guid", context.guid);
                                m.invoke(obj, _params);
                            }catch(Exception ex){
                            }
                            _maps.put(context.guid, guidObject);
                        }
                    }else{context.guid=guid;}
                    retValue = guidObject == null ? "": context.guid;
                    break;
                }
                case "constructor5" :{ 
                    HttpServletRequest request = ((HttpServletRequest)ctx.get("request"));
                    String guid=this.getInitParameter("guid");
                    if ("class".equals(guid)) {
                        guidObject = _maps.get("org.dbcloud.mongodb.MongoCollection");
                        guid = "org.dbcloud.mongodb.MongoCollection";
                    } else if ("host".equals(guid)) {
                        guidObject = _maps.get(request.getRemoteHost());
                        guid = request.getRemoteHost();
                    }
                     if (guidObject == null) {
                        Class<?> clazz = loader.getInstance().loadClass(context.guid);
                        if(clazz!=null){
                            java.lang.reflect.Constructor con = clazz.getConstructor(java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class); 
                            Object obj = con.newInstance((String)(context.args.get("0")), (String)(context.args.get("1")), (String)(context.args.get("2")), (String)(context.args.get("3")));
                            guidObject = new com.csmake.GuidObject((org.dbcloud.mongodb.MongoCollection)obj);
                            guid=this.getInitParameter("guid");
                            if (guid == null) {
                                context.guid = java.util.UUID.randomUUID().toString();
                            } else if ("class".equals(guid)) {
                                context.guid = "org.dbcloud.mongodb.MongoCollection";
                            } else if ("host".equals(guid)) {
                                context.guid = request.getRemoteHost();
                            }else{
                                context.guid = guid;
                            }
                            try{
                                java.lang.reflect.Method m = clazz.getDeclaredMethod("csmakeInit", _params.getClass());
                                _params.replace("guid", context.guid);
                                m.invoke(obj, _params);
                            }catch(Exception ex){
                            }
                            _maps.put(context.guid, guidObject);
                        }
                    }else{context.guid=guid;}
                    retValue = guidObject == null ? "": context.guid;
                    break;
                }
                /** public methods */
                case "count" :{ 
                    org.bson.Document filter = JSON.parseObject(context.args.get("0").toString(), org.bson.Document.class);
                    org.bson.Document options = JSON.parseObject(context.args.get("1").toString(), org.bson.Document.class);
                    retValue = guidObject.getObject().count(filter, options);
                    break;
                }
                case "count2" :{ 
                    org.bson.Document filter = JSON.parseObject(context.args.get("0").toString(), org.bson.Document.class);
                    retValue = guidObject.getObject().count(filter);
                    break;
                }
                case "count3" :{ 
                    retValue = guidObject.getObject().count();
                    break;
                }
                case "find" :{ 
                    retValue = guidObject.getObject().find();
                    break;
                }
                case "find2" :{ 
                    org.bson.Document filter = JSON.parseObject(context.args.get("0").toString(), org.bson.Document.class);
                    retValue = guidObject.getObject().find(filter);
                    break;
                }
                case "find3" :{ 
                    org.bson.Document filter = JSON.parseObject(context.args.get("0").toString(), org.bson.Document.class);
                    int skip = (int)(context.args.get("1"));
                    int limit = (int)(context.args.get("2"));
                    retValue = guidObject.getObject().find(filter, skip, limit);
                    break;
                }
                case "find4" :{ 
                    org.bson.Document filter = JSON.parseObject(context.args.get("0").toString(), org.bson.Document.class);
                    org.bson.Document options = JSON.parseObject(context.args.get("1").toString(), org.bson.Document.class);
                    retValue = guidObject.getObject().find(filter, options);
                    break;
                }
                case "find5" :{ 
                    org.bson.Document filter = JSON.parseObject(context.args.get("0").toString(), org.bson.Document.class);
                    org.bson.Document options = JSON.parseObject(context.args.get("1").toString(), org.bson.Document.class);
                    int skip = (int)(context.args.get("2"));
                    int limit = (int)(context.args.get("3"));
                    retValue = guidObject.getObject().find(filter, options, skip, limit);
                    break;
                }
                case "close" :{ 
                    guidObject.getObject().close();
                    break;
                }
                case "getNamespace" :{ 
                    retValue = guidObject.getObject().getNamespace();
                    break;
                }
                case "findOneAndReplace" :{ 
                    org.bson.Document filter = JSON.parseObject(context.args.get("0").toString(), org.bson.Document.class);
                    org.bson.Document replacement = JSON.parseObject(context.args.get("1").toString(), org.bson.Document.class);
                    retValue = guidObject.getObject().findOneAndReplace(filter, replacement);
                    break;
                }
                case "findOneAndReplace2" :{ 
                    org.bson.Document filter = JSON.parseObject(context.args.get("0").toString(), org.bson.Document.class);
                    org.bson.Document replacement = JSON.parseObject(context.args.get("1").toString(), org.bson.Document.class);
                    org.bson.Document options = JSON.parseObject(context.args.get("2").toString(), org.bson.Document.class);
                    retValue = guidObject.getObject().findOneAndReplace(filter, replacement, options);
                    break;
                }
                case "getDocumentClass" :{ 
                    retValue = guidObject.getObject().getDocumentClass();
                    break;
                }
                case "findOneAndUpdate" :{ 
                    org.bson.Document filter = JSON.parseObject(context.args.get("0").toString(), org.bson.Document.class);
                    org.bson.Document update = JSON.parseObject(context.args.get("1").toString(), org.bson.Document.class);
                    org.bson.Document options = JSON.parseObject(context.args.get("2").toString(), org.bson.Document.class);
                    retValue = guidObject.getObject().findOneAndUpdate(filter, update, options);
                    break;
                }
                case "findOneAndUpdate2" :{ 
                    org.bson.Document filter = JSON.parseObject(context.args.get("0").toString(), org.bson.Document.class);
                    org.bson.Document update = JSON.parseObject(context.args.get("1").toString(), org.bson.Document.class);
                    retValue = guidObject.getObject().findOneAndUpdate(filter, update);
                    break;
                }
                case "createIndex" :{ 
                    org.bson.Document keys = JSON.parseObject(context.args.get("0").toString(), org.bson.Document.class);
                    retValue = guidObject.getObject().createIndex(keys);
                    break;
                }
                case "createIndex2" :{ 
                    org.bson.Document keys = JSON.parseObject(context.args.get("0").toString(), org.bson.Document.class);
                    org.bson.Document options = JSON.parseObject(context.args.get("1").toString(), org.bson.Document.class);
                    retValue = guidObject.getObject().createIndex(keys, options);
                    break;
                }
                case "dropIndex" :{ 
                    org.bson.Document keys = JSON.parseObject(context.args.get("0").toString(), org.bson.Document.class);
                    guidObject.getObject().dropIndex(keys);
                    break;
                }
                case "dropIndex2" :{ 
                    java.lang.String keys = (String)(context.args.get("0"));
                    guidObject.getObject().dropIndex(keys);
                    break;
                }
                case "updateMany" :{ 
                    org.bson.Document filter = JSON.parseObject(context.args.get("0").toString(), org.bson.Document.class);
                    org.bson.Document update = JSON.parseObject(context.args.get("1").toString(), org.bson.Document.class);
                    org.bson.Document options = JSON.parseObject(context.args.get("2").toString(), org.bson.Document.class);
                    retValue = guidObject.getObject().updateMany(filter, update, options);
                    break;
                }
                case "updateMany2" :{ 
                    org.bson.Document filter = JSON.parseObject(context.args.get("0").toString(), org.bson.Document.class);
                    org.bson.Document update = JSON.parseObject(context.args.get("1").toString(), org.bson.Document.class);
                    retValue = guidObject.getObject().updateMany(filter, update);
                    break;
                }
                case "insertOne" :{ 
                    org.bson.Document document = JSON.parseObject(context.args.get("0").toString(), org.bson.Document.class);
                    retValue = guidObject.getObject().insertOne(document);
                    break;
                }
                case "insertOne2" :{ 
                    org.bson.Document document = JSON.parseObject(context.args.get("0").toString(), org.bson.Document.class);
                    org.bson.Document options = JSON.parseObject(context.args.get("1").toString(), org.bson.Document.class);
                    retValue = guidObject.getObject().insertOne(document, options);
                    break;
                }
                case "drop" :{ 
                    guidObject.getObject().drop();
                    break;
                }
                case "createIndexes" :{ 
                    java.util.List indexes = JSONObject.parseArray(((JSONArray)context.args.get("0")).toJSONString(), org.bson.Document.class);
                    retValue = guidObject.getObject().createIndexes(indexes);
                    break;
                }
                case "getCodecRegistry" :{ 
                    retValue = guidObject.getObject().getCodecRegistry();
                    break;
                }
                case "findOneAndDelete" :{ 
                    org.bson.Document filter = JSON.parseObject(context.args.get("0").toString(), org.bson.Document.class);
                    retValue = guidObject.getObject().findOneAndDelete(filter);
                    break;
                }
                case "findOneAndDelete2" :{ 
                    org.bson.Document filter = JSON.parseObject(context.args.get("0").toString(), org.bson.Document.class);
                    org.bson.Document options = JSON.parseObject(context.args.get("1").toString(), org.bson.Document.class);
                    retValue = guidObject.getObject().findOneAndDelete(filter, options);
                    break;
                }
                case "insertMany" :{ 
                    java.util.List documents = JSONObject.parseArray(((JSONArray)context.args.get("0")).toJSONString(), org.bson.Document.class);
                    retValue = guidObject.getObject().insertMany(documents);
                    break;
                }
                case "insertMany2" :{ 
                    java.util.List documents = JSONObject.parseArray(((JSONArray)context.args.get("0")).toJSONString(), org.bson.Document.class);
                    org.bson.Document options = JSON.parseObject(context.args.get("1").toString(), org.bson.Document.class);
                    retValue = guidObject.getObject().insertMany(documents, options);
                    break;
                }
                case "dropIndexes" :{ 
                    guidObject.getObject().dropIndexes();
                    break;
                }
                case "updateOne" :{ 
                    org.bson.Document filter = JSON.parseObject(context.args.get("0").toString(), org.bson.Document.class);
                    org.bson.Document update = JSON.parseObject(context.args.get("1").toString(), org.bson.Document.class);
                    retValue = guidObject.getObject().updateOne(filter, update);
                    break;
                }
                case "updateOne2" :{ 
                    org.bson.Document filter = JSON.parseObject(context.args.get("0").toString(), org.bson.Document.class);
                    org.bson.Document update = JSON.parseObject(context.args.get("1").toString(), org.bson.Document.class);
                    org.bson.Document options = JSON.parseObject(context.args.get("2").toString(), org.bson.Document.class);
                    retValue = guidObject.getObject().updateOne(filter, update, options);
                    break;
                }
                case "deleteMany" :{ 
                    org.bson.Document filter = JSON.parseObject(context.args.get("0").toString(), org.bson.Document.class);
                    retValue = guidObject.getObject().deleteMany(filter);
                    break;
                }
                case "replaceOne" :{ 
                    org.bson.Document filter = JSON.parseObject(context.args.get("0").toString(), org.bson.Document.class);
                    org.bson.Document replacement = JSON.parseObject(context.args.get("1").toString(), org.bson.Document.class);
                    org.bson.Document options = JSON.parseObject(context.args.get("2").toString(), org.bson.Document.class);
                    retValue = guidObject.getObject().replaceOne(filter, replacement, options);
                    break;
                }
                case "replaceOne2" :{ 
                    org.bson.Document filter = JSON.parseObject(context.args.get("0").toString(), org.bson.Document.class);
                    org.bson.Document replacement = JSON.parseObject(context.args.get("1").toString(), org.bson.Document.class);
                    retValue = guidObject.getObject().replaceOne(filter, replacement);
                    break;
                }
                case "deleteOne" :{ 
                    org.bson.Document filter = JSON.parseObject(context.args.get("0").toString(), org.bson.Document.class);
                    retValue = guidObject.getObject().deleteOne(filter);
                    break;
                }
                case "listIndexes" :{ 
                    retValue = guidObject.getObject().listIndexes();
                    break;
                }
                case "renameCollection" :{ 
                    org.bson.Document newCollectionNamespace = JSON.parseObject(context.args.get("0").toString(), org.bson.Document.class);
                    guidObject.getObject().renameCollection(newCollectionNamespace);
                    break;
                }
                case "renameCollection2" :{ 
                    org.bson.Document newCollectionNamespace = JSON.parseObject(context.args.get("0").toString(), org.bson.Document.class);
                    org.bson.Document options = JSON.parseObject(context.args.get("1").toString(), org.bson.Document.class);
                    guidObject.getObject().renameCollection(newCollectionNamespace, options);
                    break;
                }
                case "wait" :{ 
                    guidObject.getObject().wait();
                    break;
                }
                case "wait2" :{ 
                    long arg0 = Long.valueOf(context.args.get("0").toString());
                    int arg1 = (int)(context.args.get("1"));
                    guidObject.getObject().wait(arg0, arg1);
                    break;
                }
                case "wait3" :{ 
                    long arg0 = Long.valueOf(context.args.get("0").toString());
                    guidObject.getObject().wait(arg0);
                    break;
                }
                case "equals" :{ 
                    java.lang.Object arg0 = JSONObject.parseArray(((JSONArray)context.args.get("0")).toJSONString(), org.bson.Document.class);
                    retValue = guidObject.getObject().equals(arg0);
                    break;
                }
                case "toString" :{ 
                    retValue = guidObject.getObject().toString();
                    break;
                }
                case "hashCode" :{ 
                    retValue = guidObject.getObject().hashCode();
                    break;
                }
                case "getClass" :{ 
                    retValue = guidObject.getObject().getClass();
                    break;
                }
                case "notify" :{ 
                    guidObject.getObject().notify();
                    break;
                }
                case "notifyAll" :{ 
                    guidObject.getObject().notifyAll();
                    break;
                }
                case "set_guid" :{
                    String guid = (String)(context.args.get("0"));
                    if(guidObject != null){
                        _maps.remove(context.guid);
                        _maps.put(guid, guidObject);
                    }
                    break;
                }
                default:{
                    break;
                }
            }
        }
        catch(Exception ex){
            errorCode = 500;
            if(guidObject == null || guidObject.getObject()== null){
                exception="org.dbcloud.mongodb.MongoCollection is null pointer.";
            }else{
                exception = ex.toString();
            }
        }
        return new com.csmake.JSONReturn(errorCode, exception, retValue);
    }
    private final com.csmake.ClassLoader classLoader;
    private java.util.HashMap<String, String > _params = null;
    private java.util.HashMap<String, com.csmake.GuidObject<org.dbcloud.mongodb.MongoCollection> > _maps = null;
}//end of class MongoCollection
