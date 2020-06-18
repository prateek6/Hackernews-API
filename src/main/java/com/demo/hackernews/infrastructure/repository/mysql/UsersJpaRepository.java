package com.demo.hackernews.infrastructure.repository.mysql;

import com.demo.hackernews.infrastructure.enties.mysql.UsersEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsersJpaRepository extends JpaRepository<UsersEntity,Long> {
    Optional<UsersEntity> findByUserHandle(String userHandle);
}
