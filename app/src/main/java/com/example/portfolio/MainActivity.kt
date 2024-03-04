package com.example.portfolio

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.Divider
import androidx.compose.material3.DividerDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.portfolio.ui.theme.PortfolioTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PortfolioTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                        Portfolio()
                }
            }
        }
    }
}

@Composable
fun Portfolio()
{
    val data = listOf<project>(
        project("Shopping List App", "It remember what you want to buy"),
        project("Wish List App","Collect user wishes "),
        project("Portfolio App","Show user portfolio and projects")
    )
    val buttonClickedState = remember {
        mutableStateOf(false)
    }
    Surface(
        modifier = Modifier.fillMaxSize()
    ){
        Card(
            modifier = Modifier
                .padding(12.dp)
                .fillMaxSize(),
            elevation = CardDefaults.cardElevation(defaultElevation = 10.dp),
            colors = CardDefaults.cardColors(Color.White)
        ){
            Column(
                modifier = Modifier.height(300.dp),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                CreateImageProfile()
                Divider(thickness = 1.dp,color = Color.Black)
                CreateInfo()
                Button(
                    onClick = {
                            buttonClickedState.value = !buttonClickedState.value
                    }) {
                    Text(
                        "Portfolio",
                        style = MaterialTheme.typography.bodyLarge
                    )
                }
            }
            if(buttonClickedState.value)
            {
                ProjectList(data = data)
            }
        }
    }
}

data class project(
    val title:String,
    val desc:String
)

@Composable
fun ProjectView(project: project) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(150.dp)
                .padding(4.dp),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically
        ) {
            CreateImageProfile(modifier = Modifier.size(100.dp))
            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .padding(8.dp),
                verticalArrangement = Arrangement.Center
            ) {
                    Text(text = project.title,
                        modifier = Modifier.padding(bottom = 4.dp),
                        fontSize = MaterialTheme.typography.titleLarge.fontSize,
                        fontWeight = FontWeight.Bold,
                        fontStyle = FontStyle.Normal
                    )
                Text(text = project.desc,
                    modifier = Modifier.padding(bottom = 4.dp),
                    fontSize = MaterialTheme.typography.bodyLarge.fontSize,
                    fontWeight = FontWeight.Bold,
                    fontStyle = FontStyle.Normal
                )
            }

        }
}

@Composable
fun ProjectList(data :List<project>){
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .padding(4.dp),
    ) {
        Surface(
            modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth()
                .padding(3.dp),
            shape = RoundedCornerShape(corner = CornerSize(8.dp)),
            border = BorderStroke(2.dp, Color.LightGray)
        )
        {
            LazyColumn(
                modifier = Modifier.fillMaxWidth()
            ){
                items(data){item ->
                    ProjectView(project = item)
                    Divider(thickness = 2.dp)
                }
            }
        }
    }
}



@Composable
private fun CreateInfo(modifier :Modifier = Modifier) {
    Column(
        modifier
            .padding(10.dp)
            .fillMaxWidth(),

        ) {
        Text(
            "Sujan Mandal",
            color = Color.Blue,
            fontSize = MaterialTheme.typography.titleLarge.fontSize,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = "Android compose Developer",
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.padding(top = 4.dp, end = 4.dp)
        )
        Text(
            text = "sujanrintu@gmail.com",
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.padding(top = 4.dp, end = 4.dp)
        )
    }
}

@Composable
private fun CreateImageProfile(modifier : Modifier = Modifier) {
    Surface(
        modifier
            .size(150.dp)
            .padding(8.dp),
        shape = CircleShape,
        border = BorderStroke(0.5.dp, color = Color.Gray),
        tonalElevation = 5.dp,
    )
    {
        Image(
            painter = painterResource(id = R.drawable.profilepic),
            contentDescription = "profile image",
            contentScale = ContentScale.Crop
        )
    }
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    PortfolioTheme {
        Portfolio()
//        ProjectList(data = listOf<project>(
//            project("Shopping List App", "It remember what you want to buy"),
//            project("Wish List App","Collect user wishes "),
//            project("Portfolio App","Show user portfolio and projects")
//        ))
//        ProjectView(project = project("Project 1","create user portfolio"))
    }
}