package com.github.amysue.shiro.chapter5;

import com.github.amysue.shiro.BaseTest;
import org.apache.commons.beanutils.BeanUtilsBean;
import org.apache.commons.beanutils.converters.AbstractConverter;
import org.apache.shiro.crypto.RandomNumberGenerator;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.realm.jdbc.JdbcRealm;
import org.junit.Test;

/**
 * Created by Amysue on 2016/6/14.
 */
public class PasswordServiceTest extends BaseTest {
    public PasswordServiceTest() {
        super("chapter5");
    }

    @Test
    public void testPasswordServiceWithMyRealm1() {
        login("passwordservice", "zhang", "123");
    }

    @Test
    public void testGeneratePassword() {
        String username       = "liu";
        String upass          = "123";
        String salt1          = username;
        String salt2          = new SecureRandomNumberGenerator().nextBytes().toHex();
        int    hashIterations = 2;

        SimpleHash hash     = new Md5Hash(upass, salt1 + salt2, hashIterations);
        String     hashPass = hash.toHex();
        System.out.println("salt2 = " + salt2);
        System.out.println("hashPass = " + hashPass);
    }

    @Test
    public void testPasswordServiceWithJDBCRealm() {
        login("jdbc-passwordservice", "wu", "123");
    }

    @Test
    public void testHashedCredentialsMatcherWithMyRealm2() {
        login("hashed-credentialmacher", "liu", "123");
    }

    @Test
    public void testHashedCredentialsMatcherWithJDBCRealm() {
        BeanUtilsBean.getInstance().getConvertUtils().register(new EnumConverter(), JdbcRealm.SaltStyle.class);
        login("jdbc-hashed-credentialmacher", "liu", "123");
    }

    private class EnumConverter extends AbstractConverter {
        @Override
        protected String convertToString(Object value) throws Throwable {
            return ((Enum) value).name();
        }

        @Override
        protected Object convertToType(Class type, Object value) throws Throwable {
            return Enum.valueOf(type, value.toString());
        }

        @Override
        protected Class getDefaultType() {
            return null;
        }
    }

    @Test
    public void testRetryLimitsHashedCredentialsWithMyRealm2() {
        for (int i = 1; i <= 7; i++) {
            try {
                Thread.sleep(5000);
                login("retrylimits-hashed-credentialmacher", "liu", "1235");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        login("retrylimits-hashed-credentialmacher", "liu", "1235");
    }
    @Test
    public void testRetryLimitsHashedCredentialsWithMyRealm21() {
        for (int i = 1; i <= 4; i++) {
            try {
                login("retrylimits-hashed-credentialmacher", "liu", "1235");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        login("retrylimits-hashed-credentialmacher", "liu", "123");
        login("retrylimits-hashed-credentialmacher", "liu", "1235");
    }
}
