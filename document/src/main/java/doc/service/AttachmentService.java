package doc.service;

import doc.dao.IAttachmentDao;
import doc.dto.AttachDto;
import doc.entity.Attachment;
import doc.util.ActionUtil;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.util.*;

/**
 * Created by Amysue on 2016/5/13.
 */
@Service("attachService")
public class AttachmentService implements IAttachmentService {
    private IAttachmentDao attachDao;

    @Resource(name = "attachDao")
    public void setAttachDao(IAttachmentDao attachDao) {
        this.attachDao = attachDao;
    }

    @Override
    public Set<Attachment> add(AttachDto attachDto) {
        File[]          atts            = attachDto.getAtts();
        String[]        attsFileName    = attachDto.getAttsFileName();
        String[]        attsContentType = attachDto.getAttsContentType();
        Set<Attachment> attsSet        = new LinkedHashSet<>();
        for (int i = 0; i < atts.length; i++) {
            Attachment attachment = new Attachment();
            String oldName = attsFileName[i];
            attachment.setOldName(oldName);
            attachment.setNewName(getNewName(oldName));
            attachment.setContentType(attsContentType[i]);
            attachment.setType(FilenameUtils.getExtension(oldName));
            attachment.setCreateDate(new Date());
            attachment.setSize(atts[i].length());
            attachDao.add(attachment);
            attsSet.add(attachment);
        }
//        上传文件
        Attachment[] attsArr = new Attachment[attsSet.size()];
        attsSet.toArray(attsArr);
        for (int i = 0; i < atts.length; i++) {
            File fn = atts[i];
            String uploadPath = ActionUtil.getAttachPath() + "/" + attsArr[i].getNewName();
            try {
                FileUtils.copyFile(fn, new File(uploadPath));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return attsSet;
    }

    private String getNewName(String oldName) {
        String extention = FilenameUtils.getExtension(oldName);
        String newName = FilenameUtils.getBaseName(oldName);
        return newName + UUID.randomUUID().toString() + "." + extention;
    }

    @Override
    public void deleteAttachments(List<Integer> aIds) {
        attachDao.deleteAttachments(aIds);
    }
}
