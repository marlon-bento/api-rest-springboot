package com.gmail.guia2.dto;

import javax.persistence.*;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;


public class Tab1 {
    // -----------------------tab1-----------------------------------

    private Long id;

    private String veiculo;

    private double fatorMult;

    public byte[] toByteArray() throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        DataOutputStream dos = new DataOutputStream(baos);
        // escreve os dados
        dos.writeLong(id);
        dos.writeUTF(veiculo);
        dos.writeDouble(fatorMult);

        return baos.toByteArray();
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getVeiculo() {
        return veiculo;
    }

    public void setVeiculo(String veiculo) {
        this.veiculo = veiculo;
    }

    public double getFatorMult() {
        return fatorMult;
    }

    public void setFatorMult(double fatorMult) {
        this.fatorMult = fatorMult;
    }

}
