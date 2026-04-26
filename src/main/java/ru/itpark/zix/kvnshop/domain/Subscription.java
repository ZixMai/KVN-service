package ru.itpark.zix.kvnshop.domain;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "subscriptions", schema = "public", catalog = "kvnmarket_db")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Subscription {

    @EmbeddedId
    @AttributeOverrides({
            @AttributeOverride(name = "configId", column = @Column(name = "config_id", nullable = false)),
            @AttributeOverride(name = "serverId", column = @Column(name = "server_id", nullable = false)),
            @AttributeOverride(name = "userId", column = @Column(name = "user_id", nullable = false))
    })
    private SubscriptionsId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "server_id", nullable = false, insertable = false, updatable = false)
    private Server server;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "config_id", nullable = false, insertable = false, updatable = false)
    private Config config;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false, insertable = false, updatable = false)
    private User user;

    @Column(name = "valid_to")
    private LocalDateTime validTo;
}
