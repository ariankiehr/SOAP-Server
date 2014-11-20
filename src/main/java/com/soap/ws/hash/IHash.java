package com.soap.ws.hash;

import java.util.List;

import javax.jws.HandlerChain;
import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;
import javax.jws.soap.SOAPBinding.Use;

@WebService
@HandlerChain(file = "HashService_handler.xml")  
@SOAPBinding(style = Style.DOCUMENT, use=Use.LITERAL)
public interface IHash {
 
	@WebMethod String getMD5(String source);
	@WebMethod String getSHA1(String source);
	@WebMethod String getSHA256(String source);
	@WebMethod String getSHA384(String source);
	@WebMethod String getSHA512(String source);
	@WebMethod String getPBKDF2(String source);
	@WebMethod List<String> getHashType(String hash);
	@WebMethod String breakHash( HashToBreak hashToBreak );

}