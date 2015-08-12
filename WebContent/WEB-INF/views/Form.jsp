<div class="container">
	<form action="#" method="POST" class="form-inline">
	
		<div class="form-group">
			<label for="nameInput">Who are you?</label> <br>
			<div class="input-group">
				<div class="input-group-addon">First name</div>
				<input type="text" name="firstName" placeholder="First name"
					class="form-control" value="John"></input>
			</div>
			<br>
			<div class="input-group">
				<div class="input-group-addon">Last name</div>
				<input type="text" name="lastName" placeholder="Last name"
					class="form-control" value="Doe"></input>
			</div>
		</div>
		<br> <br>
		
		<div class="form-group">
			<label for="colorInput">Pick a color, any color.</label><br> <select
				name="color" class="form-control">
				<option value="red">Red</option>
				<option value="orange">Orange</option>
				<option value="yellow">Yellow</option>
				<option value="green">Green</option>
				<option value="blue">Blue</option>
				<option value="purple">Purple</option>
			</select>
		</div>
		<br> <br>
		
		<button type="submit" name="formSubmit" value="true"
			class="btn btn-primary">Let's do this!</button>
	</form>
</div>