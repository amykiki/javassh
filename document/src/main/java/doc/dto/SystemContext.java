package doc.dto;

/**
 * Created by Amysue on 2016/5/4.
 */
public class SystemContext {
    private static ThreadLocal<Integer> toPage    = new ThreadLocal<>();
    private static ThreadLocal<Integer> pageSize  = new ThreadLocal<>();
    private static ThreadLocal<Integer> pageRange = new ThreadLocal<>();

    public static Integer getToPage() {
        return toPage.get();
    }

    public static void setToPage(Integer _pageOffset) {
        toPage.set(_pageOffset);
    }

    public static void removeToPage() {
        toPage.remove();
    }

    public static Integer getPageSize() {
        return pageSize.get();
    }


    public static void setPageSize(Integer _pageSize) {
        pageSize.set(_pageSize);
    }

    public static void RemovePageSize() {
        pageSize.remove();
    }

    public static Integer getPageRange() {
        return pageRange.get();
    }

    public static void setPageRange(Integer _pageShow) {
        pageRange.set(_pageShow);
    }

    public static void removePageRange() {
        pageRange.remove();
    }
}
