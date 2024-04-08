package com.library.steps;

import com.library.pages.BasePage;
import com.library.pages.BookPage;
import com.library.pages.DashBoardPage;
import com.library.utility.BrowserUtil;
import com.library.utility.DB_Util;
import io.cucumber.java.en.*;
import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class BooksPageStepDefs {

    BasePage basePage;
    List<String> categoriesFromUI;
    @When("the user navigates to {string} page")
    public void the_user_navigates_to_page(String module) {

        basePage=new BookPage();
        basePage.navigateModule(module);


    }
    @When("the user clicks book categories")
    public void the_user_clicks_book_categories() {

        Select select=new Select(((BookPage)basePage).mainCategoryElement);


        List<WebElement> options = select.getOptions();
         categoriesFromUI= BrowserUtil.getElementsText(options);

         categoriesFromUI.remove("ALL");
        System.out.println("categoriesFromUI = " + categoriesFromUI);

    }
    @Then("verify book categories must match book_categories table from db")
    public void verify_book_categories_must_match_book_categories_table_from_db() {

        DB_Util.runQuery("select name from book_categories");
        List<String> categoriesFromDB = DB_Util.getColumnDataAsList("name");
        System.out.println("categoriesFromDB = " + categoriesFromDB);

        Assert.assertEquals(categoriesFromUI,categoriesFromDB);

    }
}
