package jm.pp_rest_template.client;

import jm.pp_rest_template.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Component
public class RestTemplateClient {

    @Autowired
    private RestTemplate restTemplate;

//    private String REQUEST_URI = "http://localhost:8081/api/users";
    private String URI_USERS = "http://91.241.64.178:7081/api/users";
    private static final String RESOURCE_PATH = "/api/users";


    public RestTemplateClient() {
    }

    public ResponseEntity<List<User>> getAllUsers() {
        ResponseEntity<List<User>> responseEntity =
                restTemplate.exchange(URI_USERS,
                        HttpMethod.GET,
                        null,
                        new ParameterizedTypeReference<List<User>>() {
                        });
        return responseEntity;
    }



    public ResponseEntity<String> updateUser(User user, String sessionid) {

        HttpHeaders headers = new HttpHeaders();
        headers.set("Cookie", sessionid);

        HttpEntity<User> entity = new HttpEntity<>(user, headers);

        ResponseEntity<String> responseEntity =
                restTemplate.exchange(URI_USERS,
                                        HttpMethod.PUT,
                                        entity,
                                        String.class);
        return responseEntity;
    }


    public ResponseEntity<String> addUser(User user, String sessionid) {

        HttpHeaders headers = new HttpHeaders();
        headers.set("Cookie", sessionid);

        HttpEntity<User> entity = new HttpEntity<>(user, headers);

        ResponseEntity<String> responseEntity =
                restTemplate.exchange(URI_USERS,
                                        HttpMethod.POST,
                                        entity,
                                        String.class);
        return responseEntity;
    }

    public ResponseEntity<String> deleteUser(User user, String sessionid) {
        String URI_FOR_DELETE = String.format("%s/%d", URI_USERS, user.getId());

        HttpHeaders headers = new HttpHeaders();
        headers.set("Cookie", sessionid);
        HttpEntity<User> entity = new HttpEntity<>(user, headers);

        ResponseEntity<String> responseEntity =
                restTemplate.exchange(URI_FOR_DELETE,
                                        HttpMethod.DELETE,
                                        entity,
                                        String.class);
        return responseEntity;
    }

    public ResponseEntity<List<User>> getAllUsers2(String sessionid) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Cookie", sessionid);

        HttpEntity<User> entity = new HttpEntity<>(new User(), headers);

        ResponseEntity<List<User>> responseEntity =
                restTemplate.exchange(URI_USERS,
                        HttpMethod.GET,
                        entity,
                        new ParameterizedTypeReference<List<User>>() {
                        });
        return responseEntity;
    }


}
