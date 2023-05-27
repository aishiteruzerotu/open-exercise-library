package com.nf.vo;

import com.nf.entity.PaginationText;

import java.util.List;

public class PagedVO<T> {
    private PaginationText paginationText;
    private List<T> list;

    public PagedVO() {
    }

    public PagedVO(PaginationText paginationText, List<T> list) {
        this.paginationText = paginationText;
        this.list = list;
    }

    @Override
    public String toString() {
        return "PagedProductVO{" +
                "paginationText=" + paginationText +
                ", list=" + list +
                '}';
    }

    public PaginationText getPaginationText() {
        return paginationText;
    }

    public void setPaginationText(PaginationText paginationText) {
        this.paginationText = paginationText;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }
}
