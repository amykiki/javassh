package doc.util;

import com.opensymphony.xwork2.ActionContext;
import org.apache.commons.beanutils.BeanUtils;

import java.lang.reflect.InvocationTargetException;

/**
 * Created by Amysue on 2016/5/6.
 */
public class ActionUtil {
    public final static String REDIRECT = "redirect";

    public static void setUrl(String url) {
        ActionContext.getContext().put("url", url);
    }

    public static void copyProperties(Object dest, Object src) {
        try {
            BeanUtils.copyProperties(dest, src);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

    }
}
