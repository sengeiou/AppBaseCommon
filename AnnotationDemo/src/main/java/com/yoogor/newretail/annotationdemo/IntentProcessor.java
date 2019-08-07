package com.yoogor.newretail.annotationdemo;

import com.google.auto.service.AutoService;
import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.ParameterSpec;
import com.squareup.javapoet.ParameterizedTypeName;
import com.squareup.javapoet.TypeSpec;
import com.yoogor.newretail.annotationdemo.annotation.IntentKey;


import java.io.IOException;
import java.util.Collections;
import java.util.Map;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Filer;
import javax.annotation.processing.Messager;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.TypeElement;
import javax.lang.model.element.VariableElement;
import javax.lang.model.util.Elements;
import javax.lang.model.util.Types;

/**
 * @Auther JiRui
 * @CreateTime 2019/8/6 9:00
 * @Describe 自定义注解处理  https://blog.csdn.net/wuyuxing24/article/details/81139846
 * JavaPoet  https://blog.csdn.net/l540675759/article/details/82931785
 */
@AutoService(Processor.class)
public class IntentProcessor extends AbstractProcessor {

    private Types typeUtils;
    private Elements elementUtils;
    private Filer filer;
    private Messager messager;

    /**
     * 每个Annotation Processor必须有一个空的构造函数。
     * 编译期间，init()会自动被注解处理工具调用，并传入ProcessingEnvironment参数，
     * 通过该参数可以获取到很多有用的工具类（Element，Filer，Messager等）
     */
    @Override
    public synchronized void init(ProcessingEnvironment processingEnvironment) {
        super.init(processingEnvironment);
        typeUtils = processingEnv.getTypeUtils();
        elementUtils = processingEnv.getElementUtils();
        filer = processingEnv.getFiler();
        messager = processingEnv.getMessager();
    }

    /**
     * 用于指定自定义注解处理器(Annotation Processor)是注册给哪些注解的(Annotation),
     * 注解(Annotation)指定必须是完整的包名+类名
     */
    @Override
    public Set<String> getSupportedAnnotationTypes() {
//        return super.getSupportedAnnotationTypes();
        return Collections.singleton(IntentKey.class.getCanonicalName());


//          或者如下面返回
//        Set<String> annotataions = new LinkedHashSet<>();
//        annotataions.add(IntentKey.class.getCanonicalName());
//        return annotataions;
    }

    /**
     * 支持java的版本
     * 这里支持java_7
     *
     * @return
     */
    @Override
    public SourceVersion getSupportedSourceVersion() {
        return SourceVersion.latestSupported();
    }

    /**
     * @param set
     * @param roundEnvironment
     * @return process为主要方法，在里面处理接收到的所有被注解修饰过的元素，这里是IntentKey修饰过的字段。
     * Annotation Processor扫描出的结果会存储进roundEnvironment中，可以在这里获取到注解内容，编写你的操作逻辑。
     * 注意:process()函数中不能直接进行异常抛出,否则程序会异常崩溃
     */
    @Override
    public boolean process(Set<? extends TypeElement> set, RoundEnvironment roundEnvironment) {
        System.out.println("process1");
        if (set != null && !set.isEmpty()) {
            ParameterizedTypeName parameterizedTypeName = ParameterizedTypeName.get(
                    ClassName.get(Map.class),
                    ClassName.get(String.class),
                    ClassName.get(Integer.class)
            );

            ParameterSpec param = ParameterSpec.builder(parameterizedTypeName, "param").build();

            MethodSpec loadInfo = MethodSpec.methodBuilder("loadInfo")
                    .addModifiers(Modifier.PUBLIC)
                    .addParameter(param)
                    .build();

            TypeSpec loadInfoClass = TypeSpec.classBuilder("LoadInfoClass")
                    .addMethod(loadInfo)
                    .addModifiers(Modifier.PUBLIC)
                    .build();

            JavaFile javaFile = JavaFile.builder("com.yoogor.newretail.annotationtest", loadInfoClass).build();

            try {
                javaFile.writeTo(filer);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return true;//这里返回true表示后续不用再处理了
        }
        return false;//这里返回true表示后续不用再处理了

    }
}
