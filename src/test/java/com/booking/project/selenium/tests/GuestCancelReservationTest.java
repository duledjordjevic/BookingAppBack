package com.booking.project.selenium.tests;

import com.booking.project.selenium.pages.FilterAccommodationsPage;
import com.booking.project.selenium.pages.GuestReservationsPage;
import com.booking.project.selenium.pages.LoginPage;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class GuestCancelReservationTest extends  TestBase{
    private String emailGuest= "n.m.aric1912@gmail.com";
    private String passwordGuest= "123";

    @Test
    public void test() throws InterruptedException {
        LoginPage loginPage = new LoginPage(driver);

        loginPage.login(emailGuest, passwordGuest);

        FilterAccommodationsPage filterPage = new FilterAccommodationsPage(driver);

        assertTrue(filterPage.isPageOpened());

        filterPage.goToGuestReservations();

        GuestReservationsPage guestReservationsPage = new GuestReservationsPage(driver);

        assertTrue(guestReservationsPage.isPageOpened());
        assertTrue(guestReservationsPage.cancelAccepted());
    }
}
