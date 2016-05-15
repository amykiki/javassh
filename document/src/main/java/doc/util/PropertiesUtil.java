package doc.util;

import java.io.IOException;
import java.util.Properties;

/**
 * Created by Amysue on 2016/5/12.
 */
public class PropertiesUtil {
    private static Properties authProp;
    private static Properties paramsProp;

    public static Properties getAuthProp() {
        if (authProp == null) {
            authProp = new Properties();
            try {
                authProp.load(PropertiesUtil.class.getClassLoader().getResourceAsStream("auth.properties"));
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        return authProp;
    }

    public static Properties getParamsProp() {
        if (paramsProp == null) {
            paramsProp = new Properties();
            try {
                paramsProp.load(PropertiesUtil.class.getClassLoader().getResourceAsStream("params.properties"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return paramsProp;
    }
}
