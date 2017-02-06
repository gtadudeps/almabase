package com.github.utils;

/*
 * Created by pmathur on 4/2/17.
 */
public interface Adapter<InputType, OutputType> {

    OutputType adapt(InputType input);

}
