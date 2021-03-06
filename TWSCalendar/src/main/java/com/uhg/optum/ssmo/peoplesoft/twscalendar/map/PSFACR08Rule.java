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

public class PSFACR08Rule extends CalendarJobRule {

	public PSFACR08Rule(int year, Set<Holiday> holidayList) {
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
		Collections.sort(result, new CalendarDayComparator());
		return result;
	}

	/*
	 * Calendar is use to process lockbox files when banks are open but UHG is
	 * closed. It runs the Saturday after Thanksgiving and also may run if any
	 * other UHC Holidays fall on a banking day.
	 */
	@Override
	public List<LocalDate> getResults() {
		List<LocalDate> listDays = CalendarUtils.getHolidays(holidays);
		for (LocalDate result : listDays) {
			if (result.getDayOfWeek() == DateTimeConstants.SATURDAY) {

				listDays.add(result);
			}
			if (result.getDayOfMonth() > 20
					&& result.getDayOfWeek() == DateTimeConstants.THURSDAY) {
				result = result.plusDays(2);
				listDays.add(result);
			}
		}

		return listDays;
	}

}
