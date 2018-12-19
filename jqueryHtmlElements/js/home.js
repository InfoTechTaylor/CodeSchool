$(document).ready(function () {
     $('h1').addClass('text-center');
    $('h2').addClass('text-center');
    $('.myBannerHeading').addClass('page-header');
    $('.myBannerHeading').removeClass('myBannerHeading');
    $('#yellowHeading').text('Yellow Team');
    $('#orangeTeamList').css('background-color', 'Orange');
    $('#blueTeamList').css('background-color', 'Blue');
    $('#redTeamList').css('background-color', 'Red');
    $('#yellowTeamList').css('background-color', 'Yellow');
    $('#yellowTeamList').append('<li>Joseph Banks</li>');
    $('#yellowTeamList').append('<li>Simon Jones</li>');
    $('#oops h1').hide();
    $('#footerPlaceholder').remove();
    $('#footer').append('<p>Taylor Lapointe | taylor.lapointe@libertymutual.com</p>');
    $('#footer p').css({'font-family': 'Courier', 'font-size': '24px' });


});
