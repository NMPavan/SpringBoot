$(document).ready(function() {
	$('#ErrorCodeName').hide();
	$('#ErrorCodedData').hide();
	$('#ErrorCodeNote').hide();

	var ErrorCodeName = false;
	var ErrorCodeNote = false;
	var ErrorCodeData = false;

	function validate_errorCodeName() {
		var expression = /^[A-Za-z0-9]{4,12}$/;
		var nameValue = $('#name').val();

		if (nameValue === '') {
			$('#ErrorCodeName').show();
			$('#ErrorCodeName').html('Please Enter <b> Name</b>');
			$('#ErrorCodeName').css("color", "red");
		} else if (!expression.test(nameValue)) {
			$('#ErrorCodeName').show();
			$('#ErrorCodeName').html('Please Enter Valid<b> Name</b>');
			$('#ErrorCodeName').css("color", "red");
		} else {
			console.log("test");
			$.ajax({
				url: "checkname",
				data: { "name": nameValue },
				success: function(rspText) {
					console.log("text" + rspText);
					if (rspText != "") {
						$('#ErrorCodeName').show();
						$('#ErrorCodeName').html(rspText);
						$('#ErrorCodeName').css("color", "red");
					} else {
						$('#ErrorCodeName').hide();
						ErrorCodeName = true;
					}
				}

			});
		}

		return ErrorCodeName;

	}


	function validate_errorCodeData() {
		var expression = /^[A-Za-z0-9]{4,12}$/;
		var codeValue = $('#code').val();

		if (codeValue === '') {
			$('#ErrorCodedData').show();
			$('#ErrorCodedData').html('Please Enter <b> Code</b>');
			$('#ErrorCodedData').css("color", "red");
		} else if (!expression.test(codeValue)) {
			$('#ErrorCodedData').show();
			$('#ErrorCodedData').html('Please Enter Valid<b> Code</b>');
			$('#ErrorCodedData').css("color", "red");
		} else {
			console.log("test");
			$.ajax({
				url: "checkcode",
				data: { "code": codeValue },
				success: function(rspText) {
					console.log("text" + rspText);
					if (rspText != "") {
						console.log("abbabab" + rspText);
						$('#ErrorCodedData').show();
						$('#ErrorCodedData').html(rspText);
						$('#ErrorCodedData').css("color", "red");
					} else {
						$('#ErrorCodedData').hide();
						ErrorCodeData = true;
					}
				}

			});
		}

		return ErrorCodeData;

	}

	function validate_errorCodeNote() {
		var expression = /^[A-Za-z0-9/s]{4,250}$/;
		var noteValue = $('#note').val();

		if (noteValue === '') {
			$('#ErrorCodeNote').show();
			$('#ErrorCodeNote').html('Please Enter <b> Note</b>');
			$('#ErrorCodeNote').css("color", "red");
		} else if (!expression.test(noteValue)) {
			$('#ErrorCodeNote').show();
			$('#ErrorCodeNote').html('Please Enter Valid<b> Note</b>');
			$('#ErrorCodeNote').css("color", "red");
		} else {
			$('#ErrorCodeNote').hide();
			ErrorCodeNote = true;
		}

		return ErrorCodeNote;

	}

	$('#name').keyup(function() {
		return validate_errorCodeName();
	});

	$('#code').keyup(function() {
		return validate_errorCodeData();
	});
	$('#note').keyup(function() {
		return validate_errorCodeNote();
	});


	$('#specializationForm').submit(function() {
		validate_errorCodeName();
		validate_errorCodeData();
		validate_errorCodeNote();


console.log("ErrorCodeName"+ ErrorCodeName);
console.log("ErrorCodeData"+ ErrorCodeData);
console.log("ErrorCodeData"+ ErrorCodeData);

		if (ErrorCodeName && ErrorCodeData && ErrorCodeData) return true;
		else return false;
	});


});
