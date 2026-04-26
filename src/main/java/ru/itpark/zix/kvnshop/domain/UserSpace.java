package ru.itpark.zix.kvnshop.domain;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "user_spaces", schema = "public", catalog = "kvnmarket_db")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserSpace {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_id")
    private User owner;

    @Column(name = "space_name", length = 256)
    private String spaceName;

    @Builder.Default
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "space_servers",
            schema = "public",
            catalog = "kvnmarket_db",
            joinColumns = @JoinColumn(name = "space_id", nullable = false),
            inverseJoinColumns = @JoinColumn(name = "server_id", nullable = false)
    )
    private Set<Server> servers = new HashSet<>();

    @Builder.Default
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "space_configs",
            schema = "public",
            catalog = "kvnmarket_db",
            joinColumns = @JoinColumn(name = "space_id", nullable = false),
            inverseJoinColumns = @JoinColumn(name = "config_id", nullable = false)
    )
    private Set<Config> configs = new HashSet<>();
}
