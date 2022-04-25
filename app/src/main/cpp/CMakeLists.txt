project(android-ncnn-ml)


# Sets the minimum version of CMake required to build your native library.
# This ensures that a certain set of CMake features is available to
# your build.

cmake_minimum_required(VERSION 3.10)

set(CMAKE_CXX_FLAGS "${CMAKE_CXX_FLAGS} -fopenmp")
set(CMAKE_C_FLAGS "${CMAKE_C_FLAGS} -fopenmp")
if(DEFINED ANDROID_NDK_MAJOR AND ${ANDROID_NDK_MAJOR} GREATER 20)
    set(CMAKE_SHARED_LINKER_FLAGS "${CMAKE_SHARED_LINKER_FLAGS} -static-openmp")
endif()

# Specify directory of the ncnn file
add_library(ncnn STATIC IMPORTED)
find_library(log-lib log)
find_library(android-lib android)
find_library(vulkan-lib vulkan)
set_target_properties( # Specifies the target library.
        ncnn
        # Specifies the parameter you want to define.
        PROPERTIES IMPORTED_LOCATION
        # Provides the path to the library you want to import.
        ${CMAKE_SOURCE_DIR}/libs/ncnn/${ANDROID_ABI}/libncnn.a )





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


