package com.example.meintasty.feature.update_screen

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.Scaffold
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.meintasty.R
import com.example.meintasty.domain.model.update_email_model.update_email_request.EmailUpdateRequest
import com.example.meintasty.domain.model.update_phone_model.update_phone_request.UpdatePhoneRequest
import com.example.meintasty.navigation.Screen
import com.example.meintasty.uicomponent.BackIcon
import com.example.meintasty.uicomponent.EmailComponent
import com.example.meintasty.uicomponent.HeaderComponent
import com.example.meintasty.uicomponent.PhoneComponent

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UpdateScreen(
    userId : Int?,
    email : String?,
    phoneNum :String?,
    updateType:String?,
    navController: NavHostController,
    updateViewModel: UpdateViewModel = hiltViewModel()
) {

    val updatePhoneState = updateViewModel.updatePhoneState.collectAsState().value

    var phoneUpdate by remember {
        mutableStateOf("")
    }
    var emailUpdate by remember {
        mutableStateOf("")
    }
    val context = LocalContext.current

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
                      val header =  when(updateType){
                          "email" -> stringResource(id = R.string.emailUpdate)
                          "phone" -> stringResource(id = R.string.phone)
                          else -> {
                              Toast.makeText(context,"Empty",Toast.LENGTH_SHORT).show()
                          }
                      }
                        HeaderComponent(text = header.toString())

                    }
                },
                actions = {
                    IconButton(onClick = {
                        when(updateType){
                            "phone" ->  {
                                val updatePhoneRequest = UpdatePhoneRequest(phoneUpdate, userId = userId)
                                updateViewModel.updatePhone(updatePhoneRequest)
                                Toast.makeText(context,"Update Phone",Toast.LENGTH_SHORT).show()
                            }
                            "email" ->{
                                val updateEmailRequest = EmailUpdateRequest(emailUpdate,userId)
                                updateViewModel.updateEmail(updateEmailRequest)
                                Toast.makeText(context,"Update Email",Toast.LENGTH_SHORT).show()
                            }
                            else -> {
                                Toast.makeText(context,"Empty",Toast.LENGTH_SHORT).show()
                            }
                        }

                        navController.navigate(Screen.ProfileScreen.route)

                    }) {
                        Icon(
                            painter = painterResource(
                                id = R.drawable.correct
                            ),
                            contentDescription = "",
                            modifier = Modifier.size(24.dp),
                            tint = Color.White
                        )
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
                    .fillMaxWidth()
                    .padding(horizontal = 10.dp)
                    .padding(padding)

            ) {
                 when(updateType){
                    "phone" -> {
                        PhoneComponent(
                            phone = phoneUpdate,
                            onPhoneChange = {
                                phoneUpdate = it
                            }
                        )
                    }
                    "email" ->{
                        EmailComponent(
                            email = emailUpdate,
                            onMailChange = {
                                emailUpdate = it
                            }
                        )
                    }
                    else -> {
                        Toast.makeText(context,"Empty",Toast.LENGTH_SHORT).show()
                    }
                }

            }
        }
    )
}