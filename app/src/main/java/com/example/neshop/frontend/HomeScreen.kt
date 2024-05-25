package com.example.neshop.frontend

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.neshop.R
import com.example.neshop.ui.theme.poppins

@Composable
fun HomeScreen(navController: NavController) {
    var searchBar by remember {
        mutableStateOf("")
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        Text(text = "Neshop", fontFamily = poppins, fontWeight = FontWeight.SemiBold, fontStyle = FontStyle.Italic, fontSize = 20.sp,modifier = Modifier.padding(start = 10.dp, top = 15.dp))
        Row(modifier = Modifier.padding(top = 8.dp, start = 10.dp)) {
            OutlinedTextField(
                value = searchBar, onValueChange = {
                    searchBar = it
                },
                placeholder = { Text(text = "Search", fontFamily = poppins, fontSize = 13.sp) },
                trailingIcon = {
                    IconButton(onClick = {}) {
                        Icon(
                            painter = painterResource(id = R.drawable.search),
                            contentDescription = "SearchIcon",
                            modifier = Modifier.size(20.dp)
                        )
                    }
                }, textStyle = TextStyle(fontFamily = poppins, fontSize = 13.sp),
                modifier = Modifier
                    .background(Color.White)
                    .width(200.dp)
                    .height(50.dp)
            )
            IconButton(onClick = {}) {
                Icon(
                    painter = painterResource(id = R.drawable.mail),
                    contentDescription = "MailIcon",
                    modifier = Modifier.size(20.dp)
                )
            }
            IconButton(onClick = {}) {
                Icon(
                    painter = painterResource(id = R.drawable.notif),
                    contentDescription = "MailIcon",
                    modifier = Modifier.size(20.dp)
                )
            }
            IconButton(onClick = {}) {
                Icon(
                    painter = painterResource(id = R.drawable.cart),
                    contentDescription = "MailIcon",
                    modifier = Modifier.size(20.dp)
                )
            }
        }
        Row(
            modifier = Modifier.padding(start = 3.dp, top = 25.dp, end = 3.dp)
        ) {
            ProductRow(
                product1 = Product(
                    name = "Coca - Cola",
                    description = "Minuman Karbonasi untuk penyegar.",
                    price = "Rp.7000",
                    imageResId = R.drawable.coke
                ),
                product2 = Product(
                    name = "Jaket Barudak Bdg",
                    description = "Auto Well Guyzz, tirizz bgt kek dilan.",
                    price = "Rp.154,000",
                    imageResId = R.drawable.jacket2
                )
            )
        }
    }
}

data class Product(val name: String, val description: String, val price: String, val imageResId: Int)

@Composable
fun ProductRow(product1: Product, product2: Product) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        ProductCard(
            product = product1,
            modifier = Modifier
                .weight(1f)
                .padding(end = 4.dp)
        )
        ProductCard(
            product = product2,
            modifier = Modifier
                .weight(1f)
                .padding(start = 4.dp)
        )
    }
}

@Composable
fun ProductCard(product: Product, modifier: Modifier = Modifier) {
    Card(
        shape = RoundedCornerShape(8.dp),
        modifier = modifier,
        colors = CardDefaults.cardColors(containerColor = colorResource(id = R.color.white)),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Image(
                painter = painterResource(id = product.imageResId),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(180.dp)
            )
            Spacer(modifier = Modifier.height(8.dp))
            Column(modifier = Modifier.padding(16.dp)) {
                Text(text = product.name, style = MaterialTheme.typography.labelLarge, fontWeight = FontWeight.Bold)
                Spacer(modifier = Modifier.height(4.dp))
                Text(text = product.description, style = MaterialTheme.typography.bodyMedium)
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = product.price, style = MaterialTheme.typography.labelLarge)
                Spacer(modifier = Modifier.height(8.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {

                }
            }
        }
    }
}

