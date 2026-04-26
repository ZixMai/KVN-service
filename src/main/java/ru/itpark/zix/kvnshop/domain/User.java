package ru.itpark.zix.kvnshop.domain;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(
        name = "users",
        schema = "public",
        catalog = "kvnmarket_db",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "tg_id"),
                @UniqueConstraint(columnNames = "username")
        }
)
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;

    @Column(name = "username", unique = true, nullable = false, length = 256)
    private String username;

    @Column(name = "tg_id", unique = true, nullable = false)
    private Long tgId;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "last_seen_at", nullable = false)
    private LocalDateTime lastSeenAt;

    @Builder.Default
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "owner")
    private Set<UserSpace> userSpaces = new HashSet<>();

    @Builder.Default
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "buyer")
    private Set<Order> buyerOrders = new HashSet<>();

    @Builder.Default
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "owner")
    private Set<Server> servers = new HashSet<>();

    @Builder.Default
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    private Set<Subscription> subscriptions = new HashSet<>();

    @Builder.Default
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "owner")
    private Set<Config> configs = new HashSet<>();

    @Builder.Default
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "seller")
    private Set<Order> sellerOrders = new HashSet<>();
}
