package com.crud.tasks.domain;

import com.fasterxml.jackson.annotation.*;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Badges {

    @JsonProperty("votes")
    private int votes;

    @JsonProperty("attachmentsByType")
    private AttachmentsByType attachmentsByType;
}
