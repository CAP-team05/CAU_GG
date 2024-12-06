package com.example.caugg.feature.auth.signup

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
//import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.caugg.R
import com.example.caugg.feature.auth.signin.SignInScreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignUpScreen(navController: NavController) {
    //val viewModel: SignUpViewModel = hiltViewModel()
    //val uiState = viewModel.state.collectAsState()
    var name by remember {
        mutableStateOf("")
    }
    var email by remember {
        mutableStateOf("")
    }
    var password by remember {
        mutableStateOf("")
    }
    var confirm by remember {
        mutableStateOf("")
    }

    val context = LocalContext.current
//    LaunchedEffect(key1 = uiState.value) {
//        when (uiState.value) {
//            is SignUpState.Success -> {
//                navController.navigate("home") {
//                    popUpTo("login") {
//                        inclusive = true
//                    }
//                    popUpTo("signup") {
//                        inclusive = true
//                    }
//                }
//            }
//
//            is SignUpState.Error -> {
//                Toast.makeText(context, "Sign In Failed", Toast.LENGTH_SHORT).show()
//            }
//
//            else -> {}
//        }
//    }
    Scaffold(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(color = Color(0xFF87CEEB)) // 배경 색상 설정
                .padding(it)
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(
                text = "CAU.GG",
                style = MaterialTheme.typography.displayLarge.copy(
                    color = Color.White, // 텍스트 색상
                    fontSize = 48.sp // 텍스트 크기
                )
            )

            Spacer(modifier = Modifier.size(32.dp))
            OutlinedTextField(
                value = name,
                onValueChange = { name = it },
                modifier = Modifier.fillMaxWidth().padding(0.dp),
                label = { Text(text = "Name") },
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    containerColor = Color.White // 배경색 설정
                )
            )
            Spacer(modifier = Modifier.size(8.dp))
            OutlinedTextField(
                value = email,
                onValueChange = { email = it },
                modifier = Modifier.fillMaxWidth().padding(0.dp),
                label = { Text(text = "Email") },
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    containerColor = Color.White // 배경색 설정
                )
            )
            Spacer(modifier = Modifier.size(8.dp))
            OutlinedTextField(
                value = password,
                onValueChange = { password = it },
                modifier = Modifier.fillMaxWidth().padding(0.dp),
                label = { Text(text = "Password") },
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    containerColor = Color.White // 배경색 설정
                ),
                visualTransformation = PasswordVisualTransformation(),
            )
            Spacer(modifier = Modifier.size(8.dp))
            OutlinedTextField(
                value = confirm,
                onValueChange = { confirm = it },
                modifier = Modifier.fillMaxWidth().padding(0.dp),
                label = { Text(text = "Confirm Password") },
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    containerColor = Color.White // 배경색 설정
                ),
                visualTransformation = PasswordVisualTransformation(),
                isError = password.isNotEmpty() && confirm.isNotEmpty() && confirm != password
            )
            Spacer(modifier = Modifier.size(16.dp))

            Button(
                onClick = { navController.navigate("signin") },//viewModel.signUp(name, email, password)
                modifier = Modifier.fillMaxWidth(),
                enabled = name.isNotEmpty() &&
                        email.isNotEmpty() &&
                        password.isNotEmpty() &&
                        confirm.isNotEmpty() &&
                        password == confirm,
            ) {
                Text(text = stringResource(id = R.string.signup))
            }
            TextButton(onClick = {navController.navigate("signin")}) {
                Text(text = stringResource(id = R.string.signuptext))
            }

        }
    }
}

@Preview(showBackground = true)
@Composable
fun SignUpScreenPreview() {
    val navController = rememberNavController() // NavController가 필요하므로 임시로 생성
    SignUpScreen(navController = navController)
}