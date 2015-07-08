<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Result From Form</title>
<jsp:include page="Formatting.jsp" />

</head>
<body>
	<jsp:include page="NavBar.jsp" />
	<div id="after-nav">
		<h1>I think I know you!</h1>
		<p>${requestScope.message}
			<span id="displayName">${requestScope.firstName}
				${requestScope.lastName}</span>!
		</p>

		<h2>Did you know...</h2>
		<p>${requestScope.factText}</p>
		<img src="${factPic}" class="img-circle"
			alt="What an interesting creature!"></img>
	</div>

	<script>
		$("#displayName").css("background-color", "${color}");
		if ("${color}" === "purple" || "${color}" === "blue"
				|| "${color}" === "green" || "${color}" === "red") {
			$("#displayName").css("color", "white");
		}
	</script>
</body>