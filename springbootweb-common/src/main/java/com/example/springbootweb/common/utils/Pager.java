package com.example.springbootweb.common.utils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author william
 * @date 2020/10/28
 */
public class Pager<T> {
    /** 换数据类型时使用
     */
    public Pager(Pager<?> pager, List<?> datas) {
        this.currPage = pager.currPage;
        this.pages = pager.pages;
        this.pageSize = pager.pageSize;
        this.count = pager.count;
        this.ind = pager.ind;
        this.datas = (List<T>) datas;
    }

    public Pager(int currPage) {
        this.currPage = currPage < 1 ? 1 : currPage;
    }
    public Pager(int currPage, int pageSize) {
        this.currPage = currPage < 1 ? 1 : currPage;
        this.pageSize = pageSize;
    }


    private int currPage; //当前页     设置-100时，则不分页和获取页数(只在有需要的地方加此逻辑)
    private int pages; // 总页数
    private int pageSize = 10;//每页条数
    private int count; //总条数
    private int ind;
    private List<T> datas;
    private Object other;


    public Pager<T> setData(int count, List<T> datas) {
        this.setCount(count);
        this.datas=datas;
        return this;
    }

    /**
     * 不能确认每页条数时使用
     * @param pages
     * @param datas
     * @return
     */
    public Pager<T> setDataLikeCount(int pages, List<T> datas) {
        this.pages = pages;
        this.datas = datas;
        return this;
    }

    public List<T> getData() {
        return this.datas;
    }

    private void setCount(int count) {
        this.count = count;
        this.ind = ((currPage - 1) * pageSize) + 1;
        this.pages = count == 0 ? 1 : ((int) (count / pageSize + (count % pageSize == 0 ? 0 : 1)));
    }

    public int getCount() {
        return count;
    }

    /**
     * 获取每页条数
     * @return
     */
    public int getPageSize() {
        return this.pageSize;
    }

    public int getStartCount() {
        return (currPage - 1) * pageSize;
    }

    public Object getOther() {
        return other;
    }

    public void setOther(Object other) {
        this.other = other;
    }

    public String toJson() {
        return JsonUtil.bean2Json(this);
    }
    public static Pager<Object> getBlankDatas() {
        Pager<Object> pager = new Pager<Object>(1);
        pager.setData(0,new ArrayList<Object>(0));
        return pager;
    }
}
