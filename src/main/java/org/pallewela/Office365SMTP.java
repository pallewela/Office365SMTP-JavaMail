package org.pallewela;

import javax.mail.*;
import javax.mail.internet.*;
import java.util.Properties;

public class Office365SMTP {
    private static final String USER_NAME = "<<user_email>>";
    private static final String PASSWORD = "<<password>>";
    private static final String FROM_EMAIL = "<<from_email>>";
    private static final String TO_EMAIL = "<<to_email>>";
    private static final Properties props = new Properties();
    static {
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.office365.com");
        props.put("mail.smtp.port", "587");
    }

    public static void main(String[] args) {
        Session session = Session.getInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(USER_NAME, PASSWORD);
            }
        });

        try {
            Message m = new MimeMessage(session);
            m.setFrom(new InternetAddress(FROM_EMAIL));
            m.setRecipient(Message.RecipientType.TO, new InternetAddress(TO_EMAIL));
            m.setSubject("Test Email Subject");
            m.setText("This is a test message");
            Transport.send(m);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
