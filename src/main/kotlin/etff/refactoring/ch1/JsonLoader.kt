package etff.refactoring.ch1
import kotlinx.serialization.json.Json
import java.io.File

class JsonLoader(
    val jsonParser: Json = Json { ignoreUnknownKeys = true },
) {
    inline fun <reified T> load(filePath: String): T {
        val jsonString = File(filePath).readText()
        return jsonParser.decodeFromString(jsonString)
    }
}
