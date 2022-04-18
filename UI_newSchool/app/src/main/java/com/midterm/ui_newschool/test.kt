package com.midterm.ui_newschool

//import android.graphics.Color
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.midterm.ui_newschool.ui.theme.UI_newSchoolTheme

class test : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            UI_newSchoolTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Greeting2("Android")
                    MainContent()
                }
            }
        }
    }
}

@Preview
@Composable

fun MainContent(){
    val circleShape = CircleShape

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF0EAD6))
            .padding(16.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentSize(Alignment.Center)
                .align(Alignment.Center)
        ) {
            Box(
                modifier = Modifier
                    .size(275.dp)
                    .clip(circleShape)
                    .background(Color(0xFFE5AA70))
            )
        }
    }
}

@Composable
fun Greeting2(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview2() {
    UI_newSchoolTheme {
        Greeting2("Android")
    }
}


//package com.midterm.ui_newschool
//
//import android.graphics.Color
//import android.os.Bundle
//import androidx.activity.ComponentActivity
//import androidx.activity.compose.setContent
//import androidx.compose.foundation.layout.*
//import androidx.compose.foundation.shape.CircleShape
//import androidx.compose.material.Card
//import androidx.compose.material.MaterialTheme
//import androidx.compose.material.Surface
//import androidx.compose.material.Text
//import androidx.compose.runtime.Composable
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.graphics.Color.Companion.White
//import androidx.compose.ui.text.font.FontWeight
//import androidx.compose.ui.tooling.preview.Preview
//import androidx.compose.ui.unit.dp
//import androidx.compose.ui.unit.sp
//import com.midterm.ui_newschool.ui.theme.UI_newSchoolTheme
//import java.time.format.TextStyle
//
//class MainActivity : ComponentActivity() {
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContent {
//            UI_newSchoolTheme {
//                // A surface container using the 'background' color from the theme
//                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background) {
//                    Column(verticalArrangement = Arrangement.Center,
//                        horizontalAlignment = Alignment.CenterHorizontally)
//                    {
//                        Text(text="0", style= TextStyle(color= android.graphics.Color.WHITE),
//                            fontSize = 35.sp,
//                            fontWeight= FontWeight.ExtraBold)
//                        CreateCircle()
//                    }
//                }
//            }
//        }
//    }
//}
//
//@Preview
//@Composable
//fun CreateCircle()
//{
//    Card(modifier = Modifier
//        .padding(3.dp)
//        .size(105.dp),
//        shape = CircleShape(){
//            Box(contentAligment = Alignment.Center)
//            {
//                Text(text="tap")
//            }
//        }){
//
//    }
//}
//
//@Composable
//fun Greeting(name: String) {
//    Text(text = "Hello $name!")
//}
//
//@Preview(showBackground = true)
//@Composable
//fun DefaultPreview() {
//    UI_newSchoolTheme {
//        Greeting("Android")
//    }
//}