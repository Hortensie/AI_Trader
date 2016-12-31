#include <jni.h>
#include <string>
#include <iostream>


extern "C"
jstring
Java_com_vaadin_polymer_demo_client_sampler_ai_1trader_MainActivity_stringFromJNI(
        JNIEnv *env,
        jobject /* this */) {
    std::string hello = "Hello from C++";
    return env->NewStringUTF(hello.c_str());
}
