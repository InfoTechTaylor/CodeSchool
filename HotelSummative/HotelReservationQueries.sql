USE hotelreservation;

/* Display all rooms, their type and CURRENT rate, with any addons: "It must keep track of all room information" 
& "The System should keep track of room rates"*/

USE HotelReservation;

SELECT r.RoomNum AS RoomNumber, 
		r.FloorID AS FloorNumber, 
        r.OccupantLimit AS MaxOccupants, 
        rt.RoomType,
        rr.Rate AS CurrentRate,
        GROUP_CONCAT(a.AmmenityName SEPARATOR ', ') AS Ammenities
FROM Room r
	INNER JOIN RoomType rt ON r.RoomTypeID = rt.RoomTypeID
    INNER JOIN RoomRate rr ON rt.RoomTypeID = rr.RoomTypeID
    INNER JOIN RoomAmmenity ra ON ra.RoomNum = r.RoomNum
    INNER JOIN Ammenity a ON a.AmmenityID = ra.AmmenityID
WHERE rr.EndDate IS NULL
GROUP by r.RoomNum
ORDER BY r.RoomNum ASC;


/* Display all Available rooms, their type and CURRENT rate */

USE HotelReservation;

SELECT 
    r.RoomNum AS RoomNumber,
    r.FloorID AS FloorNumber,
    r.OccupantLimit AS MaxOccupants,
    rt.RoomType,
    rr.Rate AS CurrentRate
FROM
    Room r
        INNER JOIN
    RoomType rt ON r.RoomTypeID = rt.RoomTypeID
        INNER JOIN
    RoomRate rr ON rt.RoomTypeID = rr.RoomTypeID
        INNER JOIN
    RoomReservation rres ON r.RoomNum = rres.RoomNum
        INNER JOIN
    Reservation res ON rres.ReservationID = res.ReservationID
WHERE
    rr.EndDate IS NULL
        AND res.StartDate < NOW()
        AND res.EndDate < NOW()
        OR res.StartDate > NOW()
        AND res.EndDate > NOW();
        
        
/*Display all Reservations & relative info*/

USE hotelreservation;

SELECT 	res.reservationID,
		DATE_FORMAT(date(res.StartDate), "%m/%d/%Y") AS StartDate, 
		DATE_FORMAT(date(res.EndDate),"%m/%d/%Y") AS EndDate, 
        GROUP_CONCAT(r.RoomNum SEPARATOR ', ') AS RoomNumbers,
        concat(per.FirstName, ' ', per.LastName) AS Customer, 
        TIMESTAMPDIFF(YEAR, per.DOB, CURDATE()) AS CustomerAge,
        per.phone, 
        per.email, 
        concat(gper.FirstName, ' ', gper.LastName) AS Guest,
        TIMESTAMPDIFF(YEAR, gper.DOB, CURDATE()) AS GuestAge
FROM Reservation res
	INNER JOIN Customer cus ON res.CustomerID = cus.CustomerID
	INNER JOIN Person per ON cus.PersonID = per.PersonID
	LEFT OUTER JOIN GuestReservation gres ON res.ReservationID = gres.ReservationID
	LEFT OUTER JOIN Guest g ON gres.GuestID = g.GuestID
	LEFT OUTER JOIN Person gper ON g.PersonID = gper.PersonID
	LEFT OUTER JOIN RoomReservation rres ON rres.ReservationID = res.ReservationID
	LEFT OUTER JOIN Room r ON r.RoomNum = rres.RoomNum
GROUP BY res.ReservationID
ORDER BY res.StartDate, per.LastName, per.FirstName;


/* Display All Promo Codes */
USE HotelReservation;

SELECT 
    PromoCode.PromoCode,
    PromoCode.PromoCodeType,
    PromoCode.PromoAmount,
    CASE
        WHEN
            PromoCode.PromoCodeType = 'PercentOff'
        THEN
            CONCAT(FLOOR((PromoCode.PromoAmount * 100)),
                    '%')
        WHEN PromoCode.PromoCodeType = 'DollarsOff' 
        THEN CONCAT('$', PromoCode.PromoAmount)
    END AS PromoAmount,
    PromoCode.StartDate,
    PromoCode.EndDate
FROM
    PromoCode;
    
    
    
/* Display all bills*/
USE HotelReservation;

SELECT 
    res.ReservationID,
    CONCAT(per.FirstName, ' ', per.LastName) AS Customer,
    DATE_FORMAT(DATE(res.StartDate), '%m/%d/%Y') AS StartDate,
    DATE_FORMAT(DATE(res.EndDate), '%m/%d/%Y') AS EndDate,
    CONCAT('$', rr.Rate) AS RoomRate,
    @NumNights:=DATEDIFF(res.EndDate, res.StartDate) AS TotalNights,
    CONCAT('$',
            @TotalRoomCost:=(SUM(rr.Rate) * @NumNights)) AS TotalRoomCost,
    CONCAT((FLOOR(tax.TaxAmount * 100)), '%') AS TaxPercent,
    CONCAT('$',
            @TotalTaxes:=ROUND((@TotalRoomCost * tax.TaxAmount), 2)) AS TotalTaxes,
    CASE
        WHEN pc.PromoAmount IS NULL THEN CONCAT('$',@PromoAmount:=0)
        ELSE CONCAT('$',@PromoAmount:=pc.PromoAmount)
    END AS PromoAmount,
    CASE
        WHEN addOnR.Rate IS NULL THEN @AddOnCost:=0
        ELSE @AddOnCost:=SUM(addOnR.Rate)
    END AS AddOnRate,
    CASE
        WHEN pc.PromoCodeType = 'PercentOff' THEN CONCAT('$',ROUND((@TotalRoomCost + @TotalTaxes + @AddOnCost) - ((@TotalRoomCost + @TotalTaxes + @AddOnCost) * @PromoAmount), 2))
        WHEN pc.PromoCodeType = 'DollarsOff' THEN CONCAT('$',ROUND((@TotalCost:=(@TotalRoomCost + @TotalTaxes + @AddOnCost) - @PromoAmount), 2))
        ELSE CONCAT('$',ROUND((@TotalRoomCost + @TotalTaxes + @AddOnCost),2))
    END AS TotalCost
FROM
    Reservation res
        INNER JOIN
    BillReservation billres ON res.ReservationID = billres.ReservationID
        INNER JOIN
    Bill bill ON billres.BillID = bill.BillID
        INNER JOIN
    Customer cus ON res.CustomerID = cus.CustomerID
        INNER JOIN
    Person per ON cus.PersonID = per.PersonID
        INNER JOIN
    Tax tax ON bill.TaxID = tax.TaxID
        INNER JOIN
    RoomReservation rres ON rres.ReservationID = res.ReservationID
        INNER JOIN
    Room r ON rres.RoomNum = r.RoomNum
        INNER JOIN
    RoomType rt ON r.RoomTypeID = rt.RoomTypeID
        INNER JOIN
    RoomRate rr ON rt.RoomTypeID = rr.RoomTypeID
        LEFT OUTER JOIN
    BillAddOn badd ON bill.BillID = badd.BillID
        LEFT OUTER JOIN
    AddOn addOn ON badd.AddOnID = addOn.AddOnID
        LEFT OUTER JOIN
    AddOnRate addOnR ON addOnR.AddOnID = addOn.AddOnID
        LEFT OUTER JOIN
    PromoCode pc ON res.PromoCodeID = pc.PromoCodeID
WHERE
    rr.EndDate IS NULL
GROUP BY res.ReservationID;

    
    
       
