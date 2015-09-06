package com.cacoveanu.makeitso.getobject;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

/**
 * Created by silviu on 2015-09-06.
 */
public class GetObjectImplementation {

    public class GetMinMax {
        private Integer min;
        private Integer max;

        public GetMinMax(Collection<Integer> numbers) {
            Iterator<Integer> iterator = numbers.iterator();
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
        }

        public Integer getMin() {
            return min;
        }

        public Integer getMax() {
            return max;
        }
    }

    @Test
    public void findMinMaxTest() throws Exception {
        List<Integer> numbers = Arrays.asList(new Integer[] {1, 2, 3, 4, 5, 6, 7, 8, 9});
        GetMinMax result = new GetMinMax(numbers);
        Assert.assertEquals((int) result.getMin(), 1);
        Assert.assertEquals((int) result.getMax(), 9);
    }
}
