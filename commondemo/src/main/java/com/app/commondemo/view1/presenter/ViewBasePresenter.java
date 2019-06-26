package com.app.commondemo.view1.presenter;

import com.app.basecommon.base.BasePresenter;
import com.app.commondemo.view1.model.ViewBaseModel;

import androidx.databinding.ViewDataBinding;

/**
 * @Auther JiRui
 * @CreateTime 2019/5/27 15:34
 * @Describe
 */
public class ViewBasePresenter extends BasePresenter {


    private ViewDataBinding mViewDataBinding;
    private ViewBaseModel mViewBaseModel;

    public String tvInfo;

    public ViewBasePresenter(ViewDataBinding viewDataBinding, ViewBaseModel viewBaseModel) {
        mViewDataBinding = viewDataBinding;
        mViewBaseModel = viewBaseModel;
    }

    @Override
    public void initData() {
        tvInfo = "1.View的坐标系\n" +
                "  getTop();       //获取子View左上角距父View顶部的距离\n" +
                "  getLeft();      //获取子View左上角距父View左侧的距离\n" +
                "  getBottom();    //获取子View右下角距父View顶部的距离\n" +
                "  getRight();     //获取子View右下角距父View左侧的距离\n\n" +
                "2.MotionEvent中   //get 和 getRaw 的区别\n" +
                "  event.getX();       //触摸点相对于其所在当前组件坐标系的坐标\n" +
                "  event.getY();       //触摸点相对于其所在当前组件坐标系的坐标\n" +
                "  event.getRawX();    //触摸点相对于屏幕默认坐标系的坐标\n" +
                "  event.getRawY();    //触摸点相对于屏幕默认坐标系的坐标\n\n" +
                "3.角度和弧度的换算关系\n" +
                "   圆一周对应的角度为360度(角度)，对应的弧度为2π弧度。\n" +
                "   故得等价关系:360(角度) = 2π(弧度) ==> 180(角度) = π(弧度)\n\n" +
                "4.自定义View进阶-分类与流程\n" +
                "   onMeasure()         测量view的大小\n"+
                "   onSizeChanged()     确定view的大小\n"+
                "   onLayout()          确定view的布局位置，包括子view\n\n"+
                "   //取出宽度的确切数值\n" +
                "   int widthsize  MeasureSpec.getSize(widthMeasureSpec);\n" +
                "   //取出宽度的测量模式\n"+
                "   int widthmode  MeasureSpec.getMode(widthMeasureSpec);\n" +
                "    \n" +
                "   在实际运用之中只需要记住有三种模式:\n" +
                "       UNSPECIFIED: 默认值，父控件没有给子view任何限制，子View可以设置为任意大小。\n" +
                "       EXACTLY: 表示父控件已经确切的指定了子View的大小。(100dp)\n" +
                "       AT_MOST: 表示子View具体大小没有尺寸限制，但是存在上限，上限一般为父View大小。(match_parent,warp_content)\n\n" +
                "   (注：)用 MeasureSpec 的 getSize是获取数值， getMode是获取模式即可。\n"+
                "   如果对View的宽高进行修改了，不要调用 super.onMeasure( widthMeasureSpec, heightMeasureSpec); 要调用 setMeasuredDimension( widthsize, heightsize); 这个函数。 \n" +
                "5.Canvas的常用操作速查表    \n" +
                "  绘制颜色:drawColor, drawRGB, drawARGB  \n" +
                "  使用单一颜色填充整个画布  \n\n" +
                "  绘制基本形状:drawPoint, drawPoints, drawLine, drawLines, drawRect, drawRoundRect, drawOval, drawCircle, drawArc \n" +
                "  依次为 点、线、矩形、圆角矩形、椭圆、圆、圆弧  \n\n" +
                "  绘制图片:drawBitmap, drawPicture  \n" +
                "  绘制位图和图片  \n\n" +
                "  绘制文本:drawText, drawPosText, drawTextOnPath  \n" +
                "  依次为 绘制文字、绘制文字时指定每个文字位置、根据路径绘制文字  \n\n" +
                "  绘制路径:drawPath  \n" +
                "  绘制路径，绘制贝塞尔曲线时也需要用到该函数  \n\n" +
                "  顶点操作:drawVertices, drawBitmapMesh  \n" +
                "  通过对顶点操作可以使图像形变，drawVertices直接对画布作用、 drawBitmapMesh只对绘制的Bitmap作用  \n\n" +
                "  画布剪裁:clipPath, clipRect  \n" +
                "  设置画布的显示区域  \n\n" +
                "  画布快照:save, restore, saveLayerXxx, restoreToCount, getSaveCount \n" +
                "  依次为 保存当前状态、 回滚到上一次保存的状态、 保存图层状态、 回滚到指定状态、 获取保存次数  \n\n" +
                "  画布变换:translate, scale, rotate, skew  \n" +
                "  依次为 位移、缩放、 旋转、错切  \n\n" +
                "  Matrix(矩阵):getMatrix, setMatrix, concat  \n" +
                "  实际上画布的位移，缩放等操作的都是图像矩阵Matrix， 只不过Matrix比较难以理解和使用，故封装了一些常用的方法。  \n\n" +
                "6.canvas缩放(scale)    \n" +
                "  scale (float sx, float sy) 前两个参数是相同的分别为x轴和y轴的缩放比例\n" +
                "  scale (float sx, float sy, float px, float py) 第二种方法比前一种多了两个参数，用来控制缩放中心位置的\n" +
                "  缩放比例(sx,sy)取值范围详解 :\n" +
                "  (-∞, -1) 先根据缩放中心放大n倍，再根据中心轴进行翻转\n" +
                "  -1 根据缩放中心轴进行翻转\n" +
                "  (-1, 0) 先根据缩放中心缩小到n，再根据中心轴进行翻转\n" +
                "  0 不会显示，若sx为0，则宽度为0，不会显示，sy同理  \n" +
                "  (0, 1) 根据缩放中心缩小到n\n" +
                "  1 没有变化\n" +
                "  (1, +∞) 根据缩放中心放大n倍\n" +
                "\n" +
                "\n" +
                "\n" +
                "\n" +
                "\n" +
                "\n" +
                "\n" +
                "\n";

    }

    @Override
    public void getData() {

    }


}
