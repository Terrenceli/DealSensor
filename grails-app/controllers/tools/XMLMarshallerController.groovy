package tools

import grails.converters.XML
import ds.*

import org.codehaus.groovy.grails.web.converters.exceptions.ConverterException;
import org.codehaus.groovy.grails.web.converters.marshaller.ObjectMarshaller

class XMLMarshallerController implements ObjectMarshaller<XML>{
	
	static configuration = "dealItem"

	@Override
	public boolean supports(Object object) {
		Deal.isAssignableFrom(object.class)
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void marshalObject(Object object, XML converter)
	throws ConverterException {
		converter.attribute("id", object.id)
		converter.attribute("title", object.title)
		converter.attribute("iconUri", object.iconUri)
		converter.attribute("longitude", object.supplier.longitude)
		converter.attribute("latitude", object.supplier.latitude)
	}
	// TODO Auto-generated method stub

}
