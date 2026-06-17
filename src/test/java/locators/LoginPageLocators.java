package locators;

import org.openqa.selenium.By;

public class LoginPageLocators {
    private LoginPageLocators() {};

    public static class LoginPage{
        public static By loginUsername = By.id("ap_email_login");
        public static By continueButton = By.id("continue");
        public static By loginPassword = By.id("ap_password");
        public static By signInButton = By.id("signInSubmit");
        public static By forgotPassword = By.xpath("//a[text()='Forgot password?']");
        public static By forgotPasswordUsername = By.id("ap_email");
        public static By otpTextBox = By.name("otpCode");
        public static By submitCodeButton = By.id("cvf-submit-otp-button");

        public static By invalidPassErrorHeading = By.xpath("//*[@id=\"auth-error-message-box\"]/div/h4");
        public static By invalidPassErrorMsg = By.xpath("//*[@id=\"auth-error-message-box\"]/div/div");
        public static By loginErrorAlert = By.xpath(
                "//div[@role='alert' and not(contains(@class,'aok-hidden'))]//div[contains(@class,'a-alert-content')]");
    }

    public static class RegistrationPage{
        public static By newToAmazonMessage = By.xpath("//*[@id=\"intent-confirmation-container\"]/h1");
        public static By createNewAccountButton = By.id("intention-submit-button");
        public static By thankYouHeader = By.xpath("//h1[normalize-space()='Thank you']");
    }

}
