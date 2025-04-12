package appium.tests;

import appium.pages.*;

import javax.management.DescriptorKey;

import org.testng.annotations.Test;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;

@Epic("Wallet")
@Feature("Existing Wallet")

public class AddExistingWallet extends Base {

    private WalletCreationPage walletCreationPage =  new WalletCreationPage();;
    private Page page  =  new Page();
    private HomePage home  =  new HomePage();
    private WalletsPage wallet  =  new WalletsPage();

    String[] passcode = {"1", "2", "3", "4", "5", "6"};
    String[] secretPhrase = {"economy combine cement patrol increase idle cage wisdom chat illegal approve deal"};

    @Test(description = "Add an existing wallet")
    @DescriptorKey("Verify an existing wallet can be added using secret phrase or QR code")
    public void testAddExistingWalletWithSecretPhrase() throws InterruptedException  {
        page.closeDeviceSecurityPopUp();
        home.getStartedButton().click();
        home.assertNewUserHomePageDisplayed();
        walletCreationPage.addExistingWalletWithSecretPhrase("Multi-coin wallet", "Existing Wallet", secretPhrase,  passcode);
        wallet.assertWalletAdded("Existing Wallet");
    }
}
