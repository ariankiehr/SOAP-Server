package com.soap.ws.mail;

import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.jws.WebService;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

@WebService(endpointInterface = "com.soap.ws.mail.IMail")
public class Mail implements IMail {
	
	private static final String EMAIL_PATTERN = 
			"^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
			+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
	
	private Pattern pattern;
	private Matcher matcher;
	
	
	private String host = "smtp.gmail.com";

	@Override
	public boolean sendMail(SendMailInfo mailInfo) {
        Properties props = System.getProperties();
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.user", mailInfo.from);
        props.put("mail.smtp.password", mailInfo.pass);
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");

        Session session = Session.getDefaultInstance(props);
        MimeMessage message = new MimeMessage(session);

        try {
            message.setFrom(new InternetAddress(mailInfo.from));
            
            for( String iAddress : mailInfo.to ) {
                message.addRecipient(Message.RecipientType.TO, new InternetAddress(iAddress));
            }         
            
            message.setSubject(mailInfo.subject);
            message.setText(mailInfo.body);
            Transport transport = session.getTransport("smtp");
            transport.connect(host, mailInfo.from, mailInfo.pass);
            transport.sendMessage(message, message.getAllRecipients());
            transport.close();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return false ;
        }		
        return true;
	}

	@Override
	public boolean validateMailAddress(String address) {
		pattern = Pattern.compile(EMAIL_PATTERN);
		matcher = pattern.matcher(address);
		return matcher.matches();
	}


}
