package br.edu.ifrs.miguelzk.domain.enums;

import lombok.Getter;

@Getter
public enum PorteCachorro {
    MINI(1, 1, 6, 0, 28),
    PEQUENO(2, 6, 15, 28, 35),
    MEDIO(3, 15, 25, 36, 49),
    GRANDE(4, 25, 45, 50, 69),
    GIGANTE(5, 45, 60, 70, 250);

    private int codigo;
    private int pesoMin;
    private int pesoMax;
    private int alturaMin;
    private int alturaMax;

    PorteCachorro(int codigo, int pesoMin, int pesoMax, int alturaMin, int alturaMax) {
        this.codigo = codigo;
        this.pesoMin = pesoMin;
        this.pesoMax = pesoMax;
        this.alturaMin = alturaMin;
        this.alturaMax = alturaMax;
    }
}
