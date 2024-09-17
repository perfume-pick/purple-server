package com.pikachu.purple.application.useraccrod.service.domain.impl;

import com.pikachu.purple.application.useraccrod.port.out.UserAccordRepository;
import com.pikachu.purple.application.useraccrod.service.domain.UserAccordDomainService;
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
    public List<UserAccord> findAllByUserId(Long userId) {
        return userAccordRepository.findAllByUserId(userId);
    }

}
