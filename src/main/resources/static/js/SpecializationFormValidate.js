	$(document).ready(function(){
            //1. hide error section
            $("#ErrorCodeName").hide();
           $('#ErrorCodedData').hide();
	       $('#ErrorCodeNote').hide();

            //2. define error variable
            var specCodeError = false;
            var specNameError = false;
            var specNoteError = false;

            //3. validate function
            function validate_specCode() {
                var val = $("#code").val();
                var exp = /^[A-Z]{4,10}$/;
                if(val=='') {
                    $("#ErrorCodedData").show();
                    $("#ErrorCodedData").html("*<b>Code</b> Can not be empty")
                    $("#ErrorCodedData").css('color','red');
                    specCodeError = false;
                } else if(!exp.test(val)) {
                    $("#ErrorCodedData").show();
                    $("#ErrorCodedData").html("*<b>Code</b> must be 4-12 chars only")
                    $("#ErrorCodedData").css('color','red');
                    specCodeError = false;
                } else {
	
	                var Id = 0; //for register
	                if($("#Id").val()!=undefined) { //edit page
						specCodeError = true;
						Id = $("#Id").val();
					}
                    $.ajax({
						url:'/spec/checkCode',
						data: {"code": val,"Id":Id},
						success:function(resTxt) {
							if(resTxt!='') {
								$("#ErrorCodedData").show();
                   				$("#ErrorCodedData").html(resTxt);
                    			$("#ErrorCodedData").css('color','red');
                    			specCodeError = false;
							} else {
								$("#ErrorCodedData").hide();
								specCodeError = true;
							}
						}
						
					});
					//$("#ErrorCodedData").hide();
					//specCodeError = true;
                }
                return specCodeError;
            }

            function validate_specName() {
                var val = $("#name").val();
                var exp = /^[A-Za-z0-9\s\.]{4,60}$/;
                if(val=='') {
                    $("#ErrorCodeName").show();
                    $("#ErrorCodeName").html("*<b>Name</b> Can not be empty")
                    $("#ErrorCodeName").css('color','red');
                    specNameError = false;
                } else if(!exp.test(val)) {
                    $("#ErrorCodeName").show();
                    $("#ErrorCodeName").html("*<b>Name</b> must be 4-25 chars")
                    $("#ErrorCodeName").css('color','red');
                    specNameError = false;
                } else {
	                var Id = 0; //for register
	                if($("#Id").val()!=undefined) { //edit page
						specCodeError = true;
						Id = $("#Id").val();
						console.log("Id"+ Id);
					}
					console.log("inside else" + Id);
					console.log("code"+ val);
                    $.ajax({
						url:'/spec/checkname',
						data: {"name": val,"Id":Id},
						success:function(resTxt) {
							if(resTxt!='') {
								$("#ErrorCodeName").show();
                   				$("#ErrorCodeName").html(resTxt);
                    			$("#ErrorCodeName").css('color','red');
                    			specCodeError = false;
							} else {
								console.log("inside else??");
								$("#ErrorCodeName").hide();
								specCodeError = true;
							}
						}
						
					});
	
                    $("#ErrorCodeName").hide();
                    specNameError = true;
                }
                
                return specNameError;
            }

            function validate_specNote() {
                var val = $("#note").val();
                var exp = /^[A-Za-z0-9\s\.\-\,\']{10,250}$/;
                if(val=='') {
                    $("#ErrorCodeNote").show();
                    $("#ErrorCodeNote").html("*<b>Note</b> Can not be empty")
                    $("#ErrorCodeNote").css('color','red');
                    specNoteError = false;
                } else if(!exp.test(val)) {
                    $("#ErrorCodeNote").show();
                    $("#ErrorCodeNote").html("*<b>Note</b> can have 10 to 150 chars[A-Za-z0-9.,-(space)]")
                    $("#ErrorCodeNote").css('color','red');
                    specNoteError = false;
                } else {
                    $("#ErrorCodeNote").hide();
                    specNoteError = true;
                }
                 return specNoteError;
            }

            //4. action-event
            $("#code").keyup(function(){
                $(this).val($(this).val().toUpperCase());
                validate_specCode();
            });

            $("#name").keyup(function(){
                validate_specName();
            });

            $("#note").keyup(function(){
                validate_specNote();
            });

            //5. on submit
            $("#specializationForm").submit(function(){
                validate_specCode();
                validate_specName();
                validate_specNote();

                if(specCodeError 
                && specNameError 
                && specNoteError)
                    return true;
                else return false;
            });
        });