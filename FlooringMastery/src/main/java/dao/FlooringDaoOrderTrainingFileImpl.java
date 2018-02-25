package dao;

import dto.Order;
import dto.Product;
import dto.Tax;

import java.io.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class FlooringDaoOrderTrainingFileImpl implements FlooringDaoOrder {

    private Map<LocalDate, HashMap<Integer, Order>> ordersByDateMap = new HashMap<>();
    private static final String STRING_DELIMITER = ",";

    @Override
    public List<Order> retrieveAllOrdersByDate(LocalDate orderDate) throws FlooringPersistenceException {

        if (!ordersByDateMap.containsKey(orderDate)) {
            loadOrders(orderDate);  // throws null pointer exception if file does not exist
        }

//        if(ordersByDateMap.get(orderDate).get(1) == null){
//            throw new FlooringPersistenceException("No orders for given date. ");
//        }
        return new ArrayList<>(ordersByDateMap.get(orderDate).values());
    }

    @Override
    public Order retrieveOrderByDateAndId(LocalDate orderDate, int orderNumber) throws FlooringPersistenceException {
        if(!ordersByDateMap.containsKey(orderDate)) {
            loadOrders(orderDate);
        }
        Map<Integer, Order> ordersMap = ordersByDateMap.get(orderDate);


        return ordersMap.get(orderNumber);
    }

    @Override
    public Order createOrder(LocalDate orderDate, Order orderObj) throws FlooringPersistenceException {
        // generate ID
        orderObj.setOrderNumber(generateOrderNumber(orderDate));
        if(ordersByDateMap.get(orderDate) == null) {
            ordersByDateMap.put(orderDate, new HashMap<>());
            ordersByDateMap.get(orderDate).put(orderObj.getOrderNumber(), orderObj);
        } else {
            ordersByDateMap.get(orderDate).put(orderObj.getOrderNumber(), orderObj);
        }
        //writeOrders();
        return orderObj;
    }

    @Override
    public void updateOrder(LocalDate orderDate, Order orderObj) throws FlooringPersistenceException {
        ordersByDateMap.get(orderDate).replace(orderObj.getOrderNumber(), orderObj);
        //writeOrders();
    }

    @Override
    public void removeOrder(LocalDate orderDate, int orderNum) throws FlooringPersistenceException{
        ordersByDateMap.get(orderDate).remove(orderNum);
        //writeOrders();
    }

    private int generateOrderNumber(LocalDate dateForFile) throws FlooringPersistenceException{

        int highestUsedOrderNumber = 0;

        List<Order> allOrders = retrieveAllOrdersByDate(dateForFile);

        if(allOrders.size() != 0) {
            for (Order currentOrder : allOrders) {
                if (currentOrder.getOrderNumber() > highestUsedOrderNumber) {
                    highestUsedOrderNumber = currentOrder.getOrderNumber();
                }
            }
        }


        return highestUsedOrderNumber + 1;
//        return 0;

    }

    private void loadOrders(LocalDate orderDate) throws FlooringPersistenceException {

        Scanner scanner = null;
        // convert local date to string and create a filename with the Orders_MMDDYYYY.txt format
        String filename = "Orders_" + orderDate.format(DateTimeFormatter.ofPattern("MMddyyyy")) + ".txt";

        try{
            scanner = new Scanner(new BufferedReader(new FileReader(filename)));
        }catch(FileNotFoundException e){
            ordersByDateMap.put(orderDate, new HashMap<>());
            //throw new FlooringPersistenceException("No orders exist for given date.");
        }

        String currentLine;
        String[] currentTokens;

        ordersByDateMap.put(orderDate, new HashMap<>());

        if(scanner != null) {
            while (scanner.hasNextLine()) {
                currentLine = scanner.nextLine();
                currentTokens = currentLine.split(STRING_DELIMITER);

                // 0 order number - int
                // 1 customer name - String
                // 2 State, - String
                // 3 TaxRate, - BD
                // 4 ProductType, - String
                // 5 Area, - BD
                // 6 Material - CostPerSquareFoot, - BD
                // 7 LaborCost - PerSquareFoot,- BD
                // 8 total MaterialCost, -BD
                // 9 total LaborCost,-BD
                // 10 total Tax, -BD
                // 11 total Cost - BD

                // create order object
                Order currentOrder = new Order();
                currentOrder.setOrderNumber(Integer.parseInt(currentTokens[0]));
                currentOrder.setCustomerName(currentTokens[1]);
                currentOrder.setTaxObject(new Tax(currentTokens[2], new BigDecimal(currentTokens[3])));
                currentOrder.setProductObject(new Product(currentTokens[4], new BigDecimal(currentTokens[6]),
                        new BigDecimal(currentTokens[7])));
                currentOrder.setArea(new BigDecimal(currentTokens[5]));
                currentOrder.setTotalMaterialCost(new BigDecimal(currentTokens[8]));
                currentOrder.setTotalLaborCost(new BigDecimal(currentTokens[9]));
                currentOrder.setTotalTax(new BigDecimal(currentTokens[10]));
                currentOrder.setTotalCost(new BigDecimal(currentTokens[11]));
                currentOrder.setOrderDate(orderDate);

                // add currentOrder to inner map within the OrderDate outer map entry
                ordersByDateMap.get(orderDate).put(currentOrder.getOrderNumber(), currentOrder);
            }

            scanner.close();
        }


    }


    public void saveOrders() throws FlooringPersistenceException {
//        writeOrders();
    }


}
