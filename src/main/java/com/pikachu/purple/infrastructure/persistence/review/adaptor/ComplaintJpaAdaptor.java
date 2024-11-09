package com.pikachu.purple.infrastructure.persistence.review.adaptor;

import static com.pikachu.purple.bootstrap.common.exception.BusinessException.ComplaintNotFoundException;
import static com.pikachu.purple.bootstrap.common.exception.BusinessException.ReviewNotFoundException;
import static com.pikachu.purple.bootstrap.common.exception.BusinessException.UserNotFoundException;

import com.pikachu.purple.application.review.port.out.ComplaintRepository;
import com.pikachu.purple.domain.review.Complaint;
import com.pikachu.purple.infrastructure.persistence.review.entity.ComplaintJpaEntity;
import com.pikachu.purple.infrastructure.persistence.review.entity.ReviewJpaEntity;
import com.pikachu.purple.infrastructure.persistence.review.repository.ComplaintJpaRepository;
import com.pikachu.purple.infrastructure.persistence.review.repository.ReviewJpaRepository;
import com.pikachu.purple.infrastructure.persistence.user.entity.UserJpaEntity;
import com.pikachu.purple.infrastructure.persistence.user.repository.UserJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ComplaintJpaAdaptor implements ComplaintRepository {

    private final ComplaintJpaRepository complaintJpaRepository;
    private final UserJpaRepository userJpaRepository;
    private final ReviewJpaRepository reviewJpaRepository;

    @Override
    public Complaint create(
        Long complaintId,
        Long userId,
        Long reviewId,
        String token
    ) {
        UserJpaEntity userJpaEntity = userJpaRepository.findById(userId)
            .orElseThrow(() -> UserNotFoundException);

        ReviewJpaEntity reviewJpaEntity = reviewJpaRepository.findById(reviewId)
            .orElseThrow(() -> ReviewNotFoundException);

        ComplaintJpaEntity complaintJpaEntity = ComplaintJpaEntity.builder()
            .id(complaintId)
            .userJpaEntity(userJpaEntity)
            .reviewJpaEntity(reviewJpaEntity)
            .token(token)
            .build();

        ComplaintJpaEntity saveComplaintJpaEntity = complaintJpaRepository.save(complaintJpaEntity);

        return ComplaintJpaEntity.toDomain(saveComplaintJpaEntity);
    }

    @Override
    public Complaint find(
        Long complaintId,
        String token
    ) {
        ComplaintJpaEntity complaintJpaEntity = complaintJpaRepository.findByIdAndToken(
            complaintId,
            token
        ).orElseThrow(() -> ComplaintNotFoundException);

        return ComplaintJpaEntity.toDomain(complaintJpaEntity);
    }

    @Override
    public void find(
        Long userId,
        Long complaintId
    ) {
        complaintJpaRepository.findByIdAndUserId(
            complaintId,
            userId
        ).ifPresentOrElse(
            complaint -> {},
            () -> {throw ComplaintNotFoundException;}
        );
    }

    @Override
    public void delete(Long complaintId) {
        complaintJpaRepository.deleteById(complaintId);
    }

}
