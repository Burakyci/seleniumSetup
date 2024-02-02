package uk.co.automationtesting;
import base.Hooks;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pageObjects.ShopContentPanel;
import pageObjects.ShopHomepage;
import pageObjects.ShopProductPage;
import pageObjects.ShoppingCart;

import java.io.IOException;
import java.time.Duration;

@Listeners(resource.Listeners.class)
public class AddRemoveItemBasket extends Hooks {
    public AddRemoveItemBasket() throws IOException{
        super();
    }


    @Test
    public void endToEndTest() throws InterruptedException, IOException {

        pageObjects.Homepage home = new pageObjects.Homepage();

        Thread.sleep(222);
        WebElement testStoreLink = getDriver().findElement(By.cssSelector("[href='http\\:\\/\\/teststore\\.automationtesting\\.co\\.uk\\/']"));
        ((JavascriptExecutor) getDriver()).executeScript("arguments[0].scrollIntoView(true);", testStoreLink);
        testStoreLink.click();


        pageObjects.ShopHomepage shopHomePage = new ShopHomepage();
        shopHomePage.getProdOne().click();
        ShopProductPage shopProductPage = new ShopProductPage();
        Thread.sleep(2000);

        Select option = new Select(shopProductPage.getSizeOption());
        option.selectByVisibleText("M");
       shopProductPage.getQuantIncrease().click();
        Thread.sleep(2000);

        shopProductPage.getAddToCartBtn().click();
        Thread.sleep(2000);

        ShopContentPanel shopContentPanel = new ShopContentPanel();
        shopContentPanel.getContinueShopBtn().click();
        shopProductPage.getHomepageLink().click();
        shopHomePage.getProdTwo().click();
        shopProductPage.getAddToCartBtn().click();
        shopContentPanel.getCheckoutBtn().click();

        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.getDeleteItemTwo().click();

        waitForElementInVisible(shoppingCart.getDeleteItemTwo(), Duration.ofSeconds(10));

        System.out.println(shoppingCart.getTotalAmount().getText());
        Assert.assertEquals(shoppingCart.getTotalAmount().getText(),"$45.23");

    }
}
