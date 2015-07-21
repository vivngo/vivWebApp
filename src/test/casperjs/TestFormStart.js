casper.test.begin('FormStart.jsp tests', 5, function(test) {
	casper.start('http://localhost:8080/vivWebApp/form', function() {
		test.assertHttpStatus(200);
		var headerText = this.getElementInfo('h1').text;
		test.assertMatch(headerText, /^hello/i, 'Header greets user with "Hello"');

	});

	casper.then(function() {
		test.assertExists('input[name="firstName"]', 'Field for first name exists on form');
		test.assertExists('input[name="lastName"]', 'Field for last name exists on form');
		test.assertExists('select[name="color"]', 'Field for color exists on form');

	});

	casper.run(function() {
		test.done();

	});

});