package unifiwrapper.utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

public class FileUtils {

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
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return macs;
	}

}
