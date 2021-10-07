package ru.rubberteam.inventa.domain.user

import lombok.AllArgsConstructor

@AllArgsConstructor
class User {

    var username: String? = null
    var name: String? = null
    var hashedPassword: String? = null
    var roles: Set<Role>? = null
    var profilePictureUrl: String? = null

}