package com.xiaoqlu.motionevent;


import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
/**
 * 事件分发机制（先分发事件、再消费事件、最后可形成一个循环--这种情况就是最外层的view分发事件，但是没有view消费，最后还是有最外层的view来消费的情况）：
 * 三层View，从外到内依次为： LayoutView1、LayoutView2、MyTextView。
 * 分别使用view1、view2、view3代替。
 * 最外层view1--调用dispatchTouchEvent分发事件--onInterceptTouchEvent返回false--继续往下分发事件
 * --view2调用dispatchTouchEvent分发事件--onInterceptTouchEvent返回false--继续往下分发事件
 * --view3调用dispatchTouchEvent分发事件--调用onTouchEvent事件 if返回false（如果返回true，则在此view3消费该事件）
 * --返回到view2中调用onTouchEvent事件 if返回false（如果true，则在此view2消费该事件）
 * --返回到view1中调用onTouchEvent事件 if返回false（如果true，则在此view1消费该事件）
 * --返回到MotionEventActivity中调用onTouchEvent事件，无论返回true or false都最终由该Activity消费该事件。
 * 
 * @author zhongyao
 *
 */
public class MotionEventTestActivity extends Activity {

    public static final String TAG = "yao";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
     }
    
    @Override
    public boolean onTouchEvent(MotionEvent event) {
    	Log.d(TAG, "Activity--onTouchEvent");
    	 int action = event.getAction();
         switch(action){
         case MotionEvent.ACTION_DOWN:
             Log.d(TAG,TAG+"Activity--onTouchEvent action:ACTION_DOWN");
             break;
         case MotionEvent.ACTION_MOVE:
             Log.d(TAG,TAG+"Activity--onTouchEvent action:ACTION_MOVE");
             break;
         case MotionEvent.ACTION_UP:
             Log.d(TAG,TAG+"Activity--onTouchEvent action:ACTION_UP");
             break;
         case MotionEvent.ACTION_CANCEL:
             Log.d(TAG,TAG+"Activity--onTouchEvent action:ACTION_CANCEL");
             break;
         }     
    	return super.onTouchEvent(event);
    }
 }
