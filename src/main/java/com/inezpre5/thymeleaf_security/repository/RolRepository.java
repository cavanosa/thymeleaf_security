package com.inezpre5.thymeleaf_security.repository;

        import com.inezpre5.thymeleaf_security.enums.RolNombre;
        import com.inezpre5.thymeleaf_security.model.Rol;
        import org.springframework.data.jpa.repository.JpaRepository;
        import org.springframework.stereotype.Repository;

        import java.util.Optional;

@Repository
public interface RolRepository extends JpaRepository<Rol, Long> {
    boolean existsByRolNombre(RolNombre rolNombre);
    Optional<Rol> findByRolNombre(RolNombre rolNombre);
}
