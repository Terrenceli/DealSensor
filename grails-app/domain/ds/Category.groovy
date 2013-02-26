package ds

class Category {
	long id
	String name

    static constraints = {
		name unique:true
    }
}
