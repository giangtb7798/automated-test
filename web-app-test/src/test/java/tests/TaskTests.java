package tests;

import base.AbstractWebTest;
import microservices.models.listmodels.FolderInput;
import microservices.models.listmodels.FolderResponse;
import microservices.steps.ListSteps;
import org.testng.annotations.Test;
import pagesobject.LoginPage;
import pagesobject.NavigationPage;
import pagesobject.TaskPage;

import java.io.IOException;
import java.net.HttpURLConnection;

public class TaskTests extends AbstractWebTest {
    ListSteps listSteps = new ListSteps();

    @Test(description = "create task")
    public void create_task() throws IOException {
        String listName = "nameList";
        String taskName = "taskName";
        String spaceName = "do not edit";
        String email = "ntruonggiangtb98@gmail.com";
        String password = "07071998Gg";
        FolderInput folderInput = new FolderInput(listName);
        FolderResponse folderResponse = (FolderResponse) listSteps
                .when_createListInSpace(folderInput)
                .validateResponse(HttpURLConnection.HTTP_OK)
                .saveResponseObject(FolderResponse.class);

        new LoginPage()
                .goToLoginPage()
                .enterEmailAddress(email)
                .enterPassword(password)
                .clickToSubmitBtn();

        NavigationPage navigationPage = new NavigationPage();
        TaskPage taskPage = new TaskPage();

        navigationPage
                .clickToSpace(spaceName);

        taskPage
                .clickToFieldTaskName()
                .enterTaskName(taskName)
                .clickToSaveTask()
                .verifyTaskNameCreatedSuccessfully(taskName);

        //delete list
        listSteps
                .when_deleteList(folderResponse.getId())
                .validateResponse(HttpURLConnection.HTTP_OK);
    }

    @Test(description = "Sort by task name")
    public void sort_by_task_name() throws IOException {
        String listName = "nameList";
        String taskName = "taskName";
        String spaceName = "do not edit";
        String email = "ntruonggiangtb98@gmail.com";
        String password = "07071998Gg";

        new LoginPage()
                .goToLoginPage()
                .enterEmailAddress(email)
                .enterPassword(password)
                .clickToSubmitBtn();

        NavigationPage navigationPage = new NavigationPage();
        TaskPage taskPage = new TaskPage();

        navigationPage
                .clickToSpace(spaceName);

        taskPage
                .clickToSortDropDown()
                .clickToSortDropDownByTaskName();

    }
}
