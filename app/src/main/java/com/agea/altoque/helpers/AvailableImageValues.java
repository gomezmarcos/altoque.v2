package com.agea.altoque.helpers;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lbais on 29/05/2015.
 */
public class AvailableImageValues {

    private List<Integer> values = new ArrayList<Integer>();

    public AvailableImageValues(int[] values) {
        for (int i = 0; i < values.length; i++) {
            this.values.add(values[i]);
        }
    }

    public boolean containsIcon(int icons) {
        return values.contains(icons);
    }
}
