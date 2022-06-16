package com.URLShortner.URLShortnerService.repository;

import com.URLShortner.URLShortnerService.model.Url;
import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@EnableScan
@Repository
public interface UrlRepository extends CrudRepository<Url, String> {
    Optional<Url> findByShortURL(String s);
    Optional<Url> findByLongURL(String s);

    }

