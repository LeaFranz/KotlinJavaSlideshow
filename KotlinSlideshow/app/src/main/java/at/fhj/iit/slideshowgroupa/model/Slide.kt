package at.fhj.iit.slideshowgroupa.model

import java.time.LocalDate

data class Slide(val id:Int,
                 var title:String = "untitled",
                 val imageName:String = "dummy",
                 var GPS:String?="Unknown location",
                 val timestamp:LocalDate,
                 var description:String = "This is a picture from my holiday.",
                 val sound:String = "Unknown sound"){


    override fun toString():String{
        return "Slide number $id: '$title'"

    }

    // by lazy = called "later" on demand and only ONCE
    // to be continued
//    val base64ImageString:String by lazy {
//        loadTheImageAsBase64()
//    }
//
//    val soundFile:String by lazy {
//        loadingSoundFromFileSystem()
//    }


//    private fun loadTheImageAsBase64():String{
//        println("Warning - expensive code... loading from the net...")
//        return "not implemented yet"
//    }
//
//    private fun loadingSoundFromFileSystem():String{
//        println("loading from the filesystem...")
//        return "not implemented yet"
//    }
}

