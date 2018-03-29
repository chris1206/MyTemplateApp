package com.yto.template;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;


import org.junit.Test;
import org.junit.runner.RunWith;



import static org.junit.Assert.assertEquals;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    @Test
    public void useAppContext() throws Exception {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();

        assertEquals("com.yto.template.mytemplateapp", appContext.getPackageName());
    }

//    @Test
//    public void javapoet_test(){
//        MethodSpec main = MethodSpec.methodBuilder("main")
//                .addCode(""
//                        + "int total = 0;\n"
//                        + "for (int i = 0; i < 10; i++) {\n"
//                        + "  total += i;\n"
//                        + "}\n")
//                .build();
//    }
}
