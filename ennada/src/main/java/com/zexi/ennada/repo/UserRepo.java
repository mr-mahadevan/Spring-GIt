package com.zexi.ennada.repo;

import com.zexi.ennada.entity.User;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<User,Long> {

    @Transactional
    @Modifying
    @Query("UPDATE User u SET u.active = true WHERE u.id = :userId")
    int updateActiveById(@Param("userId") Long userId);

    User findByEmail(String email);
}
