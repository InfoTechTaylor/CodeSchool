function displayEvens() {
     var evenNumbers = new Array();
     var startingNum = document.getElementById("startingNum").value;
     var endingNum = document.getElementById("endingNum").value;
     var step = document.getElementById("step").value;

     document.getElementById("startingNumDisplay").innerText = document.getElementById("startingNum").value;
     document.getElementById("endingNumDisplay").innerText =  document.getElementById("endingNum").value;
     document.getElementById("stepDisplay").innerText =  document.getElementById("step").value;


     if (startingNum == "" || isNaN(startingNum)) {
          alert("Starting number must be filled in with a number.");
          document.forms["displayEvensForm"]["startingNum"]
          .parentElement.className = "form-group has-error col-sm-2";
          document.forms["displayEvensForm"]["startingNum"].focus();
          return false;
     }
     if (endingNum == "" || isNaN(endingNum) || Number(endingNum) < Number(startingNum)) {
          alert("Ending number must be filled in with a number and greater than starting number.");
          document.forms["displayEvensForm"]["startingNum"]
          .parentElement.className = "form-group has-error col-sm-2";
          document.forms["displayEvensForm"]["startingNum"].focus();
          return false;
     }

     if (step == "" || isNaN(step) || Number(step) < 0) {
          alert("Step must be a positive number.");
          document.forms["displayEvensForm"]["startingNum"]
          .parentElement.className = "form-group has-error col-sm-2";
          document.forms["displayEvensForm"]["startingNum"].focus();
          return false;
     }

          for (var i = Number(startingNum); i <= Number(endingNum); i += Number(step)) {
               if (i % 2 === 0 ) { // if number is even
                    evenNumbers.push(i);
               }
          }

          var evenNumbersPrinted = "";
          for (var i = 0; i < evenNumbers.length; i++ ){
               evenNumbersPrinted += "\n" + evenNumbers[i];
          }

          document.getElementById("numbers").innerText = evenNumbersPrinted;
          document.getElementById("evenNumResults").style.display = "block";

     return false;
}
