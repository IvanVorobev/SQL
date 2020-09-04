package ru.netology.web.test;

import lombok.val;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;
import ru.netology.web.data.DataHelper;
import ru.netology.web.data.LoginPage;
import ru.netology.web.data.SQLUtils;

import java.sql.SQLException;

import static com.codeborne.selenide.Selenide.open;

public class DeadlineTest {

    @Test
    void shouldEnterDashboardPage() throws SQLException {
        open("http://localhost:9999");
        val loginPage = new LoginPage();
        val authInfo = DataHelper.getAuthInfo();
        val verificationPage = loginPage.validLogin(authInfo);
        val verificationCode = SQLUtils.getVerificationCode();
        val dashboardPage = verificationPage.validVerify(verificationCode);
        dashboardPage.dashboardPageVisible();
    }

    @Test
    void loginButtonShoulBeDisableIfEnterInvalidPasswordOverThreeTimes() {
        open("http://localhost:9999");
        val loginPage = new LoginPage();
        loginPage.invalidPassword();
        loginPage.loginButtonShouldNotBeVisible();
    }

    @AfterAll
    static void clean() throws SQLException {
        SQLUtils.cleanAuthCodesTable();
    }
}

