package Util;

public class GeneralServerResponse {
	
	private String serverResponseCode;
	private String serverMsg;
	
	public GeneralServerResponse (String serverResponseCode, String serverMsg) {
		this.serverResponseCode = serverResponseCode;
		this.serverMsg = serverMsg;
	}

	public String getServerResponseCode() {
		return serverResponseCode;
	}

	public void setServerResponseCode(String serverResponseCode) {
		this.serverResponseCode = serverResponseCode;
	}

	public String getServerMsg() {
		return serverMsg;
	}

	public void setServerMsg(String serverMsg) {
		this.serverMsg = serverMsg;
	}
	
	

}
