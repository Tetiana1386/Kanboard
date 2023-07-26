package api.models.board;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class BoardProperties {
    private Integer project_id;
    private Integer is_active;
    private Integer id;
    private String name;
    private String description;
    private List<ColumnDetails> columns;
    private Integer nb_columns;
    private Integer task_limit;
    private Integer nb_swimlanes;
    private Integer nb_tasks;
    private Integer score;
}
