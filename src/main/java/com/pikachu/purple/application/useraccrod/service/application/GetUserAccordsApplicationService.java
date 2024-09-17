package com.pikachu.purple.application.useraccrod.service.application;

import static com.pikachu.purple.support.security.SecurityProvider.getCurrentUserAuthentication;

import com.pikachu.purple.application.useraccrod.port.in.GetUserAccordsUseCase;
import com.pikachu.purple.application.useraccrod.service.domain.UserAccordDomainService;
import com.pikachu.purple.domain.user.UserAccord;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GetUserAccordsApplicationService implements GetUserAccordsUseCase {

    private final UserAccordDomainService userAccordDomainService;

    @Override
    public Result invoke() {
        Long userId = getCurrentUserAuthentication().userId();
        List<UserAccord> userAccords = userAccordDomainService.findAllByUserId(userId);

        return new Result(userAccords);
    }

}
