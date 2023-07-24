package api.models.board;

import api.models.tasks.TaskProperties;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class ColumnDetails {
    private Integer id;
    private String title;
    private Integer position;
    private Integer project_id;
    private Integer task_limit;
    private Integer hide_in_dashboard;
    private String description;
    private List<TaskProperties> tasks;
    private Integer nb_open_tasks;
    private Integer nb_tasks;
    private Integer nb_closed_tasks;
    private Integer score;
    private Integer column_nb_tasks;
    private Integer column_score;
    private Integer column_nb_score;
    private Integer column_nb_open_tasks;
}
