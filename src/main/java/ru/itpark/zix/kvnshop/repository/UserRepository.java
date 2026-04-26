package ru.itpark.zix.kvnshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.itpark.zix.kvnshop.domain.User;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByTgId(Long tgId);

    @Modifying
    @Transactional
    @Query(value = """
        INSERT INTO users (tg_id, username, last_seen_at, created_at)
        VALUES (:telegramId, :username, now(), now())
        ON CONFLICT (tg_id)
        DO UPDATE SET
            username = EXCLUDED.username,
            last_seen_at = now()
        """, nativeQuery = true)
    void upsert(@Param("telegramId") Long telegramId,
                @Param("username") String username);
}
