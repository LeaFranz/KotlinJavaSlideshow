package at.fhj.iit.slideshowgroupa.service

import at.fhj.iit.slideshowgroupa.model.Slide

// object = singleton class
object Slideshow {
    private var slides = mutableListOf<Slide>()
    var sortDirection = "asc"

    fun addSlide(aNewSlide:Slide){
        slides.add(aNewSlide)
    }

    override fun toString():String{
        val sortedSlides = slides.sortedBy { it.id }
        return sortedSlides.joinToString(" and ", "Slides: ")
    }

   fun shuffle(){
        slides.shuffle()
    }

    fun next(counter:Int):Slide{
        return slides[counter]
    }

    fun getTotalSlides():Int{
        return slides.size
    }


    fun sort(){
        if(sortDirection == "desc"){
            slides.reverse()
            sortDirection = "asc"
        } else if (sortDirection === "asc") {
            slides.sortWith(compareBy({it.timestamp}, {it.title}, {it.id}))
            sortDirection="desc"
        }
    }


}