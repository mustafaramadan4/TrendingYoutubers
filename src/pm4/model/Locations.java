package pm4.model;

public class Locations {
	protected int locationId;
	protected String location;
	
	// constructor 1: complete
	public Locations(int locationId, String location) {
		this.locationId = locationId;
		this.location = location;
	}
	
	// constructor 2: primary key
	public Locations(int locationId) {
		this.locationId = locationId;
	}
	
	// constructor 3: no primary key
	public Locations(String location) {
		this.location = location;
	}


	public int getLocationId() {
		return locationId;
	}

	public void setLocationId(int locationId) {
		this.locationId = locationId;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}
	
	

}
