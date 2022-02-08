package API_requests;

import Util.PropertyFileReader;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Properties;


import static Util.UtilConstants.*;
import static io.restassured.RestAssured.given;

public class jobs_GET {
    Properties prop;
    public jobs_GET() throws Exception {
        prop = PropertyFileReader.readPropertiesFile();

    }

    @Test
    public void getallPrograms() {
        RestAssured.baseURI=prop.getProperty(CONST_URL);
        RestAssured.basePath=prop.getProperty(CONST_PATH);
        Response response = given().when().get(RestAssured.baseURI+RestAssured.basePath);
        int status_code = response.statusCode();


        System.out.println(response.asString());
        Assert.assertEquals(status_code,200);

    }

}

