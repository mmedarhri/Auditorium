package email;

import javax.mail.*;

/*@authors Mohamed MEDARHRI
 * 
 */

import javax.mail.internet.*;
import javax.activation.*;
import java.io.*;
import java.util.Properties;
public class MailClient
{


    public void sendMail(String mailServer, String from, String to,
                            String subject, String messageBody) throws
MessagingException, AddressException
    {
        // Setup mail server
        Properties props = System.getProperties();
        props.put("pop.gmail.com", mailServer);
        
        // Get a mail session
        Session session = Session.getDefaultInstance(props, null);
        
        // Define a new mail message
        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress(from));
        message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
        message.setSubject(subject);
        
        // Create a message part to represent the body text
        BodyPart messageBodyPart = new MimeBodyPart();
        messageBodyPart.setText(messageBody);
        
        //use a MimeMultipart as we need to handle the file attachments
        Multipart multipart = new MimeMultipart();
        
        //add the message body to the mime message
        multipart.addBodyPart(messageBodyPart);
        
        // add any file attachments to the message
     //   addAtachments(attachments, multipart);
        
        // Put all message parts in the message
        message.setContent(multipart);
        
        // Send the message
        Transport.send(message);


    }

    protected void addAtachments(String[] attachments, Multipart multipart)
                    throws MessagingException, AddressException
    {
        for(int i = 0; i<= attachments.length -1; i++)
        {
            String filename = attachments[i];
            MimeBodyPart attachmentBodyPart = new MimeBodyPart();
            
            //use a JAF FileDataSource as it does MIME type detection
            DataSource source = new FileDataSource(filename);
            attachmentBodyPart.setDataHandler(new DataHandler(source));
            
            //assume that the filename you want to send is the same as the
            //actual file name - could alter this to remove the file path
            attachmentBodyPart.setFileName(filename);
            
            //add the attachment
            multipart.addBodyPart(attachmentBodyPart);
        }
    }

    public static void main(String[] args)
    {
        try
        {
            MailClient client = new MailClient();
            String server="pop3.mydomain.com";
            String from="myname@mydomain.com";
            String to = "someuser@somewhere.com";
            String subject="Test";
            String message="Testing";
            String[] filenames = {"c:/somefile.txt"};
        
            client.sendMail(server,from,to,subject,message);
        }
        catch(Exception e)
        {
            e.printStackTrace(System.out);
        }
        
    }
} 