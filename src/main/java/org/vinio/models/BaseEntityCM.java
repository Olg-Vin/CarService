package org.vinio.models;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import lombok.Data;

import java.time.LocalDateTime;

@MappedSuperclass
@Data
public abstract class BaseEntityCM extends BaseEntityId {
    @Column(name = "created")
    private LocalDateTime created;
    @Column(name = "modified")
    private LocalDateTime modified;

    @PrePersist
    void setCreated(){
        created = LocalDateTime.now();
    }

    @PreUpdate
    void setModified(){
        modified = LocalDateTime.now();
    }
}
