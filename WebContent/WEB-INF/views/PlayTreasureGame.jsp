<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Treasure Hunt!</title>
<jsp:include page="Formatting.jsp" />
<style>
#gameButtonGroup,#win-message,#activate-animation-directions {
	margin-left: 100px;
}

.gameButton {
	width: 50px;
	height: 50px;
	color: white;
	background-color: peru;
}

#win-pic {
	margin: 10px 10px 10px 30px;
}

#luck-message {
	font-size: xx-large;
	color: green;
	float: right;
	position: absolute;
	margin-left: 400px;
}

#numRows_error {
	margin-left: 413px;
}

#gameContainer {
	margin-left: auto;
	margin-right: auto;
}
</style>
</head>
<body>

	<jsp:include page="NavBar.jsp" />

	<h1>Find the treasure?</h1>

	<div class="container">
		<form action="" class="form-inline">
			<div class="form-group">
				<div class="input-group">
					<div class="input-group-addon">How many rows do you want to
						play with? (between 1 and 5)</div>
					<input type="number" name="numRows" class="form-control"></input>
				</div>
				<label class="error" for="numRows" id="numRows_error"
					style="color: white;">This field is required.</label>
			</div>
			<button type="submit" name="formSubmit" value="true"
				class="btn btn-primary" id="startGameButton">Let's play!</button>
		</form>
	</div>
	<div style="clear: both;"></div>
	<div id="gameContainer">
		<div class="btn-group" id="gameButtonGroup" data-game-active="false"
			data-game-won="false" style="float: left"></div>
	</div>

	<script>
        function generateButtonsFromServletData(generatedArray, numRows) {
            var buttonContentArray = generatedArray;
            
            if ($("#gameButtonGroup").attr("data-game-active") === "true") {
                $("#gameButtonGroup").empty();
                $("#win-message").remove();
                $("#win-pic").remove();
                $("#activate-animation-directions").remove();
                $("#luck-message").remove();
                $("#gameButtonGroup").attr("data-game-won", "false");
                $(".after-game").remove();
            } else {
                $("#gameButtonGroup").attr("data-game-active", "true");
            }
            
            buttonContentArray.forEach(function(element, index) {
                if (index % numRows === 0) {
                    $("#gameButtonGroup").append('<br>');
                }
                var button = $('<button type="button" class="gameButton"></button>').text(element);
                button.attr("data-index", index);
                $("#gameButtonGroup").append(button);
            });
        }
        
        function changeButton(button, color, text){
            button.style.backgroundColor = color;
            button.innerHTML = text;
        }
        
        function animateImage(){
        	var luckMessageMargin = $("#luck-message").css("margin-top");
            $("#win-pic").animate({
                left: '550px',
                height: '+=35px',
                width: '+=35px',
                opacity: '0.6'
            }, "slow", function(){
            	$(this).animate({
                    left: '10px',
                    height: '-=35px',
                    width: '-=35px',
                    opacity: 1
                }, "slow");
            })
            $("#luck-message").animate({
                opacity: 1
            }, "fast");
            $("#luck-message").animate({
                opacity: 0,
                marginTop: '0px'
            }, "slow");
            $("#luck-message").animate({
				marginTop: luckMessageMargin
            });
        }
        
        
        $(document).on('click', '#win-pic', animateImage);
        
        function winEvent(numRows){
        	var separateAfterGame = '<br style="clear:both" class="after-game"><br class="after-game">';
//          var winMessage = '<p id="win-message">You found the treasure! :D</p>';
//             var activateAnimationDirections = '<p id="activate-animation-directions" style="display:none">Click the leprachaun for luck!</p>';
//             var winPic = '<img class="img-circle" id="win-pic" src="resources/treasure.png" style="position:absolute">';
			var winContainer = '<p id="win-message"></p>';
			var winMessage = "You found the treasure! :D";
			var animationDirectionsContainer = '<p id="animation-directions"></p>';
			var animationDirections = "Click the leprachaun for luck!";
			var winPic = '<img class="img-circle" id="win-pic">';
            var luckContainer = $('<p id="luck-message"></p>');
            var luckMessage = '+10 Luck recieved!';
            
//             var luckMessage = '<p id="luck-message" style="opacity:0;float:left;margin-top:220px;">+10 Luck recieved!</p>';
            $("#gameContainer").append(luckContainer, separateAfterGame, winContainer, animationDirectionsContainer)
            .after(winPic);

            $("#win-message").text(winMessage);
            
            $("#animation-directions").css({
            	'display': 'none'
            }).text(animationDirections);
            
            $('#win-pic').css({
            	'position': 'absolute'
            }).attr('src', 'resources/treasure.png');
            
            $('#luck-message').css({
            	'opacity': 0,
            	'float': 'left',
            	'margin-top': ((numRows * 100) - 50)
            }).text(luckMessage);
            
            $("#activate-animation-directions").fadeIn("30000");
        }
        
  		$("form").submit(function(event) {
			event.preventDefault();
			var numRows = $("input[name=numRows]").val();
			if (numRows === "") {
				$("label#numRows_error").css("color", "black");
				$("input[name=numRows]").focus();
				return false;
			}
			$.ajax({
 				url: "TreasureGameController?action=start&index=null&numRows=" + numRows,
             	success: function(data) {
            		generateButtonsFromServletData(data.buttonText, numRows);
             	},
             	error: function(data) {
             	 	alert("Unable to contact server to control game\n" + data);
             	}
			});
		});

		$(document).on("click", ".gameButton", function(event) {
         var button = event.target;
         var buttonIndex = event.target.dataset.index;
         $.ajax({
            url: "TreasureGameController?action=move&index=" + buttonIndex,
            success: function(data) {
                if (data.won === true) {
                    changeButton(button, "green", "!!");
                    if($("#gameButtonGroup").attr("data-game-won") === "false") {
                         winEvent(data.numRows);
                         $("#gameButtonGroup").attr("data-game-won", "true");
                    }
                } else {
                    changeButton(button, "brown", "x");
                }
            },
            failure: function(data) {
         	   alert("Unable to contact server");
            }
     	});
		});
	</script>
</body>