package unifiwrapper.http;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.X509Certificate;
import java.util.HashMap;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 * General class to make an SSL connection excluding the certificate verification
 * @author Ciro Baron Neto
 */
public abstract class Connection {

	final static Logger logger = Logger.getLogger(Connection.class);

	/**
	 * Storing cookies
	 */
	private static HashMap<String, String> cookies = new HashMap<String, String>();

	/**
	 * UNIFI server address and authentication parameters.
	 */
	private static String host;
	private static String user;
	private static String password;
	private static int port;

	public Connection() {
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * Constructor with the desired parameters of the HTTP connection (default UNIFI site name)
	 * @param host
	 * @param port
	 * @param user
	 * @param password
	 */
	public Connection(String host, int port, String user, String password) {
		this.host = host;
		this.user = user;
		this.port = port;
		this.password = password;
	}
	
	/**
	 * Constructor with the desired parameters of the HTTP connection and a UNIFI site name
	 * @param host
	 * @param port
	 * @param user
	 * @param password
	 */
	public Connection(String host, int port, String user, String password, String siteName) {
		this.host = host;
		this.user = user;
		this.port = port;
		this.password = password;
		
		logger.debug("New site added: " + siteName);
		UnifiAddresses.SITE_NAME = siteName;
	}

	private HttpsURLConnection connection = null;

	/**
	 * Make the connection to UNIFI server!
	 * @throws Exception
	 */
	public void connect() throws Exception {

		// Create a trust manager that does not validate certificate chains
		TrustManager[] trustAllCerts = new TrustManager[] { new X509TrustManager() {
			public java.security.cert.X509Certificate[] getAcceptedIssuers() {
				return null;
			}

			public void checkClientTrusted(X509Certificate[] certs, String authType) {
			}

			public void checkServerTrusted(X509Certificate[] certs, String authType) {
			}
		} };

		// Install the all-trusting trust manager
		SSLContext sc = SSLContext.getInstance("SSL");
		sc.init(null, trustAllCerts, new java.security.SecureRandom());
		HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());

		// Create all-trusting host name verifier
		HostnameVerifier allHostsValid = new HostnameVerifier() {

			public boolean verify(String hostname, SSLSession session) {
				return true;
			}
		};

		// Install the all-trusting host verifier
		HttpsURLConnection.setDefaultHostnameVerifier(allHostsValid);

		URL url;

		// making the URL
		String urlAddress = "https://" + host + ":" + port + UnifiAddresses.LOGIN;

		logger.debug("Connecting to: " + urlAddress);
		url = new URL(urlAddress);

		connection = (HttpsURLConnection) url.openConnection();

		// setting some headers and the post body
		String query = "{\"username\":\"" + user + "\",\"password\":\"" + password + "\"}:";
		connection.setRequestMethod("POST");
		connection.setRequestProperty("Content-length", String.valueOf(query.length()));
		connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
		connection.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0;Windows98;DigExt)");
		connection.setDoOutput(true);
		connection.setDoInput(true);

		DataOutputStream output = new DataOutputStream(connection.getOutputStream());
		output.writeBytes(query);
		output.close();

		BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));

		createCookies();
	}

	/**
	 * Save all cookies received by the server.
	 */
	private void createCookies() {
		String headerName;
		for (int i = 1; (headerName = connection.getHeaderFieldKey(i)) != null; i++) {
			if (headerName.equals("Set-Cookie")) {
				String cookie = connection.getHeaderField(i);
				logger.debug("cookie: " + cookie);

				cookie = cookie.substring(0, cookie.indexOf(";"));
				String cookieName = cookie.substring(0, cookie.indexOf("="));
				String cookieValue = cookie.substring(cookie.indexOf("=") + 1, cookie.length());

				cookies.put(cookieName, cookieValue);
			}
		}
	}
	
	/**
	 * Make a request to the server. 
	 * @param url the server URL
	 * @param query null for a GET request, and a JSONobject to create a POST
	 * @return
	 */
	public JSONArray query(String url, JSONObject query) {
		JSONObject o = new JSONObject();

		try {
			URL myUrl = new URL("https://" + host + ":" + port + url);

			logger.debug("Running request: " + myUrl.toString());

			HttpsURLConnection urlConn = (HttpsURLConnection) myUrl.openConnection();

			// check whether is a GET or POST method
			if (query != null) {
				urlConn.setRequestMethod("POST");
				urlConn.setRequestProperty("Content-length", String.valueOf(query.toString().length()));
			}

			// retrieve the cookies!
			String cookieStr = "";
			for (String cookie : cookies.keySet()) {
				cookieStr = cookieStr + cookie + "=" + cookies.get(cookie).toString() + "; ";
			}

			cookieStr = cookieStr.substring(0, cookieStr.length() - 2);

			urlConn.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
			urlConn.setRequestProperty("Cookie", cookieStr);
			urlConn.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0;Windows98;DigExt)");
			urlConn.setDoOutput(true);
			urlConn.setDoInput(true);
			urlConn.connect();

			if (query != null) {
				DataOutputStream output = new DataOutputStream(urlConn.getOutputStream());
				output.writeBytes(query.toString());
				output.close();
			}

			BufferedReader in = new BufferedReader(new InputStreamReader(urlConn.getInputStream()));
			String inputLine;
			StringBuffer response = new StringBuffer();

			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();

			o = new JSONObject(response.toString());

			return o.getJSONArray("data");

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
