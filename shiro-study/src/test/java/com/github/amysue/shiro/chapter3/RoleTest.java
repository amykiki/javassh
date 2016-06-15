package com.github.amysue.shiro.chapter3;

import com.github.amysue.shiro.BaseTest;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.UnauthorizedException;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.apache.shiro.util.ThreadContext;
import org.junit.After;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.Arrays;

/**
 * Created by Amysue on 2016/6/12.
 */
public class RoleTest extends BaseTest{
    /*@Rule
    public final ExpectedException exception = ExpectedException.none();
    private void initial(String ini) {
        String                   iniFile = "classpath:chapter3/shiro-" + ini + ".ini";
        Factory<SecurityManager> factory = new IniSecurityManagerFactory(iniFile);
        SecurityManager          manager = factory.getInstance();
        SecurityUtils.setSecurityManager(manager);
    }
    private Subject login(String ini) {
        return login(ini, "zhang", "123");
    }
    private Subject login(String ini, String usernmae) {
        return login(ini, usernmae, "123");
    }
    private Subject login(String ini, String username, String password) {
        initial(ini);
        Subject               subject = SecurityUtils.getSubject();
        UsernamePasswordToken token   = new UsernamePasswordToken(username, password);
        subject.login(token);
        return subject;
    }*/

    public RoleTest() {
        super("chapter3");
    }

    @Test
    public void testHasRole() {
        Subject subject = login("role");
        Assert.assertEquals(true, subject.isAuthenticated());
        Assert.assertTrue(subject.hasRole("role1"));
        Assert.assertTrue(subject.hasAllRoles(Arrays.asList("role1", "role2")));
        boolean[] results = subject.hasRoles(Arrays.asList("role1", "role2", "role3"));
        Assert.assertFalse(results[2]);
    }

    @Test
    public void testCheckRole() {
        Subject subject = login("role");
        subject.checkRole("role1");
        exception.expect(UnauthorizedException.class);
        subject.checkRole("role3");
    }

    @Test
    public void testIsPermission() {
        Subject subject = login("permission");
        Assert.assertTrue(subject.isPermitted("user:create"));
        Assert.assertTrue(subject.isPermittedAll("user:create", "user:update", "user:delete"));
        boolean[] results = subject.isPermitted("user:create", "user:updatePwd", "user:update");
        Assert.assertFalse(results[1]);
    }

    @Test
    public void testCheckPermission() {
        Subject subject = login("permission");
        subject.checkPermission("user:create");
        subject.checkPermissions("user:create", "user:update", "user:delete");
        exception.expect(UnauthorizedException.class);
        subject.checkPermission("user:updatePwd");
        subject.checkPermissions("user:create", "user:updatePwd", "user:update");
    }

    @Test
    public void testWildcardPermission1() {
        Subject subject = login("permission");
        Assert.assertTrue(subject.isPermitted("system:msg:create"));
        Assert.assertTrue(subject.isPermittedAll("system:msg:view,create"));
        Assert.assertTrue(subject.isPermittedAll("system:msg:view","create"));
    }

    @Test
    public void testWildcardPermission2() {
        Subject subject = login("permission", "amy1");
        Assert.assertTrue(subject.isPermittedAll("sys:msg:view,create,update,delete"));
    }

    @Test
    public void testWildPermmMsg2() {
        Subject subject = login("permission", "amy2");
        Assert.assertTrue(subject.isPermitted("sys:msg:update"));
        Assert.assertTrue(subject.isPermitted("sys:msg:add"));
        Assert.assertTrue(subject.isPermitted("sys:msg:create"));
        Assert.assertTrue(subject.isPermitted("sys:msg:view"));
        Assert.assertTrue(subject.isPermittedAll("sys:msg:view,delete,add,update"));

    }
    @Test
    public void testWildPermmMsg3() {
        Subject subject = login("permission", "amy3");
        Assert.assertTrue(subject.isPermitted("sys:msg:update"));
        Assert.assertTrue(subject.isPermitted("sys:msg:add"));
        Assert.assertTrue(subject.isPermitted("sys:msg:create"));
        Assert.assertTrue(subject.isPermitted("sys:msg:view"));
        Assert.assertTrue(subject.isPermittedAll("sys:msg:view,delete,add,update,hello,hahha"));

    }

    @Test
    public void testWildPermmMsg4() {
        Subject subject = login("permission", "amy4");
        Assert.assertTrue(subject.isPermitted("sys:msg:update"));
        Assert.assertTrue(subject.isPermitted("sys:msg:add"));
        Assert.assertTrue(subject.isPermitted("sys:msg:create"));
        Assert.assertTrue(subject.isPermitted("sys:msg:view"));
        Assert.assertTrue(subject.isPermittedAll("sys:msg:view,delete,add,update,hello,hahha"));

    }

