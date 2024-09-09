package dio.desafio.desafio_dio_nuvem.controller.dto;

import dio.desafio.desafio_dio_nuvem.domain.model.Character;
import dio.desafio.desafio_dio_nuvem.domain.model.Player;
import dio.desafio.desafio_dio_nuvem.domain.model.Potion;
import dio.desafio.desafio_dio_nuvem.domain.model.Weapon;

import java.util.List;

public record PlayerDto (
        Long id,
        String username,
        int score,
        int level,
        int health,
        Character character,
        List<Weapon> weapons,
        List<Potion> potions){

    public PlayerDto(Player model) {
        this(
                model.getId(),
                model.getUsername(),
                model.getScore(),
                model.getLevel(),
                model.getHealth(),
                model.getCharacter(),
                model.getWeapons(),
                model.getPotions()
                );
    }

    public Player toModel() {
        Player model = new Player();
        model.setId(this.id);
        model.setUsername(this.username);
        model.setScore(this.score);
        model.setLevel(this.level);
        model.setHealth(this.health);
        model.setCharacter(this.character);
        model.setWeapons(this.weapons);
        model.setPotions(this.potions);

        return model;
    }
}
