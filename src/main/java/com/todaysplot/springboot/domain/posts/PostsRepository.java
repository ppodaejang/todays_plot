package com.todaysplot.springboot.domain.posts;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PostsRepository extends JpaRepository<Posts, Long> {

    @Query("Select p From Posts p Order By p.id DESC")
    List<Posts> findAllDesc();
}
