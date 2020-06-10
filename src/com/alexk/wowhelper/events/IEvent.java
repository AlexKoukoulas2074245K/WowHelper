package com.alexk.wowhelper.events;

public interface IEvent<L>
{
    void notify(final L listener);
}
