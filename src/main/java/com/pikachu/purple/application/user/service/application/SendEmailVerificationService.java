package com.pikachu.purple.application.user.service.application;


import com.pikachu.purple.application.user.port.in.SendEmailVerificationUseCase;
import com.pikachu.purple.application.user.port.out.MailSender;
import com.pikachu.purple.application.user.port.out.UserEmailVerificationRepository;
import com.pikachu.purple.application.user.port.out.UserRepository;
import com.pikachu.purple.application.user.vo.UserVerificationCode;
import com.pikachu.purple.bootstrap.common.exception.BusinessException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SendEmailVerificationService implements SendEmailVerificationUseCase {

    private final UserRepository userRepository;
    private final UserEmailVerificationRepository userEmailVerificationRepository;
    private final MailSender mailSender;

    private static final String EMAIL_REGEX = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
    private static final Long MAX_EXPIRATION_TIME = 600L;

    @Override
    public void invoke(String email){
        checkEmailForm(email);
        userRepository.validateNotExistedEmail(email);
        UserVerificationCode code = create();
        userEmailVerificationRepository.save(
            email,
            code.getCode(),
            MAX_EXPIRATION_TIME
        );
        mailSender.send(email, code.getCode());
    }

    private void checkEmailForm(String email){
        Pattern pattern = Pattern.compile(EMAIL_REGEX);
        Matcher matcher = pattern.matcher(email);

        if(!matcher.matches()){
            throw BusinessException.InvalidEmailException;
        }
    }

    private UserVerificationCode create(){
        return UserVerificationCode.createVerificationCode();
    }

}
