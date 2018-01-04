/**
 * Copyright (c) 2012-2017 CSMAKE, Inc.
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
 * Generate by csmake for java Tue Nov 07 16:42:02 CST 2017
 * support for: www.csmake.com
 */
package org.dbcloud.jdbc;
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
 * The StatementServlet interface.
 *
 * <p>Note: Generate by csmake for java .</p>
 *
 * {@link org.dbcloud.jdbc.Statement}
 * @author www.csmake.com
 */
public class StatementServlet extends HttpServlet{
    public StatementServlet(){
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
            out.println("<h1>post method for the <span style='color:blue'> org.dbcloud.jdbc.Statement</span> servlet call</h1>");
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
        com.csmake.GuidObject<org.dbcloud.jdbc.Statement> guidObject = _maps.get(context.guid);
        try
        {
            switch (context.symbol){
                case "constructor" :{ 
                    HttpServletRequest request = ((HttpServletRequest)ctx.get("request"));
                    String guid=this.getInitParameter("guid");
                    if ("class".equals(guid)) {
                        guidObject = _maps.get("org.dbcloud.jdbc.Statement");
                        guid = "org.dbcloud.jdbc.Statement";
                    } else if ("host".equals(guid)) {
                        guidObject = _maps.get(request.getRemoteHost());
                        guid = request.getRemoteHost();
                    }
                     if (guidObject == null) {
                        Class<?> clazz = loader.getInstance().loadClass(context.guid);
                        if(clazz!=null){
                            java.lang.reflect.Constructor con = clazz.getConstructor(); 
                            Object obj = con.newInstance();
                            guidObject = new com.csmake.GuidObject((org.dbcloud.jdbc.Statement)obj);
                            guid=this.getInitParameter("guid");
                            if (guid == null) {
                                context.guid = java.util.UUID.randomUUID().toString();
                            } else if ("class".equals(guid)) {
                                context.guid = "org.dbcloud.jdbc.Statement";
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
                        guidObject = _maps.get("org.dbcloud.jdbc.Statement");
                        guid = "org.dbcloud.jdbc.Statement";
                    } else if ("host".equals(guid)) {
                        guidObject = _maps.get(request.getRemoteHost());
                        guid = request.getRemoteHost();
                    }
                     if (guidObject == null) {
                        Class<?> clazz = loader.getInstance().loadClass(context.guid);
                        if(clazz!=null){
                            java.lang.reflect.Constructor con = clazz.getConstructor(java.lang.String.class, java.lang.String.class); 
                            Object obj = con.newInstance((String)(context.args.get("0")), (String)(context.args.get("1")));
                            guidObject = new com.csmake.GuidObject((org.dbcloud.jdbc.Statement)obj);
                            guid=this.getInitParameter("guid");
                            if (guid == null) {
                                context.guid = java.util.UUID.randomUUID().toString();
                            } else if ("class".equals(guid)) {
                                context.guid = "org.dbcloud.jdbc.Statement";
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
                        guidObject = _maps.get("org.dbcloud.jdbc.Statement");
                        guid = "org.dbcloud.jdbc.Statement";
                    } else if ("host".equals(guid)) {
                        guidObject = _maps.get(request.getRemoteHost());
                        guid = request.getRemoteHost();
                    }
                     if (guidObject == null) {
                        Class<?> clazz = loader.getInstance().loadClass(context.guid);
                        if(clazz!=null){
                            java.lang.reflect.Constructor con = clazz.getConstructor(java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class); 
                            Object obj = con.newInstance((String)(context.args.get("0")), (String)(context.args.get("1")), (String)(context.args.get("2")), (String)(context.args.get("3")));
                            guidObject = new com.csmake.GuidObject((org.dbcloud.jdbc.Statement)obj);
                            guid=this.getInitParameter("guid");
                            if (guid == null) {
                                context.guid = java.util.UUID.randomUUID().toString();
                            } else if ("class".equals(guid)) {
                                context.guid = "org.dbcloud.jdbc.Statement";
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
                /** public field */
                case "get_CLOSE_CURRENT_RESULT" :{ 
                    retValue = guidObject.getObject().CLOSE_CURRENT_RESULT;
                    break;
                }
                case "set_CLOSE_CURRENT_RESULT" :{ 
                    guidObject.getObject().CLOSE_CURRENT_RESULT = (int)(context.args.get("0"));
                    break;
                }
                case "get_KEEP_CURRENT_RESULT" :{ 
                    retValue = guidObject.getObject().KEEP_CURRENT_RESULT;
                    break;
                }
                case "set_KEEP_CURRENT_RESULT" :{ 
                    guidObject.getObject().KEEP_CURRENT_RESULT = (int)(context.args.get("0"));
                    break;
                }
                case "get_CLOSE_ALL_RESULTS" :{ 
                    retValue = guidObject.getObject().CLOSE_ALL_RESULTS;
                    break;
                }
                case "set_CLOSE_ALL_RESULTS" :{ 
                    guidObject.getObject().CLOSE_ALL_RESULTS = (int)(context.args.get("0"));
                    break;
                }
                case "get_SUCCESS_NO_INFO" :{ 
                    retValue = guidObject.getObject().SUCCESS_NO_INFO;
                    break;
                }
                case "set_SUCCESS_NO_INFO" :{ 
                    guidObject.getObject().SUCCESS_NO_INFO = (int)(context.args.get("0"));
                    break;
                }
                case "get_EXECUTE_FAILED" :{ 
                    retValue = guidObject.getObject().EXECUTE_FAILED;
                    break;
                }
                case "set_EXECUTE_FAILED" :{ 
                    guidObject.getObject().EXECUTE_FAILED = (int)(context.args.get("0"));
                    break;
                }
                case "get_RETURN_GENERATED_KEYS" :{ 
                    retValue = guidObject.getObject().RETURN_GENERATED_KEYS;
                    break;
                }
                case "set_RETURN_GENERATED_KEYS" :{ 
                    guidObject.getObject().RETURN_GENERATED_KEYS = (int)(context.args.get("0"));
                    break;
                }
                case "get_NO_GENERATED_KEYS" :{ 
                    retValue = guidObject.getObject().NO_GENERATED_KEYS;
                    break;
                }
                case "set_NO_GENERATED_KEYS" :{ 
                    guidObject.getObject().NO_GENERATED_KEYS = (int)(context.args.get("0"));
                    break;
                }
                /** public methods */
                case "execute" :{ 
                    java.lang.String sql = (String)(context.args.get("0"));
                    int[] columnIndexes = com.csmake.Util.conv(JSONObject.parseArray(((JSONArray)context.args.get("1")).toJSONString(), Integer.class).toArray(new Integer[0]));
                    retValue = guidObject.getObject().execute(sql, columnIndexes);
                    break;
                }
                case "execute2" :{ 
                    java.lang.String sql = (String)(context.args.get("0"));
                    java.lang.String[] columnNames = JSONObject.parseArray(((JSONArray)context.args.get("1")).toJSONString(), java.lang.String.class).toArray(new java.lang.String[0]);
                    retValue = guidObject.getObject().execute(sql, columnNames);
                    break;
                }
                case "execute3" :{ 
                    java.lang.String sql = (String)(context.args.get("0"));
                    int autoGeneratedKeys = (int)(context.args.get("1"));
                    retValue = guidObject.getObject().execute(sql, autoGeneratedKeys);
                    break;
                }
                case "execute4" :{ 
                    java.lang.String[] sqls = JSONObject.parseArray(((JSONArray)context.args.get("0")).toJSONString(), java.lang.String.class).toArray(new java.lang.String[0]);
                    retValue = guidObject.getObject().execute(sqls);
                    break;
                }
                case "execute5" :{ 
                    java.lang.String sql = (String)(context.args.get("0"));
                    retValue = guidObject.getObject().execute(sql);
                    break;
                }
                case "close" :{ 
                    guidObject.getObject().close();
                    break;
                }
                case "getResultSetConcurrency" :{ 
                    retValue = guidObject.getObject().getResultSetConcurrency();
                    break;
                }
                case "getResultSetHoldability" :{ 
                    retValue = guidObject.getObject().getResultSetHoldability();
                    break;
                }
                case "isCloseOnCompletion" :{ 
                    retValue = guidObject.getObject().isCloseOnCompletion();
                    break;
                }
                case "closeOnCompletion" :{ 
                    guidObject.getObject().closeOnCompletion();
                    break;
                }
                case "executeLargeUpdate" :{ 
                    java.lang.String sql = (String)(context.args.get("0"));
                    retValue = guidObject.getObject().executeLargeUpdate(sql);
                    break;
                }
                case "executeLargeUpdate2" :{ 
                    java.lang.String sql = (String)(context.args.get("0"));
                    java.lang.String[] columnNames = JSONObject.parseArray(((JSONArray)context.args.get("1")).toJSONString(), java.lang.String.class).toArray(new java.lang.String[0]);
                    retValue = guidObject.getObject().executeLargeUpdate(sql, columnNames);
                    break;
                }
                case "executeLargeUpdate3" :{ 
                    java.lang.String sql = (String)(context.args.get("0"));
                    int autoGeneratedKeys = (int)(context.args.get("1"));
                    retValue = guidObject.getObject().executeLargeUpdate(sql, autoGeneratedKeys);
                    break;
                }
                case "executeLargeUpdate4" :{ 
                    java.lang.String sql = (String)(context.args.get("0"));
                    int[] columnIndexes = com.csmake.Util.conv(JSONObject.parseArray(((JSONArray)context.args.get("1")).toJSONString(), Integer.class).toArray(new Integer[0]));
                    retValue = guidObject.getObject().executeLargeUpdate(sql, columnIndexes);
                    break;
                }
                case "getFetchDirection" :{ 
                    retValue = guidObject.getObject().getFetchDirection();
                    break;
                }
                case "setEscapeProcessing" :{ 
                    boolean enable = (boolean)(context.args.get("0"));
                    guidObject.getObject().setEscapeProcessing(enable);
                    break;
                }
                case "setFetchDirection" :{ 
                    int direction = (int)(context.args.get("0"));
                    guidObject.getObject().setFetchDirection(direction);
                    break;
                }
                case "executeLargeBatch" :{ 
                    retValue = guidObject.getObject().executeLargeBatch();
                    break;
                }
                case "getLargeUpdateCount" :{ 
                    retValue = guidObject.getObject().getLargeUpdateCount();
                    break;
                }
                case "setCursorName" :{ 
                    java.lang.String name = (String)(context.args.get("0"));
                    guidObject.getObject().setCursorName(name);
                    break;
                }
                case "setMaxRows" :{ 
                    int max = (int)(context.args.get("0"));
                    guidObject.getObject().setMaxRows(max);
                    break;
                }
                case "cancel" :{ 
                    guidObject.getObject().cancel();
                    break;
                }
                case "getResultSet" :{ 
                    org.bson.Document options = JSON.parseObject(context.args.get("0").toString(), org.bson.Document.class);
                    retValue = guidObject.getObject().getResultSet(options);
                    break;
                }
                case "getResultSet2" :{ 
                    retValue = guidObject.getObject().getResultSet();
                    break;
                }
                case "setFetchSize" :{ 
                    int rows = (int)(context.args.get("0"));
                    guidObject.getObject().setFetchSize(rows);
                    break;
                }
                case "getResultSetType" :{ 
                    retValue = guidObject.getObject().getResultSetType();
                    break;
                }
                case "executeQuery" :{ 
                    java.lang.String sql = (String)(context.args.get("0"));
                    org.bson.Document options = JSON.parseObject(context.args.get("1").toString(), org.bson.Document.class);
                    retValue = guidObject.getObject().executeQuery(sql, options);
                    break;
                }
                case "executeQuery2" :{ 
                    java.lang.String sql = (String)(context.args.get("0"));
                    retValue = guidObject.getObject().executeQuery(sql);
                    break;
                }
                case "getConnection" :{ 
                    retValue = guidObject.getObject().getConnection();
                    break;
                }
                case "clearWarnings" :{ 
                    guidObject.getObject().clearWarnings();
                    break;
                }
                case "executeUpdate" :{ 
                    java.lang.String sql = (String)(context.args.get("0"));
                    retValue = guidObject.getObject().executeUpdate(sql);
                    break;
                }
                case "executeUpdate2" :{ 
                    java.lang.String sql = (String)(context.args.get("0"));
                    java.lang.String[] columnNames = JSONObject.parseArray(((JSONArray)context.args.get("1")).toJSONString(), java.lang.String.class).toArray(new java.lang.String[0]);
                    retValue = guidObject.getObject().executeUpdate(sql, columnNames);
                    break;
                }
                case "executeUpdate3" :{ 
                    java.lang.String sql = (String)(context.args.get("0"));
                    int[] columnIndexes = com.csmake.Util.conv(JSONObject.parseArray(((JSONArray)context.args.get("1")).toJSONString(), Integer.class).toArray(new Integer[0]));
                    retValue = guidObject.getObject().executeUpdate(sql, columnIndexes);
                    break;
                }
                case "executeUpdate4" :{ 
                    java.lang.String sql = (String)(context.args.get("0"));
                    int autoGeneratedKeys = (int)(context.args.get("1"));
                    retValue = guidObject.getObject().executeUpdate(sql, autoGeneratedKeys);
                    break;
                }
                case "executeUpdate5" :{ 
                    java.lang.String[] sqls = JSONObject.parseArray(((JSONArray)context.args.get("0")).toJSONString(), java.lang.String.class).toArray(new java.lang.String[0]);
                    retValue = guidObject.getObject().executeUpdate(sqls);
                    break;
                }
                case "executeProcedure" :{ 
                    java.lang.String procedureName = (String)(context.args.get("0"));
                    org.bson.Document parameters = JSON.parseObject(context.args.get("1").toString(), org.bson.Document.class);
                    org.bson.Document options = JSON.parseObject(context.args.get("2").toString(), org.bson.Document.class);
                    retValue = guidObject.getObject().executeProcedure(procedureName, parameters, options);
                    break;
                }
                case "setMaxFieldSize" :{ 
                    int max = (int)(context.args.get("0"));
                    guidObject.getObject().setMaxFieldSize(max);
                    break;
                }
                case "getQueryTimeout" :{ 
                    retValue = guidObject.getObject().getQueryTimeout();
                    break;
                }
                case "getFetchSize" :{ 
                    retValue = guidObject.getObject().getFetchSize();
                    break;
                }
                case "addBatch" :{ 
                    java.lang.String sql = (String)(context.args.get("0"));
                    guidObject.getObject().addBatch(sql);
                    break;
                }
                case "clearBatch" :{ 
                    guidObject.getObject().clearBatch();
                    break;
                }
                case "executeBatch" :{ 
                    retValue = guidObject.getObject().executeBatch();
                    break;
                }
                case "getMaxFieldSize" :{ 
                    retValue = guidObject.getObject().getMaxFieldSize();
                    break;
                }
                case "getMaxRows" :{ 
                    retValue = guidObject.getObject().getMaxRows();
                    break;
                }
                case "setQueryTimeout" :{ 
                    int seconds = (int)(context.args.get("0"));
                    guidObject.getObject().setQueryTimeout(seconds);
                    break;
                }
                case "getUpdateCount" :{ 
                    retValue = guidObject.getObject().getUpdateCount();
                    break;
                }
                case "getMoreResults" :{ 
                    int current = (int)(context.args.get("0"));
                    retValue = guidObject.getObject().getMoreResults(current);
                    break;
                }
                case "getMoreResults2" :{ 
                    retValue = guidObject.getObject().getMoreResults();
                    break;
                }
                case "getGeneratedKeys" :{ 
                    retValue = guidObject.getObject().getGeneratedKeys();
                    break;
                }
                case "getGeneratedKeys2" :{ 
                    org.bson.Document options = JSON.parseObject(context.args.get("0").toString(), org.bson.Document.class);
                    retValue = guidObject.getObject().getGeneratedKeys(options);
                    break;
                }
                case "isPoolable" :{ 
                    retValue = guidObject.getObject().isPoolable();
                    break;
                }
                case "setLargeMaxRows" :{ 
                    long max = Long.valueOf(context.args.get("0").toString());
                    guidObject.getObject().setLargeMaxRows(max);
                    break;
                }
                case "setPoolable" :{ 
                    boolean poolable = (boolean)(context.args.get("0"));
                    guidObject.getObject().setPoolable(poolable);
                    break;
                }
                case "getLargeMaxRows" :{ 
                    retValue = guidObject.getObject().getLargeMaxRows();
                    break;
                }
                case "isClosed" :{ 
                    retValue = guidObject.getObject().isClosed();
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
                exception="org.dbcloud.jdbc.Statement is null pointer.";
            }else{
                exception = ex.toString();
            }
        }
        return new com.csmake.JSONReturn(errorCode, exception, retValue);
    }
    private final com.csmake.ClassLoader classLoader;
    private java.util.HashMap<String, String > _params = null;
    private java.util.HashMap<String, com.csmake.GuidObject<org.dbcloud.jdbc.Statement> > _maps = null;
}//end of class Statement
