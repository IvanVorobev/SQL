package ru.netology.web.data;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.Keys;

import static com.codeborne.selenide.Selenide.$;

public class LoginPage {
    private SelenideElement loginField = $("[data-test-id=login] input");
    private SelenideElement passwordField = $("[data-test-id=password] input");
    private SelenideElement loginButton = $("[data-test-id=action-login]");

    public VerificationPage validLogin(DataHelper.AuthInfo info) {
        loginField.setValue(info.getLogin());
        passwordField.setValue(info.getPassword());
        loginButton.click();
        return new VerificationPage();
    }

    public void invalidPassword() {
        loginField.setValue(DataHelper.getAuthInfo().getLogin());
        passwordField.setValue(DataHelper.getAuthInfoWithInvalidPassword().getLogin());
        loginButton.click();
        clearPasswordField();
        passwordField.setValue(DataHelper.getAuthInfoWithInvalidPassword().getLogin());
        loginButton.click();
        clearPasswordField();
        passwordField.setValue(DataHelper.getAuthInfoWithInvalidPassword().getLogin());
        loginButton.click();
        clearPasswordField();
        passwordField.setValue(DataHelper.getAuthInfoWithInvalidPassword().getLogin());
        loginButton.click();
    }

    public void loginButtonShouldNotBeVisible() {
        loginButton.shouldNotBe(Condition.visible);
    }

    public void clearPasswordField() {
        passwordField.doubleClick();
        passwordField.sendKeys(Keys.DELETE);
    }
}
