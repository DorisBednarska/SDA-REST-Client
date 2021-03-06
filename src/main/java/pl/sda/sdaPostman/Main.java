package pl.sda.sdaPostman;


import org.codehaus.jackson.map.ObjectMapper;
import pl.sda.messages.CreateUserRequest;
import pl.sda.messages.CreateUserResponse;
import pl.sda.messages.GetUserResponse;
import pl.sda.utils.HttpUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by RENT on 2017-03-06.
 */
public class Main {
//    public static void main(String[] args) throws IOException {
//        String createUserRequest = "{" + "\"name\":\"doris\",\"login\":\"db\"," + "\"mail\":\"db@gmail.com\"" + "}";
//        String createUserResponse = Sender.createUser("http://localhost:8081/sda-json/json", createUserRequest);
//        System.out.println("");
//
//        List<String> usersList = new ArrayList<>();
//    }
    public static final String URL = "http://localhost:8081/sda-json/json";

    public static void main(String[] args) throws IOException {

        List<String> userIds = new ArrayList<>();
        while (true) {
            Scanner scanner = new Scanner(System.in);

            System.out.println("1. Dodaj użytkownika");
            System.out.println("2. Wyświetl ID użytkowników");
            System.out.println("3. Wyświetl dane użytkowników");
            String choiceRaw = scanner.nextLine();

            Integer choice = Integer.parseInt(choiceRaw);


            switch (choice) {
                case 1:
                    CreateUserRequest createUserRequest = new CreateUserRequest();
                    System.out.println("Podaj login");
                    createUserRequest.setLogin(scanner.nextLine());
                    System.out.println("Podaj imię");
                    createUserRequest.setName(scanner.nextLine());
                    System.out.println("Podaj maila");
                    createUserRequest.setMail(scanner.nextLine());

                    ObjectMapper mapper = new ObjectMapper();
                    String request = mapper.writeValueAsString(createUserRequest);
                    String createUserResponse = Sender.createUser(URL, request);

                    CreateUserResponse response = mapper.readValue(createUserResponse, CreateUserResponse.class);
                    userIds.add(response.getId());
                    break;

                case 2:
                    System.out.println(userIds.toString());
                    break;
                case 3:
                    List<String> getUserResponses = new ArrayList<>();
                    for (String id : userIds){
                        ObjectMapper mapper1 = new ObjectMapper();
                        getUserResponses.add(Sender.getUser(URL, id));
                    }
                    System.out.println(getUserResponses.toString());
                    break;
                default:
                    break;
            }
        }
    }
}
