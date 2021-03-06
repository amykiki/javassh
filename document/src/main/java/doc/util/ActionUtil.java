package doc.util;

import com.opensymphony.xwork2.ActionContext;
import doc.entity.User;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.BeanUtilsBean;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Amysue on 2016/5/6.
 */
public class ActionUtil {
    public final static  String REDIRECT   = "redirect";
    public final static  String FORWARD    = "forward";
    public final static  String GERROR     = "gerror";
    public final static  String LOGIN      = "login";
    public final static  String AUTHINFO   = "authinfo";
    public final static  String WELCOME    = "welcome";
    private final static String ATTACHPATH = "attachpath";

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

    public static void copyNotNullProperties(Object dest, Object src) {
        BeanUtilsBean notNull = new NullAwareBeanUtilsBean();
        try {
            notNull.copyProperties(dest, src);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    public static User getLguser() {
        if (ActionContext.getContext().getSession().get("lguser") != null) {
            return (User) ActionContext.getContext().getSession().get("lguser");
        } else {
            return null;
        }

    }

    public static void setLguser(User lguser) {
        ActionContext.getContext().getSession().put("lguser", lguser);
    }


    public static Object stringToObj(Class<?> clz, String s) {
//        Department{id=2, name='教务处'}
        Pattern r = Pattern.compile(".*\\{(.*)}");
        Matcher m = r.matcher(s);
        if (m.find()) {
            s = m.group(1);
        }
        String[] sp = s.split(", ");
        Object   o  = null;
        try {
            o = clz.getConstructor().newInstance();
            for (String p : sp) {
                String[] maps       = p.split("=");
                String   fieldName  = maps[0];
                String   fieldValue = maps[1];
                BeanUtils.setProperty(o, fieldName, fieldValue);
            }
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        return o;
    }

    public static String redirectToList(String listName) {
        ActionUtil.setUrl("/" + listName + "_list.action");
        return ActionUtil.REDIRECT;
    }

    public static String getAttachPath() {
        return PropertiesUtil.getParamsProp().getProperty(ATTACHPATH);
    }

    public static boolean isNullStr(String string) {
        if (string == null || string.trim().equals("")) {
            return true;
        } else {
            return false;
        }
    }

    public static String getMapValStr(Map<String, Object> param, String key) {
        if (param.get(key) != null) {
            return param.get(key).toString();
        } else {
            return null;
        }
    }

    public static Map<String, Object> getActionParams(Map<String, Object> params) {
        for (String key : params.keySet()) {
            if (params.get(key) != null) {
                if (params.get(key) instanceof Object[]) {
                    Object[] values = (Object[]) params.get(key);
                    if (values.length == 1) {
                        params.put(key, values[0]);
                    }
                }
            }
        }
        return params;
    }

}
