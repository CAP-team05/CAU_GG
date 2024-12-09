package com.example.cauggtest.feature.auth.signup

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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
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
import com.example.cauggtest.R
import com.example.cauggtest.api.RegisterRequest
import com.example.cauggtest.api.RetrofitInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

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
    var major by remember {
        mutableStateOf("")
    }
    var isDropdownExpanded by remember { mutableStateOf(false) }
    var selectedDepartment by remember { mutableStateOf("소프트") }
    val departments = listOf("소프트", "전자전기", "기계", "건축", "AI", "의학부", "약학부", "ICT", "화학공학", "기계공학"
        , "에너지시스템공학", "첨단소재공학", "물리학과", "화학과", "생명과학과", "수학과",
        "교육학과", "유아교육과", "영어교육과", "체육교육과", "국어국문학과", "영어영문학과", "유럽문화학부",
        "아시아문화학부", "철학과", "역사학과", "정치국제학과", "공공인재학부", "심리학과", "문헌정보학과", "사회복지학부",
        "미디어커뮤니케이션학부", "도시계획부동산학과", "사회학과")
    var isLoading by remember {
        mutableStateOf(false)
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
                label = { Text(text = "닉네임 #해시태그") },
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    containerColor = Color.White // 배경색 설정
                )
            )
            Spacer(modifier = Modifier.size(16.dp))
            Column(
                modifier = Modifier
                    .padding(2.dp)
                    .fillMaxWidth()
            ) {
                // 드롭다운 메뉴
                ExposedDropdownMenuBox(
                    expanded = isDropdownExpanded,
                    onExpandedChange = { isDropdownExpanded = it }
                ) {
                    // 드롭다운 버튼 클릭 시 표시되는 목록
                    TextField(
                        value = selectedDepartment,
                        onValueChange = {},
                        readOnly = true,
                        label = { Text("학과 선택") },
                        trailingIcon = {
                            Icon(
                                imageVector = Icons.Filled.ArrowDropDown,
                                contentDescription = "Arrow Down"
                            )
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .menuAnchor()
                    )

                    // 선택할 학과 목록
                    ExposedDropdownMenu(
                        expanded = isDropdownExpanded,
                        onDismissRequest = { isDropdownExpanded = false }
                    ) {
                        departments.forEach { department ->
                            DropdownMenuItem(
                                text = { Text(department) },
                                onClick = {
                                    selectedDepartment = department
                                    isDropdownExpanded = false // 메뉴 닫기
                                }
                            )
                        }
                    }
                }
            }
            Spacer(modifier = Modifier.size(8.dp))
            OutlinedTextField(
                value = email,
                onValueChange = { email = it },
                modifier = Modifier.fillMaxWidth().padding(0.dp),
                label = { Text(text = "학교 이메일") },
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    containerColor = Color.White // 배경색 설정
                )
            )
            Spacer(modifier = Modifier.size(8.dp))
            OutlinedTextField(
                value = password,
                onValueChange = { password = it },
                modifier = Modifier.fillMaxWidth().padding(0.dp),
                label = { Text(text = "비밀번호") },
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
                label = { Text(text = "비밀번호 확인") },
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    containerColor = Color.White // 배경색 설정
                ),
                visualTransformation = PasswordVisualTransformation(),
                isError = password.isNotEmpty() && confirm.isNotEmpty() && confirm != password
            )
            Spacer(modifier = Modifier.size(16.dp))

            Button(
                onClick = {
                    if (!email.endsWith("@cau.ac.kr")) {
                        Toast.makeText(context, "이메일은 @cau.ac.kr로 끝나야 합니다.", Toast.LENGTH_SHORT).show()
                        return@Button
                    }

                    isLoading = true
                    val request = RegisterRequest(email, password, name, selectedDepartment)
                    Log.d("print", "request : $request")
                    RetrofitInstance.api.register(request).enqueue(object : Callback<Unit> {
                        override fun onResponse(call: Call<Unit>, response: Response<Unit>) {
                            isLoading = false
                            Log.d("print", "request in on Response : $request")
                            if (response.isSuccessful) {
                                navController.navigate("login") {
                                    popUpTo("signup") {
                                        inclusive = true
                                    }
                                }
                            } else {
                                Log.d("print", "error in onResponse")
                                // Show error
                            }
                        }

                        override fun onFailure(call: Call<Unit>, t: Throwable) {
                            isLoading = false
                            Log.e("Retrofit", "Request failed: ${t.message}")
                            // Show error
                        }
                    })
                },//viewModel.signUp(name, email, password)
                modifier = Modifier.fillMaxWidth(),
                enabled = name.isNotEmpty() &&
                        email.isNotEmpty() &&
                        password.isNotEmpty() &&
                        confirm.isNotEmpty() &&
                        password == confirm,
            ) {
                Text(text = stringResource(id = R.string.signup))
            }
            TextButton(onClick = {
                navController.navigate("signin"){
                    popUpTo("signup"){
                        inclusive = true
                    }
                }
            },) {
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