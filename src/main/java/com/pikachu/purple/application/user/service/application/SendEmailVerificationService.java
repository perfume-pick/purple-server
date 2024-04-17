package com.pikachu.purple.application.user.service.application;


import com.pikachu.purple.application.user.port.in.SendEmailVerification;
import com.pikachu.purple.application.user.port.out.MailSender;
import com.pikachu.purple.application.user.port.out.UserEmailVerificationRepository;
import com.pikachu.purple.application.user.port.out.UserRepository;
import com.pikachu.purple.bootstrap.common.exception.BusinessException;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class SendEmailVerificationService implements SendEmailVerification {
    private final UserRepository userRepository;
    private final UserEmailVerificationRepository userEmailVerificationRepository;
    private final MailSender mailSender;

    private static final int leftLimit = 48;
    private static final int rightLimit = 122;
    private static final int targetStringLength = 6;
    private static final String EMAIL_REGEX = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";

    @Override
    public void invoke(String email){
        checkEmailForm(email);
        userRepository.validateNoExistedEmail(email);
        String randomString = createVerificationNumber();
        userEmailVerificationRepository.save(email, randomString, null);
        mailSender.send(email, randomString);
    }

    private String createVerificationNumber(){
        Random random = new Random();

        return random.ints(leftLimit, rightLimit + 1)
            .filter(i -> (i <=57 || i >=65) && (i <= 90 || i>= 97))
            .limit(targetStringLength)
            .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
            .toString();
    }

    private void checkEmailForm(String email){
        Pattern pattern = Pattern.compile(EMAIL_REGEX);

        Matcher matcher = pattern.matcher(email);
        if(!matcher.matches()){
            throw BusinessException.InvalidEmailException;
        }
    }

}
