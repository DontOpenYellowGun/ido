package com.kelin.mvvmlight.messenger;

import java.lang.ref.WeakReference;

import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;


/**
 * Created by kelin on 15-8-14.
 */
public class WeakAction<T> {
    private Action action;
    private Consumer<T> Consumer;
    private boolean isLive;
    private Object target;
    private WeakReference reference;

    public WeakAction(Object target, Action action) {
        reference = new WeakReference(target);
        this.action = action;

    }

    public WeakAction(Object target, Consumer<T> Consumer) {
        reference = new WeakReference(target);
        this.Consumer = Consumer;
    }

    public void execute() {
        if (action != null && isLive()) {
            try {
                action.run();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void execute(T parameter) {
        if (Consumer != null
                && isLive()) {
            try {
                Consumer.accept(parameter);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void markForDeletion() {
        reference.clear();
        reference = null;
        action = null;
        Consumer = null;
    }

    public Action getAction() {
        return action;
    }

    public Consumer getConsumer() {
        return Consumer;
    }

    public boolean isLive() {
        if (reference == null) {
            return false;
        }
        if (reference.get() == null) {
            return false;
        }
        return true;
    }


    public Object getTarget() {
        if (reference != null) {
            return reference.get();
        }
        return null;
    }
}
