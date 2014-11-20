package com.soap.ws.mail;

import javax.jws.HandlerChain;
import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;
import javax.jws.soap.SOAPBinding.Use;

@WebService
@HandlerChain(file = "MailService_handler.xml")  
@SOAPBinding(style = Style.DOCUMENT, use=Use.LITERAL)
public interface IMail {
	
	@WebMethod boolean sendMail(SendMailInfo mailInfo);
	
	@WebMethod boolean validateMailAddress(String address);

}
