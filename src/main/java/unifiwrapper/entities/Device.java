package unifiwrapper.entities;

import org.json.JSONObject;

public class Device {
	JSONObject o;

	public Device(JSONObject o) {
		this.o = o;

		if (!o.get("radio_na").toString().equals("null"))
			radioNa = new Radio((JSONObject) o.get("radio_na"));
		
		if (!o.get("radio_ng").toString().equals("null"))
			radioNg = new Radio((JSONObject) o.get("radio_ng"));
		
		if (!o.get("config_network").toString().equals("null"))
			network = new Network((JSONObject) o.get("config_network"));
		
	}

	private Radio radioNg;

	private Radio radioNa;

	private Network network;
	
	public Radio getRadioNg() {
		return radioNg;
	}

	public String getType() {
		return o.getString("type");
	}

	public Radio getRadioNa() {
		return radioNa;
	}

	public boolean isAdopted() {
		return o.getBoolean("adopted");
	}

	public long getLastSeen() {
		return o.getLong("last_seen");
	}

	public boolean isIsolated() {
		return o.getBoolean("isolated");
	}

	public boolean isScanning() {
		return o.getBoolean("scanning");
	}

	public String getNgState() {
		return o.getString("ng_state");
	}
	
	public String getNaState() {
		return o.getString("na_state");
	}

	public Network getNetworkConfiguration() {
		return network;
	}	
	

}
