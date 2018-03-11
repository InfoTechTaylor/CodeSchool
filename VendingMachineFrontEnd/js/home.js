$(document).ready(function () {
    clearTextBoxesAndReset();
    loadInventory();

    // make purchase on click
    $('#makePurchaseBtn').click(function () {
        var usersChoice = $('#itemText').val();
        purchaseItem(usersChoice);
    });

    // add money on click
    $('#totalMoneyDiv').on('click', 'button', function() {
        var buttonVal = $(this).val();
        addMoney(+buttonVal);
    });

    // get change on click
    $('#changeBtn').click(function () {
        clearTextBoxesAndReset();
    });

    // click event for all the item divs to fill in item field
    $('#vendingMachineItemRowDiv').on('click', 'div', function () {
        $('#itemText').val($(this).find('.text-left').text());
        $('.panel').css('background-color', '')
        $(this).find('.panel').css('background-color', '#dff0d8');

    });

}); // end document ready function

/*###########################FUNCTIONS################################*/
function loadInventory() {

    $.ajax({
        type: 'GET',
        url: 'http://localhost:8080/items',
        success: function (inventoryArray) {
            // loop through all items in the inventory
            $.each(inventoryArray, function (index, inventoryItem) {
                // create a new panel div for each item in the inventory
                $('#vendingMachineItemRowDiv').append('<div id="item' + inventoryItem.id +
                    '" class="col-lg-4 col-sm-4"><div style="margin-right: 1%; padding: 2%;" class="panel panel-primary">' +
                    '<p id="id' + inventoryItem.id + '"class="text-left">' + inventoryItem.id + '</p>' +
                    '<p class="text-center">' + inventoryItem.name + '</p>' +
                    '<p class="text-center">$' + (inventoryItem.price).toFixed(2) + '</p>' +
                    '<p class="text-center">Quantity Left: ' + inventoryItem.quantity + '</p></div></div>');
            });
        },
        error: function () {
            $('#messagesTextBox').val('Unable to load items.');
        }
    }); // end get all inventory ajax call
} // end loadInventory()

function clearTextBoxesAndReset() {
    $('#currentMoneyAmt').val(0.00.toFixed(2));
    $('#itemText').val('');
    $('#changeText').val('');
    $('#messagesTextBox').val('WELCOME! Select an Item.');
    $('#messagesTextBox').css('background-color', '');
}

function addMoney(amount) {
    var currentAmt = $('#currentMoneyAmt').val();
    currentAmt = +currentAmt + amount;
    $('#currentMoneyAmt').val(currentAmt.toFixed(2));
}

function purchaseItem(usersChoice) {

    var currentAmount = $('#currentMoneyAmt').val();

    $.ajax({
        type: 'GET',
        url: 'http://localhost:8080/money/' + currentAmount + '/item/' + usersChoice,
        success: function (result) {
            $('#messagesTextBox').val('Thank You!!!');
            $('#messagesTextBox').css('background-color', '#dff0d8');
            $('#vendingMachineItemRowDiv').empty();
            loadInventory(); // reload inventory to reflect new quantity
            // display change
            $('#changeText').val('Quarters: ' + result.quarters + '\nDimes: '
                + result.dimes + '\nNickels: ' + result.nickels + '\nPennies: ' + result.pennies);
            // update current amount in the total $ in text box
            $('#currentMoneyAmt').val(((result.quarters * .25) +
                (result.dimes * .10) + (result.nickels * .05) + (result.pennies * .01)).toFixed(2));
        },
        error: function (error) {
            getChange();
            $('#messagesTextBox').css('background-color', '#f2dede');
            if($('#itemText').val() === ''){
                $('#messagesTextBox').val('Select an item to purchase.');
            } else {
                $('#messagesTextBox').val(error.responseJSON.message);
            }

        }
    }); // end
}

// getChange is only for if the user adds money and gets an error to be able to display the change
// since it seems the api doesn't return the change with the error but it is needed if they decide they
// no longer want to make purchase
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




