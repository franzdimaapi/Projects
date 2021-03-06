package com.uhg.optum.ssmo.peoplesoft.twscalendar.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.springframework.stereotype.Service;

import com.uhg.optum.ssmo.peoplesoft.twscalendar.domain.JobCode;

@Service
public class TWSCalendarServiceImpl implements TWSCalendarService {

	@Override
	public List<JobCode> listJobCodes() {
		List<JobCode> listJobCodes = new ArrayList<JobCode>();
			
		listJobCodes.add(new JobCode("PSF_BIL_CL_1ST_WD_WK", "PSFBIL05"));
		listJobCodes.add(new JobCode("PSF_ACR_CL_WD1_CHK", "PSFACR15"));
		listJobCodes.add(new JobCode("PSF_BIL_CL_WKD_TO_SAT_SKIP_MON", "PSFBIL02"));
		listJobCodes.add(new JobCode("PSF_BIL_CL_NON_1ST_WD_WK", "PSFBIL03"));
		listJobCodes.add(new JobCode("PSF_ACR_CL_BI_PIA_UNRD_CAL_18","PSFACR14"));
		listJobCodes.add(new JobCode("PSF_ACR_CL_WEEKDAYS", "PSFACR16"));
		listJobCodes.add(new JobCode("PSF_ACR_CL_2PRI_CAL24", "PSFACR02"));
		listJobCodes.add(new JobCode("PSF_ACR_CL_2PRI_CAL10", "PSFACR01"));
		listJobCodes.add(new JobCode("PSF_ACR_CL_ADHOC_LOCKBOX", "PSFACR08"));
		listJobCodes.add(new JobCode("PSF_ACR_CL_2PRI_CAL1", "PSFACR00"));
		listJobCodes.add(new JobCode("PSF_ACR_CL_2PRI_CAL25", "PSFACR03"));
		listJobCodes.add(new JobCode("PSF_ACR_CL_2PRI_CAL6", "PSFACR04"));
		listJobCodes.add(new JobCode("PSF_ACR_CL_4PRI_CAL1", "PSFACR06"));
		listJobCodes.add(new JobCode("PSF_ACR_CL_AR032UHC_10", "PSFACR10"));
		listJobCodes.add(new JobCode("PSF_ACR_CL_AR032UHC_01", "PSFACR09"));
		listJobCodes.add(new JobCode("PSF_ACR_CL_2PRI_NON_WD1_BYPASS", "PSFACR05"));
		listJobCodes.add(new JobCode("PSF_ACR_CL_AUTO_MAINT_ZERO_BAL", "PSFACR11"));
		listJobCodes.add(new JobCode("PSF_ACR_CL_BI_NON_WD1_CHK", "PSFACR13"));
		listJobCodes.add(new JobCode("PSF_ACR_CL_DAILY", "PSFACR17"));
		
		Collections.sort(listJobCodes, new Comparator<JobCode>() {
			public int compare(JobCode j1, JobCode j2) {
				return j1.getJobCode().compareTo(j2.getJobCode());
			}
		});

		return listJobCodes;
	}

}
