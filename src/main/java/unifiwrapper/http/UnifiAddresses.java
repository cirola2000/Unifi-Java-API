package unifiwrapper.http;

/**
 * List of request URL used to communicate to the UNIFI controller.
 * 
 * @author Ciro Baron Neto
 *
 */
public class UnifiAddresses {

	public static String API = "/api";

	public static String LOGIN = API + "/login";

	public static String SITE_NAME = "/s";

	public static String DEFAULT = "/default";

	public static String STAT = "/stat";

	public static String LIST = "/list";

	public static String API_CALL = API + SITE_NAME + DEFAULT;

	public static String USER_STATS = API_CALL + STAT + "/user/";

	public static String ALL_DEVICES = API_CALL + STAT + "/device/";

	public static String ALL_ACTIVE_CLIENTS = API_CALL + STAT + "/alluser/";
	
	public static String ALL_CLIENTS_LAST_TIME = API_CALL + STAT + "/alluser/";
	
	public static String ALL_CLIENTS = API_CALL + LIST + "/user/";

	public static String ALL_GROUPS = API_CALL + LIST + "/usergroup/";

	public static String WLAN = API_CALL + LIST + "/wlanconf/";

	public static String CMD = API_CALL + "/cmd/stamgr";

	public static String UPDATE_USER = API_CALL + "/upd/user/";

}
