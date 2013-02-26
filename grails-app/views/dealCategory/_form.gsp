<%@ page import="ds.DealCategory" %>



<div class="fieldcontain ${hasErrors(bean: dealCategoryInstance, field: 'name', 'error')} ">
	<label for="name">
		<g:message code="dealCategory.name.label" default="Name" />
		
	</label>
	<g:textField name="name" value="${dealCategoryInstance?.name}"/>
</div>

