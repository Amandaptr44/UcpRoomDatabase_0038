package com.example.ucp2pam.ui.navigation

interface AlamatNavigasi{
    val route: String
}


object DestinasiHomeDokter : AlamatNavigasi {
    override val route = "homeDokter"
}
object DestinasiHomeJadwal : AlamatNavigasi {
    override val route = "homeJadwal"
}


object DestinasiDetail : AlamatNavigasi{
    override val route = "detail"
    const val jadwalId = "kodenya"
    val routesWithArg = "$route/{$jadwalId}"
}

object DestinasiUpdate : AlamatNavigasi{
    override val route = "update"
    const val jadwalId = "kodenya"
    val routesWithArg = "$route/{$jadwalId}"
}