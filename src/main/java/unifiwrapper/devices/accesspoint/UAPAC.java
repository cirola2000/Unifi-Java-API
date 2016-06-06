package unifiwrapper.devices.accesspoint;

import org.json.JSONObject;

public class UAPAC extends UnifiAP{

	public UAPAC(JSONObject o) {
		super(o);
	}

	public UnifiAccessPointType getAccessPointType() {
		return UnifiAccessPointType.UAP_LR;
	}
	 
} 
