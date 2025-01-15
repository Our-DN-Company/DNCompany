package com.example.dncompany.service.sms;

import com.example.dncompany.exception.sms.SmsException;
import jakarta.annotation.PostConstruct;
import net.nurigo.sdk.NurigoApp;
import net.nurigo.sdk.message.model.Message;
import net.nurigo.sdk.message.request.SingleMessageSendingRequest;
import net.nurigo.sdk.message.service.DefaultMessageService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class SmsService {
    private DefaultMessageService messageService;
    @Value("${coolsms.api.key}")
    private String apiKey;
    @Value("${coolsms.api.secret}")
    private String apiSecret;
    @Value("${coolsms.sender.number}")
    private String senderNumber;

    @PostConstruct
    public void init() {
        messageService = NurigoApp
                .INSTANCE
                .initialize(apiKey, apiSecret, "https://api.coolsms.co.kr");
    }

    public String sendVerificationCode(String phoneNumber) {
        String verificationCode = generateVerificationCode();

        Message message = new Message();
        message.setFrom(senderNumber);
        message.setTo(phoneNumber);
        message.setText("[커뮤니티] 인증번호 : " + verificationCode);

        try {
            // 메세지 발송 처리
            this.messageService.sendOne(new SingleMessageSendingRequest(message));
        } catch (Exception e) {
            throw new SmsException("인증번호 전송 실패 : " + e);
        }

        // 사용자에게 발송한 인증코드를 반환
        return verificationCode;
    }
    private String generateVerificationCode() {
        Random random = new Random();
        int code = random.nextInt(1000000);

        // code를 6자리로 포매팅하고, 6자리보다 작은경우 0으로 채워서 포맷을 맞춰줌
        return String.format("%06d", code);
    }
}
