package com.azeemwaqar.i210679


object ChatUtils {
    data class chattest(
        val name: String,
        val Message: String,

    )

    fun getSampleGigs(): List<chattest> {
        return listOf(
            chattest("Jeremy Clarkson", "Car Enthusiast"),
            chattest(
                "Martina Watson",
                "Lead - Technology Officer"
            ),
            chattest(
                "Emma Martin",
                "Lead - Corporate Manager"
            ),
            chattest("Jenny Martinez", "Software Engineer"),
            chattest("John Cooper", "UX Designer @ Google")
        )
    }
}
