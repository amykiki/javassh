package dao;

import doc.dao.IDepScopeDao;
import jdk.nashorn.internal.ir.annotations.Reference;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

import static org.junit.Assert.*;

/**
 * Created by Amysue on 2016/5/5.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:beans.xml"})
public class DepScopeDaoTest {
    @Resource(name = "depScopeDao")
    private IDepScopeDao depScopeDao;

    @Test
    public void testAddDepScope() throws Exception {
        depScopeDao.addDepScope(2, 13);
        depScopeDao.addDepScope(2, 16);
        depScopeDao.addDepScope(2, 17);
    }

    @Test
    public void testDeleteScope() throws Exception {

    }

    @Test
    public void testListDepScopeIds() throws Exception {

    }
}