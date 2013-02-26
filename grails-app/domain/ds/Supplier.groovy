package ds

class Supplier {
	static expose='supplier'
	
	long id
	String name
	String password
	String description
	String email
	String phone
	String address
	double longitude
	double latitude
	String iconUri
	static hasMany=[categories:Category,deals:Deal]

    static constraints = {
		name
		password Size: 6..25
		address blank: false
		longitude blank: false
		latitude blank: false
		email email: true, nullable:true
		description nullable:true
		iconUri nullable:true
		
    }
	/*
	static marshalling={
		xml {
			export {
				elementName 'location'
				attribute 'longitude'
				attribute 'latitude'
			}
		}
	}*/
	
}