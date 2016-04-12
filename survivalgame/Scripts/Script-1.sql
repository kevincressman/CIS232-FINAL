--Order CityName by ASC
SELECT CityName FROM City ORDER BY Population ASC;

--Order CityName by DESC
SELECT CityName FROM City ORDER BY Population DESC;

--Order CityName by ASC
SELECT CityName FROM City ORDER BY CityName ASC;

--Total
SELECT AVG(Population) AS AveragePopulation, MIN(Population) AS MinimumPopulation,
	   MAX(Population) AS MaxPopulation FROM City;
	   