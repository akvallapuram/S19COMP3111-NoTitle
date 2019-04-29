package comp3111.coursescraper;

import java.util.Map;
import java.util.HashMap;
import java.time.LocalTime;
import java.util.Locale;
import java.time.format.DateTimeFormatter;


/**
 * OOP representation of a Slot (E.G. We14:00-15:50:Rm 5620, Lift 31-32 (70))
 */
public class Slot {
	private int day;
	private LocalTime start;
	private LocalTime end;
	private String venue;
	private String type;
	public static final String DAYS[] = {"Mo", "Tu", "We", "Th", "Fr", "Sa"};
	public static final Map<String, Integer> DAYS_MAP = new HashMap<String, Integer>();
	static {
		for (int i = 0; i < DAYS.length; i++)
			DAYS_MAP.put(DAYS[i], i);
	}

	/**
	 * Creates a Clone of a Slot
	 * @return new Slot
	 */
	@Override
	public Slot clone() {
		Slot s = new Slot();
		s.day = this.day;
		s.start = this.start;
		s.end = this.end;
		s.venue = this.venue;
		s.type = this.type;
		return s;
	}

	/**
	 * Returns String of Slot
	 * @return String representation of a Slot
	 */
	public String toString() {
		return DAYS[day] + start.toString() + "-" + end.toString() + ":" + venue;
	}

	/**
	 * Returns int of starting hour
	 * @return int starting hour of slot
	 */
	public int getStartHour() {
		return start.getHour();
	}

	/**
	 * Returns int of starting min
	 * @return return starting minute of slot
	 */
	public int getStartMinute() {
		return start.getMinute();
	}

	/**
	 * returns int of ending hour
	 * @return return ending hour of a slot
	 */
	public int getEndHour() {
		return end.getHour();
	}

	/**
	 * returns int of ending min
	 * @return return ending minute of a slot
	 */
	public int getEndMinute() {
		return end.getMinute();
	}

	/**
	 * returns LocalTime obj of start
	 * @return the start
	 */
	public LocalTime getStart() {
		return start;
	}

	/**
	 * Sets start time of Slot to String of pattern ("hh:mma")
	 * @param start the start to set
	 */
	public void setStart(String start) {
		this.start = LocalTime.parse(start, DateTimeFormatter.ofPattern("hh:mma", Locale.US));
	}

	/**
	 * returns LocalTime Object of End
	 * @return the end
	 */
	public LocalTime getEnd() {
		return end;
	}

	/**
	 * Sets the end time of Slot to String of pattern ("hh:mma")
	 * @param end the end to set
	 */
	public void setEnd(String end) {
		this.end = LocalTime.parse(end, DateTimeFormatter.ofPattern("hh:mma", Locale.US));
	}

	/**
	 * returns string venue of slot
	 * @return the venue
	 */
	public String getVenue() {
		return venue;
	}

	/**
	 * sets param string venue of Slot
	 * @param venue the venue to set
	 */
	public void setVenue(String venue) {
		this.venue = venue;
	}

	/**
	 * returns int day of Slot (0=Mon, 1=Tues, etc.)
	 * @return the day
	 */
	public int getDay() {
		return day;
	}

	/**
	 * sets int day of Slot
	 * @param day the day to set
	 */
	public void setDay(int day) {
		this.day = day;
	}


	/**
	 * returns string type of slot
	 * @return type of Slot (Lab, Tutorial, Lecture)
	 */
	public String getType() { return type; }

	/**
	 * sets string type of Slot
	 * @param type the type of Slot (Lab, Tutorial, Lecture)
	 */
	public void setType(String type) { this.type = type; }

}
