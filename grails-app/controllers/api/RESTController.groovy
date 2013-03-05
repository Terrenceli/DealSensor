package api
import ds.*

import grails.converters.*
import java.text.DateFormat
import java.text.SimpleDateFormat
import org.ccil.cowan.tagsoup.Parser

class RESTController {

	def index() {
		
	}

	def getAll(){
		render Deal.list() as XML
	}
	
	def getAll2(){
		def dealList = Deal.list()
		render(contentType: "text/xml"){
			deals{
				for(a in dealList){
					deal(id: a.id){
						title(a.title)
						iconUri(a.iconUri)
						location(latitude:a.supplier.latitude,longitude:a.supplier.longitude)
					}
				}
			}
		}
	}

	def getNearByLoc(){
		def latitude = params.latitude
		def longitude = params.longitude
		print latitude + "," + longitude
		
		Deal.list().each {
			
		}
	}
	
	def dist
	def getDealByRange2(){
		def deviceLongitude=params.longitude
		def deviceLatitude=params.latitude
		def range=params.range
		def unit=params.unit
		
		def dealList=[]
		
		Deal.getAll().each {
			this.dist=distance(deviceLatitude.toDouble(),deviceLongitude.toDouble(),it.supplier.latitude,it.supplier.longitude,unit)
			if(dist<range.toDouble()){
				dealList.add(it)
			}
		}
		render(contentType: "text/xml"){
			deals{
				for(a in dealList){
					deal(id: a.id){
						title(a.title)
						iconUri(a.iconUri)
						category(a.dealCategories)
						location(latitude:a.supplier.latitude,longitude:a.supplier.longitude)
						//range(this.dist)
					}
				}
			}
		}
	}
	
