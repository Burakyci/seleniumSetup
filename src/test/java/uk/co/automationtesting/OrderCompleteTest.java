package uk.co.automationtesting;
import base.Hooks;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.*;

import pageObject.ShopHomepage;
import pageObjects.Homepage;
import pageObjects.ShopContentPanel;
import pageObjects.ShopProductPage;

import java.io.IOException;

@Listeners(resource.Listeners.class)
public class OrderCompleteTest  extends Hooks {
    public OrderCompleteTest() throws IOException{
        super();
    }



    @Test
    public void endToEndTest() throws InterruptedException, IOException {

        Homepage home = new Homepage();

        Thread.sleep(222);
        WebElement testStoreLink = this.getDriver().findElement(By.cssSelector("[href='http\\:\\/\\/teststore\\.automationtesting\\.co\\.uk\\/']"));
        ((JavascriptExecutor) this.getDriver()).executeScript("arguments[0].scrollIntoView(true);", testStoreLink);
        testStoreLink.click();


        ShopHomepage shopHomePage = new ShopHomepage();
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
        shopContentPanel.getCheckoutBtn().click();

        pageObjects.ShoppingCart shoppingCart = new pageObjects.ShoppingCart();
        shoppingCart.getHavePromo().click();
        shoppingCart.getPromoTextbox().sendKeys("20OFF");
        shoppingCart.getPromoAddBtn().click();
        shoppingCart.getProceedCheckoutBtn().click();
        Thread.sleep(4000);

        pageObjects.OrderFormPersInfo OFPInfo = new pageObjects.OrderFormPersInfo();
        OFPInfo.getGenderMr().click();
        Thread.sleep(4000);

        OFPInfo.getFirstNameField().sendKeys("Burak");
        OFPInfo.getLastnameField().sendKeys("KALAYCI");

        Thread.sleep(4000);
        OFPInfo.getEmailField().sendKeys("johnsmith@test.com");
        OFPInfo.getTermsConditionsCheckbox().click();

        OFPInfo.getContinueBtn().click();
        Thread.sleep(4000);

        pageObjects.OrderFormDelivery orderFormDelivery= new pageObjects.OrderFormDelivery();
        orderFormDelivery.getAddressCompField().sendKeys("Adress/sss/");
        orderFormDelivery.getAddressField().sendKeys("test test/test");
        orderFormDelivery.getCityField().sendKeys("istanbukl");
        Select select = new Select(orderFormDelivery.getStateDropdown());
        select.selectByVisibleText("Texas");
        Thread.sleep(4000);

        orderFormDelivery.getPostcodeField().sendKeys("12288");
        Thread.sleep(4000);
        orderFormDelivery.getContinueBtn().click();
        System.out.println("clicked get continued Btn");

        pageObjects.OrderFormShippingMethod orderFormShippingMethod = new pageObjects.OrderFormShippingMethod();
        orderFormShippingMethod.getDeliveryMsgTextbox().sendKeys("hi");
        System.out.println("getDeliveryMsgTextbox clicked");
        orderFormShippingMethod.getContinueBtn().click();

        pageObjects.OrderFormPayment orderFormPayment = new pageObjects.OrderFormPayment();
        orderFormPayment.getPayByCheckRadioBtn().click();
        orderFormPayment.getTermsConditionsCheckbox().click();
        Thread.sleep(4000);

        orderFormPayment.getOrderBtn().click();
    }
}
