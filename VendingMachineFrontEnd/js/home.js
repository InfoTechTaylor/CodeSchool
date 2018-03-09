$(document).ready(function() {
    loadInventory();
});

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
    })
}

