package dio.desafio.desafio_dio_nuvem.controller.dto;

import dio.desafio.desafio_dio_nuvem.domain.model.Weapon;

public record WeaponDto (Long id, String description, int points){

    public WeaponDto(Weapon model) {
        this(model.getId(), model.getDescription(), model.getPoints());
    }

    public Weapon toModel() {
        Weapon model = new Weapon();
        model.setId(this.id);
        model.setDescription(this.description);
        model.setPoints(this.points);

        return model;
    }
}
