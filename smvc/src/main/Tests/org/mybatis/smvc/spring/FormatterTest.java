package org.mybatis.smvc.spring;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.smvc.entity.FormatModel;
import org.mybatis.smvc.entity.PhoneNum;
import org.mybatis.smvc.formatter.PhoneNumFormatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.core.convert.TypeDescriptor;
import org.springframework.format.number.CurrencyStyleFormatter;
import org.springframework.format.support.DefaultFormattingConversionService;
import org.springframework.format.support.FormattingConversionService;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;

/**
 * Created by Amysue on 2016/6/3.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:beans.xml"})
public class FormatterTest {
    @Autowired
    private FormattingConversionService conversionService;

    @Test
    public void testDefaultFormattingConversation() {
        DefaultFormattingConversionService conversionService = new DefaultFormattingConversionService();
        CurrencyStyleFormatter currencyStyleFormatter = new CurrencyStyleFormatter();
        currencyStyleFormatter.setFractionDigits(2);
        currencyStyleFormatter.setRoundingMode(RoundingMode.CEILING);
        conversionService.addFormatter(currencyStyleFormatter);
        LocaleContextHolder.setLocale(Locale.CHINA);
        String convertStr = conversionService.convert(new BigDecimal("123.316"), String.class);
        System.out.println(convertStr);
        Assert.assertEquals("￥123.32", convertStr);
        BigDecimal bd = conversionService.convert("￥123.995", BigDecimal.class);
        Assert.assertEquals(new BigDecimal("124.00"), bd);
        LocaleContextHolder.setLocale(null);
    }

    @Test
    public void testPhoneNumFormattingConversation() {
        DefaultFormattingConversionService conversionService = new DefaultFormattingConversionService();
        conversionService.addFormatter(new PhoneNumFormatter());
        Assert.assertEquals("0101-1234567", conversionService.convert(new PhoneNum("0101", "1234567"), String.class));
        Assert.assertEquals("0101", conversionService.convert("0101-1234567", PhoneNum.class).getAreaCode());
        Assert.assertEquals("1234567", conversionService.convert("0101-1234567", PhoneNum.class).getPhoneNumber());
    }

    @Test
    public void testFormatConversation() throws Exception{
//        DefaultFormattingConversionService conversionService = new DefaultFormattingConversionService();
        FormatModel fm = new FormatModel();
        fm.setTotalCount(1234567);
        fm.setDisCount(0.51);
        fm.setSumMoney(1345.67);
        Calendar calendar = new GregorianCalendar(2015, 5, 4);
        fm.setRegisterDate(calendar.getTime());
        Calendar orderCalendar = new GregorianCalendar(2016, 5, 4, 19, 9, 30);
        fm.setOrderDate(orderCalendar.getTime());
        fm.setPhoneNum(new PhoneNum("021", "5411516"));

        TypeDescriptor stringDescriptor = TypeDescriptor.valueOf(String.class);
        TypeDescriptor descriptor = new TypeDescriptor(FormatModel.class.getDeclaredField("totalCount"));
        int count = (int)conversionService.convert("1,230,000", stringDescriptor, descriptor);
        System.out.println("count = " + count);
        String countStr = (String) conversionService.convert(fm.getTotalCount(), descriptor, stringDescriptor);
        System.out.println("countStr = " + countStr);
        Assert.assertEquals("1,234,567", conversionService.convert(fm.getTotalCount(), descriptor, stringDescriptor));

        /*TypeDescriptor disDescriptor = new TypeDescriptor(FormatModel.class.getDeclaredField("disCount"));
        System.out.println(conversionService.convert(fm.getDisCount(), disDescriptor, stringDescriptor));
        Assert.assertEquals("51%", conversionService.convert(fm.getDisCount(), disDescriptor, stringDescriptor));
        Assert.assertEquals(0.46, conversionService.convert("46%", stringDescriptor, disDescriptor));

        TypeDescriptor sumDiscriptor = new TypeDescriptor(FormatModel.class.getDeclaredField("sumMoney"));
        Assert.assertEquals("￥1,345.67", conversionService.convert(fm.getSumMoney(), sumDiscriptor, stringDescriptor));
        Assert.assertEquals(1234567.89, conversionService.convert("￥1,234,567.89", stringDescriptor, sumDiscriptor));

        TypeDescriptor regDiscriptor = new TypeDescriptor(FormatModel.class.getDeclaredField("registerDate"));
        Assert.assertEquals("2015-06-04", conversionService.convert(fm.getRegisterDate(), regDiscriptor, stringDescriptor));
        Assert.assertEquals(new GregorianCalendar(2016, 5, 5).getTime(), conversionService.convert("2016-6-5", stringDescriptor, regDiscriptor));

        TypeDescriptor orderDescriptor = new TypeDescriptor(FormatModel.class.getDeclaredField("orderDate"));
        Assert.assertEquals("2016-06-04 19:09:30", conversionService.convert(fm.getOrderDate(), orderDescriptor, stringDescriptor));
        Assert.assertEquals(new GregorianCalendar(2016, 5, 5, 14, 14, 25).getTime(), conversionService.convert("2016-06-05 14:14:25", stringDescriptor, orderDescriptor));*/

//        conversionService.addFormatterForFieldAnnotation(new PhoneNumFormatAnnotationFormatterFactory());
        TypeDescriptor phoneDescriptor = new TypeDescriptor(FormatModel.class.getDeclaredField("phoneNum"));
        Assert.assertEquals("021-5411516", conversionService.convert(fm.getPhoneNum(), phoneDescriptor, stringDescriptor));
        Assert.assertEquals(new PhoneNum("011", "88223450"), conversionService.convert("011-88223450", stringDescriptor, phoneDescriptor));
    }

    @Test
    public void testPhoneNumEqual() {
        PhoneNum p1 = new PhoneNum("011", "8223450");
        PhoneNum p2 = new PhoneNum();
        PhoneNum p3 = new PhoneNum();
        System.out.println(p1.equals(p2));
        System.out.println(p2.equals(p3));
        System.out.println("p1.hashCode() = " + p1.hashCode());
        System.out.println("p2.hashCode() = " + p2.hashCode());
        System.out.println("p3.hashCode() = " + p3.hashCode());
    }
}
