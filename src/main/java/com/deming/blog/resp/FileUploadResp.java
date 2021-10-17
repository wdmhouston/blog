package com.deming.blog.resp;

/**
 * File upload Response
 * @param <T>
 */
public class FileUploadResp<T> {

    //File name
    private String name;

    //Original Name
    private String nameOri;

    //Name url for access
    private String nameUrl;

    //Upload content
    private T content;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNameOri() {
        return nameOri;
    }

    public void setNameOri(String nameOri) {
        this.nameOri = nameOri;
    }

    public String getNameUrl() {
        return nameUrl;
    }

    public void setNameUrl(String nameUrl) {
        this.nameUrl = nameUrl;
    }

    public T getContent() {
        return content;
    }

    public void setContent(T content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "FileUploadResp{" +
                "name='" + name + '\'' +
                ", nameOri='" + nameOri + '\'' +
                ", nameUrl='" + nameUrl + '\'' +
                '}';
    }



}
