package com.booking.project.selenium.tests;

import com.booking.project.selenium.pages.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class AddIntervalsTest extends TestBase{

    private String hostEmail = "n.m.a.ric1912@gmail.com";
    private String hostPassword = "123";
    private static final String TITLE = "Accommodation title";
    private static final String STATE = "Srbija";
    private static final String CITY = "Novi Sad";
    private static final String POSTALCODE = "25000";
    private static final String STREET = "Okrugiceva 10";
    private static final String DESCRIPTION = "Ovo je opis hotela";
    private static final String FIRSTSTARTDATE = "02/15/2024";
    private static final String FIRSTENDDATE = "02/25/2024";
    private static final String FIRSTPRICE = "100";
    private static final String STARTDATEFORDELETE = "03/15/2024";
    private static final String ENDDATEFORDELETE = "03/25/2024";
    private static final String PRICEFORDELETE = "100";
    private static final String SECONDSTARTDATE = "02/15/2024";
    private static final String SECONDENDDATE = "02/25/2024";
    private static final String SECONDPRICE = "100";
    private static final String IMAGE_FILE = "C:\\Users\\nmari\\OneDrive\\Desktop\\pictures apartment\\apart1.jpg";

    @Test
    public void test() throws InterruptedException {

        // Log in
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(hostEmail,hostPassword);

        // Find create accommodation
        FilterAccommodationsPage filterPage = new FilterAccommodationsPage(driver);
        assertTrue(filterPage.isPageOpened());
        filterPage.clickCreteAccommodation();

        // Set text to form
        CreateAccommodationPage createAccommodationPage = new CreateAccommodationPage(driver);
        assertTrue(createAccommodationPage.isPageOpened());
        createAccommodationPage.setTextToForm(TITLE, STATE, CITY, POSTALCODE, STREET, DESCRIPTION, IMAGE_FILE);
        createAccommodationPage.scrollToBottom();
        createAccommodationPage.setDate(FIRSTSTARTDATE,FIRSTENDDATE, FIRSTPRICE);
        createAccommodationPage.submit();

        // Find card
        MyPropertiesPage myPropertiesPage = new MyPropertiesPage(driver);
        assertTrue(myPropertiesPage.isPageOpened());
        assertTrue(myPropertiesPage.scrollToTop());
        myPropertiesPage.clickCard(TITLE);

        // Find update button
        AccommodationInfoPage accommodationInfoPage = new AccommodationInfoPage(driver);
        assertTrue(accommodationInfoPage.isPageOpened(TITLE));
        accommodationInfoPage.scrollToUpdate();
        accommodationInfoPage.clickUpdate();

        // Add and delete row from table
        UpdateAccommodationPage updateAccommodationPage = new UpdateAccommodationPage(driver);
        assertTrue(updateAccommodationPage.isPageOpened());
        assertTrue(updateAccommodationPage.scrollToBottom());
        assertTrue(updateAccommodationPage.checkListSize(1));
        updateAccommodationPage.setDate(STARTDATEFORDELETE,ENDDATEFORDELETE, PRICEFORDELETE);
        assertTrue(updateAccommodationPage.checkListSize(2));
        updateAccommodationPage.deleteDate(1);
        assertTrue(updateAccommodationPage.checkListSize(1));
        updateAccommodationPage.setDate(SECONDSTARTDATE,SECONDENDDATE, SECONDPRICE);
        assertTrue(updateAccommodationPage.checkListSize(2));
        updateAccommodationPage.submit();

        // Find card
        MyPropertiesPage myPropertiesPageSecond = new MyPropertiesPage(driver);
        assertTrue(myPropertiesPageSecond.isPageOpened());
        assertTrue(myPropertiesPageSecond.scrollToTop());
        myPropertiesPageSecond.clickCard(TITLE);

        // Find update button
        AccommodationInfoPage accommodationInfoPageSecond = new AccommodationInfoPage(driver);
        assertTrue(accommodationInfoPageSecond.isPageOpened(TITLE));
        accommodationInfoPage.scrollToUpdate();
        accommodationInfoPageSecond.clickUpdate();

        // Check if ordered
        UpdateAccommodationPage updateAccommodationPageSecond = new UpdateAccommodationPage(driver);
        assertTrue(updateAccommodationPageSecond.isPageOpened());
        assertTrue(updateAccommodationPageSecond.scrollToBottom());
        assertTrue(updateAccommodationPageSecond.checkListSize(1));
    }
}
