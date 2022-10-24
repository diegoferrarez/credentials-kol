package br.com.credentialskol.domain.Entities;

import br.com.credentialskol.domain.StatusCredentials;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Builder
@Data
@AllArgsConstructor
@RequiredArgsConstructor
@Document(collection = "CredentialsBancoKol")
public class DataCredentials {

    @Id
    private String id;
    private String idCargo;
    private String cargo;
    private String name;
    private String username;
    private String password;
    private StatusCredentials status;

}
