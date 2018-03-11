$(document).ready(function() {
    clearTextBoxesAndReset();
    loadInventory();

    // on add dollar button
    $('#addDollarBtn').click(function(){
        addMoney(1.00);

    }); // end on click addDollarBtn

    // on add quarter button
    $('#addQuarterBtn').click(function(){
       addMoney(.25);
    }); // end on click addQuarterBtn

    // on click add dime button
    $('#addDimeBtn').click(function(){
        addMoney(.10);
    }); // end on click addDimeBtn

    // on click add nickel button
    $('#addNickelBtn').click(function(){
        addMoney(.05);
    }); // end on click addNickelBtn

    // make purchase on click
    $('#makePurchaseBtn').click(function() {
        var usersChoice = $('#itemText').val();
        purchaseItem(usersChoice);
    });

    // get change on click
    $('#changeBtn').click(function(){
       clearTextBoxesAndReset();
    });

    // click event for all the item divs to fill in item field
    $('#vendingMachineItemRowDiv').on('click', 'div', function () {
        $('#itemText').val($(this).find('.text-left').text());
    });

}); // end document ready function


/*###########################FUNCTIONS################################*/
function loadInventory(){

    $.ajax({
        type: 'GET',
        url: 'http://localhost:8080/items',
        success: function(inventoryArray){
            // loop through all items in the inventory
            $.each(inventoryArray, function(index, inventoryItem){
                // create a new panel div for each item in the inventory
                $('#vendingMachineItemRowDiv').append('<div id="item' + inventoryItem.id + '" class="col-lg-4 col-sm-4 panel panel-primary">' +
                    '<p id="id' + inventoryItem.id +'"class="text-left">' + inventoryItem.id + '</p>' +
                    '<p class="text-center">' + inventoryItem.name + '</p>' +
                    '<p class="text-center">$' + inventoryItem.price + '</p>' +
                    '<p class="text-center">Quantity Left: '+ inventoryItem.quantity +'</p></div>');
            });
        },
        error: function(){
            alert('FAILURE');
        }
    }); // end get all inventory ajax call
} // end loadInventory()

function clearTextBoxesAndReset(){
    $('#currentMoneyAmt').val(0);
    $('#itemText').val('');
    $('#changeText').val('');
    $('#messagesTextBox').val('WELCOME! Select an item on the left you wish to purchase.');
}

function addMoney(amount){
    var currentAmt = $('#currentMoneyAmt').val();
    currentAmt = +currentAmt + amount;
    $('#currentMoneyAmt').val(currentAmt.toFixed(2));
}

function purchaseItem(usersChoice){

    var currentAmount = $('#currentMoneyAmt').val();

    $.ajax({
        type: 'GET',
        url: 'http://localhost:8080/money/' + currentAmount + '/item/' + usersChoice,
        success: function(result){
            //getChange();
            $('#messagesTextBox').val('Thank You!!!');
            $('#vendingMachineItemRowDiv').empty();
            loadInventory();
            // display change
            $('#changeText').val('Quarters: ' + result.quarters + '\nDimes: ' + result.dimes + '\nNickels: ' + result.nickels + '\nPennies: ' + result.pennies);
            // update current amount in the total $ in text box
            $('#currentMoneyAmt').val((result.quarters *.25) + (result.dimes * .10) + (result.nickels * .05) + (result.pennies * .01));
        },
        error: function(error){
            // $('#messagesTextBox').css('background-color', 'Tomato');
            $('#messagesTextBox').val(error.responseJSON.message);
        }
    }); // end

}

function getChange(){
    var currentAmt = $('#currentMoneyAmt').val();
    var currentAmtAsPennies = +currentAmt * 100;
    var numQuarters = 0;
    var numDimes = 0;
    var numNickels = 0;
    var numPennies = 0;
    var originalAmtOfPennies = 0;

    // get num quarters
    if(currentAmtAsPennies >= 25){
        originalAmtOfPennies = currentAmtAsPennies;
        currentAmtAsPennies = currentAmtAsPennies % 25;
        numQuarters = (originalAmtOfPennies - currentAmtAsPennies) / 25;
    }

    if(currentAmtAsPennies >= 10){
        originalAmtOfPennies = currentAmtAsPennies;
        currentAmtAsPennies = currentAmtAsPennies % 10;
        numDimes = (originalAmtOfPennies - currentAmtAsPennies) / 10;
    }

    if(currentAmtAsPennies >= 5){
        originalAmtOfPennies = currentAmtAsPennies;
        currentAmtAsPennies = currentAmtAsPennies % 5;
        numNickels = (originalAmtOfPennies - currentAmtAsPennies) / 5;
    }

    numPennies = currentAmtAsPennies;

    $('#changeText').val('Quarters: ' + numQuarters + '\nDimes: ' + numDimes + '\nNickels: ' + numNickels + '\nPennies: ' + numPennies);
}


