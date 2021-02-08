package jm.pp_rest_template;

import jm.pp_rest_template.client.RestTemplateClient;
import jm.pp_rest_template.configuration.WebConfig;
import jm.pp_rest_template.model.User;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class AppMain {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(WebConfig.class);
        RestTemplateClient client = context.getBean("restTemplateClient", RestTemplateClient.class);

        User addUser = new User(3L, "James", "Brown", (byte)64);
        User updateUser = new User(3L, "Thomas", "Shelby", (byte)64);
        User userDelete = new User();
        userDelete.setId(3L);

        String JSESSIONID = client.getAllUsers()
                .getHeaders()
                .getValuesAsList("Set-Cookie")
                .get(0);

        String secret1 = client.addUser(addUser, JSESSIONID).getBody();
        String secret2 = client.updateUser(updateUser, JSESSIONID).getBody();
        String secret3 = client.deleteUser(userDelete, JSESSIONID).getBody();

        System.out.println(secret1 + secret2 + secret3);



//        HttpHeaders headers =  client.getAllUsers().getHeaders();
//        System.out.println(headers);
//        System.out.println("\n\n");
//        headers.forEach((x, y) -> System.out.println(x + ":" + y));


//        System.out.println("============================");
//        User addUser2 = new User(1L, "James", "Brown", (byte)64);
//        String JSESSIONID2 = client.getAllUsers()
//                .getHeaders()
//                .getValuesAsList("Set-Cookie")
//                .get(0);
//        for(long i = 0; i < 1500; i++) {
//            client.addUser(new User(i, "James", "Brown", (byte)64), JSESSIONID2);
//        }
//        client.getAllUsers2(JSESSIONID2).getBody().forEach(x -> System.out.println(x.getId()));

    }
}
