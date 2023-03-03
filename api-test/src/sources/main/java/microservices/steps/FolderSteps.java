package microservices.steps;

import core.BaseApi;
import httprequest.HttpRequests;
import io.qameta.allure.Step;
import microservices.models.foldermodels.FolderInput;

import java.io.IOException;

import static constaints.ClickUpConstants.SPACE_ID;
import static constaints.ClickUpConstants.TEAM_ID;
import static main.java.constaints.EndPoints.*;
import static org.assertj.core.api.Assertions.assertThat;

public class FolderSteps extends BaseApi {
    @Step("create Folder")
    public FolderSteps when_createFolder(FolderInput folderInput) throws IOException {
        sendPost(CREATE_FOLDER_URI, folderInput, "space_id", SPACE_ID);
        return this;
    }
    @Step("update Folder by folder id")
    public FolderSteps when_updateFolder(FolderInput folderInput, String folderId) throws IOException {
        sendPut(UPDATE_FOLDER_URI, folderInput, "folder_id", folderId);
        return this;
    }
    @Step("delete Folder by folder id")
    public FolderSteps when_deleteFolder(String folderId) throws IOException {
        sendDelete(DELETE_FOLDER_URI, "folder_id", folderId);
        return this;
    }
    @Step("get Folder by id")
    public FolderSteps when_getFolderById(String folderId) throws IOException {
        sendGet(GET_FOLDER_URI, "folder_id", folderId);
        return this;
    }
    @Step("verify name folder just created is correct")
    public FolderSteps then_verifyNameSpaceIsCorrespondingWithNameCreated(String actual, String expected) {
        assertThat(actual)
                .as("name is not correct")
                .isEqualTo(expected);
        return this;
    }
}
