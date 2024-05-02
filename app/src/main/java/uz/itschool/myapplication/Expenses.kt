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
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.filled.Refresh
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue

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
    var sortedExpenses by remember { mutableStateOf(expenses.sortedByDescending { it.amount }) }
    var filterType by remember { mutableStateOf(FilterType.NONE) }

    // Apply filtering based on the current filterType
    val filteredExpenses = when (filterType) {
        FilterType.POSITIVE -> sortedExpenses.filter { it.amount >= 0 }
        FilterType.NEGATIVE -> sortedExpenses.filter { it.amount < 0 }
        else -> sortedExpenses
    }

    Surface(
        color = Color.White, // Set the background color to white
        modifier = Modifier
            .padding(vertical = 8.dp)
            .padding(horizontal = 8.dp)
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
                    },
                    actions = {
                        IconButton(onClick = {
                            sortedExpenses = expenses.sortedByDescending { it.amount }
                            filterType = FilterType.POSITIVE
                        }) {
                            Icon(
                                imageVector = Icons.Default.KeyboardArrowUp,
                                contentDescription = "Filter kirim"
                            )
                        }
                        IconButton(onClick = {
                            sortedExpenses = expenses.sortedBy { it.amount }
                            filterType = FilterType.NEGATIVE
                        }) {
                            Icon(
                                imageVector = Icons.Default.KeyboardArrowDown,
                                contentDescription = "Filter chiqim"
                            )
                        }
                        IconButton(onClick = {
                            sortedExpenses = expenses.sortedByDescending { it.amount }
                            filterType = FilterType.NONE
                        }) {
                            Icon(
                                imageVector = Icons.Default.Refresh,
                                contentDescription = "Reset"
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
                items(filteredExpenses) { expense ->
                    ExpenseCard(expense = expense)
                }
            }
        }
    }
}

enum class FilterType {
    NONE,
    POSITIVE,
    NEGATIVE
}




@Composable
fun ExpenseCard(expense: Expense) {
    val borderColor = Color(0xFFbba6fc)
    val amountColor = if (expense.amount >= 0) Color(0xFF4BCA50) else  Color(0xFFF13A2D)
    val amount = if (expense.amount >= 0) {"+${expense.amount}"} else {"${expense.amount}"}
    OutlinedCard(
        modifier = Modifier
            .fillMaxWidth()
            .height(85.dp)
            .padding(vertical = 4.dp),
        border = BorderStroke(1.dp, borderColor),
        ) {
        Column(modifier = Modifier){
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 20.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = expense.name,
                    modifier = Modifier.weight(1f),
                    fontWeight = FontWeight.Normal,
                    color = Color.Black
                )
                Text(
                    text = amount,
                    color = amountColor
                )
            }
        }
        Column(modifier = Modifier,
            horizontalAlignment = Alignment.CenterHorizontally){
            Text(
                text = expense.date,
//                modifier = Modifier.align(Alignment.End),
                color = Color.Gray
            )
        }


    }
}

data class Expense(val name: String, val amount: Float, val date:String)
