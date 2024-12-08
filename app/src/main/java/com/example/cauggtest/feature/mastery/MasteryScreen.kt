package com.example.cauggtest.feature.mastery

import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.R
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
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
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
import com.example.cauggtest.feature.bottomnavigation.BottomNavigationBar
import com.example.cauggtest.ui.theme.SkyBlue40
import com.example.cauggtest.R.drawable.school


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MasteryScreen(navController: NavController) {

    var isDropdownExpanded by remember { mutableStateOf(false) }
    var selectedDepartment by remember { mutableStateOf("소프트") }
    val departments = listOf("소프트", "전자전기", "기계", "건축", "AI", "의학부", "약학부", "ICT", "화학공학", "기계공학"
        , "에너지시스템공학", "첨단소재공학", "물리학과", "화학과", "생명과학과", "수학과",
        "교육학과", "유아교육과", "영어교육과", "체육교육과", "국어국문학과", "영어영문학과", "유럽문화학부",
        "아시아문화학부", "철학과", "역사학과", "정치국제학과", "공공인재학부", "심리학과", "문헌정보학과", "사회복지학부",
        "미디어커뮤니케이션학부", "도시계획부동산학과", "사회학과")

    Scaffold(
        containerColor = SkyBlue40,
        bottomBar = {
            BottomNavigationBar(currentScreen = "Mastery", navController = navController)
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
                painter = painterResource(id = com.example.cauggtest.R.drawable.school),
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
                Text(
                    text = "CAU.GG",
                    style = MaterialTheme.typography.displayLarge.copy(
                        color = Color.White,
                        fontSize = 65.sp
                    ),
                    textAlign = TextAlign.Center
                )

                // "현재 학과별 탑티어리스트" 텍스트 추가
                Spacer(modifier = Modifier.height(100.dp))
                Text(
                    text = "현재 학과별 탑티어리스트",
                    style = TextStyle(
                        color = Color.White,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Normal
                    ) ,
                    modifier = Modifier.fillMaxWidth().align(Alignment.Start)
                )

                Spacer(modifier = Modifier.height(12.dp))

                // 학과 선택 버튼 UI
                Column(
                    modifier = Modifier
                        .padding(16.dp)
                        .fillMaxWidth()
                ) {
                    // 학과 선택 버튼
                    Button(
                        onClick = { isDropdownExpanded = !isDropdownExpanded },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 16.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = Color.White)
                    ) {
                        Text(
                            text = selectedDepartment,
                            style = TextStyle(fontSize = 16.sp, fontWeight = FontWeight.Bold, color = Color.Black)
                        )
                    }

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

                Spacer(modifier = Modifier.height(10.dp))

                LazyColumn {
                    items(10) { index ->
                        SummonerInfo(
                            rank = index + 1,
                            summonerName = "괴물쥐",
                            summonerTier = "master",
                            summonerLP = 100,
                            summonerIconRes = com.example.cauggtest.R.drawable.icon_zeri // 챔피언 아이콘
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun SummonerInfo(
    rank: Int,
    summonerName: String,
    summonerTier: String,
    summonerLP: Int,
    summonerIconRes: Int
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        shape = RoundedCornerShape(12.dp),
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White)
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // 순위
            Text(
                text = "#$rank", // 순위 텍스트
                style = TextStyle(
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )
            )

            Spacer(modifier = Modifier.width(16.dp)) // 순위와 아이콘 간격

            // 챔피언 아이콘
            Image(
                painter = painterResource(id = summonerIconRes),
                contentDescription = null,
                modifier = Modifier
                    .size(50.dp)
                    .clip(RoundedCornerShape(8.dp))
            )

            Spacer(modifier = Modifier.width(16.dp))

            // 소환사 정보
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = summonerName,
                    style = TextStyle(
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black
                    )
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = summonerTier,
                    style = TextStyle(
                        fontSize = 14.sp,
                        color = Color.Gray
                    )
                )
            }

            Spacer(modifier = Modifier.width(8.dp))

            // LP
            Column(
                horizontalAlignment = Alignment.End
            ) {
                Text(
                    text = "$summonerLP LP",
                    style = TextStyle(
                        fontSize = 14.sp,
                        color = Color.Black
                    )
                )
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