$(document).ready(function(){
    var key = '0e47a40ffe6daa318502b60525541341';

    $('#getWeatherButton').click( function(event){
        event.preventDefault();
        var currentConditionLeftDiv = $('#currentConditionsLeftDiv');
        var currentConditionsDiv = $('#currentConditionsDiv');
        var currentConditionsRightDiv = $('#currentConditionsRightDiv');

        $.ajax({
            type: 'GET',
            url: 'http://api.openweathermap.org/data/2.5/weather?zip='+ $('#zipcode').val() +',us&APPID=' + key + '&units=' + $('#unitSelect').val(),
            success: function(result){

                var currentCondition = result.weather[0].main;
                var currentConditionDescription = result.weather[0].description;
                var currentCity = result.name;
                var currentConditionImage = result.weather[0].icon;
                var temperature = result.main.temp;
                var humidity = result.main.humidity;
                var windSpeed = result.wind.speed;

                currentConditionsDiv.prepend('<h2>Current Conditions in ' + currentCity + '</h2>');
                currentConditionLeftDiv.append('<img class="col-md-2" src="http://openweathermap.org/img/w/' + currentConditionImage + '.png" />')
                currentConditionLeftDiv.append('<p class="col-md-4">' + currentCondition + ': ' + currentConditionDescription + '</p>');

                currentConditionsRightDiv.append('<p>Temperature: ' + temperature + '</p>');
                currentConditionsRightDiv.append('<p>Humidity: ' + humidity + '</p>');
                currentConditionsRightDiv.append('<p>Wind: ' + windSpeed + '</p>');
                currentConditionsDiv.append('<hr />');


                $('#currentConditionsDiv').show();
                $('#fiveDayForecastDiv').show();

            },
            error: function() {
                alert("FAILURE");
            }
        });
    });
});// end on ready


function getCurrentWeather(result){

} // end getCurrentWeather


