package doc.filter;

import doc.dto.SystemContext;
import doc.entity.User;
import doc.util.ActionUtil;

import javax.servlet.*;
import java.io.IOException;

/**
 * Created by Amysue on 2016/5/9.
 */
public class SystemContextFilter implements Filter {
    private int pageSize;
    private int pageRange;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        pageSize = Integer.parseInt(filterConfig.getInitParameter("pageSize"));
        pageRange = Integer.parseInt(filterConfig.getInitParameter("pageRange"));
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
       /* int pageOffset = 0;
        try {
            pageOffset = Integer.parseInt(servletRequest.getParameter("pageOffset"));
        } catch (NumberFormatException e) {

        }*/
         try {
             SystemContext.setPageSize(pageSize);
             SystemContext.setPageRange(pageRange);
             /*User lguser = ActionUtil.getLguser();
             if (lguser != null) {
                 SystemContext.setLguser(lguser);
             }*/
             filterChain.doFilter(servletRequest, servletResponse);
         } finally {
             SystemContext.removePageRange();
             SystemContext.RemovePageSize();
             SystemContext.removeLguser();
         }

    }

    @Override
    public void destroy() {

    }
}
