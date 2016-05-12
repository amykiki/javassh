package doc.action;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
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
import java.util.List;

/**
 * Created by Amysue on 2016/5/11.
 */
@Controller("loginAction")
@Scope("prototype")
public class LoginAction extends ActionSupport {
    private User               cUser;
    private List<Department>   allds;
    private int                depId;
    private String             repeatPWD;
    private IUserService       userService;
    private IDepartmentService depService;

    public User getcUser() {
        return cUser;
    }

    public void setcUser(User cUser) {
        this.cUser = cUser;
    }

    public List<Department> getAllds() {
        return allds;
    }

    public void setAllds(List<Department> allds) {
        this.allds = allds;
    }

    public int getDepId() {
        return depId;
    }

    public void setDepId(int depId) {
        this.depId = depId;
    }

    public String getRepeatPWD() {
        return repeatPWD;
    }

    public void setRepeatPWD(String repeatPWD) {
        this.repeatPWD = repeatPWD;
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
    public String registerInput() {
        allds   = depService.listAllDep();
        return SUCCESS;
    }

    public String register() {
        cUser.setRole(Role.NORMAL);
        try {
            userService.add(cUser, depId);
        } catch (DocException e) {
            allds   = depService.listAllDep();
            addActionError(e.getMessage());
            return INPUT;
        }
        ActionUtil.setUrl("/login_userInput.action");
        return ActionUtil.REDIRECT;
    }
    @Override
    public void validate() {
        allds   = depService.listAllDep();
    }

    @SkipValidation
    public String userInput() {
        return SUCCESS;
    }

    @SkipValidation
    public String user() {
        if (cUser.getUsername() == null || cUser.getUsername().length() < 3 ||
                cUser.getPassword() == null || cUser.getPassword().length() < 4) {
            addActionError("输入用户名或密码不正确");
            return INPUT;
        }
        User lguser = userService.loadEagerByUsername(cUser.getUsername());
        if (lguser == null || !lguser.getPassword().equals(cUser.getPassword())) {
            addActionError("输入用户名或密码不正确");
            return INPUT;
        }
        ActionContext.getContext().getSession().put("lguser", lguser);
        ActionUtil.setUrl("/user_showSelf.action");
        return ActionUtil.REDIRECT;
    }

}
