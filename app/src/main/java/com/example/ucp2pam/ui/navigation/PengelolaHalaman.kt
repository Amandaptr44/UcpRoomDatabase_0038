package com.example.ucp2pam.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.ucp2pam.ui.view.DestinasiHome
import com.example.ucp2pam.ui.view.DestinasiInsertDokter
import com.example.ucp2pam.ui.view.DestinasiInsertJadwal
import com.example.ucp2pam.ui.view.DetailJadwalView
import com.example.ucp2pam.ui.view.HomeDokterView
import com.example.ucp2pam.ui.view.HomeJadwalView
import com.example.ucp2pam.ui.view.HomeUtamaview
import com.example.ucp2pam.ui.view.InsertBodyJadwal
import com.example.ucp2pam.ui.view.InsertDokterView
import com.example.ucp2pam.ui.view.InsertJadwalView
import com.example.ucp2pam.ui.view.UpdateJadwalView
import com.example.ucp2pam.ui.viewmodel.HomeJadwalViewModel

@Composable
fun PengelolaHalaman(
    navController: NavHostController = rememberNavController(),
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = DestinasiHome.route
    ) {
        composable(
                route = DestinasiHome.route
        ) {
            HomeUtamaview(
                onDokter = {
                        navController.navigate(DestinasiHomeDokter.route)
                    },
                onJadwal = {
                        navController.navigate(DestinasiHomeJadwal.route)
                    },
                )
            }

        composable(
            route = DestinasiHomeDokter.route
        ) {
            HomeDokterView(
                onAddDokter = {
                    navController.navigate(DestinasiInsertDokter.route)
                },
                onBack = {
                    navController.popBackStack()
                },
                modifier = modifier
            )
        }

        composable(
                route = DestinasiInsertDokter.route
            ) {
                InsertDokterView(
                   onBack = {
                        navController.popBackStack()
                    },
                    onNavigate = {
                        navController.popBackStack()
                    },
                    modifier = modifier
                )
        }

        composable(
            route = DestinasiHomeJadwal.route
        ) {
            HomeJadwalView(
                onDetailClick = { jadwalId ->
                    navController.navigate("${DestinasiDetail.route}/$jadwalId")
                    println(
                        "PengelolaHalaman: jadwalId = $jadwalId"
                    )
                },
                onAddjwl = {
                    navController.navigate(DestinasiInsertJadwal.route)
                },
                onBack = {
                        navController.popBackStack()
                    },
                modifier = modifier
            )
        }

        composable(
            route = DestinasiInsertJadwal.route
        ) {
            InsertJadwalView(
                onBack = {
                    navController.popBackStack()
                },
                onNavigate = {
                    navController.popBackStack()
                },
                modifier = modifier
            )
        }

        composable(
            DestinasiDetail.routesWithArg,
            arguments = listOf(
                navArgument(DestinasiDetail.jadwalId) {
                    type = NavType.StringType
                }
            )
        ) {
            val jadwalId = it.arguments?.getString(DestinasiDetail.jadwalId)

            jadwalId?.let { jadwalId ->
                DetailJadwalView(
                    onBack = {
                        navController.popBackStack()
                    },
                    onEditClick = {
                        navController.navigate("${DestinasiUpdate.route}/$it")
                    },
                    modifier = modifier,
                    onDeleteClick = {
                        navController.popBackStack()
                    }
                )
            }

        }
        composable(
            DestinasiUpdate.routesWithArg,
            arguments = listOf(
                navArgument(DestinasiUpdate.jadwalId) {
                    type = NavType.StringType
                }
            )
        ) {
            UpdateJadwalView(
                onBack = {
                    navController.popBackStack()
                },
                onNavigate = {
                    navController.popBackStack()
                },
                modifier = modifier
            )
        }
    }
}


