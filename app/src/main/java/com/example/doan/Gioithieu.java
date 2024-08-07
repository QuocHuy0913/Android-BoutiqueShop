package com.example.doan;

public class Gioithieu {
    private int resourceId;
    private String header;
    private String content;

    public Gioithieu(int resourceId, String header, String content) {
        this.resourceId = resourceId;
        this.header = header;
        this.content = content;
    }

    public int getResourceId() {
        return resourceId;
    }

    public void setResourceId(int resourceId) {
        this.resourceId = resourceId;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
