package ru.itpark.zix.kvnshop.domain;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "configs", schema = "public", catalog = "kvnmarket_db")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Config {

    @Id
    @Column(name = "id", unique = true, nullable = false)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_id")
    private User owner;

    @Column(name = "vpn_protocol", length = 256)
    private String vpnProtocol;

    @Column(name = "config_format", length = 256)
    private String configFormat;

    @Column(name = "config")
    private String config;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    @Column(name = "is_banned")
    private Boolean isBanned;

    @Column(name = "short_config_link")
    private String shortConfigLink;

    @Builder.Default
    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "configs")
    private Set<UserSpace> userSpaces = new HashSet<>();

    @Builder.Default
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "config")
    private Set<Subscription> subscriptions = new HashSet<>();
}
