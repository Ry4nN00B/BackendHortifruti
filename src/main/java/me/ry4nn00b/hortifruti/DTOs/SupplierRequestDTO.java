package me.ry4nn00b.hortifruti.DTOs;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class SupplierRequestDTO {

    //Supplier Attribute's
    @NotBlank(message = "Hortifruti Erro: O nome do fornecedor é obrigatório.")
    @Size(min = 3, max = 100, message = "Hortifruti Erro: O nome deve ter entre 3 e 100 caracteres.")
    private String name;

    @Size(max = 500, message = "Hortifruti Erro: A descrição pode ter no máximo 500 caracteres.")
    private String description;

    @NotBlank(message = "Hortifruti Erro: O telefone é obrigatório.")
    private String phoneNumber;

    @Email(message = "Hortifruti Erro: O email deve ser válido.")
    private String email;

    @NotBlank(message = "Hortifruti Erro: O CNPJ é obrigatório.")
    private String cnpj;

    public SupplierRequestDTO() {}

    public SupplierRequestDTO(String name, String description, String phoneNumber, String email, String cnpj) {
        this.name = name;
        this.description = description;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.cnpj = cnpj;
    }

    //Getter's and Setter's
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getPhoneNumber() { return phoneNumber; }
    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getCnpj() { return cnpj; }
    public void setCnpj(String cnpj) { this.cnpj = cnpj; }

}
