package appium.tests;

import appium.pages.*;
import org.testng.annotations.Test;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;

@Epic("Crypto Assets")
@Feature("Manage Crypto")

public class ManageCrypto extends Base {

    private Page page  =  new Page();
    private HomePage home  =  new HomePage();
    private ManageCryptoPage manageCrypto  =  new ManageCryptoPage();
    private WalletCreationPage wallet  =  new WalletCreationPage();

    String[] passcode = {"1", "2", "3", "4", "5", "6"};

    @Test(description = "Add a crypto and remove a crypto")
    @Description("Verify user can add new crypto and it shows up in the Crypto tab && Verify user can remove new crypto and it is removed from the Crypto tab")
    public void testAddRemoveNewCrypto() throws InterruptedException {
        page.closeDeviceSecurityPopUp();
        home.getStartedButton().click();
        home.assertNewUserHomePageDisplayed();
        wallet.createNewWalletSkippingBackup(passcode);
        home.walletDisplayed("Main Wallet 1");
        home.clickManageCryptoButton();
        manageCrypto.assertManageCryptoPageDisplayed();
        manageCrypto.toggleCrypto("Bitcoin"); // Remove
        manageCrypto.toggleCrypto("ADA"); // Add
        manageCrypto.backButton().click();
        manageCrypto.assertCryptoPresent("ADA", true);
        manageCrypto.assertCryptoPresent("BTC", false);
    }
}
