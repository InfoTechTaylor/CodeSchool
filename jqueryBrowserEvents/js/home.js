$(document).ready(function () {
     // initial load only, hide all but the main div
     $('#akronInfoDiv').hide();
     $('#minneapolisInfoDiv').hide();
     $('#louisvilleInfoDiv').hide();

     // on click of main button, only show main div
     $('#mainButton').on('click', function() {
          $('#mainInfoDiv').show();
          $('#akronInfoDiv').hide();
          $('#minneapolisInfoDiv').hide();
          $('#louisvilleInfoDiv').hide();
     }
     );

     // on click of akron button, only show akron info
     $('#akronButton').on('click', function() {
          $('#akronInfoDiv').show();
          $('#mainInfoDiv').hide();
          $('#minneapolisInfoDiv').hide();
          $('#louisvilleInfoDiv').hide();
          $('#akronWeather').hide();
     }
     );

     // on click of minneapolis button, only show minneapolis info
     $('#minneapolisButton').on('click', function() {
          $('#akronInfoDiv').hide();
          $('#mainInfoDiv').hide();
          $('#minneapolisInfoDiv').show();
          $('#louisvilleInfoDiv').hide();
          $('#minneapolisWeather').hide();
     }
     );

     // on click of louisvilleButton, show Louisville info
     $('#louisvilleButton').on('click', function() {
          $('#akronInfoDiv').hide();
          $('#mainInfoDiv').hide();
          $('#minneapolisInfoDiv').hide();
          $('#louisvilleInfoDiv').show();
          $('#louisvilleWeather').hide();
     }
     );

     // toggle (show/hide) the weather on weather button click
     $('#louisvilleWeatherButton').on('click', function() {
          $('#louisvilleWeather').toggle('slow');
     });

     $('#akronWeatherButton').on('click', function() {
          $('#akronWeather').toggle('slow');
     });

     $('#minneapolisWeatherButton').on('click', function() {
          $('#minneapolisWeather').toggle('slow');
     });

     // table hover actions
     $('.table tr td').hover(
          // in callback
          function () {
               $(this).parent().css('background-color', 'WhiteSmoke');
          },
          // out callback
          function () {
               $(this).parent().css('background-color', '');
          }
     );

     // // table hover actions
     // $('.table tr').hover(
     //      // in callback
     //      function () {
     //           $(this).css("background-color", "WhiteSmoke");
     //           $('.bgcolor').addClass('AliceBlue');
     //      },
     //      // out callback
     //      function () {
     //           $(this).css("background-color", "");
     //      }
     // );




});
