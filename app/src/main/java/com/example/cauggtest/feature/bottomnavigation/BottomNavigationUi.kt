package com.example.cauggtest.feature.bottomnavigation

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.cauggtest.R


@Composable
fun BottomNavigationBar(
    currentScreen: String,
    navController: NavController
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(60.dp)
            .background(Color.White, shape = RoundedCornerShape(16.dp)) // 배경 색상과 둥근 테두리
            .padding(horizontal = 16.dp, vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceAround // 아이콘 간 간격 균등 배치
    ) {
        // MasteryScreen 버튼
        Box(
            modifier = Modifier
                .size(if (currentScreen == "Mastery") 50.dp else 40.dp) // 선택된 버튼 크기
                .clip(CircleShape)
                .background(if (currentScreen == "Mastery") Color(0xFFB3E5FC) else Color.Transparent) // 선택된 배경색
                .clickable {
                    navController.navigate("mastery"){
                        popUpTo("noticeboard"){
                            inclusive = true
                        }
                        popUpTo("home"){
                            inclusive = true
                        }
                    }
                },
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.mastery), // 마스터리 아이콘 리소스
                contentDescription = null,
                modifier = Modifier.size(30.dp) // 아이콘 크기
            )
        }

        // HomeScreen 버튼
        Box(
            modifier = Modifier
                .size(if (currentScreen == "Home") 50.dp else 40.dp) // 선택된 버튼 크기
                .clip(CircleShape)
                .background(if (currentScreen == "Home") Color(0xFFB3E5FC) else Color.Transparent) // 선택된 배경색
                .clickable {
                    navController.navigate("home"){
                        popUpTo("noticeboard"){
                            inclusive = true
                        }
                        popUpTo("mastery"){
                            inclusive = true
                        }
                    }
                },
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.home), // 홈 아이콘 리소스
                contentDescription = null,
                modifier = Modifier.size(30.dp) // 아이콘 크기
            )
        }

        // NoticeBoardScreen 버튼
        Box(
            modifier = Modifier
                .size(if (currentScreen == "NoticeBoard") 50.dp else 40.dp) // 선택된 버튼 크기
                .clip(CircleShape)
                .background(if (currentScreen == "NoticeBoard") Color(0xFFB3E5FC) else Color.Transparent) // 선택된 배경색
                .clickable {
                    navController.navigate("noticeboard"){
                        popUpTo("signin"){
                            inclusive = true
                        }
                        popUpTo("mastery"){
                            inclusive = true
                        }
                    }
                },
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.noticeboard), // 추천 아이콘 리소스
                contentDescription = null,
                modifier = Modifier.size(30.dp) // 아이콘 크기
            )
        }
    }
    Spacer(modifier = Modifier.height(110.dp))
}

