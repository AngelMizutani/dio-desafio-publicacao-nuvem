package dio.desafio.desafio_dio_nuvem.domain.model;

import jakarta.persistence.*;

import java.util.List;

@Entity(name = "tb_player")
public class Player {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String username;
    private int score;
    private int level;
    private int health;

    @OneToOne(cascade = CascadeType.ALL)
    private Character character;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Weapon> weapons;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Potion> potions;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public Character getCharacter() {
        return character;
    }

    public void setCharacter(Character character) {
        this.character = character;
    }

    public List<Weapon> getWeapons() {
        return weapons;
    }

    public void setWeapons(List<Weapon> weapons) {
        this.weapons = weapons;
    }

    public List<Potion> getPotions() {
        return potions;
    }

    public void setPotions(List<Potion> potions) {
        this.potions = potions;
    }
}
