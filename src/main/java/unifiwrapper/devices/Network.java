package unifiwrapper.devices;

import org.json.JSONObject;

public class Network {
	
	JSONObject o;
	
	public Network(	JSONObject o) {
		this.o = o;
	}

	public String getType() {
		return o.getString("type");
	}

	public String getIp() {
		return o.getString("ip");
	}

}
