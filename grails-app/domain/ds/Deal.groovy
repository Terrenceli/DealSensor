package ds

class Deal {
	static expose='deal'
	
	static api = [
		excludedFields: [ "dateCreated","description" ],
		list : { Deal.list() },
		count: { params -> Deal.count() }
	  ]
	
	long id
	String title
	String description
	Date dateCreated
	Date expireDate
	String iconUri
	int numberofHits=0
	
	static belongsTo=[supplier:Supplier]
	static hasMany=[pictureUris:PictureUri,dealCategories:DealCategory]

    static constraints = {
		title blank:false
		description nullable:true
		expireDate blank:false
		iconUri nullable:true
		expireDate nullable:true
    }
	/*
	static marshalling={
		xml {
			export{
				attribute 'id'
				attribute 'title'
				virtual{
					supplier { deal, xml->
						xml.startNode(deal.supplier)
						xml.lookupObjectMarshaller(supplier).mashalObject(supplier,xml)
						xml.end()
					}
				}
			}
		}
	}*/
}
