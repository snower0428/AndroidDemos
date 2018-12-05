package com.lh.demos.animation;

/**
 * Created by leihui on 2018/11/26.
 * Animation描述
 */

public class AnimationDesc {

    /*
     * 在Android动画中，总共有两种类型的动画View Animation(视图动画)和Property Animator(属性动画)。
     *
     * View Animation包括Tween Animation（补间动画）和Frame Animation(逐帧动画)。
     * Property Animator包括ValueAnimator和ObjectAnimation。
     *
     * 直观上，它们有如下三点不同：
     * 1.引入时间不同：View Animation是API Level 1就引入的。
     *   Property Animation是API Level 11引入的，即Android 3.0才开始有Property Animation相关的API。
     * 2.所在包名不同：View Animation在包android.view.animation中。而Property Animation API在包 android.animation中。
     * 3.动画类的命名不同：View Animation中动画类取名都叫XXXXAnimation,而在Property Animator中动画类的取名则叫XXXXAnimator
     *
     * 为什么引入Property Animator(属性动画)：
     * 1.Property Animator能实现补间动画无法实现的功能。
     * 2.View Animation仅能对指定的控件做动画，而Property Animator是通过改变控件某一属性值来做动画的。
     * 3.补间动画虽能对控件做动画，但并没有改变控件内部的属性值。
     *   而Property Animator则是恰恰相反，Property Animator是通过改变控件内部的属性值来达到动画效果的
     *
     * ************************************************************
     */

    /*
     * 以下这四种类型属于Tween Animation（补间动画）：
     *
     * Android的animation由四种类型组成：alpha、scale、translate、rotate
     * 1.alpha 渐变透明度动画效果
     * 2.scale 渐变尺寸伸缩动画效果
     * 3.translate 画面转换位置移动动画效果
     * 4.rotate 画面转移旋转动画效果
     *
     * ************************************************************
     */

    /*
     * 一、scale标签——调节尺寸
     * 1.自有属性
     * scale标签是缩放动画，可以实现动态调控件尺寸的效果，有下面几个属性：
     * android:fromXScale 起始的X方向上相对自身的缩放比例，浮点值，
     *                    比如1.0代表自身无变化，0.5代表起始时缩小一倍，2.0代表放大一倍；
     * android:toXScale 结尾的X方向上相对自身的缩放比例，浮点值；
     * android:fromYScale 起始的Y方向上相对自身的缩放比例，浮点值，
     * android:toYScale 结尾的Y方向上相对自身的缩放比例，浮点值；
     * android:pivotX 缩放起点X轴坐标，可以是数值、百分数、百分数p 三种样式，比如 50、50%、50%p，
     *                当为数值时，表示在当前View的左上角，即原点处加上50px，做为起始缩放点；
     *                如果是50%，表示在当前控件的左上角加上自己宽度的50%做为起始点；
     *                如果是50%p，那么就是表示在当前的左上角加上父控件宽度的50%做为起始点x轴坐标。
     * android:pivotY 缩放起点Y轴坐标，取值及意义跟android:pivotX一样。
     *
     * 2.从Animation类继承的属性
     * Animation类是所有动画（scale、alpha、translate、rotate）的基类，
     * 这里以scale标签为例，讲解一下，Animation类所具有的属性及意义。
     * android:duration 动画持续时间，以毫秒为单位 
     * android:fillAfter 如果设置为true，控件动画结束时，将保持动画最后时的状态
     * android:fillBefore 如果设置为true，控件动画结束时，还原到开始动画前的状态
     * android:fillEnabled 与android:fillBefore 效果相同，都是在动画结束时，将控件还原到初始化状态
     * android:repeatCount 重复次数
     * android:repeatMode 重复类型，有reverse和restart两个值，
     *                    reverse表示倒序回放，restart表示重新放一遍，必须与repeatCount一起使用才能看到效果。
     *                    因为这里的意义是重复的类型，即回放时的动作。
     * android:interpolator 设定插值器，其实就是指定的动作效果，比如弹跳效果等。
     *
     * 二、alpha标签——调节透明度
     * 1.自身属性
     * android:fromAlpha 动画开始的透明度，从0.0 --1.0 ，0.0表示全透明，1.0表示完全不透明
     * android:toAlpha 动画结束时的透明度，也是从0.0 --1.0 ，0.0表示全透明，1.0表示完全不透明
     *
     * 2.从Animation类继承的属性
     * android:duration 动画持续时间，以毫秒为单位 
     * android:fillAfter 如果设置为true，控件动画结束时，将保持动画最后时的状态
     * android:fillBefore 如果设置为true,控件动画结束时，还原到开始动画前的状态
     * android:fillEnabled 与android:fillBefore 效果相同，都是在动画结束时，将控件还原到初始化状态
     * android:repeatCount 重复次数
     * android:repeatMode 重复类型，有reverse和restart两个值，
     *                    reverse表示倒序回放，restart表示重新放一遍，必须与repeatCount一起使用才能看到效果。
     *                    因为这里的意义是重复的类型，即回放时的动作。
     * android:interpolator 设定插值器，其实就是指定的动作效果，比如弹跳效果等。
     *
     * 三、rotate标签——旋转
     * 1.自身属性
     * android:fromDegrees 开始旋转的角度位置，正值代表顺时针方向度数，负值代码逆时针方向度数
     * android:toDegrees 结束时旋转到的角度位置，正值代表顺时针方向度数，负值代码逆时针方向度数
     * android:pivotX 缩放起点X轴坐标，可以是数值、百分数、百分数p 三种样式，比如 50、50%、50%p
     * android:pivotY 缩放起点Y轴坐标，可以是数值、百分数、百分数p 三种样式，比如 50、50%、50%p
     *
     * 2.从Animation类继承的属性
     * android:duration 动画持续时间，以毫秒为单位 
     * android:fillAfter 如果设置为true，控件动画结束时，将保持动画最后时的状态
     * android:fillBefore 如果设置为true,控件动画结束时，还原到开始动画前的状态
     * android:fillEnabled 与android:fillBefore 效果相同，都是在动画结束时，将控件还原到初始化状态
     * android:repeatCount 重复次数
     * android:repeatMode 重复类型，有reverse和restart两个值，
     *                    reverse表示倒序回放，restart表示重新放一遍，必须与repeatCount一起使用才能看到效果。
     *                    因为这里的意义是重复的类型，即回放时的动作。
     * android:interpolator 设定插值器，其实就是指定的动作效果，比如弹跳效果等。
     *
     * 四、translate标签 —— 平移
     * 1.自身属性
     * android:fromXDelta 起始点X轴坐标，可以是数值、百分数、百分数p 三种样式，比如 50、50%、50%p
     * android:fromYDelta 起始点Y轴从标，可以是数值、百分数、百分数p 三种样式；
     * android:toXDelta 结束点X轴坐标
     * android:toYDelta 结束点Y轴坐标
     *
     * 2.从Animation类继承的属性
     * android:duration 动画持续时间，以毫秒为单位 
     * android:fillAfter 如果设置为true，控件动画结束时，将保持动画最后时的状态
     * android:fillBefore 如果设置为true,控件动画结束时，还原到开始动画前的状态
     * android:fillEnabled 与android:fillBefore 效果相同，都是在动画结束时，将控件还原到初始化状态
     * android:repeatCount 重复次数
     * android:repeatMode 重复类型，有reverse和restart两个值，reverse表示倒序回放，
     *                    restart表示重新放一遍，必须与repeatCount一起使用才能看到效果。
     *                    因为这里的意义是重复的类型，即回放时的动作。
     * android:interpolator 设定插值器，其实就是指定的动作效果，比如弹跳效果等。
     *
     * 六、set标签——定义动作合集
     * set标签自己是没有属性的，它的属性都是从Animation继承而来，但当它们用于Set标签时，就会对Set标签下的所有子控件都产生作用。
     *
     * 属性有：（从Animation类继承的属性）
     * android:duration 动画持续时间，以毫秒为单位 
     * android:fillAfter 如果设置为true，控件动画结束时，将保持动画最后时的状态
     * android:fillBefore 如果设置为true,控件动画结束时，还原到开始动画前的状态
     * android:fillEnabled 与android:fillBefore 效果相同，都是在动画结束时，将控件还原到初始化状态
     * android:repeatCount 重复次数
     * android:repeatMode 重复类型，有reverse和restart两个值，reverse表示倒序回放，
     *                    restart表示重新放一遍，必须与repeatCount一起使用才能看到效果。
     *                    因为这里的意义是重复的类型，即回放时的动作。
     * android:interpolator 设定插值器，其实就是指定的动作效果，比如弹跳效果等。
     *
     * ************************************************************
     */

