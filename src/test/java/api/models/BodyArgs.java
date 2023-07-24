package api.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class BodyArgs<T> {
    @Builder.Default
    private String jsonrpc = "2.0";
    @Builder.Default
    private int id = 1416806551;
    public String method;
    public T params;
}
