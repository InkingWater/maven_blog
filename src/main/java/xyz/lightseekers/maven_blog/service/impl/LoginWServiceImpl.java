package xyz.lightseekers.maven_blog.service.impl;

import org.springframework.stereotype.Service;
import xyz.lightseekers.maven_blog.service.ILoginWService;
import xyz.lightseekers.maven_blog.util.RandomImgCodeUtil;

import java.util.HashMap;
import java.util.Map;
@Service
public class LoginWServiceImpl implements ILoginWService {

    @Override
    public Map<String, String> RandomImgCode(int lengh) throws RuntimeException {
        String answer =RandomImgCodeUtil.getStringRandom(lengh);
        String base64= null;
        try {
            base64 = RandomImgCodeUtil.imageToBase64(40,120,answer);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Map<String,String> map = new HashMap<String ,String>();
        map.put("答案",answer);
        map.put("base64",base64);
        return map;
    }
}
