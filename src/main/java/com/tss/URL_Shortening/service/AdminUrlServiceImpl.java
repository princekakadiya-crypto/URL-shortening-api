package com.tss.URL_Shortening.service;

import com.tss.URL_Shortening.dto.PageDto;
import com.tss.URL_Shortening.dto.url.ShortUrlResponseDto;
import com.tss.URL_Shortening.entity.ShortUrl;
import com.tss.URL_Shortening.exception.InvalidOperationException;
import com.tss.URL_Shortening.exception.ResourceNotFoundException;
import com.tss.URL_Shortening.mapper.ShortUrlMapper;
import com.tss.URL_Shortening.repository.ShortUrlRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class AdminUrlServiceImpl implements AdminUrlService{

<<<<<<< HEAD
//
//        private final ShortUrlMapper shortUrlMapper;
//        private final ShortUrlRepository shortUrlRepository;
//
//        @Override
//        public PageDto<ShortUrlResponseDto> getAllUrls(Pageable pageable) {
//
//            Page<ShortUrl> shortUrls=shortUrlRepository.findAllByDeletedFalse(pageable);
//
//            List<ShortUrlResponseDto> responseDtos=new ArrayList<>();
//
//            for (ShortUrl shortUrl:shortUrls){
//                ShortUrlResponseDto dto= shortUrlMapper.toDto(shortUrl);
//                responseDtos.add(dto);
//            }
//
//            PageDto<ShortUrlResponseDto> pageDto = new PageDto<>();
//
//            pageDto.setContent(responseDtos);
//            pageDto.setCurrentPage(shortUrls.getNumber());
//            pageDto.setPageSize(shortUrls.getSize());
//            pageDto.setTotalPages(shortUrls.getTotalPages());
//            pageDto.setTotalElements(shortUrls.getTotalElements());
//            pageDto.setFirst(shortUrls.isFirst());
//            pageDto.setLast(shortUrls.isLast());
//            pageDto.setEmpty(shortUrls.isEmpty());
//
//            return pageDto;
//        }
//
//        @Override
//        @Transactional
//        public ShortUrlResponseDto getUrlById(Long id) {
//
//            ShortUrl shortUrl = shortUrlRepository.findById(id)
//                    .orElseThrow(() -> new ResourceNotFoundException("URL not found"));
//
//            return shortUrlMapper.toDto(shortUrl);
//        }
//
//        @Override
//        @Transactional
//        public void deleteUrl(Long id) {
//
//            ShortUrl shortUrl = shortUrlRepository.findById(id)
//                    .orElseThrow(() -> new ResourceNotFoundException("URL not found"));
//
//            if (shortUrl.isDeleted()) {
//                throw new InvalidOperationException("URL is already deleted");
//            }
//
//            shortUrl.setDeleted(true);
//            shortUrlRepository.save(shortUrl);
//        }
//
//        @Override
//        @Transactional
//        public ShortUrlResponseDto restoreUrl(Long id) {
//
//            ShortUrl shortUrl = shortUrlRepository.findById(id)
//                    .orElseThrow(() -> new ResourceNotFoundException("URL not found"));
//
//            if (!shortUrl.isDeleted()) {
//                throw new InvalidOperationException("URL is not deleted");
//            }
//
//            shortUrl.setDeleted(false);
//
//            ShortUrl savedUrl = shortUrlRepository.save(shortUrl);
//            return shortUrlMapper.toDto(savedUrl);
//        }
=======
    private final ShortUrlMapper shortUrlMapper;
    private final ShortUrlRepository shortUrlRepository;

    @Override
    public PageDto<ShortUrlResponseDto> getAllUrls(Pageable pageable) {

        Page<ShortUrl> shortUrls=shortUrlRepository.findAllByDeletedFalse(pageable);

        List<ShortUrlResponseDto> responseDtos=new ArrayList<>();

        for (ShortUrl shortUrl:shortUrls){
            ShortUrlResponseDto dto= shortUrlMapper.toDto(shortUrl);
            responseDtos.add(dto);
        }

        PageDto<ShortUrlResponseDto> pageDto = new PageDto<>();

        pageDto.setContent(responseDtos);
        pageDto.setCurrentPage(shortUrls.getNumber());
        pageDto.setPageSize(shortUrls.getSize());
        pageDto.setTotalPages(shortUrls.getTotalPages());
        pageDto.setTotalElements(shortUrls.getTotalElements());
        pageDto.setFirst(shortUrls.isFirst());
        pageDto.setLast(shortUrls.isLast());
        pageDto.setEmpty(shortUrls.isEmpty());

        return pageDto;
    }

    @Override
    @Transactional
    public ShortUrlResponseDto getUrlById(Long id) {

        ShortUrl shortUrl = shortUrlRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("URL not found"));

        return shortUrlMapper.toDto(shortUrl);
    }

    @Override
    @Transactional
    public void deleteUrl(Long id) {

        ShortUrl shortUrl = shortUrlRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("URL not found"));

        if (shortUrl.isDeleted()) {
            throw new InvalidOperationException("URL is already deleted");
        }

        shortUrl.setDeleted(true);
        shortUrlRepository.save(shortUrl);
    }

    @Override
    @Transactional
    public ShortUrlResponseDto restoreUrl(Long id) {

        ShortUrl shortUrl = shortUrlRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("URL not found"));

        if (!shortUrl.isDeleted()) {
            throw new InvalidOperationException("URL is not deleted");
        }
>>>>>>> 43537fd34b1afd1b97f8283bbf7f8be82e9b5505

        shortUrl.setDeleted(false);

        ShortUrl savedUrl = shortUrlRepository.save(shortUrl);
        return shortUrlMapper.toDto(savedUrl);
    }
}
