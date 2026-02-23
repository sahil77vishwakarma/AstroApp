package com.capital.motion.clotho.ui.commonComposable

import android.R.attr.text
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import com.capital.motion.clotho.R
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.foundation.clickable
import androidx.compose.material3.Icon
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.capital.motion.clotho.ui.theme.Black
import com.capital.motion.clotho.ui.theme.ClothoTheme
import com.capital.motion.clotho.ui.theme.cardBg
import com.capital.motion.clotho.ui.theme.cardBlackBg
import com.capital.motion.clotho.ui.theme.creditBg
import com.capital.motion.clotho.ui.theme.greyBg
import com.capital.motion.clotho.ui.theme.greyInfo
import com.capital.motion.clotho.ui.theme.greySubTitle
import com.capital.motion.clotho.ui.theme.white
import com.capital.motion.clotho.ui.theme.yellowBg

@Composable
fun MaxWidthCard(title : String, subTitle: String,credits : Int, info : String,new : Boolean) {

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 7.dp, vertical = 15.dp)
            .clip(RoundedCornerShape(14.dp))
            .border(
                width = 1.5.dp,
                color = cardBg,
                shape = RoundedCornerShape(14.dp)
            )
            .background(color = cardBg)

    ) {


        Column(
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(top = 12.dp, end = 16.dp),
            horizontalAlignment = Alignment.End
        ) {

            Text(
                text = "$credits credits",
                fontSize = 12.sp,
                fontFamily = FontFamily(Font(R.font.semi_bold)),
                color = greyInfo,
                modifier = Modifier
                    .background(
                        color = creditBg,
                        shape = RoundedCornerShape(50)
                    )
                    .padding(horizontal = 8.dp, vertical = 4.dp)
            )
            if (new) {

                Spacer(modifier = Modifier.height(6.dp))


                Text(
                    text = "NEW",
                    fontSize = 12.sp,
                    fontFamily = FontFamily(Font(R.font.semi_bold)),
                    color = Black,
                    modifier = Modifier
                        .background(
                            color = yellowBg,
                            shape = RoundedCornerShape(4.dp)
                        )
                        .padding(horizontal = 8.dp, vertical = 4.dp)
                )
            }
        }




        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp, vertical = 17.dp)
                .padding(end = 80.dp)
        ) {

            Row() {

                Text(
                    text = "$title",
                    style = TextStyle(
                        fontSize = 28.sp,
                        fontFamily = FontFamily(Font(R.font.medium)),
                        textAlign = TextAlign.Start,
                        color = Black

                    )

                )


            }

            Spacer(modifier = Modifier.height(10.dp))

            Text(
                text = "$subTitle",
                style = TextStyle(
                    fontSize = 24.sp,
                    fontFamily = FontFamily(Font(R.font.medium)),
                    color = greySubTitle
                )

            )

            Spacer(modifier = Modifier.height(6.dp))

            Text(
                text = "$info",
                style = TextStyle(
                    fontSize = 22.sp,
                    fontFamily = FontFamily(Font(R.font.medium)),
                    color = greyInfo
                )

            )
        }

    }
}


