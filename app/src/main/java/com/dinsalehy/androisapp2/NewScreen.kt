package com.dinsalehy.androisapp2

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box

import androidx.compose.foundation.layout.Column

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height

import androidx.compose.foundation.layout.padding

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.material3.Text

import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

import androidx.compose.ui.Modifier

import androidx.compose.ui.platform.LocalContext

import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.lazy.items
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource

// NewScreen Composable
@Composable
fun NewScreen() {
    val coffeeList = remember { mutableStateListOf<CoffeeCategory>() }
    val affogatoImage = painterResource(id = R.drawable.americano)

    // Firebase reference (do this OUTSIDE composable, so no recomposition issues)
    val context = LocalContext.current
    LaunchedEffect(true) {
        val database = FirebaseDatabase.getInstance()
        val categoryRef = database.getReference("Category")

        categoryRef.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                coffeeList.clear()
                for (dataSnap in snapshot.children) {
                    val item = dataSnap.getValue(CoffeeCategory::class.java)
                    item?.let { coffeeList.add(it) }
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Log.e("Firebase", "Error fetching data", error.toException())
            }
        })
    }

    // ONLY CHANGE: Added Box with background color wrapping your existing Column
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF0F0F0)) // Darker white
    ) {
        Column(
            modifier = Modifier.fillMaxSize() // Fixed: Added comma separator
        ) {
            Image(
                painter = affogatoImage,
                contentDescription = "Affogato coffee",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(240.dp) // Fixed: Added closing parenthesis
            )

            Text(
                text = "Coffee Categories", // Fixed: Added parameter name 'text ='
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(16.dp) // Fixed: Added closing parenthesis
            )

            LazyColumn {
                items(coffeeList) { item ->
                    Text(
                        text = item.title, // Fixed: Added parameter name
                        fontSize = 18.sp,
                        modifier = Modifier.padding(12.dp) // Fixed: Added closing parenthesis
                    )
                }
            }
        }
    }
}





/*
fun NewScreen() {
    val selectedItems = remember { mutableStateListOf<CoffeeItem>() }

    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        // Top image
        Image(
            painter = painterResource(id = R.drawable.americano),
            contentDescription = "Intro Image",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .height(190.dp)
        )

        // Coffee grid
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xFFB0B0B0))
                .padding(6.dp)
                .weight(1f) // This ensures the grid takes up remaining space
        ) {
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                contentPadding = PaddingValues(8.dp),
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp),
                modifier = Modifier.fillMaxSize()
            ) {
                items(coffeeItems) { item ->
                    CoffeeCard(item) { selectedItem ->
                        selectedItems.add(selectedItem)
                    }
                }
            }
        }

     // Add selected items' details
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            selectedItems.forEach { item ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp)
                ) {
                    Text(
                        text = item.name,
                        modifier = Modifier.weight(1f),
                        fontSize = 16.sp,
                        color = Color.Black
                    )

                    Text(
                        text = item.price,
                        modifier = Modifier.align(Alignment.CenterVertically),
                        fontSize = 16.sp,
                        color = Color(0xFF795548)
                    )
                }
            }

            // Checkout button at the bottom
            Button(
                onClick = { /* Handle checkout here */ },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp)
                    .height(50.dp)
            ) {
                Text(text = "Checkout", fontSize = 18.sp)
            }
        }
    }
}

// CoffeeCard Composable
@Composable
fun CoffeeCard(item: CoffeeItem, onAddClicked: (CoffeeItem) -> Unit) {
    Column(
        modifier = Modifier
            .background(Color.White)
            .padding(10.dp)
            .fillMaxWidth()
    ) {
        Image(
            painter = painterResource(id = item.imageRes),
            contentDescription = item.name,
            modifier = Modifier
                .height(120.dp)
                .fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = item.name, fontSize = 18.sp, color = Color.Black)
        Text(text = item.description, fontSize = 14.sp, color = Color.DarkGray)
        Text(text = item.price, fontSize = 16.sp, color = Color(0xFF795548))

        // 👇 Circular Add Button with Image
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp),
            horizontalArrangement = Arrangement.End
        ) {
            Image(
                painter = painterResource(id = R.drawable.plus1), // Replace with your image
                contentDescription = "Add",
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape)
                    .background(Color(0xFFD7E8FF))
                    .clickable {
                        onAddClicked(item)
                    }
            )
        }

    }
}
*/

@Preview(showBackground = true)
@Composable
fun NewScreenPreview() {
    NewScreen()
}