package me.ry4nn00b.hortifruti.Repository;

import me.ry4nn00b.hortifruti.Model.SupplierModel;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ISupplierRepository extends MongoRepository<SupplierModel, String> {
}
