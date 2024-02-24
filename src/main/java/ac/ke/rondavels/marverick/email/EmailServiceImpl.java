package ac.ke.rondavels.marverick.email;



import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.thymeleaf.context.Context;


import java.io.File;

@Component
public class EmailServiceImpl implements EmailService {


    private final JavaMailSender emailSender;


    private final MailSenderConfig mailSenderConfig;

    @Autowired
    public EmailServiceImpl(JavaMailSender emailSender, MailSenderConfig mailSenderConfig
    ) {
        this.emailSender = emailSender;
        this.mailSenderConfig = mailSenderConfig;
    }


    public void sendSimpleMessage(
            String to, String subject, String text) {

        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("noreply@Maverick.com");
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);
        emailSender.send(message);
    }

    @Override
    public void sendMessageWithAttachment(
            String to, String subject, String text, String pathToAttachment) throws MessagingException {
        MimeMessage message = emailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        helper.setFrom("noreply@Maverick.com");
        helper.setTo(to);
        helper.setSubject(subject);
        helper.setText(text);

        FileSystemResource file
                = new FileSystemResource(new File(pathToAttachment));
        helper.addAttachment("/log/application.log", file);

        emailSender.send(message);
    }



    public void sendSimpleMessage(String to, String subject, String templateName, Context context) {
        MimeMessage message = emailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom("noreply@landG.com");
            helper.setTo(to);
            helper.setSubject(subject);
            String emailContent = mailSenderConfig.processTemplate(templateName, context);

            helper.setText(emailContent, true);

            emailSender.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }


}


