package xyz.lightseekers.maven_blog.util_test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import xyz.lightseekers.maven_blog.util.BaiDuUtil;

import java.util.Map;

/**
 * @Description:
 * @Author: Light
 * @Date: 2020/1/4 9:10
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class BaiDuUtilTest {

    @Test
    public void getLongitudeAndLatitude() {
        Map<String, Object> longitudeAndLatitude = BaiDuUtil.getLongitudeAndLatitude("118.181.163.133");
        System.out.println(longitudeAndLatitude.toString());
    }

    @Test
    public void String2Json(){
        String api="{\"address\":\"CN|\\u7518\\u8083|\\u5170\\u5dde|None|CHINANET|0|0\",\"content\":{\"address\":\"\\u7518\\u8083\\u7701\\u5170\\u5dde\\u5e02\",\"address_detail\":{\"city\":\"\\u5170\\u5dde\\u5e02\",\"city_code\":36,\"district\":\"\",\"province\":\"\\u7518\\u8083\\u7701\",\"street\":\"\",\"street_number\":\"\"},\"point\":{\"x\":\"103.82330544\",\"y\":\"36.06422553\"}},\"status\":0}";

        JSONObject objects = JSON.parseObject(api);
        Object status = objects.get("status");
        System.out.println(status);
        if ("0".equals(status)||0==Integer.parseInt(String.valueOf(status))){
            System.out.println("请求成功");
        }else{
            System.out.println("请求失败");
        }
    }
}
