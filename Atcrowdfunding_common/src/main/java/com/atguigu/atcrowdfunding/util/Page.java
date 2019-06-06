package com.atguigu.atcrowdfunding.util;

import java.util.List;

public class Page {
    private Integer pageno;//当前页
    private Integer pagesize;//当前页条数
    private List datas;
    private Integer totalno;//总页数
    private Integer totalsize;//总条数

    public Page(Integer pageno, Integer pagesize) {
        this.pageno = pageno;
        this.pagesize = pagesize;
    }

    public Integer getPageno() {
        return pageno;
    }

    public void setPageno(Integer pageno) {
        this.pageno = pageno;
    }

    public Integer getPagesize() {
        return pagesize;
    }

    public void setPagesize(Integer pagesize) {
        this.pagesize = pagesize;
    }

    public List getDatas() {
        return datas;
    }

    public void setDatas(List datas) {
        this.datas = datas;
    }

    public Integer getTotalno() {
        return totalno;
    }

    private void setTotalno(Integer totalno) {
        this.totalno = totalno;
    }

    public Integer getTotalsize() {
        return totalsize;
    }

    public void setTotalsize(Integer totalsize) {
        this.totalsize = totalsize;
        this.totalno = (totalsize%pagesize)==0?(totalsize/pagesize):(totalsize/pagesize+1);
    }

    public Integer getStartIndex(){
        return (this.pageno-1)*pagesize;
    }


}
