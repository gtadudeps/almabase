package com.github.utils;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.apache.commons.collections.CollectionUtils;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/*
 * Created by pmathur on 4/2/17.
 */
public class AppCollectionUtils {


    public static <Key, Value extends Base, Base> Map<Key, List<Value>> groupByKey(Collection<Value> data,
                                                                                   Adapter<Base, Key> adapter) {
        if (CollectionUtils.isEmpty(data)) {
            return Collections.emptyMap();
        }
        Map<Key, List<Value>> rv = Maps.newHashMap();
        for (Value v : data) {
            final Key key = adapter.adapt(v);
            if (key != null) {
                if (v != null) {
                    addToMultivaluedMapList(rv, key, v);
                }
            }
        }
        return rv;
    }



    public static <Key, Value> void addToMultivaluedMapList(Map<Key, List<Value>> map, Key key, Value value) {
        if (map == null) {
            return;
        }
        if (key == null || value == null) {
            return;
        }

        synchronized (map) {
            List<Value> values = map.get(key);
            if (values == null) {
                values = Lists.newLinkedList();
                map.put(key, values);
            }
            values.add(value);
        }
    }



}
