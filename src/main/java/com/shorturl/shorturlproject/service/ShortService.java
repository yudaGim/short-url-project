package com.shorturl.shorturlproject.service;

import com.shorturl.shorturlproject.domain.AccessLog;
import com.shorturl.shorturlproject.domain.Url;
import com.shorturl.shorturlproject.dto.AccessLogRequestDto;
import com.shorturl.shorturlproject.dto.UrlRequestDto;
import com.shorturl.shorturlproject.exception.UrlNotFoundException;

public interface ShortService {
    /**
     * Url 생성
     * : url 생성 시 url 내부 메소드 호출해 short url 생성
     *
     * @param: UrlRequestDto urlRequestDto
     * @return: String
     */
    String createUrl(UrlRequestDto urlRequestDto);

    /**
     * AccessLog 생성 및 Url 조회수 증가
     * : short url을 이용해 원본 url로 이동할 때 메소드 호출
     *   1. AccessLog 생성
     *   2. url 조회수 증가
     *   3. String 리턴
     *
     * @param: AccessLogRequestDto accessLogRequestDto
     * @return: String(Url의 destinationUrl을 이용해 redirect시키기 위해 destinationUrl 리턴)
     * */
    String clickShortUrl(AccessLogRequestDto accessLogRequestDto) throws UrlNotFoundException;

    /**
     * id를 이용해 Url 가져오기
     * : short url 상세 정보를 조회하기 위해 메소드 호출
     *
     * @param: Url url(shortUrl, password)
     * @return: Url url
     * */
    Url detailUrl(Url url);
}