    /*
     * Interpolator插值器
     *
     * AccelerateDecelerateInterpolator 在动画开始与结束的地方速率改变比较慢，在中间的时候加速
     * AccelerateInterpolator 在动画开始的地方速率改变比较慢，然后开始加速
     * AnticipateInterpolator 开始的时候向后然后向前甩
     * AnticipateOvershootInterpolator 开始的时候向后然后向前甩一定值后返回最后的值
     * BounceInterpolator 动画结束的时候弹起
     * CycleInterpolator 动画循环播放特定的次数，速率改变沿着正弦曲线
     * DecelerateInterpolator 在动画开始的地方快然后慢
     * LinearInterpolator 以常量速率改变
     * OvershootInterpolator 向前甩一定值后再回到原来位置
     *
     * ************************************************************
     */

    /*
     * 各个标签对应的类：
     * scale —— ScaleAnimation
     * alpha —— AlphaAnimation
     * rotate —— RotateAnimation
     * translate —— TranslateAnimation
     * set —— AnimationSet
     *
     * ************************************************************
     */

    /*
     * ValueAnimator常用函数：
     *
     * // 设置动画时长，单位是毫秒
     * ValueAnimator setDuration(long duration)
     *
     * // 获取ValueAnimator在运动时，当前运动点的值
     * Object getAnimatedValue();
     *
     * // 开始动画
     * void start()
     *
     * // 设置循环次数,设置为INFINITE表示无限循环
     * void setRepeatCount(int value)
     *
     * // 设置循环模式
     * // value取值有RESTART，REVERSE，
     * void setRepeatMode(int value)
     *
     * // 取消动画
     * void cancel()
     *
     * 其它函数：
     *
     * // 延时多久时间开始，单位是毫秒
     * public void setStartDelay(long startDelay)
     *
     * // 完全克隆一个ValueAnimator实例，包括它所有的设置以及所有对监听器代码的处理
     * public ValueAnimator clone()
     *
     * ************************************************************
     */

    /*
     * 我们可以通过重写加速器改变数值进度来改变数值位置，也可以通过改变Evaluator中进度所对应的数值来改变数值位置
     *
     * ************************************************************
     */
}
