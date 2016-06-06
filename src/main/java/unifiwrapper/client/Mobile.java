package unifiwrapper.client;

import org.json.JSONObject;

import unifiwrapper.devices.ClientDeviceType;

public class Mobile extends Client{

	public Mobile(JSONObject o) {
		super(o);
	}

	public ClientDeviceType getClientDeviceType() {
		return ClientDeviceType.MOBILE;
	}

}
