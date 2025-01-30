package ru.example.repository;

import org.springframework.stereotype.Repository;
import ru.example.exception.NotFoundException;
import ru.example.model.Post;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

// Stub
@Repository
public class PostRepository {
  private final ConcurrentHashMap<Long, Post> posts = new ConcurrentHashMap<>();
  private final AtomicLong currentId = new AtomicLong(1);

  public List<Post> all() {
    return new ArrayList<>(posts.values());
  }

  public Optional<Post> getById(long id) {
    return Optional.ofNullable(posts.get(id));
  }

  public Post save(Post post) {
    if (post.getId() == 0) {
      var id = currentId.getAndIncrement();
      post.setId(id);
      posts.put(id, post);
      return post;
    }
    if (posts.containsKey(post.getId())) {
      posts.put(post.getId(), post);
      return post;
    }
    throw new NotFoundException("Пост не найден");
  }

  public void removeById(long id) {
    posts.remove(id);
  }
}
