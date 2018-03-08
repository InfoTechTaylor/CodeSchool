$(document).ready(function(){

    $('#getWeatherButton').click( function(event){
        //event.preventDefault();
        clearWeather();
        checkAndDisplayValidationErrors($('#weatherForm').find('input'));
        getCurrentWeather();
        getFiveDayForecast();

    }); // end on click
});// end on ready


function getCurrentWeather(){
    var key = '0e47a40ffe6daa318502b60525541341';
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

            var tempChar;
            var windSpeedMeasure;
            if($('#unitSelect').val() === 'imperial'){
                tempChar = 'F';
                windSpeedMeasure = 'miles/hour';
            } else {
                tempChar = 'C';
                windSpeedMeasure = 'meters/sec';
            }
            currentConditionsRightDiv.append('<p>Temperature: ' + temperature + ' ' + tempChar + '</p>');
            currentConditionsRightDiv.append('<p>Humidity: ' + humidity + '%</p>');
            currentConditionsRightDiv.append('<p>Wind: ' + windSpeed + ' ' + windSpeedMeasure +  '</p>');


            $('#currentConditionsDiv').show();
            $('#fiveDayForecastDiv').show();

        },
        error: function() {
            // $('#errorMessages').append('<li>').attr({class: 'list-group-item list-group-item-danger'})
            //     .text('Error calling web service. Please try again later.');
        }
    }); // end ajax GET currentWeather
} // end getCurrentWeather


function getFiveDayForecast(result){
    var key = '0e47a40ffe6daa318502b60525541341';
    $.ajax({
        type: 'GET',
        url: 'http://api.openweathermap.org/data/2.5/forecast?zip=' + $('#zipcode').val() +',us&APPID=' + key + '&units=' + $('#unitSelect').val(),
        success: function(resultArray){

            var tempChar;
            if($('#unitSelect').val() === 'imperial'){
                tempChar = 'F';
            } else {
                tempChar = 'C';
            }

            $.each(resultArray.list, function(index, weatherForecast) {
                // console.log(weatherForecast.weather[0].icon);
                $('#day' + index).append(weatherForecast.dt_txt)
                .append('<img src="http://openweathermap.org/img/w/' + weatherForecast.weather[0].icon + '.png" />')
                .append('<p>' + weatherForecast.weather[0].main + '</p>')
                .append('<p>H ' + weatherForecast.main.temp_max + ' ' + tempChar + ' ' + 'L ' + weatherForecast.main.temp_min + ' ' + tempChar);

                return index < 4;
            });

        },
        error: function() {
            // $('#errorMessages').append('<li>').attr({class: 'list-group-item list-group-item-danger'})
            //     .text('Error calling web service. Please try again later.');
        }
    }); // end ajax GET five day forecast
} // end getFiveDayForecast

function clearWeather(){
    $('#errorMessages').empty();
    $('#currentConditionsDiv h2').empty();
    $('#currentConditionsRightDiv').empty();
    $('#currentConditionsLeftDiv').empty();
    $('#day0').empty();
    $('#day1').empty();
    $('#day2').empty();
    $('#day3').empty();
    $('#day4').empty();
}

function checkAndDisplayValidationErrors(input){
    $('#errorMessages').empty();

    var errorMessages = [];

    input.each(function() {
        if(!this.validity.valid) {
            var errorField = $('label[for=' + this.id + ']').text();
            errorMessages.push(errorField + ' ' + this.validationMessage);
        }
    });


    // if there are error messages, add them to the error message unordered list
    if (errorMessages.length > 0) {
        $.each(errorMessages, function (index, message) {
            $('#errorMessages').append($('<li>').attr({class: 'list-group-item list-group-item-danger'}).text(message));
        });
        return true;
    } else {
        return false;
    }
}


