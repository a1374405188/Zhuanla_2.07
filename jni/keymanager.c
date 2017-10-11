#include <jni.h>

char* getByte3()
{
	char * c="asdfcvreew";

    return c;
}
char* getByte2()
{
	char * c="26xffg5s44";

    return c;
}
char* getByte1()
{
	char  c[]={'a','\0'};

    return c;
}
static unsigned char c[24];
unsigned char* getByte()
{
	unsigned char *c1="qwertyuiopasdfghjklzxcvbnm'\0'";
	unsigned char *c2="QWERTYUIOPASDFGHJKLZXCVBNM'\0'";
	unsigned char *c3="0123456789'\0'";
	int i;
    for(i=0;i<8;i++)
    {
       c[i]=c1[i+1];
    }
    for(i=8;i<16;i++)
     {
        c[i]=c2[i-8+2];
     }
    for(i=16;i<24;i++)
     {
        c[i]=c3[i-16+1];
     }
    return c;
}
jbyteArray
Java_com_beikbank_android_jni_JniManager_getKey(JNIEnv* env,
        jobject thiz)
{
     jbyteArray jba =(*env)->NewByteArray(env,24);
     unsigned char *byte=getByte();
     (*env)->SetByteArrayRegion(env,jba,0,24,byte);
     return jba;
}



