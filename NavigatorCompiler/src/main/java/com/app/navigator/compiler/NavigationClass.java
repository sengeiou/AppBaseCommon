package com.app.navigator.compiler;

import com.app.navigator.api.annotation.NavService;

import javax.lang.model.element.Element;
import javax.lang.model.element.Name;
import javax.lang.model.element.TypeElement;
import javax.lang.model.type.TypeMirror;
import javax.lang.model.util.Elements;

/**
 * @Auther JiRui
 * @CreateTime 2019/5/14 14:46
 * @Describe
 */
public class NavigationClass {

    public Element mClassElement;
    /**
     * 元素相关的辅助类
     */
    public Elements mElementUtils;

    public TypeMirror elementType;

    public Name elementName;

    public NavigationClass(Element classElement) {
        this.mClassElement = classElement;
        this.elementType = classElement.asType();
        this.elementName = classElement.getSimpleName();

        classElement.getAnnotation(NavService.class);
    }

    protected Name getElementName() {
        return elementName;
    }

    protected TypeMirror getElementType(){
        return elementType;
    }


    /**
     * 包名
     */
    public String getPackageName(TypeElement type) {
        return mElementUtils.getPackageOf(type).getQualifiedName().toString();
    }
    /**
     * 类名
     */
    private static String getClassName(TypeElement type, String packageName) {
        int packageLen = packageName.length() + 1;
        return type.getQualifiedName().toString().substring(packageLen).replace('.', '$');
    }
}
