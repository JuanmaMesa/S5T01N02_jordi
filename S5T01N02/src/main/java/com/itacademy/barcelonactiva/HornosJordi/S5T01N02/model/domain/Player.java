package com.itacademy.barcelonactiva.HornosJordi.S5T01N02.model.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SourceType;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;


import java.sql.Timestamp;
import java.util.Collection;
import java.util.List;


@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "players")
public class Player implements UserDetails {

    @Column(name="username")
    private String username;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "password")
    private String password;

    private Float winRate;

    @Column(nullable = false, updatable = false, insertable = false)
    @CreationTimestamp(source = SourceType.DB)
    private Timestamp registerDate;

    public Player(String username){
        this.username = username;
        this.winRate = null;
    }

    public Player(String username, String password) {
        this.username = username;
        this.password = password;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(this.username));
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
