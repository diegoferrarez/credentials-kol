package br.com.credentialskol.repository;

import br.com.credentialskol.domain.Entities.DataCredentials;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface CredentialRepository extends ReactiveMongoRepository<DataCredentials, String> {

    @Query("{ 'idCargo' : ?0 }")
    Flux<DataCredentials> findCredentialByHeader(String idCargo);
}
