package com.example.cauggtest.feature.mastery

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.LaunchedEffect
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
//import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.cauggtest.api.RankingResponse
import com.example.cauggtest.api.RetrofitInstance
import com.example.cauggtest.feature.bottomnavigation.BottomNavigationBar
import com.example.cauggtest.ui.theme.SkyBlue40
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MasteryScreen(navController: NavController) {
    var isDropdownExpanded by remember { mutableStateOf(false) }
    var selectedDepartment by remember { mutableStateOf("소프트") }
    val departments = listOf(
        "소프트", "전자전기", "기계", "건축", "AI", "의학부", "약학부", "ICT", "화학공학",
        "기계공학", "에너지시스템공학", "첨단소재공학", "물리학과", "화학과", "생명과학과",
        "수학과", "교육학과", "유아교육과", "영어교육과", "체육교육과", "국어국문학과",
        "영어영문학과", "유럽문화학부", "아시아문화학부", "철학과", "역사학과",
        "정치국제학과", "공공인재학부", "심리학과", "문헌정보학과", "사회복지학부",
        "미디어커뮤니케이션학부", "도시계획부동산학과", "사회학과"
    )

    // 네트워크 요청 결과를 상태로 관리
    val context = LocalContext.current
    val (summonerList, setSummonerList) = remember { mutableStateOf<List<SummonerData>>(emptyList()) }

    LaunchedEffect(selectedDepartment) {
        // 학과가 변경되면 네트워크 요청 실행
        RetrofitInstance.api.getRanking(selectedDepartment).enqueue(object :
            Callback<List<RankingResponse>> {
            override fun onResponse(
                call: Call<List<RankingResponse>>,
                response: Response<List<RankingResponse>>
            ) {
                if (response.isSuccessful) {
                    // 응답 데이터를 SummonerData 형태로 변환하여 상태 업데이트
                    val rankingData = response.body()?.map { ranking ->
                        SummonerData(
                            nickname = ranking.nickname,
                            tier = ranking.solo_rank,
                            lp = parseLP(ranking.solo_rank), // LP 값 추출
                            mostChampion = ranking.most
                        )
                    } ?: emptyList()
                    setSummonerList(rankingData)
                } else {
                    Log.e("MasteryScreen", "Error: ${response.code()}")
                }
            }

            override fun onFailure(call: Call<List<RankingResponse>>, t: Throwable) {
                Log.e("MasteryScreen", "Request failed: ${t.message}")
            }
        })
    }

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

                // 학과 선택 버튼 UI
                Column(
                    modifier = Modifier
                        .padding(16.dp)
                        .fillMaxWidth()
                ) {
                    // 학과 선택 드롭다운
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

                    ExposedDropdownMenuBox(
                        expanded = isDropdownExpanded,
                        onExpandedChange = { isDropdownExpanded = it }
                    ) {
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

                        ExposedDropdownMenu(
                            expanded = isDropdownExpanded,
                            onDismissRequest = { isDropdownExpanded = false }
                        ) {
                            departments.forEach { department ->
                                DropdownMenuItem(
                                    text = { Text(department) },
                                    onClick = {
                                        selectedDepartment = department
                                        isDropdownExpanded = false
                                    }
                                )
                            }
                        }
                    }
                }

                Spacer(modifier = Modifier.height(10.dp))

                LazyColumn {
                    items(summonerList.size) { index ->
                        val summoner = summonerList[index]
                        SummonerInfo(
                            rank = index + 1,
                            summonerName = summoner.nickname,
                            summonerTier = summoner.tier,
                            summonerLP = summoner.lp,
                            summonerIconRes = getChampionIconRes(summoner.mostChampion)
                        )
                    }
                }
            }
        }
    }
}

// LP 값 파싱 함수
fun parseLP(rankString: String): String {
    val regex = """(\d+)LP""".toRegex()
    return regex.find(rankString)?.groupValues?.get(1) ?: "0"
}

data class SummonerData(
    val nickname: String,
    val tier: String,
    val lp: String,
    val mostChampion: String
)



// 챔피언 이름에 따라 리소스 ID를 가져오는 함수
@Composable
fun getChampionIconRes(championName: String): Int {
    val resourceName = "icon_${championName.lowercase()}"
    val context = LocalContext.current
    return context.resources.getIdentifier(resourceName, "drawable", context.packageName)
}


//랭킹에 쓸 카드
@Composable
fun SummonerInfo(
    rank: Int,
    summonerName: String,
    summonerTier: String,
    summonerLP: String,
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