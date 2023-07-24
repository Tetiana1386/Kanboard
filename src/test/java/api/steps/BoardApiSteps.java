package api.steps;

import io.restassured.common.mapper.TypeRef;
import io.restassured.response.Response;
import api.models.board.BoardProperties;
import api.models.BodyResult;
import api.models.BodyArgs;
import api.models.projects.ProjectId;
import java.util.List;
import static api.methods.Boards.GET_BOARD;
import static utils.EnvProperties.API_TOKEN;
import static utils.EnvProperties.API_USERNAME;
public class BoardApiSteps extends BaseApiSteps{
    public BodyResult<List<BoardProperties>> getBoardForProject(Integer projectId) {

        BodyArgs bodyArgs = BodyArgs.builder().
                params(new ProjectId(projectId))
                .method(GET_BOARD)
                .build();

        Response response = postRequest(API_USERNAME, API_TOKEN, bodyArgs);
        return response.as(new TypeRef<BodyResult<List<BoardProperties>>>(){});
    }
}
