package api.steps;

import api.models.BodyArgs;
import api.models.BodyResult;
import api.models.users.CreateUser;
import api.models.users.UpdateUser;
import api.models.users.UserId;
import api.models.users.UserProperties;
import io.restassured.common.mapper.TypeRef;
import io.restassured.response.Response;

import static api.enums.UserRoles.USER;
import static api.methods.Users.*;
import static org.apache.hc.core5.http.HttpStatus.SC_OK;
import static utils.EnvProperties.API_TOKEN;
import static utils.EnvProperties.API_USERNAME;

public class UserApiSteps extends BaseApiSteps {
    public String createUser(String username, String pass) {
        CreateUser args = CreateUser.builder()
                .username(username)
                .name(username)
                .password(pass)
                .email(username + "@mail.com")
                .role(USER.getRole())
                .build();
        BodyArgs bodyArgs = BodyArgs.builder().
                params(args)
                .method(CREATE_USER)
                .build();
        Response response = postRequest(API_USERNAME, API_TOKEN, bodyArgs);
        response.then().statusCode(SC_OK);
        BodyResult bodyResult = response.as(BodyResult.class);
        return bodyResult.getResult().toString();
    }

    public BodyResult<UserProperties> getUserById(Integer userId) {
        BodyArgs bodyArgs = BodyArgs.builder().
                params(new UserId(userId))
                .method(GET_USER)
                .build();
        Response response = postRequest(API_USERNAME, API_TOKEN, bodyArgs);
        response.then().statusCode(SC_OK);
        return response.as(new TypeRef<BodyResult<UserProperties>>() {
        });
    }

    public boolean updateUserWithRequiredParameters(Integer userId, String role) {
        UpdateUser args = UpdateUser.builder()
                .id(Integer.valueOf(userId))
                .role(role)
                .build();
        BodyArgs bodyArgs = BodyArgs.builder()
                .params(args)
                .method(UPDATE_USER)
                .build();
        Response response = postRequest(API_USERNAME, API_TOKEN, bodyArgs);
        response.then().statusCode(SC_OK);
        return (boolean) response.as(BodyResult.class).getResult();
    }

    public boolean updateUserWithoutRequiredParameters(String role) {
        UpdateUser args = UpdateUser.builder()
                .role(role)
                .build();
        BodyArgs bodyArgs = BodyArgs.builder()
                .params(args)
                .method(UPDATE_USER)
                .build();
        Response response = postRequest(API_USERNAME, API_TOKEN, bodyArgs);
        response.then().statusCode(SC_OK);
        return (boolean) response.as(BodyResult.class).getResult();
    }

    public boolean deleteUser(Integer userId) {
        BodyArgs bodyArgs = BodyArgs.builder().
                params(new UserId(Integer.valueOf(userId)))
                .method(DELETE_USER)
                .build();
        Response response = postRequest(API_USERNAME, API_TOKEN, bodyArgs);
        response.then().statusCode(SC_OK);
        return (boolean) response.as(BodyResult.class).getResult();
    }
}
