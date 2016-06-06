package unifiwrapper.devices.accesspoint;

import org.json.JSONObject;

import unifiwrapper.devices.UnifiDeviceType;

public class UAP extends UnifiAP{

	public UAP(JSONObject o) {
		super(o);
	}
	
	public UnifiAccessPointType getAccessPointType() {
		return UnifiAccessPointType.UAP;
	}

}
