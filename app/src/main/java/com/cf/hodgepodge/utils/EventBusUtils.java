package com.cf.hodgepodge.utils;

import org.greenrobot.eventbus.EventBus;

/**
 * Created by cf on 2018/1/20.
 */

public class EventBusUtils {

    /**
     * 注册EventBus
     */
    public static void register(Object subscriber) {
        if (!EventBus.getDefault().isRegistered(subscriber)) {
            EventBus.getDefault().register(subscriber);
        }
    }

    /**
     * 取消注册EventBus
     */
    public static void unregister(Object subscriber) {
        EventBus.getDefault().unregister(subscriber);
    }

    /**
     * 发布订阅事件
     */
    public static void post(Object event) {
        EventBus.getDefault().post(event);
    }

    /**
     * 发布粘性订阅事件
     */
    public static void postSticky(Object event) {
        EventBus.getDefault().postSticky(event);
    }

    /**
     * 移除指定的粘性订阅事件
     */
    public static <T> void removeStickyEvent(Class<T> eventType) {
        T stickyEvent = EventBus.getDefault().getStickyEvent(eventType);
        if (stickyEvent != null) {
            EventBus.getDefault().removeStickyEvent(stickyEvent);
        }
    }

    public static <T> T getStickyEvent(Class<T> eventType){
        return EventBus.getDefault().getStickyEvent(eventType);
    }
    /**
     * 取消事件传送
     */
    public static void cancelEventDelivery(Object event) {
        EventBus.getDefault().cancelEventDelivery(event);
    }

    /**
     * 移除所有的粘性订阅事件
     */
    public static void removeAllStickyEvents() {
        EventBus.getDefault().removeAllStickyEvents();
    }
}
