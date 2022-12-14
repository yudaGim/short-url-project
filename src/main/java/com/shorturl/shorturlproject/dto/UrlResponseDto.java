package com.shorturl.shorturlproject.dto;

import com.shorturl.shorturlproject.domain.AccessLog;
import com.shorturl.shorturlproject.domain.Url;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
public class UrlResponseDto {
    private String shortUrl;
    private String destinationUrl;
    private long totalClick;
    private LocalDateTime urlCreateDate;
    private LocalDateTime lastClickDate;

    public UrlResponseDto(Url entity) {
        this.shortUrl = entity.getShortUrl();
        this.destinationUrl = entity.getDestinationUrl();
        this.totalClick = entity.getTotalClick();
        this.urlCreateDate = entity.getUrlCreateDate();
        this.lastClickDate = entity.getLastClickDate();
    }
}
