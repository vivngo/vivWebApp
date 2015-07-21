casper.test.begin('NavBar.jsp tests', 5, function(test) {
	casper.start('http://localhost:8080/vivWebApp/form', function() {
		test.assertHttpStatus(200);
		test.assertExists('.navbar');
		test.assertExists('#userGreeting');
		test.assertExists('#weatherInfo');

	 }).waitForText('#weatherInfo', function() {
		var weatherInfoText = this.getElementInfo('#weatherInfo').text;
		test.assertMatch(weatherInfoText, /^The weather today in Irving/i,
			'Correct location for weather');
		
	}).run(function() {
		test.done();

	});

});