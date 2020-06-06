<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../include/nav.jsp"%>



<div class="container">
	<div id="summernote">
		<p>Hello Summernote</p>
	</div>
	<script>
		$(document).ready(function() {
			$('#summernote').summernote({
				tabsize : 2,
				height : 300
			});
		});
	</script>
</div>

<%@ include file="../include/footer.jsp"%>