package eu.inloop.hammer;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.view.View;

import java.util.ArrayList;

public class ContentSwitcher {
    private ArrayList<View> viewsList = new ArrayList<>();

    protected int activeViewIndex;

    private long transitionDuration;

    public ContentSwitcher(long transitionDuration)
    {
        this.transitionDuration = transitionDuration;
    }

    public int register(View view) {
        int index = viewsList.size();

        viewsList.add(view);
        view.setVisibility(View.INVISIBLE);

        return index;
    }

    public void setTo(int index) {
        View activeView = viewsList.get(activeViewIndex);
        activeView.setVisibility(View.INVISIBLE);

        View view = viewsList.get(index);
        view.setVisibility(View.VISIBLE);
        activeViewIndex = index;
    }

    public void changeTo(final int index) {
        final int fromIndex = activeViewIndex;

        if (fromIndex == index) {
            return;
        }

        final View viewFrom = viewsList.get(fromIndex);
        if (viewFrom != null) {
            viewFrom.animate()
                    .alpha(0)
                    .setDuration(transitionDuration)
                    .setListener(new AnimatorListenerAdapter() {
                        @Override
                        public void onAnimationEnd(Animator animation) {
                            viewFrom.setVisibility(View.INVISIBLE);
                        }
                    });
        }

        final View viewTo = viewsList.get(index);
        viewTo.setAlpha(0);
        viewTo.animate()
                .alpha(1)
                .setDuration(transitionDuration)
                .setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationStart(Animator animation) {
                        viewTo.setVisibility(View.VISIBLE);
                    }
                });

        activeViewIndex = index;
    }
}
