package com.example.cauggtest.feature.noticeboard

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.cauggtest.feature.bottomnavigation.BottomNavigationBar
import com.example.cauggtest.ui.theme.SkyBlue40
import com.example.cauggtest.R
import com.example.cauggtest.feature.bottomnavigation.BottomNavigationBar
import com.example.cauggtest.ui.theme.SkyBlue40


@Composable
fun WriteScreen(navController: NavController) {
    Scaffold(
        containerColor = SkyBlue40,
        bottomBar = {
            BottomNavigationBar(currentScreen = "Recommend", navController = navController)
        }
    ) {
        Box(
            modifier = Modifier
                .background(SkyBlue40)
                .padding(it)
                .fillMaxSize()
        ) {
            // 배경 이미지
            Image(
                painter = painterResource(id = R.drawable.school),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxSize()
                    .alpha(0.7f)
            )

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(modifier = Modifier.height(50.dp))

                // 현재 이용자 수
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier
                            .size(12.dp)
                            .clip(RoundedCornerShape(50))
                            .background(Color(0xFF7CFC00))
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "현재 1명 이용중입니다",
                        style = TextStyle(color = Color(0xFF7CFC00), fontSize = 16.sp, fontWeight = FontWeight.Bold),
                    )
                }

                Spacer(modifier = Modifier.height(12.dp))

                // 커뮤니티 제목
                Text(
                    text = "커뮤니티",
                    style = MaterialTheme.typography.displayLarge.copy(
                        color = Color.White,
                        fontSize = 48.sp,
                        fontWeight = FontWeight.Bold
                    )
                )

                Spacer(modifier = Modifier.height(50.dp))

                // 글쓰기 버튼 및 목록 제목



                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Button(
                        onClick = { navController.popBackStack() },
                        modifier = Modifier
                            .wrapContentSize()
                            .clip(RoundedCornerShape(1.dp)),
                        colors = androidx.compose.material3.ButtonDefaults.buttonColors(
                            containerColor = Color.White
                        )
                    ) {
                        Text(
                            text = "뒤로가기",
                            style = TextStyle(
                                fontSize = 12.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color.Black
                            )
                        )
                    }
                    Button(
                        onClick = { /*글 올리기 firebase? or local storage*/ },
                        modifier = Modifier
                            .wrapContentSize() // 버튼 크기를 텍스트에 맞춤
                            .clip(RoundedCornerShape(1.dp)),
                        colors = androidx.compose.material3.ButtonDefaults.buttonColors(
                            containerColor = Color.White
                        )
                    ) {
                        Text(
                            text = "게시",
                            style = TextStyle(
                                fontSize = 12.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color.Black
                            )
                        )
                    }
                }
                Spacer(modifier = Modifier.height(20.dp))
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight()
                        .background(Color.White, shape = RoundedCornerShape(16.dp))
                        .padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {

                    Text(
                        text = "커뮤니티에 글을 써보세요!",
                        style = TextStyle(
                            fontSize = 14.sp,
                            color = Color.Black,
                            fontWeight = FontWeight.Bold
                        )
                    )
                    // 제목 입력
                    OutlinedTextField(
                        value = "",
                        onValueChange = { /* Handle Title Input */ },
                        label = {
                            Text(
                                text = "제목을 입력해주세요",
                                style = TextStyle(
                                    fontSize = 14.sp,
                                    color = Color.Gray
                                )
                            )
                        },
                        modifier = Modifier.fillMaxWidth()
                    )

                    // 닉네임 입력
                    OutlinedTextField(
                        value = "",
                        onValueChange = { /* Handle Nickname Input */ },
                        label = {
                            Text(
                                text = "리그 오브 레전드 아이디를 입력해주세요 #태그 포함",
                                style = TextStyle(
                                    fontSize = 14.sp,
                                    color = Color.Gray
                                )
                            )
                        },
                        modifier = Modifier.fillMaxWidth()
                    )
                    Spacer(modifier = Modifier.height(120.dp))
                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun WriteScreenPreview() {
    val navController = rememberNavController() // NavController가 필요하므로 임시로 생성
    WriteScreen(navController = navController)
}