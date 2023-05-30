package com.kidbot.Services.impl;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kidbot.Entities.Story;
import com.kidbot.Entities.StoryPrompt;
import com.kidbot.Entities.User;
import com.kidbot.Repositories.StoryRepository;
import com.kidbot.Repositories.UserRepository;
import com.kidbot.Services.StoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.Optional;

@Service
public class StoryServiceImpl implements StoryService {
    @Autowired
    private Environment environment;

    @Autowired
    private StoryRepository storyRepository;

    @Autowired
    private UserRepository userRepository;

    @Value(("${openai.apikey}"))
    private String apikey;

    @Value(("${openai.endpoint}"))
    private String endpoint;


    @Override
    public List<Story> findAllStories() {
        return (List<Story>) this.storyRepository.findAll();
    }

    @Override
    public Story createStory(StoryPrompt storyPrompt) {
        int age = storyPrompt.getAge();
        String mainCharacter = storyPrompt.getCharacterName();
        String themeOne = storyPrompt.getThemeOne();
        String themeTwo = storyPrompt.getThemeTwo();
        String genre = storyPrompt.getGenre();

        // Composición del input para generar la solicitud a la API
        String input =  "\"Crea una hermosa historia de " + genre + " para niños de " + age + " años con un protagonista llamado " +
                mainCharacter + " sobre " + themeOne + " y " + themeTwo + "\"  ";

        String requestBody = "{\n" +
                "    \"model\": \"text-davinci-003\",\n" +
                "    \"prompt\":" + input + ",\n" +
                "    \"max_tokens\": 2046,\n" +
                "    \"temperature\": 0\n" +
                "}";

        try{

        HttpResponse<String> response = openaiClient(endpoint, apikey, requestBody);

        String responseBody = response.body();
        //jsonNode accede al historia y la "navega" mediante ObjectMapper accediendo a las claves "choices y text"
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode rootNode = objectMapper.readTree(responseBody);

        JsonNode storyResponse = rootNode.get("choices").get(0).get("text");
        //aqui se entrega a la variable story.Response "como texto" pasa de ser json a string
        String storyText = storyResponse.asText();
        //se imprime la historia
        System.out.println(storyText);


        Story story = new Story();
        story.setStoryContent(storyText);
        story.setStoryTitle("La historia de "+ mainCharacter + " sobre " + themeOne + " y "+ themeTwo);

        Long id =  storyPrompt.getId();
        User user = this.userRepository.findUserById(id);
        story.setUser(user);

        return this.storyRepository.save(story);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }


    }

    @Override
    public Story updateStory(Story story) {
        return this.storyRepository.save(story);
    }

    @Override
    public Optional<Story> findStoryById(Long id) {
        return this.storyRepository.findById(id);
    }

    @Override
    public void deleteStoryById(Long id) {
        this.storyRepository.deleteById(id);
    }

    public HttpResponse<String> openaiClient(String endpoint, String apiKey, String requestBody) {
        try {
            // Creación del cuerpo de la solicitud HTTP
            HttpRequest.BodyPublisher payload = HttpRequest.BodyPublishers.ofString(requestBody);
            // Construcción de la solicitud HTTP
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(endpoint))
                    .header("Content-Type", "application/json")
                    .header("Authorization", "Bearer " + apiKey)
                    .POST(payload)
                    .build();
            // Creación del cliente HTTP
            HttpClient httpClient = HttpClient.newHttpClient();
            // Envío de la solicitud HTTP y obtención de la respuesta
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

            return response;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

}
