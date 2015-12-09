package unifiwrapper.entities;

import org.json.JSONObject;

/**
 * 
 * @author Ciro Baron Neto
 *
 */
public class Client {
	JSONObject o;
	
	public Client(JSONObject o) {
		this.o = o;
	}

	public String getId() {
		return o.getString("_id");
	}

	public long getDuration() {
		return o.getLong("duration");
	}

	public long getFirstSeen() {
		return o.getLong("first_seen");
	}

	public long getLastSeen() {
		return o.getLong("last_seen");
	}

	public String getHostname() {
		return o.getString("hostname");
	}

	public Boolean getIsGuest() {
		return o.getBoolean("is_guest");
	}

	public Boolean getIsWired() {
		return o.getBoolean("is_wired");
	}
	
	public Boolean getIsBlocked() {
		return o.getBoolean("blocked");
	}

	public String getMac() {
		return  o.getString("mac");
	}

	public String getOui() {
		return  o.getString("Oui");
	}

	public long getRxBytes() {
		return o.getLong("rx_bytes");
	}

	public long getTxBytes() {
		return o.getLong("tx_bytes");
	}

	public String getSiteId() {
		return o.getString("site_id");
	}
	
	public String getStatId() {
		return o.getJSONObject("stat").getString("_id");
	}

	public long getStatBytes() {
		return  Long.parseLong(o.getJSONObject("stat").getString("bytes"));
	}

	public long getStatDuration() {
		return  Long.parseLong(o.getJSONObject("stat").getString("duration"));
	}

	public String getStatO() {
		return o.getJSONObject("stat").getString("o");
	}

	public long getStatRxBytes() {
		return Long.parseLong(o.getJSONObject("stat").getString("rx_bytes"));
	}

	public long getStatTxBbytes() {
		return Long.parseLong(o.getJSONObject("stat").getString("tx_bytes"));
	}

	public String getStatSiteId() {
		return o.getJSONObject("stat").getString("site_id");
	}

	public String getStatUser() {
		return o.getJSONObject("stat").getString("user");
	}

}
