package unifiwrapper.functions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import unifiwrapper.client.Client;
import unifiwrapper.devices.ClientDevice;
import unifiwrapper.devices.ClientDeviceFactory;
import unifiwrapper.exceptions.NoClientFoundException;
import unifiwrapper.http.Connection;
import unifiwrapper.http.UnifiAddresses;

/**
 * Class with methods for UNIFI clients
 * 
 * @author Ciro Baron Neto
 *
 */

public class ClientFunctions extends Connection {
	/**
	 * Get a client based on the MAC address
	 * 
	 * @param macAddress
	 * @return
	 * @throws NoClientFoundException
	 */
	public ClientDevice getClient(String macAddress) throws NoClientFoundException {
		try {
			return ClientDeviceFactory.createClientDevice((JSONObject) query(UnifiAddresses.USER_STATS + macAddress, null).get(0));
		} catch (Exception e) {
			throw new NoClientFoundException("No client found, MAC address: " + macAddress);
		}
	}
	
	/**
	 * Get all clients
	 * @return
	 */
	public List<ClientDevice> getAllClients() {
		JSONArray a = query(UnifiAddresses.ALL_CLIENTS, null);
		List<ClientDevice> list = new ArrayList<>();

		for (int i = 0; i < a.length(); i++) {
			ClientDevice c = ClientDeviceFactory.createClientDevice((JSONObject) a.get(i));
			list.add(c);
		}
		return list;
	}

	
	/**
	 * Get all active clients
	 * @return
	 */
	public List<ClientDevice> getAllActiveClients() {
		JSONArray a = query(UnifiAddresses.ALL_ACTIVE_CLIENTS, null);
		List<ClientDevice> list = new ArrayList<>();
		
		for (int i = 0; i < a.length(); i++) {
			ClientDevice c = ClientDeviceFactory.createClientDevice((JSONObject) a.get(i));
			list.add(c);
		}
		return list;
	}
		
	/**
	 * Get all clients in the last number of hours
	 * @param numberOfHours number of hours
	 * @return
	 */
	public List<ClientDevice> getAllClientsNh(int numberOfHours) {
		
		HashMap<String, String> cmds = new HashMap<String, String>();
		cmds.put("type", "all");
		cmds.put("conn", "all");
		cmds.put("within", String.valueOf(numberOfHours));

		JSONArray a = query(UnifiAddresses.ALL_CLIENTS_LAST_TIME, makeCMD(cmds));
		List<ClientDevice> list = new ArrayList<>();
		
		for (int i = 0; i < a.length(); i++) {
			ClientDevice c = ClientDeviceFactory.createClientDevice((JSONObject) a.get(i));
			list.add(c);
		}
		return list;
	}
	

	
	/**
	 * Block a client based on the mac address
	 * @param macAddress
	 * @return
	 */
	public JSONArray blockClient(String macAddress) {
		HashMap<String, String> cmds = new HashMap<String, String>();
		cmds.put("cmd", "block-sta");
		cmds.put("mac", macAddress);
		
		return query(UnifiAddresses.CMD, makeCMD(cmds));
	}
	
	/**
	 * Unblock a client based on the mac address
	 * @param macAddress
	 * @return
	 */
	public JSONArray unblockClient(String macAddress) {

		HashMap<String, String> cmds = new HashMap<String, String>();
		cmds.put("cmd", "unblock-sta");
		cmds.put("mac", macAddress);

		return query(UnifiAddresses.CMD, makeCMD(cmds));
	}

	/**
	 * Update a client alias. 
	 * @param macAddress
	 * @return
	 */
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
