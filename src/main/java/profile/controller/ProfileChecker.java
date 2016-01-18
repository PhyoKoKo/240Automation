package profile.controller;

import java.util.ArrayList;

import profile.entity.MeterSummary;
import profile.entity.Profile;

public class ProfileChecker {
	public static void check(String tool, String date){
		ArrayList<MeterSummary> meterSum = MeterSummaryCapturer.getMeterSummary(tool, date);
		for(int i = 0; i < meterSum.size(); i++){
			MeterSummary ms = meterSum.get(i);
			Profile p = ProfileCapturer.getProfileByIEEE(ms.getIeee(), ms.getStatus());
			if(ms.getAvg_power() >= p.getPower_min() && ms.getAvg_power() <= p.getPower_max())
				ms.setNormal(true);
			else
				ms.setNormal(false);
			ms.print();
		}
	}
}
