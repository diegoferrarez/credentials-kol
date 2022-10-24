package br.com.credentialskol.controller.dto.response;

import br.com.credentialskol.domain.StatusCredentials;
import br.com.credentialskol.utils.CriptoCredential;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CredentialResponse {

    private String id;
    private String idCargo;
    private String cargo;
    private String name;
    private String username;
    private String password;
    private StatusCredentials status;

    public static CredentialResponse converter(CredentialResponse c) {
        String password = CriptoCredential.descriptografarBase64(c.getPassword());
        return CredentialResponse.builder()
                .id(c.getId())
                .idCargo(c.getIdCargo())
                .cargo(c.getCargo())
                .name(c.getName())
                .username(c.getUsername())
                .password(password)
                .status(c.getStatus())
                .build();
    }
}
