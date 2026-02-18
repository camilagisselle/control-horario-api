package com.indra.controlhorarioapi.repository;

import com.indra.controlhorarioapi.model.PassResetToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface PassResetTokenRepository extends JpaRepository<PassResetToken, Long> {
    Optional<PassResetToken> findByToken(String token);
}