package personal.apiwebsite.service;

import org.json.JSONObject;
import personal.apiwebsite.model.Post;
import personal.apiwebsite.model.dto.PostDTO;
import personal.apiwebsite.repository.PostRepository;
import org.springframework.stereotype.Service;
import org.json.JSONArray;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

@Service
public class PostService {

    private static final String API_URL = "https://jsonplaceholder.typicode.com/posts";
    private final PostRepository postRepository;

    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public void fetchAndStorePosts() {
        List<Post> posts = new ArrayList<>();
        // Create an HttpClient
        HttpClient httpClient = HttpClient.newHttpClient();

        // Create an HTTP request to the API URL
        HttpRequest httpRequest = HttpRequest.newBuilder()
                .uri(URI.create(API_URL))
                .build();

        try {
            // Send the HTTP request and get the response
            HttpResponse<String> httpResponse = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());

            // Check if the response status is OK (200)
            if (httpResponse.statusCode() == 200) {
                // Parse the JSON response into Post objects
                JSONArray jsonArray = new JSONArray(httpResponse.body());
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    Post post = new Post();
                    post.setUserId((long) jsonObject.getInt("userId"));
                    post.setId((long) jsonObject.getInt("id"));
                    post.setTitle(jsonObject.getString("title"));
                    post.setBody(jsonObject.getString("body"));
                    posts.add(post);
                }
                postRepository.saveAll(posts);
            } else {
                System.err.println("HTTP Request failed with status code: " + httpResponse.statusCode());
            }
        } catch (InterruptedException | IOException e) {
            e.printStackTrace();
        }


    }

    public List<PostDTO> getPosts() {
        return postRepository.getPostData();
    }
}
