package com.soap.ws.mail;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.jws.WebService;

@WebService(endpointInterface = "com.soap.ws.mail.IMail")
public class Mail implements IMail {
	
	private static final String EMAIL_PATTERN = 
			"^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
			+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
	
	private Pattern pattern;
	private Matcher matcher;

	@Override
	public boolean sendMail(SendMailInfo mailInfo) {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean validateMailAddress(String address) {
		pattern = Pattern.compile(EMAIL_PATTERN);
		matcher = pattern.matcher(address);
		return matcher.matches();
	}


}
