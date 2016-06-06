package unifiwrapper.devices.accesspoint;

import org.json.JSONObject;

public class UAPPRO extends UnifiAP{

	public UAPPRO(JSONObject o) {
		super(o);
		// TODO Auto-generated constructor stub
	}

	public UnifiAccessPointType getAccessPointType() {
		return UnifiAccessPointType.UAP_PRO;
	}
 
}
