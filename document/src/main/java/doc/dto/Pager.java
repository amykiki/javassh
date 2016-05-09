package doc.dto;

import java.util.List;

/**
 * Created by Amysue on 2016/5/2.
 */
public class Pager<T> {
    private List<T> datas;
    private int     toPage;
    private int     pageSize;
    private int    totalRecords;
    private int     allPageNums;
    private int     begin;
    private int     end;

    public List<T> getDatas() {
        return datas;
    }

    public void setDatas(List<T> datas) {
        this.datas = datas;
    }

    public int getToPage() {
        return toPage;
    }

    public void setToPage(int toPage) {
        this.toPage = toPage;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getTotalRecords() {
        return totalRecords;
    }

    public void setTotalRecords(int totalRecords) {
        this.totalRecords = totalRecords;
    }

    public int getAllPageNums() {
        return allPageNums;
    }

    public void setAllPageNums(int allPageNums) {
        this.allPageNums = allPageNums;
    }

    public int getBegin() {
        return begin;
    }

    public void setBegin(int begin) {
        this.begin = begin;
    }

    public int getEnd() {
        return end;
    }

    public void setEnd(int end) {
        this.end = end;
    }
}
