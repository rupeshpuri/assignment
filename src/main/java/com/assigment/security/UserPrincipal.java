package com.assigment.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;

import com.assigment.model.User;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class UserPrincipal implements OAuth2User, UserDetails {
    /**
	 * 
	 */
	private static final long serialVersionUID = 7024075340119629007L;
	private Long id;
    private String email;
    private String password;
    private String imageUrl;
    private String name;
    private String subtitle;
    private Collection<? extends GrantedAuthority> authorities;
    private Map<String, Object> attributes;

    public UserPrincipal(Long id, String name, String subtitle,String email, String password, String imageUrl,Collection<? extends GrantedAuthority> authorities) {
        this.id = id;
        this.name= name;
        this.email = email;
        this.password = password;
        this.imageUrl = imageUrl;
        this.authorities = authorities;
        this.subtitle = subtitle;
    }

    public static UserPrincipal create(User user) {
        List<GrantedAuthority> authorities = Collections.
                singletonList(new SimpleGrantedAuthority("ROLE_USER"));

        return new UserPrincipal(
                user.getId(),
                user.getName(),
                user.getSubtitle(),
                user.getEmail(),
                user.getPassword(),
                user.getImageUrl(),
                authorities
        );
    }

    public static UserPrincipal create(User user, Map<String, Object> attributes) {
        UserPrincipal userPrincipal = UserPrincipal.create(user);
        userPrincipal.setAttributes(attributes);
        return userPrincipal;
    }

    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
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
    
    public String getSubtitle() {
		return subtitle;
	}

	public void setSubtitle(String subtitle) {
		this.subtitle = subtitle;
	}

	@Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public Map<String, Object> getAttributes() {
        return attributes;
    }

    public void setAttributes(Map<String, Object> attributes) {
        this.attributes = attributes;
    }

    @Override
    public String getName() {
        return name;
    }

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
    
    
}
