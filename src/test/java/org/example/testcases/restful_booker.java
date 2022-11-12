package org.example.testcases;

import io.restassured.path.json.JsonPath;
import org.example.configurations.Endpoints;
import org.example.configurations.EnvGlobalVals;

import org.example.payloads.bookings;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class restful_booker {
    public static String check_status;

    @Test(priority = 1)
    public void createToken(){
        EnvGlobalVals.response =
                given()
                    .contentType("application/json")
                    .body(bookings.token())
                    .post(Endpoints.baseUrl_booker+Endpoints.auth)
                    .then().statusCode(200).extract().response();
        JsonPath jsonPath = EnvGlobalVals.response.jsonPath();
        EnvGlobalVals.booker_token = jsonPath.get("token");
        if(jsonPath.get("token") instanceof String){
            System.out.println("The acquired token is: " +EnvGlobalVals.booker_token);
        }
    }

//    @Test
//    public void fetch_booking_ids(){
//        //myEnvGlobals.bookingids =
//        EnvGlobalVals.response =
//        given()
//                .contentType("application/json")
//                .queryParam("checkin=2022-06-25")
//                .queryParam("checkout=2022-06-25")
//                .get(Endpoints.baseUrl_booker +Endpoints.booker)
//                .then().statusCode(200).extract().response();
//        JsonPath jsonPath = EnvGlobalVals.response.jsonPath();
//        EnvGlobalVals.booker_pageSize = EnvGlobalVals.response.body().path("list.size()", new String[0]);
//        EnvGlobalVals.BIDs = new int[EnvGlobalVals.booker_pageSize];
//        for(int j = 0; j < EnvGlobalVals.booker_pageSize; j++){
//            EnvGlobalVals.BIDs[j] = jsonPath.get("[" + j + "].bookingid");
//            Assert.assertNotNull(jsonPath.get("[" + j + "].bookingid"));
//            //System.out.println(jsonPath.get("[" + j + "].bookingid"));
//        }
//    }
    /*
    @Test
    public void fetch_booking_details(){
        System.out.println("Testinggggg 1");
        for(int i = 0 ; i < myEnvGlobals.booker_pageSize; i++){
            System.out.println("Entered loop "+i);
            myEnvGlobals.response =
                    given()
                            .get(myEndpoints.baseUrl_booker + myEndpoints.booker +"/"+myEnvGlobals.BIDs[i])
                            .then().statusCode(200).extract().response();
            System.out.println("Waiting for response path "+i);
            JsonPath jsonPath = myEnvGlobals.response.jsonPath();
            check_status = String.valueOf(jsonPath.get("depositpaid"));
            System.out.println("Going for assertion "+i);
            Assert.assertTrue(check_status,true);
            System.out.println("Assertion Passed "+i);
        }
    }*/

   /* @Test
    public void url_encoded_booking_booking(){
       // myEnvGlobals.response =
                given()
                        .contentType("application/x-www-form-urlencoded")
                        .formParam("firstname", "Muntaha")
                        .formParam("lastname","Nadeem")
                        .formParam("totalprice", 1200)
                        .formParam("depositpaid",true)
                        .formParam("bookingdates.checkin", "2022-06-01")
                        .formParam("bookingdates.checkout", "2022-06-02")
                        .formParam("additionalneeds","none")
                        .post(myEndpoints.baseUrl_booker + myEndpoints.booker)
                        .then().log().all();
        //Assert.assertNotNull(myEnvGlobals.response);

    }*/

    @Test(priority = 2)
    public void create_booking(){
        EnvGlobalVals.actual_response =
        given()
                .contentType("application/json")
               // .cookie("token="+myEnvGlobals.booker_token)
                .body(bookings.booking())
                .post(Endpoints.baseUrl_booker + Endpoints.booker)
                .then().statusCode(200).extract().response();
        JsonPath jsonPath = EnvGlobalVals.actual_response.jsonPath();
        EnvGlobalVals.booking_id = jsonPath.get("bookingid");
        Assert.assertNotNull(EnvGlobalVals.booking_id);
        System.out.println("The booking id of the created booking is = " +EnvGlobalVals.booking_id);
        Assert.assertNotNull(EnvGlobalVals.actual_response);
    }

    @Test(priority = 3)
    public void update_booking(){
       EnvGlobalVals.updated_response =
                given()
                        .contentType("application/json")
                        .body(bookings.updated_booking())
                        .cookies("token",EnvGlobalVals.booker_token)
                        .put(Endpoints.baseUrl_booker + Endpoints.booker + "/" +EnvGlobalVals.booking_id)
                        .then().statusCode(200).extract().response();
        System.out.println(EnvGlobalVals.updated_response.asString());
        JsonPath jsonPath = EnvGlobalVals.updated_response.jsonPath();
        Assert.assertNotSame(EnvGlobalVals.updated_response,EnvGlobalVals.actual_response);
    }

    @Test(priority = 4)
    public void partially_updated_booking(){
        EnvGlobalVals.partially_updated_response =
                given()
                        .contentType("application/json")
                        .body(bookings.booking("2022-06-29", "2022-06-29"))
                        .cookies("token",EnvGlobalVals.booker_token)
                        .patch(Endpoints.baseUrl_booker + Endpoints.booker + "/" +EnvGlobalVals.booking_id)
                        .then().statusCode(200).extract().response();
        System.out.println(EnvGlobalVals.partially_updated_response.asString());
        JsonPath jsonPath = EnvGlobalVals.partially_updated_response.jsonPath();
        Assert.assertNotSame(EnvGlobalVals.partially_updated_response,EnvGlobalVals.actual_response);
    }

    @Test(priority = 5)
    public void delete_booking(){
    given()
            .cookies("token",EnvGlobalVals.booker_token)
            .when()
                .delete(Endpoints.baseUrl_booker + Endpoints.booker + "/" +EnvGlobalVals.booking_id)
                .then().statusCode(201);
    }

    @Test(priority = 6)
    public void check_Deleted_booking(){
        given()
                .contentType("application/json")
                .get(Endpoints.baseUrl_booker + Endpoints.booker + "/" +EnvGlobalVals.booking_id)
                .then().statusCode(404);
    }
}
