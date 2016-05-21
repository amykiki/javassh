package doc.action;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import doc.dto.AttachDto;
import doc.dto.Pager;
import doc.entity.Attachment;
import doc.entity.Message;
import doc.entity.User;
import doc.exception.DocException;
import doc.service.IAttachmentService;
import doc.service.IMessageService;
import doc.service.IUserService;
import doc.util.ActionUtil;
import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Amysue on 2016/5/14.
 */
@Controller("msgAction")
@Scope("prototype")
public class MessageAction extends ActionSupport {
    private IMessageService     msgService;
    private IAttachmentService  attachService;
    private IUserService        userService;
    private Message             cMsg;
    private int mid;
    private int[]               sendToIds;
    private List<Integer>       sendToIdsInt;
    private Attachment          cAttach;
    private Map<String, Object> findParams = new HashMap<>();
    private int pageOffset = 0;
    private File[]              atts;
    private String[]            attsContentType;
    private String[]            attsFileName;

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

    public int getMid() {
        return mid;
    }

    public void setMid(int mid) {
        this.mid = mid;
    }

    public int getPageOffset() {
        return pageOffset;
    }

    public void setPageOffset(int pageOffset) {
        this.pageOffset = pageOffset;
    }

    public Map<String, Object> getFindParams() {
        return findParams;
    }

    public void setFindParams(Map<String, Object> findParams) {
        this.findParams = findParams;
    }


    public String addInput() {
        System.out.println(ServletActionContext.getServletContext().getAttribute("javax.servlet.context.tempdir"));
        User       lguser = ActionUtil.getLguser();
        List<User> susers = userService.listAllSendUsers(lguser.getId());
        ActionContext.getContext().put("susers", susers);
        return SUCCESS;
    }

    public String add() {
        AttachDto attachDto = null;
        if (atts != null && atts.length > 0) {
            attachDto = new AttachDto(atts, attsContentType, attsFileName);
        }
        msgService.add(cMsg, sendToIdsInt, attachDto);
        ActionUtil.setUrl("/msg_listSend.action");
        return ActionUtil.REDIRECT;
    }

    public void validateAdd() {
        if (!hasFieldErrors()) {
            if (cMsg == null || cMsg.getTitle() == null || cMsg.getTitle().trim().equals("")) {
                addFieldError("cMsg.title", "邮件标题不能为空");
            }
            if (sendToIds == null || sendToIds.length <= 0) {
                addFieldError("sendToIds", "没有选择收信人");
            } else {
                List<Integer> allSendToIds = userService.listAllSendUsersId(ActionUtil.getLguser().getId());
                sendToIdsInt = new ArrayList<>();
                for (int i = 0; i < sendToIds.length; i++) {
                    sendToIdsInt.add(sendToIds[i]);
                }
                sendToIdsInt.retainAll(allSendToIds);
                if (sendToIdsInt.size() <= 0) {
                    addFieldError("sendToIds", "请选择正确的收信人");
                }

            }

        }
        if (hasFieldErrors()) {
            User       lguser = ActionUtil.getLguser();
            List<User> susers = userService.listAllSendUsers(lguser.getId());
            ActionContext.getContext().put("susers", susers);
        }
    }

    public String listSend() {
        ActionUtil.getActionParams(findParams);
        Pager<Message> pager = msgService.findSendMsg(findParams, pageOffset);
        ActionContext.getContext().put("mpager", pager);
        return SUCCESS;
    }

    public String loadSend() {
        try {
            cMsg = msgService.loadSendMsg(mid);
        } catch (DocException e) {
            addActionError(e.getMessage());
            return ActionUtil.GERROR;
        }
        ActionUtil.setUrl("msg/showMsg.jsp");
        return ActionUtil.FORWARD;
    }

    public String listReceive() {
        return SUCCESS;
    }

    public String deleteSend() {
        msgService.delete(mid);
        listSend();
        ActionUtil.setUrl("msg/listSend.jsp");
        return ActionUtil.FORWARD;
    }

    @Override
    public void addActionError(String anErrorMessage) {
        if (anErrorMessage.startsWith("Request exceeded allowed size limit")) {
            System.out.println(anErrorMessage);
            Pattern p = Pattern.compile("Max size allowed is: (\\d[\\d,]*) but request was: (\\d[\\d,]*)");
            Matcher m = p.matcher(anErrorMessage);
            if (m.find()) {
                String maxSizeStr    = m.group(1);
                String uploadSizeStr = m.group(2);
                maxSizeStr = maxSizeStr.replaceAll(",", "");
                uploadSizeStr = uploadSizeStr.replaceAll(",", "");
                long maxSize    = Long.parseLong(maxSizeStr);
                long uploadSize = Long.parseLong(uploadSizeStr);
                System.out.println("maxSize = " + maxSize);
                System.out.println("uploadSize = " + uploadSize);
                anErrorMessage = "上传文件大小超过了最大可上传大小：可以上传的文件最大为" +
                        getFileSize(maxSize) + "，上传文件大小为" + getFileSize(uploadSize);

                super.addFieldError("file", anErrorMessage);
            } else {
                super.addActionError(anErrorMessage);
            }
        } else {
            super.addActionError(anErrorMessage);
        }
    }

    private String getFileSize(long size) {
        double sizeKB = size / 1024;
        if (sizeKB < 1024) {
            return String.format("%.2f", sizeKB) + "KB";
        } else {
            double sizeMB = sizeKB / 1024;
            return String.format("%.2f", sizeMB) + "MB";
        }

    }
}
