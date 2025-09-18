package me.ry4nn00b.hortifruti.Repository;

import me.ry4nn00b.hortifruti.Model.UserModel;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface IUserRepository extends MongoRepository<UserModel, String> {
    Optional<UserModel> findByEmail(String email);
}
