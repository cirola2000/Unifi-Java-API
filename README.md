# Unifi-Wrapper
Java wrapper for communication with UNIFI devices.

##Simple example:
The following example creates a new connection to the UNIFI controller, retrieve a list of users connected in the last hour and all devices.

```
	public static void list() {
	  // Creating a connection with the UNIFI controller
		UnifiAPI unifi = new UnifiAPI("10.40.0.3", 8443, "admin", "");
		try {
			unifi.connect();
			
			// printing all client hostnames connected in the last hour
			ArrayList<Client> listClients = unifi.getClients().getAllClientsNh(1);

			int i = 0;
			for (Client client : listClients) {
				System.out.println((i++) + " " + client.getHostname());
			}

      // printing all devices IP
			ArrayList<Device> listDevices = unifi.getDevices().getAllDevices();
			for (Device device : listDevices) {
				System.out.println(device.getNetworkConfiguration().getIp());
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
```



