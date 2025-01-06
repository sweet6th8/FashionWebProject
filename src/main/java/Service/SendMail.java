package Service;

import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;

import java.util.Properties;

public class SendMail {
    // Cấu hình thông tin SMTP
    private final String host = "smtp.gmail.com"; // SMTP server của Gmail
    private final String port = "587"; // Port mặc định của Gmail
    private final String senderEmail = "22130015@st.hcmuaf.edu.vn"; // Email gửi
    private final String senderPassword = "sqtw uubu kqtc lcje"; // Mật khẩu ứng dụng (app password)
    private  Properties properties ;
   private  Message message;
    public SendMail() throws MessagingException {
        // Config stmp
        Properties properties = new Properties();
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", port);
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.ssl.trust", "smtp.gmail.com"); // Hoặc máy chủ SMTP bạn sử dụng
        // Create session with email
        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(senderEmail, senderPassword);
            }
        });
         message = new MimeMessage(session);


    }
    public  Message sendMail (String email , String otp ) throws MessagingException {
        message.setFrom(new InternetAddress(senderEmail));
        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email));
        message.setSubject("Mã opt của bạn là : ");
        message.setText(otp);
        return message;
    }
}
