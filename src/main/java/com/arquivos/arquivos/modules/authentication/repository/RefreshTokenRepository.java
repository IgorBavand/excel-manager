package com.arquivos.arquivos.modules.authentication.repository;

import com.arquivos.arquivos.modules.authentication.model.RefreshToken;
import com.arquivos.arquivos.modules.authentication.model.User;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.Optional;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {
    Optional<RefreshToken> findByToken(String token);
    void deleteByUser(User user);

    @Modifying
    @Transactional
    @Query("DELETE FROM RefreshToken t WHERE t.expiryDate <= ?1")
    void deleteAllExpiredSince(Date now);
}