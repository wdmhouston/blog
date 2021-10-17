package com.deming.blog.domain;

/**
 * Mlfruit class
 * Used for prediction history query
 */
public class Mlfruit {
    private Long id;

    private Long timestamp;

    private String image;

    private String imageUrl;

    private String result;

    private Float probability;

    private String content;

    private String ip;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public Float getProbability() {
        return probability;
    }

    public void setProbability(Float probability) {
        this.probability = probability;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", timestamp=").append(timestamp);
        sb.append(", image=").append(image);
        sb.append(", imageUrl=").append(imageUrl);
        sb.append(", result=").append(result);
        sb.append(", probability=").append(probability);
        sb.append(", content=").append(content);
        sb.append(", ip=").append(ip);
        sb.append("]");
        return sb.toString();
    }
}