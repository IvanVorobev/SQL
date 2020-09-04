package ru.netology.web.data;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class DashboardPage {
    private static SelenideElement heading = $("[data-test-id=dashboard]");

    public void dashboardPageVisible() {
        heading.shouldBe(Condition.visible);
    }
}
