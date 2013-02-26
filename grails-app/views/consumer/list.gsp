
<%@ page import="ds.Consumer" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'consumer.label', default: 'Consumer')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-consumer" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-consumer" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
				<thead>
					<tr>
					
						<g:sortableColumn property="deviceId" title="${message(code: 'consumer.deviceId.label', default: 'Device Id')}" />
					
						<g:sortableColumn property="dailyNumberofQueries" title="${message(code: 'consumer.dailyNumberofQueries.label', default: 'Daily Numberof Queries')}" />
					
						<g:sortableColumn property="lastLatitude" title="${message(code: 'consumer.lastLatitude.label', default: 'Last Latitude')}" />
					
						<g:sortableColumn property="lastLongitude" title="${message(code: 'consumer.lastLongitude.label', default: 'Last Longitude')}" />
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${consumerInstanceList}" status="i" var="consumerInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${consumerInstance.id}">${fieldValue(bean: consumerInstance, field: "deviceId")}</g:link></td>
					
						<td>${fieldValue(bean: consumerInstance, field: "dailyNumberofQueries")}</td>
					
						<td>${fieldValue(bean: consumerInstance, field: "lastLatitude")}</td>
					
						<td>${fieldValue(bean: consumerInstance, field: "lastLongitude")}</td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${consumerInstanceTotal}" />
			</div>
		</div>
	</body>
</html>
