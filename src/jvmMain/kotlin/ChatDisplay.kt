import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ChatDisplay(
    replyText: String,
    onSendMessage: (requestText: String) -> Unit,
    modifier: Modifier = Modifier
) {
    var scroll = rememberScrollState(0)
    var requestText by remember { mutableStateOf(TextFieldValue("")) }

    Column(
        modifier = modifier
            .padding(horizontal = 32.dp, vertical = 16.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource("images/OpenAI_Logo.png"),
            contentDescription = "logo",
            modifier = Modifier,
            contentScale = ContentScale.Fit
        )
        Spacer(Modifier.height(16.dp))
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(66, 69, 73)),
        ) {
            Text(
                modifier = Modifier
                    .verticalScroll(scroll),
                text = replyText,
                fontSize = 16.sp,
                color = Color(232, 234, 237),
                fontFamily = FontFamily.Monospace
            )
        }
    }
    Spacer(Modifier.height(16.dp))
    Column(
        modifier = modifier
            .padding(horizontal = 32.dp, vertical = 16.dp),
        verticalArrangement = Arrangement.Bottom,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TextField(
            value = requestText,
            onValueChange = { newRequestText ->
                requestText = newRequestText
            },
            colors = TextFieldDefaults.textFieldColors(
                textColor = Color(232, 234, 237),
                backgroundColor = Color(66, 69, 73)
            ),
            modifier = Modifier
                .fillMaxWidth()
        )
        Spacer(Modifier.height(8.dp))
        Button(
            onClick = { onSendMessage(requestText.text) },
            colors = ButtonDefaults.buttonColors(backgroundColor = Color(88, 101, 242)),
        ) {
            Text(
                text = "Send",
                color = Color(232, 234, 237),
                fontWeight = FontWeight(550),
                fontFamily = FontFamily.SansSerif
            )
        }
    }
}
