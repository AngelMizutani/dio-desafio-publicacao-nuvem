package dio.desafio.desafio_dio_nuvem.service.impl;

import dio.desafio.desafio_dio_nuvem.domain.model.Player;
import dio.desafio.desafio_dio_nuvem.domain.repository.PlayerRepository;
import dio.desafio.desafio_dio_nuvem.service.PlayerService;
import dio.desafio.desafio_dio_nuvem.service.exception.BusinessException;
import dio.desafio.desafio_dio_nuvem.service.exception.NotFoundException;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static java.util.Optional.ofNullable;

@Service
public class PlayerServiceImpl implements PlayerService {
    private static final Long UNCHANGEBLE_PLAYER_ID = 1L;

    private final PlayerRepository playerRepository;

    public PlayerServiceImpl (PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    @Transactional(readOnly = true)
    public List<Player> findAll() {
        return this.playerRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Player findById(Long id) {
        return this.playerRepository.findById(id).orElseThrow(NotFoundException::new);
    }

    @Transactional
    public Player findByUsername(String username) {
        return this.playerRepository.findByUsername(username);
    }

    @Transactional
    public Player create(Player player) {
        ofNullable(player).orElseThrow(() -> new BusinessException("Player to create can not be null"));

        this.validateChangeableId(player.getId(), "created");
        if (playerRepository.existsByUsername(player.getUsername())) {
            throw new BusinessException("This username already exists");
        }
        return this.playerRepository.save(player);
    }

    @Transactional
    public Player update(Long id, Player player) {
        this.validateChangeableId(id, "updated");
        Player dbPlayer = this.findById(id);

        if (!dbPlayer.getId().equals(player.getId())) {
            throw new BusinessException("Update IDs must be the same");
        }

        dbPlayer.setUsername(player.getUsername());
        dbPlayer.setScore(player.getScore());
        dbPlayer.setLevel(player.getLevel());
        dbPlayer.setHealth(player.getHealth());
        dbPlayer.setCharacter(player.getCharacter());
        dbPlayer.setWeapons(player.getWeapons());
        dbPlayer.setPotions(player.getPotions());
        return this.playerRepository.save(dbPlayer);
    }

    @Transactional
    public void delete(Long id) {
        this.validateChangeableId(id, "deleted");
        Player dbPlayer = this.findById(id);
        this.playerRepository.delete(dbPlayer);
    }

    private void validateChangeableId(Long id, String operation) {
        if (UNCHANGEBLE_PLAYER_ID.equals(id)) {
            throw new BusinessException("Player with ID %d can not be %s.".formatted(UNCHANGEBLE_PLAYER_ID, operation));
        }
    }
}
