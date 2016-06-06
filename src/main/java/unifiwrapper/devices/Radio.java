package unifiwrapper.devices;

import org.json.JSONObject;

public class Radio {
	
	JSONObject o;
	
	public Radio(JSONObject o) {
		this.o = o;
	} 

	public long getMaxTxPower() {
		return o.getLong("max_txpower");
	}

	public long getBuiltinAntGain() {
		return o.getLong("builtin_ant_gain");
	}

	public boolean isBuiltinAntenna() {
		return o.getBoolean("builtin_antenna");
	}

	public long getNss() {
		return o.getLong("nss");
	}

	public long getHt() {
		return o.getLong("ht");
	}

	public String getName() {
		return o.getString("name");
	}

	public long getMinTxpower() {
		return o.getLong("min_txpower");
	}

	public String getRadio() {
		return o.getString("radio");
	}

	public String getTxPowerMode() {
		return o.getString("tx_power_mode");
	}

	public String getChannel() {
		return o.getString("channel");
	}

	public String getTxPower() {
		return o.getString("tx_power");
	}

	public long getAntennaGain() {
		return o.getLong("antenna_gain");
	}
	


}
