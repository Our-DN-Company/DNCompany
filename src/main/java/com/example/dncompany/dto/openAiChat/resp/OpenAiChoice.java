package org.example.openaiex.resp;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.example.openaiex.req.OpenAiMessage;

@Getter @Setter @ToString
public class OpenAiChoice {
    private Integer index;
    private OpenAiMessage message;
    @JsonProperty("finish_reason")
    private String finishReason;
}














