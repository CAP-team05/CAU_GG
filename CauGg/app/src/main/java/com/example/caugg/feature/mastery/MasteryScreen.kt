package com.example.caugg.feature.mastery

import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ExitToApp
import androidx.compose.material.icons.automirrored.filled.Send
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
//import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.caugg.R
import com.example.caugg.feature.auth.signin.SignInScreen
import com.example.caugg.feature.bottomnavigation.BottomNavigationBar
import com.example.caugg.ui.theme.SkyBlue40

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MasteryScreen(navController: NavController) {
    Scaffold(
        containerColor = SkyBlue40,
        bottomBar = {
            BottomNavigationBar(currentScreen = "Mastery", navController = navController)
        }
    ) {
        Box(
            modifier = Modifier
                .background(color = SkyBlue40)
                .padding(it)
                .fillMaxSize()
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Spacer(modifier = Modifier.height(37.dp))


                //현재 온라인 유저 수 (연두색)
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    // 초록색 원
                    Box(
                        modifier = Modifier
                            .size(12.dp) // 원 크기
                            .clip(CircleShape) // 원 모양
                            .background(Color(0xFF7CFC00)) // 원 색상
                    )

                    Spacer(modifier = Modifier.width(8.dp)) // 원과 텍스트 간 간격

                    Text(
                        text = "온라인 392",
                        style = TextStyle(
                            color = Color(0xFF7CFC00), // 연두색
                            fontSize = 27.sp,
                            fontWeight = FontWeight.Bold
                        )
                    )
                }


                // CAU.GG 타이틀
                Text(
                    text = "CAU.GG",
                    style = MaterialTheme.typography.displayLarge.copy(
                        color = Color.White,
                        fontSize = 65.sp
                    ),
                    textAlign = TextAlign.Center
                )

                Spacer(modifier = Modifier.height(18.dp))
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MasteryScreenPreview() {
    val navController = rememberNavController() // NavController가 필요하므로 임시로 생성
    MasteryScreen(navController = navController)
}