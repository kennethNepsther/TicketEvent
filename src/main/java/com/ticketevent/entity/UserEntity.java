package com.ticketevent.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.ticketevent.enums.ERole;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.*;
import org.hibernate.annotations.NaturalId;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tb_users")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserEntity  implements UserDetails,Serializable {
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

    private ERole role;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (this.role == ERole.ADMIN){
            return List.of(new SimpleGrantedAuthority("ROLE_ADMIN"),
                    new SimpleGrantedAuthority("ROLE_PARTICIPANT"),
                    new SimpleGrantedAuthority("ROLE_ORGANIZER"));
        }
        if (this.role == ERole.ORGANIZER){
            return List.of(new SimpleGrantedAuthority("ROLE_ORGANIZER"),
                    new SimpleGrantedAuthority("ROLE_PARTICIPANT"));

        }else{
            return List.of(new SimpleGrantedAuthority("ROLE_PARTICIPANT"));
        }

    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public boolean isEnabled() {
        return isEnabled;
    }



   /* * @JsonIgnore
    @OneToMany(mappedBy = "organizer", orphanRemoval = true, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<EventModel> events = new ArrayList<>();*/



}
