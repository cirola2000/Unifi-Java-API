package unifiwrapper.functions;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import unifiwrapper.devices.accesspoint.UnifiAP;
import unifiwrapper.devices.accesspoint.UnifiAccessPoint;
import unifiwrapper.devices.accesspoint.UnifiAccessPointFactory;
import unifiwrapper.exceptions.NoAccessPointModelFoundException;
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
	 * @throws NoAccessPointModelFoundException 
	 * @throws JSONException 
	 */
	public List<UnifiAccessPoint> getAllDevices() throws JSONException, NoAccessPointModelFoundException {

		JSONArray a = query(UnifiAddresses.ALL_DEVICES, null);

		List<UnifiAccessPoint> list = new ArrayList<>();

		for (int i = 0; i < a.length(); i++)
//			list.add(new UnifiAP((JSONObject) a.get(i)));
			list.add(UnifiAccessPointFactory.createAccessPoint((JSONObject) a.get(i)));

		return list;

	}
}
