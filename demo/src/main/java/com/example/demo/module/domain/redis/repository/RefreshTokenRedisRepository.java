package com.example.demo.module.domain.redis.repository;

import com.example.demo.module.domain.redis.entity.RefreshToken;
import org.springframework.data.repository.CrudRepository;

public interface RefreshTokenRedisRepository extends CrudRepository<RefreshToken, String> {
}

