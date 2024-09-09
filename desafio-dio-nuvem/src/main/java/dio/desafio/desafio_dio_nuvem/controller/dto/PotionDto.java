package dio.desafio.desafio_dio_nuvem.controller.dto;

import dio.desafio.desafio_dio_nuvem.domain.model.Potion;

public record PotionDto (Long id, String description, int points){

    public PotionDto(Potion model) {
        this(model.getId(), model.getDescription(), model.getPoints());
    }

    public Potion toModel() {
        Potion model = new Potion();
        model.setId(this.id);
        model.setDescription(this.description);
        model.setPoints(this.points);

        return model;
    }
}
