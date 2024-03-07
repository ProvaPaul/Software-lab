package org.example;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * This class manages a collection of City objects.
 */
public class CityList {
    private List<City> cityCollection = new ArrayList<>();
    /**
     * Checks if the city exists in the collection.
     *
     * @param city The city to check.
     * @return True if the city exists, false otherwise.
     */
    public boolean contains(City city) {
        return cityCollection.contains(city);
    }

    /**
     * Adds a city to the collection if it doesn't already exist.
     *
     * @param newCity The city to be added.
     * @throws IllegalArgumentException if the city already exists.
     */
    public void addCity(City newCity) {
        if (cityCollection.contains(newCity)) {
            throw new IllegalArgumentException("City already exists.");
        }
        cityCollection.add(newCity);
    }

    /**
     * Deletes a city from the collection if it exists.
     *
     * @param cityToRemove The city to be removed.
     * @throws IllegalArgumentException if the city doesn't exist.
     */
    public void delete(City cityToRemove) {
        if (cityCollection.contains(cityToRemove)) {
            cityCollection.remove(cityToRemove);
        } else {
            throw new IllegalArgumentException("City does not exist.");
        }
    }

    /**
     * Returns the count of cities in the collection.
     *
     * @return The number of cities in the collection.
     */
    public int getCityCount() {
        return cityCollection.size();
    }

    /**
     * Retrieves a list of cities sorted according to the specified criteria.
     *
     * @param sortingCriteria The criteria for sorting cities.
     *                        Use {@link SortingCriteria#BY_CITY} to sort by city name,
     *                        or {@link SortingCriteria#BY_PROVINCE} to sort by state name.
     * @return A sorted list of cities based on the given criteria.
     */
    public List<City> getSortedCities(SortingCriteria sortingCriteria) {
        List<City> sortedCityList = new ArrayList<>(cityCollection);

        // Define comparator based on the sort criteria
        Comparator<City> cityComparator = null;
        if (sortingCriteria == SortingCriteria.BY_CITY) {
            cityComparator = Comparator.comparing(City::getCityName);
        } else if (sortingCriteria == SortingCriteria.BY_PROVINCE) {
            cityComparator = Comparator.comparing(City::getProvinceName);
        }

        // Sort the list using comparator if it's not null
        if (cityComparator != null) {
            sortedCityList.sort(cityComparator);
        }

        return sortedCityList;
    }

    public enum SortingCriteria {
        BY_CITY,
        BY_PROVINCE
    }
}