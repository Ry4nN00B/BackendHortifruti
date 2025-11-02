package me.ry4nn00b.hortifruti.Service;

import me.ry4nn00b.hortifruti.DTOs.PromotionRequestDTO;
import me.ry4nn00b.hortifruti.DTOs.PromotionResponseDTO;
import me.ry4nn00b.hortifruti.DTOs.PromotionUpdateDTO;
import me.ry4nn00b.hortifruti.Mapper.PromotionMapper;
import me.ry4nn00b.hortifruti.Model.PromotionModel;
import me.ry4nn00b.hortifruti.Repository.IPromotionRepository;
import me.ry4nn00b.hortifruti.Service.Interface.IPromotionService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class PromotionService implements IPromotionService {

    private final IPromotionRepository repository;
    private final PromotionMapper mapper;

    public PromotionService(IPromotionRepository repository, PromotionMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    //Get Promotion List
    @Override
    public List<PromotionResponseDTO> promotionActiveList() {
        LocalDate today = LocalDate.now();
        return repository.findActivePromotions(today, today).stream()
                .map(mapper::toResponseDTO)
                .toList();
    }

    //Get Promotion By ID
    @Override
    public Optional<PromotionResponseDTO> promotionFindById(String id) {
        return repository.findById(id)
                .map(mapper::toResponseDTO);
    }

    //Save Promotion
    @Override
    public PromotionResponseDTO promotionSave(PromotionRequestDTO requestDTO) {
        if (requestDTO.getStartDate().isAfter(requestDTO.getEndDate())) {
            throw new IllegalArgumentException("A data de início deve ser antes da data de término.");
        }

        //Check Promotion's
        List<PromotionModel> conflicts = repository.findByProductIdAndEndDateAfter(requestDTO.getProductId(), requestDTO.getStartDate());
        boolean hasConflict = conflicts.stream().anyMatch(p -> !p.getId().equals(requestDTO.getProductId()));

        if (hasConflict) {
            throw new IllegalArgumentException("Já existe uma promoção ativa para este produto nesse período.");
        }

        PromotionModel model = mapper.toModel(requestDTO);
        PromotionModel saved = repository.save(model);
        return mapper.toResponseDTO(saved);
    }

    //Update Promotion
    @Override
    public Optional<PromotionResponseDTO> promotionUpdate(String id, PromotionUpdateDTO updateDTO) {
        return Optional.ofNullable(repository.findById(id).map(existing -> {

            if (updateDTO.getProductId() != null) existing.setProductId(updateDTO.getProductId());
            if (updateDTO.getType() != null) existing.setType(updateDTO.getType());
            if (updateDTO.getValue() != null) existing.setValue(updateDTO.getValue());
            if (updateDTO.getStartDate() != null) existing.setStartDate(updateDTO.getStartDate());
            if (updateDTO.getEndDate() != null) existing.setEndDate(updateDTO.getEndDate());

            PromotionModel updated = repository.save(existing);
            return mapper.toResponseDTO(updated);
        }).orElseThrow(() -> new IllegalArgumentException("Hortifruti Erro: Categoria não encontrada. ID: " + id)));
    }

    //Delete Promotion
    @Override
    public void promotionDelete(String id) {
        repository.deleteById(id);
    }

    //Automatic Apply Promotion
    @Override
    public BigDecimal applyPromotion(String productId, BigDecimal originalPrice) {
        LocalDate today = LocalDate.now();
        Optional<PromotionModel> promotion = repository.findActivePromotionByProduct(productId, today, today);

        if (promotion.isPresent()) {
            PromotionModel promo = promotion.get();
            if (promo.getType().equalsIgnoreCase("PERCENT")) {
                return originalPrice.subtract(originalPrice.multiply(BigDecimal.valueOf(promo.getValue()))
                        .divide(BigDecimal.valueOf(100), 2, RoundingMode.HALF_UP));
            } else if (promo.getType().equalsIgnoreCase("FIXED_VALUE")) {
                return originalPrice.subtract(BigDecimal.valueOf(promo.getValue())).max(BigDecimal.ZERO);
            }
        }

        return originalPrice;
    }

}
