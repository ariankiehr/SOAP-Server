package com.soap.ws.hash;

import java.util.List;

import javax.jws.WebService;

@WebService(endpointInterface = "com.soap.ws.hash.IHash")
public class Hash implements IHash {

	@Override
	public String getMD5(String source) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getSHA1(String source) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getSHA256(String source) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getSHA384(String source) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getSHA512(String source) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getPBKDF2(String source) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> getHashType(String hash) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String breakHash(HashToBreak hashToBreak) {
		// TODO Auto-generated method stub
		return null;
	}

}
