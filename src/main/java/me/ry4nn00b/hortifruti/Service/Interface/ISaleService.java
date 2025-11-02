package me.ry4nn00b.hortifruti.Service.Interface;

import me.ry4nn00b.hortifruti.DTOs.SaleRequestDTO;
import me.ry4nn00b.hortifruti.DTOs.SaleResponseDTO;
import me.ry4nn00b.hortifruti.Model.SaleModel;

import java.util.List;
import java.util.Optional;

public interface ISaleService {

    List<SaleResponseDTO> saleList();
    Optional<SaleResponseDTO> saleFindById(String saleId);
    SaleResponseDTO saleRegister(SaleRequestDTO saleDTO);
    SaleResponseDTO confirmPayment(String saleId);
    SaleResponseDTO cancelSale(String saleId);
    Optional<SaleResponseDTO> saleUpdate(String id, SaleRequestDTO saleDTO);
    Optional<SaleResponseDTO> removeItemFromSale(String id, String productId);
    void saleDeleteId(String saleId);

}
