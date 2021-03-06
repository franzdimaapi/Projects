package com.uhg.optum.ssmo.peoplesoft.twscalendar.map;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import org.joda.time.DateTimeConstants;
import org.joda.time.LocalDate;

import com.uhg.optum.ssmo.peoplesoft.twscalendar.domain.CalendarDay;
import com.uhg.optum.ssmo.peoplesoft.twscalendar.domain.Holiday;
import com.uhg.optum.ssmo.peoplesoft.twscalendar.rules.CalendarJobRule;
import com.uhg.optum.ssmo.peoplesoft.twscalendar.util.CalendarDayComparator;
import com.uhg.optum.ssmo.peoplesoft.twscalendar.util.CalendarUtils;

public class PSFACR16Rule extends CalendarJobRule {

	public PSFACR16Rule(int year, Set<Holiday> holidayList) {
		this.year = year;
		this.holidays = holidayList;
	}

	@Override
	public List<CalendarDay> getFinalDates() {
		List<CalendarDay> result = new ArrayList<CalendarDay>();
		
		for (LocalDate d : getResults()) {
			result.add(new CalendarDay(Boolean.FALSE, d));
		}

		
		CalendarUtils.addHolidaysToList(result, holidays);
		Collections.sort(result,new CalendarDayComparator());
		return result;
	}

	/*
	 * PSF_ACR_CL_WEEKDAYS PSFACR16 Runs on weekday except {{PSFACR11}} holiday,
	 * wd1 , lwd, friday and 2 days working day before 10
	 */
	public List<LocalDate> getWeekdayExceptWD1LWDFriday(LocalDate calendar) {
		List<LocalDate> listDays = new ArrayList<LocalDate>();
		while (calendar.getDayOfMonth() <= calendar.dayOfMonth()
				.getMaximumValue()) {
			if (!CalendarUtils.isWeekEnds(calendar)
					&& calendar.getDayOfWeek() != DateTimeConstants.FRIDAY
					&& !CalendarUtils.isHoliday(calendar, holidays)) {
				listDays.add(new LocalDate(calendar.getYear(), calendar
						.getMonthOfYear(), calendar.getDayOfMonth()));
			}
			if (calendar.getDayOfMonth() == calendar.dayOfMonth()
					.getMaximumValue()) {
				break;
			}
			calendar = calendar.plusDays(1);
		}
		
		PSFACR11Rule rule = new PSFACR11Rule(year, holidays);
		listDays.removeAll(rule.getResults());
		listDays.remove(CalendarUtils.getWorkDay1(calendar, holidays));
		CalendarUtils.removeHolidays(listDays, holidays);
		return listDays;
	}

	@Override
	public List<LocalDate> getResults() {
		 List<LocalDate> listDays = new ArrayList<LocalDate>();
		for (int i = 1; i <= 12; i++) {

			List<LocalDate> dates = getWeekdayExceptWD1LWDFriday(new LocalDate(
					year, i, 1));
			for (LocalDate d : dates) {
				listDays.add(d);
			}
		}
		return listDays;
	}
}
