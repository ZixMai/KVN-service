package ru.itpark.zix.kvnshop.domain;

import io.hypersistence.utils.hibernate.type.basic.Inet;
import io.hypersistence.utils.hibernate.type.basic.PostgreSQLInetType;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Type;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "servers", schema = "public", catalog = "kvnmarket_db")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Server {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_id")
    private User owner;

    @Column(name = "hosting", length = 256)
    private String hosting;

    @Column(name = "location", length = 256)
    private String location;

    @Column(name = "description", length = 256)
    private String description;

    @Column(name = "monthly_price", precision = 10, scale = 1)
    private BigDecimal monthlyPrice;

    @Type(PostgreSQLInetType.class)
    @Column(name = "ip", nullable = false, columnDefinition = "inet")
    private Inet ip;

    @Column(name = "domain", length = 256)
    private String domain;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "last_ping_at", nullable = false)
    private LocalDateTime lastPingAt;

    @Builder.Default
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "server")
    private Set<Subscription> subscriptions = new HashSet<>();

    @Builder.Default
    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "servers")
    private Set<UserSpace> userSpaces = new HashSet<>();
}
