package com.example.meintasty.feature.password_screen

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.min
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.meintasty.R
import com.example.meintasty.domain.model.update_phone_model.update_phone_request.UpdatePhoneRequest
import com.example.meintasty.domain.model.user_password_model.user_pasword_request.UpdatePasswordRequest
import com.example.meintasty.uicomponent.BackIcon
import com.example.meintasty.uicomponent.HeaderComponent
import com.example.meintasty.uicomponent.PasswordSignUpComponent

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PasswordScreen(
    userId: Int?,
    navController: NavController,
    modifier: Modifier = Modifier,
    passwordViewModel: PasswordViewModel = hiltViewModel()
) {

    var updatePassword by remember {
        mutableStateOf("")
    }

    val context = LocalContext.current
    val passwordState = passwordViewModel.passwwordState.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Row(
                        modifier = Modifier.wrapContentSize(),
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        BackIcon {
                            navController.navigateUp()
                        }
                        HeaderComponent(text = stringResource(id = R.string.update_password))
                    }
                },

                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = colorResource(id = R.color.mein_tasty_color)
                )
            )
        },
        content = { padding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding)
                    .padding(16.dp)
            ) {
                Text(
                    modifier = Modifier.padding(top = 16.dp).padding(start = 8.dp),
                    text = stringResource(id = R.string.change_password),
                    style = TextStyle(
                        color = Color.Black,
                        fontSize = MaterialTheme.typography.titleMedium.fontSize
                    )
                )
              /*  PasswordSignUpComponent(
                    modifier = modifier,
                    password = updatePassword,
                    onPaswordChange = {
                        updatePassword = it
                    },
                    text = stringResource(id = R.string.old_password)
                )*/
                PasswordSignUpComponent(
                    password = updatePassword,
                    onPaswordChange = {
                        updatePassword = it
                    },
                    text = stringResource(id = R.string.new_password)
                )
              /*  PasswordSignUpComponent(
                    password = updatePassword,
                    onPaswordChange = {
                        updatePassword = it
                    },
                    text = stringResource(id = R.string.corfirm_password)
                )*/

                Button(
                    onClick = {
                        if (updatePassword.isEmpty().not()) {
                            val updatePhoneRequest = UpdatePasswordRequest(updatePassword, userId)
                            passwordViewModel.updatePassword(updatePhoneRequest)
                            Toast.makeText(context, "Change Password", Toast.LENGTH_SHORT).show()
                            navController.navigateUp()
                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 16.dp)
                        .wrapContentHeight(),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = colorResource(id = R.color.mein_tasty_color)
                    )
                ) {
                    Text(
                        text = stringResource(id = R.string.save),
                        style = TextStyle(
                            color = Color.White
                        ),
                        modifier = Modifier.padding(vertical = 8.dp)
                    )
                }
            }
        }
    )
}