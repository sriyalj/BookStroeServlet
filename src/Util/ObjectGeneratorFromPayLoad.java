package Util;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import Entity.Author;

public class ObjectGeneratorFromPayLoad {
	
	public Object getObjectFromText (HttpServletRequest request) throws ClassNotFoundException, IOException  {
		Object reterivedObj = null;
		byte[] message = null;
		request.setCharacterEncoding("UTF-8");
	    int contentLen = request.getContentLength();
		InputStream is = request.getInputStream();
		if (contentLen > 0) {
			int readLen = 0;
			int readLengthThisTime = 0;
			message = new byte[contentLen];
			
			while (readLen != contentLen) {
				readLengthThisTime = is.read(message, readLen, contentLen - readLen);
				if (readLengthThisTime == -1) {
					break;
				}
				readLen += readLengthThisTime;
			}
			ByteArrayInputStream in = new ByteArrayInputStream(message);
			ObjectInputStream ois = new ObjectInputStream(in);
			reterivedObj = ois.readObject();
			
			return reterivedObj;
		}
		else {
			throw new IOException ();
		}
	}
	
	public String getObjectFromJSON (HttpServletRequest request) throws ClassNotFoundException, IOException {
		
		byte[] message = null;
		request.setCharacterEncoding("UTF-8");
	    int contentLen = request.getContentLength();
		InputStream is = request.getInputStream();
		if (contentLen > 0) {
			int readLen = 0;
			int readLengthThisTime = 0;
			message = new byte[contentLen];
			
			while (readLen != contentLen) {
				readLengthThisTime = is.read(message, readLen, contentLen - readLen);
				if (readLengthThisTime == -1) {
					break;
				}
				readLen += readLengthThisTime;
			}
			ByteArrayInputStream in = new ByteArrayInputStream(message);
			ObjectInputStream ois = new ObjectInputStream(in);
			String reterivedString = (String) ois.readObject();	
			
			return reterivedString;			
		}
		else {
			throw new IOException ();
		}
	}
	
	public String getObjectFromXML (HttpServletRequest request) throws IOException, ClassNotFoundException {
		
		byte[] message = null;
		request.setCharacterEncoding("UTF-8");
	    int contentLen = request.getContentLength();
		InputStream is = request.getInputStream();
		if (contentLen > 0) {
			int readLen = 0;
			int readLengthThisTime = 0;
			message = new byte[contentLen];
			
			while (readLen != contentLen) {
				readLengthThisTime = is.read(message, readLen, contentLen - readLen);
				if (readLengthThisTime == -1) {
					break;
				}
				readLen += readLengthThisTime;
			}
			ByteArrayInputStream in = new ByteArrayInputStream(message);
			ObjectInputStream ois = new ObjectInputStream(in);
			String reterivedString = (String) ois.readObject();
			
			return reterivedString;	
		}
		else {
			throw new IOException ();
		}
	}

}
