LOCAL_PATH := $(call my-dir)

include $(CLEAR_VARS)

LOCAL_MODULE    := keymanager
LOCAL_SRC_FILES := keymanager.c
include $(BUILD_SHARED_LIBRARY)



#pdf 
include $(CLEAR_VARS)  
LOCAL_MODULE := pdf
LOCAL_SRC_FILES :=$(TARGET_ARCH_ABI)/libvudroid.so
  
include $(PREBUILT_SHARED_LIBRARY)  



#jiguang
include $(CLEAR_VARS)  
LOCAL_MODULE := jiguang
LOCAL_SRC_FILES :=$(TARGET_ARCH_ABI)/libjpush217.so
include $(PREBUILT_SHARED_LIBRARY)



#baidu
#include $(CLEAR_VARS)  
#LOCAL_MODULE := baidu
#LOCAL_SRC_FILES :=$(TARGET_ARCH_ABI)/libbdpush_V2_7.so
#include $(PREBUILT_SHARED_LIBRARY)