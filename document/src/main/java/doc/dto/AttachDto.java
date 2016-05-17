package doc.dto;

import java.io.File;

/**
 * Created by Amysue on 2016/5/16.
 */
public class AttachDto {
    private File[]   atts;
    private String[] attsContentType;
    private String[] attsFileName;

    public AttachDto() {
    }

    public AttachDto(File[] atts, String[] attsContentType, String[] attsFileName) {
        this.atts = atts;
        this.attsContentType = attsContentType;
        this.attsFileName = attsFileName;
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
}
