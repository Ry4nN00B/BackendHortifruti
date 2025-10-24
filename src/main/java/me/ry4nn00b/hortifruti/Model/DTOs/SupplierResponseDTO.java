package me.ry4nn00b.hortifruti.Model.DTOs;

public class SupplierResponseDTO {

    //Supplier Attribute's
    private String id;
    private String name;
    private String description;
    private String phoneNumber;
    private String email;
    private String cnpj;

    public SupplierResponseDTO() {}

    public SupplierResponseDTO(String id, String name, String description, String phoneNumber, String email, String cnpj) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.cnpj = cnpj;
    }

    //Getter's and Setter's
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

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
