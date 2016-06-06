package unifiwrapper.devices;

import org.json.JSONException;
import org.json.JSONObject;

import unifiwrapper.client.Laptop;
import unifiwrapper.client.Mobile;
import unifiwrapper.client.UnknownDevice;

public class ClientDeviceFactory {

	public static ClientDevice createClientDevice(JSONObject o) {

		String hostname;
		
		try {
			hostname = o.getString("hostname");
		} catch (JSONException e) {
			hostname = null;
		}

		if (hostname == null)
			return new UnknownDevice(o);
		else if (hostname.contains("android") || hostname.contains("hone") || hostname.contains("iPad")) {
			return new Mobile(o);
		} else
			return new Laptop(o);

	}

}
