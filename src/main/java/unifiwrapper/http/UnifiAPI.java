package unifiwrapper.http;

import unifiwrapper.functions.ClientFunctions;
import unifiwrapper.functions.DeviceFunctions;

/**
 * List of methods used to communicate with the UNIFI controller
 * @author Ciro Baron Neto
 * 
 */
public class UnifiAPI extends Connection {

	protected UnifiAPI() {
	}
	
	/**
	 * Starting a connection with the controller (using UNIFI default site)
	 * @param host
	 * @param port
	 * @param user
	 * @param password
	 */
	public UnifiAPI(String host, int port, String user, String password) {
		super(host, port, user, password);
	}

	/**
	 * Starting a connection with the controller (using a custom site)
	 * @param host
	 * @param port
	 * @param user
	 * @param password
	 */
	public UnifiAPI(String host, int port, String user, String password, String site) {
		super(host, port, user, password, site);
	}
	
	private ClientFunctions Clients = new ClientFunctions();

	private DeviceFunctions Devices = new DeviceFunctions();

	public ClientFunctions getClients() {
		return Clients;
	}

	public DeviceFunctions getDevices() {
		return Devices;
	}

	
	
}
