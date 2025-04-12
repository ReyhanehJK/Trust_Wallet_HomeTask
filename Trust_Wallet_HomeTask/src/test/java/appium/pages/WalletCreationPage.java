package appium.pages;

import io.appium.java_client.AppiumBy;
import org.openqa.selenium.WebElement;
import appium.utils.AppiumDriverManager;


public class WalletCreationPage extends Page {

    private BackupPage backupPage = new BackupPage();
    private SecretPhraseImportancePage secretInfoPage = new SecretPhraseImportancePage();
    private SecretPhraseDisplayPage secretPhraseDisplayPage  =  new SecretPhraseDisplayPage();
    private SecretPhraseSelectionPage secretPhraseSelectionPage  =  new SecretPhraseSelectionPage();
    private PasscodePage passcodePage  =  new PasscodePage();
    private HomePage homePage  =  new HomePage();
    private AddExistingWalletPage existingWalletPage = new AddExistingWalletPage();
    private SelectNetworkPage selectNetworkPage = new SelectNetworkPage();

    // Locators

    public WebElement createNewWalletButton(){
        return AppiumDriverManager.getDriver().findElement(AppiumBy.xpath("//android.widget.TextView[@text='Create new wallet']"));
    }

    public WebElement addExistingWalletButton(){
        return AppiumDriverManager.getDriver().findElement(AppiumBy.xpath("//android.widget.TextView[@text='Add existing wallet']"));
    }

    public WebElement walletNameInputField(){
        return AppiumDriverManager.getDriver().findElement(AppiumBy.xpath("//android.widget.EditText[@text='Main Wallet 1']"));
    }

    public WebElement secretPhraseInputField(){
        return AppiumDriverManager.getDriver().findElement(AppiumBy.xpath("//android.widget.EditText[@index='3']"));
    }

    public WebElement restoreWalletButton(){
        return AppiumDriverManager.getDriver().findElement(AppiumBy.xpath("//android.widget.TextView[@text='Restore wallet']"));
    }


    // Helpers

    public void createNewWalletWithManualBackup(String[] passcode)  {
        createNewWalletButton().click();
        backupPage.backUpManuallyButton().click();
        assert secretInfoPage.secretPhraseImportancePageHeader().isDisplayed();
        secretInfoPage.verifyAndCheckAllCheckboxes();
        secretInfoPage.continueButton().click();
        secretPhraseDisplayPage.verifySecretPhraseWordsDisplayed();
        secretPhraseDisplayPage.clickCopyToClipboardButton();
        secretPhraseDisplayPage.continueButton().click();
        String copiedSentence = getClipboardContent(AppiumDriverManager.getDriver()); 
        secretPhraseSelectionPage.selectSecretWords(AppiumDriverManager.getDriver(), copiedSentence);
        secretPhraseSelectionPage.confirmButton().click();
        passcodePage.assertPasscodeScreenDisplayed();
        passcodePage.enterPasscode(passcode);
        passcodePage.enterPasscode(passcode);
        homePage.startUsingTrustWalletButton().click();
        allowNotifications();
    }

    public void createNewWalletSkippingBackup(String[] passcode)  {
        createNewWalletButton().click();
        backupPage.backUpSkipButton().click();
        passcodePage.assertPasscodeScreenDisplayed();
        passcodePage.enterPasscode(passcode);
        passcodePage.enterPasscode(passcode);
        homePage.startUsingTrustWalletButton().click();
        allowNotifications();
    }

    public void addExistingWalletWithSecretPhrase(String networkName, String walletName, String[] secretPhrase, String[] passcode) throws InterruptedException  {
        addExistingWalletButton().click();
        existingWalletPage.scretPhraseButton().click();
        selectNetworkPage.selectNetwork(networkName).click();
        walletNameInputField().sendKeys(walletName);
        secretPhraseInputField().sendKeys(secretPhrase);
        hideKeyboard(AppiumDriverManager.getDriver());
        restoreWalletButton().click();
        passcodePage.assertPasscodeScreenDisplayed();
        passcodePage.enterPasscode(passcode);
        passcodePage.enterPasscode(passcode);
        homePage.startUsingTrustWalletButton().click();
        allowNotifications();
    }
}

