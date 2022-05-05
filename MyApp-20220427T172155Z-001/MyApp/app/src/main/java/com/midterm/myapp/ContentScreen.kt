package com.midterm.myapp

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.w3c.dom.Text

@Composable
fun HomeScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(id = R.color.colorPrimaryDark))
            .wrapContentSize(Alignment.Center)
    ){
        Text(
            text="Home view",
            fontWeight = FontWeight.Bold,
            color = Color.White,
            modifier = Modifier.align(Alignment.CenterHorizontally),
            textAlign = TextAlign.Center,
            fontSize = 25.sp

        )
    }
}
@Composable
fun ProfileScreen() {
    val status= remember {
        mutableStateOf(false)
    }
    Surface(modifier = Modifier
        .fillMaxSize(),
        color = MaterialTheme.colors.background)

    {
    Card (
        modifier = Modifier
            .width(200.dp)
            .height(390.dp)
            .padding(12.dp)
            ,
        shape = RoundedCornerShape(corner = CornerSize(15.dp)),
        elevation=4.dp

    ){
        Column(
            modifier = Modifier.height(300.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
        CreateImageProfile()
        CreateInfo()
        Button(onClick = {
            status.value=!status.value
        }){
            Text(text = "Portfolio",style = MaterialTheme.typography.button)
        }

        Divider()
        if(status.value) {
            Porfolio(data = listOf("Project 1", "Project 2", "Project 3"))

        }
        }}
    }
}
@Composable
fun BookScreen() {
    val viewStates = remember{ mutableStateOf(States())}
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(id = R.color.colorPrimaryDark))
            .padding(10.dp)
    ){
        CreateShowBill(viewStates.value)
        CreateInputCard(viewStates.value){
                newValue -> viewStates.value = newValue
            val tip = viewStates.value.tip
            val total1= viewStates.value.text.toDouble()
            val split = viewStates.value.split
            viewStates.value.total = (tip+total1)/split
        }
    }
}
@Composable
fun MusicScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(id = R.color.colorPrimaryDark))
            .wrapContentSize(Alignment.Center)
    ){
        Text(
            text="Music view",
            fontWeight = FontWeight.Bold,
            color = Color.White,
            modifier = Modifier.align(Alignment.CenterHorizontally),
            textAlign = TextAlign.Center,
            fontSize = 25.sp

        )
    }
}
@Composable
fun MovieScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(id = R.color.colorPrimaryDark))
            .wrapContentSize(Alignment.Center)

    ){
        Text(
            text="Movie view",
            fontWeight = FontWeight.Bold,
            color = Color.White,
            modifier = Modifier.align(Alignment.CenterHorizontally),
            textAlign = TextAlign.Center,
            fontSize = 25.sp

        )
    }
}
fun Double.format(digits: Int) = "%.${digits}f".format(this)
class States(){
    var text: String by mutableStateOf("")
    var split: Int  by mutableStateOf(1)
    var tip: Double by mutableStateOf(0.0)
    var total: Double by mutableStateOf(0.0)

}
@Composable
fun CreateShowBill(Status: States)
{
    Card(modifier = Modifier
        .fillMaxWidth()
        .padding(12.dp),
        shape = RoundedCornerShape(corner = CornerSize(15.dp)),
        elevation = 4.dp,
        backgroundColor = Color.Magenta
    )
    {
        Column(
            modifier = Modifier.padding(3.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        )
        {
            Text(text= "Total Per Person",
                style= MaterialTheme.typography.h5,
                color= MaterialTheme.colors.primaryVariant)

            Text(text= "$${if(Status.total.toFloat().isFinite()) Status.total.format(2) else 0}",
                style= MaterialTheme.typography.h4,
                color= MaterialTheme.colors.primaryVariant)
        }
    }

}
@Composable
fun CreateInputCard(states:States= States(), onChange: (States) -> Unit){
    var sliderPosition by remember{ mutableStateOf(0f) }
    var isOpen by remember { mutableStateOf(false) }
    Card(modifier = Modifier.padding(5.dp),
        shape = RoundedCornerShape(corner = CornerSize(15.dp)),
        elevation = 4.dp
    ) {
        Column(
        ) {
            OutlinedTextField(

                value = "${states.text}",
                onValueChange = {
                    states.text = it
                    isOpen = it.trim().isNotEmpty()

                    states.tip = if (it.trim().isNotEmpty()) (states.text.toDouble()*(sliderPosition)/100f) else 0.0
                    onChange(states)
                },
                label = { Text("Enter Bill") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                leadingIcon = { Icon(painter = painterResource(id = R.drawable.ic_baseline_attach_money_24), contentDescription = "") }
            )
            if(isOpen){
                Row(modifier = Modifier.padding(5.dp)) {
                    Text(text = "Split",
                        style = MaterialTheme.typography.body1,
                        modifier = Modifier
                            .align(Alignment.CenterVertically)
                    )

                    Box(modifier = Modifier.fillMaxWidth()){
                        Row(modifier = Modifier
                            .align(Alignment.TopEnd)
                            .padding(5.dp)
                        ) {
                            OutlinedButton(onClick = { states.split += 1
                                onChange(states)
                            },
                                modifier= Modifier.size(50.dp),
                                border = BorderStroke(1.dp, Color.Black),
                                shape = CircleShape,
                                contentPadding = PaddingValues(0.dp),
                                colors = ButtonDefaults.outlinedButtonColors(contentColor = Color.Black),
                                elevation = ButtonDefaults.elevation(
                                    defaultElevation = 6.dp,
                                    pressedElevation = 8.dp,
                                    disabledElevation = 0.dp
                                )
                            ) {
                                Icon(Icons.Default.Add, "add")
                            }
                            Text(text = "${states.split}",
                                modifier = Modifier
                                    .padding(5.dp)
                                    .align(Alignment.CenterVertically),
                                style = MaterialTheme.typography.body1
                            )

                            OutlinedButton(onClick = { if(states.split > 1) states.split -= 1
                                onChange(states)
                            },
                                modifier= Modifier.size(50.dp),
                                border = BorderStroke(1.dp, Color.Black),
                                shape = CircleShape,
                                contentPadding = PaddingValues(0.dp),
                                colors = ButtonDefaults.outlinedButtonColors(contentColor = Color.Black),
                                elevation = ButtonDefaults.elevation(
                                    defaultElevation = 6.dp,
                                    pressedElevation = 8.dp,
                                    disabledElevation = 0.dp
                                )
                            ) {
                                Image(painter = painterResource(id = R.drawable.ic_baseline_remove_24), contentDescription = "remove")
                            }
                        }
                    }

                }

                Row(modifier = Modifier.padding(5.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(text = "Tip",
                        style = MaterialTheme.typography.body1,
                        modifier = Modifier
                            .align(Alignment.CenterVertically)
                    )
                    Text(text = "$${if(states.tip.toFloat().isFinite()) states.tip.format(2) else 0}",
                        modifier = Modifier
                            .padding(10.dp)
                            .fillMaxWidth(),
                        textAlign = TextAlign.End,
                        style = MaterialTheme.typography.body1
                    )
                }
                Text(modifier = Modifier
                    .fillMaxWidth()
                    .padding(5.dp),
                    text = "${sliderPosition.toInt()}%",
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.subtitle1)

                Slider(value = sliderPosition,
                    onValueChange = {
                        sliderPosition = it
                        states.tip = states.text.toDouble()*(sliderPosition)/100f
                        onChange(states)
                    },
                    steps = 19,
                    valueRange = 0f..100f,
                    colors = SliderDefaults.colors(
                        thumbColor = MaterialTheme.colors.secondary,
                        activeTrackColor = MaterialTheme.colors.secondary
                    ),
                    modifier = Modifier.padding(10.dp,2.dp)
                )
            }
        }
    }
}

@Composable
fun CreateInfo()
{
    Column(modifier = Modifier.padding(3.dp))
    {
        Text(text= "Ly Thanh Hai",
            style= MaterialTheme.typography.h4,
            color= MaterialTheme.colors.primaryVariant)

        Text(text= "Android Programmer",
            modifier = Modifier.padding(3.dp)
        )

        Text(text= "@lythanhhai",
            style= MaterialTheme.typography.subtitle1,
            modifier = Modifier.padding(3.dp),
            //color= MaterialTheme.colors.primaryVariant)
        )

    }
}

@Composable
fun CreateImageProfile()
{
    Surface(
        modifier = Modifier
            .size(150.dp)
            .padding(5.dp),
        shape = CircleShape,
        border = BorderStroke(0.5.dp,color = androidx.compose.ui.graphics.Color.LightGray),
        elevation = 4.dp,
        color= MaterialTheme.colors.onSurface.copy(0.5f)



    ) {
        Image(painter = painterResource(id = R.drawable.male_man_people_person_avatar_white_tone_icon_159363),
            contentDescription = "profile image",
            modifier = Modifier.size(135.dp),
            contentScale = ContentScale.Crop
        )


    }
}
@Composable
fun CreateImageProfile1()
{
    Surface(
        modifier = Modifier
            .size(150.dp)
            .padding(5.dp),
        shape = CircleShape,
        border = BorderStroke(0.5.dp,color = androidx.compose.ui.graphics.Color.LightGray),
        elevation = 4.dp,
        color= MaterialTheme.colors.onSurface.copy(0.5f)



    ) {
        Image(painter = painterResource(id = R.drawable.male_man_people_person_avatar_white_tone_icon_159363),
            contentDescription = "profile image",
            modifier = Modifier.size(135.dp),
            contentScale = ContentScale.Crop
        )


    }
}


@Composable
fun Porfolio(data:List<String>)
{
    LazyColumn{
        items(data)
        {
                item ->
            Card(
                modifier = Modifier
                    .padding(13.dp)
                    .fillMaxWidth(),
                shape = RectangleShape,
                elevation = 4.dp

            ) {
                Row {
                    CreateImageProfile1()
                    Column(
                        modifier = Modifier
                            .padding(8.dp)
                            .background(MaterialTheme.colors.surface)
                            .align(alignment = Alignment.CenterVertically)

                    ) {
                        Text(text = item,fontWeight = FontWeight.Bold)
                        Text(text = "A great project",style=MaterialTheme.typography.body1)


                    }
                }
            }
        }
    }
}
