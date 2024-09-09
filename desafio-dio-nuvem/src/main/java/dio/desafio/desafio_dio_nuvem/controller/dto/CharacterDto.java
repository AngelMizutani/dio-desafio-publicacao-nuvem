package dio.desafio.desafio_dio_nuvem.controller.dto;

import dio.desafio.desafio_dio_nuvem.domain.model.Character;

public record CharacterDto (Long id, String cathegory, int attack, int defense){

    public CharacterDto(Character model) {
        this(model.getId(), model.getCathegory(), model.getAttack(), model.getDefense());
    }

    public Character toModel() {
        Character model = new Character();
        model.setId(this.id);
        model.setCathegory(this.cathegory);
        model.setAttack(this.attack);
        model.setDefense(this.defense);
        return model;
    }
}
