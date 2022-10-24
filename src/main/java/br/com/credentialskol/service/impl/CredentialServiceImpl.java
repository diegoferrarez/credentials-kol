package br.com.credentialskol.service.impl;

import br.com.credentialskol.controller.dto.request.CredentialRequest;
import br.com.credentialskol.controller.dto.response.CredentialResponse;
import br.com.credentialskol.domain.Entities.DataCredentials;
import br.com.credentialskol.domain.StatusCredentials;
import br.com.credentialskol.repository.CredentialRepository;
import br.com.credentialskol.service.CredentialService;
import br.com.credentialskol.utils.CriptoCredential;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class CredentialServiceImpl implements CredentialService {

    @Autowired
    private CredentialRepository repository;

    @Override
    public Flux<CredentialResponse> findAll() {
        return repository.findAll().map(CredentialResponse::converter);
    }

    @Override
    public Mono<CredentialResponse> findById(String id) {
        return repository.findById(id).map(CredentialResponse::converter);
    }

    @Override
    @Transactional
    public Mono<CredentialResponse> salvar(CredentialRequest dto) {
        DataCredentials credentials = upCred(dto);
        String password = CriptoCredential.criptografarBase64(dto.getPassword());
        credentials.setPassword(password);
        credentials.setStatus(StatusCredentials.ATIVO);
        return repository.save(credentials).map(CredentialResponse::converter);
    }

    @Override
    @Transactional
    public Mono<CredentialResponse> inactive(String id) {

        ModelMapper modelMapper = new ModelMapper();
        return repository.findById(id).map(result -> {
            if (result.getStatus() == StatusCredentials.ATIVO) {
                result.setStatus(StatusCredentials.INATIVO);

            } else {
                result.setStatus(StatusCredentials.ATIVO);

            }
            repository.save(result).subscribe();
            CredentialResponse response = modelMapper.map(result, CredentialResponse.class);
            return response;
        });
    }

    @Override
    @Transactional
    public Mono<CredentialResponse> update(String id, CredentialRequest dto) {
        ModelMapper modelMapper = new ModelMapper();
        return repository.findById(id).map(c -> {
            DataCredentials credentials = upCred(dto);
            credentials.setId(id);
            credentials.setStatus(c.getStatus());
            repository.save(credentials).subscribe();
            CredentialResponse result = modelMapper.map(credentials, CredentialResponse.class);
            return result;
        });
    }

    @Override
    public Flux<CredentialResponse> credentialByHeader(String idCargo, String username) {
        return null;
    }

//    @Override
//    public Flux<CredentialResponse> credentialByHeader(String idCargo, String username) {
//        return repository.findCredentialByHeader(idCargo, username);
//    }


    private DataCredentials upCred(CredentialRequest dto){
        return DataCredentials.builder()
                .idCargo(dto.getIdCargo())
                .cargo(dto.getCargo())
                .name(dto.getName())
                .username(dto.getUsername())
                .password(dto.getPassword())
                .status(dto.getStatus())
                .build();
    }
}

