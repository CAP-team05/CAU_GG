package com.example.cauggtest.feature.auth.signin

import android.util.Log
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
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
//import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.cauggtest.R
import com.example.cauggtest.api.LoginRequest
import com.example.cauggtest.api.LoginResponse
import com.example.cauggtest.api.RegisterRequest
import com.example.cauggtest.api.RetrofitInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignInScreen(navController: NavController) {
    //val viewModel: SignInViewModel = hiltViewModel()
    //val uiState = viewModel.state.collectAsState()
    var email by remember {
        mutableStateOf("")
    }
    var password by remember {
        mutableStateOf("")
    }
    var isLoading by remember {
        mutableStateOf(false)
    }

    val context = LocalContext.current
//    LaunchedEffect(key1 = uiState.value) {
//        when (uiState.value) {
//            is SignInState.Success -> {
//                navController.navigate("home") {
//                    popUpTo("login") {
//                        inclusive = true
//                    }
//                }
//            }
//
//            is SignInState.Error -> {
//                Toast.makeText(context, "Sign In Failed", Toast.LENGTH_SHORT).show()
//            }
//
//            else -> {}
//        }
//    }

    val chosunFont = FontFamily(
        Font(R.font.chosuncentennial)
    )

    Scaffold(
        modifier = Modifier
            .fillMaxSize()
    ) {
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
                ),
                fontFamily = chosunFont
            )
            Spacer(modifier = Modifier.size(32.dp))
            OutlinedTextField(
                value = email,
                onValueChange = { email = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(0.dp), // 필요시 여백을 조정
                label = { Text(text = "이메일") },
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    containerColor = Color.White // 배경색 설정
                )
            )

            Spacer(modifier = Modifier.size(8.dp))
            OutlinedTextField(
                value = password,
                onValueChange = { password = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(0.dp),
                label = { Text(text = "비밀번호") },
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    containerColor = Color.White // 배경색 설정
                ),
                visualTransformation = PasswordVisualTransformation(),
            )
            Spacer(modifier = Modifier.size(16.dp))

            Button(
                onClick = {
                    isLoading = true
                    val request = LoginRequest(email, password)
                    Log.d("print", "request: $request")
                    RetrofitInstance.api.login(request).enqueue(object : Callback<LoginResponse> {
                        override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                            isLoading = false
                            Log.d("print", "response: $response")

                            if (response.isSuccessful) {
                                Log.d("print", "response nickname ${response.body()?.nickname}")
                                if (response.body()?.nickname != null) {
                                    navController.navigate("home") {
                                        popUpTo("signin") {
                                            inclusive = true
                                        }
                                    }
                                } else {
                                    Toast.makeText(context, "이이디 혹은 비밀번호가 불일치합니다.", Toast.LENGTH_SHORT).show()
                                }

                            } else {
                                // Show error
                                Toast.makeText(context, "서버 오류 발생", Toast.LENGTH_SHORT).show()
                            }
                        }

                        override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                            isLoading = false
                            // Show error
                        }
                    })
                },   //viewModel.signIn(email, password)
                modifier = Modifier.fillMaxWidth(),
            ) {
                Text(text = stringResource(id = R.string.signin))
            }
            TextButton(onClick = { navController.navigate("signup"){

            } }) {
                Text(text = stringResource(id = R.string.signintext))
            }

        }
    }
}

@Preview(showBackground = true)
@Composable
fun SignInScreenPreview() {
    val navController = rememberNavController() // NavController가 필요하므로 임시로 생성
    SignInScreen(navController = navController)
}
