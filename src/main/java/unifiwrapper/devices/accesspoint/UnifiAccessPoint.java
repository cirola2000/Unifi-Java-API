package unifiwrapper.devices.accesspoint;

import unifiwrapper.devices.Network;
import unifiwrapper.devices.Radio;

public interface UnifiAccessPoint {
	
	public String getCFGVersion();
	
	public Radio getRadioNg();

	public Radio getRadioNa();
	
	public boolean isAdopted();

	public long getLastSeen();

	public boolean isIsolated();

	public boolean isScanning();

	public String getNgState();
	
	public String getNaState();

	public String getInformIP();

	public String getUpTime();
	
	public String getInformURL();

	public UnifiAccessPointType getAccessPointType();
	
	public Network getNetworkConfiguration();

}
