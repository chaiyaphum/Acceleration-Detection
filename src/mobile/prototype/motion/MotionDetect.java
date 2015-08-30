package mobile.prototype.motion;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class MotionDetect {
	
	public static void main(String[] args) throws IOException {
		
		ArrayList<Double> rawData = new ArrayList<Double>();
		EMA ema1 = new EMA();
		
		String testFile = "test\\r_freeflow_31_03_16_07_51.txt";
		
		/*
		for(int i=1; i<=100; i++) {
			//rawData.add((double) i);
			System.out.println( ema1.GetEMAData((double) i) );
		}
		*/
		
		BufferedReader br = new BufferedReader(new FileReader(testFile));
		
		try {
		    StringBuilder sb = new StringBuilder();
		    String line = br.readLine();

		    while (line != null) {
		        sb.append(line);
		        sb.append(System.lineSeparator());
		        line = br.readLine();

		        if(line == null) {
		        	continue;
		        }
		        System.out.println(ema1.GetEMAData((Double.parseDouble(line))));
		    }
		} finally {
		    br.close();
		}
	}

}