@Composable
fun MinWidthCard(title : String, subTitle: String,credits : Int, info : String,date : Boolean,data : String,  modifier: Modifier = Modifier) {


    Box(
        modifier
            .padding(horizontal = 7.dp, vertical = 10.dp)
            .clip(RoundedCornerShape(14.dp))
            .border(
                width = 1.5.dp,
                color = cardBg,
                shape = RoundedCornerShape(14.dp)
            )
            .background(color = cardBg)

    ) {
        Text(
            text = "$credits credits",
            style = TextStyle(
                fontSize = 10.sp,
                fontFamily = FontFamily(Font(R.font.semi_bold)), color = greyInfo
            ),
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(top = 12.dp, end = 16.dp)
                .background(color = creditBg, shape = RoundedCornerShape(50))
                .padding(horizontal = 8.dp, vertical = 6.dp)
        )


        Column {

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp, vertical = 17.dp)
                    .padding(end = 10.dp)
            ) {

                Row() {

                    Text(
                        text = "$title",
                        style = TextStyle(
                            fontSize = 18.sp,
                            fontFamily = FontFamily(Font(R.font.medium)),
                            textAlign = TextAlign.Start,
                            color = Black

                        )

                    )


                }

                if (date) {
                    Spacer(modifier = Modifier.height(4.dp))

                    Text(
                        text = "$data",
                        style = TextStyle(
                            fontSize = 12.sp,
                            fontFamily = FontFamily(Font(R.font.medium)),
                            color = greyInfo
                        )

                    )
                }

                Spacer(modifier = Modifier.height(10.dp))

                Text(
                    text = "$subTitle",
                    style = TextStyle(
                        fontSize = 15.sp,
                        fontFamily = FontFamily(Font(R.font.medium)),
                        color = greySubTitle
                    )

                )

                Spacer(modifier = Modifier.height(6.dp))

                Text(
                    text = "$info",
                    style = TextStyle(
                        fontSize = 15.sp,
                        fontFamily = FontFamily(Font(R.font.medium)),
                        color = greyInfo
                    )

                )
            }



            if (date) {
                Spacer(modifier = Modifier.height(6.dp))
                HorizontalDivider(
                    color = Black,
                    thickness = 1.dp
                )

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 10.dp)
                ) {

                    Text(
                        text = "Dec",
                        fontSize = 12.sp,
                        fontFamily = FontFamily(Font(R.font.medium)),
                        color = Black,
                        modifier = Modifier
                            .weight(1f),
                        textAlign = TextAlign.Center
                    )

                    Text(
                        text = "Jan",
                        fontSize = 12.sp,
                        fontFamily = FontFamily(Font(R.font.medium)),
                        color = Black,
                        modifier = Modifier
                            .weight(1f),
                        textAlign = TextAlign.Center
                    )
                }
                }
            }

        }
    }

@Composable
fun MaxWidthGreyCard() {


    Box(
        modifier = Modifier
            .padding(horizontal = 7.dp, vertical = 10.dp)
            .clip(RoundedCornerShape(14.dp))
            .border(
                width = 1.5.dp,
                color = greyBg,
                shape = RoundedCornerShape(14.dp)
            )
            .background(cardBlackBg)

    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 10.dp, vertical = 15.dp)

        ) {

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {

                Text(
                    text = "YOU.",
                    style = TextStyle(
                        fontSize = 28.sp,
                        fontFamily = FontFamily(Font(R.font.medium)),
                        color = white
                    )
                )

                Image(
                    painter = painterResource(id = R.drawable.ic_edit),
                    contentDescription = "Edit",
                    modifier = Modifier.size(24.dp)
                )

            }

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = "Personal Details",
                style = TextStyle(
                    fontSize = 20.sp,
                    fontFamily = FontFamily(Font(R.font.medium)),
                    color = white
                )
            )

            Spacer(modifier = Modifier.height(5.dp))

            Row  {

                Text(
                    text = "Taurus",
                    style = TextStyle(
                        fontSize = 15.sp,
                        fontFamily = FontFamily(Font(R.font.medium)),
                        color = white
                    )
                )

                VerticalDivider(
                    modifier = Modifier
                        .height(18.dp)
                        .padding(horizontal = 8.dp),
                    color = white,
                    thickness = 1.dp
                )


                Text(
                    text = "Scorpio Moon",
                    style = TextStyle(
                        fontSize = 15.sp,
                        fontFamily = FontFamily(Font(R.font.medium)),
                        color = white
                    )
                )

                VerticalDivider(
                    modifier = Modifier
                        .height(18.dp)
                        .padding(horizontal = 8.dp),
                    color = white,
                    thickness = 1.dp
                )

                Text(
                    text = "Leo Rising",
                    style = TextStyle(
                        fontSize = 15.sp,
                        fontFamily = FontFamily(Font(R.font.medium)),
                        color = white
                    )
                )
            }
        }
    }
}



