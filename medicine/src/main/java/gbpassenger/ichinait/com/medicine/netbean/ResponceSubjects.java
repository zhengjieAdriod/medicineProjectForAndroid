package gbpassenger.ichinait.com.medicine.netbean;

import java.util.List;

/**
 * Created by DawnOct on 2018/1/30.
 */

public class ResponceSubjects {
    private String code;
    private int page_num;
    private int page_size;
    private int total_page;
    private int total_size;
    private List<Detail.SubjectBean> subject_list;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public int getPage_num() {
        return page_num;
    }

    public void setPage_num(int page_num) {
        this.page_num = page_num;
    }

    public int getPage_size() {
        return page_size;
    }

    public void setPage_size(int page_size) {
        this.page_size = page_size;
    }

    public int getTotal_page() {
        return total_page;
    }

    public void setTotal_page(int total_page) {
        this.total_page = total_page;
    }

    public List<Detail.SubjectBean> getSubject_list() {
        return subject_list;
    }

    public void setSubject_list(List<Detail.SubjectBean> subject_list) {
        this.subject_list = subject_list;
    }

    public int getTotal_size() {
        return total_size;
    }

    public void setTotal_size(int total_size) {
        this.total_size = total_size;
    }
}
