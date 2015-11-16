package eu.inloop.view;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.view.View;

public class ContentSwitcher {
    protected View activeView;

    private long transitionDuration;

    public ContentSwitcher(long transitionDuration) {
        this.transitionDuration = transitionDuration;
    }

    public void prepare(View... views) {
        for (View view : views) {
            view.setVisibility(View.INVISIBLE);
        }
    }

    public void setTo(View view) {
        if (activeView != null) {
            activeView.setVisibility(View.INVISIBLE);
        }
        view.setVisibility(View.VISIBLE);
        this.activeView = view;
    }

    public void changeTo(View view) {
        if (view == activeView) {
            return;
        }

        runTransition(activeView, view);

        this.activeView = view;
    }

    private void runTransition(final View from, final View to) {
        if (from != null) {
            from.animate()
                    .alpha(0)
                    .setDuration(transitionDuration)
                    .setListener(new AnimatorListenerAdapter() {
                        @Override
                        public void onAnimationEnd(Animator animation) {
                            from.setVisibility(View.INVISIBLE);
                            from.setAlpha(1);
                        }
                    });
        }

        to.setAlpha(0);
        to.animate()
                .alpha(1)
                .setDuration(transitionDuration)
                .setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationStart(Animator animation) {
                        to.setVisibility(View.VISIBLE);
                    }
                });
    }
}
