package org.example;



import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class CityListTest {




    @Test
    public void testDeleteCityException() {
        // Arrange
        CityList cityManager = new CityList();
        City city1 = new City("Dhaka", "Bangladesh");

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> cityManager.delete(city1));
    }

    @Test
    public void testCityCount() {
        // Arrange
        CityList cityManager = new CityList();
        City city1 = new City("Dhaka", "Bangladesh");
        City city2 = new City("Paris", "France");

        // Act
        cityManager.addCity(city1);
        cityManager.addCity(city2);

        // Assert
        assertEquals(2, cityManager.getCityCount());
    }

    @Test
    public void testSortCitiesByProvince() {
        // Arrange
        CityList cityManager = new CityList();
        City city1 = new City("Dhaka", "Bangladesh");
        City city2 = new City("Paris", "France");
        City city3 = new City("Washington, D.C.", "USA");

        // Act
        cityManager.addCity(city1);
        cityManager.addCity(city2);
        cityManager.addCity(city3);
        List<City> sortedCities = cityManager.getSortedCities(CityList.SortingCriteria.BY_PROVINCE);

        // Assert
        assertSortedByProvince(sortedCities);
    }

    @Test
    public void testGetCitiesByCityName() {
        // Arrange
        CityList cityList = new CityList();
        City city1 = new City("Dhaka", "Bangladesh");
        City city2 = new City("Paris", "France");
        City city3 = new City("Washington, D.C.", "USA");

        // Act
        cityList.addCity(city1);
        cityList.addCity(city2);
        cityList.addCity(city3);
        List<City> sortedCities = cityList.getSortedCities(CityList.SortingCriteria.BY_CITY);

        // Assert
        assertSortedByCityName(sortedCities);
    }



    // Helper method to check if the list is sorted by province name
    private void assertSortedByProvince(List<City> cities) {
        int n = cities.size();
        for (int i = 0; i < n - 1; i++) {
            assertTrue(cities.get(i).getProvinceName().compareTo(cities.get(i + 1).getProvinceName()) <= 0);
        }
    }

    // Helper method to check if the list is sorted by city name
    private void assertSortedByCityName(List<City> cities) {
        int n = cities.size();
        for (int i = 0; i < n - 1; i++) {
            assertTrue(cities.get(i).getCityName().compareTo(cities.get(i + 1).getCityName()) <= 0);
        }
    }

    // Helper method to check if the list is sorted by state name
    private void assertSortedByStateName(List<City> cities) {
        int n = cities.size();
        for (int i = 0; i < n - 1; i++) {
            assertTrue(cities.get(i).getProvinceName().compareTo(cities.get(i + 1).getProvinceName()) <= 0);
        }
    }
}
