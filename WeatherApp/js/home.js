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

            var startingIndex=0;
            var endingIndex=0
            $.each(resultArray.list, function(index, weatherForecast) {
                var date = new Date(weatherForecast.dt_txt);
                var hour = date.getUTCHours();
                console.log(date.getDay());
                endingIndex++;

                if(hour === 21){
                    startingIndex = index;
                    return false;
                }
            });

            // First Day
            var listItem = resultArray.list;
            var date = new Date(listItem[startingIndex].dt_txt);
            var day = listItem[startingIndex].dt_txt.substring(8,10);
            var month = date.toLocaleString("en-us", { month : "long"});
            // console.log(weatherForecast.weather[0].icon);
            $('#day0').append('<p>' + day + ' ' + month + '</p>')
                .append('<img src="http://openweathermap.org/img/w/' + listItem[startingIndex].weather[0].icon + '.png" />')
                .append('<p>' + listItem[startingIndex].weather[0].main + '</p>')
                .append('<p>H ' + listItem[startingIndex].main.temp_max + ' ' + tempChar + ' ' + 'L ' + listItem[startingIndex].main.temp_min + ' ' + tempChar);

            // second Day
            listItem = resultArray.list;
            date = new Date(listItem[startingIndex + 8].dt_txt);
            day = listItem[startingIndex + 8].dt_txt.substring(8,10);
            month = date.toLocaleString("en-us", { month : "long"});
            // console.log(weatherForecast.weather[0].icon);
            $('#day1').append('<p>' + day + ' ' + month + '</p>')
                .append('<img src="http://openweathermap.org/img/w/' + listItem[startingIndex + 8].weather[0].icon + '.png" />')
                .append('<p>' + listItem[startingIndex + 8].weather[0].main + '</p>')
                .append('<p>H ' + listItem[startingIndex + 8].main.temp_max + ' ' + tempChar + ' ' + 'L ' + listItem[startingIndex + 8].main.temp_min + ' ' + tempChar);

            // third Day
            listItem = resultArray.list;
            date = new Date(listItem[startingIndex + 16].dt_txt);
            console.log(date);
            day = listItem[startingIndex+ 16].dt_txt.substring(8,10);
            console.log(day);
            month = date.toLocaleString("en-us", { month : "long"});
            // console.log(weatherForecast.weather[0].icon);
            $('#day2').append('<p>' + day + ' ' + month + '</p>')
                .append('<img src="http://openweathermap.org/img/w/' + listItem[startingIndex + 16].weather[0].icon + '.png" />')
                .append('<p>' + listItem[startingIndex + 16].weather[0].main + '</p>')
                .append('<p>H ' + listItem[startingIndex + 16].main.temp_max + ' ' + tempChar + ' ' + 'L ' + listItem[startingIndex + 16].main.temp_min + ' ' + tempChar);

            // fourth Day
            listItem = resultArray.list;
            date = new Date(listItem[startingIndex + 24].dt_txt);
            console.log(date);
            day = listItem[startingIndex + 24].dt_txt.substring(8,10);
            console.log(day);
            month = date.toLocaleString("en-us", { month : "long"});
            // console.log(weatherForecast.weather[0].icon);
            $('#day3').append('<p>' + day + ' ' + month + '</p>')
                .append('<img src="http://openweathermap.org/img/w/' + listItem[startingIndex + 24].weather[0].icon + '.png" />')
                .append('<p>' + listItem[startingIndex + 24].weather[0].main + '</p>')
                .append('<p>H ' + listItem[startingIndex + 24].main.temp_max + ' ' + tempChar + ' ' + 'L ' + listItem[startingIndex + 24].main.temp_min + ' ' + tempChar);

            // fifth Day
            listItem = resultArray.list;
            date = new Date(listItem[startingIndex + 32].dt_txt);
            console.log(date);
            day = listItem[startingIndex + 32].dt_txt.substring(8,10);
            console.log(day);
            month = date.toLocaleString("en-us", { month : "long"});
            // console.log(weatherForecast.weather[0].icon);
            $('#day4').append('<p>' + day + ' ' + month + '</p>')
                .append('<img src="http://openweathermap.org/img/w/' + listItem[startingIndex + 32].weather[0].icon + '.png" />')
                .append('<p>' + listItem[startingIndex + 32].weather[0].main + '</p>')
                .append('<p>H ' + listItem[startingIndex + 32].main.temp_max + ' ' + tempChar + ' ' + 'L ' + listItem[startingIndex + 32].main.temp_min + ' ' + tempChar);
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


