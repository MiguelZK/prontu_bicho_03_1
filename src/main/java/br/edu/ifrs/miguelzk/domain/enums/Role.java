package br.edu.ifrs.miguelzk.domain.enums;

public enum Role {
    TUTOR(1, "Tutor - Responsavel"),
    MEDVET(2, "Medico/a Veterinario/a"),
    ADM(3, "Admin"),
    TEMP(4, "Lar temporario - Casa de passagem"); // USAR ESTE TERMO NO VÍNCULO NA PRÓXIMA ALTERAÇÃO

    private int codigo;
    private String roleName;

    Role(int codigo, String roleName) {
        this.codigo = codigo;
        this.roleName = roleName;
    }
}
