package xyz.lightseekers.maven_blog.convert;

import org.springframework.core.convert.converter.Converter;
import org.springframework.util.StringUtils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Author: ZhiliangJia
 * @Description:
 * @Date:Create in 11:36 2019/12/28
 * @Modified User:
 */
public class String2DateConverter implements Converter<String, Date> {

    private static final String DATE = "yyyy-MM-dd HH:mm:ss";
    private static final String SHORTDATE = "yyyy-MM-dd";

    @Override
    public Date convert(String s) {
        if (StringUtils.isEmpty(s)) {
            return null;
        }
        s = s.trim();
        try {
            if (s.contains("-")) {
                SimpleDateFormat formatter;
                if (s.contains(":")) {
                    formatter = new SimpleDateFormat(DATE);
                } else {
                    formatter = new SimpleDateFormat(SHORTDATE);
                }
                Date dtDate = formatter.parse(s);
                return dtDate;
            } else if (s.matches("^\\d+$")) {
                Long lDate = new Long(s);
                return new Date(lDate);
            }
        } catch (Exception e) {
            throw new RuntimeException(String.format("parser %s to Date fail", s));
        }
        throw new RuntimeException(String.format("parser %s to Date fail", s));
    }
}
