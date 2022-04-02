package com.femissiameri.api.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Server {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotEmpty(message = "IP Address cannot be empty or null.")
    @Column(unique = true)
    private String ipAddress;
    private String name;
    private String memory;
    private String type;
    private String imageUrl;
    private EStatus status;
}
