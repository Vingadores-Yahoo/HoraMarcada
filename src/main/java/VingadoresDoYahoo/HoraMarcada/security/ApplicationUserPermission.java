package VingadoresDoYahoo.HoraMarcada.security;

public enum ApplicationUserPermission {
    ACESSAR("acessar");

    private final String permission;

    ApplicationUserPermission(String permission) {
        this.permission = permission;
    }

    public String getPermission() {
        return permission;
    }
}