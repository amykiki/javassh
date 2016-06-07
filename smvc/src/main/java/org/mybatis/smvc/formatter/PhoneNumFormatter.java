package org.mybatis.smvc.formatter;

import org.mybatis.smvc.entity.PhoneNum;
import org.springframework.format.Formatter;
import org.springframework.util.StringUtils;

import java.text.ParseException;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Amysue on 2016/6/4.
 */
public class PhoneNumFormatter implements Formatter<PhoneNum> {
    Pattern pattern = Pattern.compile("^(\\d{3,4})-(\\d{7,8})$");
    public PhoneNumFormatter() {
        super();
    }

    @Override
    public PhoneNum parse(String text, Locale locale) throws ParseException {
        if (StringUtils.isEmpty(text)) {
            return null;
        }
        Matcher matcher = pattern.matcher(text);
        if (matcher.matches()) {
            String   m1 = matcher.group(1);
            String   m2 = matcher.group(2);
            PhoneNum pn = new PhoneNum();
            pn.setAreaCode(m1);
            pn.setPhoneNumber(m2);
            return pn;
        } else {
            throw new IllegalArgumentException(String.format("类型转换失败，需要[010-12345678]的格式，但格式是[%s]", text));
        }
    }

    @Override
    public String print(PhoneNum object, Locale locale) {
        if (object == null) {
            return "";
        }
        return new StringBuilder()
                .append(object.getAreaCode())
                .append("-")
                .append(object.getPhoneNumber()).toString();
    }
}
