package ru.rubberteam.inventa.domain.user

/**
 * супервайзер, мол, кладовщик, координатор
 */
enum class Role(val roleName: String) {
    USER("user"),
    ADMIN("admin");
}