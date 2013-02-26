<%@ page import="ds.Deal" %>



<div class="fieldcontain ${hasErrors(bean: dealInstance, field: 'title', 'error')} required">
	<label for="title">
		<g:message code="deal.title.label" default="Title" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="title" required="" value="${dealInstance?.title}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: dealInstance, field: 'description', 'error')} ">
	<label for="description">
		<g:message code="deal.description.label" default="Description" />
		
	</label>
	<g:textField name="description" value="${dealInstance?.description}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: dealInstance, field: 'expireDate', 'error')} ">
	<label for="expireDate">
		<g:message code="deal.expireDate.label" default="Expire Date" />
		
	</label>
	<g:datePicker name="expireDate" precision="day"  value="${dealInstance?.expireDate}" default="none" noSelection="['': '']" />
</div>

<div class="fieldcontain ${hasErrors(bean: dealInstance, field: 'iconUri', 'error')} ">
	<label for="iconUri">
		<g:message code="deal.iconUri.label" default="Icon Uri" />
		
	</label>
	<g:textField name="iconUri" value="${dealInstance?.iconUri}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: dealInstance, field: 'dealCategories', 'error')} ">
	<label for="dealCategories">
		<g:message code="deal.dealCategories.label" default="Deal Categories" />
		
	</label>
	<g:select name="dealCategories" from="${ds.DealCategory.list()}" multiple="multiple" optionKey="id" size="5" value="${dealInstance?.dealCategories*.id}" class="many-to-many"/>
</div>

<div class="fieldcontain ${hasErrors(bean: dealInstance, field: 'numberofHits', 'error')} required">
	<label for="numberofHits">
		<g:message code="deal.numberofHits.label" default="Numberof Hits" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="numberofHits" type="number" value="${dealInstance.numberofHits}" required=""/>
</div>

<div class="fieldcontain ${hasErrors(bean: dealInstance, field: 'pictureUris', 'error')} ">
	<label for="pictureUris">
		<g:message code="deal.pictureUris.label" default="Picture Uris" />
		
	</label>
	
<ul class="one-to-many">
<g:each in="${dealInstance?.pictureUris?}" var="p">
    <li><g:link controller="pictureUri" action="show" id="${p.id}">${p?.encodeAsHTML()}</g:link></li>
</g:each>
<li class="add">
<g:link controller="pictureUri" action="create" params="['deal.id': dealInstance?.id]">${message(code: 'default.add.label', args: [message(code: 'pictureUri.label', default: 'PictureUri')])}</g:link>
</li>
</ul>

</div>

<div class="fieldcontain ${hasErrors(bean: dealInstance, field: 'supplier', 'error')} required">
	<label for="supplier">
		<g:message code="deal.supplier.label" default="Supplier" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="supplier" name="supplier.id" from="${ds.Supplier.list()}" optionKey="id" required="" value="${dealInstance?.supplier?.id}" class="many-to-one"/>
</div>

