package com.example.bill

import android.app.Application
import android.os.Bundle
import android.provider.Settings
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.gestures.draggable
import androidx.compose.foundation.gestures.rememberDraggableState
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.consumeAllChanges
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.bill.ui.theme.BillTheme
import kotlin.math.roundToInt

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BillTheme {
                // A surface container using the 'background' color from the theme
                val totalPerPerson = remember()
                {
                    mutableStateOf( 0.0)
                }

                var heightShow = remember()
                {
                    mutableStateOf(120.dp)
                }

                val valueBill = remember()
                {
                    mutableStateOf("")
                }

                val count = remember()
                {
                    mutableStateOf(1)
                }

                var sliderWeight = remember()
                {
                    mutableStateOf(0f)
                }


                var tips = remember()
                {
                    mutableStateOf(0.0)
                }

                val status = remember()
                {
                    mutableStateOf(false)
                }
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier
                        .fillMaxHeight()
                        .fillMaxWidth(),
                    color = MaterialTheme.colors.background
                ) {
                        Column(
                            modifier = Modifier.height(300.dp),
                            verticalArrangement = Arrangement.Top,
                            horizontalAlignment = Alignment.CenterHorizontally,
                        ) {
//                            CreateShowBill(value = totalPerPerson.value.toString())
//                            CreateInputBill()
//                            Divider()
                            Card(modifier = Modifier
                                .width(370.dp)
                                .height(150.dp)
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

                                    val showTotal = String.format("%.2f", totalPerPerson.value.toDouble())
                                    Text(text= "$" + showTotal.toString(),
                                        style= MaterialTheme.typography.h4,
                                        color= MaterialTheme.colors.primaryVariant)
                                }
                            }

                            Card(modifier = Modifier
                                .width(360.dp)
                                .height(heightShow.value)
                                .padding(12.dp),
                                shape = RoundedCornerShape(corner = CornerSize(15.dp)),
                                elevation = 4.dp,
                            )
                            {
                                Column(
                                    modifier = Modifier.padding(3.dp),
                                    horizontalAlignment = Alignment.CenterHorizontally,
                                    verticalArrangement = Arrangement.Top
                                )
                                {

                                    Spacer(Modifier.height(20.0.dp))
                                    TextField(
                                        value = valueBill.value,
                                        onValueChange = {
                                            valueBill.value = it
                                        },
                                        label = {
                                            Text(text= "Enter Total Bill")
                                        },
                                    )

                                    Spacer(modifier = Modifier.height(10.dp))
                                    if(valueBill.value != "")
                                    {
                                        heightShow.value = 500.dp
                                        totalPerPerson.value = (valueBill.value.toDouble() + tips.value) / count.value
                                        Row(horizontalArrangement = Arrangement.SpaceEvenly,
                                            verticalAlignment = Alignment.CenterVertically)
                                        {
                                            Text(text= "Split: ", fontSize= 20.sp)
                                            Spacer(Modifier.width(45.0.dp))
                                            Row(horizontalArrangement = Arrangement.SpaceEvenly,
                                                verticalAlignment = Alignment.CenterVertically)
                                            {
                                                Card(modifier = Modifier
                                                    .padding(3.dp)
                                                    .size(40.dp)
                                                    .clickable {
                                                        if (count.value > 1) {
                                                            count.value -= 1
                                                        }
                                                        else
                                                        {

                                                        }
                                                    },
                                                    shape = CircleShape,
                                                    elevation = 4.dp,
                                                ){
                                                    Box(contentAlignment = Alignment.Center)
                                                    {
                                                        Text(text = "-", fontSize= 30.sp)
                                                    }
                                                }
                                                Spacer(Modifier.width(10.0.dp))
                                                Text(text= count.value.toString(), fontSize= 20.sp)
                                                Spacer(Modifier.width(10.0.dp))
                                                Card(modifier = Modifier
                                                    .padding(3.dp)
                                                    .size(40.dp)
                                                    .clickable {count.value += 1},
                                                    shape = CircleShape,
                                                    elevation = 4.dp,
                                                ){
                                                    Box(contentAlignment = Alignment.Center)
                                                    {
                                                        Text(text = "+", fontSize= 30.sp)
                                                    }
                                                }
                                            }

                                        }
                                        Spacer(modifier = Modifier.height(20.dp))
                                        Row(horizontalArrangement = Arrangement.SpaceEvenly,
                                        )
                                        {
                                            Text(text= "Tips: ", fontSize= 20.sp)
                                            Spacer(Modifier.width(130.0.dp))
                                            tips.value = sliderWeight.value * valueBill.value.toDouble()
                                            val showTips = String.format("%.2f", tips.value)
                                            Text(text= "$" + showTips.toString(), fontSize= 20.sp)
                                        }
                                        Spacer(modifier = Modifier.height(20.dp))

                                        Text(text= (sliderWeight.value * 100).toString() + " %", fontSize= 20.sp)
                                        Spacer(modifier = Modifier.height(20.dp))
                                        Slider(value = sliderWeight.value,
                                            onValueChange = { sliderWeight.value = it },
                                            modifier= Modifier.width(300.dp),
                                            interactionSource = remember { MutableInteractionSource() })

                                    }
                                    else
                                    {
                                        heightShow.value = 120.dp
                                        totalPerPerson.value = 0.0
                                    }

                                }
                            }

                        }
                    }


            }
        }
    }

    //@Preview
