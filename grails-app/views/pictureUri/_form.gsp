<%@ page import="ds.PictureUri" %>



<div class="fieldcontain ${hasErrors(bean: pictureUriInstance, field: 'deal', 'error')} required">
	<label for="deal">
		<g:message code="pictureUri.deal.label" default="Deal" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="deal" name="deal.id" from="${ds.Deal.list()}" optionKey="id" required="" value="${pictureUriInstance?.deal?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: pictureUriInstance, field: 'uri', 'error')} ">
	<label for="uri">
		<g:message code="pictureUri.uri.label" default="Uri" />
		
	</label>
	<g:textField name="uri" value="${pictureUriInstance?.uri}"/>
</div>

