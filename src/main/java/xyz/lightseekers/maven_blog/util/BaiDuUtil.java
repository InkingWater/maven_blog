package xyz.lightseekers.maven_blog.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import xyz.lightseekers.maven_blog.bean.Comment;
import xyz.lightseekers.maven_blog.bean.Love;
import xyz.lightseekers.maven_blog.bean.Message;
import xyz.lightseekers.maven_blog.bean.Visitor;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @Description:
 * @Author: Light
 * @Date: 2020/1/4 9:00
 */
public class BaiDuUtil {
    private static String URL_FIRST = "http://api.map.baidu.com/location/ip?ak=mCwa7DUIjeZGejlZayO0Y6EyT4QS0GoI&ip=";
    private static String URL_SECOND = "&coor=bd09ll";
    private static String USER_AGENT = "User-Agent";
    private static String USER_AGENT_VALUE = "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:52.0) Gecko/20100101 Firefox/52.0";

    /**
     * 获取经纬度及位置
     *
     * @param ip ip地址
     * @return 以Map集合的方式返回数据
     */
    public static Map<String, Object> getLongitudeAndLatitude(String ip) {
        Map<String, Object> result = new HashMap<>();
        try {
            Connection connect = Jsoup.connect(URL_FIRST + ip + URL_SECOND);
            connect.header(USER_AGENT, USER_AGENT_VALUE);
            connect.ignoreContentType(true);
            Connection.Response response = connect.execute();
            JSONObject objects = JSON.parseObject(response.body());
            Object status = objects.get("status");
            if ("0".equals(status) || 0 == Integer.parseInt(String.valueOf(status))) {
                JSONObject content = objects.getJSONObject("content");
                JSONObject point = content.getJSONObject("point");
                result.put("longitude", point.get("x"));
                result.put("latitude", point.get("y"));
                result.put("address", content.get("address"));
            } else {
                result.put("longitude", 0.00);
                result.put("latitude", 0.00);
                result.get("longitude");
                result.put("address", "error");
            }
        } catch (IOException exception) {
            exception.printStackTrace();
        }
        return result;
    }

    /**
     * 获取Ip地址
     *
     * @param request
     * @return
     */
    public static String getIpAddr(HttpServletRequest request) {
        String ipAddress = null;
        try {
            ipAddress = request.getHeader("x-forwarded-for");
            if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
                ipAddress = request.getHeader("Proxy-Client-IP");
            }
            if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
                ipAddress = request.getHeader("WL-Proxy-Client-IP");
            }
            if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
                ipAddress = request.getRemoteAddr();
                if (ipAddress.equals("127.0.0.1")) {
                    // 根据网卡取本机配置的IP
                    InetAddress inet = null;
                    try {
                        inet = InetAddress.getLocalHost();
                    } catch (UnknownHostException e) {
                        e.printStackTrace();
                    }
                    ipAddress = inet.getHostAddress();
                }
            }
            // 对于通过多个代理的情况，第一个IP为客户端真实IP,多个IP按照','分割
            if (ipAddress != null && ipAddress.length() > 15) { // "***.***.***.***".length()
                // = 15
                if (ipAddress.indexOf(",") > 0) {
                    ipAddress = ipAddress.substring(0, ipAddress.indexOf(","));
                }
            }
        } catch (Exception e) {
            ipAddress = "";
        }
        // ipAddress = this.getRequest().getRemoteAddr();

        return ipAddress;
    }

    public static void setVisitor(HttpServletRequest request, Visitor visitor){
        String ip = BaiDuUtil.getIpAddr(request);
        visitor.setIp(ip);
        visitor.setUrl(request.getRequestURI());
        visitor.setLatitude((double) getLongitudeAndLatitude(ip).get("latitude"));
        visitor.setLongitude((double) getLongitudeAndLatitude(ip).get("longitude"));
        visitor.setAddress((String.valueOf(getLongitudeAndLatitude(ip).get("address"))));
        visitor.setDate(new Date());
    }

    public static void setComment(HttpServletRequest request, Comment comment){
        comment.setIp(BaiDuUtil.getIpAddr(request));
        Map<String, Object> longitudeAndLatitude = BaiDuUtil.getLongitudeAndLatitude(BaiDuUtil.getIpAddr(request));
        comment.setLongitude(Double.valueOf(longitudeAndLatitude.get("longitude").toString()));
        comment.setLatitude(Double.valueOf(longitudeAndLatitude.get("latitude").toString()));
    }

    public static void setMessage(HttpServletRequest request, Message message){
        String ipAddr = BaiDuUtil.getIpAddr(request);
        Map<String, Object> longitudeAndLatitude = BaiDuUtil.getLongitudeAndLatitude(ipAddr);
        message.setIp(ipAddr);
        message.setLatitude(Double.parseDouble((longitudeAndLatitude.get("latitude")).toString()));
        message.setLongitude(Double.parseDouble((longitudeAndLatitude.get("longitude").toString())));
    }

    public static void setLove(HttpServletRequest request, Love love){
        String ip = getIpAddr(request);
        love.setIp(getIpAddr(request));
        Map<String, Object> longitudeAndLatitude = BaiDuUtil.getLongitudeAndLatitude(ip);
        love.setLongitude(Double.parseDouble((longitudeAndLatitude.get("longitude").toString())));
        love.setLatitude(Double.parseDouble((longitudeAndLatitude.get("latitude").toString())));
    }
}
