#include <jni.h>
#include <string>
#include <iostream>
#include <math.h>


extern "C"
jstring
Java_com_hortensie_ai_1trader_dbTester_model_FireBaseModel_calculateArea(
        JNIEnv *env,
        jobject self,// this
        jdouble radius)
{
    jdouble area = M_PI * radius * radius;
    char output[40];
    sprintf(output,"RxJava, The area is %f sqm",area);
    //std::string hello = "Hello from C++";
    //return env->NewStringUTF(hello.c_str());
    return  env->NewStringUTF(output);

}
