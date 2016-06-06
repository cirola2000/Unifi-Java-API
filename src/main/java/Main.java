import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import unifiwrapper.client.Client;
import unifiwrapper.devices.ClientDevice;
import unifiwrapper.devices.accesspoint.UnifiAP;
import unifiwrapper.devices.accesspoint.UnifiAccessPoint;
import unifiwrapper.http.UnifiAPI;

public class Main {

	public static void main(String[] args) {
		list();
	}

	public static void list() {
		UnifiAPI unifi = new UnifiAPI("10.40.0.3", 8443, "admin", "");
		try {
			unifi.connect();

			// listing all clients within last 24h
			List<ClientDevice> clients = unifi.getClients().getAllClientsNh(24);

			Date date = new Date();
			
			List<UnifiAccessPoint> devices = unifi.getDevices().getAllDevices();
//			
			System.out.println(devices.iterator().next().getCFGVersion());
			
			Collections.sort(clients, new Comparator<ClientDevice>(){
				public int compare(ClientDevice a, ClientDevice b){
					return a.getClientDeviceType().toString().compareToIgnoreCase(b.getClientDeviceType().toString());
				}
			});
			
			
			for (ClientDevice client : clients) {
				System.out.println(client.getClientDeviceType());
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
