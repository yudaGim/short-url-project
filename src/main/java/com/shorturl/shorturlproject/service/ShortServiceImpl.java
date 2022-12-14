package com.shorturl.shorturlproject.service;

import com.shorturl.shorturlproject.domain.AccessLog;
import com.shorturl.shorturlproject.domain.Url;
import com.shorturl.shorturlproject.dto.AccessLogRequestDto;
import com.shorturl.shorturlproject.dto.UrlRequestDto;
import com.shorturl.shorturlproject.dto.UrlDetailResponseDto;
import com.shorturl.shorturlproject.dto.UrlResponseDto;
import com.shorturl.shorturlproject.exception.InvalidLoginInformationException;
import com.shorturl.shorturlproject.exception.UrlNotFoundException;
import com.shorturl.shorturlproject.repository.AccessLogRepository;
import com.shorturl.shorturlproject.repository.UrlRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class ShortServiceImpl implements ShortService{
    private final UrlRepository urlRepository;
    private final AccessLogRepository accessLogRepository;

    /**
     * Url 생성
     * : url 생성 시 url 내부 메소드 호출해 short url 생성
     *
     * @param: UrlRequestDto urlRequestDto
     * @return: String
     */
    @Override
    public String createUrl(UrlRequestDto urlRequestDto) {
        while (true) {
            urlRequestDto.createShortUrl();
            if (urlRepository.findById(urlRequestDto.getShortUrl()).orElse(null) == null) {
                break;
            }
        }
        Url url = urlRepository.save(urlRequestDto.toEntity());
        return url.getShortUrl();
    }

    /**
     * AccessLog 생성 및 Url 조회수 증가
     * : short url을 이용해 원본 url로 이동할 때 메소드 호출
     *   1. AccessLog 생성
     *   2. url 조회수 증가
     *   3. String 리턴
     *
     * @param: AccessLogRequestDto accessLogRequestDto
     * @return: String(Url의 destinationUrl을 이용해 redirect시키기 위해 destinationUrl 리턴)
     */
    @Override
    public String clickShortUrl(AccessLogRequestDto accessLogRequestDto) throws UrlNotFoundException {
        Url url = urlRepository.findById(accessLogRequestDto.getShortUrl()).orElseThrow(() -> new throw UrlNotFoundException());

        accessLogRequestDto.insertUrl(url);
        AccessLog accessLog = accessLogRepository.save(accessLogRequestDto.toEntity());

        url.plusTotalClick();

        return url.getDestinationUrl();
    }

    /**
     * id를 이용해 Url 가져오기
     * : short url 상세 정보를 조회하기 위해 메소드 호출
     *
     * @param: String shortUrl
     * @return: UrlResponseDto urlResponseDto
     */
    @Override
    public UrlDetailResponseDto detailUrl(String shortUrl) throws UrlNotFoundException {
        Url url = urlRepository.findById(shortUrl).orElseThrow(() -> new throw UrlNotFoundException());
        return new UrlDetailResponseDto(url);
    }

    /**
     * 인증
     * : DB에서 shortUrl과 password가 동일한 레코드를 검색
     *
     * @param: UrlRequestDto urlRequestDto
     * @return: UrlResponseDto urlResponseDto
     */
    @Override
    public UrlResponseDto authenticateUrl(UrlRequestDto urlRequestDto) throws InvalidLoginInformationException, UrlNotFoundException {
        Url url = urlRepository.findById(urlRequestDto.getShortUrl()).orElseThrow(() -> new throw UrlNotFoundException());
        if (!url.getPassword().equals(urlRequestDto.getPassword())) new throw InvalidLoginInformationException();
        return new UrlResponseDto(url);
    }
}
