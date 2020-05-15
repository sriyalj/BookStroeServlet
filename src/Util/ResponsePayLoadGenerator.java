package Util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import com.fasterxml.jackson.databind.ObjectMapper;

import Entity.Author;

public class ResponsePayLoadGenerator {	
	
	public  ResponsePayLoadGenerator () {
		
	}	
		
	public byte[] textPayLoadGenerator (Object obj) throws IOException  {
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
	    ObjectOutputStream oos = new ObjectOutputStream(bos);
	    oos.writeObject(obj);
	    oos.flush();
	    byte [] requestBody = bos.toByteArray();
	    return requestBody;
	}
	
	public byte [] jsonPayLoadGenerator (Object obj) throws IOException {
		ObjectMapper mapper = new ObjectMapper();
	    String jsonString = mapper.writeValueAsString(obj);
	    
	    ByteArrayOutputStream bos = new ByteArrayOutputStream();
	    ObjectOutputStream oos = new ObjectOutputStream(bos);
	    oos.writeObject(jsonString);
	    oos.flush();
	    byte [] requestBody = bos.toByteArray();
	    return requestBody;
	}
	
	public String xmlPayLoadGenerator (Object obj) {
		return null;
	}

}
