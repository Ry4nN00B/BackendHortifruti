package me.ry4nn00b.hortifruti.Service.Interface;

import me.ry4nn00b.hortifruti.Model.SaleModel;

import java.util.List;
import java.util.Optional;

public interface ISaleService {

    List<SaleModel> saleList();
    Optional<SaleModel> saleFindById(String saleId);
    void saleDeleteId(String saleId);
    SaleModel saleRegister(SaleModel sale);
    SaleModel confirmPayment(String saleId);
    SaleModel cancelSale(String saleId);
    SaleModel saleUpdate(String saleId, SaleModel newSale);
    SaleModel removeItemFromSale(String saleId, String productId);

}
