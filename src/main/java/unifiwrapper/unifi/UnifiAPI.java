package unifiwrapper.unifi;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import unifiwrapper.entities.Client;
import unifiwrapper.entities.Device;
import unifiwrapper.http.Connection;

/**
 * 
 * @author Ciro Baron Neto
 * 
 */
public class UnifiAPI extends Connection {

	public UnifiAPI(String host, int port, String user, String password) {
		super(host, port, user, password);
	}

	public UnifiAPI(String host, int port, String user, String password, String site) {
		super(host, port, user, password, site);
	}

	public JSONObject getClient(String macAddress) {
		return (JSONObject) query(UnifiAddresses.USER_STATS + macAddress, null).get(0);
	}

	public ArrayList<Device> getAllDevices() {

		JSONArray a = query(UnifiAddresses.ALL_DEVICES, null);
		
		ArrayList<Device> list = new ArrayList<Device>();

		for (int i = 0; i < a.length(); i++)
			list.add(new Device((JSONObject) a.get(i)));

		return list;

	}

	public ArrayList<Client> getAllClients() {
		JSONArray a = query(UnifiAddresses.ALL_CLIENTS, null);
		ArrayList<Client> list = new ArrayList<Client>();

		for (int i = 0; i < a.length(); i++)
			list.add(new Client((JSONObject) a.get(i)));

		return list;
	}

	public JSONArray unblockClient(String macAddress) {

		HashMap<String, String> cmds = new HashMap<String, String>();
		cmds.put("cmd", "unblock-sta");
		cmds.put("mac", macAddress);

		return query(UnifiAddresses.CMD, makeCMD(cmds));
	}

	public JSONArray blockClient(String macAddress) {
		HashMap<String, String> cmds = new HashMap<String, String>();
		cmds.put("cmd", "block-sta");
		cmds.put("mac", macAddress);

		return query(UnifiAddresses.CMD, makeCMD(cmds));
	}

	public JSONArray updateUserAlias(String id, String alias) {
		HashMap<String, String> cmds = new HashMap<String, String>();
		cmds.put("name", alias);

		return query(UnifiAddresses.UPDATE_USER + id, makeCMD(cmds));
	}

	private JSONObject makeCMD(HashMap<String, String> cmd) {
		JSONObject c = new JSONObject();
		for (String key : cmd.keySet()) {
			c.put(key, cmd.get(key));
		}
		return c;
	}

}
