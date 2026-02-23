package com.capital.motion.clotho.ui.commonComposable

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import com.capital.motion.clotho.R
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.capital.motion.clotho.ui.theme.Black
import com.capital.motion.clotho.ui.theme.ClothoTheme
import com.capital.motion.clotho.ui.theme.cardBg
import com.capital.motion.clotho.ui.theme.creditBg
import com.capital.motion.clotho.ui.theme.greyInfo
import com.capital.motion.clotho.ui.theme.greySubTitle
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
            modifier = Modifier.fillMaxWidth()
                .padding(horizontal = 20.dp, vertical = 17.dp)
                .padding(end = 80.dp)
        ) {

            Row() {

                Text(
                    text = "$title",
                    style = TextStyle(
                        fontSize = 25.sp,
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
                    fontSize = 25.sp,
                    fontFamily = FontFamily(Font(R.font.medium)),
                    color = greySubTitle
                )

            )

            Spacer(modifier = Modifier.height(6.dp))

            Text(
                text = "$info",
                style = TextStyle(
                    fontSize = 25.sp,
                    fontFamily = FontFamily(Font(R.font.medium)),
                    color = greyInfo
                )

            )
        }

    }
}


@Composable
fun MinWidthCard(title : String, subTitle: String,credits : Int, info : String,date : Boolean,data : String) {


    Box(
        modifier = Modifier
            .width(250.dp)
            .padding(horizontal = 7.dp, vertical = 15.dp)
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
                fontSize = 12.sp,
                fontFamily = FontFamily(Font(R.font.semi_bold)), color = greyInfo
            ),
            modifier = Modifier.align(Alignment.TopEnd)
                .padding(top = 12.dp, end = 16.dp)
                .background(color = creditBg, shape = RoundedCornerShape(50))
                .padding(horizontal = 8.dp, vertical = 6.dp)
        )


        Column {

            Column(
                modifier = Modifier.fillMaxWidth()
                    .padding(horizontal = 20.dp, vertical = 17.dp)
                    .padding(end = 80.dp)
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
                        fontSize = 12.sp,
                        fontFamily = FontFamily(Font(R.font.medium)),
                        color = greySubTitle
                    )

                )

                Spacer(modifier = Modifier.height(6.dp))

                Text(
                    text = "$info",
                    style = TextStyle(
                        fontSize = 9.sp,
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



@Preview(showBackground = true)
@Composable
fun CardsPreview() {
    ClothoTheme {
        MinWidthCard("Monthly Cycle","Lunar Return Forecast",5,"Your emotional landscape forecast.",true,"December (12/12)")
        /*MaxWidthCard(
        "Your Pattern",
            "Natal Chart Analysis",
            5,
            "Your cosmic blueprint at the moment of your birth, revealing personality and destiny.",
            false
            )*/
    }
}