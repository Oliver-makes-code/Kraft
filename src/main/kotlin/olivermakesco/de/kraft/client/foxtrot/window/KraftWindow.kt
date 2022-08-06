package olivermakesco.de.kraft.client.foxtrot.window

import olivermakesco.de.kraft.client.KraftClient
import org.lwjgl.glfw.*
import org.lwjgl.glfw.GLFW.*
import org.lwjgl.opengl.GL
import org.lwjgl.opengl.GL11
import org.lwjgl.opengl.GL11.*
import org.lwjgl.opengl.GL11C.GL_COLOR_BUFFER_BIT
import org.lwjgl.system.MemoryStack.*
import org.lwjgl.system.MemoryUtil.*
import kotlin.concurrent.thread
import kotlin.system.exitProcess


// Type alias' to know which variables mean what
typealias Window = Long

class KraftWindow(width: Int, height: Int, name: String, private val client: KraftClient) {
    private val window: Window
    private val stack = stackPush()

    init {
        // Errors
        GLFWErrorCallback.createPrint(System.err).set()
        // Init glfw
        if (!glfwInit()) error("Unable to init glfw")
        // Make window
        window = glfwCreateWindow(width, height, name, NULL, NULL)
        // Check if window
        if (window == NULL) error("Unable to create window")
    }

    fun startThread() {
        thread(name="Rendering") {
            // Make window current
            glfwMakeContextCurrent(window)
            // Enable v-sync TODO: make this toggleable
            glfwSwapInterval(1)
            GL.createCapabilities()
            while (!glfwWindowShouldClose(window)) {
                glBegin(GL_TRIANGLES) {
                    glVertex2f(0F, 0F)
                    glVertex2f(1F, 0F)
                    glVertex2f(0F, 1F)
                }

                glfwSwapBuffers(window)
                glfwPollEvents()
            }
            glfwDestroyWindow(window)
            glfwTerminate()
            // TODO: halt other running parts of the program before exiting

            exitProcess(0)
        }
    }

    fun glBegin(mode: Int, action: () -> Unit) {
        glBegin(mode)
        action()
        glEnd()
    }
}