package com.javacto.utils;

/**
 * describe  这一个代码以后开发100%不会让我们写，我们现在是在学思路
 *  以后也有分页，使用分页插件即可，
 */

public class PageInfo {

    //当前页，默认为第一页
    private  int pageNo=1;
    //每页查询几条数据，默认查询2条
    private int pageSize=2;
    //总共多少页  总页数应该是算出来的，是根据  总共多少条数据/每页查询几条数据 得到的
    private  int totalPageCount=0;
    //总共多少条数据   是不是在给总条数设置值的时候 算出  总共多少页
    private  int totalCount=0;


    public int getPageNo() {
        return pageNo;
    }

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getTotalPageCount() {
        return totalPageCount;
    }

    public void setTotalPageCount(int totalPageCount) {
        this.totalPageCount = totalPageCount;
    }

    public int getTotalCount() {
        return totalCount;
    }

    //这个是设置总条数的
    //总页数应该是算出来的，是根据  总共多少条数据/每页查询几条数据 得到的
    public void setTotalCount(int totalCount) {
        if(totalCount>0){
            this.totalCount = totalCount;
            //思路是这样的，思路是正确的，但代码有问题  6/2=3   5/2
            if(totalCount%pageSize==0){
                this.totalPageCount=totalCount/pageSize;
            }else {
                this.totalPageCount=totalCount/pageSize+1;
            }
        }

    }
}
