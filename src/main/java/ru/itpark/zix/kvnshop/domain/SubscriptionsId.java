package ru.itpark.zix.kvnshop.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.*;

import java.io.Serializable;
import java.util.UUID;

@Embeddable
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class SubscriptionsId implements Serializable {

    @Column(name = "config_id", nullable = false)
    private UUID configId;

    @Column(name = "server_id", nullable = false)
    private Long serverId;

    @Column(name = "user_id", nullable = false)
    private Long userId;
}
