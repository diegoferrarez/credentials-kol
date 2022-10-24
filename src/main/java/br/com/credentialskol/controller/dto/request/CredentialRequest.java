package br.com.credentialskol.controller.dto.request;

import br.com.credentialskol.domain.StatusCredentials;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CredentialRequest {

    private String id;
    private String idCargo;
    private String cargo;
    private String name;
    private String username;
    private String password;
    private StatusCredentials status;
}
