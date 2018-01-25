package org.lining.undertow.portal.servlet;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringEscapeUtils;
import org.lining.undertow.portal.log.LogPortal;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * description:
 * date 2018/1/25
 *
 * @author lining1
 * @version 1.0.0
 */
public class MessageServlet extends HttpServlet {

    private static final String DEFAILT_PATTERN = "yyyy-MM-dd_HH-mm-ss";

    private static final Pattern CRLF = Pattern.compile("(\r\n|\r|\n|\n\r)");

    @Override
    protected void doGet(final HttpServletRequest req, final HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter writer = resp.getWriter();
        SimpleDateFormat format = new SimpleDateFormat(DEFAILT_PATTERN);
        Date date = new Date(System.currentTimeMillis());
        String time = format.format(date);
        writer.write(time);
        writer.close();
    }

    @Override
    protected void doPost(final HttpServletRequest req, final HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/json");
        BufferedReader br = req.getReader();
        String json = br.readLine();
        PrintWriter out = resp.getWriter();
/*        JSONObject totaljson = new JSONObject();
        processRequest(totaljson, json, req, br);*/
        out.write(json);
        out.flush();
        out.close();
        writeFile(json);
    }

    private void processRequest(JSONObject totaljson, String json, HttpServletRequest req, BufferedReader br) {
        long currentTime = System.currentTimeMillis();
        Date curDate = new Date(currentTime);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String showTime = formatter.format(curDate);
        try {
            totaljson.put("result", 0);
            totaljson.put("timeStamp", showTime);
            totaljson.put("json", json);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (br != null) {
                    br.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    private void writeFile(String json){
        Matcher m = CRLF.matcher(json);
        LogPortal.log2File(json);
    }


}