@Composable
fun PersonalInfoCard(
    sunSign: String,
    moonSign: String,
    risingSign: String,
    date: String,
    time: String,
    location: String,
    modifier: Modifier = Modifier,
    onEditClick: () -> Unit = {},
    onInfoClick: () -> Unit = {}
) {

    Box(
        modifier = modifier
            .clip(RoundedCornerShape(14.dp))
            .border(
                width = 1.5.dp,
                color = greyBg,
                shape = RoundedCornerShape(14.dp)
            )
            .background(cardBlackBg)
    ) {

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 14.dp, vertical = 16.dp)
        ) {

            // 🔹 Header row
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {

                Column {
                    Text(
                        text = "YOU.",
                        fontSize = 26.sp,
                        fontFamily = FontFamily(Font(R.font.medium)),
                        color = white
                    )

                    Text(
                        text = "Personal Details",
                        fontSize = 18.sp,
                        fontFamily = FontFamily(Font(R.font.medium)),
                        color = white
                    )
                }

                Row {
                    Image(
                        painter = painterResource(R.drawable.ic_edit),
                        contentDescription = "Edit",
                        modifier = Modifier
                            .size(22.dp)
                            .clickable { onEditClick() }
                    )

                    Spacer(modifier = Modifier.width(12.dp))

                    Image(
                        painter = painterResource(R.drawable.ic_info),
                        contentDescription = "Info",
                        modifier = Modifier.size(24.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            // 🔹 Zodiac row
            Row(verticalAlignment = Alignment.CenterVertically) {

                Text(sunSign, color = white, fontSize = 15.sp)
                VerticalDivider(Modifier
                    .height(16.dp)
                    .padding(horizontal = 8.dp), color = white)

                Text("$moonSign Moon", color = white, fontSize = 15.sp)
                VerticalDivider(Modifier
                    .height(16.dp)
                    .padding(horizontal = 8.dp), color = white)

                Text("$risingSign Rising", color = white, fontSize = 15.sp)
            }

            Spacer(modifier = Modifier.height(10.dp))

            // 🔹 Date / Time / Location row
            Row(verticalAlignment = Alignment.CenterVertically) {

                Text(date, color = greyInfo, fontSize = 13.sp)
                VerticalDivider(Modifier
                    .height(14.dp)
                    .padding(horizontal = 8.dp), color = greyInfo)

                Text(time, color = greyInfo, fontSize = 13.sp)
                VerticalDivider(Modifier
                    .height(14.dp)
                    .padding(horizontal = 8.dp), color = greyInfo)

                Text(location, color = greyInfo, fontSize = 13.sp)
            }
        }
    }
}

@Composable
fun ClothoInfoCard(
    title: String = "CLOTHO.",
    subtitle: String = "Personalized Astrology",
    description: String = "Real astrologers wrote it, AI delivers it.",
    modifier: Modifier = Modifier,
    onInfoClick: () -> Unit = {}
) {

    Box(
        modifier = modifier
            .clip(RoundedCornerShape(14.dp))
            .border(
                width = 1.5.dp,
                color = greyBg,
                shape = RoundedCornerShape(14.dp)
            )
            .background(cardBlackBg)
    ) {

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 14.dp, vertical = 16.dp)
        ) {

            // 🔹 Header
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {

                Column {
                    Text(
                        text = title,
                        fontSize = 26.sp,
                        fontFamily = FontFamily(Font(R.font.medium)),
                        color = white
                    )

                    Text(
                        text = subtitle,
                        fontSize = 18.sp,
                        fontFamily = FontFamily(Font(R.font.medium)),
                        color = white
                    )
                }

                Image(
                    painter = painterResource(id = R.drawable.ic_info),
                    contentDescription = "Info",
                    modifier = Modifier
                        .size(22.dp)
                        .clickable { onInfoClick() }
                )
            }

            Spacer(modifier = Modifier.height(12.dp))

            // 🔹 Description
            Text(
                text = description,
                fontSize = 15.sp,
                fontFamily = FontFamily(Font(R.font.medium)),
                color = greyInfo
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
fun CardsPreview() {
    ClothoTheme {
//        PersonalInfoCard(
//            sunSign = "Taurus",
//            moonSign = "Scorpio",
//            risingSign = "Leo",
//            date = "January 15, 1990",
//            time = "12:00 AM",
//            location = "London, UK",
//            modifier = Modifier
//                .fillMaxWidth()
//                .padding(horizontal = 7.dp, vertical = 10.dp)
//        )


        ClothoInfoCard(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 7.dp, vertical = 10.dp),
            onInfoClick = {
                // TODO: show info dialog / bottom sheet
            }
        )

//        MaxWidthGreyCard()
    }
}