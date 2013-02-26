
<%@ page import="ds.Consumer" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'consumer.label', default: 'Consumer')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-consumer" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-consumer" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list consumer">
			
				<g:if test="${consumerInstance?.deviceId}">
				<li class="fieldcontain">
					<span id="deviceId-label" class="property-label"><g:message code="consumer.deviceId.label" default="Device Id" /></span>
					
						<span class="property-value" aria-labelledby="deviceId-label"><g:fieldValue bean="${consumerInstance}" field="deviceId"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${consumerInstance?.dailyNumberofQueries}">
				<li class="fieldcontain">
					<span id="dailyNumberofQueries-label" class="property-label"><g:message code="consumer.dailyNumberofQueries.label" default="Daily Numberof Queries" /></span>
					
						<span class="property-value" aria-labelledby="dailyNumberofQueries-label"><g:fieldValue bean="${consumerInstance}" field="dailyNumberofQueries"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${consumerInstance?.lastLatitude}">
				<li class="fieldcontain">
					<span id="lastLatitude-label" class="property-label"><g:message code="consumer.lastLatitude.label" default="Last Latitude" /></span>
					
						<span class="property-value" aria-labelledby="lastLatitude-label"><g:fieldValue bean="${consumerInstance}" field="lastLatitude"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${consumerInstance?.lastLongitude}">
				<li class="fieldcontain">
					<span id="lastLongitude-label" class="property-label"><g:message code="consumer.lastLongitude.label" default="Last Longitude" /></span>
					
						<span class="property-value" aria-labelledby="lastLongitude-label"><g:fieldValue bean="${consumerInstance}" field="lastLongitude"/></span>
					
				</li>
				</g:if>
			
			</ol>
			<g:form>
				<fieldset class="buttons">
					<g:hiddenField name="id" value="${consumerInstance?.id}" />
					<g:link class="edit" action="edit" id="${consumerInstance?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
