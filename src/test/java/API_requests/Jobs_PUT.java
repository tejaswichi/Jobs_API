package API_requests;

import Util.ExcelUtil;
import Util.PropertyFileReader;
import static io.restassured.RestAssured.given;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import static Util.UtilConstants.*;

public class Jobs_PUT {
    Properties prop;

    public Jobs_PUT() throws Exception {
        prop = PropertyFileReader.readPropertiesFile();

    }
    @DataProvider
    public Object[][] putData() throws IOException {
        String path = prop.getProperty(CONST_EXCELFILEPATH);
        int rowCount = ExcelUtil.getRowCount(path,CONST_JOBSPUTSHEET);
        int colCount = ExcelUtil.getCellCount(path,CONST_JOBSPUTSHEET,rowCount);
        String getprogData[][] = new String[rowCount][colCount];

        for (int i=1;i<=rowCount;i++){
            for(int j=0;j<colCount;j++) {
                getprogData[i-1][j]=ExcelUtil.getCellData(path,CONST_JOBSPUTSHEET,i,j);

            }
        }

        return getprogData;

    }

    @Test(dataProvider="putData")
    public void updateJob(String Job_Id,String Job_Title,String Job_Company_Name,String Job_Location,
                      String Job_Type,String Job_Posted_time,String Job_Description,String StatusCode_expected) {
        //Specify base URI
        RestAssured.baseURI=prop.getProperty(CONST_URL);
        RestAssured.basePath=prop.getProperty(CONST_PATH);


        RequestSpecification request = RestAssured.given();
        JSONObject requestParam=new JSONObject();
        if (Job_Id!=null) {
            requestParam.put("Job Id", Job_Id);
        }
        if (Job_Title!=null) {
            requestParam.put("Job Title",Job_Title);
        }
        if (Job_Company_Name!=null) {
            requestParam.put("Job Company Name",Job_Company_Name);
        }
        if (Job_Location!=null) {
            requestParam.put("Job Location",Job_Location);
        }
        if (Job_Type!=null) {
            requestParam.put("Job_Type",Job_Type);
        }
        if (Job_Posted_time!=null) {
            requestParam.put("Job Posted time",Job_Posted_time);
        }
        if (Job_Description!=null) {
            requestParam.put("Job Description",Job_Description);
        }

        request.header("Content-Type","application/json");
        request.body(requestParam.toJSONString());
        //Response object
        Response response=request.request(Method.PUT);
        String responsebody=response.getBody().asString();
        Assert.assertEquals(response.getStatusCode(),Integer.parseInt(StatusCode_expected));

        if (StatusCode_expected.equals(Success_Status)){
            if (Job_Id!=null) {
                Assert.assertEquals(responsebody.contains(Job_Id),true);
            }
            if (Job_Title!=null) {
                Assert.assertEquals(responsebody.contains(Job_Title),true);
            }
            if (Job_Company_Name!=null) {
                Assert.assertEquals(responsebody.contains(Job_Company_Name),true);
            }
            if (Job_Location!=null) {
                Assert.assertEquals(responsebody.contains(Job_Location),true);
            }
            if (Job_Type!=null) {
                Assert.assertEquals(responsebody.contains(Job_Type),true);
            }
            if (Job_Posted_time!=null) {
                Assert.assertEquals(responsebody.contains(Job_Posted_time),true);
            }
            if (Job_Description!=null) {
                Assert.assertEquals(responsebody.contains(Job_Description),true);
            }

        }


        System.out.println("The Response Body is :"+responsebody);


    }


}

