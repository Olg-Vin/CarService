package org.vinio.models;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import lombok.Data;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@MappedSuperclass
public abstract class BaseEntityCM extends BaseEntityId {
    private LocalDateTime created;
    private LocalDateTime modified;

    @PrePersist
    void setCreated() {
        created = LocalDateTime.now();
    }

    @PreUpdate
    void setModified() {
        modified = LocalDateTime.now();
    }

    @Column(name = "created")
    public LocalDateTime getCreated() {
        return created;
    }

    @Column(name = "modified")
    public LocalDateTime getModified() {
        return modified;
    }
}
