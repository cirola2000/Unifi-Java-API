package unifiwrapper.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;

public class AllowedUsers implements Iterator<AllowedUser> {
	
    private BufferedReader r;

    public AllowedUsers(String path) {
        try {
			this.r = new BufferedReader(new FileReader(new File(path)));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    @Override
    public boolean hasNext() {
        try {
            r.mark(1);
            if (r.read() < 0) {
                return false;
            }
            r.reset();
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    @Override
    public AllowedUser next() {
        try {
        	AllowedUser a = new AllowedUser();
        	String line = r.readLine();
//        	if(line.startsWith("#"))
        		a.userName = line; 
//        	else 
        		line = r.readLine();
        		a.macAddress = line;
        		
        		return a;
        	
        } catch (IOException e) {
            return null;
        }
    }

    @Override
    public void remove() {
        throw new UnsupportedOperationException();
    }

}
