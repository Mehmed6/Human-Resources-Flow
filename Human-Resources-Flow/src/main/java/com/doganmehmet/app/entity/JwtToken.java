package com.doganmehmet.app.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "jwt_token")
@NoArgsConstructor
public class JwtToken {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "jwt_token_id")
    private long jwtTokenId;

    @Column(name = "jwt_token")
    private String jwtToken;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "refresh_token_id", nullable = false)
    private RefreshToken refreshToken;

    public JwtToken(String jwtToken, User user, RefreshToken refreshToken)
    {
        this.jwtToken = jwtToken;
        this.user = user;
        this.refreshToken = refreshToken;
    }
}
