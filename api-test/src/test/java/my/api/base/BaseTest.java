package my.api.base;

import httprequest.HttpRequests;
import io.restassured.RestAssured;
import microservices.steps.FolderSteps;
import microservices.steps.ListSteps;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import microservices.steps.SpaceSteps;


public class BaseTest {
    protected SpaceSteps spaceSteps = new SpaceSteps();
    protected FolderSteps folderSteps = new FolderSteps();

    protected ListSteps listSteps = new ListSteps();

    @BeforeMethod
    public void setUp() throws Exception {
        HttpRequests.initReq();
        HttpRequests.setAuthorization("pk_55578828_L0EICZVTIO92ZOPYY9MA7UBI6WZ20JU7");
        HttpRequests.setAllUrl("https://api.clickup.com/api/v2");
    }
    @BeforeSuite
    public void setupSuite() throws Exception {
        HttpRequests.initReq();
    }
    @AfterSuite
    public void tearDownSuite() throws Exception {
        RestAssured.reset();
    }
    @AfterMethod
    public void tearDown() throws Exception {
        RestAssured.reset();
    }
}
