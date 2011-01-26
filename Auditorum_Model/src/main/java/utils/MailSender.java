package utils;


import java.io.IOException;
import java.io.StringReader;
import java.io.UnsupportedEncodingException;
import java.security.Security;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Authenticator;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.html.HTMLEditorKit;


public class MailSender {
    
    final private static String CHARSET = "charset=ISO-8859-1";
    
    final private static String SSL_FACTORY = "javax.net.ssl.SSLSocketFactory";
    
    final private static int DEFAULT_SMTP_PORT = 25;
    
    final private Session _sessionmail;

    
    
    // Constructeur n�1: Connexion au serveur mail
    public MailSender(final String host, final int port, final String userName,
            final String password, final boolean ssl) {
        final String strPort = String.valueOf(port);
            final Properties props = new Properties();
            props.put("mail.smtp.host", host);
            props.put("mail.smtp.port", strPort);
            if (ssl) {
                // Connection SSL
                Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());
                    props.put("mail.smtp.socketFactory.class", SSL_FACTORY);
                    props.put("mail.smtp.socketFactory.fallback", "false");
                    props.put("mail.smtp.auth", "true");
                    props.put("mail.smtp.socketFactory.port", strPort);
            }
            if (null == userName || null == password) {
                _sessionmail = Session.getDefaultInstance(props, null);
            } else {
                // Connexion avec authentification
                _sessionmail = Session.getDefaultInstance(props, new Authenticator(){
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(userName, password);
                }
                });
            }
        }

  

    
    // Autres constructeurs
    public MailSender(final String host, final String userName,
            final String password, final boolean ssl) {
        this(host, DEFAULT_SMTP_PORT, userName, password, ssl);
    }
    public MailSender(final String host, final String userName,
            final String password) {
        this(host, DEFAULT_SMTP_PORT, userName, password, false);
    }
    public MailSender(final String host, final int port) {
        this(host, port, null, null, false);
    }
    public MailSender(final String host) {
        this(host, DEFAULT_SMTP_PORT, null, null, false);
    }
    
    // Convertit un texte au format html en texte brut
    private static final String HtmlToText(final String s) {
        final HTMLEditorKit kit = new HTMLEditorKit();
        final Document doc = kit.createDefaultDocument();
        try {
            kit.read(new StringReader(s), doc, 0);
            return doc.getText(0, doc.getLength()).trim();
        } catch (final IOException ioe) {
            return s;
        } catch (final BadLocationException ble) {
            return s;
        }
    }
    
    // D�fini les fichiers � joindre
    private void setAttachmentPart(final String[] attachmentPaths,
            final MimeMultipart related, final MimeMultipart attachment,
            final String body, final boolean htmlText)
    throws MessagingException {
        for (int i = 0; i < attachmentPaths.length; ++i) {
            // Cr�ation du fichier � inclure
            final MimeBodyPart messageFilePart = new MimeBodyPart();
            final DataSource source = new FileDataSource(attachmentPaths[i]);
            final String fileName = source.getName();
            messageFilePart.setDataHandler(new DataHandler(source));
            messageFilePart.setFileName(fileName);
            // Image � inclure dans un texte au format HTML ou pi�ce jointe
            if (htmlText && null != body && body.matches(
                    ".*<img[^>]*src=[\"|']?cid:([\"|']?" + fileName + "[\"|']?)[^>]*>.*")) {
                // " <-- pour �viter une coloration syntaxique d�sastreuse...
                messageFilePart.setDisposition("inline");
                messageFilePart.setHeader("Content-ID", '<' + fileName + '>');
                related.addBodyPart(messageFilePart);
            } else {
                messageFilePart.setDisposition("attachment");
                attachment.addBodyPart(messageFilePart);
            }
        }
    }
    
    // Texte alternatif = texte + texte html
    private void setHtmlText(final MimeMultipart related,
            final MimeMultipart alternative, final String body)
    throws MessagingException {
        // Plain text
        final BodyPart plainText = new MimeBodyPart();
        plainText.setContent(HtmlToText(body), "text/plain; " + CHARSET);
        alternative.addBodyPart(plainText);
        // Html text ou Html text + images incluses
        final BodyPart htmlText = new MimeBodyPart();
        htmlText.setContent(body, "text/html; " + CHARSET);
        if (0 != related.getCount()) {
            related.addBodyPart(htmlText, 0);
            final MimeBodyPart tmp = new MimeBodyPart();
            tmp.setContent(related);
            alternative.addBodyPart(tmp);
        } else {
            alternative.addBodyPart(htmlText);
        }
    }
    
    // Definition du corps de l'e-mail
    private void setContent(final Message message, 
            final MimeMultipart alternative, final MimeMultipart attachment,
            final String body)
    throws MessagingException {
        if (0 != attachment.getCount()) {
            // Contenu mixte: Pi�ces jointes +  texte
              if (0 != alternative.getCount() && null != body) {
                // Texte alternatif = texte + texte html
                final MimeBodyPart tmp = new MimeBodyPart();
                tmp.setContent(alternative);
                attachment.addBodyPart(tmp, 0);
            } else {
                // Juste du texte
                final BodyPart plainText = new MimeBodyPart();
                plainText.setContent(body, "text/plain; " + CHARSET);
                attachment.addBodyPart(plainText, 0);
            }
            message.setContent(attachment);
        } else {
            // Juste un message texte
            if (0 != alternative.getCount()) {
                // Texte alternatif = texte + texte html
                message.setContent(alternative);
            }else {
                // Texte
                message.setText(body);
            }
        }
    }
    
    // Prototype n�1: Envoi de message avec pi�ce jointe
    public void sendMessage(final MailMessage mailMsg)
    throws MessagingException {
        final Message message = new MimeMessage(_sessionmail);
        // Subect
        message.setSubject(mailMsg.getSubject());
        // Exp�diteur
        message.setFrom(mailMsg.getFrom());
        // Destinataires
        message.setRecipients(Message.RecipientType.TO, mailMsg.getTo());
        message.setRecipients(Message.RecipientType.CC, mailMsg.getCc());
        message.setRecipients(Message.RecipientType.BCC, mailMsg.getBcc());
        // Contenu + pi�ces jointes + images
        final MimeMultipart related = new MimeMultipart("related");
        final MimeMultipart attachment = new MimeMultipart("mixed");
        final MimeMultipart alternative = new MimeMultipart("alternative");
        final String[] attachments = mailMsg.getAttachmentURL();
        final String body = (String) mailMsg.getContent();
        final boolean html = mailMsg.isHtml();
        if (null != attachments)
            setAttachmentPart(attachments, related, attachment, body, html);
        if (html && null != body)
            setHtmlText(related, alternative, body);
        setContent(message, alternative, attachment, body);       
        // Date d'envoi
        message.setSentDate(mailMsg.getSendDate());
        // Envoi
        Transport.send(message);
        // R�initialise le message
        mailMsg.reset();
    }
}
