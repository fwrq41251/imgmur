package com.winry.dto;

import java.text.MessageFormat;

/**
 * Created by cong on 2016/3/29.
 */
public class UploadResult {

    final String fullName;

    final String name;

    private String url;

    private String htmlCode;

    private String BBCode;

    private String markdown;

    public UploadResult(String name, String fullName) {
        super();
        this.name = name;
        this.fullName = fullName;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getHtmlCode() {
        return htmlCode;
    }

    public void setHtmlCode(String htmlCode) {
        this.htmlCode = htmlCode;
    }

    public String getBBCode() {
        return BBCode;
    }

    public void setBBCode(String bBCode) {
        BBCode = bBCode;
    }

    public String getMarkdown() {
        return markdown;
    }

    public void setMarkdown(String markdown) {
        this.markdown = markdown;
    }

    public static UploadResult build(String name, String fullName) {
        UploadResult result = new UploadResult(name, fullName);
        result.setUrl(result.fullName);
        result.setHtmlCode(MessageFormat.format("<img src=\"{0}\" alt=\"{1}\" title=\"{1}\" />", result.fullName, name));
        result.setBBCode(MessageFormat.format("[img]{0}[/img]", result.fullName));
        result.setMarkdown(MessageFormat.format("![{0}]({1})", name, result.fullName));
        return result;
    }
}
