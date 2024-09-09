package dio.desafio.desafio_dio_nuvem.domain.repository;

import dio.desafio.desafio_dio_nuvem.domain.model.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlayerRepository extends JpaRepository<Player, Long> {
    Player findByUsername(String username);
    boolean existsByUsername(String username);
}
