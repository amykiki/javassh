package org.mybatis.smvc.formatter;

import org.mybatis.smvc.entity.PhoneNum;
import org.springframework.format.AnnotationFormatterFactory;
import org.springframework.format.Parser;
import org.springframework.format.Printer;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Amysue on 2016/6/5.
 */
public class PhoneNumFormatAnnotationFormatterFactory implements AnnotationFormatterFactory<PhoneNumFormat> {
    private final Set<Class<?>> fieldTypes;
    private final PhoneNumFormatter formatter;

    public PhoneNumFormatAnnotationFormatterFactory() {
        Set<Class<?>> set = new HashSet<>();
        set.add(PhoneNum.class);
        this.fieldTypes = set;
        formatter = new PhoneNumFormatter();
    }

    @Override
    public Set<Class<?>> getFieldTypes() {
        return fieldTypes;
    }

    @Override
    public Printer<?> getPrinter(PhoneNumFormat annotation, Class<?> fieldType) {
        return formatter;
    }

    @Override
    public Parser<?> getParser(PhoneNumFormat annotation, Class<?> fieldType) {
        return formatter;
    }
}
