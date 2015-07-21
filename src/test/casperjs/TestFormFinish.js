casper.test.begin('FormFinish.jsp tests', 5, function(test) {
	casper.start();

	casper.thenOpen('http://localhost:8080/vivWebApp/form', {
		method: 'post',
		data: {
			'firstName': 'Jane',
			'lastName': 'Doe',
			'color': 'green',
			'formSubmit': 'true'
		}
	});

	casper.then(function() {
		test.assertHttpStatus(200);
		
		var displayNameText = this.getElementInfo('span#displayName').text;
		test.assertEquals(displayNameText, 'Jane Doe', 'Correct name displayed');

		var displayNameColor = this.evaluate(function() {
			return document.querySelector('span#displayName').style.backgroundColor;
		});
		test.assertEquals(displayNameColor, 'green', 'Correct color displayed');

		var animalImage = this.getElementInfo('img');
		test.assertNotEquals(animalImage, '', 'Resource provided for image');

		var headerName = this.getElementInfo('#userGreeting > p:first-child').text;
		test.assertEquals(headerName, 'Hi Jane Doe', 'Correct name displayed in header');

	});

	casper.run(function() {
		test.done();

	});

});