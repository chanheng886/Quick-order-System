package com.example.backend.entities;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.UUID;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import com.example.backend.enums.UserRole;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table (name = "tb_user")
@Data 
@NoArgsConstructor 
@AllArgsConstructor 
@Entity 
public class User implements UserDetails {
    @Id @GeneratedValue (strategy = GenerationType.UUID)
    private UUID id;

    @Column (nullable = false, unique = true)
    private String email;

    @Column (name = "password_hash", nullable = false)
    private String password;

    @Column (name = "full_name", nullable = false)
    private String fullName;

    @Enumerated (EnumType.STRING)
    @Column (nullable = false )
    private UserRole role = UserRole.USER;
    
    @Column (name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Override 
    public Collection<? extends GrantedAuthority> getAuthorities(){
        return List.of(new SimpleGrantedAuthority("ROLE_" + role.name()));
    }

    @Override 
    public String getUsername(){
        return email;
    }

    @Override 
    public String getPassword(){
        return password;
    }

    @Override  
    public boolean isAccountNonExpired(){
        return true;
    }

    @Override 
    public boolean isAccountNonLocked(){
        return true;
    }

    @Override 
    public boolean isCredentialsNonExpired(){
        return true;
    }

    @Override 
    public boolean isEnabled(){
        return  true;
    }

    
}