	def getDetailsById(){
		def iden=params.id
		def deal=Deal.findById(iden)
		println deal
		//render deal as XML
		render(contentType: "text/xml"){
			details(){
				id(deal.id)
				title(deal.title)
				iconUri(deal.iconUri)
				expireDate(deal.expireDate)
				dateCreated(deal.dateCreated)
				description(deal.description)
				categories(deal.dealCategories)
				supplier{
					supplierId(deal.supplier.id)
					name(deal.supplier.name)
					information(deal.supplier.description)
					phone(deal.supplier.phone)
					address(deal.supplier.address)
				}
				location(latitude:deal.supplier.latitude,longitude:deal.supplier.longitude)
				
				
			}
		}
	}
	
	
	def initSuppliers(){
		
		if(Supplier.count()==0){
		
		println "Start parsing...\n"
		
		//@Grab(group='org.ccil.cowan.tagsoup', module='tagsoup', version='1.2.1' )
		def tagsoupParser = new Parser()
		def slurper = new XmlSlurper(tagsoupParser)
		def url = "files/restaurants.html"
		def htmlParser = slurper.parse(url)
		
		def loc_tagsoupParser = new org.ccil.cowan.tagsoup.Parser()
		def loc_slurper = new XmlSlurper(loc_tagsoupParser)
		def loc_htmlParser
		
		
		println "***featured"
		def records=htmlParser.'**'.grep{it.@class== "featured_restaurant"}.each{
			def name=it.div[1].p[0].a[0].text()
			def tempUrl= it.div[1].p[0].a[0].'@href'
			//println tempUrl
			def phone=it.div[1].p[2].text()
			def addr=it.div[1].p[1].text().replace("\n","").split(",")
			def addrlvls=[]
			addr.each {
				addrlvls.add(it.trim())
			}
			def cuisinesTemp=it.div[1].p[3].text().replace("Cuisines:","").split(",")
			def cuisines=[]
			cuisinesTemp.each {
				cuisines.add(it.trim())
			}
			boolean succeed=false
		
			def newUrl=tempUrl.toString().replaceAll(" ", "%20")
			def lati
			def longi
			while(succeed==false){
				try{
					loc_htmlParser = loc_slurper.parse(newUrl.toString())
					succeed=true
					def location=loc_htmlParser.body."@onload"
					def temps=location.toString().split("\'")
					lati=temps[1]
					longi=temps[3]
		
					println name
					println "Cuisines: "+ cuisines
					println "address: "+ addrlvls
					println "phone: "+phone
					println "latitude: "+lati
					println "longitude: "+longi
					println ""
					
					def latiDou=Double.valueOf(lati)
					def longiDou=Double.valueOf(longi)
					println "after:"+longiDou
					def addrStr=addrlvls.toString();
					def supplierInstance
					try{
						supplierInstance=new Supplier(name:name,address:addrStr,password:"11111111",phone:phone,latitude:latiDou,longitude:longiDou)
						cuisines.each{
							def categoryInstance=new Category(name:it)
							categoryInstance.save(flush:true)
							def cateOfSup=Category.findByName(it)
							supplierInstance.addToCategories(cateOfSup)
						}
						
						String text = "2011-11-19T00:00:00.000-0500";
						DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
						Date parsed
						try {
							parsed = sdf.parse(text.trim());
						} catch (Exception e) {
							e.printStackTrace();
						}
						def dealInstance=new Deal(title: "Test Deal from "+name,description:"no description",expireDate:parsed)
						//dealInstance.save(failOnError: true, flush:true)
						supplierInstance.addToDeals(dealInstance).save(failOnError: true, flush:true)
						//supplierInstance.addToDeals(dealInstance)
						//supplierInstance.save(failOnError: true, flush:true)
						

					}catch(Exception e){
						println e.toString()				
					}
					
					
				}catch(Exception e){
					continue
				}
				
			}
		
		}
		
		println "***normal"
		
		def records2=htmlParser.'**'.grep{it.@class== "normal_restaurant"}.each{
			
				def name= it.div[0].div[0].p[0].a[0].text()
		
				def tempUrl=it.div[0].div[0].p[0].a[0].'@href'


				def newUrl=tempUrl.toString().replaceAll(" ", "%20")
				def phone=it.div[1].p[0].text()
		
				def addr=it.div[0].div[0].p[1].text().replace("\n","").split(",")
				def addrlvls=[]
				addr.each {
					addrlvls.add(it.trim())
				}
		
				def cuisinesTemp=it.div[1].p[1].text().replace("Cuisines:","").split(",")
				def cuisines=[]
				cuisinesTemp.each {
					cuisines.add(it.trim())
				}
		
				boolean succeed=false
				def loc_tagsoupParser2 = new org.ccil.cowan.tagsoup.Parser()
				def loc_slurper2 = new XmlSlurper(loc_tagsoupParser2)
				def loc_htmlParser2
				while(succeed==false){
					try{
						loc_htmlParser2 = loc_slurper2.parse(newUrl.toString())
						succeed=true
						def location=loc_htmlParser2.body."@onload"
						def temps=location.toString().split("\'")
						def lati=temps[1]
						def longi=temps[3]
		
						println name
						println "Cuisines: "+ cuisines
						println "address: "+ addrlvls
						println "phone: "+ phone
						println "latitude: "+ lati
						println "longitude: "+ longi
						println ""
						
						def latiDou=Double.valueOf(lati)
						def longiDou=Double.valueOf(longi)
						def addrStr=addrlvls.toString();
						def supplierInstance
						try{
							supplierInstance=new Supplier(name:name,address:addrStr,password:"11111111",phone:phone,latitude:latiDou,longitude:longiDou)
							cuisines.each{
								def categoryInstance=new Category(name:it)
								categoryInstance.save(flush:true)
								def cateOfSup=Category.findByName(it)
								supplierInstance.addToCategories(cateOfSup)
							}
							String text = "2011-11-19T00:00:00.000-0500";
							DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
							Date parsed
							try {
								parsed = sdf.parse(text.trim());
							} catch (Exception e) {
								e.printStackTrace();
							}
							def dealInstance=new Deal(title: "Test Deal from "+name,description:"no description",expireDate:parsed)
							//dealInstance.save(failOnError: true, flush:true)
							supplierInstance.addToDeals(dealInstance).save(failOnError: true, flush:true)
							//supplierInstance.addToDeals(dealInstance)
							//supplierInstance.save(failOnError: true, flush:true)
	
						}catch(Exception e){
							println e.toString()
						}
					
					}catch(Exception e){
						continue
					}
				}
			}
			
		

			println "Finish parsing..."
			render "Initialization Succeed!"
		}else{
		render "Already Initialized!"
		}
		
	}

	def getDealByRange(){
		def deviceLongitude=params.longitude
		def deviceLatitude=params.latitude
		def range=params.range
		def unit=params.unit
		
		def dealList=[]
		Deal.getAll().each {
			if(distance(deviceLatitude.toDouble(),deviceLongitude.toDouble(),it.supplier.latitude,it.supplier.longitude,unit)<range.toDouble()){
				dealList.add(it)
			}
		}
		render dealList as XML
		
	}

	private double distance(double lat1, double lon1, double lat2, double lon2, String unit) {
		double theta = lon1 - lon2;
		double dist = Math.sin(deg2rad(lat1)) * Math.sin(deg2rad(lat2)) + Math.cos(deg2rad(lat1)) * Math.cos(deg2rad(lat2)) * Math.cos(deg2rad(theta));

		dist = Math.acos(dist);
		dist = rad2deg(dist);

		dist = dist * 60 * 1.1515;

		if (unit == "K") {
			dist = dist * 1.609344;
		} else if (unit == "N") {
			dist = dist * 0.8684;
		}

		return (dist);
	}

	private double deg2rad(double deg) {
		return (deg * Math.PI / 180.0);
	}

	private double rad2deg(double rad) {
		return (rad * 180 / Math.PI);
	}
}

