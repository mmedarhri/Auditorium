package email;

import javax.mail.*;
import javax.mail.internet.*;

import java.util.Properties;


/*@authors Mohamed MEDARHRI
 * 
 */
class SimpleHTMLMail {
    public static void main(String[] args) throws Exception{
      Properties props = new Properties();
      props.setProperty("mail.transport.protocol", "smtp");
      props.setProperty("mail.host", "mymail.server.org");
      props.setProperty("mail.user", "emailuser");
      props.setProperty("mail.password", "");

      Session mailSession = Session.getDefaultInstance(props, null);
      Transport transport = mailSession.getTransport();

      MimeMessage message = new MimeMessage(mailSession);
      message.setSubject("Testing javamail html");
      message.setContent
         ("This is a test <b>HOWTO<b>", "text/html; charset=ISO-8859-1");
      message.addRecipient(Message.RecipientType.TO,
         new InternetAddress("elvis@presley.org"));

      transport.connect();
      transport.sendMessage(message,
         message.getRecipients(Message.RecipientType.TO));
      transport.close();
    }
}
