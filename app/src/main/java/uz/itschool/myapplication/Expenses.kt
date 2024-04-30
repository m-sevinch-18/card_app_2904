package uz.itschool.myapplication

import android.annotation.SuppressLint
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.runtime.Composable

import androidx.compose.foundation.layout.*

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar

import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
 @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Expenses(expenses: List<Expense>) {
     Surface(
         color = Color.White, // Set the background color to white
         modifier = Modifier
             .padding(vertical = 7.dp)
             .padding(horizontal = 10.dp)
     ) {
         Scaffold(
             topBar = {
                 TopAppBar(
                     title = {
                         Text(
                             text = "Expenses",
                             modifier = Modifier.padding(start = 15.dp)
                         )
                     },
                     navigationIcon = {
                         IconButton(onClick = { /* Handle navigation back */ }) {
                             Icon(
                                 imageVector = Icons.Default.ArrowBack,
                                 contentDescription = "Back"
                             )
                         }
                     }
                 )
             }
         ) {
             LazyColumn(
                 modifier = Modifier
                     .fillMaxSize()
                     .padding(top = 60.dp),
                 contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp)
             ) {
                 items(expenses) { expense ->
                     ExpenseCard(expense = expense)
                 }
             }
         }
     }

}



@Composable
fun ExpenseCard(expense: Expense) {
    val borderColor = Color(expense.name.hashCode())
    OutlinedCard(
        modifier = Modifier
            .fillMaxWidth()
            .height(85.dp)
            .padding(vertical = 4.dp),
        border = BorderStroke(2.dp, borderColor),
        ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 20.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = expense.name,
                modifier = Modifier.weight(1f),
                fontWeight = FontWeight.Bold
            )
            Text(
                text = "-${expense.amount} so'm",
                color = Color.Black
            )
        }
    }
}

data class Expense(val name: String, val amount: Float)
