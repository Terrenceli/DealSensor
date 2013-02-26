package ds

class PictureUri {
	long id
	String uri
	
	static belongsTo=[deal:Deal]

    static constraints = {
    }
}
