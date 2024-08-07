package com.mapper.platform.user.infrastructure.persistence.jpa.repositories;

import com.mapper.platform.user.domain.projections.UserProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserProjection, Long> {
    @Procedure(procedureName = "get_session")
    List<UserProjection> getSession(String correo);
}