    @Test
    public void testWildPermmMsg5() {
        Subject subject = login("permission", "amy5");
        Assert.assertTrue(subject.isPermitted("sys:user:msg"));
        Assert.assertTrue(subject.isPermitted("book:delete:msg"));
        Assert.assertFalse(subject.isPermitted("sys:msg"));
        Assert.assertFalse(subject.isPermitted("sys:msg:view"));
        Assert.assertTrue(subject.isPermittedAll("sys:view,delete,add,update,hello,hahha:msg"));
    }

    @Test
    public void testWildPermmMsg6() {
        Subject subject = login("permission", "amy6");
        Assert.assertTrue(subject.isPermitted("user:add:1"));
        Assert.assertTrue(subject.isPermitted("user:update:1"));
        Assert.assertFalse(subject.isPermitted("user:delete:1"));
        Assert.assertTrue(subject.isPermittedAll("user:add,update:1"));
    }

    @Test
    public void testWildPermmMsg7() {
        Subject subject = login("permission", "amy7");
        Assert.assertTrue(subject.isPermitted("user:add:1"));
        Assert.assertTrue(subject.isPermitted("user:update:1"));
        Assert.assertTrue(subject.isPermitted("user:delete:1"));
        Assert.assertFalse(subject.isPermitted("msg:delete:1"));
        Assert.assertTrue(subject.isPermittedAll("user:add,update,delete:1"));
    }

    @Test
    public void testWildPermmMsg8() {
        Subject subject = login("permission", "amy8");
        Assert.assertTrue(subject.isPermitted("user:view:1"));
        Assert.assertTrue(subject.isPermitted("user:view:2"));
        Assert.assertTrue(subject.isPermitted("user:view:delete"));
        Assert.assertFalse(subject.isPermitted("user:delete:1"));
        Assert.assertTrue(subject.isPermittedAll("user:view:1,add,update,delete"));
    }

    @Test
    public void testWildPermmMsg9() {
        Subject subject = login("permission", "amy9");
        Assert.assertTrue(subject.isPermitted("user:view:1"));
        Assert.assertTrue(subject.isPermitted("user:add:2"));
        Assert.assertTrue(subject.isPermitted("user:update:delete"));
        Assert.assertFalse(subject.isPermitted("msg:delete:1"));
        Assert.assertTrue(subject.isPermittedAll("user:view:1,add,update,delete","user:add", "user:delete:234"));
    }

    @Test
    public void testWildPermmMsg10() {
        Subject subject = login("permission", "amy10");
        Assert.assertTrue(subject.isPermitted("user:view:1"));
        Assert.assertTrue(subject.isPermitted("user:add:2"));
        Assert.assertTrue(subject.isPermitted("user:update:delete"));
        Assert.assertFalse(subject.isPermitted("msg:delete:1"));
        Assert.assertTrue(subject.isPermittedAll("user:view:1,add,update,delete","user:add", "user:delete:234"));
    }

    @Test
    public void testWildPermmMsg11() {
        Subject subject = login("permission", "amy11");
        Assert.assertTrue(subject.isPermitted("user:view:1"));
        Assert.assertTrue(subject.isPermitted("user:add:2"));
        Assert.assertTrue(subject.isPermitted("user:update:delete"));
        Assert.assertFalse(subject.isPermitted("msg:delete:1"));
        Assert.assertTrue(subject.isPermittedAll("user:view:1,add,update,delete","user:add", "user:delete:234"));
    }

    @Test
    public void testAuthorizer1() {
        Subject subject = login("authorizer");
        Assert.assertTrue(subject.hasRole("role1"));
        Assert.assertTrue(subject.isPermitted("user1:create"));
        Assert.assertTrue(subject.isPermitted("user1:update:2"));
        Assert.assertTrue(subject.isPermitted("user2:delete:*"));
        Assert.assertTrue(subject.isPermitted("+user1+2+add"));
        Assert.assertTrue(subject.isPermitted("menu:view"));
        Assert.assertFalse(subject.isPermitted("+user3+14+delete"));
        Assert.assertFalse(subject.isPermitted("+user3+1+printer"));
        Assert.assertTrue(subject.isPermitted("+user3+2+printer"));
        Assert.assertFalse(subject.isPermitted("+user3+2"));
        Assert.assertFalse(subject.isPermitted("+user3+4+*"));
    }

}
