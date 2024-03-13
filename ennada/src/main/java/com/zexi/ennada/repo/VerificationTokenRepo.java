package com.zexi.ennada.repo;

import com.zexi.ennada.entity.User;
import com.zexi.ennada.entity.VerificationToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface VerificationTokenRepo extends JpaRepository<VerificationToken,Long> {
    VerificationToken findByToken(String token);


    VerificationToken findByUserId(Long id);
}
