package com.humanbooster.slideshowplayer.model;

import java.util.Comparator;
import java.util.Objects;

/**
 * Order SlideElements by ZIndex (ASC)
 */
public class ZIndexAscendantComparator implements Comparator<SlideElement> {
    /**
     * o1.getZIndex() > o2.getZIndex() => 1
     * o1.getZIndex() = o2.getZIndex() => 0
     * o1.getZIndex() < o2.getZIndex() => -1
     * @param o1
     * @param o2
     * @return
     */
    @Override
    public int compare(SlideElement o1, SlideElement o2) {
        Objects.requireNonNull(o1);
        Objects.requireNonNull(o2);
        return o1.getZIndex() - o2.getZIndex();
    }
}
