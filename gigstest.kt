package com.azeemwaqar.i210679

object GigUtils {
    data class gigstest(
        val name: String,
        val description: String,
        val availability: String,
        val price: String,
        val isFavorite: Boolean
    )

    fun getSampleGigs(): List<gigstest> {
        return listOf(
            gigstest("Jeremy Clarkson", "Car Enthusiast", "Available", "$500/Session", false),
            gigstest(
                "Martina Watson",
                "Lead - Technology Officer",
                "Not Available",
                "$500/Session",
                false
            ),
            gigstest(
                "Emma Martin",
                "Lead - Corporate Manager",
                "Not Available",
                "$500/Session",
                false
            ),
            gigstest("Jenny Martinez", "Software Engineer", "Not Available", "$500/Session", false),
            gigstest("John Cooper", "UX Designer @ Google", "Available", "$1500/Session", true)
        )
    }
}
