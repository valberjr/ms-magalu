package com.example.ms_magalu.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_status")
public class Status {

    @Id
    private Long id;

    private String description;

    public Status() {
    }

    public Status(Long id, String description) {
        this.id = id;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public enum Values {
        PENDING(1L, "pending"),
        SUCCESS(2L, "success"),
        ERROR(3L, "error"),
        CANCELLED(4L, "cancelled");

        private Long id;
        private String description;

        Values(Long id, String decription) {
            this.id = id;
            this.description = decription;
        }

        public Status toStatus() {
            return new Status(id, description);
        }
    }
}
