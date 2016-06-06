package unifiwrapper.devices.accesspoint;

import org.json.JSONObject;

import unifiwrapper.exceptions.NoAccessPointModelFoundException;

public class UnifiAccessPointFactory {

	public static UnifiAccessPoint createAccessPoint(JSONObject o) throws NoAccessPointModelFoundException {

		String accessPointModel = o.getString("model");

		if (accessPointModel.equalsIgnoreCase(UnifiAccessPointType.UAP.model())) {
			return new UAP(o);
		} else if (accessPointModel.equalsIgnoreCase(UnifiAccessPointType.UAP_LR.model())) {
			return new UAPLR(o);
		} else if (accessPointModel.equalsIgnoreCase(UnifiAccessPointType.UAP_PRO.model())) {
			return new UAPPRO(o);
		} else if (accessPointModel.equalsIgnoreCase(UnifiAccessPointType.UAP_AC.model())) {
			return new UAPAC(o);
		} else
			throw new NoAccessPointModelFoundException("Access point model: " + accessPointModel + " not found");

	}

}
