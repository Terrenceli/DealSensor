package ds

class Consumer {
	long id
	String deviceId
	double lastLatitude
	double lastLongitude
	
	int dailyNumberofQueries
	
    static constraints = {
		deviceId blank: false
    }
}
