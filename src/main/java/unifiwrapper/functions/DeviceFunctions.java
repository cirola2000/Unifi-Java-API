package unifiwrapper.functions;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

import unifiwrapper.entities.Device;
import unifiwrapper.http.Connection;
import unifiwrapper.http.UnifiAddresses;

/**
 * Class with methods for UNIFI devices
 * 
 * @author Ciro Baron Neto
 *
 */
public class DeviceFunctions extends Connection {
	
	/**
	 * Get all UNIFI devices 
	 * @return
	 */
	public ArrayList<Device> getAllDevices() {

		JSONArray a = query(UnifiAddresses.ALL_DEVICES, null);

		ArrayList<Device> list = new ArrayList<Device>();

		for (int i = 0; i < a.length(); i++)
			list.add(new Device((JSONObject) a.get(i)));

		return list;

	}
}
