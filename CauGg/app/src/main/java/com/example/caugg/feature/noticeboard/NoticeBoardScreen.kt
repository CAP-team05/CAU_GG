package com.example.caugg.feature.noticeboard

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.caugg.R
import com.example.caugg.feature.bottomnavigation.BottomNavigationBar
import com.example.caugg.ui.theme.SkyBlue40

@Composable
fun NoticeBoardScreen(navController: NavController) {
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
                        text = "현재 27명 이용중입니다",
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


                Button(
                    onClick = { navController.navigate("write") },
                    modifier = Modifier
                        .wrapContentSize() // 버튼 크기를 텍스트에 맞춤
                        .clip(RoundedCornerShape(4.dp))
                        .align(Alignment.End),
                    colors = androidx.compose.material3.ButtonDefaults.buttonColors(
                        containerColor = Color.White
                    )
                ) {
                    Text(
                        text = "글쓰기",
                        style = TextStyle(
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.Black
                        )
                    )
                }

                Text(
                    text = "CAU.GG 커뮤니티 목록",
                    style = TextStyle(color = Color.White, fontSize = 18.sp,
                        fontWeight = FontWeight.Bold),
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Start
                )

                Spacer(modifier = Modifier.height(16.dp))

                // 글 목록 (더미 데이터)
                LazyColumn(modifier = Modifier.fillMaxSize()) {
                    items(listOf(
                        Triple("자랭 돌릴 팟 구합니다", "hide on bush#KR1", "4시간 전"),
                        Triple("롤 1:1 맞춤 강의 배워 보실 분?", "이거진짜임#KR1", "5시간 전"),
                        Triple("소프트 VS 전자전기 5:5 내전 하실 분?", "피아니스트#KR1", "6시간 전")
                    )) { post ->
                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 6.dp)
                                .background(Color.White),
                            shape = RoundedCornerShape(8.dp),
                        ) {
                            Column(
                                modifier = Modifier.padding(12.dp)
                            ) {
                                Text(
                                    text = post.first, // 제목
                                    style = TextStyle(
                                        fontSize = 16.sp,
                                        fontWeight = FontWeight.Bold,
                                        color = Color.Black
                                    )
                                )
                                Spacer(modifier = Modifier.height(35.dp))
                                Row(
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Text(
                                        text = post.second, // 닉네임
                                        style = TextStyle(
                                            fontSize = 12.sp,
                                            color = Color.Gray
                                        )
                                    )
                                    Spacer(modifier = Modifier.width(8.dp))
                                    Text(
                                        text = "|",
                                        style = TextStyle(
                                            fontSize = 12.sp,
                                            color = Color.Gray
                                        )
                                    )
                                    Spacer(modifier = Modifier.width(8.dp))
                                    Text(
                                        text = post.third, // 시간
                                        style = TextStyle(
                                            fontSize = 12.sp,
                                            color = Color.Gray
                                        )
                                    )
                                    Spacer(modifier = Modifier.width(8.dp))
                                    Text(
                                        text = "|",
                                        style = TextStyle(
                                            fontSize = 12.sp,
                                            color = Color.Gray
                                        )
                                    )
                                    Spacer(modifier = Modifier.width(8.dp))
                                    Text(
                                        text = "티어: 비공개", // 티어 정보
                                        style = TextStyle(
                                            fontSize = 12.sp,
                                            color = Color.Gray
                                        )
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun NoticeBoardScreenPreview() {
    val navController = rememberNavController() // NavController가 필요하므로 임시로 생성
    NoticeBoardScreen(navController = navController)
}