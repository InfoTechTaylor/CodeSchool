var arrayOfNumbers = [1, 2, 6, 342, 675485, 674, 3, 5, 8];

function findMax(arrayOfNumbers){
     var max = 0;
     for (i=0; i < arrayOfNumbers.length; i++){
          if (arrayOfNumbers[i] > max) {
               max = arrayOfNumbers[i];
          }
     }
     return max;
}
