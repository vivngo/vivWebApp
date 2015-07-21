casper.test.begin('PlayTreasureGame.jsp tests', 7, function(test) {
	casper.start('http://localhost:8080/vivWebApp/treasuregame', function() {
		test.assertHttpStatus(200);
		this.fill('form', {
			'numRows': '2'
		}, true);

	});

	casper.waitForSelector('.gameButton', function() {
		test.assertDoesntExist('#win-message', 'Win message not generated prematurely');

		var gameButtons = this.getElementsInfo('.gameButton');
		test.assertEquals(gameButtons.length, 4, 'Correct number of buttons generated');

		casper.capture('before-clicks.png');

	});

	casper.then(function() {
		clickGameButtons(this, 4);

	});

	casper.waitForSelectorTextChange('.gameButton:nth-of-type(4)', function() {
		var gameButtonColors = [];

  		for (var num = 1; num <= 4; num++) {
	  		var mySelector = '.gameButton:nth-of-type(' + num + ')';

			var color = this.evaluate(function(selector) {
				return document.querySelector(selector).style.backgroundColor;
			}, mySelector);

			gameButtonColors.push(color);
		}

		var greenButtons = countElementsMatching(gameButtonColors, 'green');
		var redButtons = countElementsMatching(gameButtonColors, 'rgb(165, 42, 42)');
		test.assertEquals(greenButtons, 1, 'Correct number of green buttons');
		test.assertEquals(redButtons, 3, 'Correct number of red buttons');

	});

	casper.then(function() {
		test.assertExists('#win-message', 'Win message properly generated');
		test.assertExists('#luck-message', 'Luck message properly generated');

		casper.capture('after-clicks.png');
		
	});

	casper.run(function() {
		test.done();

	});
});

function clickGameButtons(capser, numButtons) {
	for (var i = 1; i <= numButtons; i++) {
		var selector = '.gameButton:nth-of-type(' + i + ')';
		casper.click(selector);
	}
}

function getGameButtonColor(casper, doc, num) {
	var buttonColor = casper.evaluate(function() {
		var selector = '.gameButton:nth-of-type(' + num + ')';
		var color = doc.querySelector(selector).style.backgroundColor;
		return color;
	});
	return buttonColor;
}

function countElementsMatching(array, compare) {
	var matches =  array.reduce(function(count, element) {
		if (element === compare) {
			return count + 1;
		} else {
			return count;
		}
	}, 0);
	return matches;
}