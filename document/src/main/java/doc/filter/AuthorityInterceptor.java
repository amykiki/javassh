package doc.filter;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import doc.entity.User;
import doc.enums.Role;
import doc.util.ActionUtil;
import doc.util.PropertiesUtil;
import org.springframework.stereotype.Component;

import java.util.Properties;

/**
 * Created by Amysue on 2016/5/12.
 */
@Component("authInterceptor")
public class AuthorityInterceptor extends AbstractInterceptor {
    private String[] normalNames;
    private String[] adminNames;
    @Override
    public void init() {
        System.out.println("==========INITION AuthorityInterceptor============");
        Properties authProp = PropertiesUtil.getAuthProp();
        String adminStr = authProp.getProperty(Role.ADMIN.toString());
        String normalStr = authProp.getProperty(Role.NORMAL.toString());
        normalNames = normalStr.trim().split(",");
        adminNames = adminStr.split(",");
    }


    @Override
    public String intercept(ActionInvocation actionInvocation) throws Exception {
        String actionName = actionInvocation.getProxy().getActionName();
        ActionContext ctx = actionInvocation.getInvocationContext();
        User lguser = (User) ctx.getSession().get("lguser");
        if (lguser == null) {
            if (!actionName.startsWith("login_") || actionName.equals("login_logout")) {
                ctx.put(ActionUtil.AUTHINFO, "没有登录，请先登录");
                ctx.getSession().put("loginAction", actionName);
                return ActionUtil.LOGIN;
            }
        } else {
            if (actionName.startsWith("login_register")) {
                ctx.put(ActionUtil.AUTHINFO, "已经登录");
                return ActionUtil.WELCOME;
            } else if (lguser.getRole() == Role.NORMAL) {
                for (String name : normalNames) {
                    if (actionName.startsWith(name)) {
                        return actionInvocation.invoke();
                    }
                }
                for (String name : adminNames) {
                    if (actionName.startsWith(name)) {
                        ctx.put(ActionUtil.AUTHINFO, "没有权限访问的内容");
                        return ActionUtil.GERROR;
                    }
                }
            }
        }
        return actionInvocation.invoke();
    }
}
