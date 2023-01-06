package com.gmail.guia2.dto;

public class Tab2 {
    // -----------------------tab2-----------------------------------
    private Long id;

    private String email;
    private double distanciaPav;
    private double distanciaNaoPav;
    private String veiculoUtilizado;
    private int carga;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    private double custoTotal;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getDistanciaPav() {
        return distanciaPav;
    }

    public void setDistanciaPav(double distanciaPav) {
        this.distanciaPav = distanciaPav;
    }

    public double getDistanciaNaoPav() {
        return distanciaNaoPav;
    }

    public void setDistanciaNaoPav(double distanciaNaoPav) {
        this.distanciaNaoPav = distanciaNaoPav;
    }

    public String getVeiculoUtilizado() {
        return veiculoUtilizado;
    }

    public void setVeiculoUtilizado(String veiculoUtilizado) {
        this.veiculoUtilizado = veiculoUtilizado;
    }

    public int getCarga() {
        return carga;
    }

    public void setCarga(int carga) {
        this.carga = carga;
    }

    public double getCustoTotal() {
        return custoTotal;
    }

    public void setCustoTotal(double custoTotal) {
        this.custoTotal = custoTotal;
    }
}
