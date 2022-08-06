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
        // *chuckles* I'm in danger
        GLFWErrorCallback.createPrint(System.err).set()
        // Start glfw
        if (!glfwInit()) error("Unable to init glfw")
        // Make window
        window = glfwCreateWindow(width, height, name, NULL, NULL)
        // Wait, was the window created???
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
                // Trivago
                glBegin(GL_TRIANGLES) {
                    glVertex3f(0F, 0F, 0F)
                    glVertex3f(1F, 0F, 1F)
                    glVertex3f(1F, 0F, 0F)
                }

                // Swap draw buffers
                glfwSwapBuffers(window)

                // Run an election
                glfwPollEvents()
            }
            // Perfectly balanced, as all things should be.
            glfwDestroyWindow(window)
            glfwTerminate()
            // TODO: halt other running parts of the program before exiting

            exitProcess(0)
        }
    }

    // OpenGL more like OpenGetSomeBitches
    fun glBegin(mode: Int, action: () -> Unit) {
        glBegin(mode)
        action()
        glEnd()
    }
}