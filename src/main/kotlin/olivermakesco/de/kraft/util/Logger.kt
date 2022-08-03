package olivermakesco.de.kraft.util

object Logger {
    fun info(message: String) {
        log("[Kraft/INFO] $message")
    }

    fun warn(message: String) {
        log("[Kraft/WARN] $message")
    }

    fun error(message: String) {
        log("[Kraft/ERROR] $message")
    }

    fun debug(message: String) {
        log("[Kraft/DEBUG] $message")
    }

    private fun log(message: String) {
        println(message)
        TODO("Log to file")
    }
}
