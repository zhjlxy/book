package com.book.vo;

/**
 * Created by lixuy on 2016/4/18.
 */
public class PageResultVo {
    private boolean flag;

    private String msg;

    private int count;

    private int pageNum;

    private String data;

    public PageResultVo(boolean isArray){
        if(isArray){
            this.data = "[]";
        }else{
            this.data = "{}";
        }
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
