package com.soap.ws.mail;

import javax.jws.WebService;

@WebService(endpointInterface = "com.soap.ws.mail.IMail")
public class Mail implements IMail {

	@Override
	public boolean sendMail(SendMailInfo mailInfo) {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean validateMailAddress(String address) {
		// TODO Auto-generated method stub
		return false;
	}


}
