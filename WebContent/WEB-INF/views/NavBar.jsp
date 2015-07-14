<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<nav class="navbar navbar-inverse">
	<div class="container-fluid">
		<div class="navbar-header">
			<button type="button" class="navbar-toggle collapsed"
				data-toggle="collapse" data-target="#my-navbar-collapse">
				<span class="sr-only">Toggle navigation</span> 
				<span class="icon-bar"></span>
				<span class="icon-bar"></span>
				<span class="icon-bar"></span>
			</button>
			<a class="navbar-brand" href="form">Vivien's Web App</a>
		</div>
		<div class="collapse navbar-collapse" id="my-navbar-collapse">
			<ul class="nav navbar-nav">
				<li><a href="form">Home</a></li>
				<li class="divider"></li>
				<li><a href="treasuregame">Hunt for Treasure</a></li>
			</ul>
		</div>
	</div>
</nav>

<div id="userGreeting" align="right" style="margin-right: 50px; float: right;">
	<p>Hi ${userName}</p>
	<p>Happy ${dayOfWeek}!</p>
	<p id="weatherInfo"></p>
</div>

<script>
	if (${inputContainsScript}) {
    	alert("You put in a script");
	}
	
	$.ajax({
		url: "http://api.openweathermap.org/data/2.5/weather?zip=75038,us",
		success: function(data) {
			var weatherInfo = "The weather today in " + data.name + ": " + data.weather[0].description + "";
			$("#weatherInfo").append(weatherInfo);
		},
		error: function(data) {
			alert("Unable to reach weather API");
		}
	});

	//test test

</script>