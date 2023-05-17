import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.aallam.openai.api.BetaOpenAI
import com.aallam.openai.api.chat.ChatCompletion
import com.aallam.openai.api.chat.ChatCompletionRequest
import com.aallam.openai.api.chat.ChatMessage
import com.aallam.openai.api.chat.ChatRole
import com.aallam.openai.api.model.ModelId
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class Client {
    var replyText by mutableStateOf("")

    private var coroutineScope = CoroutineScope(Dispatchers.Main)

    fun sendMessage(requestText: String) {
        if (requestText.isEmpty()) return

        replyText = "OpenAI is typing..."
        getResponse(requestText)
    }

    @OptIn(BetaOpenAI::class)
    fun getResponse(requestText: String) {
        if (requestText.isEmpty()) return

        coroutineScope.launch {
            val chatCompletionRequest = ChatCompletionRequest(
                model = ModelId("gpt-3.5-turbo"),
                messages = listOf(
                    ChatMessage(
                        role = ChatRole.User,
                        content = requestText
                    )
                )
            )
            val completion: ChatCompletion = openAI.chatCompletion(chatCompletionRequest)
            val message: String = completion.choices.first().message!!.content
            replyText = message
        }
    }
}
