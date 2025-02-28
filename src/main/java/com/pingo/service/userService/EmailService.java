package com.pingo.service.userService;

import com.pingo.dto.ResponseDTO;
import com.pingo.exception.BusinessException;
import com.pingo.exception.ExceptionCode;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.MailException;
import org.springframework.stereotype.Service;
import jakarta.mail.internet.MimeMessage;
import jakarta.mail.MessagingException;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import java.util.UUID;


@Service
@RequiredArgsConstructor
public class EmailService {

    private final JavaMailSender javaMailSender;
    private static final String senderEmail = "pingo250120@gmail.com";

    // 1. 인증코드 생성
    public String createVerificationCode() {
        String uuid = UUID.randomUUID().toString();
        return uuid.substring(0, 8);
    }
    
    // 2. 인증코드 이메일 생성
    public MimeMessage createVerificationEmail(String userEmail, String code) throws MessagingException {
        MimeMessage message = javaMailSender.createMimeMessage();

        message.setFrom(senderEmail);
        message.setRecipients(MimeMessage.RecipientType.TO, userEmail);
        message.setSubject("이메일 인증코드", "UTF-8");

        String body = "";
        body += "<h1>인증코드 : " + code + "</h1>";
        body += "<h3>안녕하세요!</h3>";
        body += "<h3>Pingo 이메일 확인 메일입니다.</h3>";
        body += "<h2>※중요: 인증코드는 10분후에 만료됩니다. 10분 내로 입력하여 주시기 바랍니다.</h2>";
        message.setText(body, "UTF-8", "html");

        return message;
    }
    
    // 3. 인증코드 이메일 발송
    public ResponseEntity<?>  sendVerificationEmail(String sendEmail, HttpSession session) throws MessagingException {
        String code = createVerificationCode(); // 랜덤 인증코드 생성

        MimeMessage message = createVerificationEmail(sendEmail, code); // 메일 생성
        try {
            javaMailSender.send(message); // 메일 발송

            // 4. 이메일이 보내지면 세션에 인증코드 저장 (10분 후 만료)
            session.setAttribute(sendEmail, code);
            session.setMaxInactiveInterval(600);
        } catch (MailException e) {
            throw new BusinessException(ExceptionCode.EMAIL_SEND_FAILED);
        }

        return ResponseEntity.ok().body(ResponseDTO.of("1","성공",true));
    }


    // ----------------------------------------------------------------

    // 이메일 인증번호 확인 메서드

}
