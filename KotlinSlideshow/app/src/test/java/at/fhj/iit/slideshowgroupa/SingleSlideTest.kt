package at.fhj.iit.slideshowgroupa

import org.junit.Test
import at.fhj.iit.slideshowgroupa.model.Slide
import org.junit.Assert.*
import java.time.LocalDate

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class SingleSlideTest {
    @Test
    fun slideCreation() {
        val evening = Slide(3,"Evening Mood", "", "", LocalDate.parse("2019-07-25"), "")
        assertEquals(evening.id, 3)
        assertEquals(evening.toString(), "Slide number 3: 'Evening Mood'")
    }
}
