package email;

import javax.mail.*;
import javax.mail.internet.*;
import javax.activation.FileDataSource;
import javax.activation.DataHandler;

import java.util.Properties;

class SimpleMailWithAttachment {
    public static void main(String[] args) throws Exception{
      boolean debug = false;
      Properties props = new Properties();
      props.setProperty("mail.transport.protocol", "smtp");
      props.setProperty("mail.host", "mymail.server.org");
      props.setProperty("mail.user", "emailuser");
      props.setProperty("mail.password", "");

      Session mailSession = Session.getDefaultInstance(props, null);
      mailSession.setDebug(debug);
      Transport transport = mailSession.getTransport();

      MimeMessage message = new MimeMessage(mailSession);
      message.setSubject("Testing javamail with attachment");

      MimeBodyPart textPart = new MimeBodyPart();
      textPart.setContent("<h1>Check attachment</h1>", "text/html");

      MimeBodyPart attachFilePart = new MimeBodyPart();
      FileDataSource fds = 
          new FileDataSource("SimpleMailWithAttachment.java");
      attachFilePart.setDataHandler(new DataHandler(fds));
      attachFilePart.setFileName(fds.getName());

      Multipart mp = new MimeMultipart();
      mp.addBodyPart(textPart);
      mp.addBodyPart(attachFilePart);

      message.setContent(mp);
      message.addRecipient(Message.RecipientType.TO,
          new InternetAddress("elvis@presley.org"));

      transport.connect();
      transport.sendMessage(message,
          message.getRecipients(Message.RecipientType.TO));
      transport.close();
    }
}
