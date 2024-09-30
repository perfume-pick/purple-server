package com.pikachu.purple.application.review.service.domain.impl;

import com.pikachu.purple.application.review.port.out.ComplaintRepository;
import com.pikachu.purple.application.review.service.domain.ComplaintDomainService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ComplaintDomainServiceImpl implements ComplaintDomainService {

    private final ComplaintRepository complaintRepository;

    @Override
    public void create(
        Long userId,
        Long reviewId
    ) {
        complaintRepository.create(
            userId,
            reviewId
        )
    }

}
