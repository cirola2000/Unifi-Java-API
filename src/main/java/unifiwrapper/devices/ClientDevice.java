package unifiwrapper.devices;

public interface ClientDevice {

	public String getId();

	public long getDuration();

	public long getFirstSeen();

	public long getLastSeen();

	public String getHostname();

	public Boolean getIsGuest();

	public Boolean getIsWired();

	public Boolean getIsBlocked();

	public String getMac();

	public String getOui();

	public long getRxBytes();

	public long getTxBytes();

	public String getSiteId();

	public String getStatId();

	public long getStatBytes();

	public long getStatDuration();

	public String getStatO();

	public long getStatRxBytes();

	public long getStatTxBbytes();

	public String getStatSiteId();

	public String getStatUser();
	
	public ClientDeviceType getClientDeviceType();
	
}
