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

import unifiwrapper.unifi.UnifiAddresses;

public abstract class Connection {

	final static Logger logger = Logger.getLogger(Connection.class);

	private final String USER_AGENT = "Mozilla/5.0";

	private HashMap<String, String> cookies = new HashMap<String, String>();

	String host;
	String user;
	String password;
	int port;

	public Connection(String host, int port, String user, String password) {
		this.host = host;
		this.user = user;
		this.port = port;
		this.password = password;
	}

	public Connection(String host, int port, String user, String password, String siteName) {
		this.host = host;
		this.user = user;
		this.port = port;
		this.password = password;

		logger.debug("New site added: " + siteName);
		UnifiAddresses.SITE_NAME = siteName;
	}

	public HttpsURLConnection con = null;

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

		String urlAddress = "https://" + host + ":" + port + UnifiAddresses.LOGIN;

		logger.debug("Connecting to: " + urlAddress);

		url = new URL(urlAddress);

		con = (HttpsURLConnection) url.openConnection();

		con.setRequestMethod("POST");

		String query = "{\"username\":\"" + user + "\",\"password\":\"" + password + "\"}:";

		con.setRequestProperty("Content-length", String.valueOf(query.length()));
		con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
		con.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0;Windows98;DigExt)");
		con.setDoOutput(true);
		con.setDoInput(true);

		DataOutputStream output = new DataOutputStream(con.getOutputStream());
		output.writeBytes(query);
		output.close();

		BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));

		createCookies();

	}

	private void createCookies() {
		String headerName;
		for (int i = 1; (headerName = con.getHeaderFieldKey(i)) != null; i++) {
			if (headerName.equals("Set-Cookie")) {
				String cookie = con.getHeaderField(i);
				logger.debug("cookie: " + cookie);

				cookie = cookie.substring(0, cookie.indexOf(";"));
				String cookieName = cookie.substring(0, cookie.indexOf("="));
				String cookieValue = cookie.substring(cookie.indexOf("=") + 1, cookie.length());

				cookies.put(cookieName, cookieValue);
			}
		}
	}

	public JSONArray query(String url, JSONObject query) {
		JSONObject o = new JSONObject();

		try {
			URL myUrl = new URL("https://" + host + ":" + port + url);

			logger.debug("Running command: " + myUrl.toString());

			HttpsURLConnection urlConn = (HttpsURLConnection) myUrl.openConnection();

			if (query != null) {
				urlConn.setRequestMethod("POST");
				urlConn.setRequestProperty("Content-length", String.valueOf(query.toString().length()));
			}

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
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
