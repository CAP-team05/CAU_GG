package com.example.cauggtest.feature.home

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
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
//import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.cauggtest.R
import com.example.cauggtest.feature.bottomnavigation.BottomNavigationBar
import com.example.cauggtest.ui.theme.SkyBlue40

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavController) {

    // SummonerItemData 리스트 생성
    val summonerList = listOf(
        SummonerItemData(
            summonerName = "김날개 #KR1",
            summonerTier = "Emerald 4 77LP",
            summonerChamRes = R.drawable.azir,
            tierIconRes = R.drawable.emerald
        ),
        SummonerItemData(
            summonerName = "서코횽아 #KR1",
            summonerTier = "Iron 2 24LP",
            summonerChamRes = R.drawable.galio,
            tierIconRes = R.drawable.iron
        ),
        SummonerItemData(
            summonerName = "태토야 #KR1",
            summonerTier = "Gold 33LP",
            summonerChamRes = R.drawable.amumu,
            tierIconRes = R.drawable.gold
        ),
        SummonerItemData(
            summonerName = "alvaro siza #KR1",
            summonerTier = "Silver 4 100LP",
            summonerChamRes = R.drawable.anivia,
            tierIconRes = R.drawable.silver
        ),
        SummonerItemData(
            summonerName = "노혁규 #KR1",
            summonerTier = "Platinum 4 75LP",
            summonerChamRes = R.drawable.jhin,
            tierIconRes = R.drawable.platinum
        ),
        SummonerItemData(
            summonerName = "화려한 솔로킬 #KR644",
            summonerTier = "Gold 1 50LP",
            summonerChamRes = R.drawable.sylas,
            tierIconRes = R.drawable.gold
        )
    )

    val chosunFont = FontFamily(
        Font(R.font.chosuncentennial)
    )

    Scaffold(
        containerColor = SkyBlue40,
        bottomBar = {
            BottomNavigationBar(currentScreen = "Home", navController = navController)
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
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.Start // 왼쪽 정렬
                ) {
                    IconButton(onClick = { navController.navigate("login") {
                        popUpTo("login") {
                            inclusive = true
                        }
                    } }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ExitToApp,
                            contentDescription = null,
                            tint = Color.Gray,
                        )
                    }
                }

                Spacer(modifier = Modifier.height(37.dp))

                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier
                            .size(12.dp)
                            .clip(CircleShape)
                            .background(Color(0xFF7CFC00))
                    )

                    Spacer(modifier = Modifier.width(8.dp))

                    Text(
                        text = "온라인 1",
                        style = TextStyle(
                            color = Color(0xFF7CFC00),
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
                    textAlign = TextAlign.Center,
                    fontFamily = chosunFont
                )

                Spacer(modifier = Modifier.height(18.dp))

                // Search Bar
                SearchBar()

                Spacer(modifier = Modifier.height(65.dp))

                // "가입된 플레이어"
                SectionTitle(title = "가입된 플레이어", modifier = Modifier.align(Alignment.Start))

                Spacer(modifier = Modifier.height(8.dp))

                // 플레이어 목록
                LazyColumn {
                    items(summonerList) { summoner ->
                        SummonerItem(
                            summonerName = summoner.summonerName,
                            summonerTier = summoner.summonerTier,
                            summonerChamRes = summoner.summonerChamRes,
                            tierIconRes = summoner.tierIconRes,
                            modifier = Modifier
                                .padding(horizontal = 10.dp, vertical = 3.dp),
                            onClick = {
                                // 클릭 이벤트 처리
                            }
                        )
                    }
                }
            }

        }
    }
}


data class SummonerItemData(
    val summonerName: String,
    val summonerTier: String,
    val summonerChamRes: Int,
    val tierIconRes: Int
)

//검색 창
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchBar() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(60.dp)
            .clip(RoundedCornerShape(26.dp))
            .background(Color.White)
            .padding(horizontal = 6.dp, vertical = 4.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // cau 아이콘
        Image(
            painter = painterResource(id = R.drawable.cau),
            contentDescription = null,
            modifier = Modifier.size(55.dp)
        )

        // cau 옆 막대기
        Box(
            modifier = Modifier
                .padding(3.dp)
                .width(1.dp)
                .height(30.dp)
                .background(Color.Gray)
        )

        Spacer(modifier = Modifier.width(8.dp))


        OutlinedTextField(
            value = "",
            onValueChange = {},
            label = {
                Text(text = "닉네임 #게임태그를 입력해주세요", style = TextStyle(
                    fontSize = 12.sp, color = Color.LightGray
                )
                ) },
            trailingIcon = {  // 검색 아이콘
                IconButton(onClick = { /* 검색 동작 */ }) {
                    Icon(
                        imageVector = Icons.Filled.Search,
                        contentDescription = "Search Icon",
                        tint = Color.Black,
                        modifier = Modifier.size(36.dp)
                    )
                }
            },
            modifier = Modifier.weight(1f),
            colors = androidx.compose.material3.TextFieldDefaults.outlinedTextFieldColors(
                containerColor = Color.Transparent, // 배경색 투명
                focusedBorderColor = Color.White, // 포커스 시 테두리 색상
                unfocusedBorderColor = Color.White // 비포커스 시 테두리 색상
            ),
            maxLines = 1

        )

        Spacer(modifier = Modifier.width(8.dp))
    }
}


//"핫플레이어" 박스
@Composable
fun SectionTitle(title: String, modifier: Modifier) {
    Box(
        modifier = modifier
            .padding(4.dp)
            .background(Color.White, shape = RoundedCornerShape(16.dp)) // 배경색과 둥근 테두리 설정
            .clip(RoundedCornerShape(16.dp)) // 클립 모양을 둥글게 설정
            .wrapContentSize() // 텍스트 크기에 맞추어 Box 크기 조정
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.bodyLarge.copy(
                color = Color.Black,
                fontWeight = FontWeight.Bold,
                fontSize = 12.sp
            ),
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp), // 텍스트 주변 패딩 추가
            textAlign = TextAlign.Start
        )
    }
}


// 플레이어 card
@Composable
fun SummonerItem(
    summonerName: String,
    summonerTier: String,
    summonerChamRes: Int,
    tierIconRes: Int,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {

    Card(
        shape = RoundedCornerShape(16.dp),
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 2.dp)
            .clickable { onClick() },
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()  // Card의 크기를 채우도록 설정
                .height(100.dp)
        ) {
            // 배경 챔피언 설정
            Image(
                painter = painterResource(summonerChamRes),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(3.0f) // 비율로 윗부분만 자르기
                    .alpha(0.7f)
                    .clip(RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp)),
                contentScale = ContentScale.Crop // 이미지를 Card 크기에 맞게 자르기
            )


            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                // 티어 아이콘
                Image(
                    painter = painterResource(id = tierIconRes),
                    contentDescription = null,
                    modifier = Modifier.size(90.dp)
                )

                Spacer(modifier = Modifier.width(16.dp))

                // 플레이어 이름, 플레이어 티어
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
                            color = Color.Black
                        )
                    )


                }

                Spacer(modifier = Modifier.width(8.dp))

            }
        }
    }
}