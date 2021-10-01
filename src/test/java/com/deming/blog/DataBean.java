package com.deming.blog;

public class DataBean {
    private long id;
    private long parent;
    private String name;
    private int sort;

    @Override
    public String toString() {
        return "DataBean{" +
                "id=" + id +
                ", parent=" + parent +
                ", name='" + name + '\'' +
                ", sort=" + sort +
                '}';
    }
}
