package unifiwrapper.devices.accesspoint;

import org.json.JSONObject;

import unifiwrapper.devices.UnifiDeviceType;

public class UAPLR extends UnifiAP{

	public UAPLR(JSONObject o) {
		super(o);
	}
	
	public UnifiAccessPointType getAccessPointType() {
		return UnifiAccessPointType.UAP;
	}

}
