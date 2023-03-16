package com.example.dogshelter.service;

import com.example.dogshelter.domain.Mail;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessagePreparator;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class SimpleEmailServiceTest {

    @InjectMocks
    private SimpleEmailService simpleEmailService;
    @Mock
    private JavaMailSender javaMailSender;


    @Test
    public void shouldSendEmail()  {
        //Given
        Mail mail = new Mail.MailBuilder()
                .mailTo("test@test.com")
                .subject("Test Message")
                .message("Text")
                .build();
        //When
        simpleEmailService.send(mail);
        //Then
        verify(javaMailSender).send(ArgumentMatchers.any(MimeMessagePreparator.class));
        verify(javaMailSender, times(1)).send(ArgumentMatchers.any(MimeMessagePreparator.class));
    }

    @Test
    public void shouldSendScheduledEmail() {
        //Given
        Mail mail = new Mail.MailBuilder()
                .mailTo("test@test.com")
                .subject("Test Message")
                .message("Text")
                .build();
        //When
        simpleEmailService.sendScheduledEmailWithWeather(mail);
        //Then
        verify(javaMailSender).send(ArgumentMatchers.any(MimeMessagePreparator.class));
        verify(javaMailSender, times(1)).send(ArgumentMatchers.any(MimeMessagePreparator.class));
    }
}