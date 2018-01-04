/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.dbcloud;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.oreilly.servlet.multipart.FilePart;
import com.oreilly.servlet.multipart.FileRenamePolicy;
import com.oreilly.servlet.multipart.MultipartParser;
import com.oreilly.servlet.multipart.Part;
import org.bson.*;
import java.util.Calendar;

/**
 *
 * @author kylin
 */
public class fileServlet extends HttpServlet {

    org.dbcloud.jdbc.Statement stmt = null;
    String root = "/file/";

    @Override
    public void init() throws ServletException {
        try {
            root = this.getInitParameter("root");
            String driver = this.getInitParameter("driver");
            String connection = this.getInitParameter("connection");
            String user = this.getInitParameter("user");
            String password = this.getInitParameter("password");
            stmt = new org.dbcloud.jdbc.Statement(driver, connection, user, password);
            String create = this.getInitParameter("createTable");
            stmt.execute(create);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(fileServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(fileServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
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
            throws ServletException, IOException {
        String id = request.getParameter("id");
        String name = request.getParameter("name");
        String sql = null;
        if (id != null) {
            sql = String.format(this.getInitParameter("selectById"), id);
        } else if (name != null) {
            sql = String.format(this.getInitParameter("selectByName"), name);
        }

        if (sql != null) {
            org.dbcloud.jdbc.ResultSet rst =  stmt.executeQuery(sql, new Document("document", true));
            ArrayList rows = rst.rows;
            if (rows.size() > 0) {
                Document file = (Document) rows.get(0);
                String type = file.containsKey("contentType") ? file.get("contentType").toString() : "application/octet-stream";
                response.setContentType(type);
                long fileLength = file.getLong("length"); // 记录文件大小
                long pastLength = 0; // 记录已下载文件大小
                long contentLength = 0; // 客户端请求的字节总量
                String contentRange = "";
                if (request.getHeader("Range") != null) { // 客户端请求的下载的文件块的开始字节
                    response.setStatus(javax.servlet.http.HttpServletResponse.SC_PARTIAL_CONTENT);
                    long toLength = 0; // 记录客户端需要下载的字节段的最后一个字节偏移量（比如bytes=27000-39000，则这个值是为39000）
                    String rangeBytes = request.getHeader("Range").replaceAll("bytes=", "");// 记录客户端传来的形如“bytes=27000-”或者“bytes=27000-39000”的内容
                    String[] tmps = rangeBytes.split("-");
                    pastLength = Long.parseLong(tmps[0].trim());
                    if (tmps.length == 2) {
                        String str = tmps[1].trim();
                        toLength = str.length() > 0 ? Long.parseLong(str) : fileLength;
                        contentLength = toLength - pastLength;
                        toLength = pastLength + contentLength;
                    } else {
                        contentLength = fileLength - pastLength;
                        toLength = pastLength + contentLength;
                    }
                    contentRange = new StringBuffer("bytes ").append(Long.toString(pastLength)).append("-").append(Long.toString(toLength - 1)).append("/").append(Long.toString(fileLength)).toString();
                    response.setHeader("Accept-Ranges", "bytes");// 如果是第一次下,还没有断点续传,状态是默认的 200,无需显式设置;响应的格式是:HTTP/1.1 200 OK
                    response.setHeader("Content-Range", contentRange);
                    response.addHeader("Content-Length", String.valueOf(contentLength));
                    response.setHeader("Content-Disposition", "attachment;filename="+file.getString("name"));
                } else { // 从开始进行下载
                    contentLength = fileLength; // 客户端要求全文下载
                }

                try (OutputStream out = response.getOutputStream()) {
                    try (java.io.InputStream is = new java.io.FileInputStream(String.format("%s/%s", root, file.getString("path")))) {
                        if (pastLength != 0) {
                            is.skip(pastLength);
                        }
                        byte[] buffer = new byte[32 * 1024];
                        while (contentLength > 0) {
                            int len = contentLength > buffer.length ? buffer.length : (int) contentLength;
                            len = is.read(buffer, 0, len);
                            out.write(buffer, 0, len);
                            contentLength -= len;
                        }
                    } catch (Exception ex) {
                        System.err.println(ex.getMessage());
                    }
                }
            }
        } else {
            response.setContentType("text/html;charset=UTF-8");
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
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        {
            FileRenamePolicy rfrp = new DefaultFileRenamePolicy();
            MultipartParser mp = new MultipartParser(request, 100 * 1024 * 1024,
                    true, true, "UTF-8");//“UTF-8”必须和jsp编码格式相同，不然会产生中文乱码
            int error = 0;
            String message = "";
            StringBuilder sb = new StringBuilder();
            try (PrintWriter out = response.getWriter()) {
                try {
                    sb.append("[");
                    boolean flag = false;
                    String owner = "";
                    FilePart filePart;
                    Part part;
                    int pot;
                    while ((part = mp.readNextPart()) != null) {
                        if (part.isParam()) {
                            if ("owner".equals(part.getName())) {
                                owner = ((com.oreilly.servlet.multipart.ParamPart) part).getStringValue("UTF-8");
                            }
                        } else if (part.isFile()) {// it's a file part
                            filePart = (FilePart) part;
                            filePart.setRenamePolicy(rfrp);
                            String fileName = filePart.getFileName();
                            InputStream in = filePart.getInputStream();
                            pot = fileName.lastIndexOf(".");
                            String type = fileName.substring(pot).toLowerCase();
                            if (type.endsWith(".jpg") || type.endsWith(".jpeg") || type.endsWith(".gif") || type.endsWith(".png") || type.endsWith(".bmp")) {
                                type = type.replace(".", "image/");
                            } else if (type.endsWith(".mp4") || type.endsWith(".ogg") || type.endsWith(".avi") || type.endsWith(".rmvb") || type.endsWith(".wmv") || type.endsWith(".rm")) {
                                type = type.replace(".", "video/");
                            }
                            long id = System.currentTimeMillis();
                            java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy/MM/dd");
                            java.util.Date date = new java.util.Date(id);
                            String filePath = String.format("%s/%d", sdf.format(date), id);
                            String dirs = String.format("%s/%s", root, sdf.format(date));
                            new java.io.File(dirs).mkdirs();
                            try (java.io.FileOutputStream os = new java.io.FileOutputStream(String.format("%s/%s", root, filePath))) {
                                byte[] buffer = new byte[32 * 1024];
                                int contentLength = 0;
                                int len;
                                while ((len = in.read(buffer, 0, buffer.length)) > 0) {
                                    os.write(buffer, 0, len);
                                    contentLength += len;
                                }
                                String sql = String.format(this.getInitParameter("insert"), id, fileName, filePath, type, owner, contentLength, new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(date));
                                stmt.execute(sql);
                            }
                            if (flag) {
                                sb.append(",");
                            }
                            sb.append("{id:'").append(id).append("',name:'").append(fileName).append("'}");
                            flag = true;
                        }
                    }
                    sb.append("]");
                } catch (Exception ex) {
                    sb.append("]");
                    message = ex.toString();
                    error = 500;
                    Logger.getLogger(fileServlet.class.getName()).log(Level.SEVERE, null, ex);
                }
                out.print(String.format("{'error':%d,'message':'%s',value:%s}", error, message, sb.toString()));
            }
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "file upload(post) download(get)";
    }// </editor-fold>
}
