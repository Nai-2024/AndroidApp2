package com.dinsalehy.androisapp2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.sp
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.dinsalehy.androisapp2.ui.theme.AndroisApp2Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()

            NavHost(navController = navController, startDestination = "coffee") {
                composable("coffee") {
                    CoffeeScreen(navController = navController)
                }
                composable("newScreen") {
                    NewScreen()
                }
           /* AndroisApp2Theme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    CoffeeScreen(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }*/
        }
    }
}

@Composable
fun CoffeeScreen(navController: NavHostController, modifier: Modifier = Modifier) {
//fun CoffeeScreen(modifier: Modifier = Modifier) {
    val coffeeImage = painterResource(id = R.drawable.intro_pic)

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(Color(0xFF4B3621)) // Coffee brown background
    ) {
        Image(
            painter = coffeeImage,
            contentDescription = "A cup of coffee",
            contentScale = ContentScale.FillBounds,
            modifier = Modifier
                .fillMaxWidth()
                .height(730.dp)
        )

        Text(
            text = "Welcome to NaiCafe",
            color = Color(0xFFDDDDDD),
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .align(Alignment.TopCenter)
                .padding(top = 16.dp)
        )

        Column(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 69.dp), // create space above the button
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Freshly brewed just for you!",
                color = Color(0xFFDDDDDD),
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
               // textAlign = TextAlign.Center,
                modifier = Modifier
                   // .padding(top = 23.dp)
            )
        }

        // Correct Button Placement
        Button(
            onClick = { navController.navigate("newScreen") },
            shape = RectangleShape,
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .offset(y = 4.dp)
                .padding(end = 0.dp)
        ) {
            Text(text = "Start to Order")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    val navController = rememberNavController()
    AndroisApp2Theme {
        CoffeeScreen(navController = navController)
    }
}}
