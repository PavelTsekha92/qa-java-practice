import io.restassured.RestAssured;
import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ApiTests {

    @Test
    public void firstApiTest() {

        RestAssured.baseURI = "https://petstore.swagger.io/v2";

        given()
                .when()
                .get("/pet/findByStatus?status=available")
                .then()
                .statusCode(200);
    }

        @Test
        public void createPetTest() {

            RestAssured.baseURI = "https://petstore.swagger.io/v2";

            Pet pet = new Pet();
            pet.id = 123456;
            pet.name = "Barsik";
            pet.status = "available";

            // given()
                    //.contentType("application/json")
                    //.body(pet)
                    //.when()
                    //.post("/pet")
                    //.then()
                    //statusCode(200);
            Pet responsePet = given()
                    .contentType("application/json")
                    .body(pet)
                    .when()
                    .post("/pet")
                    .then()
                    .statusCode(200)
                    .extract()
                    .as(Pet.class);

            assertEquals(pet.id, responsePet.id);
            assertEquals(pet.name, responsePet.name);
            assertEquals(pet.status, responsePet.status);
    }
}