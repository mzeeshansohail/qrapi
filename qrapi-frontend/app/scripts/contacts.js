accountId = JSON.parse(localStorage.accountId);
var locations = [];
var data;
data = [];
$(document).ready(function () {
	loadContacts();
	
});

function loadContacts(){
	$.ajax({
		url: 'http://localhost:8080/qrapi/api/v1/contacts/' + accountId,
		type: 'GET',
		contentType: "application/json; charset=utf-8",
		dataType: "json",
		success:
			function (response, status) { 
				data = response;
				var strTable;
				strTable = '';
				strTable += '<table border="2" class="table table-hover tbldata" style="padding:40px;margin-bottom: 100px" id="myTable">';
				strTable += '<tr class="header">';
				strTable += '<th>Name</th>';
				strTable += '<th>Email</th>';
				strTable += '<th>address</th>';
				strTable += '<th>table Action';
				strTable += '</tr>';

				$.each(data, function (i, field) {
					d = data[i]['Id'];
					strTable += '<tr>';
					var name= data[i]['firstName'] + ' ' + data[i]['lastName'];
					strTable += '<td>' + name + '</td>';
					strTable += '<td>' + data[i]['email'] + '</td>';
					var location = data[i]['address']['lineOne'] + ' ' + data[i]['address']['lineTwo'] + ' ' + data[i]['address']['city'] + ' ' + data[i]['address']['state'] + ' ' + data[i]['address']['country'];
					strTable += '<td>' + location + '</td>';
					strTable += '<td>' + '<a href="#" id="edit" data-id=' +data[i]['id']+' onClick="getRow()">Edit</a>' + ' ' + '<a href="#" id="delete" data-id=' +data[i]['id']+' onClick="deleteFunction()">Delete</a>' + '</td>';
					strTable += '</tr>';
				});
				$('#ScreenData').html(strTable);

			},
		error:
			function (response, status) {
				alert("Invalid Username or password");
			}
	});
}
function getRow(){
	event.preventDefault();
	var id = $(event.target).data("id");
	window.location.href = "http://" + window.location.host + "/editcontact.html?id=" + id;
}

function deleteFunction(){
		event.preventDefault();
		var contact_Id= $(event.target).data("id");
		
		var r = confirm("Are u sure u want to delete this Contact?");
    if (r == true) {
			$.ajax({
				url: 'http://localhost:8080/qrapi/api/v1/contacts/' + contact_Id,
				type: 'DELETE',
				dataType: "text",

				success:
					function (response, status) {
						loadContacts();
					},
				error:
					function (response, status) {
						alert("error deleting the contact"+ response);
						console.log(response);
						window.location.href= "http://"+ window.location.host+"/contacts.html"; 
					}
		});
    } else {
			window.location.href= "http://"+ window.location.host+"/contacts.html";	
		}
}

function logout(){
  localStorage.clear();
  window.location.href= "index.html";
}
$.extend({
	getUrlVars: function(){
	  var vars = [], hash;
	  var hashes = window.location.href.slice(window.location.href.indexOf('?') + 1).split('&');
	  for(var i = 0; i < hashes.length; i++)
	  {
		hash = hashes[i].split('=');
		vars.push(hash[0]);
		vars[hash[0]] = hash[1];
	  }
	  return vars;
	},
	getUrlVar: function(name){
	  return $.getUrlVars()[name];
	}
  });

  function myFunction() {
	var input, filter, table, tr, td, i;
	input = document.getElementById("myInput");
	filter = input.value.toUpperCase();
	table = document.getElementById("myTable");
	tr = table.getElementsByTagName("tr");
	for (i = 0; i < tr.length; i++) {
	  td = tr[i].getElementsByTagName("td")[1];
	  if (td) {
		if (td.innerHTML.toUpperCase().indexOf(filter) > -1) {
		  tr[i].style.display = "";
		} else {
		  tr[i].style.display = "none";
		}
	  }       
	}
	
	}
	function loggedIn(){
		var id= localStorage.getItem("accountId");
		return !(id=="" || id==null || id==undefined)
	}

	function loggedOut(){
		var id= localStorage.getItem("accountId");
		return (id=="" || id==null || id==undefined)
	}