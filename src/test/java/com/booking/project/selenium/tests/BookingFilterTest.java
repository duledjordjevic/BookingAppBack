package com.booking.project.selenium.tests;

import com.booking.project.selenium.pages.FilterAccommodationsPage;
import com.booking.project.selenium.pages.LoginPage;
import org.junit.jupiter.api.Test;


import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class BookingFilterTest extends TestBase {
    private String emailGuest = "nma.ric1912@gmail.com";
    private String passwordGuest = "123";
    private List<String> amenities = new ArrayList<>(List.of("Breakfast"));
    private String accommodationType = "Both options";
    private String city = "Nis";
    private String startDate = "1/25/2024";
    private String endDate = "1/27/2024";
    private String numOfGuests = "6";
    private int minPrice = 100;
    private int maxPrice = 600;

    @Test
    public void test() throws InterruptedException {
        LoginPage loginPage = new LoginPage(driver);

        loginPage.login(emailGuest,passwordGuest);

        FilterAccommodationsPage filterPage = new FilterAccommodationsPage(driver);

        assertTrue(filterPage.isPageOpened());

        filterPage.putDataInHeaderFilter(city,startDate,endDate,numOfGuests);
        filterPage.putDataInSideFilter(amenities,accommodationType);
        filterPage.moveDualSlider(minPrice,maxPrice);
        filterPage.clickSearch();
        assertTrue(filterPage.isSearchResultLoad());
        assertTrue(filterPage.checkSearchByAddress(city));
    }
}
