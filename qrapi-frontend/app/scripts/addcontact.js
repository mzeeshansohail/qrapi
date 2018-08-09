function addContact(){
    var firstName  = $("#first-name").val();
    var lastName   = $("#last-name").val();
    var email      = $("#email").val();
    var gender     = $("#gender").val();
    var phoneNumber= $("#phoneNumber").val();
    var status     = $("#status").val();
    var lineOne    = $("#lineOne").val();
    var lineTwo    = $("#lineTwo").val();
    var city       = $("#city").val();
    var state      = $("#state").val();
    var country    = $("#country").val();

    if((firstName==null||firstName==undefined||firstName=="")||(lastName==null||lastName==undefined||lastName=="")||(email==null||email==undefined||email=="")
    ||(phoneNumber==null||phoneNumber==undefined||phoneNumber=="")||(status==null||status==undefined||status=="")||
    (lineOne==null||lineOne==undefined||lineOne=="")||(lineTwo==null||lineTwo==undefined||lineTwo=="")||(city==null||city==undefined||city=="")||
    (state==null||state==undefined||state=="")||(country==null||country==undefined||country=="")){
        alert("plz fill the empty fields!");
    }
    else{
    var jsonContact= {
       "firstName": firstName,
       "lastName" : lastName,
       "email" : email,
       "gender": gender,
       "phoneNumber": phoneNumber,
       "status": status,
       "address":{
         "lineOne": lineOne,
         "lineTwo": lineTwo,
         "city": city,
         "state": state,
         "country": country
       },
       "account":{
       "name":localStorage.name,
       "username":localStorage.username,
       "password":localStorage.password,
       "city":localStorage.city
       }
    } 
    var Contact= JSON.stringify(jsonContact);
        $.ajax({
            url: 'http://localhost:8080/qrapi/api/v1/contacts/' + localStorage.accountId,
            type: 'POST',
            data: Contact,
            contentType: "application/json",
            dataType: "text",
            success:
                function (response, status) { // Required Callback Function
                    //"response" receives - whatever written in echo of above PHP script.
                    alert("contact Added!");
                    window.location.href = "/contacts.html"
                },
            error:
                function (response, status) { // Required Callback Function
                    //"response" receives - whatever written in echo of above PHP script.
                    alert("Error");
                }
        });
    }
}
function cancel(){
window.location.href= "http://"+window.location.host + "/contacts.html"; 
}
function account(){
$("#name").val(localStorage.name);
$("#username").val(localStorage.username);
$("#password").val(localStorage.password);
$("#accountcity").val(localStorage.city);
}

function loggedIn(){
    var id= localStorage.getItem("accountId");
    return !(id=="" || id==null || id==undefined)
}

function loggedOut(){
    var id= localStorage.getItem("accountId");
    return (id=="" || id==null || id==undefined)
}