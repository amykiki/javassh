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
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;
import java.io.File;
import java.util.List;

/**
 * Created by Amysue on 2016/5/14.
 */
@Controller("msgAction")
@Scope("prototype")
public class MessageAction extends ActionSupport {
    private IMessageService msgService;
    private IAttachmentService attachService;
    private IUserService userService;
    private Message cMsg;
    private int[] sendToIds;
    private Attachment cAttach;
    private File[] atts;
    private String[] attsContentType;
    private String[] attsFileName;

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
        User lguser = ActionUtil.getLguser();
        ActionContext.getContext().put("susers",userService.listAllSendUsers(lguser.getId()));
        return SUCCESS;
    }

    public String add() {
        return ActionUtil.REDIRECT;
    }

    public void validateAdd() {

    }


}
