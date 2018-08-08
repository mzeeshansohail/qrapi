
	$(document).ready(function(){
			$("#button").click(function(){
				Jdata=[];
				var un= $("#username").val();
				var pas=$("#password").val();
				if((un==undefined || un==null || un=="") || (pas==undefined || pas==null || pas=="")){
					alert("username or password cannot be null");
				}
				else{
					var userData = {
						username: un,
						password: pas
				};
						$.ajax({
				url: 'http://localhost:8080/qrapi/api/v1/accounts/',
				type: 'POST',
				contentType: "application/json; charset=utf-8",
				data: JSON.stringify(userData),
				success:
				function(response,status){ // Required Callback Function
					//"response" receives - whatever written in echo of above PHP script.
					localStorage.accountId = response.id;
					localStorage.name= response.name;
					localStorage.username= response.username;
					localStorage.password= response.password;
					localStorage.city= response.city;
					$("#main-text").innerHtml= "login Successful!"
					console.log(localStorage.accountId);
					document.location.href = "contacts.html";
					
					},
					error:
					function(response,status){ // Required Callback Function
						//"response" receives - whatever written in echo of above PHP script.
						$("#main-text").innerHtml= "incorrect username or password!";
						}
		});
				}
	});
	});

	function loggedIn(){
		var id= localStorage.getItem("accountId");
		return !(id=="" || id==null || id==undefined)
	}

	function loggedOut(){
		var id= localStorage.getItem("accountId");
		return (id=="" || id==null || id==undefined)
	}