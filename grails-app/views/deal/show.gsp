
<%@ page import="ds.Deal" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'deal.label', default: 'Deal')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-deal" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-deal" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list deal">
			
				<g:if test="${dealInstance?.title}">
				<li class="fieldcontain">
					<span id="title-label" class="property-label"><g:message code="deal.title.label" default="Title" /></span>
					
						<span class="property-value" aria-labelledby="title-label"><g:fieldValue bean="${dealInstance}" field="title"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${dealInstance?.description}">
				<li class="fieldcontain">
					<span id="description-label" class="property-label"><g:message code="deal.description.label" default="Description" /></span>
					
						<span class="property-value" aria-labelledby="description-label"><g:fieldValue bean="${dealInstance}" field="description"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${dealInstance?.expireDate}">
				<li class="fieldcontain">
					<span id="expireDate-label" class="property-label"><g:message code="deal.expireDate.label" default="Expire Date" /></span>
					
						<span class="property-value" aria-labelledby="expireDate-label"><g:formatDate date="${dealInstance?.expireDate}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${dealInstance?.iconUri}">
				<li class="fieldcontain">
					<span id="iconUri-label" class="property-label"><g:message code="deal.iconUri.label" default="Icon Uri" /></span>
					
						<span class="property-value" aria-labelledby="iconUri-label"><g:fieldValue bean="${dealInstance}" field="iconUri"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${dealInstance?.dateCreated}">
				<li class="fieldcontain">
					<span id="dateCreated-label" class="property-label"><g:message code="deal.dateCreated.label" default="Date Created" /></span>
					
						<span class="property-value" aria-labelledby="dateCreated-label"><g:formatDate date="${dealInstance?.dateCreated}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${dealInstance?.dealCategories}">
				<li class="fieldcontain">
					<span id="dealCategories-label" class="property-label"><g:message code="deal.dealCategories.label" default="Deal Categories" /></span>
					
						<g:each in="${dealInstance.dealCategories}" var="d">
						<span class="property-value" aria-labelledby="dealCategories-label"><g:link controller="dealCategory" action="show" id="${d.id}">${d?.encodeAsHTML()}</g:link></span>
						</g:each>
					
				</li>
				</g:if>
			
				<g:if test="${dealInstance?.numberofHits}">
				<li class="fieldcontain">
					<span id="numberofHits-label" class="property-label"><g:message code="deal.numberofHits.label" default="Numberof Hits" /></span>
					
						<span class="property-value" aria-labelledby="numberofHits-label"><g:fieldValue bean="${dealInstance}" field="numberofHits"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${dealInstance?.pictureUris}">
				<li class="fieldcontain">
					<span id="pictureUris-label" class="property-label"><g:message code="deal.pictureUris.label" default="Picture Uris" /></span>
					
						<g:each in="${dealInstance.pictureUris}" var="p">
						<span class="property-value" aria-labelledby="pictureUris-label"><g:link controller="pictureUri" action="show" id="${p.id}">${p?.encodeAsHTML()}</g:link></span>
						</g:each>
					
				</li>
				</g:if>
			
				<g:if test="${dealInstance?.supplier}">
				<li class="fieldcontain">
					<span id="supplier-label" class="property-label"><g:message code="deal.supplier.label" default="Supplier" /></span>
					
						<span class="property-value" aria-labelledby="supplier-label"><g:link controller="supplier" action="show" id="${dealInstance?.supplier?.id}">${dealInstance?.supplier?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
			</ol>
			<g:form>
				<fieldset class="buttons">
					<g:hiddenField name="id" value="${dealInstance?.id}" />
					<g:link class="edit" action="edit" id="${dealInstance?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
