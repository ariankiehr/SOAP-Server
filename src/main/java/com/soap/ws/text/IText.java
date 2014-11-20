package com.soap.ws.text;

import java.util.List;

import javax.jws.HandlerChain;
import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;
import javax.jws.soap.SOAPBinding.Use;


@WebService
@HandlerChain(file = "TextService_handler.xml")  
@SOAPBinding(style = Style.DOCUMENT, use=Use.LITERAL)
public interface IText {

	@WebMethod List<String> getMostUsedWords( String text );
	
	@WebMethod String correctErrors( String text );
	
	@WebMethod List<Tag> detectLanguage( String text );
	
}
