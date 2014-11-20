package com.soap.ws.text;

import java.util.LinkedList;
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
		
		Tag t = new Tag();
		t.language = "Espanol";
		t.probability = 0.77f;
		
		Tag t1 = new Tag();
		t1.language = "Inlges";
		t1.probability = 0.77f;
		
		
		List<Tag> l = new LinkedList<Tag>();
		l.add(t);
		l.add(t1);
		
		return l;
	}

}
