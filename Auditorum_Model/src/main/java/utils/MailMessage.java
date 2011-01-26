package utils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Date;

import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

import javax.mail.*;
import javax.mail.internet.*;
import java.util.*; 


public final class MailMessage {

    private String _subject = "";
    
    private Object _content = "";
    
    private boolean _html = false;
    
    private InternetAddress _from = null;
    
    private InternetAddress[] _to = null;
    
    private InternetAddress[] _cc = null;
    
    private InternetAddress[] _bcc = null;
    
    private String[] _attachmentURL = null;
    
    private Date _sendDate = new Date();
    
    private Date _receivedDate = null;
    
    private InternetAddress[] getAddress(final InternetAddress address) {
        return new InternetAddress[] { address };
    }
    private InternetAddress[] getAddress(final String[] address)
    throws AddressException {
        final InternetAddress[] result = new InternetAddress[address.length];
        for (int i = 0; i < address.length; ++i)
            result[i] = new InternetAddress(address[i]);
        return result;
    }
    private InternetAddress[] getAddress(final String address)
    throws AddressException {
        return new InternetAddress[] { new InternetAddress(address) };
    }
    
    private InternetAddress[] getInternetAddress(final Address[] address)
    throws AddressException, UnsupportedEncodingException {
        if (null == address)
            return null;
        final InternetAddress[] result = new InternetAddress[address.length];
        for (int i = 0; i < address.length; ++i)
            result[i] = new InternetAddress(decodeText(address[i].toString()));
        return result;
    }
    
    public MailMessage() {
    }
    
    public MailMessage(final Message msg)
    throws IOException, MessagingException {
        _from = getInternetAddress(msg.getFrom())[0];
        _to = getInternetAddress(msg.getRecipients(Message.RecipientType.TO));
        _cc = getInternetAddress(msg.getRecipients(Message.RecipientType.CC));
        _subject = msg.getSubject();
        _content = msg.getContent();
        _sendDate = msg.getSentDate();
        _receivedDate = msg.getReceivedDate();
    }
    
    private static String decodeText(final String text)
    throws UnsupportedEncodingException {
        return null == text ? text : new String(text.getBytes("iso-8859-1"));
    }
    
    public void reset() {
        _subject = "";
        _content = "";
        _html = false;
        _from = null;
        _to = null;
        _cc = null;
        _bcc = null;
        _attachmentURL = null;
    }
    
    // Getters
    public InternetAddress getFrom() {
        return _from;
    }
    public InternetAddress[] getTo() {
        return _to;
    }
    public InternetAddress[] getCc() {
        return _cc;
    }
    public InternetAddress[] getBcc() {
        return _bcc;
    }
    public Object getContent() {
        return _content;
    }
    public String getSubject() {
        return _subject;
    }
    public String[] getAttachmentURL() {
        return _attachmentURL;
    }
    public boolean isHtml() {
        return _html;
    }
    public Date getSendDate() {
        return _sendDate;
    }
    
    // Setters
    public void setFrom(final InternetAddress from) {
        _from = from;
    }
    public void setFrom(final String from)
    throws AddressException {
        _from = new InternetAddress(from);
    }
    public void setTo(final InternetAddress[] to) {
        _to = to;
    }
    public void setTo(final InternetAddress to) {
        _to = getAddress(to);
    }
    public void setTo(final String[] to)
    throws AddressException {
        _to = getAddress(to);
    }
    public void setTo(final String to)
    throws AddressException {
        _to = getAddress(to);
    }
    public void setCc(final InternetAddress[] cc) {
        _cc = cc;
    }
    public void setCc(final InternetAddress cc) {
        _cc = getAddress(cc);
    }
    public void setCc(final String[] cc)
    throws AddressException {
        _cc = getAddress(cc);
    }
    public void setCc(final String cc)
    throws AddressException {
        _cc = getAddress(cc);
    }
    public void setBcc(final InternetAddress[] bcc) {
        _bcc = bcc;
    }
    public void setBcc(final InternetAddress bcc) {
        _bcc = getAddress(bcc);
    }
    public void setBcc(final String[] bcc)
    throws AddressException {
        _bcc = getAddress(bcc);
    }
    public void setBcc(final String bcc)
    throws AddressException {
        _bcc = getAddress(bcc);
    }
    public void setSubject(final String subject) {
        _subject = subject;
    }
    public void setContent(final String content, final boolean html) {
        _content = content;
        _html = html;
    }
    public void setAttachmentURL(final String[] attachmentURL) {
        _attachmentURL = attachmentURL;
    }
    public void setAttachmentURL(final String attachmentURL) {
        _attachmentURL = new String[] { attachmentURL };
    }

    public void postMail( String recipients[ ], String subject, String message , String from) throws MessagingException
    {
        boolean debug = false;

         //Set the host smtp address
         Properties props = new Properties();
         props.put("mail.smtp.host", "smtp.jcom.net");

        // create some properties and get the default Session
        Session session = Session.getDefaultInstance(props, null);
        session.setDebug(debug);

        // create a message
        Message msg = new MimeMessage(session);

        // set the from and to address
        InternetAddress addressFrom = new InternetAddress(from);
        msg.setFrom(addressFrom);

        InternetAddress[] addressTo = new InternetAddress[recipients.length]; 
        for (int i = 0; i < recipients.length; i++)
        {
            addressTo[i] = new InternetAddress(recipients[i]);
        }
        msg.setRecipients(Message.RecipientType.TO, addressTo);
       

        // Optional : You can also set your custom headers in the Email if you Want
        msg.addHeader("MyHeaderName", "myHeaderValue");

        // Setting the Subject and Content Type
        msg.setSubject(subject);
        msg.setContent(message, "text/plain");
        Transport.send(msg);
    }
    
    public static void main(String args[]) throws FileNotFoundException, IOException
    {
    	MailMessage test = new MailMessage();
    	String[] receivers = new String[1];
    	receivers[0]="m.medarhri@gmail.com";
    	try {
			test.postMail(receivers, "test", "cc simo", "simocedre@yahoo.fr");
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
   }
