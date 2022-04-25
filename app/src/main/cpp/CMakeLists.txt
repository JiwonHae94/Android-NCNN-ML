project(android-ncnn-ml)


# Sets the minimum version of CMake required to build your native library.
# This ensures that a certain set of CMake features is available to
# your build.

cmake_minimum_required(VERSION 3.10)
set(CMAKE_CXX_FLAGS "${CMAKE_CXX_FLAGS} -std=c++11  -Werror -D VK_USE_PLATFORM_ANDROID_KHR")
set(ncnn_DIR ${CMAKE_SOURCE_DIR}/libs/ncnn-vulkan/${ANDROID_ABI}/lib/cmake/ncnn)
find_package(ncnn REQUIRED)

# Specifies a library name, specifies whether the library is STATIC or
# SHARED, and provides relative paths to the source code. You can
# define multiple libraries by adding multiple add_library() commands,
# and CMake builds them for you. When you build your app, Gradle
# automatically packages shared libraries with your APK.

find_library( # Sets the name of the path variable.
        log-lib

        # Specifies the name of the NDK library that
        # you want CMake to locate.
        log)

add_library(YoloXNCNN SHARED yoloXncnn_jni.cpp)

target_link_libraries(
        YoloXNCNN
        ncnn
        jnigraphics
)

