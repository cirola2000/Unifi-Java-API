import java.util.ArrayList;

import unifiwrapper.entities.Client;
import unifiwrapper.entities.Device;
import unifiwrapper.unifi.UnifiAPI;

public class Main {

	public static void main(String[] args) {
		list();
	}

	public static void list() {
		UnifiAPI unifi = new UnifiAPI("10.40.0.3", 8443, "admin", "");
		try {
			unifi.connect();
			ArrayList<Client> listClients = unifi.getAllClients();

			for (Client client : listClients) {
				System.out.println(client.getMac());
			}

			ArrayList<Device> listDevices = unifi.getAllDevices();
			for (Device device : listDevices) {
				System.out.println(device.getNetworkConfiguration().getIp());
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}


}
