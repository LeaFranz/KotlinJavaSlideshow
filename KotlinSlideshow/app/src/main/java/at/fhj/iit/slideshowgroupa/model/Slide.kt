package at.fhj.iit.slideshowgroupa.model

import java.time.LocalDate

data class Slide(
    val id: Int,
    var title: String = "untitled",
    val imageName: String = "dummy",
    var GPS: String? = "Unknown location",
    val timestamp: LocalDate,
    var description: String = "This is a picture from my holiday.",
    val sound: String = "Unknown sound"
) {

    override fun toString(): String {
        return "Slide number $id: '$title'"
    }
}

