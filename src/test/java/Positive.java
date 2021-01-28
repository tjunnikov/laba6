import io.restassured.RestAssured;
import io.restassured.http.ContentType;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;


public class Positive {

    private String token;

    @BeforeTest
    public void beforeTest() {
        RestAssured.baseURI = "https://gorest.co.in";
        token = "k4s-Ih5Py-N6HBrG37QmXggKA4R9lAuQkCnd";
    }

    //получить список всех пользователей
    @Test
    public void GetUsers() {
        System.out.println("Получить список всех пользователей");
        given()
                .auth().oauth2(token)
                .log().all()
                .when()
                .request("GET", "public-api/users")
                .then()
                .log().status()
                .statusCode(200);
        System.out.println("");
    }
    //получить список пользователей с указанным именем
    @Test
    public void GetUserName()
    {
        System.out.println("Тест 2");
        System.out.println("Получить список пользователей с указанным именем.");
        given()
                .auth().oauth2(token)
                .when()
                .request("GET", "/public-api/users?access-token=" + token +
                        "&first_name=Polina")
                .then()
                .log().all()
                .statusCode(200);
    }
    //создать нового пользователя
    @DataProvider(name = "DataCreateUser") public
    Object[][] DataCreateUser()
    {
        return new Object[][] {{"Po", "Poli", "female", "po.pol@gmail.com", "+7 (900)00-00-00"}};
    }
    @Test(dataProvider = "DataCreateUser")

    public void test_create_new_user( String first_name, String last_name, String gender, String email, String phone)
    {
        System.out.println("Cоздать нового пользователя.");
        Users Users1 = new Users(first_name, last_name,  gender, email, phone);
        given().auth().oauth2(token)
                .log().all()
                .contentType(ContentType.JSON)
                .body(Users1)
                .when()
                .post( "/public-api/users")
                .then()
                .log().all()
                .assertThat()
                .body("result.first_name", equalTo(Users1.getFirst_name()))
                .body("result.last_name", equalTo(Users1.getLast_name()))
                .body("result.gender", equalTo(Users1.getGender()))
                .body("result.email", equalTo(Users1.getEmail()))
                .body("result.phone", equalTo(Users1.getPhone()))
                .statusCode(302);

    }


    //получить пользователя по его ID
        @Test
        public void get_user_ID()
        {
            String id = "17873";
            System.out.println("Получить пользователя по его ID.");
            given()
                    .auth().oauth2(token)
                    .when()
                    .request("GET", "/public-api/users/" + id +"?access-token=" + token)
                    .then()
                    .log().body()
                    .statusCode(200);
        }
        Integer id = 17874;

    //Меняем номер телефона
    @DataProvider(name = "data_change_user")
    public Object[][] data_change_ID()
    {
        return new Object[][] {{"Po", "Poli", "female", "po.pol@gmail.com", "+7 (900)00-00-00"}};
    }
    @Test(dataProvider = "data_change_user")
    public void put_user_ID(int id,String first_name, String last_name, String gender, String email, String phone )
    {
        System.out.println("Изменить пользователя с указанным ID.");
        Users Users2 =  new Users(first_name, last_name,  gender, email, phone);
        given().auth().oauth2(token)
                .contentType(ContentType.JSON)
                .body(Users2)
                .when().put("public-api/users/" + id)
                .then()
                .log().all()
                .statusCode(200);
    }
            @Test
            public void test_delete_ID() {
            Integer id = 2345;
            System.out.println("Удалить пользователя с указанным ID.");
            given().auth().oauth2(token)
                    .contentType(ContentType.JSON)
                    .when().delete("public-api/users/" + id)
                    .then().log().all()
                    .body("result", equalTo(null))
                    .statusCode(200);
        }
}