package service;

import doc.entity.Department;
import doc.service.IDepartmentService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by Amysue on 2016/5/4.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:beans.xml"})
public class DepartmentServiceTest {
    @Resource(name = "depService")
    private IDepartmentService depService;

    @Test
    public void testAdd() throws Exception {
        Department dep   = new Department();
//        String[]   names = new String[]{"学生处", "就业招生部", "行政办公室", "科研办公室", "党委", "宣传部", "后勤", "法务部", "留学生办公室", "信息学院", "法学院", "数学系"};
//        for (String name : names) {
//            dep.setName(name);
//            depService.add(dep);
//        }
//        dep.setName("法学院");
//        depService.add(dep);
//        dep.setName("历史系");
//        dep.setName("中文系");
        dep.setName("校长办公室");
        depService.add(dep);
    }

    @Test
    public void testDelete() throws Exception {
        depService.delete(14);
    }

    @Test
    public void testLoad() throws Exception {
        System.out.println(depService.load(2).getName());
    }

    @Test
    public void testListAllDep() throws Exception {
        List<Department> deps = depService.listAllDep();
        for (Department dep : deps) {
            System.out.println(dep);
        }
    }

    @Test
    public void testUpdate() throws Exception {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("beans.xml");
        Object o = ctx.getBean("dataSource");
        Department dep = new Department();
        dep.setName("党委研究");
        dep.setId(8);
        depService.update(dep);
    }

    @Test
    public void testGetDepByName() throws Exception {

    }

    @Test
    public void testAddDepScope() throws Exception {
//        depService.addDepScope(2, 13);
        depService.addDepScope(2, 16);
        depService.addDepScope(2, 6);
        depService.addDepScope(2, 7);
        depService.addDepScope(2, 9);
        depService.addDepScope(2, 17);

    }

    @Test
    public void testDeleteScope() throws Exception {
        depService.deleteScope(2, new Integer[]{16, 6, 7});
    }

    @Test
    public void testListDepScopeIds() throws Exception {
        List<Integer> sids = depService.listDepScopeIds(2);
        for (int sid : sids) {
            System.out.println(sid);
        }
    }

    @Test
    public void testUpdateDepScope() throws Exception {
        depService.updateDepScope(2, new Integer[]{13, 16, 6, 7, 3, 4, 10});

    }
}