//    @Composable
//    fun CreateShowBill(value: String)
//    {
//        Card(modifier = Modifier
//            .width(370.dp)
//            .height(150.dp)
//            .padding(12.dp),
//            shape = RoundedCornerShape(corner = CornerSize(15.dp)),
//            elevation = 4.dp,
//            backgroundColor = Color.Magenta
//        )
//        {
//            Column(
//                modifier = Modifier.padding(3.dp),
//                horizontalAlignment = Alignment.CenterHorizontally,
//                verticalArrangement = Arrangement.Center
//            )
//            {
//                Text(text= "Total Per Person",
//                    style= MaterialTheme.typography.h5,
//                    color= MaterialTheme.colors.primaryVariant)
//
//                Text(text= "$" + value,
//                    style= MaterialTheme.typography.h4,
//                    color= MaterialTheme.colors.primaryVariant)
//            }
//        }
//
//    }
//
//    //@Preview
//    @Composable
//    fun CreateInputBill()
//    {
//        var heightShow = remember()
//        {
//            mutableStateOf(120.dp)
//        }
//        Card(modifier = Modifier
//            .width(360.dp)
//            .height(heightShow.value)
//            .padding(12.dp),
//            shape = RoundedCornerShape(corner = CornerSize(15.dp)),
//            elevation = 4.dp,
//        )
//        {
//            Column(
//                modifier = Modifier.padding(3.dp),
//                horizontalAlignment = Alignment.CenterHorizontally,
//                verticalArrangement = Arrangement.Top
//            )
//            {
//                val valueBill = remember()
//                {
//                    mutableStateOf("")
//                }
//                Spacer(Modifier.height(20.0.dp))
//                TextField(
//                    value = valueBill.value,
//                    onValueChange = {
//                        valueBill.value = it
//                    },
//                    label = {
//                        Text(text= "Enter Total Bill")
//                    },
//                )
//
//                Spacer(modifier = Modifier.height(10.dp))
//                if(valueBill.value != "")
//                {
//                    heightShow.value = 500.dp
//                    Detail(valueInput = valueBill.value)
//                }
//                else
//                {
//                    heightShow.value = 120.dp
//                }
//            }
//        }
//
//    }
//
//    @Composable
//    fun Detail(valueInput: String)
//    {
//        val count = remember()
//        {
//            mutableStateOf(1)
//        }
//        splitPersonGlo = count.value;
//        Row(horizontalArrangement = Arrangement.SpaceEvenly,
//            verticalAlignment = Alignment.CenterVertically)
//        {
//            Text(text= "Split: ", fontSize= 20.sp)
//            Spacer(Modifier.width(45.0.dp))
//            Row(horizontalArrangement = Arrangement.SpaceEvenly,
//                verticalAlignment = Alignment.CenterVertically)
//            {
//                Card(modifier = Modifier
//                    .padding(3.dp)
//                    .size(40.dp)
//                    .clickable {
//                        if (count.value > 1) {
//                            count.value -= 1
//                        }
//                        else
//                        {
//
//                        }
//                    },
//                    shape = CircleShape,
//                    elevation = 4.dp,
//                ){
//                    Box(contentAlignment = Alignment.Center)
//                    {
//                        Text(text = "-", fontSize= 30.sp)
//                    }
//                }
//                Spacer(Modifier.width(10.0.dp))
//                Text(text= count.value.toString(), fontSize= 20.sp)
//                Spacer(Modifier.width(10.0.dp))
//                Card(modifier = Modifier
//                    .padding(3.dp)
//                    .size(40.dp)
//                    .clickable {count.value += 1},
//                    shape = CircleShape,
//                    elevation = 4.dp,
//                ){
//                    Box(contentAlignment = Alignment.Center)
//                    {
//                        Text(text = "+", fontSize= 30.sp)
//                    }
//                }
//            }
//
//        }
//        Spacer(modifier = Modifier.height(20.dp))
//        Row(horizontalArrangement = Arrangement.SpaceEvenly,
//        )
//        {
//            Text(text= "Tips: ", fontSize= 20.sp)
//            Spacer(Modifier.width(130.0.dp))
//            tipsCost = tipsPercent / 100 * (valueInput).toDouble()
//            Text(text= "$" + (tipsPercent).toString(), fontSize= 20.sp)
//        }
//        Spacer(modifier = Modifier.height(20.dp))
//        VerticalProgress()
//    }
//
//
//    @Composable
//    fun VerticalProgress() {
//        var sliderWeight = remember()
//        {
//            mutableStateOf(0f)
//        }
//        Text(text= (sliderWeight.value * 100).toString() + " %", fontSize= 20.sp)
//        Spacer(modifier = Modifier.height(20.dp))
//        Slider(value = sliderWeight.value,
//            onValueChange = { sliderWeight.value = it },
//            modifier= Modifier.width(300.dp),
//            interactionSource = remember { MutableInteractionSource() })
//
//        tipsPercent = sliderWeight.value.toDouble() * 100;
//
//    }
//
//    @Composable
//    fun VerticalProgress_1() {
//        val offsetX = remember()
//        {
//            mutableStateOf(0F)
//        }
//        var percent = offsetX.value.roundToInt() / 8
//        Text(text= (percent).toString() + " %", fontSize= 20.sp)
//        Spacer(modifier = Modifier.height(20.dp))
//        Box(modifier = Modifier.width(250.dp).height(2.dp).background(Color.Magenta).clip(CircleShape)){
//            Box(
//                modifier= Modifier
//                    .offset { IntOffset(offsetX.value.roundToInt(), 0) }
//                    .background(Color.Blue)
//                    .size(30.dp)
//                    .clip(CircleShape)
//                    .draggable(
//                        orientation = Orientation.Horizontal,
//                        state = rememberDraggableState { delta ->
//                            offsetX.value += delta
//                        }
//
//                    )
//            )
//        }
//    }
//
//
//    @Composable
//    fun Greeting(name: String) {
//        Text(text = "Hello $name!")
//    }
//
//    @Preview(showBackground = true)
//    @Composable
//    fun DefaultPreview() {
//        BillTheme {
//            Greeting("Android")
//        }
//    }
}
