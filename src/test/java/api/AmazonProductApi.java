package api;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import models.Product;
import utils.ConfigReader;

public class AmazonProductApi {
    private static final String API_KEY = ConfigReader.getProperty("amazonProductApi.key");
    private static final String BASE_URL = ConfigReader.getProperty("amazonProductApi.base.url");

    public Product getProduct(String asin) {
        JsonPath json = RestAssured
                        .given()
                        .queryParam("engine", "amazon_product")
                        .queryParam("asin", asin)
                        .queryParam("api_key", API_KEY)
                        .queryParam("amazon_domain", "amazon.in")
                        .when()
                        .get(BASE_URL + "/search.json")
                        .then()
                        .statusCode(200)
                        .extract()
                        .jsonPath();

        return mapToProduct(json, asin);
    }

    private Product mapToProduct(JsonPath json, String asin) {
        Product product = new Product();
        product.setAsin(asin);
        product.setTitle(json.getString("product_results.title"));
        product.setRating(json.getString("product_results.rating"));
        product.setPrice(json.getString("product_results.price"));
        return product;
    }

}
