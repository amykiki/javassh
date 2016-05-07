package doc.util;

import org.apache.commons.beanutils.BeanUtilsBean;

import java.lang.reflect.InvocationTargetException;

/**
 * Created by Amysue on 2016/5/7.
 */
public class NullAwareBeanUtilsBean extends BeanUtilsBean {

    @Override
    public void copyProperty(Object bean, String name, Object value) throws IllegalAccessException, InvocationTargetException {
        if (value == null) return;
        super.copyProperty(bean, name, value);
    }
}
