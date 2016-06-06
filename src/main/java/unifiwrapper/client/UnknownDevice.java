package unifiwrapper.client;

import org.json.JSONObject;

import unifiwrapper.devices.ClientDeviceType;

public class UnknownDevice extends Client{

	public UnknownDevice(JSONObject o) {
		super(o);
	}
	
	@Override
	public String getHostname(){
		return "unknown";
	}

	public ClientDeviceType getClientDeviceType() {
		return ClientDeviceType.UNKNOWN;
	}

}
