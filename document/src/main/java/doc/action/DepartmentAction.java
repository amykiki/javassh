package doc.action;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import doc.entity.DepScope;
import doc.entity.Department;
import doc.exception.DocException;
import doc.service.IDepartmentService;
import doc.util.ActionUtil;
import org.apache.struts2.interceptor.validation.SkipValidation;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Amysue on 2016/5/5.
 */
@Controller("depAction")
@Scope("prototype")
//public class DepartmentAction extends ActionSupport implements ModelDriven<Department>{
public class DepartmentAction extends ActionSupport{
    private Department dep;
    private Department oldDep;
    private DepScope dscope;
    private IDepartmentService depService;
    private String[] dscopeIds;

    public Department getDep() {
        return dep;
    }

    public void setDep(Department dep) {
        this.dep = dep;
    }

    public String[] getDscopeIds() {
        return dscopeIds;
    }

    public void setDscopeIds(String[] dscopeIds) {
        this.dscopeIds = dscopeIds;
    }

    public IDepartmentService getDepService() {
        return depService;
    }

    @Resource(name = "depService")
    public void setDepService(IDepartmentService depService) {
        this.depService = depService;
    }

   /* @Override
    public Department getModel() {
        if (dep == null) {
            dep = new Department();
        }
        return dep;
    }*/

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
        try {
            depService.delete(dep.getId());
        } catch (DocException e) {
            addFieldError("dep.id", e.getMessage());
            addActionError(e.getMessage());
            addActionMessage(e.getMessage());
            list();
            ActionUtil.setUrl("dep/list.jsp");
            return ActionUtil.FORWARD;
        }
        return redirectToList();
    }

    @SkipValidation
    public String updateScopeInput() {
        Department tdep = depService.load(dep.getId());
        ActionUtil.copyProperties(dep, tdep);
        List<Department> nods = depService.listAllDep();
        nods.remove(dep);
        List<Department> ds = depService.listAllDepScope(dep.getId());
        nods.removeAll(ds);
        ActionContext.getContext().put("ds", ds);
        ActionContext.getContext().put("nods", nods);
        return SUCCESS;
    }

    @SkipValidation
    public String updateScope() {
        Integer[] dsIds = new Integer[dscopeIds.length];
        for (int i = 0; i < dscopeIds.length; i++) {
            dsIds[i] = Integer.parseInt(dscopeIds[i]);
        }
        depService.updateDepScope(dep.getId(), dsIds);
        ActionUtil.setUrl("/dep_show.action?dep.id=" + dep.getId());
        return ActionUtil.REDIRECT;
    }

    @SkipValidation
    public String show() {
        Department tdep = depService.load(dep.getId());
        ActionUtil.copyProperties(dep, tdep);
        List<Department> ds = depService.listAllDepScope(dep.getId());
        ActionContext.getContext().put("ds", ds);
        return SUCCESS;
    }
    private String redirectToList() {
        ActionUtil.setUrl("/dep_list.action");
        return ActionUtil.REDIRECT;
    }
}
