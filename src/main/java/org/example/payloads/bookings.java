package org.example.payloads;

public class bookings {
    //For Restful_booker APIs
    public static String token(){
        return "{\n" +
                "    \"username\" : \"admin\",\n" +
                "    \"password\" : \"password123\"\n" +
                "}";
    }
    public static String booking() {

        return "{\n" +
                "    \"firstname\" : \"Hassan\",\n" +
                "    \"lastname\" : \"Nadeem\",\n" +
                "    \"totalprice\" : 1200,\n" +
                "    \"depositpaid\": true,\n" +
                "    \"bookingdates\" : {\"checkin\": \"2022-01-01\", \"checkout\": \"2022-03-01\"},\n" +
                "    \"additionalneeds\" : \"none\"\n" +
                "}";

    }
    public static String updated_booking() {

        return "{\n" +
                "    \"firstname\" : \"Mustafa\",\n" +
                "    \"lastname\" : \"Nadeem\",\n" +
                "    \"totalprice\" : 1000,\n" +
                "    \"depositpaid\": true,\n" +
                "    \"bookingdates\" : {\"checkin\": \"2022-06-01\", \"checkout\": \"2022-06-11\"},\n" +
                "    \"additionalneeds\" : \"Lunch\"\n" +
                "}";

    }
    public static String booking(String Checkin, String Checkout) {

        return "{\n" +
                "    \"firstname\" : \"Hassan\",\n" +
                "    \"lastname\" : \"Nadeem\",\n" +
                "    \"totalprice\" : 1200,\n" +
                "    \"depositpaid\": true,\n" +
                "    \"bookingdates\" : {\"checkin\": \""+Checkin+"\", \"checkout\": \""+Checkout+"\"},\n" +
                "    \"additionalneeds\" : \"none\"\n" +
                "}";

    }


}
