package me.ry4nn00b.hortifruti.Service;

import me.ry4nn00b.hortifruti.Model.StockModel;
import me.ry4nn00b.hortifruti.Repository.IProductRepository;
import me.ry4nn00b.hortifruti.Repository.IStockRepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class MonitorService {

    private final IStockRepository stockRepository;
    private final IProductRepository productRepository;

    public MonitorService(IStockRepository stockRepository, IProductRepository productRepository) {
        this.stockRepository = stockRepository;
        this.productRepository = productRepository;
    }

    private static final int nextDays = 7;

    //It runs every day at 8 am
    @Scheduled(cron = "0 0 8 * * *", zone = "America/Sao_Paulo")
    public void checkValidity() {
        LocalDate today = LocalDate.now();
        LocalDate limit = today.plusDays(nextDays);

        List<StockModel> productsToExpire = stockRepository.findAll().stream()
                .filter(stock -> stock.getValidity() != null &&
                        !stock.getValidity().isBefore(today) &&
                        stock.getValidity().isBefore(limit))
                .toList();

        if (!productsToExpire.isEmpty()) {
            sendAlert(productsToExpire);
        }
    }

    //AINDA VAI SER MODIFICADO PARA ENVIAR PARA EMAIL OU WHATSAPP
    private void sendAlert(List<StockModel> products) {
        System.out.println("Hortifruti Alerta: Produtos próximos do vencimento:");

        products.forEach(stock -> {
            //Find the product by stock ID
            productRepository.findById(stock.getProductId()).ifPresentOrElse(product -> {
                System.out.println("Hortifruti: " + product.getName() + " (vence em " + stock.getValidity() + ")");
            }, () -> {
                System.out.println("Hortifruti Erro: Produto com ID " + stock.getProductId() + " não encontrado no banco!");
            });
        });
    }

}
