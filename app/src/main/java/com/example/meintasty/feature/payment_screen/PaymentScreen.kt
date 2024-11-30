package com.example.meintasty.feature.payment_screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.meintasty.R
import com.example.meintasty.uicomponent.BackIcon
import com.example.meintasty.uicomponent.HeaderComponent
import kotlinx.coroutines.flow.MutableStateFlow

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PaymentScreen(
    navController: NavController,
    modifier: Modifier = Modifier
) {

    var orderTip by remember {
        mutableStateOf("")
    }
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        BackIcon { navController.navigateUp() }
                        HeaderComponent(text = stringResource(id = R.string.payment))
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = colorResource(id = R.color.mein_tasty_color)
                )
            )
        },
        content = { paddingValues ->
            Column(
                modifier = Modifier.padding(paddingValues)
            ) {
                OutlinedTextField(
                    value = orderTip,
                    onValueChange = { newValue ->
                        orderTip = newValue
                    },
                    modifier = modifier
                        .fillMaxWidth()
                        .padding(top = 8.dp)
                        .padding(horizontal = 8.dp),
                    textStyle = TextStyle(
                        fontSize = MaterialTheme.typography.titleMedium.fontSize,
                        fontWeight = FontWeight.ExtraBold,
                        color = Color.Black
                    ),
                    keyboardOptions = KeyboardOptions.Default.copy(
                        keyboardType = KeyboardType.Phone,
                        imeAction = ImeAction.Next
                    ),
                    shape = RoundedCornerShape(25.dp),
                    label = {
                        Text(
                            text = stringResource(id = R.string.ordertip),
                            style = TextStyle(
                                color = Color.LightGray
                            )
                        )
                    },
                    colors = TextFieldDefaults.colors(
                        focusedIndicatorColor = colorResource(id = R.color.mein_tasty_color),
                        focusedContainerColor = Color.White,
                        unfocusedContainerColor = Color.White,
                        unfocusedTextColor = colorResource(id = R.color.mein_tasty_color),
                    )
                )
                Spacer(modifier = Modifier.height(8.dp))
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = Color.White
                    ),
                    elevation = CardDefaults.cardElevation(1.dp)
                ) {
                    Column(
                        modifier = Modifier.padding(12.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Row {
                            Image(
                                painter = painterResource(id = R.drawable.purse),
                                contentDescription = "",
                                modifier = Modifier.size(24.dp)
                            )
                            Spacer(modifier = Modifier.width(16.dp))
                            Text(
                                text = stringResource(id = R.string.payment_method),
                                style = MaterialTheme.typography.titleMedium.copy(color = Color.Black),
                                maxLines = 1,
                                fontWeight = FontWeight.Bold
                            )
                        }
                        Spacer(modifier = Modifier.height(16.dp))
                        Row {
                            Image(
                                painter = painterResource(id = R.drawable.plus),
                                contentDescription = "",
                                modifier = Modifier.size(20.dp)
                            )
                            Spacer(modifier = Modifier.width(16.dp))
                            Text(
                                text = stringResource(id = R.string.payment_options),
                                style = MaterialTheme.typography.bodyLarge.copy(color = Color.Black),
                                maxLines = 1
                            )
                        }
                    }
                }
            }
        }
    )
}