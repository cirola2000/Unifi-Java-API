package unifiwrapper.devices.accesspoint;

public enum UnifiAccessPointType {

	UAP("BZ2"),
	UAP_LR("BZ2LR"),
	UAP_PRO("U7P"),
	UAP_AC("U7E");
	
	public String m;
	
	UnifiAccessPointType(String model){
		this.m = model;
	}
	
	public String model(){
		return m;
	}
	
}
