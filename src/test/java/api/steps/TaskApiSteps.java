package api.steps;

import api.models.BodyArgs;
import api.models.BodyResult;
import api.models.tasks.*;
import io.restassured.common.mapper.TypeRef;
import io.restassured.response.Response;

import java.util.List;

import static api.methods.Tasks.*;
import static org.apache.hc.core5.http.HttpStatus.SC_OK;
import static utils.EnvProperties.API_TOKEN;
import static utils.EnvProperties.API_USERNAME;

public class TaskApiSteps extends BaseApiSteps {
    public String createNewTaskWithRequiredParameters(String titleTask, Integer projectId) {
        CreateTask args = CreateTask.builder()
                .title(titleTask)
                .project_id(projectId)
                .build();

        BodyArgs bodyArgs = BodyArgs.builder().
                params(args)
                .method(CREATE_TASK)
                .build();

        Response response = postRequest(API_USERNAME, API_TOKEN, bodyArgs);
        response.then().statusCode(SC_OK);
        BodyResult bodyResult = response.as(BodyResult.class);
        return bodyResult.getResult().toString();
    }

    public String createNewTask(String titleTask, Integer projectId,
                                String colorId, Integer columnId, Integer ownerId, Integer creatorId,
                                String dateDue, String description, Integer categoryId, Integer score,
                                Integer swimLaneId, Integer priority, Integer recurrenceStatus,
                                Integer recurrenceTrigger, Integer recurrenceFactor, Integer recurrenceTimeframe,
                                Integer recurrenceBaseDate, String reference, List<String> tags,
                                String dateStarted) {
        CreateTask args = CreateTask.builder()
                .title(titleTask)
                .project_id(projectId)
                .color_id(colorId)
                .column_id(columnId)
                .owner_id(ownerId)
                .creator_id(creatorId)
                .date_due(dateDue)
                .description(description)
                .category_id(categoryId)
                .score(score)
                .swimlane_id(swimLaneId)
                .priority(priority)
                .recurrence_status(recurrenceStatus)
                .recurrence_trigger(recurrenceTrigger)
                .recurrence_factor(recurrenceFactor)
                .recurrence_timeframe(recurrenceTimeframe)
                .recurrence_basedate(recurrenceBaseDate)
                .reference(reference)
                .tags(tags)
                .date_started(dateStarted)
                .build();

        BodyArgs bodyArgs = BodyArgs.builder().
                params(args)
                .method(CREATE_TASK)
                .build();

        Response response = postRequest(API_USERNAME, API_TOKEN, bodyArgs);
        response.then().statusCode(SC_OK);
        BodyResult bodyResult = response.as(BodyResult.class);
        return bodyResult.getResult().toString();
    }

    public BodyResult<TaskProperties> getTaskById(Integer taskId) {

        BodyArgs bodyArgs = BodyArgs.builder()
                .params(new TaskId(taskId))
                .method(GET_TASK_BY_ID)
                .build();

        Response response = postRequest(API_USERNAME, API_TOKEN, bodyArgs);
        return response.as(new TypeRef<BodyResult<TaskProperties>>() {
        });
    }

    public BodyResult<List<TaskProperties>> getAllTasks(Integer projectId, Integer statusId) {

        RequestAllTasks args = new RequestAllTasks().builder()
                .project_id(projectId)
                .status_id(statusId)
                .build();

        BodyArgs bodyArgs = BodyArgs.builder()
                .params(args)
                .method(GET_ALL_TASKS)
                .build();

        Response response = postRequest(API_USERNAME, API_TOKEN, bodyArgs);
        return response.as(new TypeRef<BodyResult<List<TaskProperties>>>() {
        });
    }

    public boolean deleteTask(Integer taskId) {

        BodyArgs bodyArgs = BodyArgs.builder().
                params(new TaskId(taskId))
                .method(REMOVE_TASK)
                .build();

        Response response = postRequest(API_USERNAME, API_TOKEN, bodyArgs);
        return (boolean) response.as(BodyResult.class).getResult();
    }

    public boolean closeTask(Integer taskId) {

        BodyArgs bodyArgs = BodyArgs.builder().
                params(new TaskId(taskId))
                .method(CLOSE_TASK)
                .build();

        Response response = postRequest(API_USERNAME, API_TOKEN, bodyArgs);
        return (boolean) response.as(BodyResult.class).getResult();
    }

    public boolean moveTaskPosition(Integer projectId, Integer taskId, Integer columnId, Integer position, Integer swimLaneId) {
        RequestMoveTask args = RequestMoveTask.builder()
                .project_id(projectId)
                .task_id(taskId)
                .column_id(columnId)
                .position(position)
                .swimlane_id(swimLaneId)
                .build();

        BodyArgs bodyArgs = BodyArgs.builder().
                params(args)
                .method(MOVE_TASK_POSITION)
                .build();

        Response response = postRequest(API_USERNAME, API_TOKEN, bodyArgs);
        return (boolean) response.as(BodyResult.class).getResult();
    }
}
