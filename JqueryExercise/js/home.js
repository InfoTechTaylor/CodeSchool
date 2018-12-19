$(document).ready(function(){
    //alert("Ready to go!!!");

   // $('H1').hide();
   //$('#second').hide();
   $('#third').remove();
   $('#emptyDiv').append('p').text('A new paragraph of text!');
   $('#newButton').add('btn btn-default');
   $('#first').css('color', 'blue');

})



/* The below is the same as the above but is referencing a method call
     // Run a named function when the document is ready.

     function handleReady() {
          alert("Ready to go!");
     }

     // Run a named function when the document is Ready
     $(document).ready(handleReady);

}



*/
