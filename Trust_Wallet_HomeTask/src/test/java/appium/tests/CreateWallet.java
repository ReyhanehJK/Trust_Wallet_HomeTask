package appium.tests;


import appium.pages.*;
import org.testng.annotations.Test;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;

@Epic("Wallet")
@Feature("New Wallet")

public class CreateWallet extends Base {

    private WalletCreationPage walletCreationPage =  new WalletCreationPage();;
    private Page page  =  new Page();
    private HomePage home  =  new HomePage();
    private WalletsPage wallet  =  new WalletsPage();

    String[] passcode = {"1", "2", "3", "4", "5", "6"};


    @Test(description = "Creating a new wallet")
    @Description("Verify that a new wallet can be successfully created with manual backup")
    public void testCreateNewWalletManuallyWithManualBackup()  {
        page.closeDeviceSecurityPopUp();
        home.getStartedButton().click();
        home.assertNewUserHomePageDisplayed();
        walletCreationPage.createNewWalletWithManualBackup(passcode);
        wallet.assertWalletAdded("Main Wallet 1");
    }

    @Test(description = "Creating a new wallet without secret phrase")
    @Description("Verify that a new wallet can be successfully created skipping backup")
    public void testCreateNewWalletManuallySkippingBackup()  {
        page.closeDeviceSecurityPopUp();
        home.getStartedButton().click();
        home.assertNewUserHomePageDisplayed();
        walletCreationPage.createNewWalletSkippingBackup(passcode);
        wallet.assertWalletAdded("Main Wallet 1");
    }

}
