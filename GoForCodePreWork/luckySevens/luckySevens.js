function playLuckySevens(){
     var amount = document.getElementById("betAmount").value;

     if (Number(amount) <= 0){
          alert("Starting bet must be greater than zero. ")
          document.forms["bet"]["betAmount"].parentElement.className = "col-sm-2 has-error";
          document.forms["bet"]["betAmount"].focus();
     }

     console.log(amount);

     var startingBet = amount;
     var countRolls = 0;
     var maxAmount = amount;
     var maxAmmountRollCount = 1;
     while (amount > 0) {
          var dice1 = Math.ceil(Math.random() * (1 + 6 - 1));
          var dice2 = Math.ceil(Math.random() * (1 + 6 - 1));
          countRolls++;

          if (dice1 + dice2 === 7){
               amount += 4;
          } else {
               amount -= 1;
          }

          if (amount > maxAmount){
               maxAmount = amount;
               maxAmmountRollCount = countRolls;
          }
     } // end while

     document.getElementById("startingBet").innerText = startingBet;
     document.getElementById("totalRolls").innerText = countRolls;
     document.getElementById("highestWon").innerText = maxAmount;
     document.getElementById("rollCountMax").innerText = maxAmmountRollCount;
     document.getElementById("submitButton").value = "Play Again";

     document.getElementById("results").style.display = "block";

     return false;
}  // end playLuckySevens()
