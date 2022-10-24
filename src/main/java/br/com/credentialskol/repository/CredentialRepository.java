package br.com.credentialskol.repository;

import br.com.credentialskol.controller.dto.response.CredentialResponse;
import br.com.credentialskol.domain.Entities.DataCredentials;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface CredentialRepository extends ReactiveMongoRepository<DataCredentials, String> {

    @Query("{$and : [{idCargo: { $in : ?0 }}, {username: { $in : ?1 }}]}")
    Flux<CredentialResponse> findCredentialByHeader(String idCargo, String username);

}
