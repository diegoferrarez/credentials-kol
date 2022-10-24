package br.com.credentialskol.service;

import br.com.credentialskol.controller.dto.request.CredentialRequest;
import br.com.credentialskol.controller.dto.response.CredentialResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CredentialService {

    Flux<CredentialResponse> findAll();

    Mono<CredentialResponse> findById(String id);

    Mono<CredentialResponse> salvar(CredentialRequest request);

    Mono<CredentialResponse> inactive(String id);

    Mono<CredentialResponse> update(String id, CredentialRequest request);

    Flux<CredentialResponse> credentialByHeader(String idCargo, String username);

}
