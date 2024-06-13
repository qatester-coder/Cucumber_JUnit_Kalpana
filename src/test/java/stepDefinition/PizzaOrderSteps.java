package stepDefinition;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Listeners;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

@Listeners(listeners.MyListeners.class)

public class PizzaOrderSteps {

	WebDriver driver;

	@Before
	public void launchBrowser() {
		System.getProperty("webdriver.gecko.driver", "geckodriver.exe");
		driver = new FirefoxDriver();
	}

	@After
	public void closeBrowser() {
		// driver.close();
	}

	@Given("User launch Pizzahut application with {string}")
	public void user_launch_Pizzahut_application_with(String strURL) {
		driver.navigate().to(strURL);
	}

	@When("User wait for auto location black pop up screen")
	public void user_wait_for_auto_location_black_pop_up_screen() {

	}

	@Then("User close the pop up screen")
	public void user_close_the_pop_up_screen() {

	}

	@Then("User see pop up for delivery asking for enter location")
	public void user_see_pop_up_for_delivery_asking_for_enter_location() {

	}

	@Then("User type address as {string}")
	public void user_type_address_as(String enterLocation) {
		// WebElement LocationForDelivery = driver
		// .findElement(By.xpath("/html/body/div[1]/div/div[4]/div/div/div/div[2]/form/div/div[1]/input"));

		// WebElement LocationForDelivery = driver
		// .findElement(By.cssSelector("[placeholder='Enter your location for
		// delivery']"));

		// WebElement LocationForDelivery = driver.findElement(By
		// .cssSelector("[placeholder='Enter your location for delivery'],
		// [aria-label='Location for delivery']"));
		WebDriverWait wait = new WebDriverWait(driver, 10);
		WebElement LocationForDelivery = wait.until(ExpectedConditions.elementToBeClickable(
				By.cssSelector("input.typography-5.search.search--hut.input.border-0.py-15.px-10.min-w-50")));

		// WebElement LocationForDelivery = driver.findElement(
		// By.cssSelector("input.typography-5.search.search--hut.input.border-0.py-15.px-10.min-w-50"));

		if (LocationForDelivery.isDisplayed()) {
			LocationForDelivery.sendKeys(enterLocation);
		} else {
			System.out.println("Location for delivery");
		}

		long timeoutInSeconds = Duration.ofSeconds(10).toMillis();
		driver.manage().timeouts().implicitlyWait(timeoutInSeconds, TimeUnit.MILLISECONDS);
	}

	@Then("User select first auto populate drop down option")
	public void user_select_first_auto_populate_drop_down_option() {

		// WebElement dropDownOption = driver.findElement(By.xpath("//button[2]"));
		WebElement dropDownOption = driver
				.findElement(By.cssSelector("button:nth-child(2) div:nth-child(2) div:nth-child(2)"));

		long timeoutInSeconds = Duration.ofSeconds(10).toMillis();
		driver.manage().timeouts().implicitlyWait(timeoutInSeconds, TimeUnit.MILLISECONDS);
		dropDownOption.click();

	}

	@When("User navigate to deails page")
	public void user_navigate_to_deails_page() {

	}

	@Then("User validate vegetarian radio button flag is off")
	public void user_validate_vegetarian_radio_button_flag_is_off() {
		WebElement vegRadioButton = driver.findElement(By.xpath(
				"//div[@class='hidden 2xl:flex w-full']//span[@class='py-4 px-5 border rounded-full flex items-center cursor-pointer bg-grey-light border-grey-light justify-start']"));
		if (vegRadioButton.isSelected()) {
			System.out.println("vegetarian radio button flag is ON");
		} else {
			System.out.println("vegetarian radio button flag is off");
		}

	}

	@Then("User clicks on Pizzas menu bar option")
	public void user_clicks_on_Pizzas_menu_bar_option() {
		WebElement clkPizzaMenubar = driver.findElement(
				By.xpath("//a[contains(@class,'capitalize lg:border-r')]//span[contains(text(),'Pizzas')]"));
		clkPizzaMenubar.click();
	}

