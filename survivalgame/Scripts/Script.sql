-- Selecting Coffee by Price
SELECT * FROM Coffee WHERE Price > 10;

-- Coffee = 7.95
SELECT * FROM Coffee WHERE Price = 7.95;

--Coffee not equal to 7.95
SELECT * FROM Coffee WHERE Price <> 7.95;

-- Find bolivian dark
SELECT * FROM Coffee WHERE Description = 'Bolivian Dark';

SELECT * FROM Coffee WHERE upper (Description) = upper ('Bolivian Dark');

--WildCard Searches
SELECT * FROM Coffee WHERE Description LIKE 'Sumatra%';

SELECT * FROM Coffee WHERE Description LIKE '&Dark%';

SELECT * FROM Coffee WHERE ProdNum LIKE '2_-00_%';

--AND & OR
SELECT * FROM Coffee WHERE Price > 5.00 AND Price < 10.00;

SELECT * FROM Coffee WHERE Price <= 5.00 OR Price >= 10.00;

--Order
SELECT Description,ProdNum,Price FROM Coffee WHERE Price > 10.00 ORDER BY Price DESC,
ProdNum DESC;

--MATH Functions
SELECT AVG(Price) AS AveragePrice, 
	   SUM(Price) AS TotalPrice, 
	   MIN(Price) AS MinimumPrice,
	   MAX(Price) AS MaxPrice, 
	   COUNT(*) AS CoffeeCount
FROM Coffee 
WHERE ProdNum 
LIKE '2_-00_%';




