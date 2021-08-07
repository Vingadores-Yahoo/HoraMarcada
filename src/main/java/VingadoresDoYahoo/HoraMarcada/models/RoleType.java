package VingadoresDoYahoo.HoraMarcada.models;

import com.google.common.collect.Sets;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import VingadoresDoYahoo.HoraMarcada.security.ApplicationUserPermission;

import java.util.Set;
import java.util.stream.Collectors;

import static VingadoresDoYahoo.HoraMarcada.security.ApplicationUserPermission.*;

public enum RoleType {
    CONSUMIDOR(Sets.newHashSet(ACESSAR)),
    ADMIN(Sets.newHashSet(ACESSAR)),
    PRESTADORSERVICO(Sets.newHashSet(ACESSAR));


    private final Set<ApplicationUserPermission> permissions;

    RoleType(Set<ApplicationUserPermission> permissions) {
        this.permissions = permissions;
    }

    public Set<ApplicationUserPermission> getPermissions() {
        return permissions;
    }

    public Set<SimpleGrantedAuthority> getGrantedAuthorities() {
        Set<SimpleGrantedAuthority> permissions = getPermissions().stream()
                .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
                .collect(Collectors.toSet());
        permissions.add(new SimpleGrantedAuthority("ROLE_" + this.name()));
        return permissions;
    }

}