	@When("User select add button of any pizza from Recommended")
	public void user_select_add_button_of_any_pizza_from_Recommended() {
		// WebElement btnAddPizza = driver.findElement(By.xpath(
		// "//button[contains(@data-synth,'button--margherita-bestseller-pan-personal--one-tap')]//span//span[contains(text(),'Add')]"));
		WebElement btnAddPizza = driver.findElement(By.xpath(
				"//button[contains(@data-synth,'button--mazedar-makhni-paneer-recommended-pan-personal--one-tap')]//span[2]"));
		btnAddPizza.click();
	}

	@Then("User see that the pizza is getting added under Your Basket")
	public void user_see_that_the_pizza_is_getting_added_under_Your_Basket() {
		WebElement addIntoBasket = driver.findElement(By.xpath("//*[@id=\"basket\"]"));
		String strBasketItem = addIntoBasket.getText();
		System.out.println(strBasketItem);
	}

	@Then("User validate pizza price plus Tax is checkout price")
	public void user_validate_pizza_price_plus_Tax_is_checkout_price() {
		// WebElement checkoutPrice = driver.findElement(By.xpath(
		// "//button[contains(@class,'justify-between')]//span[contains(@class,'absolute
		// inset-0 flex-center')]"));
		WebElement checkoutAmount = driver
				.findElement(By.xpath("//*[@id=\"app\"]/div/div[1]/div[2]/div/div[2]/div[2]"));

		String strcheckoutAmount = checkoutAmount.getText();
		System.out.println(strcheckoutAmount);

		// Subtotal
		WebElement SubTotal = driver
				.findElement(By.xpath("//*[@id=\"app\"]/div/div[1]/div[2]/div/div[2]/div[2]/div/div[1]"));
		String strSubTotal = SubTotal.getText();
		System.out.println(strSubTotal);
		// Restaurant Handling Charges
		WebElement HandlingCharges = driver
				.findElement(By.xpath("//*[@id=\"app\"]/div/div[1]/div[2]/div/div[2]/div[2]/div/div[2]"));
		String strHandlingCharges = HandlingCharges.getText();
		System.out.println(strHandlingCharges);

		// Total Tax
		WebElement TotalTax = driver
				.findElement(By.xpath("//*[@id=\"app\"]/div/div[1]/div[2]/div/div[2]/div[2]/div/div[3]"));
		String strTotalTax = TotalTax.getText();
		System.out.println(strTotalTax);

		// Amount Payable
		WebElement AmountPay = driver
				.findElement(By.xpath("//*[@id=\"app\"]/div/div[1]/div[2]/div/div[2]/div[2]/div/div[4]"));
		String strAmountPay = AmountPay.getText().replaceAll("[^\\d.]", "");
		// String ap = strAmountPay.replaceAll("[^\\d.]", "");
		Float flAmountPay = Float.parseFloat(strAmountPay);
		System.out.println(flAmountPay);

		// Checkout Price
		WebElement CheckoutPrice = driver
				.findElement(By.xpath("//*[@id=\"app\"]/div/div[1]/div[2]/div/div[2]/div[3]/div/div/div/a/span[3]"));
		String strCheckoutPrice = CheckoutPrice.getText().replaceAll("[^\\d.]", "");
		// String cp = strCheckoutPrice.replaceAll("[^\\d.]", "");
		Float flCheckoutPrice = Float.parseFloat(strCheckoutPrice);
		System.out.println(flCheckoutPrice);

		if (Math.abs(flAmountPay - flCheckoutPrice) < 0.01) {
			System.out.println("Matching: pizza price plus Tax is checkout price");
		} else {
			System.out.println("Not Matching: pizza price plus Tax is checkout price");
		}

		// SoftAssertion
		// System.out.println("Start Assertion");
		// SoftAssert sa = new SoftAssert();
		// sa.assertEquals(flAmountPay, flCheckoutPrice);
		// System.out.println("End Assertion");
		// sa.assertAll();

		// HardAssertion

		// System.out.println("Start Assertion");
		// Assert.assertEquals(flAmountPay, flCheckoutPrice);
		// System.out.println("End Assertion");
	}

