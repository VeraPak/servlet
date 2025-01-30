package ru.example.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.example.controller.PostController;
import ru.example.repository.PostRepository;
import ru.example.service.PostService;

@Configuration
public class JavaConfig {

    @Bean
    public PostRepository postRepository() {
        return new PostRepository();
    }

    @Bean
    public PostService postService(PostRepository repository) {
        return new PostService(repository);
    }

    @Bean
    public PostController postController(PostService service) {
        return new PostController(service);
    }
}
