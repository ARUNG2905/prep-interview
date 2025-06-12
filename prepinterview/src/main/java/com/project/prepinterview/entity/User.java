package com.project.prepinterview.entity;

import com.project.prepinterview.enums.UserRole;
import jakarta.persistence.*;
import jdk.jfr.Timestamp;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.Instant;
@Entity
@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "user_id",nullable = false,updatable = false)
    private String userId;

    @Column(name = "user_name",nullable = false)
    private String userName;


    @Column(name = "email",nullable = false,updatable = false)
    private String email;

    @Column(name = "password",nullable = false)
    private String password;

    @CreationTimestamp
    @CreatedDate
    @Column(name = "created_at",nullable = false,updatable = false)
    private Instant created;


    @UpdateTimestamp
    @LastModifiedDate
    @Column(name = "last_modified_at",nullable = false,updatable = false)
    private  Instant lastModified;

    @Column(name = "role", nullable = false, updatable = false)
    private UserRole role;

}
