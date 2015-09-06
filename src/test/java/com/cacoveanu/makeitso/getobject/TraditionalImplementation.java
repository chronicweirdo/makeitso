package com.cacoveanu.makeitso.getobject;

import org.junit.Assert;
import org.junit.Test;

import java.util.*;

/**
 * Created by silviu on 2015-09-06.
 */
public class TraditionalImplementation {

    public class MinMaxTuple {
        private Integer min;
        private Integer max;

        public MinMaxTuple(Integer min, Integer max) {
            this.min = min;
            this.max = max;
        }

        public Integer getMin() {
            return min;
        }

        public Integer getMax() {
            return max;
        }
    }

    public MinMaxTuple findMinMax(Collection<Integer> numbers) {
        Iterator<Integer> iterator = numbers.iterator();
        Integer min, max;
        if (iterator.hasNext()) {
            Integer number = iterator.next();
            min = number;
            max = number;
        } else {
            min = 0;
            max = 0;
        }
        while (iterator.hasNext()) {
            Integer number = iterator.next();
            if (number < min) {
                min = number;
            }
            if (number > max) {
                max = number;
            }
        }
        return new MinMaxTuple(min, max);
    }

    @Test
    public void findMinMaxTest() throws Exception {
        List<Integer> numbers = Arrays.asList(new Integer[] {1, 2, 3, 4, 5, 6, 7, 8, 9});
        MinMaxTuple result = findMinMax(numbers);
        Assert.assertEquals((int) result.getMin(), 1);
        Assert.assertEquals((int) result.getMax(), 9);
    }
}
