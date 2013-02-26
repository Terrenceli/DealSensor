<%@ page import="ds.Consumer" %>



<div class="fieldcontain ${hasErrors(bean: consumerInstance, field: 'deviceId', 'error')} required">
	<label for="deviceId">
		<g:message code="consumer.deviceId.label" default="Device Id" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="deviceId" required="" value="${consumerInstance?.deviceId}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: consumerInstance, field: 'dailyNumberofQueries', 'error')} required">
	<label for="dailyNumberofQueries">
		<g:message code="consumer.dailyNumberofQueries.label" default="Daily Numberof Queries" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="dailyNumberofQueries" type="number" value="${consumerInstance.dailyNumberofQueries}" required=""/>
</div>

<div class="fieldcontain ${hasErrors(bean: consumerInstance, field: 'lastLatitude', 'error')} required">
	<label for="lastLatitude">
		<g:message code="consumer.lastLatitude.label" default="Last Latitude" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="lastLatitude" value="${fieldValue(bean: consumerInstance, field: 'lastLatitude')}" required=""/>
</div>

<div class="fieldcontain ${hasErrors(bean: consumerInstance, field: 'lastLongitude', 'error')} required">
	<label for="lastLongitude">
		<g:message code="consumer.lastLongitude.label" default="Last Longitude" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="lastLongitude" value="${fieldValue(bean: consumerInstance, field: 'lastLongitude')}" required=""/>
</div>

