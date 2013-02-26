package ds

class DealCategory {

    long id
	String name

    static constraints = {
		name unique:true
    }
}
