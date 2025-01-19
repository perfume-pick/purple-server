package com.pikachu.purple.application.review.service.domain.impl;

import com.pikachu.purple.application.review.port.out.ComplaintRepository;
import com.pikachu.purple.application.review.service.domain.ComplaintDomainService;
import com.pikachu.purple.application.review.util.TokenGenerator;
import com.pikachu.purple.application.util.IdUtil;
import com.pikachu.purple.domain.review.Complaint;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
class ComplaintDomainServiceImpl implements ComplaintDomainService {

    private final ComplaintRepository complaintRepository;

    @Override
    public Complaint create(
        Long userId,
        Long reviewId
    ) {
        Long complaintId = IdUtil.generateId();
        String token = TokenGenerator.generate();

        return complaintRepository.create(
            complaintId,
            userId,
            reviewId,
            token
        );
    }

    @Override
    public Complaint find(
        Long complaintId,
        String token
    ) {
        return complaintRepository.find(
            complaintId,
            token
        );
    }

    @Override
    public Complaint find(
        Long userId,
        Long reviewId
    ) {
        return complaintRepository.find(
            userId,
            reviewId
        );
    }

    @Override
    public void delete(Long complaintId) {
        complaintRepository.delete(complaintId);
    }

}
