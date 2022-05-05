package com.midterm.myapp

import android.icu.text.CaseMap
import android.os.Bundle
import android.service.quicksettings.Tile
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.material.SnackbarDefaults.backgroundColor
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.midterm.myapp.ui.theme.MyAppTheme
import androidx.compose.material.TopAppBar as TopAppBar

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyAppTheme {
                val navController= rememberNavController()
                // A surface container using the 'background' color from the theme
                Scaffold(
                    topBar = { TopBar()},
                    bottomBar = { BottoMNavigation(navController = navController)}
                
                ) {
                   Navigation(navController = navController)
                }
            }
        }
    }
}
@Composable
fun Navigation(navController:NavHostController)
{
    NavHost(navController = navController, startDestination = Navigationitem.Home.route )
    {
        composable(Navigationitem.Home.route)
        {
            HomeScreen()
        }
        composable(Navigationitem.Music.route)
        {
            MusicScreen()
        }
        composable(Navigationitem.Movies.route)
        {
            MovieScreen()
        }
        composable(Navigationitem.Book.route)
        {
            BookScreen()
        }
        composable(Navigationitem.Profile.route)
        {
            ProfileScreen()
        }
        
    }
}
@Composable
fun TopBar()
{

    TopAppBar(
        contentColor = Color.White,
        backgroundColor = colorResource(id = R.color.colorPrimary),


    ) {
  Text(text = "Navigation");
    }
}
@Composable
fun BottoMNavigation( navController: NavHostController)
{
    var items= listOf(
        Navigationitem.Home,
        Navigationitem.Music,
        Navigationitem.Movies,
        Navigationitem.Book,
        Navigationitem.Profile,
    )
androidx.compose.material.BottomNavigation(
    backgroundColor = colorResource(id = androidx.core.R.color.androidx_core_secondary_text_default_material_light) ,
    contentColor= Color.White
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute= navBackStackEntry?.destination?.route
     
    items.forEach { 
        item->
        BottomNavigationItem(selected = currentRoute==item.route, onClick = {
                                                                            navController.navigate(item.route) 
                                                                            {
                                                                                navController.graph.startDestinationRoute?.let { 
                                                                                    route->popUpTo(route )
                                                                                {
                                                                                        saveState=true
                                                                                }
                                                                                }
                                                                                launchSingleTop=true
                                                                                restoreState=true
                                                                            }},
            icon= {Icon(painter = painterResource(id = item.icon), contentDescription =item.title )},
            label = { Text(text = item.title)},
            selectedContentColor = Color.White,
            unselectedContentColor = Color.White.copy(0.4f),
            alwaysShowLabel = true
        
        ) 
    }
        
    }
}
@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MyAppTheme {
        Greeting("Android")
    }
}