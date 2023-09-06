import org.lwjgl.system.MemoryStack
import org.lwjgl.util.tinyfd.TinyFileDialogs

fun main(args: Array<String>) {
    println("Hello World!")

    // Try adding program arguments via Run/Debug configuration.
    // Learn more about running applications: https://www.jetbrains.com/help/idea/running-applications.html.
    println("Program arguments: ${args.joinToString()}")

    val fileExtension = "json"

    val result = MemoryStack.stackPush().use { stack ->
        val filters = if (fileExtension.isNotEmpty()) fileExtension.split(",") else emptyList()
        val aFilterPatterns = stack.mallocPointer(filters.size)
        filters.forEach {
            aFilterPatterns.put(stack.UTF8("*.$it"))
        }
        aFilterPatterns.flip()
        TinyFileDialogs.tinyfd_openFileDialog(
            "Choose File",
            null,
            aFilterPatterns,
            null,
            false
        )
    }

    println(result)
}