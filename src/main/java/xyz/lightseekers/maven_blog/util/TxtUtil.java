package xyz.lightseekers.maven_blog.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

/**
 * @Description:
 * @Author: Light
 * @Date: 2020/1/9 10:23
 */
public class TxtUtil {

    private final static Logger logger = LoggerFactory.getLogger(TxtUtil.class);

    public static File writeTXT(String title, String content) {
        File writeName = null;
        try {
            // 防止文件建立或读取失败，用catch捕捉错误并打印，也可以throw
            // 写入Txt文件
            writeName = new File("src/main/resources/static/blog/");// 相对路径，如果没有则要建立一个新的output。txt文件
            if (!writeName.exists()) {
                writeName.mkdirs();
            }
            logger.debug(writeName.getAbsolutePath());
            writeName = new File(writeName.getAbsolutePath() + "\\" + title + ".txt");
            // 相对路径，如果没有则要建立一个新的output。txt文件
            writeName.createNewFile(); // 创建新文件
            BufferedWriter out = new BufferedWriter(new FileWriter(writeName));
            out.write(content);
            // \r\n即为换行
            out.flush();
            // 把缓存区内容压入文件
            out.close();
            // 最后记得关闭文件
            logger.debug("执行成功");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            return writeName;
        }
    }
}
