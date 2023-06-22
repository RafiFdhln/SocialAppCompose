package com.example.socialcompose.ui.about

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.socialcompose.R

@Composable
fun About(
    modifier: Modifier = Modifier,
) {
    Column(modifier = modifier
        .fillMaxSize()
        .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally) {
        Image(
            painter = painterResource(R.drawable.img),
            contentDescription = "",
            modifier = Modifier
                .size(200.dp)
                .clip(CircleShape)
        )
        Spacer(modifier = Modifier.padding(14.dp))
        Text(
            text = "Nama:",
            fontSize = 16.sp,
            fontFamily = FontFamily.Default,
            fontWeight = FontWeight.ExtraBold,
        )
        Card(
            modifier = modifier
            .background(Color.LightGray,)
            .padding(5.dp),
            shape = RoundedCornerShape(5.dp),) {
            Spacer(modifier = Modifier.padding(8.dp))
            Text(
                text = "Rafi Ahmad Fadhlan",
                style = MaterialTheme.typography.h2,
                fontSize = 20.sp
            )
        }

        Spacer(modifier = Modifier.padding(8.dp))
        Text(
            text = "Email:",
            fontSize = 16.sp,
            fontFamily = FontFamily.Default,
            fontWeight = FontWeight.ExtraBold,
        )
        Card(
            modifier = modifier
                .background(Color.LightGray,)
                .padding(5.dp),
        ){
            Spacer(modifier = Modifier.padding(8.dp))
            Text(
                text = "rafifadhlan39@gmail.com",
                style = MaterialTheme.typography.h3,
                fontSize = 18.sp
            )
        }
    }
}