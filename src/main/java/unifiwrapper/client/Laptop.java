package unifiwrapper.client;

import org.json.JSONObject;

import unifiwrapper.devices.ClientDeviceType;

public class Laptop extends Client{

	public Laptop(JSONObject o) {
		super(o);
	}

	public ClientDeviceType getClientDeviceType() {
		return ClientDeviceType.LAPTOP;
	}

}
