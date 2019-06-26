package com.app.navigator.compiler;

import com.app.navigator.api.IServicesExtension;
import com.app.navigator.api.ModuleServices;
import com.app.navigator.api.annotation.NavService;
import com.google.auto.service.AutoService;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeName;
import com.squareup.javapoet.TypeSpec;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
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
import javax.lang.model.util.Elements;
import javax.lang.model.util.Types;
import javax.tools.Diagnostic;

/**
 * @Auther JiRui
 * @CreateTime 2019/5/14 14:28
 * @Describe
 */
@AutoService(Processor.class)
public class ModuleProcessor extends AbstractProcessor {
    private static final String SUFFIX = "$$extension";
    private static final char CHAR_DOT = '.';
    private Map<String, String> annotatedElementMap = new LinkedHashMap<>();
    private Messager messager;
    private Types typesUtil;
    private Elements elementsUtil;
    private Filer filer;

    @Override
    public synchronized void init(ProcessingEnvironment processingEnvironment) {
        super.init(processingEnvironment);
        messager = processingEnv.getMessager();
        typesUtil = processingEnv.getTypeUtils();
        elementsUtil = processingEnv.getElementUtils();
        filer = processingEnv.getFiler();
    }

    @Override
    public SourceVersion getSupportedSourceVersion() {
        return SourceVersion.latestSupported();
    }

    @Override
    public Set<String> getSupportedAnnotationTypes() {
        Set<String> annotataions = new LinkedHashSet<>();
        annotataions.add(NavService.class.getCanonicalName());
        return annotataions;
    }

    @Override
    public boolean process(Set<? extends TypeElement> set, RoundEnvironment roundEnvironment) {
        annotatedElementMap.clear();
        messager.printMessage(Diagnostic.Kind.ERROR, "121212");
        for (Element element : roundEnvironment.getElementsAnnotatedWith(NavService.class)) {
            if (element.getKind() != ElementKind.CLASS) {
                messager.printMessage(Diagnostic.Kind.ERROR, "Only CLASS can be annotated with @%s");
            }
            TypeElement encloseElement = (TypeElement) element.getEnclosingElement();

            String fullClassName = encloseElement.getQualifiedName().toString();
            NavService navService = encloseElement.getAnnotation(NavService.class);
            String moduleName = navService.name();

            if (fullClassName != null && !fullClassName.equals("")) {
                annotatedElementMap.put(moduleName, fullClassName);
            }
        }

        if (annotatedElementMap.size() == 0) {
            return true;
        }
        try {
            MethodSpec methodSpec = createMethod("getModuleServices");
            TypeSpec binder = createClass(getClassName(IServicesExtension.class.getSimpleName() + SUFFIX), methodSpec);
            JavaFile javaFile = JavaFile.builder("com.app.navigator.compiler", binder).build();
            javaFile.writeTo(filer);

        } catch (IOException e) {
            messager.printMessage(Diagnostic.Kind.ERROR, "Error on creating java file");
        }
        return true;
    }

    private MethodSpec createMethod(String methodName) {
        MethodSpec.Builder builder = MethodSpec.methodBuilder(methodName)
                .addModifiers(Modifier.PUBLIC)
                .returns(Map.class);
        for (int i = 0; i < annotatedElementMap.size(); i++) {
            addStatement(builder, annotatedElementMap.get(i));
        }
        builder.addStatement("return new $T()",Map.class);
        return builder.build();
    }

    private void addStatement(MethodSpec.Builder builder, String fullClassName) {
        builder.addStatement("try{");
        builder.addStatement(fullClassName+".class.newInstance();");
        builder.addStatement(" }catch (Exception e){");
        builder.addStatement(" e.printStackTrace();");
        builder.addStatement("}");
    }

    private TypeSpec createClass(String className, MethodSpec methodSpec) {
        return TypeSpec.classBuilder(className + SUFFIX)
                .addModifiers(Modifier.PUBLIC)
                .addMethod(methodSpec)
                .build();
    }

    private String getPackage(String qualifier) {
        return qualifier.substring(0, qualifier.lastIndexOf(CHAR_DOT));
    }

    private String getClassName(String qualifier) {
        return qualifier.substring(qualifier.lastIndexOf(CHAR_DOT) + 1);
    }
}
