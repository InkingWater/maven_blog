package xyz.lightseekers.maven_blog.util;


import com.baidu.aip.util.Base64Util;
import org.json.JSONObject;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.*;

/**
 * @Description:
 * @Author: Light
 * @Date: 2020/1/5 22:03
 */
public class AuthUtil {
    /**
     * 获取权限token
     * @return 返回示例：
     * {
     * "access_token": "24.460da4889caad24cccdb1fea17221975.2592000.1491995545.282335-1234567",
     * "expires_in": 2592000
     * }
     */
    public static String getAuth() {
        // 官网获取的 API Key 更新为你注册的
        String clientId = "百度云应用的AK";
        // 官网获取的 Secret Key 更新为你注册的
        String clientSecret = "百度云应用的SK";
        return getAuth(clientId, clientSecret);
    }

    /**
     * 获取API访问token
     * 该token有一定的有效期，需要自行管理，当失效时需重新获取.
     * @param ak - 百度云官网获取的 API Key
     * @param sk - 百度云官网获取的 Securet Key
     * @return assess_token 示例：
     * "24.460da4889caad24cccdb1fea17221975.2592000.1491995545.282335-1234567"
     */
    public static String getAuth(String ak, String sk) {
        // 获取token地址
        String authHost = "https://aip.baidubce.com/oauth/2.0/token?";
        String getAccessTokenUrl = authHost
                // 1. grant_type为固定参数
                + "grant_type=client_credentials"
                // 2. 官网获取的 API Key
                + "&client_id=" + ak
                // 3. 官网获取的 Secret Key
                + "&client_secret=" + sk;
        try {
            URL realUrl = new URL(getAccessTokenUrl);
            // 打开和URL之间的连接
            HttpURLConnection connection = (HttpURLConnection) realUrl.openConnection();
            connection.setRequestMethod("GET");
            connection.connect();
            // 获取所有响应头字段
            Map<String, List<String>> map = connection.getHeaderFields();
            // 遍历所有的响应头字段
            for (String key : map.keySet()) {
                System.err.println(key + "--->" + map.get(key));
            }
            // 定义 BufferedReader输入流来读取URL的响应
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String result = "";
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
            /**
             * 返回结果示例
             */
            System.err.println("result:" + result);
            JSONObject jsonObject = new JSONObject(result);
            String access_token = jsonObject.getString("access_token");
            return access_token;
        } catch (Exception e) {
            System.err.printf("获取token失败！");
            e.printStackTrace(System.err);
        }
        return null;
    }
    public static String checkSession() {
        URL resource = AuthUtil.class.getClassLoader().getResource("data.properties");
        File file=new File(resource.getFile());
        System.out.println(file);
        Properties properties = new Properties();
        try {
            properties.load(new FileInputStream(file));
        } catch (IOException e) {
            e.printStackTrace();
        }
        String baiDuSession = properties.getProperty("baiDuSession", "");
        if (baiDuSession.isEmpty()){
            String auth = getAuth(properties.getProperty("apiKey"),
                    properties.getProperty("securityKey"));
            properties.setProperty("baiduSession",auth);
            return auth;
        }
        return baiDuSession;
    }

    public static String faceMatch(String baiDuSession,String file1,String file2) {
        // 请求url
        String url = "https://aip.baidubce.com/rest/2.0/face/v3/match";
        try {
            String accessToken =baiDuSession;
            List<Map<String,String>> list=new ArrayList<>();
            Map<String,String> map1=new HashMap<>();
            map1.put("image",Base64Util.encode(FileUtil.readFileByBytes(file1)));
            map1.put("image_type","BASE64");
            map1.put("face_type","LIVE");
            map1.put("quality_control","LOW");
            map1.put("liveness_control","LOW");
            list.add(map1);
            Map<String,String> map2=new HashMap<>();
            map2.put("image", Base64Util.encode(FileUtil.readFileByBytes(file2)));
            map2.put("image_type","BASE64");
            map2.put("face_type","IDCARD");
            map2.put("quality_control","LOW");
            map2.put("liveness_control","LOW");
            list.add(map2);
            String param= GsonUtils.toJson(list);
            String result = HttpUtil.post(url, accessToken, "application/json", param);
            System.out.println(result);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
