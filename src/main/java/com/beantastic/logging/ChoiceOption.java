package com.beantastic.logging;

import java.util.function.Supplier;

public record ChoiceOption<R> (String description, Supplier<R> outcome){ }
