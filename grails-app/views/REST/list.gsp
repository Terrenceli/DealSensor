
<%@ page import="ds.Deal" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'deal.label', default: 'Deal')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-deal" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-deal" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
				<thead>
					<tr>
					
						<g:sortableColumn property="dateCreated" title="${message(code: 'deal.dateCreated.label', default: 'Date Created')}" />
					
						<g:sortableColumn property="description" title="${message(code: 'deal.description.label', default: 'Description')}" />
					
						<g:sortableColumn property="expireDate" title="${message(code: 'deal.expireDate.label', default: 'Expire Date')}" />
					
						<g:sortableColumn property="iconUri" title="${message(code: 'deal.iconUri.label', default: 'Icon Uri')}" />
					
						<g:sortableColumn property="numberofHits" title="${message(code: 'deal.numberofHits.label', default: 'Numberof Hits')}" />
					
						<th><g:message code="deal.supplier.label" default="Supplier" /></th>
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${dealInstanceList}" status="i" var="dealInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${dealInstance.id}">${fieldValue(bean: dealInstance, field: "dateCreated")}</g:link></td>
					
						<td>${fieldValue(bean: dealInstance, field: "description")}</td>
					
						<td><g:formatDate date="${dealInstance.expireDate}" /></td>
					
						<td>${fieldValue(bean: dealInstance, field: "iconUri")}</td>
					
						<td>${fieldValue(bean: dealInstance, field: "numberofHits")}</td>
					
						<td>${fieldValue(bean: dealInstance, field: "supplier")}</td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${dealInstanceTotal}" />
			</div>
		</div>
	</body>
</html>
