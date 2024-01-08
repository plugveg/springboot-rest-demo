package org.setsuma.springboot.demorest.tests;

import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;
import static org.apache.commons.lang3.RandomStringUtils.randomNumeric;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.setsuma.springboot.demorest.domain.BannedBooks;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import io.restassured.RestAssured;
import io.restassured.response.Response;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class SpringBootBootstrapLiveTestBannedBooks {

    @LocalServerPort
    private int port;
       
    private String getApiRoot() {
        return "http://localhost:"+port+"/api/bannedbooks";
    }

    @Test
    public void whenGetAllBannedBooks_thenOK() {
        final Response response = RestAssured.get(getApiRoot());
        assertEquals(HttpStatus.OK.value(), response.getStatusCode());
    }

    @Test
    public void whenGetBannedBooksByTitle_thenOK() {
        final BannedBooks bannedbooks = createRandomBannedBooks();
        createBannedBooksAsUri(bannedbooks);

        final Response response = RestAssured.get(getApiRoot() + "?title=" + bannedbooks.getTitle());
        assertEquals(HttpStatus.OK.value(), response.getStatusCode());
        assertTrue(response.as(List.class)
            .size() > 0);
    }

    @Test
    public void whenGetCreatedBannedBooksById_thenOK() {
        final BannedBooks bannedbooks = createRandomBannedBooks();
        final String location = createBannedBooksAsUri(bannedbooks);

        final Response response = RestAssured.get(location);
        assertEquals(HttpStatus.OK.value(), response.getStatusCode());
        assertEquals(bannedbooks.getTitle(), response.jsonPath()
            .get("title"));
    }

    @Test
    public void whenGetNotExistBannedBooksById_thenNotFound() {
        final Response response = RestAssured.get(getApiRoot() + "/" + randomNumeric(4));
        assertEquals(HttpStatus.NOT_FOUND.value(), response.getStatusCode());
    }

    // POST
    @Test
    public void whenCreateNewBannedBooks_thenCreated() {
        final BannedBooks bannedbooks = createRandomBannedBooks();

        final Response response = RestAssured.given()
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .body(bannedbooks)
            .post(getApiRoot());
        assertEquals(HttpStatus.CREATED.value(), response.getStatusCode());
    }

    @Test
    public void whenInvalidBannedBooks_thenError() {
        final BannedBooks bannedbooks = createRandomBannedBooks();
        bannedbooks.setAuthor(null);

        final Response response = RestAssured.given()
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .body(bannedbooks)
            .post(getApiRoot());
        assertEquals(HttpStatus.BAD_REQUEST.value(), response.getStatusCode());
    }

    @Test
    public void whenUpdateCreatedBannedBooks_thenUpdated() {
        final BannedBooks bannedbooks = createRandomBannedBooks();
        final String location = createBannedBooksAsUri(bannedbooks);

        bannedbooks.setId(Long.parseLong(location.split("api/bannedbooks/")[1]));
        bannedbooks.setAuthor("newAuthor");
        Response response = RestAssured.given()
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .body(bannedbooks)
            .put(location);
        assertEquals(HttpStatus.OK.value(), response.getStatusCode());

        response = RestAssured.get(location);
        assertEquals(HttpStatus.OK.value(), response.getStatusCode());
        assertEquals("newAuthor", response.jsonPath()
            .get("author"));

    }

    @Test
    public void whenDeleteCreatedBannedBooks_thenOk() {
        final BannedBooks bannedbooks = createRandomBannedBooks();
        final String location = createBannedBooksAsUri(bannedbooks);

        Response response = RestAssured.delete(location);
        assertEquals(HttpStatus.OK.value(), response.getStatusCode());

        response = RestAssured.get(location);
        assertEquals(HttpStatus.NOT_FOUND.value(), response.getStatusCode());
    }

    // ===============================

    private BannedBooks createRandomBannedBooks() {
        final BannedBooks bannedbooks = new BannedBooks();
        bannedbooks.setTitle(randomAlphabetic(10));
        bannedbooks.setAuthor(randomAlphabetic(15));
        return bannedbooks;
    }

    private String createBannedBooksAsUri(BannedBooks bannedbooks) {
        final Response response = RestAssured.given()
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .body(bannedbooks)
            .post(getApiRoot());
        return getApiRoot() + "/" + response.jsonPath()
            .get("id");
    }

}