#include <stdio.h>
#include "B1.h"
#include <jni.h>

JNIEXPORT jint JNICALL Java_B1_add(JNIEnv *env, jobject obj, jint num1, jint num2) {
    return num1 + num2;
}

JNIEXPORT jint JNICALL Java_B1_sub(JNIEnv *env, jobject obj, jint num1, jint num2) {
    return num1 - num2;
}

JNIEXPORT jint JNICALL Java_B1_mul(JNIEnv *env, jobject obj, jint num1, jint num2) {
    return num1 * num2;
}

JNIEXPORT jdouble JNICALL Java_B1_div(JNIEnv *env, jobject obj, jint num1, jint num2) {
    return (jdouble) num1 / num2;
}
