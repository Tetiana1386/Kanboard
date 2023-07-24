package api.models.projects;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class CreateProject {
    private String name;
    private String description;
    private Integer owner_id;
    private String identifier;
    private String start_date;
    private String end_date;
}
