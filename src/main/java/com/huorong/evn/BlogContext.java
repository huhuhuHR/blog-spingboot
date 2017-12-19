package com.huorong.evn;

public class BlogContext {
    private static ThreadLocal<String> permissionLocal = new InheritableThreadLocal();

    public static String getPermission() {
        return permissionLocal.get();
    }

    public static void setPermission(String permission) {
        permissionLocal.set(permission);
    }

    public static void clear() {
        permissionLocal.remove();
    }
}
