package com.github.amysue.shiro;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.apache.shiro.util.ThreadContext;
import org.junit.After;
import org.junit.Rule;
import org.junit.rules.ExpectedException;

/**
 * Created by Amysue on 2016/6/14.
 */
public abstract class BaseTest {
    private String chapter;

    public BaseTest(String chapter) {
        this.chapter = chapter;
    }

    @Rule
    public final ExpectedException exception = ExpectedException.none();
    protected void initial(String ini) {
        String                   iniFile = "classpath:"+chapter+"/shiro-" + ini + ".ini";
        Factory<SecurityManager> factory = new IniSecurityManagerFactory(iniFile);
        SecurityManager          manager = factory.getInstance();
        SecurityUtils.setSecurityManager(manager);
    }
    protected Subject login(String ini) {
        return login(ini, "zhang", "123");
    }
    protected Subject login(String ini, String usernmae) {
        return login(ini, usernmae, "123");
    }
    protected Subject login(String ini, String username, String password) {
        initial(ini);
        Subject               subject = SecurityUtils.getSubject();
        UsernamePasswordToken token   = new UsernamePasswordToken(username, password);
        subject.login(token);
        return subject;
    }

    @After
    public void tearDown() {
        ThreadContext.unbindSubject();
    }

}
