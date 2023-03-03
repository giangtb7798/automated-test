package pagesobject;

import core.WebApi;
import io.qameta.allure.Step;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

public class TaskPage extends WebApi {
    @FindBy(xpath = "//div[contains(text(),'nameList')]//parent::a")
    private WebElement taskName;
    @FindBy(xpath = "//input[@data-test=\"task-row-new__input\"]")
    private WebElement fieldTaskName;
    @FindBy(xpath = "//div[contains(text(),'Save')]")
    private WebElement saveTask;
    @FindBy(xpath = "//cu-task-list-header-field[@sorttitle=\"Sort by Task Name\"]")
    private WebElement sortDropdown;

    @FindBy(xpath = "//cu-dropdown-list-item[@data-id=\"sortGroup\"]")
    private WebElement taskNameDropdown;
    private String txtTaskName = "//span[contains(text(),'%s')]";

    @Step("click to task")
    public TaskPage clickToTask(){
        clickToElement(taskName);
        return this;
    }
    @Step("click to task")
    public TaskPage clickToFieldTaskName(){
        clickToElement(fieldTaskName);
        return this;
    }
    @Step("enter task name")
    public TaskPage enterTaskName(String taskName){
        clearTextElement(fieldTaskName);
        sendKeyToElement(fieldTaskName, taskName);
        return this;
    }
    @Step("click to save task")
    public TaskPage clickToSaveTask(){
        clickToElement(saveTask);
        return this;
    }
    @Step("click to sort dropdown")
    public TaskPage clickToSortDropDown(){
        clickToElement(sortDropdown);
        return this;
    }
    @Step("click to sort dropdown by task name")
    public TaskPage clickToSortDropDownByTaskName(){
        clickToElement(taskNameDropdown);
        return this;
    }
    @Step("verify task name created successfully")
    public TaskPage verifyTaskNameCreatedSuccessfully(String taskName){
        Assert.assertTrue(isControlDisplayed(txtTaskName, taskName));
        return this;
    }
}
