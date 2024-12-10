package br.edu.ifrs.miguelzk.domain.enums;

public enum TipoImunizante {
    VACINA(1, "Vacina"),
    VERMIFUGO(2, "Vermífugo"),
    ANTIPULGASECARRAPATOS(3, "Anti-pulgas e anti-carrapatos");

    private int codigo;
    private String tipoImunizante;

    TipoImunizante(int codigo, String tipoImunizante) {
        this.codigo = codigo;
        this.tipoImunizante = tipoImunizante;
    }
}
