<div id="tabs-1" class="${configureTabs.contains('tabs-1') ? '' : 'hide'}">
	<label for="messageText">Enter message</label><br />
	<g:textArea name="messageText" value="${messageText}" rows="5" cols="40"/>
</div>

<script>
	$("#messageText").live("blur", function() {
		var value = $(this).val();
		$("#confirm-message-text").html(value)
	})
</script>