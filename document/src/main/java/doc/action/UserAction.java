package doc.action;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import doc.dto.Pager;
import doc.entity.Department;
import doc.entity.User;
import doc.enums.Role;
import doc.service.IDepartmentService;
import doc.service.IUserService;
import doc.util.ActionUtil;
import org.apache.struts2.interceptor.validation.SkipValidation;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Amysue on 2016/5/9.
 */
@Controller("userAction")
@Scope("prototype")
public class UserAction extends ActionSupport{
    private Map<String, Object> findParams = new HashMap<>();
    private Map<String, String> roleParams = new HashMap<>();
    private IUserService       userService;
    private IDepartmentService depService;
   {
        roleParams.put("-1", "所有用户");
        roleParams.put(Role.NORMAL.toString(), "普通用户");
        roleParams.put(Role.ADMIN.toString(), "管理员");
    }

    public Map<String, String> getRoleParams() {
        return roleParams;
    }

    public Map<String, Object> getFindParams() {
        return findParams;
    }

    public void setFindParams(Map<String, Object> findParams) {
        this.findParams = findParams;
    }

    @Resource(name = "userService")
    public void setUserService(IUserService userService) {
        this.userService = userService;
    }
    @Resource(name = "depService")
    public void setDepService(IDepartmentService depService) {
        this.depService = depService;
    }

    @SkipValidation
    public String list() {
        if (findParams.get("role") == null) {
            findParams.put("role", "-1");
        } else {
            String[] pRole = (String[])findParams.get("role");
            findParams.put("role", (String)pRole[0]);
        }
        if (findParams.get("deps") == null) {
            findParams.put("deps", null);
        } else {
            String[] selected = (String[]) findParams.get("deps");
            Integer[] selectDIds = new Integer[selected.length];
            for (int i = 0; i < selected.length; i++) {
                selectDIds[i] = Integer.parseInt(selected[i]);
            }
            findParams.put("deps", selectDIds);
        }
        if (findParams.get("username") != null) {
            String[] pUsername = (String[])findParams.get("username");
            findParams.put("username", pUsername[0]);
        }
        if (findParams.get("nickname") != null) {
            String[] pNickname = (String[])findParams.get("nickname");
            findParams.put("nickname", pNickname[0]);
        }
        List<Department> allDeps   = depService.listAllDep();
        Pager<User>      findUsers = userService.findUser(findParams);
        ActionContext.getContext().put("fusers", findUsers);
        ActionContext.getContext().put("allds", allDeps);
        return SUCCESS;
    }
  /*  @SkipValidation
    public int[] getDefaultDeps() {
        return (int[])findParams.get("deps");
    }*/


}
