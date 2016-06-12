package com.github.amysue.shiro.chapter2;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

/**
 * Created by Amysue on 2016/6/12.
 */
public class AuthenticatorTest {
    @Rule
    public final ExpectedException exception = ExpectedException.none();
    private void initial(String ini) {
        String                   iniFile = "classpath:" + ini + ".ini";
        Factory<SecurityManager> factory = new IniSecurityManagerFactory(iniFile);
        SecurityManager          manager = factory.getInstance();
        SecurityUtils.setSecurityManager(manager);
    }

    private void login(String ini) {
        initial(ini);
        Subject               subject = SecurityUtils.getSubject();
        UsernamePasswordToken token   = new UsernamePasswordToken("zhang", "123");
        subject.login(token);
    }

    @Test
    public void TestAllSuccessfulStrategyWithSuccess() {
        login("shiro-authenticator-all-success");
        Subject subject = SecurityUtils.getSubject();
        PrincipalCollection principalCollection = subject.getPrincipals();
        Assert.assertEquals(2, principalCollection.asList().size());
    }

    @Test
    public void TestAllSuccessfulStrategyWithFail() {
        exception.expect(UnknownAccountException.class);
        login("shiro-authenticator-all-fail");
    }

    @Test
    public void TestAtLeastOneStrategyWithSuccess() {
        login("shiro-authenticator-atLeastOne-success");
        Subject subject = SecurityUtils.getSubject();
        PrincipalCollection principalCollection = subject.getPrincipals();
        Assert.assertEquals(2, principalCollection.asList().size());
    }
    @Test
    public void TestFirstStrategyWithSuccess() {
        login("shiro-authenticator-first-success");
        Subject subject = SecurityUtils.getSubject();
        PrincipalCollection principalCollection = subject.getPrincipals();
        Assert.assertEquals(1, principalCollection.asList().size());
    }

    @Test
    public void TestAtLeastTwoStrategyWithSuccess() {
        login("shiro-authenticator-atLeastTwo-success");
        Subject subject = SecurityUtils.getSubject();
        PrincipalCollection principalCollection = subject.getPrincipals();
        Assert.assertEquals(1, principalCollection.asList().size());
    }

    @Test
    public void TestAtLeastTwoStrategyWithFail() {
        initial("shiro-authenticator-atLeastTwo-success");
        Subject               subject = SecurityUtils.getSubject();
        UsernamePasswordToken token   = new UsernamePasswordToken("wang", "456");
        subject.login(token);
        PrincipalCollection principalCollection = subject.getPrincipals();
        Assert.assertEquals(2, principalCollection.getRealmNames().size());
    }

}
