package doc.action;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import doc.dto.Pager;
import doc.entity.Department;
import doc.entity.User;
import doc.enums.Role;
import doc.exception.DocException;
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
    private int uid;
    private String role;
    private User cUser;
    private String oldPwd;
    private int pageOffset = 0;
    private List<Department> allds;
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

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public List<Department> getAllds() {
        return allds;
    }

    public void setAllds(List<Department> allds) {
        this.allds = allds;
    }

    public User getcUser() {
        return cUser;
    }

    public void setcUser(User cUser) {
        this.cUser = cUser;
    }

    public String getOldPwd() {
        return oldPwd;
    }

    public void setOldPwd(String oldPwd) {
        this.oldPwd = oldPwd;
    }

    public int getPageOffset() {
        return pageOffset;
    }

    public void setPageOffset(int pageOffset) {
        this.pageOffset = pageOffset;
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
    public String delete() {
        // TODO: 2016/5/10 如果用户还有信息和邮件则不能删除
        userService.delete(uid);
        list();
        ActionUtil.setUrl("user/list.jsp");
        return ActionUtil.FORWARD;
    }

    @SkipValidation
    public String updateRole() {
        Role r1 = null;
        if (role.equals(Role.ADMIN.toString())) {
            r1 = Role.NORMAL;
        } else if (role.equals(Role.NORMAL.toString())) {
            r1 = Role.ADMIN;
        }

        if (r1 != null) {
            userService.updateRole(r1, uid);
        }
        list();
        ActionUtil.setUrl("user/list.jsp");
        return ActionUtil.FORWARD;
    }

    @SkipValidation
    public String updateInput() {
        cUser = userService.loadEagerById(uid);
        if (cUser != null) {
            allds   = depService.listAllDep();
            role = cUser.getRole().toString();
            return SUCCESS;
        } else {
            addActionError("ID为" + uid + "用户不存在");
            return ActionUtil.GERROR;
        }
    }

    public String update() {
        try {
            cUser.setId(uid);
            cUser.setRole(Role.valueOf(role));
            userService.update(cUser, cUser.getDep().getId());
        } catch (DocException e) {
            allds   = depService.listAllDep();
            addActionError(e.getMessage());
            return INPUT;
        }

        return ActionUtil.redirectToList("user");
    }

    @Override
    public void validate() {
        allds   = depService.listAllDep();
    }

    @SkipValidation
    public String showSelf() {
        return SUCCESS;
    }

    @SkipValidation
    public String updateSelfInput() {
        cUser = ActionUtil.getLguser();
        return SUCCESS;
    }

    public String updateSelf() {
        User lguser = ActionUtil.getLguser();
        //以下属性用户不能修改,必须由admin用户修改
        cUser.setId(lguser.getId());
        cUser.setDep(lguser.getDep());
        cUser.setRole(lguser.getRole());
        //这里不允许修改密码
        cUser.setPassword(lguser.getPassword());

        try {
            userService.update(cUser, lguser.getDep().getId());
        } catch (DocException e) {
            addActionError(e.getMessage());
            return INPUT;
        }
        lguser = userService.loadEagerById(lguser.getId());
        ActionUtil.setLguser(lguser);
        ActionUtil.setUrl("/user_showSelf.action");
        return ActionUtil.REDIRECT;
    }

    public String updatePwdInput() {
        return SUCCESS;
    }

    public String updatePwd() {
        if (cUser.getPassword() == null || cUser.getPassword().length() < 4 || cUser.getPassword().length() > 10) {
            addFieldError("cUser.password", "密码长度不能小于4位不能大于10位");
            return INPUT;
        }
        User lguser = ActionUtil.getLguser();
        if (!lguser.getPassword().equals(oldPwd)) {
            addFieldError("oldPwd", "原有密码不正确，不能修改");
            return INPUT;
        }
        userService.updatePwd(lguser.getId(), cUser.getPassword());
        lguser.setPassword(cUser.getPassword());
        ActionUtil.setUrl("/user_showSelf.action");
        return ActionUtil.REDIRECT;
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
        allds   = depService.listAllDep();
        Pager<User>      findUsers = userService.findUser(findParams, pageOffset);
        ActionContext.getContext().put("fusers", findUsers);
        return SUCCESS;
    }
  /*  @SkipValidation
    public int[] getDefaultDeps() {
        return (int[])findParams.get("deps");
    }*/
}
