package my.api.tests;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import microservices.models.foldermodels.FolderInput;
import microservices.models.foldermodels.FolderResponse;
import microservices.models.spacemodels.SpaceInput;
import my.api.base.BaseTest;
import org.testng.annotations.Test;

import java.net.HttpURLConnection;

@Epic("Click up")
@Feature("api-folder")
public class FolderTests extends BaseTest {

    @Test(description = "verify that create folder successfully")
    public void create_folder_successfully() throws Exception {
        String name = "study";
        FolderInput folderInput = new FolderInput(name);
        FolderResponse folderResponse = (FolderResponse) folderSteps
                .when_createFolder(folderInput)
                .validateResponse(HttpURLConnection.HTTP_OK)
                .saveResponseObject(FolderResponse.class);
        //Bỏ trống trường name
        String emptyName = "";
        folderInput = new FolderInput(emptyName);
        folderSteps.when_createFolder(folderInput)
                .validateResponse(HttpURLConnection.HTTP_BAD_REQUEST);

        //name = 1
        int nameNumber = 1;
        folderInput = new FolderInput(nameNumber);
        folderSteps.when_createFolder(folderInput)
                .validateResponse(HttpURLConnection.HTTP_BAD_REQUEST);

        //clean data
        folderSteps
                .when_deleteFolder(folderResponse.getId())
                .validateResponse(HttpURLConnection.HTTP_OK);
    }
    @Test(description = "get folder successfully")
    public void get_folder_successfully() throws Exception {
        String name = "study";
        FolderInput folderInput = new FolderInput(name);
        FolderResponse folderResponse = (FolderResponse) folderSteps
                .when_createFolder(folderInput)
                .validateResponse(HttpURLConnection.HTTP_OK)
                .saveResponseObject(FolderResponse.class);

        folderResponse = (FolderResponse) folderSteps
                .when_getFolderById(folderResponse.getId())
                .validateResponse(HttpURLConnection.HTTP_OK)
                .saveResponseObject(FolderResponse.class);

        folderSteps.then_verifyNameSpaceIsCorrespondingWithNameCreated(name, folderResponse.getName());

        //clean data
        folderSteps
                .when_deleteFolder(folderResponse.getId())
                .validateResponse(HttpURLConnection.HTTP_OK);
    }
    @Test(description = "verify that update folder successfully")
    public void update_folder_successfully() throws Exception {
        String name = "study";
        FolderInput folderInput = new FolderInput(name);
        FolderResponse folderResponse = (FolderResponse) folderSteps
                .when_createFolder(folderInput)
                .validateResponse(HttpURLConnection.HTTP_OK)
                .saveResponseObject(FolderResponse.class);

        // name = "";
        name = "";
        folderInput = new FolderInput(name);
        folderSteps
                .when_updateFolder(folderInput, folderResponse.getId())
                .validateResponse(HttpURLConnection.HTTP_OK)
                .saveResponseObject(FolderResponse.class);

        // name = 1;
        int nameNumber = 1;
        folderInput = new FolderInput(nameNumber);
        folderSteps
                .when_updateFolder(folderInput, folderResponse.getId())
                .validateResponse(HttpURLConnection.HTTP_BAD_REQUEST)
                .saveResponseObject(FolderResponse.class);

        //clean data
        folderSteps
                .when_deleteFolder(folderResponse.getId())
                .validateResponse(HttpURLConnection.HTTP_OK);
    }
    @Test(description = "verify that delete folder successfully")
    public void delete_folder_successfully() throws Exception {
        String name = "study";
        FolderInput folderInput = new FolderInput(name);
        FolderResponse folderResponse = (FolderResponse) folderSteps
                .when_createFolder(folderInput)
                .validateResponse(HttpURLConnection.HTTP_OK)
                .saveResponseObject(FolderResponse.class);

        //delete data
        folderSteps
                .when_deleteFolder(folderResponse.getId())
                .validateResponse(HttpURLConnection.HTTP_OK);
    }

}
