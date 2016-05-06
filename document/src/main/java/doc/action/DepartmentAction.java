package doc.action;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import doc.entity.Department;
import doc.exception.DocException;
import doc.service.IDepartmentService;
import doc.util.ActionUtil;
import org.apache.struts2.interceptor.validation.SkipValidation;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;

/**
 * Created by Amysue on 2016/5/5.
 */
@Controller("depAction")
@Scope("prototype")
public class DepartmentAction extends ActionSupport implements ModelDriven<Department>{
    private Department dep;
    private Department oldDep;
    private IDepartmentService depService;

    public Department getDep() {
        return dep;
    }

    public void setDep(Department dep) {
        this.dep = dep;
    }

    public IDepartmentService getDepService() {
        return depService;
    }

    @Resource(name = "depService")
    public void setDepService(IDepartmentService depService) {
        this.depService = depService;
    }

    @Override
    public Department getModel() {
        if (dep == null) {
            dep = new Department();
        }
        return dep;
    }

    @SkipValidation
    public String list() {
        ActionContext.getContext().put("ds", depService.listAllDep());
        return SUCCESS;
    }

    public String addInput() {
        return SUCCESS;
    }

    public String add() {
        try {
            depService.add(dep);
        } catch (DocException e) {
            addFieldError("dep.name", e.getMessage());
            return INPUT;
        }
        ActionUtil.setUrl("/dep_list.action");
        return ActionUtil.REDIRECT;
    }

    @SkipValidation
    public String updateInput() {
        Department tdep = depService.load(dep.getId());
        ActionUtil.copyProperties(dep, tdep);
        return SUCCESS;
    }

    public String update() {
        try {
            depService.update(dep);
        } catch (DocException e) {
            addFieldError("dep.name", e.getMessage());
            return INPUT;
        }
        return redirectToList();
    }

    @SkipValidation
    public String delete() {
        depService.delete(dep.getId());
        return redirectToList();
    }
    private String redirectToList() {
        ActionUtil.setUrl("/dep_list.action");
        return ActionUtil.REDIRECT;
    }
}
