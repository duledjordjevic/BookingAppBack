package com.booking.project.selenium.tests;

import com.booking.project.selenium.pages.FilterAccommodationsPage;
import com.booking.project.selenium.pages.HostReservationsPage;
import com.booking.project.selenium.pages.LoginPage;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class HostAcceptDeclineReservationTest extends TestBase{
    private String emailHost= "djordjevic.dusan24@gmail.com";
    private String passwordHost= "123";
    private boolean isAccept = false;

    @Test
    public void test() throws InterruptedException {
        LoginPage loginPage = new LoginPage(driver);

        loginPage.login(emailHost, passwordHost);

        FilterAccommodationsPage filterPage = new FilterAccommodationsPage(driver);
        assertTrue(filterPage.isPageOpened());

        filterPage.goToHostReservations();

        HostReservationsPage hostReservationsPage = new HostReservationsPage(driver);

        assertTrue(hostReservationsPage.isPageOpened());
        assertTrue(hostReservationsPage.acceptOrDeclineReservation(isAccept));
    }
}
