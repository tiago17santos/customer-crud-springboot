package com.techVerse.clienteCRUD.dtos;

import com.techVerse.clienteCRUD.entities.Client;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PastOrPresent;

import java.time.LocalDate;

public class ClientDto {

    private Long id;
    @NotBlank(message = "Campo requerido")
    private String name;
    private String cpf;
    private Double income;
    @PastOrPresent(message = "Data de nascimento deve ser menor ou igual a data atual")
    private LocalDate birthDate;
    private Integer children;


    public ClientDto() {
    }

    public ClientDto(Long id, String name, String cpf, Double income, LocalDate birthDate, Integer children) {
        this.id = id;
        this.name = name;
        this.cpf = cpf;
        this.income = income;
        this.birthDate = birthDate;
        this.children = children;
    }

    public ClientDto(Client client) {
        id = client.getId();
        name = client.getName();
        cpf = client.getCpf();
        income = client.getIncome();
        birthDate = client.getBirthDate();
        children = client.getChildren();
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCpf() {
        return cpf;
    }

    public Double getIncome() {
        return income;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public Integer getChildren() {
        return children;
    }
}
