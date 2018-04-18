$(document).ready(function(){
    loadDvds();

    $('#createDvdButton').on('click', function(){
        $('#dvdTableDiv').hide();
        $('#navDiv').hide();
        $('#createDvdDiv').find('input, textarea').val('');
        $('#createDvdRatingInput').find('option:first').attr('selected', 'selected');
        $('#createDvdDiv').show();
    });

    $('#createCancelBtn, #editCancelBtn').on('click', function(){
        $('#createDvdDiv, #editDvdDiv').hide();
        $('#navDiv').show();
        $('#dvdTableDiv').show();
    });

    $('#createSaveBtn').on('click', function(){
       createDvd();
    });
});

function loadDvds(){

    $.ajax({
       type: 'GET',
       url: 'http://localhost:8080/dvds',
        success: function(resultArray){

           $.each(resultArray, function(index, dvd){
               $('#dvdTableBody').append('<tr><td>'+ dvd.title +'</td>' +
                   '<td>'+ dvd.releaseYear +'</td>' +
                   '<td>'+ dvd.director +'</td>' +
                   '<td>'+ dvd.rating +'</td>' +
                   '<td><a onclick="">Edit</a> | <a onclick="">Delete</a></td>' +
                   '</tr>');
           });

        },
        error: function(){
           alert('fail');
        }
    });
}

function createDvd(){

    $.ajax({
        type: 'POST',
        url: 'http://localhost:8080/dvd',
        data: JSON.stringify({
            title: $('#createDvdTitleInput').val(),
            releaseYear: $('#createDvdReleaseYearInput').val(),
            director: $('#createDvdDirectorInput').val(),
            rating: $('#createDvdRatingInput').val(),
            notes: $('#createDvdNotesInput').val()
        }),
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        dataType: 'json',
        success: function(){
            $('#createDvdDiv').hide();
            $('#navDiv').show();
            loadDvds();
        },
        error: function(){

        }

    });
}
