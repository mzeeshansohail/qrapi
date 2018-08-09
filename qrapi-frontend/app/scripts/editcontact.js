id=($.getUrlVar('id'));
    function getContact(){
      $.ajax({
		url: 'http://localhost:8080/qrapi/api/v1/contacts/contactId/' + id,
		type: 'GET',
		contentType: "application/json; charset=utf-8",
		dataType: "json",
		success:
			function (response, status) { // Required Callback Function
        //"response" receives - whatever written in echo of above PHP script.
        $("#first-name").val(response['firstName']);
        $("#last-name").val(response['lastName']);
        $("#email").val(response['email']);
        $("#gender").val(response['gender']);
        $("#phoneNumber").val(response['phoneNumber']);
        $("#status").val(response['status']);
        $("#address_id").val(response['address']['id']);
        $("#lineOne").val(response['address']['lineOne']);
        $("#lineTwo").val(response['address']['lineTwo']);
        $("#city").val(response['address']['city']);
        $("#state").val(response['address']['state']);
        $("#country").val(response['address']['country']);
        $("#name").val(localStorage.name);
        $("#username").val(localStorage.username);
        $("#password").val(localStorage.password);
        $("#accountcity").val(localStorage.city);
      },
		error:
			function (response, status) { // Required Callback Function
				//"response" receives - whatever written in echo of above PHP script.
				alert("Could not load contact");
			}
	});
    }
    function editContact(){
        var firstName  = $("#first-name").val();
        var lastName   = $("#last-name").val();
        var email      = $("#email").val();
        var gender     = $("#gender").val();
        var phoneNumber= $("#phoneNumber").val();
        var status     = $("#status").val();
        var address_id = $("#address_id").val();
        var lineOne    = $("#lineOne").val();
        var lineTwo    = $("#lineTwo").val();
        var city       = $("#city").val();
        var state      = $("#state").val();
        var country    = $("#country").val();

        var jsonContact= {
            "id":id,
           "firstName": firstName,
           "lastName" : lastName,
           "email" : email,
           "gender": gender,
           "phoneNumber": phoneNumber,
           "status": status,
           "address":{
             "id": address_id,
             "lineOne": lineOne,
             "lineTwo": lineTwo,
             "city": city,
             "state": state,
             "country": country
           },
           "account":{
           "id":localStorage.accountId,
           "name":localStorage.name,
           "username":localStorage.username,
           "password": localStorage.password,
           "city": localStorage.city
           }
        }
        var Contact= JSON.stringify(jsonContact);
        $.ajax({
		url: 'http://localhost:8080/qrapi/api/v1/contacts/' + id,
		type: 'PUT',
    data: Contact,
    contentType: "application/json; charset=utf-8",
    dataType: "text",
		success:
			function (response, status) { // Required Callback Function
        //"response" receives - whatever written in echo of above PHP script.
        alert("contact updated!");
        window.location.href = "http://"+ window.location.host+ "/contacts.html"
      },
		error:
			function (response, status) { // Required Callback Function
				//"response" receives - whatever written in echo of above PHP script.
				alert("Error");
			}
	});
    }
  function cancel(){
    window.location.href= "http://"+window.location.host + "/contacts.html"; 
  }

function loggedIn(){
		var id= localStorage.getItem("accountId");
		return !(id=="" || id==null || id==undefined)
	}

	function loggedOut(){
		var id= localStorage.getItem("accountId");
		return (id=="" || id==null || id==undefined)
	}