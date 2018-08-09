var locations=[];
accountId= JSON.parse(localStorage.accountId);
function initMap() {

	$.ajax({
		url: "http://localhost:8080/qrapi/api/v1/contacts/addresses/"+accountId,
		type: 'GET',
		contentType: "application/json; charset=utf-8",
		dataType: "json",
		success:
			function (response, status) { // Required Callback Function
                //"response" receives - whatever written in echo of above PHP script.
                data= response;
                $.each(data,function(i,field){
                    var location = data[i]['lineOne'] + ' ' + data[i]['lineTwo'] + ' ' + data[i]['city'] + ' ' + data[i]['state'] + ' ' + data[i]['country'];
                    locations.push(location);
                    console.log(location);
                })
                console.log(locations);
                var map = new google.maps.Map(document.getElementById('map'), {
                    zoom: 8,
                    center: {lat:33.6844, lng:73.0479}
                  });    
                  console.log(locations.length)
                  for (var x = 0; x <locations.length; x++) {
                      $.getJSON('http://maps.googleapis.com/maps/api/geocode/json?address='+locations[x]+'&sensor=false', null, function (data) {
                          console.log(data);
                      	if(!(data.results[0]=="" || data.results[0]==null ||data.results[0]==undefined)){
                          var p = data.results[0].geometry.location
                          var latlng = new google.maps.LatLng(p.lat, p.lng);
                          console.log("if");
                      }else{
                      	console.log("null");
                      }

                          new google.maps.Marker({
                              position: latlng,
                              map: map
                          });
              
                      }); 
                    }          
			},
		error:
            function (response, status) { // Required Callback Function
                console.log(status);
			}
	});
}