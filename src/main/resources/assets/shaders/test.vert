#version 450

vec2 testVerts[3] = {
    vec2(0.0,0.0),
    vec2(0.5,0.0),
    vec2(0.0,0.75)
};

void main() {
    gl_Position = vec4(testVerts[gl_VertexIndex], 0.0, 1.0);
}