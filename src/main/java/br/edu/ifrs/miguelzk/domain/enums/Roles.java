package br.edu.ifrs.miguelzk.domain.enums;

import com.sun.jna.platform.win32.NTSecApi;

public enum Roles {
    TUTOR(1, "Tutor - Responsavel"),
    MEDVET(2, "Medico/a Veterinario/a"),
    ADM(3, "Admin"),
    TEMP(4, "Lar temporario - Casa de passagem");

    private int codigo;
    private String roleName;

    Roles(int codigo, String roleName) {
        this.codigo = codigo;
        this.roleName = roleName;
    }
}
