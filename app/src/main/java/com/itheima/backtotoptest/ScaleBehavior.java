package com.itheima.backtotoptest;

import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPropertyAnimatorCompat;
import android.support.v4.view.ViewPropertyAnimatorListener;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by sszz on 2017/2/7.
 */

public class ScaleBehavior extends FloatingActionButton.Behavior{
	private boolean isAnimate=false;
	//如果是把Behavior定义在xml文件中必须重写当前Behavior的两个参数的构造

	public ScaleBehavior(Context context, AttributeSet attrs) {
		super(context, attrs);
	}
	//当开始嵌套滚动的时候
	//判断滚动的方向
	//nestedScrollAxes:嵌套滚动的方向
	@Override
	public boolean onStartNestedScroll(CoordinatorLayout coordinatorLayout, FloatingActionButton child, View directTargetChild, View target, int nestedScrollAxes) {
		//判断滚动的方向是否是垂直方向
		return nestedScrollAxes== ViewCompat.SCROLL_AXIS_VERTICAL;
	}
	//已经处于嵌套滚动中
	@Override
	public void onNestedScroll(CoordinatorLayout coordinatorLayout, FloatingActionButton child, View target, int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed) {
		super.onNestedScroll(coordinatorLayout, child, target, dxConsumed, dyConsumed, dxUnconsumed, dyUnconsumed);
		//判断如果是向上滚动,则让FAB放大
		//否则让FAB缩小

		if(((dyConsumed>0&&dyUnconsumed==0)||(dyConsumed==0&&dyUnconsumed>0))&&!isAnimate){
			//让FAB放大
			AnimatorUtil.scaleShow(child,listener);
		}else if(((dyConsumed<0&&dyUnconsumed==0)||(dyConsumed==0&&dyUnconsumed<0))&&!isAnimate){
			AnimatorUtil.scaleHide(child,listener);
		}
	}
	private ViewPropertyAnimatorListener listener=new ViewPropertyAnimatorListener() {
		@Override
		public void onAnimationStart(View view) {
			isAnimate=true;
		}

		@Override
		public void onAnimationEnd(View view) {
			isAnimate=false;
		}

		@Override
		public void onAnimationCancel(View view) {
			isAnimate=false;
		}
	};
}
