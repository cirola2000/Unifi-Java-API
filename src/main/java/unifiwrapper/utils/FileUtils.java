package unifiwrapper.utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

public class FileUtils {
	
	/**
	 * 
	 * Reads a file using a line for alias (starting with #) and a line for the MAC address
	 *
	 * File example:
	 * #Tablet 1
	 * aa:bb:cc:dd:ee:ff
	 * #Laptop 2
	 * aa:bb:cc:dd:ee:ef
	 * #Ciro's mobile
	 * aa:bb:cc:dd:ee:df
	 * #Moleta's laptop
	 * aa:bb:cc:dd:ee:cf
	 * 
	 * @param fileName
	 * @return hashmap with MAC address and Alias name
	 */

	public HashMap<String,String> readAllowedMACs(String fileName) {
		int i =0 ;
		HashMap<String,String> macs = new HashMap<String,String>();
		try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
			String line;
			String lastLine="";
			while ((line = br.readLine()) != null) {
				if (!line.startsWith("#"))
					if(lastLine.startsWith("#")){
						macs.put(line, lastLine);
					}
					else{
						macs.put(line, "#Some important device."+i);
					}
				lastLine = line;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return macs;
	}

}
