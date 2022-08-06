package olivermakesco.de.kraft.client.foxtrot.window

import io.github.kgpu.*
import io.github.kgpu.kshader.*
import kotlinx.coroutines.runBlocking
import olivermakesco.de.kraft.client.KraftClient
import kotlin.concurrent.thread

class KraftWindow(width: Int, height: Int, name: String, private val client: KraftClient) {
    private val window = run {
        Kgpu.init(true)
        Window()
    }
    private val adapter = runBlocking {
        Kgpu.requestAdapterAsync(window)
    }
    private val device = runBlocking {
        adapter.requestDeviceAsync()
    }
    private val testVertex = runBlocking {
        KShader.init()
        device.createShaderModule(KShader.compile("test.vert", javaClass.getResource("/assets/shaders/test.vert")!!.readText(), KShaderType.VERTEX))
    }
    private val testFragment = runBlocking {
        device.createShaderModule(KShader.compile("test.vert", javaClass.getResource("/assets/shaders/test.frag")!!.readText(), KShaderType.FRAGMENT))
    }

    private val pipelineLayout = device.createPipelineLayout(PipelineLayoutDescriptor())

    private val pipelineDesc = RenderPipelineDescriptor(
        pipelineLayout,
        ProgrammableStageDescriptor(testVertex, "main"),
        ProgrammableStageDescriptor(testFragment, "main"),
        PrimitiveTopology.TRIANGLE_LIST,
        RasterizationStateDescriptor(),
        arrayOf(
            ColorStateDescriptor(
                TextureFormat.BGRA8_UNORM,
                BlendDescriptor(),
                BlendDescriptor(),
                0xF
            )
        ),
        Kgpu.undefined,
        VertexStateDescriptor(null),
        1,
        0xFFFFFFFF,
        false
    )

    private val pipeline = device.createRenderPipeline(pipelineDesc)

    private val swapchainDesc = SwapChainDescriptor(device, TextureFormat.BGRA8_UNORM)

    var swapchain = window.configureSwapChain(swapchainDesc)

    init {
        window.onResize = {
            swapchain = window.configureSwapChain(swapchainDesc)
        }
    }

    fun startThread() {
        thread(name="Rendering") {
            Kgpu.runLoop(window) {
                val swapChainTexture = swapchain.getCurrentTextureView();
                val cmdEncoder = device.createCommandEncoder();

                val colorAttachment = RenderPassColorAttachmentDescriptor(swapChainTexture, Color.WHITE)
                val renderPassEncoder = cmdEncoder.beginRenderPass(RenderPassDescriptor(colorAttachment))
                renderPassEncoder.setPipeline(pipeline)
                renderPassEncoder.draw(3, 1)
                renderPassEncoder.endPass()

                val cmdBuffer = cmdEncoder.finish()
                val queue = device.getDefaultQueue()
                queue.submit(cmdBuffer)
                swapchain.present();
            }
        }
    }
}