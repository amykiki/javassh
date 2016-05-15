package doc.action;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import doc.entity.Attachment;
import doc.entity.Message;
import doc.entity.User;
import doc.service.IAttachmentService;
import doc.service.IMessageService;
import doc.service.IUserService;
import doc.util.ActionUtil;
import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import java.io.File;
import java.util.List;

/**
 * Created by Amysue on 2016/5/14.
 */
@Controller("msgAction")
@Scope("prototype")
public class MessageAction extends ActionSupport {
    private IMessageService    msgService;
    private IAttachmentService attachService;
    private IUserService       userService;
    private Message            cMsg;
    private int[]              sendToIds;
    private Attachment         cAttach;
    private File[]             atts;
    private String[]           attsContentType;
    private String[]           attsFileName;

    @Resource(name = "msgService")
    public void setMsgService(IMessageService msgService) {
        this.msgService = msgService;
    }

    @Resource(name = "attachService")
    public void setAttachService(IAttachmentService attachService) {
        this.attachService = attachService;
    }

    @Resource(name = "userService")
    public void setUserService(IUserService userService) {
        this.userService = userService;
    }

    public Message getcMsg() {
        return cMsg;
    }

    public void setcMsg(Message cMsg) {
        this.cMsg = cMsg;
    }

    public int[] getSendToIds() {
        return sendToIds;
    }

    public void setSendToIds(int[] sendToIds) {
        this.sendToIds = sendToIds;
    }

    public Attachment getcAttach() {
        return cAttach;
    }

    public void setcAttach(Attachment cAttach) {
        this.cAttach = cAttach;
    }

    public File[] getAtts() {
        return atts;
    }

    public void setAtts(File[] atts) {
        this.atts = atts;
    }

    public String[] getAttsContentType() {
        return attsContentType;
    }

    public void setAttsContentType(String[] attsContentType) {
        this.attsContentType = attsContentType;
    }

    public String[] getAttsFileName() {
        return attsFileName;
    }

    public void setAttsFileName(String[] attsFileName) {
        this.attsFileName = attsFileName;
    }

    public String addInput() {
        System.out.println(ServletActionContext.getServletContext().getAttribute("javax.servlet.context.tempdir"));
        User       lguser = ActionUtil.getLguser();
        List<User> susers = userService.listAllSendUsers(lguser.getId());
        ActionContext.getContext().put("susers", susers);
        return SUCCESS;
    }

    public String add() {
        ActionUtil.setUrl("/msg_listSend.action");
        return ActionUtil.REDIRECT;
       /* if (hasActionErrors()) {
            ActionUtil.setUrl("msg_addInput.action");
            return ActionUtil.REDIRECT;
        } else {

        }*/
    }

    /*public void validateAdd() {
        if (!hasActionErrors()) {
            if (cMsg.getTitle() == null || cMsg.getTitle().trim().equals("")) {
                addFieldError("cMsg.title", "邮件标题不能为空");
            }
            if (sendToIds == null || sendToIds.length <= 0) {
                addFieldError("sendToIds", "没有选择收信人");
            }
            if (hasFieldErrors()) {
                User       lguser = ActionUtil.getLguser();
                List<User> susers = userService.listAllSendUsers(lguser.getId());
                ActionContext.getContext().put("susers", susers);
            }

        } else {
            addInput();
        }
    }*/

    public String listSend() {
        return SUCCESS;
    }

    public String listReceive() {
        return SUCCESS;
    }

    @Override
    public void addActionError(String anErrorMessage) {
        System.out.println(anErrorMessage);
        super.addActionError(anErrorMessage);
    }
}
