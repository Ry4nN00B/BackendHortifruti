package me.ry4nn00b.hortifruti.Service;

import me.ry4nn00b.hortifruti.Model.ProductModel;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MonitorService {

    private final ProductService productService;

    //Method's
    public MonitorService(ProductService productService) {
        this.productService = productService;
    }

    //Products with expiration dates of up to 'X' days
    public List<ProductModel> productsCloseToExpirationDate(int days) {
        LocalDate limit = LocalDate.now().plusDays(days);
        return productService.productList().stream()
                .filter(p -> p.getValidity() != null && !p.getValidity().isAfter(limit))
                .collect(Collectors.toList());
    }

}
