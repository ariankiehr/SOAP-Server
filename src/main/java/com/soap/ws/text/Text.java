package com.soap.ws.text;

import java.util.List;

import javax.jws.WebService;

@WebService(endpointInterface = "com.soap.ws.text.IText")
public class Text implements IText {

	@Override
	public List<String> getMostUsedWords(String text) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String correctErrors(String text) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Tag> detectLanguage(String text) {
		// TODO Auto-generated method stub
		return null;
	}

}
