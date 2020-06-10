package com.alexk.wowhelper.events;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public final class EventSystem
{
    private static final Map<Class, ArrayList> mEventListenerMap = new HashMap<Class, ArrayList>();

    public static <L> void subscribeToEvent(Class<? extends IEvent<L>> eventClass, L listener)
    {
        final ArrayList<L> listeners = getListenersForEvent(eventClass);
        synchronized (listeners)
        {
            if (!listeners.contains(listener))
            {
                listeners.add(listener);
            }
        }
    }

    public static <L> void unsubscribeFromEvent(Class<? extends IEvent<L>> eventClass, L listener)
    {
        final ArrayList<L> listeners = getListenersForEvent(eventClass);
        synchronized (listeners)
        {
            listeners.remove(listener);
        }
    }

    public static <L> void dispatchEvent(final IEvent<L> evt)
    {
        @SuppressWarnings("unchecked")
        Class<IEvent<L>> eventClass = (Class<IEvent<L>>) evt.getClass();

        for (L listener : getListenersForEvent(eventClass))
        {
            evt.notify(listener);
        }
    }

    private static <L> ArrayList<L> getListenersForEvent(Class<? extends IEvent<L>> eventClass)
    {
        synchronized (mEventListenerMap)
        {
            @SuppressWarnings("unchecked")
            final ArrayList<L> existingListeners = mEventListenerMap.get(eventClass);
            if (existingListeners != null)
            {
                return existingListeners;
            }

            final ArrayList<L> newListenersList = new ArrayList<L>();
            mEventListenerMap.put(eventClass, newListenersList);
            return newListenersList;
        }
    }
}