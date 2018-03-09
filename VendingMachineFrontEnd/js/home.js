$(document).ready(function() {
    clearTextBoxes();
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
                $('#vendingMachineItemRowDiv').append('<div class="col-lg-4 col-sm-4 panel panel-primary">' +
                    '<p class="text-left">' + inventoryItem.id + '</p>' +
                    '<p class="text-center">' + inventoryItem.name + '</p>' +
                    '<p class="text-center">$' + inventoryItem.price + '</p>' +
                    '<p class="text-center">Quantity Left: '+ inventoryItem.quantity +'</p></div>');
            });
        },
        error: function(){
            alert('FAILURE');
        }
    }) // end get all inventory
} // end loadInventory()

function clearTextBoxes(){
    $('#currentMoneyAmt').val('');
}

function addMoney(amount){
    var currentAmt = $('#currentMoneyAmt').val();
    currentAmt = +currentAmt + amount;
    $('#currentMoneyAmt').val(currentAmt.toFixed(2));
}

