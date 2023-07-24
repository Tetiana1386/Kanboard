package api.models.projects;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class ProjectProperties {
    private String id;
    private String name;
    private String is_active;
    private String token;
    private String last_modified;
    private String is_public;
    private String is_private;
    private String default_swimlane;
    private String show_default_swimlane;
    private String description;
    private String identifier;
}
