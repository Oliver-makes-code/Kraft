package olivermakesco.de.kraft.client.foxtrot.window

import org.lwjgl.glfw.GLFW.*
import org.lwjgl.system.MemoryUtil.*
import kotlin.concurrent.thread

typealias GLFWWindow = Long

class KraftWindow {
    val glfwWindow: GLFWWindow
    init {
        // initialize GLFW and create a window
        glfwInit()

        glfwWindowHint(GLFW_CLIENT_API, GLFW_NO_API)

        glfwWindow = glfwCreateWindow(100, 100, "", NULL, NULL)

        thread(name="Rendering") {
            while (!glfwWindowShouldClose(glfwWindow)) {
                glfwPollEvents()
            }

            glfwDestroyWindow(glfwWindow)

            glfwTerminate()
        }
    }
}