package com.korea.blog.global.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@Setter
@Getter
@Component
@Scope("request")
public class UrlParamManager {
    private String keyword;
    private String isModalOpen;
    private String sortTarget;

    public UrlParamManager() {
        keyword = "";
        isModalOpen = "false";
        sortTarget = "id";
    }

    public String getQueryParam() {
        return "keyword=" + URLEncoder.encode(keyword, StandardCharsets.UTF_8) + "&isModalOpen=" + isModalOpen + "&sortTarget=" + sortTarget;
    }

    public String getBookUrl(long bookId) {
        isModalOpen = "false";
        return getParamUrl("/books/%d".formatted(bookId));
    }

    public String getNoteUrl(long bookId, long noteId) {
        isModalOpen = "false";
        return getParamUrl("/books/%d/notes/%d".formatted(bookId, noteId));
    }

    public String getParamUrl(String url) {
        return url + "?" + getQueryParam();
    }

    public String getRedirectUrl(String url) {
        return "redirect:" + getParamUrl(url);
    }
}