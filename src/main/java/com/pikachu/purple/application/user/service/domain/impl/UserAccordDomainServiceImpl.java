package com.pikachu.purple.application.user.service.domain.impl;

import com.pikachu.purple.application.user.port.out.UserAccordRepository;
import com.pikachu.purple.application.user.service.domain.UserAccordDomainService;
import com.pikachu.purple.domain.user.UserAccord;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserAccordDomainServiceImpl implements UserAccordDomainService {

    private final UserAccordRepository userAccordRepository;

    @Override
    public void createAll(Long userId, List<UserAccord> userAccords) {
        userAccordRepository.createAll(userId, userAccords);
    }

    @Override
    public List<UserAccord> findAllOrderByScoreDesc(
        Long userId,
        int maxSize
    ) {
        return userAccordRepository.findAllOrderByScoreDesc(
            userId,
            maxSize
        );
    }

    @Override
    public List<UserAccord> findAllOrderByScoreAsc(
        Long userId,
        int maxSize
    ) {
        return userAccordRepository.findAllOrderByScoreAsc(
            userId,
            maxSize
        );
    }

}
