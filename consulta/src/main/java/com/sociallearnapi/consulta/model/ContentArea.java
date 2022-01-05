package com.sociallearnapi.cadastro.model;


import com.sociallearnapi.cadastro.response.ContentAreaResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document("contentareas")
public class ContentArea {
    @Id
    private String id;
    private String name;
    private boolean active;

    @CreatedDate
    private Date createdDate;

    @LastModifiedDate
    private Date lastModifiedDate;

    public ContentAreaResponse toResponse() {
        return ContentAreaResponse.builder().id(getId()).name(getName()).active(isActive()).build();
    }
}
