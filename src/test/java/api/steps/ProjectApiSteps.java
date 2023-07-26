package api.steps;

import api.models.BodyArgs;
import api.models.BodyResult;
import api.models.projects.CreateProject;
import api.models.projects.ProjectId;
import api.models.projects.ProjectName;
import api.models.projects.ProjectProperties;
import io.restassured.common.mapper.TypeRef;
import io.restassured.response.Response;

import java.util.List;

import static api.methods.Projects.*;
import static org.apache.hc.core5.http.HttpStatus.SC_OK;
import static utils.EnvProperties.API_TOKEN;
import static utils.EnvProperties.API_USERNAME;

public class ProjectApiSteps extends BaseApiSteps {

    public String createNewProject(String projectName) {
        CreateProject args = CreateProject.builder()
                .name(projectName)
                .build();
        BodyArgs bodyArgs = BodyArgs.builder().
                params(args)
                .method(CREATE_PROJECT)
                .build();
        Response response = postRequest(API_USERNAME, API_TOKEN, bodyArgs);
        response.then().statusCode(SC_OK);
        BodyResult bodyResult = response.as(BodyResult.class);
        return bodyResult.getResult().toString();
    }

    public BodyResult<ProjectProperties> getProjectById(Integer projectId) {
        BodyArgs bodyArgs = BodyArgs.builder().
                params(new ProjectId(projectId))
                .method(GET_PROJECT_BY_ID)
                .build();
        Response response = postRequest(API_USERNAME, API_TOKEN, bodyArgs);
        response.then().statusCode(SC_OK);
        return response.as(new TypeRef<BodyResult<ProjectProperties>>() {
        });
    }

    public BodyResult<ProjectProperties> getProjectByName(String projectName) {
        BodyArgs bodyArgs = BodyArgs.builder().
                params(new ProjectName(projectName))
                .method(GET_PROJECT_BY_NAME)
                .build();
        Response response = postRequest(API_USERNAME, API_TOKEN, bodyArgs);
        response.then().statusCode(SC_OK);
        return response.as(new TypeRef<BodyResult<ProjectProperties>>() {
        });
    }

    public BodyResult<List<ProjectProperties>> getAllProjects() {
        BodyArgs bodyArgs = BodyArgs.builder()
                .method(GET_ALL_PROJECTS)
                .build();
        Response response = postRequest(API_USERNAME, API_TOKEN, bodyArgs);
        response.then().statusCode(SC_OK);
        return response.as(new TypeRef<BodyResult<List<ProjectProperties>>>() {
        });
    }

    public boolean addProjectUser(String projectId, String userId, String projectRole) {
        BodyArgs bodyArgs = BodyArgs.builder()
                .params(List.of(projectId, userId, projectRole))
                .method(ADD_PROJECT_USER)
                .build();
        Response response = postRequest(API_USERNAME, API_TOKEN, bodyArgs);
        response.then().statusCode(SC_OK);
        return (boolean) response.as(BodyResult.class).getResult();
    }

    public boolean deleteProject(Integer projectId) {

        BodyArgs bodyArgs = BodyArgs.builder().
                params(new ProjectId(Integer.valueOf(projectId)))
                .method(REMOVE_PROJECT)
                .build();
        Response response = postRequest(API_USERNAME, API_TOKEN, bodyArgs);
        response.then().statusCode(SC_OK);
        return (boolean) response.as(BodyResult.class).getResult();
    }
}
