package net.engineeringdigest.journalApp.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class EmailServiceTest {

    @Autowired
    EmailService emailService;

    @Test
  void testSendMail(){
        emailService.sendEmail("viveknk777@gmail.com",
                               "Testing java mail sender ",
                               "I am able to send this mail using smtp.gmail.com");
    }
}
