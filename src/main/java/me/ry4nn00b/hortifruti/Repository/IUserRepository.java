package me.ry4nn00b.hortifruti.Repository;

import me.ry4nn00b.hortifruti.Model.UserModel;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IUserRepository extends MongoRepository<UserModel, String> {
    Optional<UserModel> findByEmail(String email);
    boolean existsByEmail(String email);
}
