package api.models.users;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserProperties {
    private Integer id;
    private String username;
    private String password;
    private String is_ldap_user;
    private String role;
    private String name;
    private String email;
    private String google_id;
    private String github_id;
    private String notifications_enabled;
    private String timezone;
    private String language;
    private String disable_login_form;
    private String twofactor_activated;
    private String twofactor_secret;
    private String token;
    private String notifications_filter;
}
