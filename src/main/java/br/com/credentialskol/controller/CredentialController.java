package br.com.credentialskol.controller;

import br.com.credentialskol.controller.dto.request.CredentialRequest;
import br.com.credentialskol.controller.dto.response.CredentialResponse;
import br.com.credentialskol.service.CredentialService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/credentials")
public class CredentialController {

    @Autowired
    private CredentialService service;

    @GetMapping("/byHeader")
    @ResponseStatus(HttpStatus.OK)
    public Flux<CredentialResponse> getCredentialByHeader(@RequestHeader(value = "LGN-id-cargo") String idCargo){
        return service.byHeader(idCargo);
    }

    @ApiOperation("busca todas as credenciais")
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Flux<CredentialResponse> getAll(){
        return service.findAll();
    }

    @ApiOperation("busca uma credencial por id")
    @GetMapping("/employee/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Mono<CredentialResponse> getById(@PathVariable String id){
        return service.findById(id);
    }

    @ApiOperation("cria uma credencial")
    @PostMapping("/bkCreate")
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<CredentialResponse> create(@RequestBody CredentialRequest request){
        return service.salvar(request);
    }

    @ApiOperation("deixa a credencial inativada")
    @PatchMapping("/employee/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Mono<CredentialResponse> inactivated(@PathVariable String id){
        return service.inactive(id);
    }

    @ApiOperation("aumento de cargo")
    @PutMapping("/increase/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Mono<CredentialResponse> update(@PathVariable String id, @RequestBody CredentialRequest request){
        return service.update(id, request);
    }
}
