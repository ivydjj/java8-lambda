package com.djj.languagepoints.chapter5.customcollect;

public class StringCombiner {
    private StringBuilder builder;
    private String prefix;
    private String suffix;
    private String delim; // 分隔符

    public StringCombiner(String prefix, String suffix, String delim) {
        builder = new StringBuilder();

        this.prefix = prefix;
        this.suffix = suffix;
        this.delim = delim;
    }

    public StringCombiner add(String element) {
        if (!this.areAtStart()) {
            builder.append(delim);
        }
        builder.append(element);
        return this;
    }

    // 合并
    public StringCombiner merge(StringCombiner other) {
        if(!other.equals(this)) {
            if(!other.areAtStart() && !this.areAtStart()){
                other.builder.insert(0, this.delim);
            }
            this.builder.append(other.builder);
        }
        return this;
    }

    @Override
    public String toString() {
        return prefix + builder.toString() + suffix;
    }

    private boolean areAtStart() {
        return builder.length() == 0;
    }

}
