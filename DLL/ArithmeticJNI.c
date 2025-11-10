
#include <jni.h>
#include "ArithmeticJNI.h"

JNIEXPORT jint JNICALL Java_ArithmeticJNI_add
  (JNIEnv *env, jobject obj, jint a, jint b) {
    return a + b;
}

JNIEXPORT jint JNICALL Java_ArithmeticJNI_subtract
  (JNIEnv *env, jobject obj, jint a, jint b) {
    return a - b;
}

JNIEXPORT jint JNICALL Java_ArithmeticJNI_multiply
  (JNIEnv *env, jobject obj, jint a, jint b) {
    return a * b;
}

JNIEXPORT jdouble JNICALL Java_ArithmeticJNI_divide
  (JNIEnv *env, jobject obj, jint a, jint b) {
    if (b == 0) {
       
        return 0.0;
    }
    return (double)a / (double)b;
}
