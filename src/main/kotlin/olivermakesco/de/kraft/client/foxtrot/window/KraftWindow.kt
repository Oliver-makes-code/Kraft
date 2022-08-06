package olivermakesco.de.kraft.client.foxtrot.window

import org.lwjgl.glfw.GLFW.*
import org.lwjgl.system.MemoryUtil.*
import java.nio.IntBuffer
import kotlin.concurrent.thread

// Declare typealias to easily know what it's supposed to be
typealias GLFWWindow = Long

class KraftWindow(width: Int, height: Int, name: String) {
    // Width getter/setter
    var width: Int
        get() {
            // Create a buffer with length of 1
            val wBuffer = IntBuffer.allocate(1)
            // Write to the buffer
            glfwGetWindowSize(window, wBuffer, IntBuffer.allocate(1))
            // Return the value from the buffer
            return wBuffer[0]
        }
        set(value) {
            // Set the width and height
            glfwSetWindowSize(window, value, height)
        }
    // Width getter/setter
    var height: Int
        get() {
            // Create a buffer with length of 1
            val hBuffer = IntBuffer.allocate(1)
            // Write to the buffer
            glfwGetWindowSize(window, IntBuffer.allocate(1), hBuffer)
            // Return the value from the buffer
            return hBuffer[0]
        }
        set(value) {
            // Set the width and height
            glfwSetWindowSize(window, width, value)
        }
    // Internal variable to store window name
    internal var internalName: String = ""
    // Window name getter/setter
    var name: String
        get() {
            // Return internal window name
            return internalName
        }
        set(value) {
            // Set internal window name
            internalName = value
            // Set actual window name
            glfwSetWindowTitle(window, value)
        }

    val window: GLFWWindow

    init {
        // Init glfw
        glfwInit()

        // No more OpenGL! No more context! No more OpenGl context!
        glfwWindowHint(GLFW_CLIENT_API, GLFW_NO_API)

        // Store window name
        internalName = name

        // Create window
        window = glfwCreateWindow(width, height, name, NULL, NULL)
    }

    fun startThread() {
        // Starts thread to handle window events
        thread(name="Rendering") {
            // Check if window should close
            while (!glfwWindowShouldClose(window)) {
                // Poll glfw events
                glfwPollEvents()
                // TODO: Render things here
            }
            // Window should close now, so kill it
            destroy()
        }
    }

    fun destroy() {
        // Kill window and glfw
        glfwDestroyWindow(window)
        glfwTerminate()
    }
}