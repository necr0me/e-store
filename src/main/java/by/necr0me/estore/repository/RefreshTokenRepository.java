package by.necr0me.estore.repository;

import by.necr0me.estore.entity.RefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {
    @Query("""
            SELECT t FROM RefreshToken t inner join User u
            on t.user.id = u.id
            where t.user.id = :userId and t.loggedOut = false
            """)
    List<RefreshToken> findAllRefreshTokenByUser(Long userId);
    Optional<RefreshToken> findByValue(String value);
}
