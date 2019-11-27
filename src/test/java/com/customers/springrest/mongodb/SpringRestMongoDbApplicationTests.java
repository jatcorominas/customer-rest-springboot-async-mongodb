package com.customers.springrest.mongodb;

import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.assertThat;

/** Spring 3.2.x use these */
 import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
 import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
 import static org.springframework.test.web.client.response.MockRestResponseCreators.withServerError;
 import static org.springframework.test.web.client.response.MockRestResponseCreators.withStatus;
 import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

/** Spring 3.1.x use these */
//import static org.springframework.test.web.client.match.RequestMatchers.method;
//import static org.springframework.test.web.client.match.RequestMatchers.requestTo;
//import static org.springframework.test.web.client.response.ResponseCreators.withServerError;
//import static org.springframework.test.web.client.response.ResponseCreators.withStatus;
//import static org.springframework.test.web.client.response.ResponseCreators.withSuccess;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.web.client.RestTemplate;

import com.customers.springrest.service.CustomerRestService;

@ContextConfiguration(locations = {"classpath:/applicationContext-test.xml"})
public class SpringRestMongoDbApplicationTests extends AbstractJUnit4SpringContextTests {
    
	@Autowired
    private CustomerRestService simpleRestService;

    @Autowired
    private RestTemplate restTemplate;

    private MockRestServiceServer mockServer;

    @Before
    public void setUp() {

        mockServer = MockRestServiceServer.createServer(restTemplate);
    }

    @Test
    public void testGetMessage() {
        mockServer.expect(requestTo("http://localhost:8090/api/customers")).andExpect(method(HttpMethod.GET))
                .andRespond(withSuccess("resultSuccess", MediaType.TEXT_PLAIN));

        String result = simpleRestService.getMessage();

        mockServer.verify();
        assertThat(result, allOf(containsString("SUCCESS"), containsString("resultSuccess")));
    }

    @Test
    public void testGetMessage_500() {
        mockServer.expect(requestTo("http://localhost:8090/api/customers")).andExpect(method(HttpMethod.GET))
                .andRespond(withServerError());

        String result = simpleRestService.getMessage();

        mockServer.verify();
        assertThat(result, allOf(containsString("FAILED"), containsString("500")));
    }

    @Test
    public void testGetMessage_404() {
        mockServer.expect(requestTo("http://localhost:8090/api/customers")).andExpect(method(HttpMethod.GET))
                .andRespond(withStatus(HttpStatus.NOT_FOUND));

        String result = simpleRestService.getMessage();

        mockServer.verify();
        assertThat(result, allOf(containsString("FAILED"), containsString("404")));
    }
    
    @Test
    public void tesPostMessage() {
        mockServer.expect(requestTo("http://localhost:8090/api/customers/create")).andExpect(method(HttpMethod.POST))
                .andRespond(withSuccess("resultSuccess", MediaType.TEXT_PLAIN));

        String result = simpleRestService.postMessage();

        mockServer.verify();
        assertThat(result, allOf(containsString("SUCCESS"), containsString("resultSuccess")));
    }
    
    @Test
    public void testPostMessage_500() {
        mockServer.expect(requestTo("http://localhost:8090/api/customers/create")).andExpect(method(HttpMethod.POST))
                .andRespond(withServerError());

        String result = simpleRestService.postMessage();

        mockServer.verify();
        assertThat(result, allOf(containsString("FAILED"), containsString("500")));
    }

    @Test
    public void testPostMessage_404() {
        mockServer.expect(requestTo("http://localhost:8090/api/customers/create")).andExpect(method(HttpMethod.POST))
                .andRespond(withStatus(HttpStatus.NOT_FOUND));

        String result = simpleRestService.postMessage();

        mockServer.verify();
        assertThat(result, allOf(containsString("FAILED"), containsString("404")));
    }
    
    @Test
    public void tesPutMessage() {
        mockServer.expect(requestTo("http://localhost:8090/api/customers")).andExpect(method(HttpMethod.PUT))
                .andRespond(withSuccess("resultSuccess", MediaType.TEXT_PLAIN));

        String result = simpleRestService.putMessage();

        mockServer.verify();
        assertThat(result, allOf(containsString("SUCCESS"), containsString("resultSuccess")));
    }
    
    @Test
    public void testPutMessage_500() {
        mockServer.expect(requestTo("http://localhost:8090/api/customers")).andExpect(method(HttpMethod.PUT))
                .andRespond(withServerError());

        String result = simpleRestService.putMessage();

        mockServer.verify();
        assertThat(result, allOf(containsString("FAILED"), containsString("500")));
    }
    
    @Test
    public void testPutMessage_404() {
        mockServer.expect(requestTo("http://localhost:8090/api/customers")).andExpect(method(HttpMethod.PUT))
                .andRespond(withStatus(HttpStatus.NOT_FOUND));

        String result = simpleRestService.putMessage();

        mockServer.verify();
        assertThat(result, allOf(containsString("FAILED"), containsString("404")));
    }
    
    @Test
    public void testDeleteMessage() {
        mockServer.expect(requestTo("http://localhost:8090/api/customers/delete")).andExpect(method(HttpMethod.DELETE))
                .andRespond(withSuccess("resultSuccess", MediaType.TEXT_PLAIN));

        String result = simpleRestService.deleteMessage();

        mockServer.verify();
        assertThat(result, allOf(containsString("SUCCESS"), containsString("resultSuccess")));
    }
    
    @Test
    public void testDeleteMessage_500() {
        mockServer.expect(requestTo("http://localhost:8090/api/customers/delete")).andExpect(method(HttpMethod.DELETE))
                .andRespond(withServerError());

        String result = simpleRestService.deleteMessage();

        mockServer.verify();
        assertThat(result, allOf(containsString("FAILED"), containsString("500")));
    }
    
    @Test
    public void testDeleteMessage_404() {
        mockServer.expect(requestTo("http://localhost:8090/api/customers/delete")).andExpect(method(HttpMethod.DELETE))
                .andRespond(withStatus(HttpStatus.NOT_FOUND));

        String result = simpleRestService.deleteMessage();

        mockServer.verify();
        assertThat(result, allOf(containsString("FAILED"), containsString("404")));
    }
    
}