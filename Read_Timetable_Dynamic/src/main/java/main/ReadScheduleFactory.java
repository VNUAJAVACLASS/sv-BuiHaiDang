package main;

public class ReadScheduleFactory {
	 public static ReadSchedule createReadScheduleObj(String type) {
	        if ("playwright".equalsIgnoreCase(type)) {
	            return new TKBManager();
	        } else if ("selenium".equalsIgnoreCase(type)) {
	            return new SeleniumSchedule();
	        } else {
	            throw new IllegalArgumentException("Unsupported schedule reader type: " + type);
	        }
	    }
}
