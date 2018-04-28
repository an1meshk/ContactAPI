package com.animesh.contactapi.controller;

import com.animesh.contactapi.vo.Contact;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import static org.junit.Assert.assertTrue;
import static org.springframework.test.web.client.ExpectedCount.once;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withBadRequest;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withNoContent;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

/**
 * Created by Animeesh Kumar on 14-04-2018.
 */
@RunWith(SpringRunner.class)
@SpringBootConfiguration
public class ContactControllerTest {

    private RestTemplate restTemplate = null;
    private MockRestServiceServer server = null;

    @Before
    public void setup() {
        restTemplate = new RestTemplate();
        server = MockRestServiceServer.bindTo(restTemplate).build();
    }

    @Test
    public void retrieveContact_success() {
        server.expect(once(), requestTo("/contact")).andExpect(method(HttpMethod.GET))
                .andRespond(withSuccess("{\n" +
                        "  \"id\" : 1,\n" +
                        "  \"name\" : \"john\",\n" +
                        "  \"company\" : \"Google\",\n" +
                        "  \"email\" : \"john@gmail.com\",\n" +
                        "  \"birthday\" : \"1990-04-15\",\n" +
                        "  \"personalNumber\" : \"8472011234\",\n" +
                        "  \"workNumber\" : \"2242011234\",\n" +
                        "  \"city\" : \"Chicago\",\n" +
                        "  \"state\" : \"IL\"\n" +
                        "}", MediaType.APPLICATION_JSON_UTF8));

        Contact contact = restTemplate.getForObject("/contact", Contact.class,"John");

        assertTrue(contact != null);
    }

    @Test(expected = HttpClientErrorException.class)
    public void retrieveContact_httpError() {
        server.expect(once(), requestTo("/contact")).andExpect(method(HttpMethod.GET))
                .andRespond(withBadRequest());

        Contact contact = restTemplate.getForObject("/contact", Contact.class,"John");

    }

    @Test
    public void retrieveContact_noContent() {
        server.expect(once(), requestTo("/contact")).andExpect(method(HttpMethod.GET))
                .andRespond(withNoContent());

        Contact contact = restTemplate.getForObject("/contact", Contact.class,"John");

        assertTrue(contact == null);
    }
}
