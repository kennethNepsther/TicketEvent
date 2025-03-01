package com.ticketevent.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.ticketevent.entity.dto.request.AuthRequestDto;
import com.ticketevent.enums.ERole;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.*;
import org.hibernate.annotations.NaturalId;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tb_users")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(updatable = false, unique = true, nullable = false)
    private UUID userId;
    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Column(unique = true, nullable = false)
    private String phoneNumber;

    @JsonIgnore
    private String password;

    @Column(nullable = false)
    @NaturalId(mutable = true)
    private String email;

    private boolean isEnabled = false;
    private boolean isAccountNonLocked;

    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE,  CascadeType.REFRESH})
    @JoinTable(
            name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<RoleEntity> roles;


    public boolean isLoginCorrect(@Valid AuthRequestDto authRequestDto, BCryptPasswordEncoder passwordEncoder) {
        return passwordEncoder.matches(authRequestDto.password(), this.password);
    }






   /* * @JsonIgnore
    @OneToMany(mappedBy = "organizer", orphanRemoval = true, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<EventModel> events = new ArrayList<>();*/


}
