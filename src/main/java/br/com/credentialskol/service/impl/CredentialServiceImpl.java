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

import java.util.ArrayList;
import java.util.List;

@Service
public class CredentialServiceImpl implements CredentialService {

    @Autowired
    private CredentialRepository repository;

    @Override
    public Flux<CredentialResponse> byHeader(String idCargo) {
        return repository.findCredentialByHeader(idCargo).map(CredentialResponse::converte);
    }

    @Override
    public Flux<CredentialResponse> findAll() {
        return repository.findAll().map(CredentialResponse::converte);
    }

    @Override
    public Mono<CredentialResponse> findById(String id) {
        return repository.findById(id).map(CredentialResponse::converte);
    }

    @Override
    @Transactional
    public Mono<CredentialResponse> salvar(CredentialRequest dto) {
        DataCredentials credentials = upCred(dto);
        String password = CriptoCredential.criptografarBase64(dto.getPassword());
        credentials.setPassword(password);
        credentials.setStatus(StatusCredentials.ATIVO);
        return repository.save(credentials).map(CredentialResponse::converte);
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
            return modelMapper.map(result, CredentialResponse.class);
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
            return modelMapper.map(credentials, CredentialResponse.class);
        });
    }

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