	@Then("User validate checkout button contains Item count")
	public void user_validate_checkout_button_contains_Item_count() {
		WebElement ItemCount = driver
				.findElement(By.xpath("//*[@id=\"app\"]/div/div[1]/div[2]/div/div[2]/div[3]/div/div/div/a/span[1]"));
		String strItemCount = ItemCount.getText();
		System.out.println("Item Count: " + strItemCount);

	}

	@Then("User validate checkout button contains total price count")
	public void user_validate_checkout_button_contains_total_price_count() {
		WebElement CheckoutPrice1 = driver
				.findElement(By.xpath("//*[@id=\"app\"]/div/div[1]/div[2]/div/div[2]/div[3]/div/div/div/a/span[3]"));
		String strCheckoutPrice1 = CheckoutPrice1.getText();
		System.out.println("Checkout Price:" + strCheckoutPrice1);

	}

	@Then("User clicks on Drinks option")
	public void user_clicks_on_Drinks_option() {
		WebElement drinksTab = driver
				.findElement(By.xpath("/html/body/div[1]/div/div[1]/div[1]/div[3]/div[1]/div/a[7]"));
		drinksTab.click();
	}

	@Then("User select Pepsi option to add into the Basket")
	public void user_select_Pepsi_option_to_add_into_the_Basket() {
		WebElement drinkPepsi = driver.findElement(
				By.xpath("/html/body/div[1]/div/div[1]/div[1]/div[3]/div[2]/div[2]/span/div/a[1]/div[3]/div/button"));
		drinkPepsi.click();
	}

	@Then("User see 2 items are showing under checkout button")
	public void user_see_items_are_showing_under_checkout_button() {
		WebElement ItemCount1 = driver
				.findElement(By.xpath("//*[@id=\"app\"]/div/div[1]/div[2]/div/div[2]/div[3]/div/div/div/a/span[1]"));
		String strItemCount1 = ItemCount1.getText();
		System.out.println("Item Count: " + strItemCount1);

	}

	@Then("User see total price is now more than before")
	public void user_see_total_price_is_now_more_than_before() {
		WebElement CheckoutPrice2 = driver
				.findElement(By.xpath("//*[@id=\"app\"]/div/div[1]/div[2]/div/div[2]/div[3]/div/div/div/a/span[3]"));
		String strCheckoutPrice2 = CheckoutPrice2.getText();
		System.out.println("Checkout Price:" + strCheckoutPrice2);

	}

	@Then("User remove the Pizza item from Basket")
	public void user_remove_the_Pizza_item_from_Basket() {
		WebElement removePizza = driver
				.findElement(By.xpath("/html/body/div[1]/div/div[1]/div[2]/div/div[1]/div[3]/div/div[1]/button[2]"));
		removePizza.click();
	}

	@Then("see Price tag got removed from the checkout button")
	public void see_Price_tag_got_removed_from_the_checkout_button() {
		// WebElement CheckoutPrice3 = driver
		// .findElement(By.xpath("//*[@id=\"app\"]/div/div[1]/div[2]/div/div[2]/div[3]/div/div/div/a/span[3]"));
		// String strCheckoutPrice3 = CheckoutPrice3.getText();
		// System.out.println("Checkout Price:" + strCheckoutPrice3);

	}

	@Then("User see 1 item showing in checkout button")
	public void user_see_item_showing_in_checkout_button() {
		// WebElement ItemCount2 = driver
		// .findElement(By.xpath("//*[@id=\"app\"]/div/div[1]/div[2]/div/div[2]/div[3]/div/div/div/a/span[1]"));
		// String strItemCount2 = ItemCount2.getText();
		// System.out.println("Item Count: " + strItemCount2);

	}

	@Then("User Clicks on Checkout button")
	public void user_Clicks_on_Checkout_button() {
		WebElement clkCheckoutButton = driver
				.findElement(By.xpath("/html/body/div[1]/div/div[1]/div[2]/div/div[2]/div[3]/div[2]"));
		clkCheckoutButton.click();
	}

	@Then("User see minimum order required pop up is getting displayed")
	public void user_see_minimum_order_required_pop_up_is_getting_displayed() {
		WebElement minimumOrderPopup = driver.findElement(By.xpath("/html/body/div[7]/div/div/div/button[2]"));
		boolean b = minimumOrderPopup.isDisplayed();
		System.out.println(b);
	}

}
