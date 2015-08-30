package mobile.prototype.motion;

import java.util.ArrayList;

/*
 * 	k = 2 / (timePer + 1);
 *	EMA = (k * (CurrentPrice-PreviousPeriodEMA)) / PreviousPeriodEMA
 *	EMA = (k * (vin-vout)) / vout
 *	EMA = K*vin + ((1-k) * vout)
*/

public class EMA {
	
	private ArrayList<Double> accelerometerCollection = new ArrayList<Double>();
	
	private final int EMA_WINDOW_SIZE = 15;
	private boolean windowDataComplete = false;
	
	private double previouValue;
	private double k, oneK;
	
	public EMA() {
		windowDataComplete = false;
		accelerometerCollection.clear();
		
		k = 2.0 / (EMA_WINDOW_SIZE + 1);
		oneK = 1-k;;
	}
	
	public double GetEMAData(double accelerometerRaw) {
		
		if(windowDataComplete) {
			previouValue = CalculateEMA(accelerometerRaw, previouValue);
			return previouValue;
			
		} else {
			accelerometerCollection.add(accelerometerRaw);
			
			if(accelerometerCollection.size() == EMA_WINDOW_SIZE) {
				windowDataComplete = true;
				previouValue = (accelerometerCollection.get(EMA_WINDOW_SIZE-1) * k) + (AverageList(accelerometerCollection) * oneK);
				
				return previouValue;
			}
			
			return -1.0;
		}
	}
	
	private double CalculateEMA(double _currentValue, double _previouValue) {
		return ( (k * _currentValue) + (oneK * _previouValue) );
	}
	
	private double AverageList(ArrayList<Double> _list) {
		double sum = 0;
		for(int i=0; i<_list.size(); i++) {
			sum += _list.get(i);
		}
		
		return (sum / _list.size());
	}
}
