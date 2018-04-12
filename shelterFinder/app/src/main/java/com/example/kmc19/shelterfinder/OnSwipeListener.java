package com.example.kmc19.shelterfinder;

import android.content.Context;
import android.view.GestureDetector;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.Toast;

class OnSwipeListener implements OnTouchListener {

    private final GestureDetector gestureDetector;
    private Context context;

    public boolean onTouch(final View view, final MotionEvent motionEvent) {
        return gestureDetector.onTouchEvent(motionEvent);
    }

    public GestureDetector getGestureDetector() {
        return gestureDetector;
    }

    public OnSwipeListener(Context context) {
        super();
        this.context = context;
        gestureDetector = new GestureDetector(context, new GestureListener());
    }

    private final class GestureListener extends SimpleOnGestureListener {
        private static final int SWIPE_THRESHOLD = 100;
        private static final int SWIPE_VELOCITY_THRESHOLD = 100;

        @Override
        public boolean onDown(MotionEvent e) {
            return true;
        }

        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            boolean result = false;
            try {
                float diffX = e2.getRawX() - e1.getRawX();
                float diffY = e2.getRawY() - e1.getRawY();
                if ((Math.abs(diffX) - Math.abs(diffY)) > SWIPE_THRESHOLD) {
                    if (Math.abs(diffX)
                            > SWIPE_THRESHOLD && Math.abs(velocityX) > SWIPE_VELOCITY_THRESHOLD) {
                        if (diffX > 0) {
                            onSwipeRight();
                        } else {
                            onSwipeLeft();
                        }
                        result = true;
                    }
                } else if (Math.abs(diffY)
                        > SWIPE_THRESHOLD && Math.abs(velocityY) > SWIPE_VELOCITY_THRESHOLD) {
                    if (diffY > 0) {
                        onSwipeBottom();
                    } else {
                        onSwipeTop();
                    }
                }
            } catch (Exception e) {
                int duration = Toast.LENGTH_LONG;
                Toast.makeText(context, e.getMessage(), duration).show();
            }
            return result;
        }
    }

    void onSwipeRight() {
    }

    void onSwipeLeft() {
    }

    private void onSwipeBottom() {
    }

    private void onSwipeTop() {
    }
}
