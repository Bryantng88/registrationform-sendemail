var fileArr;
var imageArr;
var accountTypes = [];



// function getBase64(file) {
// 	var reader = new FileReader();
// 	reader.onload = (function(file) {
// 		return function(e) {
// 			fileArr.push(this.result);
// 		}
// 	})(file);
// 	reader.readAsBinaryString(file);
// 	reader.onerror = function (error) {
// 		console.log('Error ', error);
// 	};
// }


  

function validateField() {
	var x = $("#boss-name").val();
    if (x == "") {
        $("#alert1").show();
    }

    if(accountTypes.length === 0) {
    	$("#alert2").show();
    }
    return false;
}

function handleFileSelect(evt) {
	  var f = evt.target.files[0]; // FileList object
	  var reader = new FileReader();
	  // Closure to capture the file information.
	  reader.onload = (function(theFile) {
	    return function(e) {
	      var binaryData = e.target.result;
	      //Converting Binary Data to base 64
	      var base64String = window.btoa(binaryData);
	      //showing file converted to base64
	      fileArr = base64String;
	    };
	  })(f);
	  // Read in the image file as a data URL.
	  reader.readAsBinaryString(f);
	}

function handleImageSelect(evt) {
	  var f = evt.target.files[0]; // FileList object
	  var reader = new FileReader();
	  // Closure to capture the file information.
	  reader.onload = (function(theFile) {
	    return function(e) {
	      var binaryData = e.target.result;
	      //Converting Binary Data to base 64
	      var base64String = window.btoa(binaryData);
	      //showing file converted to base64
	      imageArr = base64String;
	    };
	  })(f);
	  // Read in the image file as a data URL.
	  reader.readAsBinaryString(f);
	}

$(document).ready(function() {

	var availableTags = [
		"Nguyen Duc Long",
		"Nguyen Minh Trung",
		"Cao Minh Nhut",
		"Tran Van Ba",
		"Nguyen Thi Hai",
		"Le Thi Tu",
		"Dao Thi Buoi"
	];



	
	document.getElementById('files').addEventListener('change', handleFileSelect, false);
	document.getElementById('images').addEventListener('change', handleImageSelect, false);	
	
	// this is for autocompleting name suggestion when type in "chi huy don vi" textbox
 
	$("#boss-name").autocomplete({
		source: availableTags
	});

	// show/hide "tao moi" and "dieu chinh" when checkbox each and other

	$("#new").click(function() {
		$("#account").show();
		$("#description").hide();
	});
	$("#modify").click(function() {
		$("#description").show();
		$("#account").hide();
	});



	// 

	$("#btnSubmit").click(function() {
		
		
		$('input[type=checkbox]').each(function() {
			if(this.checked) {
				accountTypes.push($(this).val());
			};
		});

		accountTypes.push($("#description").val());

		validateField();
	
		//var descript = $("#description").val();
		
		var files = document.getElementById('files').files;
		var images = document.getElementById('images').files;
		var fileName = "danhsachnhanvien.txt";
		var imageName = "congvandenghi.jpg";
	  	if (files.length > 0) {
	  		fileName = files[0].name;
	  	}else{
	  		$("#alert3").show();
	  	};

	  	if (images.length > 0) {
	  		imageName = images[0].name;

	  	}else{
	  		$("#alert4").show();
	  	};



		var data = {
				  "departmentName": $('#don-vi option:selected').text(),
				  "departmentManager": $("#boss-name").val(),
				  "accountTypes": accountTypes,
				  "fileName": fileName,
				  "imageName": imageName,
				  "files": fileArr,
				  "images": imageArr
				};

		var jsonData = JSON.stringify(data);
		console.log(jsonData);

		if (files != 0 && images != 0 && files.length > 0 && images.length > 0 && $("#boss-name").val() != 0 && accountTypes.length > 0) {
			$.ajax ({
				type: "POST",
				data: jsonData,
				contentType: "json",
				url: "http://localhost:8090/registration/create",
				beforeSend : function(xhr) {
					xhr.setRequestHeader("Accept", "application/json");
					xhr.setRequestHeader("Content-Type", "application/json");
				},
				success: function(response) {
					console.log("response",response);
				},
				error: function (errMessage) {
					console.log("errMessage",errMessage);
				},	
			});
		}else{
			console.log("fields not filled")
		}

			

//		var files = document.getElementById('file').files;
//	  	if (files.length > 0) {
//	    getBase64(files[0]);
//	    	console.log(filearr);
//	  	}
//
//		var data = {
//			"departmentName": donVi,
//			"departmentManager": bossName,
//			"account": accountarr,
//			"fileTemplate": filearr,
//			"descript": descript
//		}
//
//		var jsonData = JSON.stringify(data);
//
//		$.ajax ({
//			type: "POST",
//			data: jsonData,
//			contentType: "json",
//			url: "http://localhost:8090/registration-success",
//			beforeSend : function(xhr) {
//								xhr.setRequestHeader("Accept", "application/json");
//								xhr.setRequestHeader("Content-Type", "application/json");
//			},
//
//			success: function(data) {
//				console.log("yeeeeee");
//				console.log(data);
//			},
//			error: function (msg) {
//				jsonValue = jQuery.parseJSON(error.response.Text);
//				console.log("error" + error.response.Text);
//			},	
//		});

		// this is to change from array to String
		// var urlParameter = a.join('&');

	});

	// document.getElementById('result').addEventListener('click', function() {
	//   var files = document.getElementById('file').files;
	//   if (files.length > 0) {
	//     getBase64(files[0]);
	//     	console.log(loads);
	//   }
	// });


		
});




