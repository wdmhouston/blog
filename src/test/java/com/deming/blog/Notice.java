package com.deming.blog;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.assertj.core.util.Lists;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Notice{
    private boolean success;
    private String message;
    private List<DataBean> content = Lists.newArrayList();

    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder();

        for (DataBean obj : content) {
            sb.append(obj.toString());
            sb.append("\t");
        }

        return "Notice{" +
                "success=" + success +
                ", message='" + message + '\'' +
                ", content=" + sb.toString() +
                '}';
    }
}
